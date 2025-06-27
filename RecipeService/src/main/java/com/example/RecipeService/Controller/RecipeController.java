package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.Recipe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    @GetMapping("/recipes/")
    public List<Recipe> getRecipes() {
        return null;
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(final @PathVariable String id) {
        return null;
    }

    @PostMapping("/recipes/")
    public List<Recipe> createRecipe(final @RequestBody Recipe recipe) {
        return null;
    }

    @DeleteMapping("/recipes/{id}")
    public Recipe deleteRecipe(final @PathVariable String id) {
        return null;
    }
}
