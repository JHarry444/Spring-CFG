package com.qa.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.persistence.repo.FlowerRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FlowerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	private Flower flower;

	private Flower savedFlower;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private FlowerRepo repo;

	private Long id;
	@Before
	public void init() {
		this.repo.deleteAll();
		
		this.flower = new Flower("tulip", 34, "purple", 34.54, false);
		
		this.savedFlower = this.repo.save(this.flower);
		
		this.id = this.savedFlower.getId() + 1;
	}

//	@Test
//	public void bloop() throws JsonProcessingException {
//		System.out.println(flower);
//		System.out.println(this.mapper.writeValueAsString(flower));
//	}

	@Test
	public void testCreate() throws Exception {
		
		this.savedFlower.setId(id);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/flower/create");
		reqBuilder.contentType(MediaType.APPLICATION_JSON);
		reqBuilder.accept(MediaType.APPLICATION_JSON);
		reqBuilder.content(this.mapper.writeValueAsString(flower));

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(savedFlower));

		this.mockMVC.perform(reqBuilder).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void testCreateBuilder() throws Exception {
		this.mockMVC
				.perform(post("/flower/create").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(flower)))
				.andExpect(status().isCreated()).andExpect(content().json(this.mapper.writeValueAsString(savedFlower)));

	}

	@Test
	public void testReadOneSuccess() throws  Exception {
		this.mockMVC
				.perform(get("/flower/read/" + this.savedFlower.getId()).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(savedFlower)));
	}
	
	@Test
	public void testReadOneFailure() throws Exception {
		this.mockMVC
				.perform(get("/flower/read/999999").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	
	
	
	
	
	
	
}
