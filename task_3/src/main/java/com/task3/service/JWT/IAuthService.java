package com.task3.service.JWT;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.task3.Dto.ResponseDto;
import com.task3.Dto.logginDto;
import com.task3.Entity.Trainee;
import com.task3.Entity.User;
@Service
public interface IAuthService {
	public HashMap<String, String> loggin(logginDto login) throws Exception;
	ResponseDto register(User user) throws Exception;
}
