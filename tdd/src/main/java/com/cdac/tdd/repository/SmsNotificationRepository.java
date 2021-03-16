package com.cdac.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tdd.inheritanceStrategy.joined.SmsNotification;

@Repository
public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Integer> {

}
