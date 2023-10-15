package com.example.addressbook;

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
				HttpMethod.PUT,
				requestEntity,
				String.class
		);

		String response = responseEntity.getBody();

		System.out.println(response);

		assertThat(response).contains("Your expected content here");
	}
}
