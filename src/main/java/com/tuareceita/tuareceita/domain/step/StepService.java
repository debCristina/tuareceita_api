package com.tuareceita.tuareceita.domain.step;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StepService {

    public StepDetailsData convertToDetails(Step step) {
        return new StepDetailsData(step);
    }

    public List<StepDetailsData> convertToDetailsList(List<Step> steps) {
        return steps.stream()
                .map(this::convertToDetails)
                .collect(Collectors.toList());
    }
}
