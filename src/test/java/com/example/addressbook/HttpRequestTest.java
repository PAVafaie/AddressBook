package com.example.addressbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testAddAddressBook() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity<>("{}", headers);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/addressBooks",
				HttpMethod.POST,
				requestEntity,
				String.class
		);

		String response = responseEntity.getBody();

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201); // Check for HTTP 201 Created

		// Parse the response JSON to verify its structure
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response);

		// Check if "numBuddies" is present and has a value of 0
		assertThat(jsonNode.path("numBuddies").asInt()).isEqualTo(0);

		// Check for the presence of "_links" in the response
		assertThat(jsonNode.has("_links")).isTrue();
	}

	@Test
	public void testAddBuddyInfo() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");

		// JSON request body for adding a BuddyInfo
		String jsonRequest = "{ \"name\": \"Patrick\", \"phoneNumber\": \"123456789\" }";

		HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequest, headers);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + port + "/buddyInfoes",
				HttpMethod.POST, // Use POST to create a new BuddyInfo
				requestEntity,
				String.class
		);

		String response = responseEntity.getBody();

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201); // Check for HTTP 201 Created

		// Parse the response JSON to verify its structure
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response);

		// Optionally, check the response for specific information
		assertThat(jsonNode.has("name")).isTrue();
		assertThat(jsonNode.path("name").asText()).isEqualTo("Patrick");
		assertThat(jsonNode.has("phoneNumber")).isTrue();
		assertThat(jsonNode.path("phoneNumber").asText()).isEqualTo("123456789");
	}
}
