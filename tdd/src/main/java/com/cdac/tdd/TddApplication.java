package com.cdac.tdd;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdac.tdd.inheritanceStrategy.joined.EmailNotification;
import com.cdac.tdd.inheritanceStrategy.joined.SmsNotification;
import com.cdac.tdd.repository.EmailNotificationRepository;
import com.cdac.tdd.repository.SmsNotificationRepository;
import com.cdac.tdd.service.NotificationServiceImpl;

@SpringBootApplication
public class TddApplication implements CommandLineRunner {

	@Autowired
	EmailNotificationRepository emailNotificationRepository;

	@Autowired
	SmsNotificationRepository smsNotificationRepository;
	@Autowired
	NotificationServiceImpl notificationServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(TddApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		EmailNotification emailNotification = new EmailNotification("ajay", "sabale", new Date(), "ajay@gmail.com");
		emailNotificationRepository.save(emailNotification);
		SmsNotification smsNotification = new SmsNotification("vijay", "khadake", new Date(), "9768523669");
		smsNotificationRepository.save(smsNotification);

		notificationServiceImpl.sendNotification("We have new plan for you");

	}

}
