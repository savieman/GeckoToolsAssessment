package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        List<Recipe> recipeList = recipeService.getALlRecipes();
        return recipeList;
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
