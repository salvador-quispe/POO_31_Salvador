/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_crud_mysql;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Maria
 */
public class CAlumnos {

    int codigo;
    String nombreAlumnos;
    String apellidosAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }

    // Método para insertar alumnos
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos) {
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());

        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO Alumnos (nombres, apellidos) VALUES (?, ?);";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno. Error: " + e.toString());
        }
    }

    // Método para mostrar alumnos
    public void mostrarAlumnos(JTable paramTablaTotalAlumnos) {
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalAlumnos.setRowSorter(ordenarTabla);

        String sql = "SELECT * FROM Alumnos;";
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        paramTablaTotalAlumnos.setModel(modelo);

        String[] datos = new String[3];
        Statement st;

        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1); // ID
                datos[1] = rs.getString(2); // Nombre
                datos[2] = rs.getString(3); // Apellidos
                modelo.addRow(datos);
            }
            paramTablaTotalAlumnos.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros. Error: " + e.toString());
        }
    }

    // Método para seleccionar un alumno
    public void seleccionarAlumnos(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos) {
        try {
            int fila = paramTablaAlumnos.getSelectedRow();

            if (fila >= 0) {
                paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección. Error: " + e.toString());
        }
    }
    public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramNombres.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Alumnos SET nombres = ?, apellidos = ? WHERE id = ?;";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(1, getApellidosAlumnos());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion Exitosa");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se modifico, error;"+ e.toString());
            
        }
    }
    public void EliminarAlumno(JTextField paramCodigo){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos WHERE id = ?;";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            
            JOptionPane.showConfirmDialog(null, "Se elimino correctamente el alumno");
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "No se elimino al alumno, "+ e.toString());
        }
    }
}
