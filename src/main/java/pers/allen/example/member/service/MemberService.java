package pers.allen.example.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.allen.example.exception.AccountNotEnabledException;
import pers.allen.example.exception.LoginFailedException;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;

	public Member login(Member m) throws LoginFailedException, AccountNotEnabledException {

		Member member = memberDAO.findByEmailAndPassword(m.getEmail(), m.getPassword());

		if (member == null) {
			throw new LoginFailedException("自定義的登入失敗錯誤");
		}

		if (!member.getEnabled()) {
			throw new AccountNotEnabledException("自定義的帳號為啟用錯誤");
		}

		return member;
	}

	public Member findMemberByID(Integer mID) {
		return memberDAO.findById(mID).get();
	}
}
