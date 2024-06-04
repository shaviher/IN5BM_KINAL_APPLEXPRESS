package org.javierhernandez.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;
    private static Conexion instancia;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // Conexion  dbXavi 
             conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBApplexpress?useSSL=false&allowPublicKeyRetrieval=true", "root", "DKsWhbua");
            // Conexion cbLuis 
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBApplexpress?useSSL=false&allowPublicKeyRetrieval=true", "root", "14/09/2020sRrpgfyt");
            //conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBApplexpress?useSSL=false&allowPublicKeyRetrieval=true", "root", "Aurorita0306@");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
