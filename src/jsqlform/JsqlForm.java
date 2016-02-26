/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

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
public class JsqlForm extends JFrame {

    
    // параметры подключения

private String queryString = "SELECT calldate,src,dst,duration,billsec  FROM cdr where calldate > '2016-01-26 00:00:00.0'";
 

private DatabaseTableModel dbm;
private JTextField phoneTextField;
private JTextField dateTextField;


public JsqlForm() {
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
    phoneTextField = new JTextField(4);
    dateTextField = new JTextField(10);
    addPanel.add(phoneTextField);
    addPanel.add(dateTextField);
    JButton addButton = new JButton("GO");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        actionAdd();
      }
    });
    addPanel.add(addButton);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(addPanel,BorderLayout.NORTH);
    // наша модель
dbm =
new DatabaseTableModel(false);

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

 
}

public void actionAdd() {
    String phone = phoneTextField.getText();
    String date = dateTextField.getText();
    
   setQuerySting ("SELECT FROM cdr where calldate > '"+date+" 00:00:00.0'and dst = '"+phone+"'");
    JsConn jsc = new JsConn (dbm,queryString);
}

public void setQuerySting (String arg) {
    queryString = arg;
}

 // Exit this program.
  private void actionExit() {
    System.exit(0);
  }
  
public static void main(String[] args) {
JsqlForm jsf = new JsqlForm();
jsf.show();
}//end main 
   
    
}
