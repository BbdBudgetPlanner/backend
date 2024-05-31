package com.bbd.BudgetPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;

//@EntityScan({ "com.bbd.BudgetPlanner" })
//@ComponentScan({ "com.bbd.BudgetPlanner" })
@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    SpringApplication.run(ServerApplication.class, args);
  }
}
