package com.cdac.tdd.service;

public interface NotificationSender<T> {

	Class<T> appliesTo();

	void send(T t);

}
