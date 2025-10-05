package org.example.metrics.coupling;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class CouplingBetweenObjectsMetric extends AbstractMetricCalculator {

    public CouplingBetweenObjectsMetric(ClassAnalysisData data) {
        super(data);
    }

    /**
     * CÁLCULO CBO
     */

    @Override
    public double calculateMetric() {
        // O cálculo é o tamanho do Set que simula as classes usadas
        return analysisData.coupledClasses.size();
    }

}
