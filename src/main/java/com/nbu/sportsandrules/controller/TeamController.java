package com.nbu.sportsandrules.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbu.sportsandrules.controller.body.AthleteBody;
import com.nbu.sportsandrules.controller.body.TeamBody;
import com.nbu.sportsandrules.entity.Athlete;
import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.AthleteService;
import com.nbu.sportsandrules.service.LeagueService;
import com.nbu.sportsandrules.service.TeamService;

@Controller
@RequestMapping("api/teams")
public class TeamController {
	@Autowired
	private TeamService teamService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private AthleteService athleteService;

	@GetMapping()
	public ResponseEntity<List<TeamBody>> getAllTeams() {
		List<Team> allTeams = teamService.getAllTeams();
		List<TeamBody> teamBodies = new ArrayList<>();

		for (Team team : allTeams) {
			teamBodies.add(team.initTeamBody());
		}

		return new ResponseEntity<>(teamBodies, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeamBody> getTeamById(@PathVariable("id") Integer id) {
		Team team = teamService.getTeamById(id);
		if (team == null) {
			return new ResponseEntity<TeamBody>(HttpStatus.NOT_FOUND);
		}

		TeamBody teamBody = team.initTeamBody();
		return new ResponseEntity<TeamBody>(teamBody, HttpStatus.OK);
	}

	@GetMapping("/{id}/athletes")
	public ResponseEntity<List<AthleteBody>> getAllAthletesByTeamId(@PathVariable("id") Integer id) {
		List<AthleteBody> athleteBodies = new ArrayList<>();
		List<Athlete> athletes = athleteService.getAllAthletesByTeamId(id);

		for (Athlete athlete : athletes) {
			athleteBodies.add(athlete.initAthleteBody());
		}

		return new ResponseEntity<List<AthleteBody>>(athleteBodies, HttpStatus.OK);
	}

	/**
	 * add
	 */
	@PostMapping()
	public ResponseEntity<Void> addTeam(@RequestBody TeamBody teamBody) {
		Team team;
		try {
			team = teamBody.initTeam();
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		League leagueById = leagueService.getLeagueById(teamBody.getLeagueId());
		team.setLeague(leagueById);

		teamService.addTeam(team);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * UPDATE
	 */

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateTeamById(@PathVariable("id") Integer id, @RequestBody TeamBody teamBody) {
		Team team = teamService.getTeamById(id);

		Integer leagueID = teamBody.getLeagueId();
		if (leagueID != null) {
			team.setLeague(leagueService.getLeagueById(leagueID));
		}

		String name = teamBody.getName();
		if (name != null) {
			team.setName(name);
		}

		teamService.updateTeam(team);

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Delete
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable("id") Integer id) {
		teamService.deleteTeam(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
