package com.task3.IntegrationTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.User;
import com.task3.service.JWT.IJwtService;
import com.task3.service.trainer.iTrainerServiceImpl;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNodes.JsonNode;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class TrainerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
    private ObjectMapper objectMapper;
	@Autowired
	iTrainerServiceImpl trainerService;
	@Autowired
	private IJwtService ijwtservice;
	
    private String token;


    @BeforeEach
    void setUp() throws Exception {
		User user = new User(1L,"jesus", "doca","username", "password", true,
				null, null);
		String ob = objectMapper.writeValueAsString(user);
	
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
				.content(ob)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
        String jsonContent = "{\"username\":\"username\",\"password\":\"password\"}";
        MvcResult result = this.mockMvc.perform(post("/auth/loging")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse =result.getResponse().getContentAsString(); 
        com.fasterxml.jackson.databind.JsonNode rootNode = objectMapper.readTree(jsonResponse);
        String jwt = rootNode.get("jwt").asText();

        System.out.println(jwt);
        this.token=jwt;
        
        
        log.info(token);
    }

	
	@Test
	public void TestStatus401getall() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/apitrainer/trainer")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized());
		
	}
	@Test
	public void TestStatusall() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/apitrainer/trainer").header("Authorization","Bearer " + token))
		.andExpect(status().isOk());
		
	}
	@Test
	public void Testgetid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/apitrainer/trainer/1").header("Authorization","Bearer " + token))
		.andExpect(status().isOk());
		
	}
	@Test
	void TestUpdate() throws Exception {
		Trainer trainer = new Trainer(1l,null,null,null,"jesus","david",null, null, true);

	    String jwt = ijwtservice.generarteJwt(trainer.getId());

	    String ob = objectMapper.writeValueAsString(trainer);

	    mockMvc.perform(put("/apitrainer/trainer/1")
	            .header("Authorization", "Bearer " + jwt)
	            .content(ob)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	@Test
	public void Testgetusername() throws Exception {
		Trainer trainer = new Trainer(1l,null,null,null,"jesus","david","username", "username", true);
    	trainerService.save(trainer);

		mockMvc.perform(MockMvcRequestBuilders.get("/apitrainer/trainer/username/NombreUsuario").header("Authorization","Bearer " + token))
		.andExpect(status().isNotFound());
		
	}
	

    @Test
    public void testFindById401() throws Exception {
        // Asumiendo que existe un entrenador con ID 1 en la base de datos
        Long trainerId = 1L;

        mockMvc.perform(get("/trainer/{id}", trainerId))
            .andExpect(status().isUnauthorized());
        // Asegúrate de ajustar esta línea para que coincida con la estructura de tu objeto Trainer
    }
    
    @Test
    public void Testpost400() throws Exception  {
    	mockMvc.perform(MockMvcRequestBuilders.post("/apitrainer/trainer")
    			.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isBadRequest());
    }
    @Test
    public void testPost() throws Exception {
 
    	Trainer trainer = new Trainer(null,null,null,null,"jesus","david",null, null, true);

        
        String trainerJson = objectMapper.writeValueAsString(trainer);

        mockMvc.perform(post("/apitrainer/trainer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(trainerJson)) // Envía el objeto como una cadena JSON en el cuerpo de la solicitud
                .andExpect(status().isOk()); // Ajusta este estado esperado según la lógica de tu aplicación
    }
    
    
    
    @Test
    public void testFindByIdWithJwtFalseNotAuthorization() throws Exception {
        String jwtToken = "BearerTokenFalso";

        Long trainerId = 1L; //

        mockMvc.perform(get("/trainer/{id}", trainerId)
                .header("Authorization", jwtToken))
       
            .andExpect(status().isUnauthorized());
        
    }
    @Test
    public void testFindall() throws Exception {
    	Trainer trainer = new Trainer(1l,null,null,null,"jesus","david",null, null, true);
    	trainerService.save(trainer);
        String trainerJson = objectMapper.writeValueAsString(trainer);
    	
    	ijwtservice.generarteJwt(trainer.getId());
    	
        mockMvc.perform(get("/apitrainer/trainer")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(trainer.getId())))
            .andExpect(status().isOk());
        
    }



    

}