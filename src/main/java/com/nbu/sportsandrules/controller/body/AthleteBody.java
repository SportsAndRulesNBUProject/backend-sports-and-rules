package com.nbu.sportsandrules.controller.body;

import javax.validation.ConstraintViolationException;

import com.nbu.sportsandrules.entity.Athlete;

public class AthleteBody {
	private Integer id;
	private String name;
	private Integer age;
	private Integer teamId;
	private Integer leagueId;

	public Athlete initAthlete() {
		if (name == null) {
			throw new ConstraintViolationException("Athlete name Cannot be null.", null);
		}

		Athlete newAthlete = new Athlete();
		newAthlete.setName(name);
		newAthlete.setAge(age);
		return newAthlete;
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

	public Integer getTeamId() {
		return teamId;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

}
