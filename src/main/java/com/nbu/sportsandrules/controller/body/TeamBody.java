package com.nbu.sportsandrules.controller.body;

import com.nbu.sportsandrules.entity.Team;

public class TeamBody {
    private Integer id;
    private String name;
    private Integer leagueId;

    public Team initTeam() {
        Team team = new Team();
        team.setName(name);
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

}
