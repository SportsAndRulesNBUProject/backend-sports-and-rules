package com.nbu.sportsandrules.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private OffsetDateTime date;

    @JoinColumn(name = "sport_id")
    @ManyToOne
    private Sport sport;

    @JoinColumn(name = "host_team_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Team hostTeam;

    @JoinColumn(name = "guest_team_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Team guestTeam;

    @OneToMany(mappedBy = "event")
    private Set<Comment> comments;

    public Event() {
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

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(Team hostTeam) {
        this.hostTeam = hostTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
