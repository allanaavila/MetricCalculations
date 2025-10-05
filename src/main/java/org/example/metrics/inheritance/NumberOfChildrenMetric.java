package org.example.metrics.inheritance;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class NumberOfChildrenMetric extends AbstractMetricCalculator {

    public NumberOfChildrenMetric(ClassAnalysisData data) {
        super(data);
    }

    /**
     * CÁLCULO NOC
     */


    @Override
    public double calculateMetric() {
        return analysisData.directChildrenClasses.size();
    }
}
