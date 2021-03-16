package com.cdac.tdd;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cdac.tdd.controllers.CarController;
import com.cdac.tdd.domain.Car;
import com.cdac.tdd.exception.CarNotFoundException;
import com.cdac.tdd.service.Carservice;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private Carservice carservice;

	@Test
	public void getCar_shouldReturnCar() throws Exception {
		doReturn(new Car("prius", "hybrid")).when(carservice).getCarDetails(Mockito.anyString());

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name").value("prius"))
				.andExpect(jsonPath("type").value("hybrid"));
	}

	@Test
	public void getCar_notFound() throws Exception {
		doThrow(new CarNotFoundException()).when(carservice).getCarDetails(Mockito.anyString());

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius")).andExpect(status().isNotFound());
	}

}
