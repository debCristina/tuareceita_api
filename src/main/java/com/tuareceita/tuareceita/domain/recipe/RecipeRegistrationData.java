package com.tuareceita.tuareceita.domain.recipe;

import com.tuareceita.tuareceita.domain.ingredient.IngredientData;
import com.tuareceita.tuareceita.domain.step.StepData;
import jakarta.validation.constraints.*;

import java.util.List;

public record RecipeRegistrationData(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotEmpty(message = "Ingredients cannot be empty")
        List<IngredientData> ingredients,
        @NotEmpty(message = "Steps cannot be empty")
        List<StepData> steps,
        AverageCost averageCost,
        @NotNull
        Category category,
        Integer preparationTime) {
}
