package pers.allen.example.member.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MemberDetail")
public class MemberDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mDID;

	@Column(columnDefinition = "nvarchar(10)")
	private String name;

	@Column
	private Integer age;

	@Column(columnDefinition = "nvarchar(50)")
	private String address;

	@Column(columnDefinition = "nvarchar(MAX)")
	private String photo;

	@JsonIgnore
	@JoinColumn(name = "f_mID", referencedColumnName = "mID", nullable = false)
	@OneToOne
	private Member member;

	public Integer getmDID() {
		return mDID;
	}

	public void setmDID(Integer mDID) {
		this.mDID = mDID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "MemberDetail [mDID=" + mDID + ", name=" + name + ", age=" + age + ", address=" + address + ", photo="
				+ photo + ", member=" + member + "]";
	}

}