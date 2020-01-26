package com.nbu.sportsandrules.controller;

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
import com.nbu.sportsandrules.entity.Athlete;
import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.AthleteService;
import com.nbu.sportsandrules.service.LeagueService;
import com.nbu.sportsandrules.service.TeamService;

@Controller
@RequestMapping("api/athletes")
public class AthleteController {

	@Autowired
	private AthleteService athleteService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private LeagueService leagueService;

	@GetMapping("/{id}")
	public ResponseEntity<AthleteBody> getAthleteById(@PathVariable("id") Integer id) {
		Athlete athlete = athleteService.getAthleteByid(id);
		if (athlete == null) {
			return new ResponseEntity<AthleteBody>(HttpStatus.NOT_FOUND);
		}

		AthleteBody athleteBody = athlete.initAthleteBody();
		return new ResponseEntity<AthleteBody>(athleteBody, HttpStatus.OK);
	}

	/**
	 * add
	 */
	@PostMapping()
	public ResponseEntity<Void> addAthlete(@RequestBody AthleteBody athleteBody) {
		Athlete newAthlete;
		try {
		 newAthlete = athleteBody.initAthlete();}
		catch (ConstraintViolationException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		Integer teamId = athleteBody.getTeamId();
		if (teamId != null) {
			Team team = teamService.getTeamById(teamId);
			newAthlete.setTeam(team);
		}

		Integer leagueId = athleteBody.getLeagueId();
		if (leagueId != null) {
			League league = leagueService.getLeagueById(leagueId);
			newAthlete.setLeague(league);
		}

		athleteService.addAthlete(newAthlete);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * UPDATE
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAthleteById(@PathVariable("id") Integer id,
			@RequestBody AthleteBody athleteBody) {
		Athlete athlete = athleteService.getAthleteByid(id);
		String name = athleteBody.getName();
		if (name != null) {
			athlete.setName(name);
		}

		Integer age = athleteBody.getAge();
		if (age != null) {
			athlete.setAge(age);
		}

		Integer leagueId = athleteBody.getLeagueId();
		if (leagueId != null) {
			athlete.setLeague(leagueService.getLeagueById(leagueId));
		}

		Integer teamId = athleteBody.getTeamId();
		if (teamId != null) {
			athlete.setTeam(teamService.getTeamById(teamId));
		}

		athleteService.updateAthlete(athlete);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Delete
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAthlete(@PathVariable("id") Integer id) {
		athleteService.deleteAthlete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
