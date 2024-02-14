package com.task3.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.task3.models.Repository.iTrainingdao;

import com.task3.models.entity.Training;

import com.task3.models.service.training.iTrainingServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;









@ExtendWith(SpringExtension.class)
public class trainingServiceTest {
	
	@Mock
	private iTrainingdao trainingrepo;
	
	@InjectMocks
	private iTrainingServiceImpl trainingService;
	
	@Test
	public void findbyidTest() {
		Training training1 = new Training((long) 1,null,null,"jesus",null, null,null);
		
		Long id = 1L;
		when(trainingrepo.existsById(id)).thenReturn(true);
        when(trainingrepo.findById(id)).thenReturn(Optional.of(training1));
        
		given(trainingrepo.findById(training1.getId())).willReturn(Optional.of(training1));
		Training training1encontrado = trainingService.findbyid(training1.getId());
		
		assertThat(training1encontrado).isEqualTo(training1);
	
		
        assertEquals(training1, training1encontrado);
	}
	@Test
	public void findbyidTestnull() {
		Training training3 = new Training(null,null,null,"jesus",null, null,null);
	
		
		Training training1encontrado3 = trainingService.findbyid(training3.getId());
		
		assertNull(training1encontrado3);
	}
	@Test 
	void guardarEntityTest() {
        Long id = 1L;
        Long id2 = 2L;
        Training training = new Training((long) 1,null,null,"jesus",null, null,null); // Completar con parámetros adecuados
    	when(trainingrepo.existsById(id)).thenReturn(true);
    	when(trainingrepo.findById(id)).thenReturn(Optional.of(training));

        // Ejecución
        Training savedTraining = trainingService.save(training, id);

        // Verificación
        assertNotNull(savedTraining);
       
	}
	@Test 
	void guardarEntityTestnull() {
        Long id2 = null;
        Training training = new Training((long) 1,null,null,"jesus",null, null,null); 
        Training training1= null;
    	when(trainingrepo.existsById(id2)).thenReturn(false);
    	when(trainingrepo.findById(id2)).thenReturn(Optional.of(training));
		
    	Training savedTraining = trainingService.save(training1, id2);
    	assertNull(savedTraining);
    	
	}
	}

	
