package org.example.data;

import java.util.List;
import java.util.Set;

public class ClassAnalysisData {

    // Para Complexidade Ciclomática (CC) e WMC
    public final int totalDecisionPoints;

    // Para Acoplamento (CBO)
    public final Set<String> coupledClasses;

    // Para Herança (DIT)
    public final int inheritanceDepth;

    // Para Coesão (Exemplo para LCOM: Pares de métodos relacionados vs não relacionados)
    public final int relatedMethodPairs;
    public final int unrelatedMethodPairs;

    // Para Herança (NOC)
    public final List<String> directChildrenClasses;

    // Para Tamanho (CLOC)
    public final int nonBlankLinesOfCode;

    public ClassAnalysisData(int totalDecisionPoints, Set<String> coupledClasses,
                             int inheritanceDepth, int relatedMethodPairs,
                             int unrelatedMethodPairs, List<String> directChildrenClasses,
                             int nonBlankLinesOfCode) {
        this.totalDecisionPoints = totalDecisionPoints;
        this.coupledClasses = coupledClasses;
        this.inheritanceDepth = inheritanceDepth;
        this.relatedMethodPairs = relatedMethodPairs;
        this.unrelatedMethodPairs = unrelatedMethodPairs;
        this.directChildrenClasses = directChildrenClasses;
        this.nonBlankLinesOfCode = nonBlankLinesOfCode;
    }

}
