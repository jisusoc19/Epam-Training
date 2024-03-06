package com.task3.service.JWT;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.task3.Dto.ResponseDto;
import com.task3.Dto.logginDto;
import com.task3.Entity.Trainee;
import com.task3.Entity.User;
import com.task3.Repository.iTraineedao;
import com.task3.Repository.iTrainerdao;
import com.task3.Repository.iTraining_TypeRepo;
import com.task3.Repository.iTrainingdao;
import com.task3.Repository.iUserRepository;
@Service
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private iUserRepository userRepo;
	
	
	@Autowired
	private IJwtService ijwtservice;
	
	@Override
	public HashMap<String, String> loggin(logginDto login) throws Exception{
		
		try {
			HashMap<String, String> jwt = new HashMap<>();
			Optional<User> user = userRepo.findByUsername(login.getUsername());
			if (user.isEmpty()) {
				jwt.put("error", "User no registrado");
				return jwt;
			}
			if(verifypassword(login.getPassword(), user.get().getPassword())) {
				jwt.put("jwt", ijwtservice.generarteJwt(user.get().getId()));
			}else {
				jwt.put("Error","Auth fallida");
			}
			return jwt;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public ResponseDto register(User user) throws Exception {
		
		try {
			ResponseDto response = new ResponseDto();
			Optional<User> userresponse =  userRepo.findByUsername(user.getUsername());
			
			if (userresponse.isPresent()) {
					response.setMessage("user ya  existe");
					return response;
				}
		
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
			user.setPassword(encoder.encode(user.getPassword()));
			userRepo.save(user);
			response.setMessage("User con contraseña encriptada guardada con exito");
			return response;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private boolean verifypassword(String passwordentrada, String passwordstorage) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(passwordentrada, passwordstorage);
	}
	


}
