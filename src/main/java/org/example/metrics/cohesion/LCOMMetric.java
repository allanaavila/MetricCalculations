package org.example.metrics.cohesion;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class LCOMMetric extends AbstractMetricCalculator {

    public LCOMMetric(ClassAnalysisData data) {
        super(data);
    }

    /**
     * CÁLCULO LCOM
     */

    @Override
    public double calculateMetric() {
        int P = analysisData.unrelatedMethodPairs;
        int Q = analysisData.relatedMethodPairs;

        // A lógica de cálculo básica do LCOM:
        return Math.max(0, P - Q);
    }
}
