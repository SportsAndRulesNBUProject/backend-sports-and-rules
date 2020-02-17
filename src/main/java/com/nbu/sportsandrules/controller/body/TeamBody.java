package com.nbu.sportsandrules.controller.body;

import com.nbu.sportsandrules.entity.Team;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class TeamBody {
    private Integer id;
    private String name;
    private Integer leagueId;
    private String country;
    private String image;

    public Team initTeam() {
        Team team = new Team();
        team.setName(name);
        team.setCountry(country);
        team.setImage(Base64.decode(image));
        return team;
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

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
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
