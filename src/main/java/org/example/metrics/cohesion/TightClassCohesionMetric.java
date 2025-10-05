package org.example.metrics.cohesion;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class TightClassCohesionMetric extends AbstractMetricCalculator {

    public TightClassCohesionMetric(ClassAnalysisData data) {
        super(data);
    }

    /**
     * CÁLCULO TCC
     */

    @Override
    public double calculateMetric() {
        int P = analysisData.relatedMethodPairs;
        int totalPairs = P + analysisData.unrelatedMethodPairs;

        if (totalPairs == 0) return 0.0;

        // Retorna um valor entre 0 e 1, onde 1 é coesão perfeita.
        return (double) P / totalPairs;
    }


}
