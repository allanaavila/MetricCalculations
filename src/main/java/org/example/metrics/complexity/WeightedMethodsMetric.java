package org.example.metrics.complexity;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class WeightedMethodsMetric extends AbstractMetricCalculator {

    public WeightedMethodsMetric(ClassAnalysisData data) {
        super(data);
    }

    @Override
    public double calculateMetric() {
        // No mundo real, seria a soma das CCs de cada método.
        // Aqui, simulamos que a soma é proporcional aos pontos de decisão.
        return 5.0 + analysisData.totalDecisionPoints;
    }
}
