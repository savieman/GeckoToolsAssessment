package com.example.RecipeService.Service;

import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;


    public List<Recipe> getALlRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipes(final String name,
                                      final String ingrdientName,
                                      final MealType mealType,
                                      final Double cookTimeInMinutes) {
        List<Recipe> recipes = recipeRepository.queryRecipes(name,
                ingrdientName,
                mealType,
                cookTimeInMinutes);
        return recipes;
    }

    public Recipe getRecipe(final Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new EntityNotFoundException();
    }

    public List<Recipe> getRecipesByRecipeName(final String recipeName) {
//        Optional<List<Recipe>> recipes = recipeRepository.findRecipesByName(recipeName);
//        if (recipes.isPresent()) {
//            return recipes.get();
//        }
        throw new EntityNotFoundException();
    }


    public Recipe createRecipe(final Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    public Recipe deleteRecipe(final Long id) {
        Recipe recipe = this.getRecipe(id);
        recipeRepository.delete(recipe);
        return recipe;
    }
}
