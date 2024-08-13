package com.tuareceita.tuareceita.controller;

import com.tuareceita.tuareceita.domain.recipe.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/recipes/")
    @Transactional
    public ResponseEntity registerRecipe (@RequestBody @Valid RecipeRegistrationData data, UriComponentsBuilder uribuilder) {
        var recipeDetails = recipeService.createRecipe(data);

        var uri = uribuilder.path("recipes/{id}").buildAndExpand(recipeDetails.id()).toUri();
        return ResponseEntity.created(uri).body(recipeDetails);
    }

    @GetMapping("/recipes/All")
    public ResponseEntity listAllRecipes() {
        List<RecipeDetailsData> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes/{recipeName}")
    public ResponseEntity searchByName(@PathVariable String recipeName) {
        List<RecipeDetailsData> recipes = recipeService.getRecipeByName(recipeName);

        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No recipes found with this name.");
        }

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes/category/{category}")
    public ResponseEntity getRecipesByCategory (@PathVariable String category) {
        try{
            Category recipeCategory = Category.valueOf(category.toUpperCase());
            List<RecipeDetailsData> recipes = recipeService.getRecipeByCategory(recipeCategory);
            return ResponseEntity.ok(recipes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid category: " +category);
        }
    }
}
