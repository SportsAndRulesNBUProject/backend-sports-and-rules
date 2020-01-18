package com.nbu.sportsandrules.controller;

import com.nbu.sportsandrules.entity.User;
import com.nbu.sportsandrules.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RolesExampleController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/for-everyone")
    public ResponseEntity<?> doSmth() {
        // Will be null
        User user = getCurrentUser();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> onlyUser() {
        User user = getCurrentUser();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> onlyAdmin() {
        User user = getCurrentUser();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> onlyModerator() {
        User user = getCurrentUser();

        return ResponseEntity.ok().build();
    }

    // Get the user for who is calling the api. There will be a user only if jwt token provided.
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            return userRepository.findByUsername(auth.getName()).get();
        }

        return null;
    }
}
