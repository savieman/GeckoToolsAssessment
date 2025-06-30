package com.example.RecipeService.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Ingredient> ingredientList = new ArrayList<>();
    @Column(nullable = false)
    private double cookTimeInMinutes;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealType mealType;

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
