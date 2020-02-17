package com.nbu.sportsandrules.controller;

import java.time.OffsetDateTime;
import java.util.List;

import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.nbu.sportsandrules.controller.body.EventBody;
import com.nbu.sportsandrules.entity.Event;
import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.EventService;
import com.nbu.sportsandrules.service.SportService;
import com.nbu.sportsandrules.service.TeamService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private SportService sportService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueService leagueService;

    @GetMapping("all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventBody> getEventById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(eventService.getEventById(id).buildEventBody(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(EventBody eventBody) {
        Event newEvent = eventBody.initEvent();

        Sport sport = sportService.getSportById(eventBody.getSportId());
        League league = leagueService.getLeagueById(eventBody.getLeagueId());
        Team guestTeam = teamService.getTeamById(eventBody.getGuestTeamId());

        newEvent.setSport(sport);
        newEvent.setLeague(league);
        newEvent.setGuestTeam(guestTeam);

        newEvent.setCreatedDate(OffsetDateTime.now());
        newEvent.setUpdatedDate(OffsetDateTime.now());

        eventService.addEvent(newEvent);
        return new ResponseEntity<Event>(HttpStatus.ACCEPTED);
    }
}
