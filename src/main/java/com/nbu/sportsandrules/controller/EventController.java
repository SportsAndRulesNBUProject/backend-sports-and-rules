package com.nbu.sportsandrules.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbu.sportsandrules.controller.body.EventBody;
import com.nbu.sportsandrules.entity.Event;
import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.entity.Team;
import com.nbu.sportsandrules.service.EventService;
import com.nbu.sportsandrules.service.SportService;
import com.nbu.sportsandrules.service.TeamService;

@Controller
@RequestMapping("event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private SportService sportService;

    @Autowired
    private TeamService teamService;

    @GetMapping("all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") Integer id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addExample(@PathVariable("id") EventBody eventBody) {
        Event newEvent = eventBody.initEvent();

        Sport sport = sportService.getSportById(eventBody.getSportId());
        Team hostTeam = teamService.getTeamById(eventBody.getHostTeamId());
        Team guestTeam = teamService.getTeamById(eventBody.getGuestTeamId());

        newEvent.setSport(sport);
        newEvent.setHostTeam(hostTeam);
        newEvent.setGuestTeam(guestTeam);

        eventService.addEvent(newEvent);
        return new ResponseEntity<Event>(HttpStatus.ACCEPTED);
    }
}
