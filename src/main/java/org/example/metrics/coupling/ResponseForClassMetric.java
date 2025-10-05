package org.example.metrics.coupling;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;

public class ResponseForClassMetric extends AbstractMetricCalculator {
    public ResponseForClassMetric(ClassAnalysisData data) {
        super(data);
    }

 /**
    * CÁLCULO RFC
 */

    @Override
    public double calculateMetric() {
        // Simulação: 5 métodos locais base + o número de classes que ela chama (acoplamento).
        return 5.0 + analysisData.coupledClasses.size();
    }
}
