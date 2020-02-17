package com.nbu.sportsandrules.controller.body;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.sportsandrules.entity.Event;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class EventBody {
    private Integer id;
    private String name;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime date;
    private Integer sportId;
    private Integer leagueId;
    private Integer guestTeamId;
    private Set<CommentBody> commentBodies;
    private String image;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime createdDate;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime updatedDate;

    public Event initEvent() {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        event.setImage(Base64.decode(image));
        return event;
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

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getGuestTeamId() {
        return guestTeamId;
    }

    public void setGuestTeamId(Integer guestTeamId) {
        this.guestTeamId = guestTeamId;
    }

    public Set<CommentBody> getCommentBodies() {
        return commentBodies;
    }

    public void setCommentBodies(Set<CommentBody> commentBodies) {
        this.commentBodies = commentBodies;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
