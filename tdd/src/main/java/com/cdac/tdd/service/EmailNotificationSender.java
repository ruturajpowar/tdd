package com.cdac.tdd.service;

import org.springframework.stereotype.Component;

import com.cdac.tdd.inheritanceStrategy.joined.EmailNotification;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailNotificationSender implements NotificationSender<EmailNotification> {

	@Override
	public Class<EmailNotification> appliesTo() {
		return EmailNotification.class;
	}

	@Override
	public void send(EmailNotification emailNotification) {
		log.info("send email to {} {} via adress {} ", emailNotification.getFirstName(),
				emailNotification.getLastName(), emailNotification.getEmailAddress());

	}

}
