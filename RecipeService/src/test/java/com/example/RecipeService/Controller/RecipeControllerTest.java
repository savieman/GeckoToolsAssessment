package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.Ingredient;
import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.Service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
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
                ingredients, 5, MealType.BREAKFAST);
        recipes.add(recipe);
    }

    @Test
    void testGetRecipes() throws Exception {
        when(recipeService.searchRecipes("Grilled Cheese", "Bread", MealType.BREAKFAST, 5D))
                .thenReturn(recipes);

        mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .param("name", "Grilled Cheese")
                        .param("ingredientName", "Bread")
                        .param("mealType", "BREAKFAST")
                        .param("cookTimeInMinutes", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(recipe.getName())))
                .andExpect(jsonPath("$[0].description", is(recipe.getDescription())))
                .andExpect(jsonPath("$[0].cookTimeInMinutes", is(recipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$[0].mealType", is(recipe.getMealType().toString())))
                .andExpect(jsonPath("$[0].ingredients[0].name", is(recipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$[0].ingredients[1].name", is(recipe.getIngredients().get(1).getName())));
    }

    @Test
    void testGetRecipeWhenRecipeIsFound() throws Exception {
        when(recipeService.getRecipe(1L)).thenReturn(recipe);

        mvc.perform(MockMvcRequestBuilders.get("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recipe.getName())))
                .andExpect(jsonPath("$.description", is(recipe.getDescription())))
                .andExpect(jsonPath("$.cookTimeInMinutes", is(recipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$.mealType", is(recipe.getMealType().toString())))
                .andExpect(jsonPath("$.ingredients[0].name", is(recipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$.ingredients[1].name", is(recipe.getIngredients().get(1).getName())));

    }

    @Test
    void testGetRecipeWhenNoRecipeIsFound() throws Exception {
        when(recipeService.getRecipe(1L)).thenThrow(EntityNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateRecipe() throws Exception {
        when(recipeService.createRecipe(ArgumentMatchers.any(Recipe.class))).thenReturn(recipe);


        mvc.perform(MockMvcRequestBuilders.post("/recipes")
                        .content("""
                          {
                                            "description": "A grilled sandwich with molten cheese in between",
                                            "name": "Grilled Cheese",
                                            "ingredients": [
                                              {
                                                "name": "cheese",
                                                "amount": 1
                                              },
                                              {
                                                "name": "bread",
                                                "amount": 2
                                              }
                                            ],
                                            "cookTimeInMinutes": 5,
                                            "mealType": "BREAKFAST"
                                          }
                        """
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recipe.getName())))
                .andExpect(jsonPath("$.description", is(recipe.getDescription())))
                .andExpect(jsonPath("$.cookTimeInMinutes", is(recipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$.mealType", is(recipe.getMealType().toString())))
                .andExpect(jsonPath("$.ingredients[0].name", is(recipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$.ingredients[1].name", is(recipe.getIngredients().get(1).getName())));

    }

    @Test
    void testCreateRecipeWhenBodyIsInvalid() throws Exception {
        when(recipeService.createRecipe(ArgumentMatchers.any(Recipe.class))).thenReturn(recipe);


        mvc.perform(MockMvcRequestBuilders.post("/recipes")
                        .content("""
                          {
                            "description": "A grilled sandwich with molten cheese in between",
                            "name": "Grilled Cheese",
                                            
                        """
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testDeleteRecipeWhenRecipeIsFound() throws Exception {
        when(recipeService.deleteRecipe(1L)).thenReturn(recipe);

        mvc.perform(MockMvcRequestBuilders.delete("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recipe.getName())))
                .andExpect(jsonPath("$.description", is(recipe.getDescription())))
                .andExpect(jsonPath("$.cookTimeInMinutes", is(recipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$.mealType", is(recipe.getMealType().toString())))
                .andExpect(jsonPath("$.ingredients[0].name", is(recipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$.ingredients[0].amount", is(recipe.getIngredients().get(0).getAmount())))
                .andExpect(jsonPath("$.ingredients[1].name", is(recipe.getIngredients().get(1).getName())))
                .andExpect(jsonPath("$.ingredients[1].amount", is(recipe.getIngredients().get(1).getAmount())));
    }

    @Test
    void testDeleteRecipeWhenRecipeIsNotFound() throws Exception {
        when(recipeService.deleteRecipe(1L)).thenThrow(EntityNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.delete("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}