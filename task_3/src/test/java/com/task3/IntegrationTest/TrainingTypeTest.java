package com.task3.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.task3.Entity.Training_Type;
import com.task3.Repository.iTraining_TypeRepo;
import com.task3.service.JWT.IJwtService;
import com.task3.service.trainingType.training_typeService;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingTypeTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private IJwtService jwtser;
	
	@Autowired
	iTraining_TypeRepo trai;
	@Test
	public void TestStatus401() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/trainingtype/list").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());

	}
	@Test 
	void TestGetId() throws Exception {
		
		Training_Type training2 = new Training_Type(2, null, null, null);
		trai.save(training2);
		jwtser.generarteJwt(1l);
    	
        mockMvc.perform(get("/trainingtype/list")
                .header("Authorization","Bearer " + jwtser.generarteJwt(1l)))
            .andExpect(status().isOk());
	}
}
