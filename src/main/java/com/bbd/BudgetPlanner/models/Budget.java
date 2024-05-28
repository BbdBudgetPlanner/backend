package com.bbd.BudgetPlanner.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "budgets")
public class Budget {
    
    @Id 
    @GeneratedValue
    @Column(name = "id") 
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL) // cascade all will save the data from the customer object in the Customer table in db
    @JoinColumn(name = "userid") 
    private Users user; // foreign key

    @Column(name = "amount")
    private Double amount; 

    @Column(name = "createdat")
    private String createdat; // date and time
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "budget", cascade = CascadeType.ALL)
    private List<ExpenseItem> expenseItems;

    Budget() {}

    public Budget(Double amount, String createdat) {
        this.amount = amount;
        this.createdat = createdat;
    }
}
