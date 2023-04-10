package pers.allen.example.pet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pers.allen.example.member.model.bean.Member;
import pers.allen.example.member.model.dao.MemberDAO;
import pers.allen.example.pet.model.bean.Pet;
import pers.allen.example.pet.model.bean.PetDTO;
import pers.allen.example.pet.model.dao.PetDAO;

@Service
public class PetService {
	@Autowired
	private PetDAO petDAO;

	@Autowired
	private MemberDAO memberDAO;

	public Page<Pet> getPetsByPage(Integer page, Integer count) {

		Page<Pet> pagePet = petDAO.findAll(PageRequest.of(page - 1, count));

		return pagePet;
	}

	public byte[] getPetPhotoByID(Integer pID) {
		Optional<Pet> oPet = petDAO.findById(pID);
		Pet pet = oPet.orElse(new Pet());
		return pet.getPhoto();
	}

	public Page<PetDTO> coverPetToPetDTO(Page<Pet> pagePet) {
		List<PetDTO> petDTOList = pagePet.toList().stream().map(p -> new PetDTO(p)).collect(Collectors.toList());
		Page<PetDTO> pagePetDTO = new PageImpl<PetDTO>(petDTOList, pagePet.getPageable(), pagePet.getTotalElements());
		return pagePetDTO;
	}

	public String addPet(Pet p) {

		petDAO.save(p);

		return "success";
	}

	public String deletePet(Integer mID, Integer pID) {
		Member m = memberDAO.findById(mID).get();

		for (Pet p : m.getPets()) {
			if (p.getpID() == pID) {
				
				petDAO.deleteById(pID);
				return "success";
			}
		}

		return "fail";
	}
}
