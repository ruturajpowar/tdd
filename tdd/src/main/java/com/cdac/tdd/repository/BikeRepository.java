package com.cdac.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tdd.domain.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

}
