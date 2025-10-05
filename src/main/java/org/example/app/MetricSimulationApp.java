package org.example.app;

import org.example.AbstractMetricCalculator;
import org.example.data.ClassAnalysisData;
import org.example.metrics.cohesion.LCOMMetric;
import org.example.metrics.cohesion.TightClassCohesionMetric;
import org.example.metrics.complexity.CyclomaticComplexityMetric;
import org.example.metrics.complexity.WeightedMethodsMetric;
import org.example.metrics.coupling.CouplingBetweenObjectsMetric;
import org.example.metrics.coupling.ResponseForClassMetric;
import org.example.metrics.inheritance.DepthOfInheritanceMetric;
import org.example.metrics.inheritance.NumberOfChildrenMetric;
import org.example.metrics.size.LinesOfCodeMetric;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MetricSimulationApp {
    public static void main(String[] args) {
        ClassAnalysisData highRiskData = new ClassAnalysisData(
                12, // totalDecisionPoints -> CC e WMC altos
                Set.of("ServiceA", "ServiceB", "DAO", "Logger"), // 4 classes acopladas -> CBO e RFC altos
                4, // inheritanceDepth -> DIT alto
                1, // relatedMethodPairs (Baixa coesão)
                10, // unrelatedMethodPairs (Baixa coesão)
                List.of("Child1", "Child2", "Child3"), // 3 filhos -> NOC alto
                450 // nonBlankLinesOfCode -> CLOC alto
        );

        System.out.println("=====================================================================");
        System.out.println(">>> SIMULAÇÃO DO COMPONENTE DE ALTO RISCO (OMI ALTO) <<<");
        System.out.println("=====================================================================");


        List<AbstractMetricCalculator> highRiskCalculators = new ArrayList<>();

        // Complexidade
        highRiskCalculators.add(new CyclomaticComplexityMetric(highRiskData));
        highRiskCalculators.add(new WeightedMethodsMetric(highRiskData));

        // Acoplamento
        highRiskCalculators.add(new CouplingBetweenObjectsMetric(highRiskData));
        highRiskCalculators.add(new ResponseForClassMetric(highRiskData));

        // Coesão
        highRiskCalculators.add(new LCOMMetric(highRiskData));
        highRiskCalculators.add(new TightClassCohesionMetric(highRiskData));

        // Herança e Tamanho
        highRiskCalculators.add(new DepthOfInheritanceMetric(highRiskData));
        highRiskCalculators.add(new NumberOfChildrenMetric(highRiskData));
        highRiskCalculators.add(new LinesOfCodeMetric(highRiskData));


        // --- 3. Execução dos Relatórios ---
        for (AbstractMetricCalculator calculator : highRiskCalculators) {
            calculator.generateReport();
            // Lógica de feedback:
            if (calculator instanceof LCOMMetric && calculator.calculateMetric() > 3.0) {
                System.out.println("  [ALERTA COESÃO]: LCOM muito alto (Baixa coesão)!");
            } else if (calculator instanceof CyclomaticComplexityMetric && calculator.calculateMetric() > 10.0) {
                System.out.println("  [ALERTA COMPLEXIDADE]: CC muito alta (Alto risco de erro)!");
            }
            System.out.println("------------------------------------");
        }
        System.out.println("\n--- Fim da Simulação de Alto Risco ---\n");
    }
}
