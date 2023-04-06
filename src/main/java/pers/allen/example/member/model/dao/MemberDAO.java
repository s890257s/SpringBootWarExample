package pers.allen.example.member.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.allen.example.member.model.bean.Member;

public interface MemberDAO extends JpaRepository<Member, Integer> {

	public Member findByEmailAndPassword(String email, String password);

}
