CREATE TABLE expenseitems (
    id SERIAL PRIMARY KEY,
    budgetid INT NOT NULL,
    categoryid INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (budgetid) REFERENCES budgets(id) ON DELETE CASCADE,
    FOREIGN KEY (categoryid) REFERENCES category(id) ON DELETE CASCADE
);
