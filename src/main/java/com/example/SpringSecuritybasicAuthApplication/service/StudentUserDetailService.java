package com.example.SpringSecuritybasicAuthApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringSecuritybasicAuthApplication.model.Student;
import com.example.SpringSecuritybasicAuthApplication.repo.StudentRepo;

@Service
public class StudentUserDetailService implements UserDetailsService {

	@Autowired
	private StudentRepo studentrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		System.out.println("8667678689767");
	Student std =	studentrepo.findByUsername(username);
		 if (std == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new StudentUserDetails(std);	
	        }

	
}
