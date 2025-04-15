import java.util.ArrayList;
import java.util.List;

public class ExecutionMetrics {
    // Classe interna para armazenar os dados de cada execução
    private static class ResultadoExecucao {
        int leitores;
        int escritores;
        long tempoMedio;

        ResultadoExecucao(int leitores, int escritores, long tempoMedio) {
            this.leitores = leitores;
            this.escritores = escritores;
            this.tempoMedio = tempoMedio;
        }
    }

    // Lista para armazenar todos os resultados de execução
    private final List<ResultadoExecucao> resultados;

    // Construtor
    public ExecutionMetrics() {
        resultados = new ArrayList<>();
    }

    // Método para registrar os tempos médios de cada execução
    public void registrarTempoExecucao(int leitores, int escritores, long tempoMedio) {
        resultados.add(new ResultadoExecucao(leitores, escritores, tempoMedio));
    }

    // Método para gerar o relatório
    public void gerarRelatorio() {
        System.out.println("\nRelatório de Execução:");
        for (ResultadoExecucao resultado : resultados) {
            System.out.printf("Leitores: %d, Escritores: %d, Tempo Médio: %dms%n", 
                              resultado.leitores, resultado.escritores, resultado.tempoMedio);
        }

        // Aqui você poderia adicionar código para gerar gráficos ou fazer outras análises.
    }
}