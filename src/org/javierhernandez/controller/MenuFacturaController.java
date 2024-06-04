package org.javierhernandez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javierhernandez.bean.Clientes;
import org.javierhernandez.bean.Empleados;
import org.javierhernandez.bean.Factura;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuFacturaController implements Initializable {

    private Main escenarioPrincipal;

    private ObservableList<Factura> listasFactura;
    private ObservableList<Clientes> listasClientes;
    private ObservableList<Empleados> listasEmpleados;

    @FXML
    private DatePicker DpFechaFactura;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnReportes;

    @FXML
    private ComboBox cmbClienteID;

    @FXML
    private ComboBox cmbEmpleadoID;

    @FXML
    private TableColumn colClienteID;

    @FXML
    private TableColumn colFacturaID;

    @FXML
    private TableColumn colEmpleadoID;

    @FXML
    private TableColumn colEstado;

    @FXML
    private TableColumn colFechaFac;

    @FXML
    private TableColumn colTotalFactura;

    @FXML
    private TableView tblFactura;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtFacturaID;

    @FXML
    private TextField txtTotalFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();

        }
    }

    public void CargarDatosFactura() {
        tblFactura.setItems(getFactura());
        colFacturaID.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("IDDeFactura"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Factura, String>("estado"));
        colTotalFactura.setCellValueFactory(new PropertyValueFactory<Factura, Double>("totalFactura"));
        colFechaFac.setCellValueFactory(new PropertyValueFactory<Factura, String>("fechaFactura"));
        colClienteID.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("IDCliente"));
        colEmpleadoID.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("IDEmpleado"));
    }

    public void seleccionarElementos() {
        int IDDeFactura = ((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura();

        txtFacturaID.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura()));
        txtEstado.setText(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getEstado());
        txtTotalFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura()));
        DpFechaFactura.setValue(LocalDate.parse(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getFechaFactura()));
        cmbClienteID.getSelectionModel().select(buscarClientes(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDCliente()));
        cmbEmpleadoID.getSelectionModel().select(buscarEmpleados(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDEmpleado()));
    }

    public Clientes buscarClientes(int IDCliente) {
        Clientes resultado = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarClientes()}");
            procedimiento.setInt(1, IDCliente);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Clientes(registro.getInt("IDCliente"),
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

    public Empleados buscarEmpleados(int IDEmpleado) {
        Empleados resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarEmpleados(?)}");
            procedimiento.setInt(1, IDEmpleado);
            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                resultado = new Empleados(registro.getInt("IDEmpleado"),
                        registro.getString("nombresEmpleado"),
                        registro.getString("apellidosEmpleado"),
                        registro.getDouble("sueldo"),
                        registro.getString("direccion"),
                        registro.getString("turno"),
                        registro.getInt("idCargoEmpleado")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<Factura> getFactura() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarFactura()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Factura(resultado.getInt("IDDeFactura"),
                        resultado.getString("estado"),
                        resultado.getDouble("totalFactura"),
                        resultado.getString("fechaFactura"),
                        resultado.getInt("IDCliente"),
                        resultado.getInt("IDEmpleado")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listasFactura = FXCollections.observableArrayList(lista);
    }
    
    public void guardarFactura(){
        Factura registro = new Factura();
        registro.setFechaFactura(DpFechaFactura.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        registro.setIDCliente(((Clientes) cmbClienteID.getSelectionModel().getSelectedItem()).getIDCliente());
        registro.setIDEmpleado(((Empleados) cmbEmpleadoID.getSelectionModel().getSelectedItem()).getIDEmpleado());
        registro.setEstado(txtEstado.getText());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarFactura(?, ?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDDeFactura());
            procedimiento.setInt(2, registro.getIDDeFactura());
            procedimiento.setInt(3, registro.getIDDeFactura());
            procedimiento.setInt(4, registro.getIDDeFactura());
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    

}
