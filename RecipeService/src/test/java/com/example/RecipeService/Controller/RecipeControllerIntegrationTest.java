package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.Ingredient;
import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.RecipeServiceApplication;
import com.example.RecipeService.Service.RecipeRepository;
import com.example.RecipeService.Service.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RecipeServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class RecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    List<Ingredient> ingredients = new ArrayList<>();

    private Recipe recipe;

    private final List<Recipe> recipes = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Ingredient breadIngredient = new Ingredient(2, "Bread");
        Ingredient cheeseIngredient = new Ingredient(1, "Cheese");

        recipe = new Recipe( "A grilled sandwich with molten cheese in between", "Grilled Cheese",
                ingredients, 5, MealType.BREAKFAST);

        ingredients.add(cheeseIngredient);
        ingredients.add(breadIngredient);

        recipes.add(recipe);
    }

    Recipe fillDatabase() {
        return recipeRepository.save(recipe);
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteAll();
    }

    @Test
    void testGeRecipesWhenNoRecipesAreAvailableWithNoRequestParameters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGeRecipesWhenNoRecipesAreFoundWithRequestParameters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .param("name", "Grilled Cheese")
                        .param("ingredientName", "Bread")
                        .param("mealType", "BREAKFAST")
                        .param("cookTimeInMinutes", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRecipesWhenRecipesAreAvailableWithQueryParameters() throws Exception {
        Recipe addedRecipe = fillDatabase();
        mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .param("name", "Grilled Cheese")
                        .param("ingredientName", "Bread")
                        .param("mealType", "BREAKFAST")
                        .param("cookTimeInMinutes", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(addedRecipe.getName())))
                .andExpect(jsonPath("$[0].description", is(addedRecipe.getDescription())))
                .andExpect(jsonPath("$[0].cookTimeInMinutes", is(addedRecipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$[0].mealType", is(addedRecipe.getMealType().toString())))
                .andExpect(jsonPath("$[0].ingredients[0].name", is(addedRecipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$[0].ingredients[1].name", is(addedRecipe.getIngredients().get(1).getName())));
    }

    @Test
    void testGetRecipesWhenRecipesAreAvailableWithoutQueryParameters() throws Exception {
        Recipe addedRecipe = fillDatabase();
        mvc.perform(MockMvcRequestBuilders.get("/recipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(addedRecipe.getName())))
                .andExpect(jsonPath("$[0].description", is(addedRecipe.getDescription())))
                .andExpect(jsonPath("$[0].cookTimeInMinutes", is(addedRecipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$[0].mealType", is(addedRecipe.getMealType().toString())))
                .andExpect(jsonPath("$[0].ingredients[0].name", is(addedRecipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$[0].ingredients[1].name", is(addedRecipe.getIngredients().get(1).getName())));
    }

    @Test
    void testGetRecipeWhenRecipeIsFound() throws Exception {
        Recipe addedRecipe = fillDatabase();
        mvc.perform(MockMvcRequestBuilders.get("/recipes/" + addedRecipe.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(addedRecipe.getName())))
                .andExpect(jsonPath("$.description", is(addedRecipe.getDescription())))
                .andExpect(jsonPath("$.cookTimeInMinutes", is(addedRecipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$.mealType", is(addedRecipe.getMealType().toString())))
                .andExpect(jsonPath("$.ingredients[0].name", is(addedRecipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$.ingredients[1].name", is(addedRecipe.getIngredients().get(1).getName())));

    }

    @Test
    void testGetRecipeWhenNoRecipeIsFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateRecipe() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/recipes")
                        .content("""
                          {
                                            "description": "A grilled sandwich with molten cheese in between",
                                            "name": "Grilled Cheese",
                                            "ingredients": [
                                              {
                                                "name": "Cheese",
                                                "amount": 1
                                              },
                                              {
                                                "name": "Bread",
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
        Recipe addedRecipe = fillDatabase();
        mvc.perform(MockMvcRequestBuilders.delete("/recipes/" + addedRecipe.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(addedRecipe.getName())))
                .andExpect(jsonPath("$.description", is(addedRecipe.getDescription())))
                .andExpect(jsonPath("$.cookTimeInMinutes", is(addedRecipe.getCookTimeInMinutes())))
                .andExpect(jsonPath("$.mealType", is(addedRecipe.getMealType().toString())))
                .andExpect(jsonPath("$.ingredients[0].name", is(addedRecipe.getIngredients().get(0).getName())))
                .andExpect(jsonPath("$.ingredients[0].amount", is(addedRecipe.getIngredients().get(0).getAmount())))
                .andExpect(jsonPath("$.ingredients[1].name", is(addedRecipe.getIngredients().get(1).getName())))
                .andExpect(jsonPath("$.ingredients[1].amount", is(addedRecipe.getIngredients().get(1).getAmount())));
    }

    @Test
    void testDeleteRecipeWhenRecipeIsNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}