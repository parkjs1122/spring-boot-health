package com.jspark.health.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "user")
@Getter @Setter @ToString
public class User {
	@Id
	private String id;
	private String name;
	private String password;
	private Date registerDate;
	private Date lastLogin;
}
