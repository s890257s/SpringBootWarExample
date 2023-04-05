package pers.allen.example.member.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pers.allen.example.member.model.bean.Likes;

public interface LikesDAO extends JpaRepository<Likes, Integer> {

}
