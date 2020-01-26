package com.nbu.sportsandrules.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.LeagueBody;

@Entity
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String country;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Favourite_Athletes", joinColumns = { @JoinColumn(name = "league_id") }, inverseJoinColumns = {
			@JoinColumn(name = "athlete_id") })
	private Set<Athlete> favouriteAthletes;

	@JsonIgnore
	@OneToMany(mappedBy = "league")
	private List<Team> teams;

	@OneToMany(mappedBy = "league")
	@JsonIgnore
	private Set<Comment> comments;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sport_id")
	private Sport sport;

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

	public LeagueBody initLeagueBody() {
		LeagueBody body = new LeagueBody();
		body.setId(id);
		body.setCountry(country);
		body.setName(name);
		body.setSportId(sport.getId());
		return body;
	}

}
