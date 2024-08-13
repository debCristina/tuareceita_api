package com.tuareceita.tuareceita.domain.ingredient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    public IngredientDetailsData convertToDetails(Ingredient ingredient) {
        return new IngredientDetailsData(ingredient);
    }

    public List<IngredientDetailsData> convertToDetailsList(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::convertToDetails)
                .collect(Collectors.toList());
    }
}
