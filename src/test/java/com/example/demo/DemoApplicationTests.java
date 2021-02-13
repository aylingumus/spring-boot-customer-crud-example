package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.domain.AddressDO;
import com.example.demo.domain.CustomerDO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		mockMvc.perform(get("/api/v1/customers")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$[*].id").exists())
						.andExpect(jsonPath("$[*].username").exists())
						.andExpect(jsonPath("$[*].name").exists())
						.andExpect(jsonPath("$[*].surname").exists());						;				;
	}

	@Test
	public void testGetCustomerById() throws Exception {
		String customerId = "6";

		mockMvc.perform(get("/api/v1/customers/{customerId}", customerId)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$.username").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.surname").exists())						;

	}

	@Test
	public void testGetCustomerByUsername() throws Exception {
		String customerUsername = "morgokyuzu";

		mockMvc.perform(get("/api/v1//customers-by-username/{customerUsername}", customerUsername)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$.username").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.surname").exists())
						.andExpect(jsonPath("$.address").exists())
						.andExpect(jsonPath("$.address.id").exists())
						.andExpect(jsonPath("$.address.city").exists())
						.andExpect(jsonPath("$.address.street").exists())
						.andExpect(jsonPath("$.address.zipCode").exists());
	}

	@Test
	public void testCreateCustomer() throws Exception {
		CustomerDO newCustomer = new CustomerDO();
		newCustomer.setUsername("morgokyuzu");
		newCustomer.setName("Mor");
		newCustomer.setSurname("Gokyuzu");
		AddressDO newAddress = new AddressDO();
		newAddress.setCity("Mavi Nehir");
		newAddress.setStreet("Şimşek Sokak");
		newAddress.setZipCode("121212");
		newCustomer.setAddress(newAddress);

		mockMvc.perform(post("/api/v1/customer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newCustomer)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.id").exists())
						.andExpect(jsonPath("$.username").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.surname").exists())
						.andExpect(jsonPath("$.address").exists())
						.andExpect(jsonPath("$.address.id").exists())
						.andExpect(jsonPath("$.address.city").exists())
						.andExpect(jsonPath("$.address.street").exists())
						.andExpect(jsonPath("$.address.zipCode").exists())
						.andExpect(jsonPath("$.username").value("morgokyuzu"));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		CustomerDO newCustomer = new CustomerDO();
		newCustomer.setId(10L);
		newCustomer.setUsername("morgokyuzu");
		newCustomer.setName("Mor");
		newCustomer.setSurname("Gokyuzu");
		AddressDO newAddress = new AddressDO();
		newAddress.setCity("Mor Nehir");
		newAddress.setStreet("Cam Sokak");
		newAddress.setZipCode("777222");
		newCustomer.setAddress(newAddress);

		mockMvc.perform(put("/api/v1/customer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(newCustomer)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.surname").exists())
						.andExpect(jsonPath("$.address").exists())
						.andExpect(jsonPath("$.address.city").exists())
						.andExpect(jsonPath("$.address.street").exists())
						.andExpect(jsonPath("$.address.zipCode").exists());					;
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		String customerId = "10";

		mockMvc.perform(delete("/api/v1/customers/{customerId}", customerId)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}