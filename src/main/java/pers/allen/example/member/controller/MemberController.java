package pers.allen.example.member.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.MemberDAO;
import pers.allen.example.pet.model.bean.PetDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO memberDAO;

	@GetMapping(value = "/Login")
	public String login(HttpSession session) {

		Member member = memberDAO.findByEmailAndPassword("Nick@xxmail.com", "1234");
		System.out.println(member);
		return "A";
//		return "redirect:/";
	}

}
