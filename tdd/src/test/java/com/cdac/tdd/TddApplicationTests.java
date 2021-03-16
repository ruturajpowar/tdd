package com.cdac.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cdac.tdd.domain.Car;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TddApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void getCar_returnsCarDetail() throws Exception {

		ResponseEntity<Car> response = testRestTemplate.getForEntity("/cars/prius", Car.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		assertThat(response.getBody().getName()).isEqualTo("prius");

		assertThat(response.getBody().getType()).isEqualTo("hybrid");

	}

}
