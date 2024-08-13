package com.tuareceita.tuareceita.domain.step;

import com.tuareceita.tuareceita.domain.recipe.Recipe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "steps")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer stepNumber;
    private String instruction;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Step (StepData data) {
        this.stepNumber = data.stepNumber();
        this.instruction = data.instruction();
    }
}
