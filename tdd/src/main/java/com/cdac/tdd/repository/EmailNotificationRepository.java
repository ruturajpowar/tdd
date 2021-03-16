package com.cdac.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.tdd.inheritanceStrategy.joined.EmailNotification;

@Repository
public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Integer> {

}
