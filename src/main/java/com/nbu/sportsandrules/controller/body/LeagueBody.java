package com.nbu.sportsandrules.controller.body;

import java.util.ArrayList;
import java.util.List;

import com.nbu.sportsandrules.entity.League;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class LeagueBody {
    private Integer id;
    private Integer sportId;
    private String name;
    private String country;
    private String image;
    //private List<TeamBody> teams;

    public League initLeague() {
        League league = new League();
        league.setName(name);
        league.setCountry(country);
        league.setImage(Base64.decode(image));
        //teams = new ArrayList<>();
        return league;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    /*
     * public List<TeamBody> getTeams() { return teams; }
     *
     * public void setTeams(List<TeamBody> teams) { this.teams = teams; }
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
