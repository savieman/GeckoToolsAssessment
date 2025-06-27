package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.Service.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
//
//    RecipeController(RecipeRepository repo) {
//        this.recipeRepository = repo;
//    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList;
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(final @PathVariable String id) {
        return null;
    }

    @PostMapping("/recipes")
    public Recipe createRecipe(final @RequestBody Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @DeleteMapping("/recipes/{id}")
    public Recipe deleteRecipe(final @PathVariable Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return recipe.get();
        } else {
            return null;
        }
    }
}
