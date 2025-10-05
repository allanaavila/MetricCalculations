package org.example;

import org.example.data.ClassAnalysisData;

public abstract class AbstractMetricCalculator {
    protected ClassAnalysisData analysisData;

    public AbstractMetricCalculator(ClassAnalysisData analysisData) {
        if (analysisData == null) {
            throw new IllegalArgumentException("Analysis data cannot be null.");
        }
        this.analysisData = analysisData;
    }

    public abstract double calculateMetric();

    public String getMetricName() {
        // Ex: CyclomaticComplexityMetric -> Cyclomatic Complexity Metric
        return this.getClass().getSimpleName().replaceAll("Metric", " Metric").trim();
    }

    public void generateReport() {
        System.out.printf("--- REPORT: %s ---%n", getMetricName());
        System.out.printf("  Metric Result: %.2f%n", calculateMetric());
    }

}
