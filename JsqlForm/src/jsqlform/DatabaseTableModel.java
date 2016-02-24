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
 * @author Ilya
 */
public class DatabaseTableModel extends AbstractTableModel {
// здесь мы будем хранить названия столбцов
private ArrayList columnNames = new ArrayList();
// список типов столбцов
private ArrayList columnTypes = new ArrayList();
// хранилище для полученных данных из базы данных
private ArrayList data = new ArrayList();

// конструктор позволяет задать возможность редактирования
public DatabaseTableModel(boolean editable) {
this.editable = editable;
}
private boolean editable;
// количество строк
public int getRowCount() {
synchronized (data) {
return data.size();
}
}
// количество столбцов
public int getColumnCount() {
return columnNames.size();
}
// тип данных столбца
public Class getColumnClass(int column) {
return (Class)columnTypes.get(column);
}
// название столбца
public String getColumnName(int column) {
return (String)columnNames.get(column);
}
// данные в ячейке
public Object getValueAt(int row, int column) {
synchronized (data) {
return ((ArrayList)data.get(row)).get(column);
}
}
// возможность редактирования
public boolean isEditable(int row, int column) {
return editable;
}
// замена значения ячейки
public void setValueAt( Object value, int row, int column){
synchronized (data) {
((ArrayList)data.get(row)).set(column, value);
}
}
// получение данных из объекта ResultSet
public void setDataSource( ResultSet rs) throws Exception {
// удаляем прежние данные
data.clear();
columnNames.clear();
columnTypes.clear();
// получаем вспомогательную информацию о столбцах
ResultSetMetaData rsmd = rs.getMetaData();
int columnCount = rsmd.getColumnCount();
for ( int i=0; i < columnCount; i++) {
// название столбца
columnNames.add(rsmd.getColumnName(i+1));
// тип столбца
Class type =
Class.forName(rsmd.getColumnClassName(i+1));
columnTypes.add(type);
}
// сообщаем об изменениях в структуре данных
fireTableStructureChanged();
// получаем данные
while ( rs.next() ) {
// здесь будем хранить ячейки одной строки
ArrayList row = new ArrayList();
for ( int i=0; i < columnCount; i++) {
if (columnTypes.get(i) == String.class)
row.add(rs.getString(i+1));
else
row.add(rs.getObject(i+1));
}
synchronized (data) {
data.add(row);
// сообщаем о прибавлении строки
fireTableRowsInserted( data.size()-1, data.size()-1);
}
}//end while
}//end setDataSource
} 
