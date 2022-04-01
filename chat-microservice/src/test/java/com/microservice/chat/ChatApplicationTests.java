package com.microservice.chat;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.chat.repository.RoomRepository;
import com.microservice.chat.repository.UserRepository;
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

class ChatApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@BeforeAll
	public void prepare(){
		userRepository.deleteAll();
		userRepository.flush();

		roomRepository.deleteAll();
		roomRepository.flush();
	}

	@Test
	@Order(1)
	public void contextLoads() throws Exception {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@Order(2)
	public void testIfTableIsEmpty() throws Exception{
		MvcResult result = this.mockMvc.perform(get("/api/chat/user")).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentLength()).isEqualTo(0);
	}

	@Test
	@Order(3)
	public void addRoom() throws Exception{
		this.mockMvc.perform(post("/api/chat/room")
						.content("{\"name\": \"room1\"}")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void updateRoom() throws Exception{
		this.mockMvc.perform(put("/api/chat/room/1")
						.content("{\"name\": \"roooom1\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	public void deleteRoom() throws Exception{
		this.mockMvc.perform(delete("/api/chat/room/1"))
				.andExpect(status().isOk());
	}

	@Test
	@Order(6)
	public void addUser() throws Exception{
		this.mockMvc.perform(post("/api/chat/user")
						.content("{\"username\": \"user1\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(7)
	public void updateUser() throws Exception{
		this.mockMvc.perform(put("/api/chat/user/1")
						.content("{\"username\": \"useeeerr1\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(8)
	public void deleteUser() throws Exception{
		this.mockMvc.perform(delete("/api/chat/user/1"))
				.andExpect(status().isOk());
	}

}
