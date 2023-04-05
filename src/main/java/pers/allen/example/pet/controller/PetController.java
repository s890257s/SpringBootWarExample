package pers.allen.example.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.allen.example.pet.model.bean.Pet;
import pers.allen.example.pet.service.PetService;

@Controller
public class PetController {
	@Autowired
	private PetService petService;

	@GetMapping("/getPets")
	@ResponseBody
	public Page<Pet> getPetsByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer count) {

		return petService.getPetsByPage(page, count);

	}

}
