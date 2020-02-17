package com.nbu.sportsandrules.entity;

import com.nbu.sportsandrules.controller.body.CommentBody;
import com.nbu.sportsandrules.controller.body.EventBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private LocalDate date;

    @JoinColumn(name = "sport_id")
    @ManyToOne
    private Sport sport;

    @JoinColumn(name = "guest_team_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team guestTeam;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
    private Set<Comment> comments;

    @JoinColumn(name = "league_id")
    @ManyToOne
    private League league;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    private OffsetDateTime createdDate;

    private OffsetDateTime updatedDate;

    public Event() {
    }

    public EventBody buildEventBody() {
        EventBody eventBody = new EventBody();
        eventBody.setName(name);
        eventBody.setDate(date.toString());
        eventBody.setImage(Base64.encode(image));
        eventBody.setCreatedDate(createdDate);
        eventBody.setUpdatedDate(updatedDate);
        Set<CommentBody> commentBodies = new HashSet<>();
        for (Comment comment : comments) {
            CommentBody commentBody = new CommentBody();
            commentBody.setComment(comment.getComment());
            commentBody.setCreatedAt(comment.getCreatedAt());
            commentBody.setUserId(comment.getUser().getId());
            commentBody.setUser(comment.getUser());
            commentBody.setEventId(this.id);
            commentBodies.add(commentBody);
        }

        eventBody.setCommentBodies(commentBodies);
        eventBody.setId(id);

        return eventBody;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(OffsetDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
