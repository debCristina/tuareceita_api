package com.tuareceita.tuareceita.domain.recipe;

import com.tuareceita.tuareceita.domain.ingredient.Ingredient;
import com.tuareceita.tuareceita.domain.step.Step;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Step> steps = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private AverageCost averageCost;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer preparationTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Recipe(RecipeRegistrationData data) {
        this.name = data.name();
        this.description = data.description();
        this.averageCost = data.averageCost();
        this.category = data.category();
        this.preparationTime = data.preparationTime();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.ingredients = data.ingredients().stream()
                .map(ingredientData -> {
                    Ingredient ingredient = new Ingredient(ingredientData);
                    ingredient.setRecipe(this);
                    return ingredient;
                })
                .collect(Collectors.toList());

        this.steps = data.steps().stream()
                .map(stepData -> {
                    Step step = new Step(stepData);
                    step.setRecipe(this);
                    return step;
                })
                .collect(Collectors.toList());
    }
}
