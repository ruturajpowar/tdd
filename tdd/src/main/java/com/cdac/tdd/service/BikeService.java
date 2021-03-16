package com.cdac.tdd.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cdac.tdd.domain.Bike;
import com.cdac.tdd.repository.BikeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BikeService {

	@Autowired
	BikeRepository bikeRepository;

	@Async
	public CompletableFuture<List<Bike>> saveBikes(final InputStream inputStream) throws Exception {
		final long start = System.currentTimeMillis();
		final List<Bike> bikes = parseCsvFile(inputStream);
		log.info("saving list of bikes having size {} ", bikes.size());
		bikeRepository.saveAll(bikes);
		log.info("Elapsed Time {} ", (System.currentTimeMillis() - start));
		return CompletableFuture.completedFuture(bikes);
	}

	private List<Bike> parseCsvFile(InputStream inputStream) throws IOException {
		final List<Bike> bikes = new ArrayList<Bike>();
		try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				final String[] data = line.split(";");
				// final Bike bike =
				// Bike.builder().manufacturer(data[0]).model(data[1]).type(data[2]).build();
				final Bike bike = new Bike();
				bike.setManufacturer(data[0]);
				bike.setModel(data[1]);
				bike.setType(data[2]);
				bikes.add(bike);
			}
			return bikes;
		} catch (IOException e) {
			log.error("Failed to parse CSV file ", e);
			e.initCause(new Throwable("Failed to parse CSV file"));
			throw e;
		}
	}

	@Async
	public CompletableFuture<List<Bike>> getAllBikes() throws InterruptedException {
		Thread.sleep(1000l);
		log.info("Getting all bikes");
		final List<Bike> bikes = bikeRepository.findAll();
		return CompletableFuture.completedFuture(bikes);
	}

}
