package pers.allen.example.member.model.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pers.allen.example.pet.model.bean.Pet;

@Entity
@Table(name = "Member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mID;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, columnDefinition = "nvarchar(10)")
	private String level;

	@Column(nullable = false)
	private Boolean enabled;

	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
	private MemberDetail memberDetail;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Pet> pets;

	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Likes> likes;

	public Integer getmID() {
		return mID;
	}

	public void setmID(Integer mID) {
		this.mID = mID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public MemberDetail getMemberDetail() {
		return memberDetail;
	}

	public void setMemberDetail(MemberDetail memberDetail) {
		this.memberDetail = memberDetail;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Member [mID=" + mID + ", email=" + email + ", password=" + password + ", level=" + level + ", enabled="
				+ enabled + ", memberDetail=" + memberDetail + ", pets=" + pets + ", likes=" + likes + "]";
	}

}
