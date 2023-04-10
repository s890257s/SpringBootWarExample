package pers.allen.example.pet.model.bean;

import java.util.List;
import java.util.stream.Collectors;

import pers.allen.example.member.model.bean.Member;

public class PetDTO {

	public PetDTO(Pet pet) {
		this.pID = pet.getpID();
		this.type = pet.getType();
		this.name = pet.getName();
		this.age = pet.getAge();
		this.member = pet.getMember();

		this.likedByMemberIds = pet.getLikes().stream().map(l -> l.getMember().getmID()).collect(Collectors.toList());
	}

	private Integer pID;

	private String type;

	private String name;

	private Integer age;

	private Member member;

	private List<Integer> likedByMemberIds;

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

	public List<Integer> getLikedByMemberIds() {
		return likedByMemberIds;
	}

	public void setLikedByMemberIds(List<Integer> likedByMemberIds) {
		this.likedByMemberIds = likedByMemberIds;
	}

	@Override
	public String toString() {
		return "PetDTO [pID=" + pID + ", type=" + type + ", name=" + name + ", age=" + age + ", member=" + member
				+ ", likedByMemberIds=" + likedByMemberIds + "]";
	}

}
