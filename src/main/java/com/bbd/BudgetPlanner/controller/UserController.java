package com.bbd.BudgetPlanner.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.BudgetPlanner.models.Users;
import com.bbd.BudgetPlanner.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
    
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("username", principal.getAttribute("username"));
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> createCategory(@RequestBody Users user) {
        Users entity = repo.save(user);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(entity);
    }
}


