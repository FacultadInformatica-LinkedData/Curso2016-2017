/*
 * Copyright (C) 2014 Delcio Amarillo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package san_francisco_app;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase abstracta que implementa la interfaz {@code TableModel} y provee una API
 * para trabajar con objetos de dominio.
 *
 * @author Delcio Amarillo
 * @param <T> El tipo de clase de Dominio manejado por este modelo.
 */
public abstract class GenericDomainTableModel<T> implements TableModel {

    private EventListenerList listenerList;
    private List columnIdentifiers;
    private final List<T> data;

    /**
     * Crea un nuevo {@code GenericDomainTableModel} vacío,
     * sin datos ni identificadores para las columnas
     */
    public GenericDomainTableModel() {
        data = new ArrayList<>();
        columnIdentifiers = new ArrayList();
        listenerList = new EventListenerList();
    }

    /**
     * Crea un nuevo {@code GenericDomainTableModel} sin datos
     * con identificadores para las columnas.
     *
     * @param columnIdentifiers Los identificadores para las columnas de la tabla.
     * @throws IllegalArgumentException Si {@code columnIdentifiers} es {@code null}.
     */
    public GenericDomainTableModel(List columnIdentifiers) {
        this();
        if (columnIdentifiers == null) {
            throw new IllegalArgumentException("El parámetro columnIdentifers no puede ser null.");
        } else {
            this.columnIdentifiers.addAll(columnIdentifiers);
        }
    }

    /* ***************** *
     * Manejo de eventos *
     * ***************** */

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    /**
     * Método conveniente para obtener obtener una lista con
     * los TableModelListeners suscriptos a nuestro modelo.
     * NO es parte de la interfaz TableModel
     */
    public TableModelListener[] getTableModelListeners() {
        return listenerList.getListeners(TableModelListener.class);
    }

    /**
     * Método general para notificar a los {@code TableModelListeners}
     * que ha ocurrido un evento.
     * Nota: Los listeners son notificados en orden inverso al de suscripción.
     */
    protected void notifyTableChanged(TableModelEvent e) {
        TableModelListener[] listeners = getTableModelListeners();
        for (int i = listeners.length - 1; i >= 0; i--) {
            listeners[i].tableChanged(e);
        }
    }

    /**
     * Notifica que el header de la tabla ha cambiado.
     */
    protected void notifyTableHeaderChanged() {
        TableModelEvent e = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
        notifyTableChanged(e);
    }

    /**
     * Notifica que han sido insertadas nuevas filas.
     *
     * @param firstRow El índice de la primera fila insertada.
     * @param lastRow El índice de la última fila insertada.
     */
    protected void notifyTableRowsInserted(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        notifyTableChanged(e);
    }

    /**
     * Notifica que una o más filas en un rango han sido modificadas.
     *
     * @param firstRow El índice de la primera fila en el rango.
     * @param lastRow El índice de la última fila en el rango.
     */
    protected void notifyTableRowsUpdated(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        notifyTableChanged(e);
    }

    /**
     * Notifica que una o más filas en un rango han sido borradas.
     *
     * @param firstRow El índice de la primera fila en el rango.
     * @param lastRow El índice de la última fila en el rango.
     */
    protected void notifyTableRowsDeleted(int firstRow, int lastRow) {
        TableModelEvent e = new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
        notifyTableChanged(e);
    }

    /**
     * Notifica que el valor de una celda ha cambiado.
     *
     * @param row El índice de la fila a la que pertenece la celda.
     * @param column El índice de la columna a la que pertenece la celda.
     */
    protected void notifyTableCellUpdated(int row, int column) {
        TableModelEvent e = new TableModelEvent(this, row, row, column);
    }

