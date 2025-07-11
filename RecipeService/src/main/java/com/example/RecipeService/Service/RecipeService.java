package com.example.RecipeService.Service;

import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> searchRecipes(final String name,
                                      final String ingredientName,
                                      final MealType mealType,
                                      final Double cookTimeInMinutes) {
        if (StringUtils.isBlank(name) && StringUtils.isBlank(ingredientName) && mealType == null
                && cookTimeInMinutes == null) {
            return this.recipeRepository.findAll();
        }

        return recipeRepository.queryRecipes(name,
                ingredientName,
                mealType,
                cookTimeInMinutes);
    }

    public Recipe getRecipe(final Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        }
        throw new EntityNotFoundException();
    }

    public Recipe createRecipe(final Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe deleteRecipe(final Long id) {
        Recipe recipe = this.getRecipe(id);
        recipeRepository.delete(recipe);
        return recipe;
    }
}
