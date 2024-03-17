package com.example.SpringSecuritybasicAuthApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringSecuritybasicAuthApplication.model.Student;
import com.example.SpringSecuritybasicAuthApplication.repo.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	StudentRepo stdrepo;

	@PostMapping("/register")
	public void register(@RequestBody Student std) {
		Student stdnt = new Student();
		
		stdnt.setPassword(passwordEncoder.encode(std.getPassword())); // Encode the password
		stdnt.setUserName(std.getUserName());
		stdnt.setAuthority(std.getAuthority());
		stdrepo.save(stdnt);

	}
	
	@GetMapping("/userLlogin")
	public String login() {
		return "login succsfully";

	}
}
