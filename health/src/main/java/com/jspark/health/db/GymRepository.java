package com.jspark.health.db;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jspark.health.dto.Gym;

@Repository
public interface GymRepository extends MongoRepository<Gym, String> {
	  public List<Gym> findByNameLike(String name);
	  public List<Gym> findByAddress(String address);
	  public List<Gym> findByLocationNear(Point location, Distance distance);
}
