package com.example.RecipeService.Service;

import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("""
        SELECT r FROM Recipe r
        JOIN r.ingredients i
        WHERE i.name = :ingredientName
          OR r.name = :name
          OR r.cookTimeInMinutes = :cookTimeInMinutes
          OR r.mealType = :mealType
    """)
    List<Recipe> queryRecipes(
            @Param("name") String name,
            @Param("ingredientName") String ingredientName,
            @Param("mealType") MealType mealType,
            @Param("cookTimeInMinutes") Double cookTimeInMinutes
    );
//    Optional<List<Recipe>> findRecipes (final String name,
//    final String ingrdientName,
//    final String mealType,
//    final String cookTimeInMintes) {
}
