public class ThreadManager {
    private final Database database;
    private final int numLeitores;
    private final int numEscritores;

    public ThreadManager(Database database, int numLeitores, int numEscritores){
        this.database = database;
        this.numLeitores = numLeitores;
        this.numEscritores = numEscritores;
    }

    public long executar(){
        Thread[] threads = new Thread[numLeitores + numEscritores];
        for(int i = 0; i < numLeitores; i++){
            threads[i] = new Thread(new Reader(database));
        }
        for(int i = 0; i < numEscritores; i++){
            threads[numLeitores + i] = new Thread(new Writer(database));
        }

        long tempoInicio = System.currentTimeMillis();

        for(Thread thread : threads){
            thread.start();
        }
        
        for(Thread thread : threads){
            try{
                thread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        long tempoFim = System.currentTimeMillis();
        return tempoFim - tempoInicio;
    }
}
