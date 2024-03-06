package com.task3.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.Dto.ResponseDto;
import com.task3.Dto.logginDto;
import com.task3.Entity.Trainee;
import com.task3.Entity.User;
import com.task3.service.JWT.AuthServiceImpl;
import com.task3.service.JWT.IAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/auth")
@RestController
public class AuthController {
	@Autowired
	private IAuthService authservice;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registrar(@RequestBody User user) throws Exception {
		return new ResponseEntity<ResponseDto>(authservice.register(user),HttpStatus.CREATED);
	}
	@PostMapping("/loging")
	public ResponseEntity<HashMap<String,String>> loggin(@RequestBody logginDto loginRe) throws Exception {
		HashMap<String,String> login = authservice.loggin(loginRe);
		//TODO: process POST request
		if(login.containsKey("jwt")) {
			return new ResponseEntity<>(login,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(login,HttpStatus.UNAUTHORIZED);
		}
		
		
	}
 
	
}
