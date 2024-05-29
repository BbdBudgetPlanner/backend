package com.bbd.BudgetPlanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bbd.BudgetPlanner.models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    
}
