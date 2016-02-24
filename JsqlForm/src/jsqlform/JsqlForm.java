/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import jdk.nashorn.internal.ir.Statement;
import java.sql.*;
import javax.swing.*;
//import com.porty.swing.*;

/**
 *
 * @author Ilya
 */
public class JsqlForm {

    /**
     * @param args the command line arguments
     */
    // параметры подключения
private static String
//dsn = "jdbc:mysql://localhost:3306/affablebean",
dsn =  "jdbc:mysql://10.0.68.15:3306/asterisk",
queryString = "SELECT * FROM cdr where calldate > '2016-01-24 00:00:00.0'",
uid = "root",
//pwd = "1234";
pwd = "astRO#27";     
public void setQuerySting (String arg) {
    queryString = arg;
}
public static void main(String[] args) {
// инициализация JDBC
Connection conn = null;
try {
//Class.forName("com.mysql.jdbc.Driver");
// объект-соединение с БД
conn = DriverManager.getConnection(dsn, uid, pwd);
Statement st = conn.createStatement();
// выполняем запрос
ResultSet rs = st.executeQuery(queryString );
// наша модель
DatabaseTableModel dbm =
new DatabaseTableModel(false);

// таблица и окно
JTable table = new JTable(dbm);
JFrame frame = new JFrame("Hi");
frame.setSize(700, 700);
frame.getContentPane().add(new JScrollPane(table));
frame.show();

// выводим результат запроса на экран
dbm.setDataSource(rs);
rs.close();
conn.close();
} catch (Exception ex) {
throw new RuntimeException(ex);
}

}//end main 
   
    
}
