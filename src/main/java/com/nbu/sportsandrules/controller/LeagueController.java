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
import com.nbu.sportsandrules.controller.body.LeagueBody;
import com.nbu.sportsandrules.controller.body.TeamBody;
import com.nbu.sportsandrules.entity.Athlete;
import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.AthleteService;
import com.nbu.sportsandrules.service.LeagueService;
import com.nbu.sportsandrules.service.SportService;
import com.nbu.sportsandrules.service.TeamService;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("api/leagues")
public class LeagueController {
    @Autowired
    private SportService sportService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private AthleteService athleteService;

    @GetMapping()
    public ResponseEntity<List<LeagueBody>> getAllLeagues() {
        List<League> leagues = leagueService.getAllLEagues();
        List<LeagueBody> leagueBodies = new ArrayList<>();

        for (League league : leagues) {
            LeagueBody leagueBody = league.initLeagueBody();
            /*
             * List<TeamBody> teamBodies = new ArrayList<>();
             *
             * List<Team> teams = teamService.getTeamsByLeagueId(league.getId()); for (Team
             * team : teams) { team.setLeague(league); teamBodies.add(team.initTeamBody());
             * }
             *
             * leagueBody.setTeams(teamBodies);
             */
            leagueBodies.add(leagueBody);
        }
        return new ResponseEntity<List<LeagueBody>>(leagueBodies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueBody> getLeagueById(@PathVariable("id") Integer id) {
        League league = leagueService.getLeagueById(id);
        if (league == null) {
            return new ResponseEntity<LeagueBody>(HttpStatus.NOT_FOUND);
        }
        LeagueBody leagueBody = league.initLeagueBody();
        return new ResponseEntity<LeagueBody>(leagueBody, HttpStatus.OK);
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<Team>> getTeamsByLeagueById(@PathVariable("id") Integer id) {
        List<TeamBody> teamBodies = new ArrayList<>();
        League league = leagueService.getLeagueById(id);

        List<Team> teams = teamService.getTeamsByLeagueId(league.getId());
        for (Team team : teams) {
            team.setLeague(league);
            teamBodies.add(team.initTeamBody());
        }
        return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}/athletes")
    public ResponseEntity<List<AthleteBody>> getAllAthletesByTeamId(@PathVariable("id") Integer id) {
        List<AthleteBody> athleteBodies = new ArrayList<>();
        List<Athlete> athletes = athleteService.getAllAthletesByLeagueId(id);

        for (Athlete athlete : athletes) {
            athleteBodies.add(athlete.initAthleteBody());
        }

        return new ResponseEntity<List<AthleteBody>>(athleteBodies, HttpStatus.OK);
    }

    /**
     * add
     */
    @PostMapping()
    public ResponseEntity<Void> addLeague(@RequestBody LeagueBody leagueBody) {
        League newLeague;

        try {
            newLeague = leagueBody.initLeague();
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        newLeague.setSport(sportService.getSportById(leagueBody.getSportId()));

        boolean leagueExists = leagueService.addLeague(newLeague);
        if (leagueExists) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * PUT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLeagueById(@PathVariable("id") Integer id, @RequestBody LeagueBody leagueBody) {
        League league = leagueService.getLeagueById(id);

        String name = leagueBody.getName();
        if (name != null) {
            league.setName(name);
        }

        String country = leagueBody.getCountry();
        if (country != null) {
            league.setCountry(country);
        }

        Integer sportId = leagueBody.getSportId();
        if (sportId != null) {
            league.setSport(sportService.getSportById(sportId));
        }

        leagueService.updateLeague(league);

        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable("id") Integer id) {
        leagueService.deleteLeague(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
