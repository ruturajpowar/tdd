package com.cdac.tdd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.tdd.inheritanceStrategy.joined.Notification;
import com.cdac.tdd.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	List<NotificationSender> notificationSenders;

	private Map<Class<? extends Notification>, NotificationSender> notificationSenderMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		System.out.println("%%%%%%%%%%%%%%%% " + notificationSenders.size());
		notificationSenders.forEach(ns -> {
			notificationSenderMap.put(ns.appliesTo(), ns);
		});
	}

	@Override
	public void sendNotification(String message) {
		List<Notification> notifications = notificationRepository.findAll();
		for (Notification n : notifications) {
			notificationSenderMap.get(n.getClass()).send(n);
		}

	}

}
