/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KuznetsovIV
 */
public class JsConn {
private String
//dsn = "jdbc:mysql://localhost:3306/affablebean",
dsn =  "jdbc:mysql://10.0.68.15:3306/asterisk",
        uid = "root",
//pwd = "1234";
pwd = "astRO#27"; 
    
    public JsConn (DatabaseTableModel dbm,String queryString){
        
        // инициализация JDBC
   Connection conn = null;
try {
//Class.forName("com.mysql.jdbc.Driver");
// объект-соединение с БД
conn = DriverManager.getConnection(dsn, uid, pwd);
Statement st = conn.createStatement();
// выполняем запрос
ResultSet rs = st.executeQuery(queryString);



// выводим результат запроса на экран
dbm.setDataSource(rs);
rs.close();
conn.close();
} catch (Exception ex) {
throw new RuntimeException(ex);
}
        
    }
    
}
