package com.task3.service.JWT;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
@Service
public interface IJwtService  {
	public String generarteJwt(Long id) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException ;
	public JWTClaimsSet parseJWT(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException, JOSEException;
}
