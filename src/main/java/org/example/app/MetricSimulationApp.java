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


// O método selectScenario() precisa ser atualizado assim:

    private static void selectScenario(Scanner scanner, int metricChoice) {
        System.out.println("\nEscolha o cenário para o cálculo:");
        System.out.println("A. Cenário de ALTO RISCO (Valores Fixos)");
        System.out.println("B. Cenário de BAIXO RISCO (Valores Fixos)");
        System.out.println("C. ENTRADA MANUAL (Apenas os dados necessários)"); // Texto atualizado
        System.out.print("Opção (A/B/C): ");

        String scenarioChoice = scanner.next().toUpperCase();
        ClassAnalysisData data = null;

        if (scenarioChoice.equals("A")) {
            data = HIGH_RISK_DATA;
            System.out.println("\n--- Cenário Selecionado: ALTO RISCO (Fixo) ---");
        } else if (scenarioChoice.equals("B")) {
            data = LOW_RISK_DATA;
            System.out.println("\n--- Cenário Selecionado: BAIXO RISCO (Fixo) ---");
        } else if (scenarioChoice.equals("C")) {
            // CHAMA O NOVO MÉTODO DE ENTRADA INTELIGENTE
            data = getRelevantManualInputData(scanner, metricChoice);
            if (data == null) return;
            System.out.println("\n--- Cenário Selecionado: MANUAL DINÂMICO ---");
        } else {
            System.out.println("Opção de cenário inválida. Retornando ao menu principal.");
            return;
        }
        calculateAndReport(metricChoice, data);
    }


 // O método getManualInputData() será substituído por este:
    private static ClassAnalysisData getRelevantManualInputData(Scanner scanner, int metricChoice) {
        System.out.println("\n================= ENTRADA MANUAL DE DADOS =================");

        int cc = 0, dit = 1, rel = 10, unrel = 1, cloc = 50;
        Set<String> coupled = Set.of();
        List<String> children = List.of();

        if (scanner.hasNextLine()) scanner.nextLine();

        try {
            System.out.println("--- Insira apenas os dados necessários para o cálculo: ---\n");

            // --- 1. Complexidade Ciclomática (CC) e WMC ---
            if (metricChoice == 1 || metricChoice == 5) {
                System.out.print("Pontos de Decisão (if, for, while): ");
                cc = scanner.nextInt();
            }

            // --- 2. Acoplamento (CBO) e RFC ---
            if (metricChoice == 2 || metricChoice == 6) {
                System.out.print("Nomes de Classes Acopladas (separadas por vírgula): ");
                String coupledInput = scanner.nextLine(); // Usar nextLine() para ler toda a linha
                if (coupledInput.isEmpty()) coupledInput = scanner.nextLine(); // Às vezes precisa de uma segunda leitura

                coupled = Arrays.stream(coupledInput.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toSet());
            }

            // --- 3. Coesão (LCOM) e TCC ---
            if (metricChoice == 3 || metricChoice == 7) {
                System.out.print("Pares de Métodos RELACIONADOS (Q): ");
                rel = scanner.nextInt();
                System.out.print("Pares de Métodos NÃO RELACIONADOS (P): ");
                unrel = scanner.nextInt();
            }

            // --- 4. Herança (DIT) ---
            if (metricChoice == 4) {
                System.out.print("Profundidade da Herança (DIT, 1 = Object): ");
                dit = scanner.nextInt();
            }

            // --- 5. Herança (NOC) ---
            if (metricChoice == 8) {
                System.out.print("Nomes de Classes Filhas Diretas (separadas por vírgula): ");
                String childrenInput = scanner.nextLine();
                if (childrenInput.isEmpty()) childrenInput = scanner.nextLine();

                children = Arrays.stream(childrenInput.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }

            // --- 6. Tamanho (CLOC) ---
            if (metricChoice == 9) {
                System.out.print("Linhas de Código Não Vazias (CLOC): ");
                cloc = scanner.nextInt();
            }

            // Retorna o novo objeto ClassAnalysisData com os valores inseridos
            return new ClassAnalysisData(
                    cc, coupled, dit,
                    rel, unrel, children,
                    cloc
            );

        } catch (java.util.InputMismatchException e) {
            System.err.println("\nErro: Entrada inválida. Por favor, insira um número inteiro válido.");
            scanner.nextLine();
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