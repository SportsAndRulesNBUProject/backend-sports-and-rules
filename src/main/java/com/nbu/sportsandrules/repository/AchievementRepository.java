package com.nbu.sportsandrules.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.Achievement;

public interface AchievementRepository extends CrudRepository<Achievement, Integer> {

	List<Achievement> findBySportId(Integer sportId);

	Achievement findByIdAndSportId(Integer id, Integer sportId);
}
