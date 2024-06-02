package com.bbd.BudgetPlanner.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.BudgetPlanner.models.*;
import com.bbd.BudgetPlanner.repository.*;

@RestController
public class BudgetController {

	private final BudgetRepository budgetRepo;
	private final UserRepository userRepo;

	public BudgetController(BudgetRepository budgetRepo, UserRepository userRepo) {
		this.budgetRepo = budgetRepo;
		this.userRepo = userRepo;
	}
	
    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

    @GetMapping("/api/usersbudget/{id}")
	ResponseEntity<?> getAllUsersBudgets(@PathVariable Users user) {
		List<Budget> b = budgetRepo.findByUser(user);
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(b);
	}

	@GetMapping("/api/usersbudget/{id}")
	ResponseEntity<?> getBudget(@PathVariable Long id) {
		Optional<Budget> b = budgetRepo.findById(id);
        if (b.isPresent()) {
            Budget entity = b.get();
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
        }
        String errorMessage = "Budget not found with id: " + id;
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(createEntity("message", errorMessage));
	}

	@GetMapping("/api/budgetbyname/{name}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String name) {
        Optional<Budget> b = budgetRepo.findByName(name);
        if (b.isPresent()) {
            Budget entity = b.get();
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
        }
        String errorMessage = "Budget not found with name: " + name;
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(createEntity("message", errorMessage));
    }

	@PostMapping("/api/budget")
    ResponseEntity<?> createBudget( @RequestParam Long userid,
                                    @RequestParam String name,
									@RequestParam Double amount ) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // time in local db is 2 hours behind for some reason
		Budget budget = new Budget(name, amount, timestamp);

        Optional<Users> user = userRepo.findById(userid);

        if (user.isPresent()) {
			budget.setUser(user.get());
			Budget b = budgetRepo.save(budget);
            return ResponseEntity
				.status(HttpStatus.OK)
				.body(b);
        }
        String errorMessage = "User id is invalid: " + userid;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createEntity("message", errorMessage));
    }

	public HashMap<String, String> createEntity(String x, String y) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(x, y);
        return map;
    }
}
