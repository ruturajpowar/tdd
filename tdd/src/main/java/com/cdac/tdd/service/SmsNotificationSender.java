package com.cdac.tdd.service;

import org.springframework.stereotype.Component;

import com.cdac.tdd.inheritanceStrategy.joined.SmsNotification;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmsNotificationSender implements NotificationSender<SmsNotification> {

	@Override
	public Class<SmsNotification> appliesTo() {
		return SmsNotification.class;
	}

	@Override
	public void send(SmsNotification smsNotification) {
		log.info("sending notification to {} {} via phone numer ", smsNotification.getFirstName(),
				smsNotification.getLastName(), smsNotification.getPhoneNumber());
	}

}
