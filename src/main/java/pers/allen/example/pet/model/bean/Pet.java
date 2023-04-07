package pers.allen.example.pet.model.bean;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pers.allen.example.member.model.bean.Likes;
import pers.allen.example.member.model.bean.Member;

@Entity
@Table(name = "Pet")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pID;

	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String type;

	@Column(nullable = false, columnDefinition = "nvarchar(50)")
	private String name;

	@Column
	private Integer age;

	@Column(columnDefinition = "varbinary(MAX)")
	private byte[] photo;

	@JsonIgnoreProperties({ "pets" })
	@JoinColumn(name = "f_mID", referencedColumnName = "mID")
	@ManyToOne
	private Member member;

	@JsonIgnore
	@OneToMany(mappedBy = "pet")
	private List<Likes> likes;

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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Pet [pID=" + pID + ", type=" + type + ", name=" + name + ", age=" + age + "]";
	}

}
