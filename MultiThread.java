
/*
 * Con questo programma voglio illustrare i seguenti concetti:
 * 1. MAIN e' un thread come gli altri e quindi puo' terminare prima che gli altri
 * 2. THREADs vengono eseguiti allo stesso tempo
 * 3. THREADs possono essere interrotti e hanno la possibilita' di interrompersi in modo pulito
 * 4. THREADs possono essere definiti mediante una CLASSE che implementa un INTERFACCIA Runnable
 * 5. THREADs possono essere avviati in modo indipendente da quando sono stati definiti
 * 6. posso passare parametri al THREADs tramite il costruttore della classe Runnable
 */
package multithread;
import static java.lang.Math.random;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static multithread.TicTacToe.punteggio;
/**
 *
 * @author Matteo Palitto
 */
public class MultiThread {

    /**
     * @param args the command line arguments
     */
    // "main" e' il THREAD principale da cui vengono creati e avviati tutti gli altri THREADs
    // i vari THREADs poi evolvono indipendentemente dal "main" che puo' eventualmente terminare prima degli altri
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        // Posso creare un THREAD e avviarlo immediatamente
        Thread tic = new Thread (new TicTacToe("TIC"));
        Thread tac = new Thread (new TicTacToe("TAC"));
        Thread toe = new Thread (new TicTacToe("TOE"));
        tic.start();
        tac.start();
        toe.start();
        
        try {
        tic.join();
        tac.join();
        toe.join();
        }
        catch (InterruptedException e) {    
        }
        System.out.println("Punteggio = " + punteggio);
        long end = System.currentTimeMillis();
          System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "mg");
    }
}

// Ci sono vari (troppi) metodi per creare un THREAD in Java questo e' il mio preferito per i vantaggi che offre
// +1 si puo estendere da un altra classe
// +1 si possono passare parametri (usando il Costruttore)
// +1 si puo' controllare quando un THREAD inizia indipendentemente da quando e' stato creato
class TicTacToe implements Runnable {
    
    // non essesndo "static" c'e' una copia delle seguenti variabili per ogni THREAD 
    private String t;
    private String msg;
    public static boolean conf = false;
    public static int punteggio = 0;
    public TicTacToe (String g) {
    this.t = g;
    }
    @Override
    public void run(){
         for(int i = 10; i>0; i--){
             if("TAC".equals(t))
             {
                 conf = true;
                 
             }
             Random rand = new Random();
             int j = 100;
             int n = 300-j;
             int tempo = rand.nextInt(n)+j;
             
             msg = "<" + t + "> ";
             try {
                 TimeUnit.MILLISECONDS.sleep(tempo);
             }
             catch (InterruptedException e){
             }
             if("TOE".equals(t)&& conf == true)
             {
                 punteggio++;
             }
             else
             {
                 conf = false;
             }
             msg += t + ":"+i;
             System.out.println(msg);
         }
    }
}
