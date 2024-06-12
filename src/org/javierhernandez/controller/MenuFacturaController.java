package org.javierhernandez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.JOptionPane;
import org.javierhernandez.bean.Clientes;
import org.javierhernandez.bean.Empleados;
import org.javierhernandez.bean.Factura;
import static org.javierhernandez.controller.MenuClientesController.operaciones.ACTUALIZAR;
import static org.javierhernandez.controller.MenuClientesController.operaciones.NULL;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.report.GenerarReportes;
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

    private enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCELAR, NULL
    }

    private operaciones tipoOperaciones = operaciones.NULL;

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
        CargarDatosFactura();
        cmbClienteID.setItems(getClientes());
        cmbEmpleadoID.setItems(getEmpleados());
        cmbClienteID.setDisable(true);
        cmbEmpleadoID.setDisable(true);
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

    public void seleccionarElementosFactura() {
        int IDDeFactura = ((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura();

        txtFacturaID.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura()));
        txtEstado.setText(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getEstado());
        txtTotalFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura()));
        DpFechaFactura.setValue(LocalDate.parse(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getFechaFactura()));
        cmbClienteID.getSelectionModel().select(buscarClientes(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDCliente()));
        cmbEmpleadoID.getSelectionModel().select(buscarEmpleados(IDDeFactura));
    }

    public Clientes buscarClientes(int IDCliente) {
        Clientes resultado = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarClientes(?)}");
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

    public ObservableList<Empleados> getEmpleados() {
        ArrayList<Empleados> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarEmpleado()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Empleados(resultado.getInt("IDEmpleado"),
                        resultado.getString("nombresEmpleado"),
                        resultado.getString("apellidosEmpleado"),
                        resultado.getDouble("sueldo"),
                        resultado.getString("direccion"),
                        resultado.getString("turno"),
                        resultado.getInt("idCargoEmpleado")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listasEmpleados = FXCollections.observableList(lista);
    }

    public ObservableList<Clientes> getClientes() {
        ArrayList<Clientes> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarClientes()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Clientes(resultado.getInt("IDCliente"),
                        resultado.getString("nitCliente"),
                        resultado.getString("nombreCliente"),
                        resultado.getString("apellidoCliente"),
                        resultado.getString("direccionCliente"),
                        resultado.getString("telefonoCliente"),
                        resultado.getString("correoCliente")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listasClientes = FXCollections.observableList(lista);
    }

    public void guardarFactura() {
        Factura registro = new Factura();
        registro.setIDDeFactura(Integer.parseInt(txtFacturaID.getText()));
        registro.setEstado(txtEstado.getText());
        registro.setTotalFactura(Double.parseDouble(txtTotalFactura.getText()));
        registro.setFechaFactura(DpFechaFactura.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        registro.setIDCliente(((Clientes) cmbClienteID.getSelectionModel().getSelectedItem()).getIDCliente());
        registro.setIDEmpleado(((Empleados) cmbEmpleadoID.getSelectionModel().getSelectedItem()).getIDEmpleado());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarFactura(?, ?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDDeFactura());
            procedimiento.setString(2, registro.getEstado());
            procedimiento.setDouble(3, registro.getTotalFactura());
            procedimiento.setString(4, registro.getFechaFactura());
            procedimiento.setInt(5, registro.getIDEmpleado());
            procedimiento.setInt(6, registro.getIDCliente());

            procedimiento.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarFactura() {
        Factura registro = new Factura();
        registro.setIDDeFactura(Integer.parseInt(txtFacturaID.getText()));
        registro.setEstado(txtEstado.getText());
        registro.setTotalFactura(Double.parseDouble(txtTotalFactura.getText()));
        registro.setFechaFactura(DpFechaFactura.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        registro.setIDCliente(((Clientes) cmbClienteID.getSelectionModel().getSelectedItem()).getIDCliente());
        registro.setIDEmpleado(((Empleados) cmbEmpleadoID.getSelectionModel().getSelectedItem()).getIDEmpleado());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarFactura(?, ?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDDeFactura());
            procedimiento.setString(2, registro.getEstado());
            procedimiento.setDouble(3, registro.getTotalFactura());
            procedimiento.setString(4, registro.getFechaFactura());
            procedimiento.setInt(5, registro.getIDEmpleado());
            procedimiento.setInt(6, registro.getIDCliente());

            procedimiento.execute();

            listasFactura.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarFactura() {
        switch (tipoOperaciones) {
            case NULL:
                activarControles();
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReportes.setDisable(true);
                tipoOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarFactura();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReportes.setDisable(false);
                tipoOperaciones = operaciones.NULL;
                CargarDatosFactura();
                break;
        }
    }

    public void ActualizarFactura() {
        switch (tipoOperaciones) {
            case NULL:
                if (tblFactura.getSelectionModel().getSelectedItem() != null) {
                    btnReportes.setText("Cancelar");
                    btnEditar.setText("actualizar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnRegresar.setDisable(true);
                    txtFacturaID.setDisable(true);
                    activarControles();
                    tipoOperaciones = operaciones.ACTUALIZAR;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una Celda para editar");
                    break;
                }
            case ACTUALIZAR:
                actualizarFactura();
                btnReportes.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoOperaciones = operaciones.NULL;
                CargarDatosFactura();
        }
    }

    public void eliminarFactura() {
        switch (tipoOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnReportes.setDisable(false);
                btnEditar.setDisable(false);
                btnRegresar.setDisable(false);
                tipoOperaciones = operaciones.NULL;
                break;
            default:
                if (tblFactura.getSelectionModel().getSelectedItem() != null) {

                    int resp = JOptionPane.showConfirmDialog(null, "Confirmar eliminar la factura", "Eliminar factura",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (resp == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarFactura(?)}");
                            procedimiento.setInt(1, ((Factura) tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura());
                            boolean execute = procedimiento.execute();
                            listasFactura.remove(tblFactura.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione una factura para eliminar");
                    }
                    CargarDatosFactura();
                    break;
                }
        }
    }
    
     public void imprimirReporte(){
        Map parametros = new HashMap();
        
        int facturaID = Integer.valueOf(((Factura)tblFactura.getSelectionModel().getSelectedItem()).getIDDeFactura());
        parametros.put("facturaID", facturaID);
        GenerarReportes.mostrarReportes("ReporteFactura.jasper", "Factura", parametros);
    }
    
    
    public void reporte() {
        switch (tipoOperaciones) {
            case NULL:
                imprimirReporte();
                break;
            case ACTUALIZAR:
                btnReportes.setText("Reportes");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                limpiarControles();
                desactivarControles();
                tipoOperaciones =  operaciones.NULL;
                CargarDatosFactura();
        }
    }
    
    public void desactivarControles() {
        txtFacturaID.setEditable(false);
        txtEstado.setEditable(false);
        txtTotalFactura.setEditable(false);
        DpFechaFactura.setEditable(false);
        cmbClienteID.setDisable(true);
        cmbEmpleadoID.setDisable(true);
    }

    public void activarControles() {
        txtFacturaID.setEditable(true);
        txtEstado.setEditable(true);
        txtTotalFactura.setEditable(true);
        DpFechaFactura.setEditable(true);
        cmbClienteID.setDisable(false);
        cmbEmpleadoID.setDisable(false);
    }

    public void limpiarControles() {
        txtFacturaID.clear();
        txtEstado.clear();
        txtTotalFactura.clear();
        DpFechaFactura.setValue(null);
        cmbClienteID.setValue(null);
        cmbEmpleadoID.setValue(null);
    }

   
}
