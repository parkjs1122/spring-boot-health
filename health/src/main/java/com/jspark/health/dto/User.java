package com.jspark.health.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "user")
@Data
public class User {
	@Id
	private String id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Password is required")
	private String password;
	private Date registerDate;
	private Date lastLogin;
}
