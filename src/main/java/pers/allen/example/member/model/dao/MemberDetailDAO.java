package pers.allen.example.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.allen.example.member.model.bean.MemberDetail;

public interface MemberDetailDAO extends JpaRepository<MemberDetail, Integer> {

}
