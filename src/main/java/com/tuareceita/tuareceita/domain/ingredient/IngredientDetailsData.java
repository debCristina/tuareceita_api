package com.tuareceita.tuareceita.domain.ingredient;

public record IngredientDetailsData (Long id,
                                     String description){
    public IngredientDetailsData(Ingredient ingredient) {
        this(ingredient.getId(), ingredient.getDescription());
    }
}
