CREATE TABLE expenseitems (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    budgetid INTEGER NOT NULL,
    categoryid INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (budgetid) REFERENCES budgets(id) ON DELETE CASCADE,
    FOREIGN KEY (categoryid) REFERENCES category(id) ON DELETE CASCADE
);
