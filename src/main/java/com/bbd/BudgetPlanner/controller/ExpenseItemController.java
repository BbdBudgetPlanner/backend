package com.bbd.BudgetPlanner.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.BudgetPlanner.models.*;
import com.bbd.BudgetPlanner.repository.*;

record ExpenseItemDTO() {

}

@RestController
public class ExpenseItemController {
    
    private final ExpenseItemRepository exRepo;
    private final BudgetRepository budgetRepo;
    private final CategoryRepository categoryRepo;

    public ExpenseItemController(ExpenseItemRepository exRepo, BudgetRepository budgetRepo, CategoryRepository categoryRepo) {
        this.exRepo = exRepo;
        this.budgetRepo = budgetRepo;
        this.categoryRepo = categoryRepo;
    }

    @PostMapping("/api/expenseitem")
    ResponseEntity<?> createExpenseItem( @RequestParam Long budgetid,
                                    @RequestParam Long categoryid,
                                    @RequestParam String name,
									@RequestParam Double price ) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // time in local db is 2 hours behind for some reason
		ExpenseItem ex = new ExpenseItem(name, price, timestamp);

        Optional<Budget> b = budgetRepo.findById(budgetid);
        Optional<Category> c = categoryRepo.findById(categoryid);

        if (c.isPresent() && b.isPresent()) {
			ex.setBudget(b.get());
            ex.setCategory(c.get());
			ExpenseItem item = exRepo.save(ex);
            return ResponseEntity
				.status(HttpStatus.OK)
				.body(item);
        }
        String errorMessage = "Budget id or category id is invalid";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createEntity("message", errorMessage));
    }

    public HashMap<String, String> createEntity(String x, String y) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(x, y);
        return map;
    }

}