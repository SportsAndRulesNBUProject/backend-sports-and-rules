package com.nbu.sportsandrules.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nbu.sportsandrules.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    List<Team> findByName(String name);

    List<Team> findByLeagueId(Integer id);

}
