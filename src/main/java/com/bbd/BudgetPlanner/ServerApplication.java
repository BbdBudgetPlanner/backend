package com.bbd.BudgetPlanner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
@EnableMethodSecurity
public class ServerApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();
    System.setProperty("DB_URL", dotenv.get("DB_URL"));
    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    SpringApplication.run(ServerApplication.class, args);
  }

  @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
        .allowedOrigins("https://dfn01vp2479p8.cloudfront.net")
        .allowedHeaders("*")
        .allowCredentials(true)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}

  //@Bean
  //public Filter corsFilter() {
  //  return new OncePerRequestFilter() {
  //    @Override
  //    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
  //            throws ServletException, IOException {
  //      response.setHeader("Access-Control-Allow-Origin", "https://dfn01vp2479p8.cloudfront.net");
  //      response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
  //      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
  //      response.setHeader("Access-Control-Allow-Credentials", "true");
  //      
  //      if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
  //          response.setStatus(HttpServletResponse.SC_OK);
  //      } 
  //      else {
  //        filterChain.doFilter(request, response);
  //      }
  //    }
  //  };
  //}
}
