package org.javierhernandez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.javierhernandez.bean.CargoEmpleado;
import org.javierhernandez.bean.Empleados;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuEmpleadosController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NULL
    }

    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Empleados> ListaEmpledos;
    private ObservableList<CargoEmpleado> ListaCargoEmpleado;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnReportesE;

    @FXML
    private Button btnEliminarE;

    @FXML
    private Button btnActualizarE;

    @FXML
    private Button btnAgregarE;

    @FXML
    private ComboBox cmbCargoID;

    @FXML
    private TableView tblEmpleados;

    @FXML
    private TableColumn colApellidoEmpleado;

    @FXML
    private TableColumn colCargoEmpleado;

    @FXML
    private TableColumn colDireccionEmpleado;

    @FXML
    private TableColumn colEmpleadoID;

    @FXML
    private TableColumn colNombreEmpleados;

    @FXML
    private TableColumn colSueldoEmpleado;

    @FXML
    private TableColumn colTurnoEmpleado;

    @FXML
    private TextField txtApellidoEmpleado;

    @FXML
    private TextField txtDirrecionEmpleado;

    @FXML
    private TextField txtEmpleadoID;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtSueldoEmpleado;

    @FXML
    private TextField txtTurnoEmpleado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatosEmpleados();
        cmbCargoID.setItems(getCargoEmpleado());
        
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

    public void cargarDatosEmpleados() {
        tblEmpleados.setItems(getEmpleados());
        colEmpleadoID.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("IDEmpleado"));
        colNombreEmpleados.setCellValueFactory(new PropertyValueFactory<Empleados, String>("nombresEmpleado"));
        colApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("apellidosEmpleado"));
        colSueldoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, Double>("sueldo"));
        colDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("direccion"));
        colTurnoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, String>("turno"));
        colCargoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleados, Integer>("idCargoEmpleado"));
    }

    public void selecionarElementos() {
        txtEmpleadoID.setText(String.valueOf(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getIDEmpleado()));
        txtNombreEmpleado.setText(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getNombresEmpleado());
        txtApellidoEmpleado.setText(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getApellidosEmpleado());
        txtSueldoEmpleado.setText(String.valueOf(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getSueldo()));
        txtDirrecionEmpleado.setText(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getDireccion());
        txtTurnoEmpleado.setText(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getTurno());
        cmbCargoID.getSelectionModel().select(buscarCargoEmpleado(((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getIdCargoEmpleado()));
    }

    public CargoEmpleado buscarCargoEmpleado(int idCargoEmpleado) {
        CargoEmpleado resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarCargoEmpleado(?)}");
            procedimiento.setInt(1, idCargoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new CargoEmpleado(registro.getInt("idCargoEmpleado"),
                        registro.getString("nombreCargo"),
                        registro.getString("descripcionCargo")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<Empleados> getEmpleados() {
        ArrayList<Empleados> ListaEm = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarEmpleado()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                ListaEm.add(new Empleados(resultado.getInt("IDEmpleado"),
                        resultado.getString("nombresEmpleado"),
                        resultado.getString("apellidosEmpleado"),
                        resultado.getDouble("sueldo"),
                        resultado.getString("direccion"),
                        resultado.getString("turno"),
                        resultado.getInt("idCargoEmpleado")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListaEmpledos = FXCollections.observableList(ListaEm);
    }

    public ObservableList<CargoEmpleado> getCargoEmpleado() {
        ArrayList<CargoEmpleado> listaCargoEmpleado = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ListarCargoEmpleado() }");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaCargoEmpleado.add(new CargoEmpleado(resultado.getInt("idCargoEmpleado"),
                        resultado.getString("nombreCargo"),
                        resultado.getString("descripcionCargo")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListaCargoEmpleado = FXCollections.observableList(listaCargoEmpleado);
    }

    public void guardarEmpleados() {
        Empleados registro = new Empleados();
        registro.setIDEmpleado(Integer.parseInt(txtEmpleadoID.getText()));
        registro.setNombresEmpleado(txtNombreEmpleado.getText());
        registro.setApellidosEmpleado(txtApellidoEmpleado.getText());
        registro.setSueldo(Double.parseDouble(txtSueldoEmpleado.getText()));
        registro.setDireccion(txtDirrecionEmpleado.getText());
        registro.setTurno(txtTurnoEmpleado.getText());
        registro.setIdCargoEmpleado(((CargoEmpleado) cmbCargoID.getSelectionModel().getSelectedItem()).getIdCargoEmpleado());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarEmpleado(?, ?, ?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDEmpleado());
            procedimiento.setString(2, registro.getNombresEmpleado());
            procedimiento.setString(3, registro.getApellidosEmpleado());
            procedimiento.setDouble(4, registro.getSueldo());
            procedimiento.setString(5, registro.getDireccion());
            procedimiento.setString(6, registro.getTurno());
            procedimiento.setInt(7, registro.getIdCargoEmpleado());
            procedimiento.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActualizarEmpleados() {
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarEmpleado(?, ?, ?, ?, ?, ?, ?)}");
            Empleados registro = (Empleados) tblEmpleados.getSelectionModel().getSelectedItem();

            registro.setIDEmpleado(Integer.parseInt(txtEmpleadoID.getText()));
            registro.setNombresEmpleado(txtNombreEmpleado.getText());
            registro.setApellidosEmpleado(txtApellidoEmpleado.getText());
            registro.setSueldo(Double.parseDouble(txtSueldoEmpleado.getText()));
            registro.setDireccion(txtDirrecionEmpleado.getText());
            registro.setTurno(txtTurnoEmpleado.getText());
            registro.setIdCargoEmpleado(((CargoEmpleado) cmbCargoID.getSelectionModel().getSelectedItem()).getIdCargoEmpleado());

            procedimiento.setInt(1, registro.getIDEmpleado());
            procedimiento.setString(2, registro.getNombresEmpleado());
            procedimiento.setString(3, registro.getApellidosEmpleado());
            procedimiento.setDouble(4, registro.getSueldo());
            procedimiento.setString(5, registro.getDireccion());
            procedimiento.setString(6, registro.getTurno());
            procedimiento.setInt(7, registro.getIdCargoEmpleado());
            procedimiento.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarEmpleados() {
        switch (tipoDeOperaciones) {
            case NULL:
                activarControles();
                btnAgregarE.setText("Guardar");
                btnEliminarE.setText("Cancelar");
                btnActualizarE.setDisable(true);
                btnReportesE.setDisable(true);
                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarEmpleados();
                desactivarControles();
                limpiarControles();
                btnAgregarE.setText("Agregar");
                btnEliminarE.setText("Eliminar");
                btnActualizarE.setDisable(false);
                btnReportesE.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                cargarDatosEmpleados();
                break;
        }
    }

    public void ActualizarEmpleado(){
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblEmpleados.getSelectionModel().getSelectedItem() != null) {
                    btnActualizarE.setText("Actualizar");
                    btnReportesE.setText("Cancelar");
                    btnAgregarE.setDisable(true);
                    btnEliminarE.setDisable(true);
                    activarControles();
                    txtEmpleadoID.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe SELECCIONAR un empleado para actualizar");
                }
                break;
            case ACTUALIZAR:
                try {
                ActualizarEmpleados();
            } catch (Exception e) {
                e.printStackTrace();
            }
            btnActualizarE.setText("Editar");
            btnReportesE.setText("Reporte");
            btnAgregarE.setDisable(false);
            btnEliminarE.setDisable(false);
            desactivarControles();
            limpiarControles();
            tipoDeOperaciones = operaciones.NULL;
            cargarDatosEmpleados();
            break;
        }
    }
    
    public void EliminarEmpleado() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarE.setText("Agregar");
                btnEliminarE.setText("Eliminar");
                btnActualizarE.setDisable(false);
                btnReportesE.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                break;
            default:
                if (tblEmpleados.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirma la eliminación del registro", "Eliminar Producto?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarEmpleado(?)}");
                            procedimiento.setInt(1, ((Empleados) tblEmpleados.getSelectionModel().getSelectedItem()).getIDEmpleado());
                            procedimiento.execute();
                            ListaEmpledos.remove(tblEmpleados.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para eliminar");
                }
                break;
        }
    }
    

    public void activarControles() {
        txtEmpleadoID.setEditable(true);
        txtNombreEmpleado.setEditable(true);
        txtApellidoEmpleado.setEditable(true);
        txtSueldoEmpleado.setEditable(true);
        txtDirrecionEmpleado.setEditable(true);
        txtTurnoEmpleado.setEditable(true);
        cmbCargoID.setDisable(false);
    }

    public void desactivarControles() {
        txtEmpleadoID.setEditable(false);
        txtNombreEmpleado.setEditable(false);
        txtApellidoEmpleado.setEditable(false);
        txtSueldoEmpleado.setEditable(false);
        txtDirrecionEmpleado.setEditable(false);
        txtTurnoEmpleado.setEditable(false);
        cmbCargoID.setDisable(true);
    }

    public void limpiarControles() {
        txtEmpleadoID.clear();
        txtNombreEmpleado.clear();
        txtApellidoEmpleado.clear();
        txtSueldoEmpleado.clear();
        txtDirrecionEmpleado.clear();
        txtTurnoEmpleado.clear();
        cmbCargoID.getSelectionModel().getSelectedItem();
    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarE.setText("Agregar");
        btnActualizarE.setText("Editar");
        btnEliminarE.setText("Eliminar");
        btnRegresar.setText("Regresar");
        btnReportesE.setText("Reportes");
        btnAgregarE.setDisable(false);
        btnActualizarE.setDisable(false);
        btnEliminarE.setDisable(false);
        btnReportesE.setDisable(false);
        tipoDeOperaciones = operaciones.NULL;
    }

}
