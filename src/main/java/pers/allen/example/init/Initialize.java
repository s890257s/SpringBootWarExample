package pers.allen.example.init;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import pers.allen.example.member.model.bean.Likes;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.LikesDAO;
import pers.allen.example.member.model.dao.MemberDAO;
import pers.allen.example.member.model.dao.MemberDetailDAO;
import pers.allen.example.pet.model.dao.PetDAO;

@Component
public class Initialize implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MemberDAO mDAO;
	@Autowired
	private MemberDetailDAO mDDAO;

	@Autowired
	private PetDAO pDAO;

	@Autowired
	private LikesDAO lDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ServletContext servletContext = ((WebApplicationContext) event.getApplicationContext()).getServletContext();
		servletContext.setAttribute("root", servletContext.getContextPath());
		servletContext.setAttribute("webName", "Doge寵物交流園地");
	
		insertDataIntoDB();
	}

	/**
	 * 讀取resources/init目錄下的資料.json檔案，<br>
	 * 使用Gson解析成對應的List<Member>，並新增至資料庫。<br>
	 */
	private void insertDataIntoDB() {

		if (mDAO.count() != 0 || mDDAO.count() != 0 || pDAO.count() != 0 || lDAO.count() != 0) {
			return;
		}
		try {

			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm").create();

			BufferedReader mBR = new BufferedReader(
					new FileReader(ResourceUtils.getFile("classpath:init\\Member.json")));
			List<Member> mList = gson.fromJson(mBR, new TypeToken<List<Member>>() {
			}.getType());
			mBR.close();

			BufferedReader lBR = new BufferedReader(
					new FileReader(ResourceUtils.getFile("classpath:init\\Likes.json")));
			List<Likes> lList = gson.fromJson(lBR, new TypeToken<List<Likes>>() {
			}.getType());
			lBR.close();

			mList.stream().forEach(m -> {
				try {
					String mPhotoPath = "classpath:init\\photo\\user\\" + m.getMemberDetail().getPhoto() + ".png";
					BufferedInputStream mBis = new BufferedInputStream(
							new FileInputStream(ResourceUtils.getFile(mPhotoPath)));
					m.getMemberDetail().setPhoto(
							"data:image/png;base64," + Base64.getEncoder().encodeToString(mBis.readAllBytes()));
					mBis.close();

					m.getMemberDetail().setMember(m);

					m.getPets().stream().forEach(p -> {
						try {
							p.setMember(m);
							String pPhotoPath = "classpath:init\\photo\\" + p.getType() + "\\" + p.getType() + "-"
									+ p.getName() + ".jpg";
							BufferedInputStream pBis = new BufferedInputStream(
									new FileInputStream(ResourceUtils.getFile(pPhotoPath)));

							p.setPhoto(pBis.readAllBytes());
							pBis.close();

						} catch (Exception e) {
							e.printStackTrace();
						}

					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				mDAO.save(m);
			});

			System.out.println(lList);
			lList.stream().forEach(l -> {
				l.getMember().setLikes(lList);
				l.getPet().setLikes(lList);
			});
			lDAO.saveAll(lList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
