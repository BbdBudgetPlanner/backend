package com.bbd.BudgetPlanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetController {
	
    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
