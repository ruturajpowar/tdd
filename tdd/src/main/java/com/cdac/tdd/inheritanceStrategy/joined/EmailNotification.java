package com.cdac.tdd.inheritanceStrategy.joined;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification extends Notification {

	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	public EmailNotification(String fn, String ln, Date dt, String mail) {
		super(fn, ln, dt);
		this.emailAddress = mail;
	}

}
