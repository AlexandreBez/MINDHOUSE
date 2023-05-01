package com.studentsystem.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.model.entity.Promotion;

public interface PromotionJPA extends JpaRepository<Promotion, Integer>{

}
