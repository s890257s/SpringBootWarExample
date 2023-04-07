package pers.allen.example.pet.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.allen.example.pet.model.bean.Pet;
import pers.allen.example.pet.model.bean.PetDTO;
import pers.allen.example.pet.service.PetService;

@Controller
public class PetController {
	@Autowired
	private PetService petService;

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
	public byte[] getPetPhotoByPID(@RequestParam Integer pID) throws IOException {

		byte[] petPhoto = petService.getPetPhotoByID(pID);

		if (petPhoto == null) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(ResourceUtils.getFile("classpath:\\no_image.png")));
			petPhoto = bis.readAllBytes();
			bis.close();
		}

		return petPhoto;
	}
}
