package pers.allen.example.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.allen.example.exception.LoginFailedException;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;

	public Member login(String email, String password) throws LoginFailedException {

		Member member = memberDAO.findByEmailAndPassword(email, password);

		if (member == null) {
			throw new LoginFailedException("自定義的登入失敗錯誤");
		}

		return member;
	}
}
