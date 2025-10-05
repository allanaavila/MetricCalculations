package org.example.metrics.complexity;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class CyclomaticComplexityMetric extends AbstractMetricCalculator {

    public CyclomaticComplexityMetric(ClassAnalysisData data) {
        super(data);
    }

    @Override
    public double calculateMetric() {
        // Fórmula CC = 1 + totalDecisionPoints
        return 1.0 + analysisData.totalDecisionPoints;
    }
}
