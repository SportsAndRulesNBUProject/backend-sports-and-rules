package com.nbu.sportsandrules.entity;

import com.nbu.sportsandrules.controller.body.CommentBody;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String comment;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @JoinColumn(name = "event_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Event event;

    @JoinColumn(name = "league_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private League league;

    @JoinColumn(name = "team_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @JoinColumn(name = "athlete_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Athlete athlete;

    private ZonedDateTime createdAt;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
