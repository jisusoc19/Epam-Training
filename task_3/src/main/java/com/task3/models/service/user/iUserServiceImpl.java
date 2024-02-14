package com.task3.models.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task3.models.Repository.iUserRepository;
import com.task3.models.entity.User;
import java.util.Random;

@Service
public class iUserServiceImpl implements iUserService {

	@Autowired
	private iUserRepository userRepo;

	@Override
	public String generateUniqueUsername(String firstName, String lastName) {
		String gUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
		String username = gUsername;
		Integer nSerail = 1;
		while (userRepo.findByUsername(username).isPresent()) {
			username = gUsername + nSerail;
			nSerail++;
		}
		return username;
	}
	@Override
	public String generatePasword(String password) {
	    int length = 10;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(letras.length());
            sb.append(letras.charAt(randomIndex));
        }  
        return sb.toString();
    }
		
	

	@Override
	public User findbyusername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username).orElse(null);
	}

}
