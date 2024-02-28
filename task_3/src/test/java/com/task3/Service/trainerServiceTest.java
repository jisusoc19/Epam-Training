package com.task3.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.Training;
import com.task3.Exception.RestHandlerException.ResouceNotFoundException;
import com.task3.Repository.iTrainerdao;
import com.task3.service.trainer.iTrainerServiceImpl;


import jakarta.validation.constraints.AssertTrue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
public class trainerServiceTest {
	@Mock
	private iTrainerdao trainerjpa;
	
	

	@InjectMocks
	private iTrainerServiceImpl trainerservice;
	
	@Test
	public void findallTest() {
		Trainer trainer1 = new Trainer(null,null,null,null,"jesus","david",null, null, true);
		Trainer trainer2 = new Trainer(null,null,null,null,"jesus","david",null, null, true);
		given(trainerjpa.findAll()).willReturn(Arrays.asList(trainer1, trainer2));
		List<Trainer> trainer = trainerservice.findAll();
		assertThat(trainer).hasSize(2);
		assertThat(trainer).contains(trainer1);
	}
	@Test
	public void Testfindbyallnull() {
		given(trainerjpa.findAll()).willReturn(null);
		
		assertThrows(ResouceNotFoundException.class, () -> {
	            List<Trainer> train = trainerservice.findAll();
	        });
	}
	@Test
	public void findbyid() {
		Trainer trainer1 = new Trainer(1L,null,null,null,"jesus","david",null, null, true);
		Long id =1L;
		when(trainerjpa.existsById(id)).thenReturn(true);
        when(trainerjpa.findById(id)).thenReturn(Optional.of(trainer1));
		given(trainerjpa.findById(trainer1.getId())).willReturn(Optional.of(trainer1));
		Trainer training1encontrado = trainerservice.findById(id);
		
		assertThat(training1encontrado).isEqualTo(trainer1);
	
		
        assertEquals(trainer1, training1encontrado);
	}
	@Test
	public void findbyidnull() {
		Long id = 1L;
        when(trainerjpa.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
            trainerservice.findById(id);
        });

        String expectedMessage = "No se encontro el id " + id;
        String actualMessage = exception.getMessage();
        
        assertThat(actualMessage.contains(expectedMessage));
	}
	@Test
	public void saveTest() {
		Trainer trainer1 = new Trainer(1L,null,null,null,"jesus","david",null, null, true);
		
		Trainer trainesave = trainerservice.save(trainer1);
    	
    	assertThat(trainesave).isEqualTo(trainer1);
	}
	@Test
	public void saveTestnull() {
		Trainer trainer1 = null;
		
		when(trainerjpa.save(trainer1)).thenReturn(null);
		
        assertThrows(ResouceNotFoundException.class, () -> {
        	trainerservice.save(trainer1);
        });
	}
	@Test
	public void updateTestnull() {
		Trainer trainer1 = new Trainer(1L,null,null,null,"jesus","david",null, null, true);
		Trainer trainesave = trainerservice.save(trainer1);
		Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
			Trainer trainern =trainerservice.update(trainer1, trainer1.getId());
	        });
		assertThat(trainesave).isEqualTo(trainer1);
	    String expectedMessage = "Trainer with ID " + trainer1.getId() + " no update in the database";
	    String actualMessage = exception.getMessage();
	        
	    assertThat(actualMessage.contains(expectedMessage));
	}
    @Test
    public void TrainerUpdate() {
        Long id = 1L;
        Trainer existingTrainer = new Trainer(1L,null,null,null,"jesus","david",null, null, true); // Configura el trainer existente
        Trainer updatedTrainer = new Trainer();
        Trainer trainergaurdado = trainerservice.save(existingTrainer);
        updatedTrainer.setFirstName("asdasd");
        updatedTrainer.setLastName("nuevo");
        updatedTrainer.setIsActive(true);
        
        when(trainerjpa.findById(id)).thenReturn(Optional.of(existingTrainer));

        Trainer result = trainerservice.update(updatedTrainer, id);

        assertNull(result);
    }
    
    @Test
    public void generatestring() {
        String firstName = "John";
        String lastName = "Doe";
        when(trainerjpa.findbyusername("john.doe")).thenReturn(Optional.empty());

        String username = trainerservice.generateUniqueUsername(firstName, lastName);

        assertEquals("john.doe", username);
    }
    @Test
    public void UsernameExistin() {
        String firstName = "John";
        String lastName = "Doe";
        when(trainerjpa.findbyusername("john.doe")).thenReturn(Optional.of(new Trainer()));
        when(trainerjpa.findbyusername("john.doe1")).thenReturn(Optional.of(new Trainer()));
        when(trainerjpa.findbyusername("john.doe2")).thenReturn(Optional.empty());

        String username = trainerservice.generateUniqueUsername(firstName, lastName);

        assertEquals("john.doe2", username);
    }
    @Test
    public void generatePasswordLength() {
       
        String password = trainerservice.generatePasword("anyPassword");

        
        assertEquals(10, password.length(), "La contraseña generada debe tener 10 caracteres de longitud.");
    }

    @Test
    public void generatePasswordCaracter() {
        
        String password = trainerservice.generatePasword("anyPassword");
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        
        for (char c : password.toCharArray()) {
            assertTrue(allowedChars.indexOf(c) >= 0, "tiene caracters no permitidos");
        }
    }
    @Test
    public void findusernameResourceException() {
    	String username = "jesus";
    	   assertThrows(ResouceNotFoundException.class, () -> {
           	trainerservice.findbyusername(username);
           });
    }
    
 
    @Test
    public void isactive() {
        String username = "existingUsername";
        Boolean newStatus = true;
        Trainer existingTrainer = new Trainer();
        existingTrainer.setIsActive(false);

        when(trainerjpa.findbyusername(username)).thenReturn(Optional.of(existingTrainer));
        

        Trainer updatedTrainer = trainerservice.activeTranierTrainee(username, newStatus);

        assertTrue(updatedTrainer.getIsActive().equals(newStatus));
        verify(trainerjpa).save(updatedTrainer);
    }
    @Test
    public void isResourceExceptionactive() {
    	Boolean newStatus = true;
    	   assertThrows(ResouceNotFoundException.class, () -> {
    		   trainerservice.activeTranierTrainee(null, null);
              });
    	
    }
	
	

}
