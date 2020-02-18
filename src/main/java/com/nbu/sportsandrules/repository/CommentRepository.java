package com.nbu.sportsandrules.repository;

import com.nbu.sportsandrules.entity.Comment;
import com.nbu.sportsandrules.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment getCommentByEvent(Event event);
}
