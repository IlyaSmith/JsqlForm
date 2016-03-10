/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author KuznetsovIV
 */
public class TPane extends JScrollPane implements Runnable {
    // JTable table;
   private DatabaseTableModelForAst dbm;
   private JsqlForm jsf;
   private JsConn jsc = new JsConn();
    public TPane (DatabaseTableModelForAst tbm, JsqlForm j) {
        //table = 
        
        super(new JTable (tbm));
        jsf=j;
        dbm=tbm;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) { 
            synchronized (jsf) {
            jsf.actionAdd();}
           // String st = jsf.getQueryString();
            synchronized (jsc) {
                System.out.println (jsc);
            jsc.JsConn1 (dbm,jsf.getQueryString(),true);}
            try {
                Thread.sleep (2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
