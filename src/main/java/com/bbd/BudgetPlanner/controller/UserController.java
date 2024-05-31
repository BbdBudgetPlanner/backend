package com.bbd.BudgetPlanner.controller;

import java.util.HashMap;
import java.util.Optional;

import java.util.Collections;
import java.util.Map;
//
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.BudgetPlanner.models.Users;
import com.bbd.BudgetPlanner.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;

@RestController
public class UserController {
    
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("===========================");
        System.out.println(principal.toString());
        System.out.println(principal.getName());
        System.out.println("===========================");
        return Collections.singletonMap("username", principal.getAttribute("username"));
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // time in local db is 2 hours behind for some reason
        user.setCreatedat(timestamp);
        Users entity = repo.save(user);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(entity);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<Users> user = repo.findById(id);
        if (user.isPresent()) {
            Users entity = user.get();
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
        }
        String errorMessage = "User not found with id: " + id;
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(createEntity("message", errorMessage));
    }

    @GetMapping("/api/usersemail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<Users> user = repo.findByEmail(email);
        if (user.isPresent()) {
            Users entity = user.get();
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
        }
        String errorMessage = "User not found with email: " + email;
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(createEntity("message", errorMessage));
    }

    public HashMap<String, String> createEntity(String x, String y) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(x, y);
        return map;
    }
}


