package com.tuareceita.tuareceita.domain.recipe;

import com.tuareceita.tuareceita.domain.ingredient.IngredientService;
import com.tuareceita.tuareceita.domain.step.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    StepService stepService;

    public RecipeDetailsData createRecipe (RecipeRegistrationData data) {
        var recipe = new Recipe(data);
        repository.save(recipe);

        return new RecipeDetailsData(recipe);
    }

    public List<RecipeDetailsData> getAllRecipes() {
        return converteDados(repository.findAll());
    }

    public List<RecipeDetailsData> getRecipeByName(String recipeName) {
        List<Recipe> recipes = repository.findByNameContainingIgnoreCase(recipeName);

        if (recipes.isEmpty()) {
            return Collections.emptyList(); // Nenhuma receita encontrada
        }

        return converteDados(recipes);
    }

    public List<RecipeDetailsData> getRecipeByCategory (Category recipeCategory){
        return converteDados(repository.findAllByCategory(recipeCategory));
    }


    private List<RecipeDetailsData> converteDados(List<Recipe> recipes){
        return recipes.stream()
                .map(r -> new RecipeDetailsData(r.getId(), r.getName(), r.getDescription(), ingredientService.convertToDetailsList(r.getIngredients()),
                stepService.convertToDetailsList(r.getSteps()), r.getAverageCost(), r.getCategory(),r.getPreparationTime(), r.getCreatedAt(), r.getUpdatedAt()))
                .collect(Collectors.toList());
    }
}
