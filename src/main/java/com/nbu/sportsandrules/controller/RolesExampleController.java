package com.nbu.sportsandrules.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RolesExampleController {

    @PostMapping("/for-everyone")
    public ResponseEntity<?> doSmth() {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> onlyUser() {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> onlyAdmin() {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/only-for-moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> onlyModerator() {

        return ResponseEntity.ok().build();
    }
}
