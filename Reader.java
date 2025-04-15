import java.util.Random;
public class Reader implements Runnable{
    private final Database database; // base de dados compartilhada

    public Reader(Database database){
        this.database = database;
    }

    @Override
    public void run(){
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            int posicaoRandom = random.nextInt(database.getTamanho()); //gera posição aleatória
            String palavra = database.ler(posicaoRandom); //lê na posição
            System.out.println("Leitor " + Thread.currentThread().getId() + " leu: " + palavra); //imprime a palavra lida
        }
        //soneca 1ms
        try{
            Thread.sleep(1);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
}
