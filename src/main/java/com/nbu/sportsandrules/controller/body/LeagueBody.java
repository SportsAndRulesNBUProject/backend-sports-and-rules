package com.nbu.sportsandrules.controller.body;

import java.util.ArrayList;
import java.util.List;

import com.nbu.sportsandrules.entity.League;

public class LeagueBody {
	private Integer id;
	private Integer sportId;
	private String name;
	private String country;
	private List<TeamBody> teams;

	public League initLeague() {
		League league = new League();
		league.setName(name);
		league.setCountry(country);
		teams = new ArrayList<>();
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

	public List<TeamBody> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamBody> teams) {
		this.teams = teams;
	}

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

}
