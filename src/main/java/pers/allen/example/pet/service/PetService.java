package pers.allen.example.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pers.allen.example.pet.model.bean.Pet;
import pers.allen.example.pet.model.dao.PetDAO;

@Service
public class PetService {
	@Autowired
	private PetDAO petDAO;

	public Page<Pet> getPetsByPage(Integer page, Integer count) {
		
		Page<Pet> findAll = petDAO.findAll(PageRequest.of(page - 1, count));

		return  findAll;
	}
	
	
}
