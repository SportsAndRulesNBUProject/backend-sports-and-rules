package com.nbu.sportsandrules.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.League;

public interface LeagueRepository extends CrudRepository<League, Integer> {

	List<League> findByName(String name);

	List<League> findBySportId(Integer id);

}
