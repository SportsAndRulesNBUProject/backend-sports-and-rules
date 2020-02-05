package com.nbu.sportsandrules.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.AthleteBody;

@Entity
public class Athlete {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@NotBlank
	private String name;

	private Integer age;

	@JoinColumn(name = "team_id")
	@JsonIgnore
	@ManyToOne
	private Team team;

	@JoinColumn(name = "league_id")
	@ManyToOne // (cascade = CascadeType.ALL)
	@JsonIgnore
	private League league;

	@ManyToMany(mappedBy = "favouriteAthletes")
	@JsonIgnore
	private Set<League> favouriteLeagues;

	@OneToMany(mappedBy = "athlete")
	private Set<Comment> comments;

	public Athlete() {
	}

	public AthleteBody initAthleteBody() {
		AthleteBody athleteBody = new AthleteBody();
		athleteBody.setId(id);
		athleteBody.setName(name);
		athleteBody.setAge(age);
		if (team != null) {
			athleteBody.setTeamId(team.getId());
		}
		athleteBody.setLeagueId(league.getId());
		return athleteBody;
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
