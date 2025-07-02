package com.example.RecipeService.Service;

import com.example.RecipeService.Model.Ingredient;
import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;
    private final List<Recipe> recipes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Ingredient breadIngredient = new Ingredient(2, "Bread");
        Ingredient cheeseIngredient = new Ingredient(1, "Cheese");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(breadIngredient);
        ingredients.add(cheeseIngredient);

        recipe = new Recipe("A grilled sandwich with molten cheese in between", "Grilled Cheese",
                ingredients, 5,MealType.BREAKFAST);
        recipes.add(recipe);
    }

    private void assertSearchResultIsValid(List<Recipe> resultRecipeList) {
        assertNotNull(resultRecipeList);
        assertEquals(1, resultRecipeList.size());
        assertEquals(recipe.getName(), resultRecipeList.get(0).getName());
        assertEquals(recipe.getIngredients().get(0).getName(),
                resultRecipeList.get(0).getIngredients().get(0).getName());
        assertEquals(recipe.getIngredients().get(0).getAmount(),
                resultRecipeList.get(0).getIngredients().get(0).getAmount());
        assertEquals(recipe.getIngredients().get(1).getName(),
                resultRecipeList.get(0).getIngredients().get(1).getName());
        assertEquals(recipe.getIngredients().get(1).getAmount(),
                resultRecipeList.get(0).getIngredients().get(1).getAmount());
        assertEquals(MealType.BREAKFAST, resultRecipeList.get(0).getMealType());
        assertEquals(recipe.getCookTimeInMinutes(), resultRecipeList.get(0).getCookTimeInMinutes());
    }

    @Test
    public void testSearchRecipesWhenNoParametersAreFilledIn() {
        when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> resultRecipeList = recipeService.searchRecipes("", "", null,
                null);

        assertSearchResultIsValid(resultRecipeList);
    }


    @Test
    public void testSearchRecipesWhenAllParametersAreFilledInCorrectly() {
        when(recipeRepository.queryRecipes("Grilled Cheese", "Bread", MealType.BREAKFAST, 5D))
                .thenReturn(recipes);

        List<Recipe> resultRecipeList = recipeService.searchRecipes("Grilled Cheese", "Bread",
                MealType.BREAKFAST, 5D);

        assertSearchResultIsValid(resultRecipeList);
    }

    private void assertGetRecipeResultIsValid(Recipe resultRecipe) {
        assertNotNull(resultRecipe);
        assertEquals(recipe.getName(), resultRecipe.getName());
        assertEquals(recipe.getIngredients().get(0).getName(),
                resultRecipe.getIngredients().get(0).getName());
        assertEquals(recipe.getIngredients().get(0).getAmount(),
                resultRecipe.getIngredients().get(0).getAmount());
        assertEquals(recipe.getIngredients().get(1).getName(),
                resultRecipe.getIngredients().get(1).getName());
        assertEquals(recipe.getIngredients().get(1).getAmount(),
                resultRecipe.getIngredients().get(1).getAmount());
        assertEquals(MealType.BREAKFAST, resultRecipe.getMealType());
        assertEquals(recipe.getCookTimeInMinutes(), resultRecipe.getCookTimeInMinutes());
    }

    @Test
    public void testGetRecipeWhenRecipeIsFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe resultRecipe = recipeService.getRecipe(1L);

        assertGetRecipeResultIsValid(resultRecipe);
    }

    @Test
    public void testGetRecipeWhenNoRecipeIsFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.getRecipe(1L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void testDeletedRecipeWhenRecipeIsFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe resultRecipe = recipeService.deleteRecipe(1L);
        assertGetRecipeResultIsValid(resultRecipe);
    }

    @Test
    public void testDeletedRecipeWhenNoRecipeIsFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.getRecipe(1L))
                .isInstanceOf(EntityNotFoundException.class);    }
}