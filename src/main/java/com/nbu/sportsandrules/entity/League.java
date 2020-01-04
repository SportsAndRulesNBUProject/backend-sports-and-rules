package com.nbu.sportsandrules.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String country;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Favourite_Athletes",
            joinColumns = {@JoinColumn(name = "league_id")},
            inverseJoinColumns = {@JoinColumn(name = "athlete_id")}
    )
    private Set<Athlete> favouriteAthletes;

    @OneToMany(mappedBy = "league")
    private Set<Team> teams;

    @OneToMany(mappedBy = "league")
    private Set<Comment> comments;

    public League() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
