/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsqlform;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KuznetsovIV
 */
public class DatabaseTableModelForAst extends AbstractTableModel {
    // This is the number of column
    int columnCount = 6;
    // These are the names for the table's columns.
  private static final String[] columnNames = {"Date", "Phone src",
    "Phone dst" ,"Time", "Billing time", "Status"};

  // These are the classes for each column's values.
  private static final Class[] columnTypes = {String.class,
    String.class, String.class, String.class, Integer.class, Integer.class, String.class};
  
  // хранилище для полученных данных из базы данных
private ArrayList data = new ArrayList();

// конструктор позволяет задать возможность редактирования
public DatabaseTableModelForAst(boolean editable) {
this.editable = editable;
}
private boolean editable;

    @Override
    public int getRowCount() {
        synchronized (data) {
return data.size();
}
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        synchronized (data) {
return ((ArrayList)data.get(row)).get(column);
}
    }
    
    public void setDataSource( ResultSet rs) throws Exception {
        
        // получаем данные
while ( rs.next() ) {
// здесь будем хранить ячейки одной строки
ArrayList row = new ArrayList();
for ( int i=0; i < columnCount; i++) {
if (columnTypes[i] == String.class)
row.add(rs.getString(i+1));
else
    System.out.println(columnTypes[i]);
row.add(rs.getInt(i+1));
}
synchronized (data) {
data.add(row);
// сообщаем о прибавлении строки
fireTableRowsInserted( data.size()-1, data.size()-1);
}
}//end while
}//end setDataSource
    }
    

