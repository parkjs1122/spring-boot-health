package com.jspark.health.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jspark.health.db.GymRepository;
import com.jspark.health.dto.Gym;

@RestController
public class GymController {
	
	@Autowired
	GymRepository repository;
	
	@RequestMapping("/gym/get/location")
	public List<Gym> getGymByLocation(double latitude, double longitude, double distance) {
		return repository.findByLocationNear(new Point(longitude, latitude), new Distance(distance, Metrics.KILOMETERS));
	}
	
	@RequestMapping("/gym/get/name")
	public List<Gym> getGymByName(String name) {
		return repository.findByNameLike(name);
	}
	
	@RequestMapping(value = "/gym/update", method=RequestMethod.POST, consumes="application/json")
	public Gym updateGym(@RequestBody Gym gym) {
		gym.setUpdatedDate(new Date());
		repository.save(gym);	
		return gym;
	}
	
}
