package pers.allen.example.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.allen.example.member.model.bean.Member;

public interface MemberDAO extends JpaRepository<Member, Integer> {

}
