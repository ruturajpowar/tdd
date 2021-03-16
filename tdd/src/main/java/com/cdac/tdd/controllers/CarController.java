package com.cdac.tdd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.tdd.domain.Car;
import com.cdac.tdd.exception.CarNotFoundException;
import com.cdac.tdd.service.Carservice;

@RestController
public class CarController {

	@Autowired
	Carservice carservice;

	@GetMapping("/cars/{name}")
	public Car getCar(@PathVariable String name) {
		return carservice.getCarDetails(name);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void carNotFound(CarNotFoundException carNotFoundException) {

	}
}
