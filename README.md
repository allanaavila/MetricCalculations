# 📐 Software Metrics Calculator (Calculadora de Métricas de Software)

Este projeto é uma ferramenta de simulação desenvolvida em **Java Puro**, utilizando os princípios de **Herança** e **Polimorfismo**, 
para calcular e analisar métricas de qualidade de código orientado a objetos (OO), como Complexidade Ciclomática (CC), Coesão (LCOM/TCC) e Acoplamento (CBO/RFC).

## 💡 O Motivo do Projeto

O sucesso de longo prazo de um software depende diretamente de sua **Manutenibilidade**. Este projeto foi criado para:

1.  **Validar a Arquitetura OO:** Demonstrar o poder de uma **Classe Pai Abstrata** (`AbstractMetricCalculator`) para gerenciar múltiplas e complexas fórmulas de cálculo.
2.  **Educação e Análise de Risco:** Mostrar, de forma interativa, como as diferentes métricas (Coesão, Acoplamento, Complexidade) são calculadas e como seus resultados se traduzem em alertas de risco de refatoração para o Índice de Manutenção (OMI).
3.  **Simulação Flexível:** Oferecer uma calculadora dinâmica onde o usuário pode inserir valores brutos (simulando a saída de um parser) para testar qualquer cenário de código.

---

## 🛠️ Arquitetura e Padrões de Design

O projeto utiliza uma arquitetura limpa e extensível, baseada no padrão **Template Method** (via classes abstratas).

### Principais Componentes

| Componente | Função | Padrão |
| :--- | :--- | :--- |
| **`AbstractMetricCalculator`** | **Classe Pai Abstrata:** Define o contrato (`calculateMetric()`) e o estado comum para todas as métricas. | Template Method / Contrato |
| **`ClassAnalysisData`** | **Dados de Entrada:** Simula a saída de um parser de código, armazenando todos os valores brutos para o cálculo. | Objeto de Dados |
| **Classes Filhas** | **Implementação:** Cada classe (ex: `LCOMMetric`, `CyclomaticComplexityMetric`) estende o Pai e implementa a fórmula específica para **uma única** métrica. | Polimorfismo / Herança |

---

## 🚀 Como Usar e Testar o Simulador

O aplicativo é executado como um aplicativo de console interativo.

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior.

### Execução

Basta executar o método `main` na classe **`org.example.app.MetricSimulationApp`**.

### Menu Interativo e Cenários

O sistema apresenta um menu para escolher a métrica e o cenário de dados:

| Opção | Descrição | Destaque |
| :--- | :--- | :--- |
| **A. Alto Risco (Fixo)** | Simula um código com design ruim (valores que geram alertas). | **Risco Elevado** |
| **B. Baixo Risco (Fixo)** | Simula um código com design excelente (valores próximos do ideal). | **Qualidade Alta** |
| **C. Entrada Manual Inteligente** | O sistema pergunta **apenas** os dados brutos relevantes para a métrica escolhida (ex: se escolher DIT, só perguntará pela profundidade). | **Mais Flexível** |

---

## 📊 Métricas Suportadas e Detalhes

O simulador cobre as nove métricas cruciais para a manutenibilidade:

| Categoria | Métrica | Lógica Chave | Risco (OMI) |
| :--- | :--- | :--- | :--- |
| **Complexidade** | **CC** | $1 + \text{Pontos de Decisão}$ | Alto |
| **Coesão** | **LCOM** | $\text{Máximo}(0, \text{Não Relacionados} - \text{Relacionados})$ | Alto (se for alto) |
| **Acoplamento** | **CBO** | $\text{Contagem de Classes Acopladas}$ | Alto |
| **Herança** | **DIT** | $\text{Profundidade da Herança}$ | Moderado |
| **Tamanho** | **CLOC** | $\text{Linhas Não Vazias}$ | Moderado |

---

## 🤝 Contribuições

Contribuições, sugestões e relatórios de bugs são bem-vindos! Sinta-se à vontade para abrir uma *Issue* ou um *Pull Request* no projeto.