    /* ***************************************************** *
     * Información del TableModel para el armado de la tabla *
     * ***************************************************** */

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnIdentifiers.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnCount()) {
            throw new ArrayIndexOutOfBoundsException(columnIndex);
        } else {
            return columnIdentifiers.get(columnIndex).toString();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /* ********************* *
     * Manipulación de datos *
     * ********************* */

    /**
     * Agrega un nuevo objeto de dominio como fila al final del table model.
     * @param domainObject El objeto de dominio.
     */
    public void addRow(T domainObject) {
        int rowIndex = data.size();
        data.add(domainObject);
        notifyTableRowsInserted(rowIndex, rowIndex);
    }

    /**
     * Agrega varios objetos de dominio como filas al final del table model.
     * @param domainObjects Los objetos de dominio
     */
    public void addRows(List<T> domainObjects) {
        if (!domainObjects.isEmpty()) {
            int firstRow = data.size();
            data.addAll(domainObjects);
            int lastRow = data.size() - 1;
            notifyTableRowsInserted(firstRow, lastRow);
        }
    }

    /**
     * Inserta un objeto de dominio como fila en el table model, en un
     * número de fila específico.
     *
     * @param domainObject El objeto de dominio.
     * @param rowIndex El número de fila.
     */
    public void insertRow(T domainObject, int rowIndex) {
        data.add(rowIndex, domainObject);
        notifyTableRowsInserted(rowIndex, rowIndex);
    }

    /**
     * Notifica que un objeto de dominio ha cambiado, causando
     * una notificación en cascada hacia los listeners suscriptos.
     * @param domainObject El objeto de dominio
     */
    public void notifyDomainObjectUpdated(T domainObject) {
        T[] elements = (T[])data.toArray();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == domainObject) {
                notifyTableRowsUpdated(i, i);
            }
        }
    }

    /**
     * Elimina un objeto de dominio del table model.
     * @param domainObject El objeto de dominio a eliminar.
    public void deleteRow(T domainObject) {
        int rowIndex = -1;
        while ((rowIndex = data.indexOf(domainObject)) > -1) {
            data.remove(domainObject);
            notifyTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    **/

    /**
     * Elimina una fila del table model según un índice.
     * Nota: NO remueve todas las ocurrencias del objeto
     * de dominio asociado al número de fila, sólo la indicada
     * por el parámetro {@code rowIndex}
     *
     * @param rowIndex El número de fila a eliminar.
     */
    public void deleteRow(int rowIndex) {
        if (data.remove(data.get(rowIndex))) {
            notifyTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    /**
     * Elimina las filas dentro del rango {@code [firstRow, lastRow]}.
     *
     * @param firstRow La primera fila a eliminar (inclusive).
     * @param lastRow La última fila a eliminar (inclusive).
     * @throws IllegalArgumentException Si {@code firstRow < 0} ó {@code lastRow < 0} ó {@code firstRow > lastRow}.
     */
    public void deleteRows(int firstRow, int lastRow) {
        if (firstRow < 0 || lastRow < 0 || firstRow > lastRow) {
            throw new IllegalArgumentException("Los parámetros firstRow y lastRow deben ser positivos y firstRow >= lastRow.");
        } else {
            for (int i = firstRow; i <= lastRow; i++) {
                data.remove ( i );
            }
            notifyTableRowsDeleted ( firstRow, lastRow );
        }
    }

    /**
     * Elimina todas las filas de este table model, notificando a los listeners.
     */
    public void clearTableModelData() {
        if (!data.isEmpty()) {
            int lastRow = data.size() - 1;
            data.clear();
            notifyTableRowsDeleted(0, lastRow);
        }
    }

    /**
     * Devuelve un objeto de dominio basado en un número de fila.
     * @param rowIndex El número de la fila.
     * @return Un objeto de dominio.
     */
    public T getDomainObject(int rowIndex) {
        return data.get(rowIndex);
    }

    /**
     * Devuelve una sublista de objetos de dominio comprendida en
     * el rango {@code [firstRow, lastRow]}.
     *
     * @param firstRow La primera fila del rango (inclusive).
     * @param lastRow La última fila del rango (inclusive).
     * @return Una sublista con objetos de dominio.
     */
    public List<T> getDomainObjects(int firstRow, int lastRow) {
        return Collections.unmodifiableList(data.subList(firstRow, lastRow + 1));
    }

    /**
     * @return Todos los objetos de dominio de este table model.
     */
    public List<T> getDomainObjects() {
        return Collections.unmodifiableList(data);
    }

    /**
     * Establece los identificadores de las columnas para este table model,
     * notificando a los listeners.
     * @param columnIdentifiers Los nuevos identificadores de columnas.
     */
    public void setColumnIdentifiers(List columnIdentifiers) {
        this.columnIdentifiers.clear();
        this.columnIdentifiers.addAll(columnIdentifiers);
        notifyTableHeaderChanged();
    }

    public T getFirst() {
        if (data.isEmpty())
            return null;
        return data.get(0);
    }
    public int getDataSize() {
        return data.size ();
    }

    public Object getDomainObject(String topic) {
        return null;
    }

    public void deleteRow(String topic){

    }

} // End of class GenericDomainTableModel