package com.task3.security;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.task3.service.JWT.IJwtService;
import com.task3.service.JWT.JWTServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	IJwtService jwtservice;
	
	
	
	public JWTAuthorizationFilter(IJwtService jwtservice) {
		this.jwtservice= jwtservice;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		
		if(header==null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.substring(7);
		
		try {
			JWTClaimsSet claims = jwtservice.parseJWT(token);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null , Collections.emptyList());
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	filterChain.doFilter(request, response);
	}
	

}
