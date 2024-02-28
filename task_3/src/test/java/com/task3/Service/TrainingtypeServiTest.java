package com.task3.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.task3.Entity.Trainee;
import com.task3.Entity.Training_Type;
import com.task3.Exception.RestHandlerException.ResouceNotFoundException;
import com.task3.Repository.iTrainer_TypeRepo;
import com.task3.service.trainingType.trainingTypeImplem;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class TrainingtypeServiTest {
	
	@Mock
	private iTrainer_TypeRepo trainingtyperepo;
	
	@InjectMocks
	private trainingTypeImplem trainingtypeser;
	 
	
	@Test
	public void findAllTest() {
		Training_Type training1 = new Training_Type(1, null, null, null);
		Training_Type training2 = new Training_Type(2, null, null, null);
		
		given(trainingtyperepo.findAll()).willReturn(Arrays.asList(training1,training2));
		
		List<Training_Type> tra = trainingtypeser.findALL();
		
		assertThat(tra).contains(training1);
		assertThat(tra).hasSize(2);
	}
	
	@Test
	public void findAllTestnull() {
		given(trainingtyperepo.findAll()).willReturn(null);
		
		   assertThrows(ResouceNotFoundException.class, () -> {
	            List<Training_Type> t = trainingtypeser.findALL();
	        });
		
	}
	

}
