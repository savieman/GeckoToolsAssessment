package com.example.RecipeService.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ingredient {
    @Column(nullable = false)
    @Id
    private String name;

    // For now I used just "amount" but with a real application this is too generic and units of measure would need to be taken into account like grams or cups etc.
    @Column(nullable = false)
    private double amount;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
