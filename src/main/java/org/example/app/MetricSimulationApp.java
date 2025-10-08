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

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MetricSimulationApp {

    // As constantes de dados fixos ainda são úteis, mas vamos movê-las
    // para que a entrada manual seja o foco.

    private static final ClassAnalysisData HIGH_RISK_DATA = new ClassAnalysisData(
            12, Set.of("ServiceA", "ServiceB", "DAO", "Logger"), 4, 1, 10, List.of("Child1", "Child2", "Child3"), 450
    );
    private static final ClassAnalysisData LOW_RISK_DATA = new ClassAnalysisData(
            1, Set.of("Logger"), 1, 5, 0, List.of(), 50
    );

    public static void main(String[] args) {
        runMenu();
    }

    // ... (runMenu() permanece o mesmo) ...

    // O método runMenu() deve ser o mesmo que você já tem.
    private static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice;

        do {
            System.out.println("\n====================== MENU DE CÁLCULO DE MÉTRICAS ======================");
            System.out.println("Escolha a métrica que deseja calcular:");
            System.out.println("1. Complexidade Ciclomática (CC)");
            System.out.println("2. Acoplamento entre Objetos (CBO)");
            System.out.println("3. Falta de Coesão em Métodos (LCOM)");
            System.out.println("4. Profundidade da Árvore de Herança (DIT)");
            System.out.println("5. Weighted Methods per Class (WMC)");
            System.out.println("6. Response For a Class (RFC)");
            System.out.println("7. Coesão Rígida de Classe (TCC)");
            System.out.println("8. Número de Filhos (NOC)");
            System.out.println("9. Linhas de Código (CLOC)");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                menuChoice = scanner.nextInt();
                if (menuChoice > 0 && menuChoice <= 9) {
                    selectScenario(scanner, menuChoice);
                } else if (menuChoice == 0) {
                    System.out.println("Encerrando o simulador. Até mais!");
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
                menuChoice = -1;
            }
        } while (menuChoice != 0);

        scanner.close();
    }


    private static void selectScenario(Scanner scanner, int metricChoice) {
        System.out.println("\nEscolha o cenário para o cálculo:");
        System.out.println("A. Cenário de ALTO RISCO (Valores Fixos)");
        System.out.println("B. Cenário de BAIXO RISCO (Valores Fixos)");
        System.out.println("C. ENTRADA MANUAL de todos os dados de análise");
        System.out.print("Opção (A/B/C): ");

        String scenarioChoice = scanner.next().toUpperCase();
        ClassAnalysisData data = null;

        if (scenarioChoice.equals("A")) {
            data = HIGH_RISK_DATA;
            System.out.println("\n--- Cenário Selecionado: ALTO RISCO ---");
        } else if (scenarioChoice.equals("B")) {
            data = LOW_RISK_DATA;
            System.out.println("\n--- Cenário Selecionado: BAIXO RISCO ---");
        } else if (scenarioChoice.equals("C")) {
            // Nova chamada para entrada de dados manual
            data = getManualInputData(scanner);
            if (data == null) return; // Retorna se a entrada falhar
            System.out.println("\n--- Cenário Selecionado: MANUAL ---");
        } else {
            System.out.println("Opção de cenário inválida. Retornando ao menu principal.");
            return;
        }

        calculateAndReport(metricChoice, data);
    }

    // --- NOVO MÉTODO PARA ENTRADA MANUAL DE DADOS ---
    private static ClassAnalysisData getManualInputData(Scanner scanner) {
        System.out.println("\n================= ENTRADA MANUAL DE DADOS DE ANÁLISE =================");
        try {
            // CC, WMC: totalDecisionPoints
            System.out.print("1. Total de Pontos de Decisão (if/while/for): ");
            int totalDecisionPoints = scanner.nextInt();

            // CBO, RFC: coupledClasses
            scanner.nextLine(); // Consome a linha pendente
            System.out.print("2. Classes Acopladas (separadas por vírgula, ex: User, DB, Log): ");
            String coupledInput = scanner.nextLine();
            Set<String> coupledClasses = Arrays.stream(coupledInput.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());

            // DIT: inheritanceDepth
            System.out.print("3. Profundidade da Herança (DIT, 1 = herda de Object): ");
            int inheritanceDepth = scanner.nextInt();

            // LCOM, TCC: relatedMethodPairs
            System.out.print("4. Pares de Métodos RELACIONADOS (compartilham atributos): ");
            int relatedMethodPairs = scanner.nextInt();

            // LCOM, TCC: unrelatedMethodPairs
            System.out.print("5. Pares de Métodos NÃO RELACIONADOS: ");
            int unrelatedMethodPairs = scanner.nextInt();

            // NOC: directChildrenClasses
            scanner.nextLine();
            System.out.print("6. Classes Filhas Diretas (separadas por vírgula, ex: Funcionario, Gerente): ");
            String childrenInput = scanner.nextLine();
            List<String> directChildrenClasses = Arrays.stream(childrenInput.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            // CLOC: nonBlankLinesOfCode
            System.out.print("7. Linhas de Código Não Vazias (CLOC): ");
            int nonBlankLinesOfCode = scanner.nextInt();

            // Retorna o novo objeto ClassAnalysisData com os valores inseridos
            return new ClassAnalysisData(
                    totalDecisionPoints, coupledClasses, inheritanceDepth,
                    relatedMethodPairs, unrelatedMethodPairs,
                    directChildrenClasses, nonBlankLinesOfCode
            );

        } catch (java.util.InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Por favor, insira apenas números inteiros onde solicitado.");
            scanner.nextLine(); // Limpa o buffer
            return null;
        }
    }

    // ... (calculateAndReport() permanece o mesmo) ...

    private static void calculateAndReport(int metricChoice, ClassAnalysisData data) {
        AbstractMetricCalculator calculator = null;

        // O switch usa o polimorfismo para instanciar a classe filha correta
        switch (metricChoice) {
            case 1: calculator = new CyclomaticComplexityMetric(data); break;
            case 2: calculator = new CouplingBetweenObjectsMetric(data); break;
            case 3: calculator = new LCOMMetric(data); break;
            case 4: calculator = new DepthOfInheritanceMetric(data); break;
            case 5: calculator = new WeightedMethodsMetric(data); break;
            case 6: calculator = new ResponseForClassMetric(data); break;
            case 7: calculator = new TightClassCohesionMetric(data); break;
            case 8: calculator = new NumberOfChildrenMetric(data); break;
            case 9: calculator = new LinesOfCodeMetric(data); break;
        }

        if (calculator != null) {
            calculator.generateReport();

            // Lógica de alerta centralizada
            double result = calculator.calculateMetric();
            if (calculator instanceof LCOMMetric && result > 3.0) {
                System.out.println("  [ALERTA COESÃO]: LCOM muito alto (Baixa coesão)! Sugestão: Dividir a classe.");
            } else if (calculator instanceof CyclomaticComplexityMetric && result > 10.0) {
                System.out.println("  [ALERTA COMPLEXIDADE]: CC muito alta! Sugestão: Refatorar o método.");
            } else if (calculator instanceof CouplingBetweenObjectsMetric && result > 4.0) {
                System.out.println("  [ALERTA ACOPLAMENTO]: CBO alto! Sugestão: Reduzir dependências externas.");
            }
            System.out.println("=====================================================================");
        }
    }
}