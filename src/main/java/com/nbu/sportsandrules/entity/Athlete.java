package com.nbu.sportsandrules.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    @JoinColumn(name = "team_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @JoinColumn(name = "league_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private League league;

    @ManyToMany(mappedBy = "favouriteAthletes")
    private Set<League> favouriteLeagues;

    @OneToMany(mappedBy = "athlete")
    private Set<Comment> comments;

    public Athlete() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<League> getFavouriteLeagues() {
        return favouriteLeagues;
    }

    public void setFavouriteLeagues(Set<League> favouriteLeagues) {
        this.favouriteLeagues = favouriteLeagues;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
