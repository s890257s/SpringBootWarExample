package pers.allen.example.member.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import pers.allen.example.pet.model.bean.Pet;

@Entity
@Table(name = "Likes")
public class Likes {

	public Likes() {
	}

	public Likes(Date time, Member member, Pet pet) {
		this.time = time;
		this.member = member;
		this.pet = pet;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lID;

	@Column
	private Date time;

	@JoinColumn(name = "f_mID", referencedColumnName = "mID", nullable = false)
	@ManyToOne
	private Member member;

	@JoinColumn(name = "f_pID", referencedColumnName = "pID", nullable = false)
	@ManyToOne
	private Pet pet;

	public Integer getlID() {
		return lID;
	}

	public void setlID(Integer lID) {
		this.lID = lID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Override
	public String toString() {
		return "Likes [lID=" + lID + ", time=" + time + ", member=" + member.getmID() + ", pet=" + pet.getpID() + "]";
	}

}
