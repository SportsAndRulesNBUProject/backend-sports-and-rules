package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.TeamBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @JsonIgnore
    @JoinColumn(name = "league_id")
    @ManyToOne
    private League league;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<Athlete> athletes;

    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private Set<Event> hostEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "guestTeam")
    private Set<Event> guestEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<Comment> comments;

    private String country;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public TeamBody initTeamBody() {
        TeamBody teamBody = new TeamBody();
        teamBody.setId(id);
        teamBody.setLeagueId(league.getId());
        teamBody.setName(name);
        teamBody.setCountry(country);
        teamBody.setImage(Base64.encode(image));
        return teamBody;
    }
}
