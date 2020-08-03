package com.jspark.health.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "gym")
@Data
public class Gym {
	@Id
	private String id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Address is required")
	private String address;
	@NotBlank(message = "Location is required")
	private GeoJsonPoint location;
	private int dailyUse;
	private int yogaRoom;
	private int powerRack;
	private int smithMachine;
	private int chiningDippingMachine;
	private int cableMachine;
	private int runningMachine;
	private int bench;
	private int inclineBench;
	private int declineBench;
	private int hackSquatMachine;
	private int barbell;
	private int ezBar;
	private Date updatedDate;
}
