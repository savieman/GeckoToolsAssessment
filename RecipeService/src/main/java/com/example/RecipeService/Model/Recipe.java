package com.example.RecipeService.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Ingredient> ingredients = new ArrayList<>();
    @Column(nullable = false)
    private double cookTimeInMinutes;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    public Recipe(final Long id, final String description, final String name, final List<Ingredient> ingredients,
                  final double cookTimeInMinutes, final MealType mealType) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.ingredients = ingredients;
        this.cookTimeInMinutes = cookTimeInMinutes;
        this.mealType = mealType;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
