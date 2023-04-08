package pers.allen.example.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/")
	public String toIndexPage() {
		return "index";
	}

	@GetMapping("/add_pet")
	public String toAddPetPage() {
		return "add_pet";
	}
}
