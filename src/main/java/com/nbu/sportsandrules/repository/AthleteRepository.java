package com.nbu.sportsandrules.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.Athlete;

public interface AthleteRepository extends CrudRepository<Athlete, Integer> {

	List<Athlete> findByName(String name);

	List<Athlete> findByTeamId(Integer id);

	List<Athlete> findByLeagueId(Integer id);

}
