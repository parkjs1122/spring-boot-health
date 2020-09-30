package com.jspark.health.dao;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jspark.health.dto.Gym;

import reactor.core.publisher.Flux;

@Repository
public interface GymRepository extends ReactiveCrudRepository<Gym, String> {
	public Flux<Gym> findByNameIgnoreCaseLikeAndLocationNear(String name, Point location, Distance distance);
	public Flux<Gym> findByAddress(String address);
	public Flux<Gym> findByLocationNear(Point location, Distance distance);
}
