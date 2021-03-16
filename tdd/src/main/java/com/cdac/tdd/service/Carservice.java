package com.cdac.tdd.service;

import org.springframework.stereotype.Service;

import com.cdac.tdd.domain.Car;

@Service
public class Carservice {

	public Car getCarDetails(String name) {
		Car car = new Car("prius", "hybrid");
		return car;
	}

}
