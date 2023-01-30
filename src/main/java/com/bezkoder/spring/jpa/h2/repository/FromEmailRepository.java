package com.bezkoder.spring.jpa.h2.repository;

import java.util.List;

import com.bezkoder.spring.jpa.h2.model.FromEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FromEmailRepository extends JpaRepository<FromEmail, Long> {
  //List<FromEmail> findByPublished(boolean published);

  List<FromEmail> findByEmail(String email);
}
