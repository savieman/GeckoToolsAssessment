package com.example.RecipeService.Controller;

import com.example.RecipeService.Model.MealType;
import com.example.RecipeService.Model.Recipe;
import com.example.RecipeService.Service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Operation(summary = "Gets a list recipes",
            description = "Gets a list of recipes. It's also possible to query through them with specific parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Recipe.class)) })
    })
    @GetMapping("/recipes")
    public List<Recipe> getRecipes(final @RequestParam(value = "name", required = false) String name,
                                   final @RequestParam(value = "ingredientName", required = false) String ingredientName,
                                   final @RequestParam(value = "mealType", required = false) MealType mealType,
                                   final @RequestParam(value = "cookTimeInMinutes", required = false) Double cookTimeInMinutes) {
        return recipeService.searchRecipes(name, ingredientName, mealType, cookTimeInMinutes);
    }

    @Operation(summary = "Gets a a specific recipe by id",
            description = "Gets a specific recipe by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Recipe.class)) }),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ExceptionController.ErrorResponse.class))})
    })
    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(final @PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return recipe;
    }

    @Operation(summary = "Create a recipe",
            description = "Create a recipe with ingredients included")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Recipe.class)) }),
            @ApiResponse(responseCode = "400", description = "Recipe object given is not correct", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ExceptionController.ErrorResponse.class))})
    })
    @PostMapping("/recipes")
    public Recipe createRecipe(final @RequestBody @Valid Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @Operation(summary = "Delete a recipe",
            description = "Delete a recipe by giving its id as a path variable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Recipe.class)) }),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = ExceptionController.ErrorResponse.class))})
    })
    @DeleteMapping("/recipes/{id}")
    public Recipe deleteRecipe(final @PathVariable Long id) {
       return this.recipeService.deleteRecipe(id);
    }
}
