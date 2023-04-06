package pers.allen.example.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;

	public Member Login() {
		
		return null;
	}
}
