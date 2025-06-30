package com.example.RecipeService.Service;

import com.example.RecipeService.Model.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<List<Recipe>> findRecipesByName(final String name);
}
