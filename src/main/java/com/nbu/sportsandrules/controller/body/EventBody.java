package com.nbu.sportsandrules.controller.body;

import java.time.OffsetDateTime;
import java.util.List;

import com.nbu.sportsandrules.entity.Event;

public class EventBody {
	private String name;
	private OffsetDateTime date;
	private Integer sportId;
	private Integer hostTeamId;
	private Integer guestTeamId;
	private List<CommentBody> commentBodies;

	public Event initEvent() {
		Event event = new Event();
		event.setName(name);
		event.setDate(date);
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

	public Integer getHostTeamId() {
		return hostTeamId;
	}

	public void setHostTeamId(Integer hostTeamId) {
		this.hostTeamId = hostTeamId;
	}

	public Integer getGuestTeamId() {
		return guestTeamId;
	}

	public void setGuestTeamId(Integer guestTeamId) {
		this.guestTeamId = guestTeamId;
	}

	public List<CommentBody> getCommentBodies() {
		return commentBodies;
	}

	public void setCommentBodies(List<CommentBody> commentBodies) {
		this.commentBodies = commentBodies;
	}

}
