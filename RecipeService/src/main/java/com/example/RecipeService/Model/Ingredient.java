package com.example.RecipeService.Model;

public class Ingredient {
    private String name;

    // For now I used just "amount" but with a real application this is too generic and units of measure would need to be taken into account like grams or cups etc.
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
