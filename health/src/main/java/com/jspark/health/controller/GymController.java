package com.jspark.health.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jspark.health.dao.GymRepository;
import com.jspark.health.dto.Gym;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/gym", produces = "application/json")
@CrossOrigin(origins = "*")
public class GymController {

	@Autowired
	GymRepository gymRepo;

	@GetMapping("/location/{longitude}/{latitude}/{distance}")
	public Flux<Gym> getGymByLocation(@PathVariable(value = "longitude") double longitude,
			@PathVariable(value = "latitude") double latitude, @PathVariable(value = "distance") double distance) {
		return gymRepo.findByLocationNear(new Point(longitude, latitude),
				new Distance(distance, Metrics.KILOMETERS));
	}

	@GetMapping("/name/{name}/{longitude}/{latitude}")
	public Flux<Gym> getGymByName(@PathVariable(value = "name") String name,
			@PathVariable(value = "longitude") double longitude, @PathVariable(value = "latitude") double latitude) {
		return gymRepo.findByNameIgnoreCaseLikeAndLocationNear(name, new Point(longitude, latitude),
				new Distance(20, Metrics.KILOMETERS));
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Gym createGym(@RequestBody Gym gym) {
		gym.setUpdatedDate(new Date());
		gymRepo.save(gym);
		return gym;
	}
	
	@PatchMapping(value = "/{gymId}", consumes = "application/json")
	public Gym updateGym(@PathVariable("gymId") String gymId, @RequestBody Gym gym) {
		gym.setUpdatedDate(new Date());
		gymRepo.save(gym);
		return gym;
	}
	
	@DeleteMapping("/{gymId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGym(@PathVariable("gymId") String gymId) {
		try {
			gymRepo.deleteById(gymId);
		} catch (EmptyResultDataAccessException e) {}
	}

}
