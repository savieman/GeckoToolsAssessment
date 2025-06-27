package com.example.RecipeService.Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private long id;
    private String description;
    private String name;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private double cookTimeInMinutes;
    private MealType mealType;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(final List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public double getCookTimeInMinutes() {
        return cookTimeInMinutes;
    }

    public void setCookTimeInMinutes(final double cookTimeInMinutes) {
        this.cookTimeInMinutes = cookTimeInMinutes;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(final MealType mealType) {
        this.mealType = mealType;
    }

}
