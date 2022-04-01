package com.microservice.donation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.donation.controller.DonationController;
import com.microservice.donation.repository.DonationRepository;
import com.microservice.donation.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DonationApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DonationRepository donationRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	public void contextLoads() throws Exception {
		assertThat(mockMvc).isNotNull();
	}
	@BeforeAll
	public void prepare(){
		userRepository.deleteAll();
		userRepository.flush();
	}
	@Test
	@Order(2)
	public void testIfTableIsEmpty() throws Exception{
		MvcResult result = this.mockMvc.perform(get("/api/donation/user")).andDo(print()).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentLength()).isEqualTo(0);
	}
	@Test
	@Order(3)
	public void addUser() throws Exception{
		this.mockMvc.perform(post("/api/donation/user")
				.content("{\"donated_sum\": 12}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{\"id\": 1, \"donated_sum\": 12}"));

		//assertThat(result.getResponse().getContentLength()).isEqualTo(0);
	}
	@Test
	@Order(4)
	public void checkIfUserWasAddedWithGet() throws Exception{
		this.mockMvc.perform(get("/api/donation/user")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().json("[{\"id\": 1, \"donated_sum\": 12.0}]")
		);
	}
	@Test
	@Order(5)
	public void updateUser() throws Exception{
		this.mockMvc.perform(put("/api/donation/user/1")
				.content("{\"donated_sum\": 45}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{\"donated_sum\": 45.0}"));
	}
	@Test
	@Order(6)
	public void addUser2() throws Exception{
		this.mockMvc.perform(post("/api/donation/user")
				.content("{\"donated_sum\": 35}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{\"id\": 2, \"donated_sum\": 35}"));
	}
	@Test
	@Order(7)
	public void deleteUser() throws Exception{
		this.mockMvc.perform(delete("/api/donation/user/1"))
				.andExpect(status().isOk());
	}
	@Test
	@Order(8)
	public void addDonation() throws Exception{
		this.mockMvc.perform(post("/api/donation/")
				.content(" {\n" +
						"     \"amount\": 13.8,\n" +
						"     \"date\": \"2020-03-20\",\n" +
						"     \"type\": \"paypal\",\n" +
						"     \"user\":\n" +
						"        {\n" +
						"            \"id\":2\n" +
						"        }\n" +
						" }")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	@Order(9)
	public void updateDonation() throws Exception {
		this.mockMvc.perform(put("/api/donation/1")
				.content("{\n" +
						"    \"amount\": 40,\n" +
						"    \"date\": \"2020-03-20\",\n" +
						"    \"type\": \"cash\",\n" +
						"    \"user\":\n" +
						"        {\n" +
						"            \"id\":2\n" +
						"        }\n" +
						"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
