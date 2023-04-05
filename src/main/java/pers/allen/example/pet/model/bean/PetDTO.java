package pers.allen.example.pet.model.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pers.allen.example.member.model.bean.Likes;
import pers.allen.example.member.model.bean.Member;

public class PetDTO {

	private Integer pID;

	private String type;

	private String name;

	private Integer age;

	private byte[] photo;

	private Member member;

}
