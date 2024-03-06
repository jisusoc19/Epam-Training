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

import com.task3.Entity.Trainee;
import com.task3.service.JWT.IJwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingControllerTest {

	

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private IJwtService jwtser;
	@Test
	public void TestStatus401() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/apitraining/training/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
	@Test 
	void TestGetId() throws Exception {
		
    	
		jwtser.generarteJwt(1l);
    	
        mockMvc.perform(get("/apitraining/training/1")
                .header("Authorization","Bearer " + jwtser.generarteJwt(1l)))
            .andExpect(status().isOk());
	}
	

	
}
