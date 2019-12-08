package com.nbu.sportsandrules.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private SportCategory category;

    private String history;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Sport_Achievement",
            joinColumns = {@JoinColumn(name = "sport_id")},
            inverseJoinColumns = {@JoinColumn(name = "achievement_id")}
    )
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "sport")
    private List<Event> events;

    public Sport() {
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
}
