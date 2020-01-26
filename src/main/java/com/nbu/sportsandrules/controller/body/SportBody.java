package com.nbu.sportsandrules.controller.body;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.entity.SportCategory;

public class SportBody {
	private Integer id;
	private Integer categoryId;
	private String history;
	private String name;
	private List<AchievementBody> achievements;

	public Sport initSport(SportCategory sportCategory) {
		if (name == null || sportCategory == null) {
			throw new ConstraintViolationException("Sport Category and Name Cannot be null.", null);
		}
		Sport newSport = new Sport(name, sportCategory);
		newSport.setHistory(history);
		return newSport;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<AchievementBody> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementBody> achievements) {
		this.achievements = achievements;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SportRest [categoryId=" + categoryId + ", history=" + history + ", name=" + name + ", achievements="
				+ achievements + "]";
	}

}
