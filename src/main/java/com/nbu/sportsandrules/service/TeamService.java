package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.repository.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	public Team getTeamById(Integer id) {
		try {
			Team team = teamRepository.findById(id).get();
			return team;
		} catch (NoSuchElementException nse) {
			return null;
		}
	}

	public List<Team> getAllTeams() {
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach(team -> teams.add(team));
		return teams;
	}

	public void deleteTeam(Integer id) {
		teamRepository.deleteById(id);
	}

	public void addTeam(Team team) {
		teamRepository.save(team);
	}

	public List<Team> getTeamsByLeagueId(Integer id) {
		return teamRepository.findByLeagueId(id);
	}

	public void updateTeam(Team team) {
		teamRepository.save(team);
	}

}
