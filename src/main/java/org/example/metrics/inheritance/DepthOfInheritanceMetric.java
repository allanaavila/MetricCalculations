package org.example.metrics.inheritance;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class DepthOfInheritanceMetric extends AbstractMetricCalculator {

    public DepthOfInheritanceMetric(ClassAnalysisData data) {
        super(data);
    }

    @Override
    public double calculateMetric() {
        // O cálculo é o próprio valor da profundidade
        return analysisData.inheritanceDepth;
    }
}
