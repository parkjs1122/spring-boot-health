package com.jspark.health.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/gym/get/location/{longitude}/{latitude}/{distance}")
	public List<Gym> getGymByLocation(@PathVariable(value = "longitude") double longitude,
			@PathVariable(value = "latitude") double latitude, @PathVariable(value = "distance") double distance) {
		return repository.findByLocationNear(new Point(longitude, latitude),
				new Distance(distance, Metrics.KILOMETERS));
	}

	@RequestMapping("/gym/get/name/{name}/{longitude}/{latitude}")
	public List<Gym> getGymByName(@PathVariable(value = "name") String name,
			@PathVariable(value = "longitude") double longitude, @PathVariable(value = "latitude") double latitude) {
		return repository.findByNameIgnoreCaseLikeAndLocationNear(name, new Point(longitude, latitude),
				new Distance(20, Metrics.KILOMETERS));
	}

	@RequestMapping(value = "/gym/update", method = RequestMethod.POST, consumes = "application/json")
	public Gym updateGym(@RequestBody Gym gym) {
		gym.setUpdatedDate(new Date());
		repository.save(gym);
		return gym;
	}

}
