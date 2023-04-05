package pers.allen.example.pet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.allen.example.pet.model.bean.Pet;

public interface PetDAO extends JpaRepository<Pet, Integer> {

}
