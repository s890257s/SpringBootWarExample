package pers.allen.example.pet.model.bean;

import pers.allen.example.member.model.bean.Member;

public class PetDTO {

	public PetDTO(Pet pet) {
		this.pID = pet.getpID();
		this.type = pet.getType();
		this.name = pet.getName();
		this.age = pet.getAge();
		this.member = pet.getMember();
	}

	private Integer pID;

	private String type;

	private String name;

	private Integer age;

	private Member member;

	private Boolean isLiked;

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "PetDTO [pID=" + pID + ", type=" + type + ", name=" + name + ", age=" + age + ", member=" + member
				+ ", isLiked=" + isLiked + "]";
	}

}
