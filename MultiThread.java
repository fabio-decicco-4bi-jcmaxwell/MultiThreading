package multithread;

import static java.lang.Math.random;                                            //Importazione del metodo Random
import java.util.Random;                                                        
import java.util.concurrent.TimeUnit;

public class MultiThread {                                                      //crezione della classe
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        Schermo m = new Schermo();
        Thread tic = new Thread(new TicTacToe("TIC", m));                 //Creazione del thread con nome tic
        Thread tac = new Thread(new TicTacToe("TAC", m));                 //Creazione del thread con nome tac
        Thread toe = new Thread(new TicTacToe("TOE", m));                 //Creazione del thread con nome toe
        tic.start();                                                            //Avvio del thread tic
        tac.start();                                                            //Avvio del thread tac
        toe.start();                                                            //Avvio del thread toe

        try {
            tic.join();                                                         //Aspetta che i thread finiscano la l'esecuzione
            tac.join();
            toe.join();
        } 
        catch (InterruptedException e) {    
        }
        System.out.println("Punteggio = " + m.getPunteggio());    //Stampa il punteggio del metodo synchronized
        long end = System.currentTimeMillis();
        System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "ms");  
    }
}

class TicTacToe implements Runnable {                                           //la classe dei thread
    private String nome;
    private String msg;
    Schermo m;
    //Costruttore
    public TicTacToe (String n, Schermo m) {
        this.nome = n;
        this.m = m;
    }
    @Override                                                                   //operazione di riscrittura di un metodo ereditato
    public void run() {                                                     
        for (int i = 10; i > 0; i--) {
            Random rand = new Random();                                         //Creazione di un numero casuale
            int j = 100;
            int n = 300-j;
            int tempo = rand.nextInt(n)+j;
            m.Punteggio(nome, msg, tempo);                                      //Richiama il metodo synchronized
            msg = "<" + nome + "> " + nome + ": " + i;
            System.out.println(msg);
        }
    } 
}

class Schermo {                                                                //Classe dello schermo
    String ultimo = " ";                                                      //Ultimo thread visualizzato dal metodo condiviso synchronized
    int punteggio = 0;                                                      //Punteggio del metodo condiviso synchronized
    
    public int getPunteggio() {                                             //Stampa il punteggio del metodo condiviso synchronized
        return punteggio;
    }
    
    public synchronized void Punteggio(String thread, String msg, int tempo){//Metodo synchronized condiviso dai thread
        msg += ": " + tempo + " :";
        if( thread.equals("TOE") && ultimo.equals("TAC"))                     //Controlla quando TAC viene prima di TIC 
        {                       
            punteggio ++;                                                    // aggiorna il punteggio se è vero
        }
        try {
            TimeUnit.MILLISECONDS.sleep(tempo);                                 //Thread in pausa per un tempo casuale
        } 
        catch (InterruptedException e) {
        }            
        ultimo = thread;                                                      //Ultimo thread visualizzato 
    }    
     
}
