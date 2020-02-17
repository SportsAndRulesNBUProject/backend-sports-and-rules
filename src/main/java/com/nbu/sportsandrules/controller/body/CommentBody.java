package com.nbu.sportsandrules.controller.body;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbu.sportsandrules.entity.Comment;
import com.nbu.sportsandrules.entity.User;

import java.time.OffsetDateTime;

public class CommentBody {
    private String comment;
    private Integer eventId;

    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime createdAt;
    private Integer userId;
    private User user;

    public Comment buildComment() {
        Comment comment = new Comment();
        comment.setComment(this.comment);
        comment.setCreatedAt(createdAt);
        return comment;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
