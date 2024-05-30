package org.javierhernandez.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.javierhernandez.bean.Clientes;
import org.javierhernandez.bean.Empleados;
import org.javierhernandez.db.Conexion;

/**
 *
 * @author lcordova-2020429
 */
public class LoginController {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnLimpiar;
    
    public Clientes buscaCodigoCliente(int codigoCliente) {
        Clientes resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarClientePorID(?)}");
            procedimiento.setInt(1, codigoCliente);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Clientes(
                        registro.getInt("IDCliente"),
                        registro.getString("nitCliente"),
                        registro.getString("nombreCliente"),
                        registro.getString("apellidoCliente"),
                        registro.getString("direccionCliente"),
                        registro.getString("telefonoCliente"),
                        registro.getString("correoCliente")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Empleados buscarCodigoEmp(int codigoEmpleado) {
        Empleados resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarEmpleadoPorCodigo(?)}");
            procedimiento.setInt(1, codigoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Empleados(registro.getInt("codigoEmpleado"),
                       registro.getString("nombresEmpleado"),
                        registro.getString("apellidosEmpleado"),
                        registro.getDouble("sueldo"),
                        registro.getString("direccion"),
                        registro.getNString("turno"),
                        registro.getInt("codigoCargoEmpleado")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    
    public void ingressar(){
        String usuario = txtUser.getText();
        
        if(usuario  != null){
            
        }
    }
    
    
    public void limpiarDatos (){
        passwordTxt.clear();
        txtUser.clear();
    }

}
