package com.nbu.sportsandrules.controller.body;

import java.time.OffsetDateTime;

import com.nbu.sportsandrules.entity.Achievement;

public class AchievementBody {
    private Integer id;
    private String name;
    private String description;
    private Double score;
    private OffsetDateTime date;

    public Achievement initAchievement() {
        Achievement achievement = new Achievement();
        achievement.setName(name);
        achievement.setScore(score);
        achievement.setDate(date);
        achievement.setDescription(description);
        return achievement;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

}
