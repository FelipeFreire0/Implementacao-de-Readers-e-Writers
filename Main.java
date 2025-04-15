public class Main {
    public static void main(String[] args) {
        // Criação da base de dados compartilhada
        Database database = new Database();
        database.carregarBaseDeDados("bd.txt");

        // Definir o número de execuções para fazer a média
        int numExecucoes = 10; // Pode aumentar para ter uma média mais precisa (ex: 50)

        // Definindo a quantidade específica de leitores e escritores
        int numLeitores = 50;
        int numEscritores = 50;

        System.out.println("\nExecutando com " + numLeitores + " leitores e " + numEscritores + " escritores.");

        long tempoTotal = 0;

        // Executa a combinação específica de 50 leitores e 50 escritores
        for (int execucao = 0; execucao < numExecucoes; execucao++) {
            ThreadManager manager = new ThreadManager(database, numLeitores, numEscritores);
            tempoTotal += manager.executar();
        }

        // Calcula e exibe o tempo médio de execução para 50 leitores e 50 escritores
        long tempoMedio = tempoTotal / numExecucoes;
        System.out.println("Tempo médio: " + tempoMedio + "ms");

        // Registra o tempo médio na classe de métricas
        ExecutionMetrics metrics = new ExecutionMetrics();
        metrics.registrarTempoExecucao(numLeitores, numEscritores, tempoMedio);

        // Gera o relatório com todos os dados coletados
        metrics.gerarRelatorio();
    }
}
