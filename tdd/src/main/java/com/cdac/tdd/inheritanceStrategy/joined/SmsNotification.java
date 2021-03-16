package com.cdac.tdd.inheritanceStrategy.joined;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sms_notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsNotification extends Notification {

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	public SmsNotification(String fn, String ln, Date dt, String phone) {
		super(fn, ln, dt);
		this.phoneNumber = phone;
	}
}
