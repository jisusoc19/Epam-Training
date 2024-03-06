package com.task3.service.user;


import org.springframework.stereotype.Service;

import com.task3.Entity.User;
@Service
public interface iUserService {
	public User findbyusername(String username);
	String generateUniqueUsername(String firstName, String lastName);
	String generatePasword();
}
