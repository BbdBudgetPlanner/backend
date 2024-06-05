package com.bbd.BudgetPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;

//@EntityScan({ "com.bbd.BudgetPlanner" })
//@ComponentScan({ "com.bbd.BudgetPlanner" })
@SpringBootApplication
@EnableMethodSecurity
public class ServerApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    //System.setProperty("COGNITO_CLIENT_ID", dotenv.get("COGNITO_CLIENT_ID"));
    //System.setProperty("COGNITO_CLIENT_SECRET", dotenv.get("COGNITO_CLIENT_SECRET"));
    //System.setProperty("COGNITO_CLIENT_NAME", dotenv.get("COGNITO_CLIENT_NAME"));
    SpringApplication.run(ServerApplication.class, args);
  }
}
