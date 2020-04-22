package com.jspark.health.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "gym")
@Getter @Setter @ToString
public class Gym {
	@Id
	private String id;
	private String name;
	private String address;
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
