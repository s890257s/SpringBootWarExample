package pers.allen.example.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.allen.example.member.model.bean.Likes;
import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.LikesDAO;
import pers.allen.example.member.model.dao.MemberDAO;

@Controller
public class MemberController {

	@Autowired
	private LikesDAO memberDAO;

	@GetMapping(value = "/OK")
	@ResponseBody
	public List<Likes> helloWorld() {

		return memberDAO.findAll();
	}
}
