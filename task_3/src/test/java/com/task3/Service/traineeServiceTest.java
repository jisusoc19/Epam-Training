package com.task3.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.task3.models.Repository.iTraineedao;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Trainer;
import com.task3.models.entity.Training;
import com.task3.models.service.trainee.iTraineServiceImpl;
import com.task3.models.service.trainee.iTraineeService;

import RestHandlerException.ResouceNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class traineeServiceTest {
	@Mock
	private iTraineedao traineeRepo;

	@InjectMocks
	private iTraineServiceImpl traineeService;

	@InjectMocks
	private ResouceNotFoundException e;

	@Test
	public void Testfindbyall() {
		Trainee trainee1 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		Trainee trainee2 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		given(traineeRepo.findAll()).willReturn(Arrays.asList(trainee1, trainee2));

		List<Trainee> trainee = (List<Trainee>) traineeService.findAll();
		assertThat(trainee).hasSize(2);
		assertThat(trainee).contains(trainee1, trainee2);
	}

	@Test
	public void Testfindbyallnull() {
		given(traineeRepo.findAll()).willReturn(null);

		assertThrows(ResouceNotFoundException.class, () -> {
			List<Trainee> trainee1 = traineeService.findAll();
		});
	}

	@Test
	public void findbyid() {
		Trainee trainee1 = new Trainee(1L, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		Long id = 1L;
		when(traineeRepo.existsById(id)).thenReturn(true);
		when(traineeRepo.findById(id)).thenReturn(Optional.of(trainee1));

		given(traineeRepo.findById(trainee1.getId())).willReturn(Optional.of(trainee1));
		Trainee training1encontrado = traineeService.findById(trainee1.getId());

		assertThat(training1encontrado).isEqualTo(trainee1);
	}

	@Test
	public void findbyidTestnull() {
		Trainee trainee1 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		Long id = 1l;
		when(traineeRepo.findById(id)).thenReturn(Optional.empty());
		Long id2 = null;
		assertThrows(ResouceNotFoundException.class, () -> {
			traineeService.findById(id2);
		});
		;
	}

	@Test
	public void saveEntity() {
		Trainee trainee1 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		Long id = 1l;
		when(traineeRepo.existsById(id)).thenReturn(true);
		when(traineeRepo.findById(id)).thenReturn(Optional.of(trainee1));
		Trainee trainesave = traineeService.save(trainee1);

		assertThat(trainesave).isEqualTo(trainee1);
	}

	@Test
	public void saveEntityNull() {
		assertThrows(NullPointerException.class, () -> {
			traineeService.save(null);
		});
		
	}

	@Test
	public void deleten() {
		Trainee trainee2 = new Trainee(1L, null, null, null, null, null, null, null, null, true);
		Long id = 1L;

		when(traineeRepo.existsById(id)).thenReturn(false);
		when(traineeRepo.findById(id)).thenReturn(Optional.of(trainee2));

		traineeService.delete(id);
		Trainee trainees = traineeService.findById(id);
		assertThat(trainees).isEqualTo(trainee2);
		verify(traineeRepo).deleteById(id);
	}
	@Test
	public void deletennull() {
		assertThrows(ResouceNotFoundException.class, () -> {
			traineeService.delete(null);
		});
	}

	@Test
	public void generatestring() {
		String firstName = "John";
		String lastName = "Doe";
		when(traineeRepo.findbyusername("john.doe")).thenReturn(Optional.empty());

		String username = traineeService.generateUniqueUsername(firstName, lastName);

		assertEquals("john.doe", username);
	}

	@Test
	public void UsernameExistin() {
		String firstName = "John";
		String lastName = "Doe";
		when(traineeRepo.findbyusername("john.doe")).thenReturn(Optional.of(new Trainee()));
		when(traineeRepo.findbyusername("john.doe1")).thenReturn(Optional.of(new Trainee()));
		when(traineeRepo.findbyusername("john.doe2")).thenReturn(Optional.empty());

		String username = traineeService.generateUniqueUsername(firstName, lastName);

		assertEquals("john.doe11", username);
	}

	
	@Test
	public void generatePasswordLength() {

		String password = traineeService.generatePasword("anyPassword");

		assertEquals(10, password.length(), "La contraseña generada debe tener 10 caracteres de longitud.");
	}

	@Test
	public void generatePasswordCaracter() {

		String password = traineeService.generatePasword("anyPassword");
		String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (char c : password.toCharArray()) {
			assertTrue(allowedChars.indexOf(c) >= 0, "tiene caracters no permitidos");
		}
	}
	@Test
	public void updateTestnull() {
		Trainee trainee1 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		
		Trainee traineesave = traineeService.save(trainee1);
		Exception exception = assertThrows(ResouceNotFoundException.class, () -> {
			Trainee traineen = traineeService.update(trainee1, trainee1.getId());
	        });
		assertThat(traineesave).isEqualTo(trainee1);
	    String expectedMessage = "Trainee with ID " + trainee1.getId() + " no update in the database";
	    String actualMessage = exception.getMessage();
	        
	    assertThat(actualMessage.contains(expectedMessage));
	}
    @Test
    public void TraineeUpdate() {
        Long id = 1L;
        Trainee existingTrainee = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true); // Configura el trainer existente
        Trainee updatedTrainee = new Trainee(); // Configura el trainer con los nuevos datos
        Trainee traineegaurdado = traineeService.save(existingTrainee);
        updatedTrainee.setFirstName("asdasd");
        updatedTrainee.setLastName("nuevo");
        updatedTrainee.setIsActive(true);
        
        when(traineeRepo.findById(id)).thenReturn(Optional.of(existingTrainee));

        Trainee result = traineeService.update(updatedTrainee, id);

        assertNull(result);
    }
    @Test
    public void findusernameResourceException() {
    	String username = "jesus";
    	   assertThrows(ResouceNotFoundException.class, () -> {
           	traineeService.findbyusername(username);
           });
    }
    @Test
    public void deleteusernameResourcenoFound() {
    String username = "jesus";
 	   assertThrows(ResouceNotFoundException.class, () -> {
        	traineeService.deleteByusername(username);
        });
    }
    @Test
    public void deleteusername() {
		Trainee trainee2 = new Trainee(1L, null, null, null, null, null, null, null, null, true);
		String id = "jesus";

		when(traineeRepo.findbyusername(id)).thenReturn(Optional.of(trainee2));

		traineeService.deleteByusername(id);
		Trainee trainees = traineeService.findbyusername(id);
		assertThat(trainees).isEqualTo(trainee2);
		verify(traineeRepo).deleteByUsername(id);
    }


}
