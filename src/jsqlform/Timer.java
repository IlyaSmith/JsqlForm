 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KuznetsovIV
 */
public class Timer extends Observable implements Runnable {
  private  long time; 
    public void Timer() {
    time = System.currentTimeMillis();    
    }
  public void  counter(boolean stop) {
      if (stop)
      {
      //если меньше секунды то задержка
    //  if (time+1000<System.currentTimeMillis()) {
         // run ();
     // }
      setChanged ();
      notifyObservers (time); 
      }
            
      
  
  }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
