package com.task3.IntegrationTest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task3.Dto.logginDto;
import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.Entity.User;
import com.task3.Repository.iUserRepository;
import com.task3.service.JWT.AuthServiceImpl;
import com.task3.service.JWT.IJwtService;
import com.task3.service.user.iUserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectmapper;
	@Autowired
	iUserRepository userservice;
	
	@Autowired
	private AuthServiceImpl authservice;
	
	@Autowired
	private IJwtService ijwtservice;
	
	
	@Test
	public void TestStatus400Register() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
		
	}

	@Test
	public void ResgistroTest() throws Exception {
		User user = new User(1L,"asdasd", "asdad","asdasd", "adsfff", true,
				null, null);
		String ob = objectmapper.writeValueAsString(user);
	
		mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
				.content(ob)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void TestStatusloggin400() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/auth/loging")
				.with(httpBasic("username","password")))
		.andExpect(status().isBadRequest())
		.andReturn();
		
		String token = result.getResponse().getContentAsString();
		
		mockMvc.perform(get("/auth/loging")
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void TestStatusloggin() throws Exception {
		User user = new User(1L,"jesus", "doca","username", "password", true,
				null, null);
    	userservice.save(user);

    	
    	ijwtservice.generarteJwt(user.getId());
    	
        mockMvc.perform(get("/apitrainer/trainer")
                .header("Authorization","Bearer " + ijwtservice.generarteJwt(user.getId())))
            .andExpect(status().isOk());
	}
	
}
