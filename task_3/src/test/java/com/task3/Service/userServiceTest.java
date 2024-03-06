package com.task3.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.task3.Entity.Trainer;
import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;
import com.task3.service.user.iUserServiceImpl;

@ExtendWith(SpringExtension.class)
public class userServiceTest {
	@Mock
	private iUserRepository userrepo;
	
	@InjectMocks
	private iUserServiceImpl userservice;
	 
	
    
    @Test
    public void generatestring() {
        String firstName = "John";
        String lastName = "Doe";
        when(userrepo.findByUsername("john.doe")).thenReturn(Optional.empty());

        String username = userservice.generateUniqueUsername(firstName, lastName);

        assertEquals("john.doe", username);
    }
    @Test
    public void UsernameExistin() {
        String firstName = "John";
        String lastName = "Doe";
        when(userrepo.findByUsername("john.doe")).thenReturn(Optional.of(new User(null, lastName, lastName, lastName, lastName, null, null, null)));
        when(userrepo.findByUsername("john.doe1")).thenReturn(Optional.of(new User(null, lastName, lastName, lastName, lastName, null, null, null)));
        when(userrepo.findByUsername("john.doe2")).thenReturn(Optional.empty());

        String username = userservice.generateUniqueUsername(firstName, lastName);

        assertEquals("john.doe2", username);
    }
    @Test
    public void generatePasswordLength() {
       
        String password = userservice.generatePasword();

        
        assertEquals(10, password.length(), "La contraseña generada debe tener 10 caracteres de longitud.");
    }

    @Test
    public void generatePasswordCaracter() {
        
        String password = userservice.generatePasword();
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        
        for (char c : password.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0, "tiene caracters no permitidos");
        }
    }
	
}
