package pers.allen.example.pet.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.service.MemberService;
import pers.allen.example.pet.model.bean.Pet;
import pers.allen.example.pet.model.bean.PetDTO;
import pers.allen.example.pet.service.PetService;

@Controller
public class PetController {
	@Autowired
	private PetService petService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/GetPets")
	@ResponseBody
	public Page<PetDTO> getPetsByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer count) {

		Page<Pet> petPage = petService.getPetsByPage(page, count);

		Page<PetDTO> petDTOPage = petService.coverPetToPetDTO(petPage);

		return petDTOPage;

	}

	@GetMapping(value = "/getPetPhoto", produces = "image/*")
	@ResponseBody
	public byte[] getPetPhotoByPID(@RequestParam(defaultValue = "0") Integer pID) throws IOException {

		byte[] petPhoto = petService.getPetPhotoByID(pID);

		if (petPhoto == null) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(ResourceUtils.getFile("classpath:\\no_image.png")));
			petPhoto = bis.readAllBytes();
			bis.close();
		}

		return petPhoto;
	}

	@PostMapping(value = "addPet")
	@ResponseBody
	public String addPet(@ModelAttribute Pet p, @RequestParam(required = false) MultipartFile photoContent,
			HttpSession session) {

		Member m = (Member) session.getAttribute("LoggedInMember");
		if (photoContent != null) {
			try {
				BufferedInputStream bis = new BufferedInputStream(photoContent.getInputStream());
				p.setPhoto(bis.readAllBytes());
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		p.setMember(m);
		return petService.addPet(p);
	}

	@PostMapping(value = "deletePet")
	@ResponseBody
	public String deletePet(@RequestParam(defaultValue = "0") Integer pID, HttpSession session) {
		Member m = (Member) session.getAttribute("LoggedInMember");

		if (m == null) {
			return "fail";
		}

		String status = petService.deletePet(m.getmID(), pID);

		if (status.equals("success")) {
			m = memberService.findMemberByID(m.getmID());
			session.setAttribute("LoggedInMember", m);
		}
		
		return status;
	}
}
