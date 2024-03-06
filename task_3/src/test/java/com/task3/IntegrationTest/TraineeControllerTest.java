package com.task3.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.jupiter.api.Test;
import org.mockito.internal.creation.bytebuddy.MockAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.service.JWT.IJwtService;
import com.task3.service.trainee.iTraineeService;

@SpringBootTest
@AutoConfigureMockMvc
public class TraineeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMa;
	
	@Autowired
	IJwtService ijwtservice;
	@Autowired
	iTraineeService traineeser;
	
	@Test
	public void TestStatus401() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/apitrainee/trainee")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
		
	}
	@Test
	public void TestPost()  throws Exception {
		Trainee trainee1 = new Trainee(null, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
		String map = objectMa.writeValueAsString(trainee1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/apitrainee/trainee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(map))
		.andExpect(status().isOk());

	}
	@Test 
	void TestGetId() throws Exception {
		Trainee trainee = new Trainee(1l, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
    	
    	ijwtservice.generarteJwt(trainee.getId());
    	
        mockMvc.perform(get("/apitrainee/trainee/1")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(trainee.getId())))
            .andExpect(status().isOk());
	}
	
	@Test
	public void deleteTest() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException, Exception {
		Trainee trainee = new Trainee(1l, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
    	
    	ijwtservice.generarteJwt(trainee.getId());
    	
        mockMvc.perform(delete("/apitrainee/trainee/1")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(trainee.getId())))
            .andExpect(status().isOk());
	}
	@Test 
	void TestGetall() throws Exception {
		Trainee trainee = new Trainee(1l, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);
    	
    	ijwtservice.generarteJwt(trainee.getId());
    	
        mockMvc.perform(get("/apitrainee/trainee")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(trainee.getId())))
            .andExpect(status().isOk());
	}
	
	@Test
	void TestUpdate() throws Exception {
	    Trainee trainee = new Trainee(1l, null, "asdasd@gmail.com", null, null, "jesus", "david", null, null, true);

	    String jwt = ijwtservice.generarteJwt(trainee.getId());

	    String ob = objectMa.writeValueAsString(trainee);

	    mockMvc.perform(put("/apitrainee/trainee/1")
	            .header("Authorization", "Bearer " + jwt)
	            .content(ob)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}


}
