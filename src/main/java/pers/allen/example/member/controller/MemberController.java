package pers.allen.example.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pers.allen.example.exception.LoginFailedException;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping(value = "/Login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session,
			HttpServletResponse response) {
		try {
			Member member = memberService.login(email, password);

			session.setAttribute("LoggedInMember", member);
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);  

			response.addHeader("Location", "/");
			return "redirect:/";

		} catch (LoginFailedException e) {
			e.printStackTrace();

			return "A";
		}
	}

}
