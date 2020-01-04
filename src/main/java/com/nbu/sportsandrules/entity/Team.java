package com.nbu.sportsandrules.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @JoinColumn(name = "league_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private League league;

    @OneToMany(mappedBy = "team")
    private Set<Athlete> athletes;

    @OneToMany(mappedBy = "hostTeam")
    private Set<Event> hostEvents;

    @OneToMany(mappedBy = "guestTeam")
    private Set<Event> guestEvents;

    @OneToMany(mappedBy = "team")
    private Set<Comment> comments;

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
    }

    public Set<Event> getHostEvents() {
        return hostEvents;
    }

    public void setHostEvents(Set<Event> hostEvents) {
        this.hostEvents = hostEvents;
    }

    public Set<Event> getGuestEvents() {
        return guestEvents;
    }

    public void setGuestEvents(Set<Event> guestEvents) {
        this.guestEvents = guestEvents;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
