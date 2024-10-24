/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Maria
 */
public class CConexion {

    Connection conectar = null;

    String usuario = "admin";
    String contraseña = "15423803salvador";
    String bd = "salvador";
    String ip = "database-salvador.c48vkx4s2haj.us-east-1.rds.amazonaws.com";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection estableceConexion() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "la coneccion se ha realizado con exito");

        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "error al conectarse a la base de datos,error: " + e.toString());
        }
        return conectar;
    }
}
