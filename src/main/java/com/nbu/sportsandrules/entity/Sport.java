package com.nbu.sportsandrules.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nbu.sportsandrules.controller.body.AchievementBody;
import com.nbu.sportsandrules.controller.body.SportBody;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "category_id")
    @ManyToOne
    @NotNull
    private SportCategory category;

    private String history;

    @OneToMany(mappedBy = "sport", cascade = {CascadeType.ALL})
    @JsonIgnore
    //@JoinTable(name = "Sport_Achievement", joinColumns = { @JoinColumn(name = "sport_id") }, inverseJoinColumns = {
    //@JoinColumn(name = "achievement_id") })
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "sport")
    @JsonIgnore
    private List<Event> events;

    @NotBlank
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    private List<League> leagues;

    public Sport() {
    }

    public Sport(String name, SportCategory sportCategory) {
        this.name = name;
        this.category = sportCategory;
    }

    public List<League> getLeague() {
        return leagues;
    }

    public void setLeague(List<League> leagues) {
        this.leagues = leagues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SportCategory getCategory() {
        return category;
    }

    public void setCategory(SportCategory category) {
        this.category = category;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sport [id=" + id + ", category=" + category.toString() + ", history=" + history + ", achievements="
                + achievements + ", events=" + events + ", name=" + name + "]";
    }

    public SportBody initSportBody() {
        SportBody sportBody = new SportBody();
        sportBody.setId(id);
        sportBody.setName(name);
        sportBody.setHistory(history);
        sportBody.setCategoryId(category.getId());
        List<AchievementBody> achievementBodys = new ArrayList<>();
        for (Achievement achievement : achievements) {
            AchievementBody achievementBody = achievement.initAchievementBody();
            achievementBodys.add(achievementBody);
        }
        sportBody.setAchievements(achievementBodys);

        return sportBody;
    }

}
