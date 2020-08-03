package com.jspark.health.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jspark.health.db.GymRepository;
import com.jspark.health.dto.Gym;

@RestController
@RequestMapping(path = "/gym", produces = "application/json")
@CrossOrigin(origins = "*")
public class GymController {

	@Autowired
	GymRepository repository;

	@GetMapping("/get/location/{longitude}/{latitude}/{distance}")
	public List<Gym> getGymByLocation(@PathVariable(value = "longitude") double longitude,
			@PathVariable(value = "latitude") double latitude, @PathVariable(value = "distance") double distance) {
		return repository.findByLocationNear(new Point(longitude, latitude),
				new Distance(distance, Metrics.KILOMETERS));
	}

	@GetMapping("/get/name/{name}/{longitude}/{latitude}")
	public List<Gym> getGymByName(@PathVariable(value = "name") String name,
			@PathVariable(value = "longitude") double longitude, @PathVariable(value = "latitude") double latitude) {
		return repository.findByNameIgnoreCaseLikeAndLocationNear(name, new Point(longitude, latitude),
				new Distance(20, Metrics.KILOMETERS));
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public Gym updateGym(@RequestBody Gym gym) {
		gym.setUpdatedDate(new Date());
		repository.save(gym);
		return gym;
	}

}
