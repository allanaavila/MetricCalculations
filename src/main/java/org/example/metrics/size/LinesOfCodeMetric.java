package org.example.metrics.size;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class LinesOfCodeMetric extends AbstractMetricCalculator {

    public LinesOfCodeMetric(ClassAnalysisData data) {
        super(data);
    }

    /**
     * CÁLCULO CLOC
     */

    @Override
    public double calculateMetric() {
        // O cálculo é o próprio valor da contagem de linhas não vazias
        return analysisData.nonBlankLinesOfCode;
    }
}
