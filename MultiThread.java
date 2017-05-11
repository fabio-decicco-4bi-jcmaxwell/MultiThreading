
package multithread;
import static java.lang.Math.random;//importo classe random;
import java.util.Random;//importo classe random;import java.util.concurrent.TimeUnit;
import static multithread.TicTacToe.punteggio;//importi variabuile statica punteggio della classe tictactoe
/**
 *
 * @author Fabio De Cicco
 */
public class MultiThread {

   
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        
        Thread tic = new Thread (new TicTacToe("TIC"));//creazione thread con nome tic;
        Thread tac = new Thread (new TicTacToe("TAC"));//creazione thread con nome tac;
        Thread toe = new Thread (new TicTacToe("TOE"));//creazione thread con nome toe;
        tic.start();//faccio partire il thread
        tac.start();//faccio partire il thread
        toe.start();//faccio partire il thread
        
        try {
        tic.join();//ferma l'esecuzione del programma e continua quando tic ha finito 
        tac.join();//ferma l'esecuzione del programma e continua quando tac ha finito 
        toe.join();//ferma l'esecuzione del programma e continua quando toe ha finito 
        }
        catch (InterruptedException e) {    //possibil sospensione del thread 
        }
        System.out.println("Punteggio = " + punteggio);//stampa quante volte toe è venuto dopo tac
        long end = System.currentTimeMillis();
          System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "mg");//con questa procedura si stampa il tempo impiegato per l'esecuzione
    }
}


class TicTacToe implements Runnable {
    
    
    private String t; // variabile di tipo stringa;
    private String msg;// variabile di tipo stringa;
    public static boolean conf = false;//variabile statica booleana;
    public static int punteggio = 0;//valore statico intero inizializzato a 0;
    public TicTacToe (String g) {
    this.t = g;
    }
    @Override //sovrascrivo metodo già preso dalla classe 
    public void run(){//procedure eseguite dal thread 
         for(int i = 10; i>0; i--){
             if("TAC".equals(t))//confronto se il thread è tac
             {
                 conf = true;
                 
             }
             Random rand = new Random();//creazione di un numero casuale
             int j = 100;
             int n = 300-j;
             int tempo = rand.nextInt(n)+j;
             
             msg = "<" + t + "> ";
            //ricerca possibile eccezione;
             try {
                 TimeUnit.MILLISECONDS.sleep(tempo);// attesa tempo casuale
             }
             catch (InterruptedException e){//cattura eccezione
             }
             if("TOE".equals(t)&& conf == true)//paragone, in caso di interruzione;
             {
                 punteggio++; //incremento
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
