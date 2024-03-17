package com.example.SpringSecuritybasicAuthApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringSecuritybasicAuthApplication.model.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByUsername(String username);

}
