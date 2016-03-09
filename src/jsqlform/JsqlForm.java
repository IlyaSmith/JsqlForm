/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import jdk.nashorn.internal.ir.Statement;

//import javax.swing.*;
//import com.porty.swing.*;

/**
 *
 * @author Ilya
 */
public class JsqlForm extends JFrame //implements Observer 
{

    
    // параметры подключения

private String queryString ;//= "SELECT calldate,src,dst,duration,billsec  FROM cdr where calldate > '2016-01-26 00:00:00.0'";
private String dataString;

private DatabaseTableModelForAst dbm;
private JTextField phoneTextField;
private JTextField dateTextField;
private static Timer tm;
private static boolean up;


private JsConn jsc = new JsConn();

public JsqlForm() {
    up = true;
   //Панель ввода, формирования стоки запроса в БД
    // Set up file menu.
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    JMenuItem fileExitMenuItem = new JMenuItem("Exit",
      KeyEvent.VK_X);
    fileExitMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        actionExit();
      }
    });
    fileMenu.add(fileExitMenuItem);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);

    // Set up add panel.
    JPanel addPanel = new JPanel();
    phoneTextField = new JTextField(10);
    //Текстовое поле с текущей датой
    
    dateTextField = new JTextField(getData());
    addPanel.add(phoneTextField);
    addPanel.add(dateTextField);
    JButton addButton = new JButton("GO");
    
    addPanel.add(addButton);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(addPanel,BorderLayout.NORTH);
    
    
     
    // наша модель
dbm =
new DatabaseTableModelForAst(false);

// таблица и окно
JTable table = new JTable(dbm);
setTitle ("Asterisk Loader");
setSize(800, 700); 

 // Handle window closing events.
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        actionExit();
      }
    });
getContentPane().add(new JScrollPane(table));

//
addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        actionStart();
      }
    });
 
}
public void actionStart() {
   
        actionAdd ();
        /*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
                */
//        tm.counter(true);
    
}
public void actionAdd() {
    String phone = phoneTextField.getText();
    String date = dateTextField.getText();
    //Убираем пробелы
   
    date = date.replace(" ","");
    //date = date.replace(".", "-");
    //Проверка
    Pattern p = Pattern.compile("^[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])$");  
    Matcher m = p.matcher(date);  
    if (m.matches() ){
   setQuerySting ("SELECT calldate,src,dst,duration,billsec,disposition FROM cdr where calldate > '"+date+" 00:00:00.0'and dst = '"+phone+"'");
     jsc.JsConn1 (dbm,queryString,true);}
    
    else {
        errorDate(date);
    }
//  tm.counter(true);
}

public void setQuerySting (String arg) {
    queryString = arg;
}
//
private String getData () {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date d = new Date();
    return format.format(d);
}
 // Exit this program.
  private void actionExit() {
    System.exit(0);
  }
  // Обработка ошибок
  private void errorDate(String date) {
      System.out.print(date);
  }
public static void main(String[] args) {
    
JsqlForm jsf = new JsqlForm();
/*
//Таймер
     tm = new Timer();
     tm.addObserver(jsf);
    */ 
        
jsf.show();
}//end main 

 /*   @Override
  //  public void update(Observable o, Object arg) {
        
        actionAdd();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   */
    
}
