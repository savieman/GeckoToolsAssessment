package com.example.RecipeService.Model;

import jakarta.persistence.*;
import org.hibernate.boot.model.internal.BinderHelper;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Recipe recipe;

    // For now I used just "amount" but with a real application this is too generic and units of measure would need to be taken into account like grams or cups etc.
    @Column(nullable = false)
    private double amount;

    public Ingredient() {}

    public Ingredient(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

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
