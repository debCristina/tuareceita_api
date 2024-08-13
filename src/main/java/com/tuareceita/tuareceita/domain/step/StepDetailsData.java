package com.tuareceita.tuareceita.domain.step;

public record StepDetailsData (Long id, Integer stepNumber, String instruction) {

    public StepDetailsData(Step step) {
        this(step.getId(), step.getStepNumber(), step.getInstruction());
    }
}
