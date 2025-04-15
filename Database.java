import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Database {
    private List<String> baseDeDados; // Armazena palavras
    private final ReentrantReadWriteLock lock; // Controla o acesso

    public Database(){
        this.baseDeDados = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    //Método para carregar o arquivo na base de dados
    public void carregarBaseDeDados(String nomeArquivo){
        try(BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))){
            String linha;
            while((linha = reader.readLine()) != null){
                baseDeDados.add(linha);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Método para ler um valor na base de dados
    public String ler(int posicao){
        lock.readLock().lock(); // bloqueia para ler
        try{
            if(posicao >= 0 && posicao < baseDeDados.size()){
                return baseDeDados.get(posicao); // retorna a palavra
            }
        } finally{
            lock.readLock().unlock(); // desbloqueia
        }
        return null;
    }

    //Método para escrever um valor na base de dados
    public void escrever(int posicao, String valor){
        lock.writeLock().lock(); //bloqueia para escrever
        try{
            if(posicao >= 0 && posicao < baseDeDados.size()){
                baseDeDados.set(posicao, valor); //Modifica a palavra 
            }
        } finally{
            lock.writeLock().unlock(); //desbloqueia
        }
    }

    //GETS E SETS

    public int getTamanho(){
        return baseDeDados.size();
    }
}

