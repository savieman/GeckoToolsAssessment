package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(final @RequestParam(value = "name", required = false) String name,
                                   final @RequestParam(value = "ingredientName", required = false) String ingredientName,
                                   final @RequestParam(value = "mealType", required = false) MealType mealType,
                                   final @RequestParam(value = "cookTimeInMinutes", required = false) Double cookTimeInMinutes) {
        return recipeService.searchRecipes(name, ingredientName, mealType, cookTimeInMinutes);
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(final @PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return recipe;
    }

    @PostMapping("/recipes")
    public Recipe createRecipe(final @RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @DeleteMapping("/recipes/{id}")
    public Recipe deleteRecipe(final @PathVariable Long id) {
       return this.recipeService.deleteRecipe(id);
    }
}
