package com.bezkoder.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "FROM_EMAIL")
public class FromEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "email")
	private String email;

	public FromEmail() {

	}

	public FromEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "FromEmail [id=" + id + ", email=" + email + "]";
	}

}
