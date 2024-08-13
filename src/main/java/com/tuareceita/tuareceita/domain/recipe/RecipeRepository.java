package com.tuareceita.tuareceita.domain.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByNameContainingIgnoreCase(String nameRecipe);

    List<Recipe> findAllByCategory(Category recipeCategory);
}
