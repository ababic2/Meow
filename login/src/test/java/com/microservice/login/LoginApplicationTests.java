package com.microservice.login;

import com.microservice.login.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginApplicationTests {
	@LocalServerPort
	private int port;
	@Autowired
	private UserController userController;

	private String url = "http://localhost:" + port;
	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}
	@Test
	public void testIfTableIsEmpty() throws Exception{

	}
}
