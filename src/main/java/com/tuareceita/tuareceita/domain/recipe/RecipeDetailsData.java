package com.tuareceita.tuareceita.domain.recipe;

import com.tuareceita.tuareceita.domain.ingredient.IngredientData;
import com.tuareceita.tuareceita.domain.ingredient.IngredientDetailsData;
import com.tuareceita.tuareceita.domain.step.StepData;
import com.tuareceita.tuareceita.domain.step.StepDetailsData;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record RecipeDetailsData(
        Long id,
        String name,
        String description,
        List<IngredientDetailsData> ingredients,
        List<StepDetailsData> steps,
        AverageCost averageCost,
        Category category,
        Integer preparationTime,
        LocalDateTime createdAt,
        LocalDateTime updatedTime){

    public RecipeDetailsData(Recipe recipe) {
        this(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getIngredients().stream().map(IngredientDetailsData::new).collect(Collectors.toList()),
                recipe.getSteps().stream().map(StepDetailsData::new).collect(Collectors.toList()),
                recipe.getAverageCost(),
                recipe.getCategory(),
                recipe.getPreparationTime(),
                recipe.getCreatedAt(),
                recipe.getUpdatedAt());
    }
}
