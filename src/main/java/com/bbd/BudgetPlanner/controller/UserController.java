package com.bbd.BudgetPlanner.controller;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/user")
    public ResponseEntity<?> createCategory(@RequestBody Users user) {
        Users entity = repo.save(user);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(entity);
    }
}
