package pers.allen.example.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.allen.example.exception.AccountNotEnabledException;
import pers.allen.example.exception.LoginFailedException;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping(value = "/Login", consumes = { "application/json;charset=utf-8" })
	@ResponseBody
	public String login(@RequestBody Member m, HttpSession session) {
		try {
			System.out.println(m);
			Member member = memberService.login(m);

			session.setAttribute("LoggedInMember", member);

			System.out.println(member);
			return "success";

		} catch (LoginFailedException e) {

			return "fail";

		} catch (AccountNotEnabledException e) {

			return "account_not_enabled";

		}
	}

	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/profile")
	public String toProfilePage(@RequestParam(required = false) Integer mID, Model model, HttpSession session) {
		Member member = null;

		if (mID != null) {
			member = memberService.findMemberByID(mID);
		}

		if (mID == null) {
			member = (Member) session.getAttribute("LoggedInMember");
		}

		model.addAttribute("member", member);

		return "profile";
	}
}
