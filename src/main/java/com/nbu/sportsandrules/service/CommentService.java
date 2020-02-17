package com.nbu.sportsandrules.service;

import com.nbu.sportsandrules.controller.body.CommentBody;
import com.nbu.sportsandrules.entity.Comment;
import com.nbu.sportsandrules.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentRepository commentRepository;

    public CommentBody getCommentByEventId(Integer eventId) {

        Comment comment = commentRepository.getCommentByEvent(eventService.getEventById(eventId));

        CommentBody commentBody = new CommentBody();
        commentBody.setUserId(comment.getUser().getId());
        commentBody.setCreatedAt(comment.getCreatedAt());
        commentBody.setComment(comment.getComment());
        commentBody.setUser(comment.getUser());

        return commentBody;
    }

    public boolean addComment(Comment comment) {
        commentRepository.save(comment);
        return true;
    }
}
