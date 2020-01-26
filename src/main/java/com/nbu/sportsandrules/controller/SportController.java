package com.nbu.sportsandrules.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

import com.nbu.sportsandrules.controller.body.AchievementBody;
import com.nbu.sportsandrules.controller.body.LeagueBody;
import com.nbu.sportsandrules.controller.body.SportBody;
import com.nbu.sportsandrules.controller.body.TeamBody;
import com.nbu.sportsandrules.entity.Achievement;
import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.entity.SportCategory;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.LeagueService;
import com.nbu.sportsandrules.service.SportCategoryService;
import com.nbu.sportsandrules.service.SportService;
import com.nbu.sportsandrules.service.TeamService;

@Controller
@RequestMapping(path = "api/sports")
public class SportController {
	@Autowired
	private SportService sportService;

	@Autowired
	private SportCategoryService sportCategoryService;

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private TeamService teamService;

	@GetMapping()
	public ResponseEntity<List<SportBody>> getAllSports() {
		List<Sport> allSports = sportService.getAllSports();
		List<SportBody> allSportBodies = new ArrayList<>();
		for (Sport sport : allSports) {
			allSportBodies.add(sport.initSportBody());
		}
		return new ResponseEntity<>(allSportBodies, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SportBody> getSportById(@PathVariable("id") Integer id) {
		Sport sport = sportService.getSportById(id);

		if (sport == null) {
			return new ResponseEntity<SportBody>(HttpStatus.NOT_FOUND);
		}

		SportBody sportBody = sport.initSportBody();
		return new ResponseEntity<SportBody>(sportBody, HttpStatus.OK);
	}

	@GetMapping("/{id}/leagues")
	public ResponseEntity<List<LeagueBody>> getLeaguesBySportId(@PathVariable("id") Integer id) {
		List<League> leagues = leagueService.getLeaguesBySportId(id);
		List<LeagueBody> leagueBodies = new ArrayList<>();

		for (League league : leagues) {
			LeagueBody leagueBody = league.initLeagueBody();
			List<TeamBody> teamBodies = new ArrayList<>();

			List<Team> teams = teamService.getTeamsByLeagueId(league.getId());
			for (Team team : teams) {
				team.setLeague(league);
				teamBodies.add(team.initTeamBody());
			}

			leagueBody.setTeams(teamBodies);
			leagueBodies.add(leagueBody);
		}

		return new ResponseEntity<List<LeagueBody>>(leagueBodies, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> addSport(@RequestBody SportBody sportBody) {
		SportCategory sportCategoryByid = sportCategoryService.getSportCategoryByid(sportBody.getCategoryId());
		Sport sport;
		try {
			sport = sportBody.initSport(sportCategoryByid);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		boolean sportExists = sportService.addSport(sport);
		if (sportExists) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		List<Achievement> achievements = new ArrayList<>();
		for (AchievementBody achievementBody : sportBody.getAchievements()) {
			Achievement a = achievementBody.initAchievement();
			a.setSport(sport);
			achievements.add(a);
		}

		sport.setAchievements(achievements);
		sportService.saveAchievements(achievements);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> updateSportById(@PathVariable("id") Integer id, @RequestBody SportBody sportBody) {
		Sport sport = sportService.getSportById(id);
		if (sport == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		String name = sportBody.getName();
		if (name != null) {
			sport.setName(name);
		}

		String history = sportBody.getHistory();
		if (history != null) {
			sport.setHistory(history);
		}

		Integer categoryId = sportBody.getCategoryId();
		if (categoryId != null) {
			sport.setCategory(sportCategoryService.getSportCategoryByid(categoryId));
		}

		List<AchievementBody> achievements = sportBody.getAchievements();
		if (achievements != null) {
			List<Achievement> updateAchievements = new ArrayList<>();

			for (AchievementBody achievementBody : achievements) {
				try {
					Achievement a = addOrUpdateAchievement(sport, achievementBody);
					updateAchievements.add(a);
				} catch (NoSuchElementException nse) {
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}

			}

			sport.setAchievements(updateAchievements);
			sportService.saveAchievements(updateAchievements);
		}
		sportService.updateSport(sport);

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	private Achievement addOrUpdateAchievement(Sport sport, AchievementBody achievementBody) {
		Integer achievementToUpdateId = achievementBody.getId();
		Achievement a;
		if (achievementToUpdateId == null) {
			a = achievementBody.initAchievement();
		} else {
			a = sportService.getAchivementByIdAndSportId(achievementToUpdateId, sport.getId());
			if (a == null) {

				throw new NoSuchElementException();
			}
		}

		String newName = achievementBody.getName();
		if (newName != null) {
			a.setName(newName);
		}

		String newDescription = achievementBody.getDescription();
		if (newDescription != null) {
			a.setDescription(newDescription);
		}

		OffsetDateTime newDate = achievementBody.getDate();
		if (newDate != null) {
			a.setDate(newDate);
		}

		Double newScore = achievementBody.getScore();
		if (newScore != null) {
			a.setScore(newScore);
		}

		a.setSport(sport);
		return a;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSportById(@PathVariable("id") Integer id) {
		sportService.deleteSportById(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
