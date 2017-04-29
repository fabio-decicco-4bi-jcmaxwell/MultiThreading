
package multithread;
import static java.lang.Math.random;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static multithread.TicTacToe.punteggio;
/**
 *
 * @author Fabio De Cicco
 */
public class MultiThread {

   
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        
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


class TicTacToe implements Runnable {
    
    
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
            //ricerca possibile eccezione;
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
             System.out.println(msg);//stampa messaggio finale;
         }
    }
}
