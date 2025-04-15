import java.util.Random;
public class Writer implements Runnable {
    private final Database database;

    public Writer(Database database){
        this.database = database;
    }

    @Override
    public void run(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            int posicaoRandom = random.nextInt(database.getTamanho()); //gera posição aleatória
            database.escrever(posicaoRandom, "MODIFICADO"); //escreve na posição
            System.out.println("Escritor " + Thread.currentThread().getId() + " escreveu na posição: " + posicaoRandom); //imprime a palavra escrita
        }
        //soneca 1ms
        try{
            Thread.sleep(1);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
