package com.nbu.sportsandrules.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.LeagueBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String country;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Favourite_Athletes", joinColumns = {@JoinColumn(name = "league_id")}, inverseJoinColumns = {
            @JoinColumn(name = "athlete_id")})
    private Set<Athlete> favouriteAthletes;

    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private List<Team> teams;

    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private List<Athlete> athletes;

    @OneToMany(mappedBy = "league")
    @JsonIgnore
    private Set<Comment> comments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @OneToMany(mappedBy = "league")
    @JsonIgnore
    private List<Event> events;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public League() {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Athlete> getFavouriteAthletes() {
        return favouriteAthletes;
    }

    public void setFavouriteAthletes(Set<Athlete> favouriteAthletes) {
        this.favouriteAthletes = favouriteAthletes;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LeagueBody initLeagueBody() {
        LeagueBody body = new LeagueBody();
        body.setId(id);
        body.setCountry(country);
        body.setName(name);
        body.setSportId(sport.getId());
        body.setImage(Base64.encode(image));
        return body;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
