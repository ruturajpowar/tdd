package com.cdac.tdd.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.tdd.domain.Bike;
import com.cdac.tdd.service.BikeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/bike")
@Slf4j
public class BikeController {

	@Autowired
	BikeService bikeService;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity uploadFiles(@RequestParam(value = "files") MultipartFile[] files) {
		try {
			for (MultipartFile file : files) {
				bikeService.saveBikes(file.getInputStream());
			}

			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CompletableFuture<ResponseEntity> getAllBikes() throws InterruptedException {
		return bikeService.getAllBikes()
				.<ResponseEntity>thenApply(ResponseEntity::ok)
				.exceptionally(handleGetBikeFailure);

	}

	@GetMapping(value = "/test", consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity getAllBikes2() {

		try {
			CompletableFuture<List<Bike>> bikes1 = bikeService.getAllBikes();
			CompletableFuture<List<Bike>> bikes2 = bikeService.getAllBikes();
			CompletableFuture<List<Bike>> bikes3 = bikeService.getAllBikes();
			CompletableFuture.allOf(bikes1, bikes2, bikes3).join();
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	private static Function<Throwable, ? extends ResponseEntity> handleGetBikeFailure = throwable -> {
		log.error("Error getting bikes ", throwable);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	};

}
