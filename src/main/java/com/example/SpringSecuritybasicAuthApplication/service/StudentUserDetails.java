package com.example.SpringSecuritybasicAuthApplication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.SpringSecuritybasicAuthApplication.model.Student;

public class StudentUserDetails  implements UserDetails{

	private final Student user;
    private List<GrantedAuthority> authorities;

    public StudentUserDetails(Student user) {
    	System.out.println("---------------");
        this.user = user;
        List<GrantedAuthority> list = new ArrayList();

        GrantedAuthority aut =  new SimpleGrantedAuthority(user.getAuthority());
        list.add(aut);
        this.authorities=list;
   
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(authorities);
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
