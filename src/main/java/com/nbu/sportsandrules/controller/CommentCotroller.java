package com.nbu.sportsandrules.controller;

import com.nbu.sportsandrules.controller.body.CommentBody;
import com.nbu.sportsandrules.entity.Comment;
import com.nbu.sportsandrules.entity.User;
import com.nbu.sportsandrules.repository.UserRepository;
import com.nbu.sportsandrules.service.CommentService;
import com.nbu.sportsandrules.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("api/comment")
public class CommentCotroller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/event/{id}")
    public ResponseEntity<CommentBody> getAllEventComments(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(commentService.getCommentByEventId(id), HttpStatus.OK);
    }

    @PostMapping("event")
    @PreAuthorize("hasAnyRole('USER','MODERATOR','ADMIN')")
    public ResponseEntity<CommentBody> addEventComment(@RequestBody CommentBody commentBody) {
        Comment comment = commentBody.buildComment();
        comment.setUser(getCurrentUser());
        comment.setEvent(eventService.getEventById(commentBody.getEventId()));
        comment.setCreatedAt(ZonedDateTime.now());
        commentService.addComment(comment);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            return userRepository.findByUsername(auth.getName()).get();
        }

        return null;
    }
}
