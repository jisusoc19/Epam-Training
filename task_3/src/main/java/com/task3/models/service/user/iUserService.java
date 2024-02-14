package com.task3.models.service.user;


import org.springframework.stereotype.Service;

import com.task3.models.entity.User;
@Service
public interface iUserService {
	public User findbyusername(String username);
	String generateUniqueUsername(String firstName, String lastName);
	String generatePasword(String password);
}
