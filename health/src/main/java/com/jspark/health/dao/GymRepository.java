package com.jspark.health.dao;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jspark.health.dto.Gym;

import reactor.core.publisher.Flux;

@Repository
public interface GymRepository extends MongoRepository<Gym, String> {
	public Flux<Gym> findByNameIgnoreCaseLikeAndLocationNear(String name, Point location, Distance distance);
	public Flux<Gym> findByAddress(String address);
	public Flux<Gym> findByLocationNear(Point location, Distance distance);
}
