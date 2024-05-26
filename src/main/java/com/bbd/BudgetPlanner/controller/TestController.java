package com.bbd.BudgetPlanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    TestController() {

    }

    @SuppressWarnings("null")
    @GetMapping("/test")
    public String testEndpoint() {
        return "Hello, World";
    }

}