package dev.hotel.api;

import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

	@PersistenceContext
	TestEntityManager entityManager;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testPagination() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/clients?size=1&start=0"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Odd"));
	}

}
