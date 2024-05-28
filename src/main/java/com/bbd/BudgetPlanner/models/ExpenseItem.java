package com.bbd.BudgetPlanner.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "expenseitems")
public class ExpenseItem {
    
    @Id 
    @GeneratedValue
    @Column(name = "id") 
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL) // cascade all will save the data from the customer object in the Customer table in db
    @JoinColumn(name = "budgetid") 
    private Budget budget; // foreign key

    @ManyToOne(cascade = CascadeType.ALL) // cascade all will save the data from the customer object in the Customer table in db
    @JoinColumn(name = "categoryid") 
    private Category category; // foreign key

    @Column(name = "name")
    private String name; // date and time

    @Column(name = "price")
    private Double price; 

    @Column(name = "createdat")
    private String createdat; // date and time

    public ExpenseItem() {}

    public ExpenseItem(String name, Double price, String createdat) {
        this.name = name;
        this.price = price;
        this.createdat = createdat;
    }
}
