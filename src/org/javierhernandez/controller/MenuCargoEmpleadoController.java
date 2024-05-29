package org.javierhernandez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.javierhernandez.bean.CargoEmpleado;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuCargoEmpleadoController implements Initializable {

    private Main escenarioPrincipal;

    private ObservableList<CargoEmpleado> listarCargoEmpleados;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;

    @FXML
    private Button btnAgregarCp;

    @FXML
    private Button btnEditarCp;

    @FXML
    private Button btnEliminarCp;

    @FXML
    private Button btnRegresarCp;

    @FXML
    private Button btnReportesCp;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colIdCa;

    @FXML
    private TableColumn colnombreCa;

    @FXML
    private TableView tblCEmpleados;

    @FXML
    private TextField txtdescripcionCargo;

    @FXML
    private TextField txtidCargoEmpleado;

    @FXML
    private TextField txtnombreCargo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosCargoEmpleado();
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
        if (event.getSource() == btnRegresarCp) {
            escenarioPrincipal.menuPrincipalView();

        }
    }

    public void cargarDatosCargoEmpleado() {
        tblCEmpleados.setItems(getCargoEmpleado());
        colIdCa.setCellValueFactory(new PropertyValueFactory<CargoEmpleado, Integer>("idCargoEmpleado"));
        colnombreCa.setCellValueFactory(new PropertyValueFactory<CargoEmpleado, String>("nombreCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<CargoEmpleado, String>("descripcionCargo"));
    }

    public void seleccionarElementoCargoEmpleado() {
        txtidCargoEmpleado.setText(String.valueOf(((CargoEmpleado) tblCEmpleados.getSelectionModel().getSelectedItem()).getIdCargoEmpleado()));
        txtnombreCargo.setText(((CargoEmpleado) tblCEmpleados.getSelectionModel().getSelectedItem()).getNombreCargo());
        txtdescripcionCargo.setText(((CargoEmpleado) tblCEmpleados.getSelectionModel().getSelectedItem()).getDescripcionCargo());
    }

    public ObservableList<CargoEmpleado> getCargoEmpleado() {
        ArrayList<CargoEmpleado> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ListarCargoEmpleado() }");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new CargoEmpleado(resultado.getInt("idCargoEmpleado"),
                        resultado.getString("nombreCargo"),
                        resultado.getString("descripcionCargo")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listarCargoEmpleados = FXCollections.observableList(lista);
    }

    public void guardarCargoEmpleado() {
        CargoEmpleado registro = new CargoEmpleado();
        registro.setIdCargoEmpleado(Integer.parseInt(txtidCargoEmpleado.getText()));
        registro.setNombreCargo(txtnombreCargo.getText());
        registro.setDescripcionCargo(txtdescripcionCargo.getText());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarCargoEmpleado(?,?,?)}");
            procedimiento.setInt(1, registro.getIdCargoEmpleado());
            procedimiento.setString(2, registro.getNombreCargo());
            procedimiento.setString(3, registro.getDescripcionCargo());

            procedimiento.execute();
            listarCargoEmpleados.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarCargoEmpleado() {

        CargoEmpleado registro = (CargoEmpleado) tblCEmpleados.getSelectionModel().getSelectedItem();
        registro.setIdCargoEmpleado(Integer.parseInt(txtidCargoEmpleado.getText()));
        registro.setNombreCargo(txtnombreCargo.getText());
        registro.setDescripcionCargo(txtdescripcionCargo.getText());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizaRCargoEmpleado(?,?,?)}");
            procedimiento.setInt(1, registro.getIdCargoEmpleado());
            procedimiento.setString(2, registro.getNombreCargo());
            procedimiento.setString(3, registro.getDescripcionCargo());

            procedimiento.execute();
            listarCargoEmpleados.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarCargoEmpleado() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarCp.setText("Guardar");
                btnReportesCp.setText("Cancelar");
                btnAgregarCp.setDisable(false);
                btnReportesCp.setDisable(false);
                btnEliminarCp.setDisable(true);
                btnEditarCp.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarCargoEmpleado();
                desactivarControles();
                btnAgregarCp.setText("Agregar");
                btnReportesCp.setText("Cancelar");
                btnEditarCp.setDisable(false);
                btnEliminarCp.setDisable(false);
                btnEliminarCp.setDisable(true);
                btnEditarCp.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void eliminarCargoEmpleado() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarCp.setText("Agregar");
                btnEliminarCp.setText("Eliminar");
                btnEditarCp.setDisable(false);
                btnReportesCp.setDisable(false);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblCEmpleados.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del Cargo de Empleado",
                            "Eliminar Cargo de Empleado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_EliminarCargoEmpleado(?)}");
                            procedimiento.setInt(1, ((CargoEmpleado) tblCEmpleados.getSelectionModel().getSelectedItem()).getIdCargoEmpleado());
                            listarCargoEmpleados.remove(tblCEmpleados.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un Cargo de Empleado para eliminar");
                }
                break; // Agregué este break que faltaba
        }
    }

    public void editarCargoEmpleado() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblCEmpleados.getSelectionModel().getSelectedItem() != null) {
                    btnEditarCp.setText("Actualizar");
                    btnReportesCp.setText("Cancelar");
                    btnEditarCp.setDisable(false);
                    btnReportesCp.setDisable(false);
                    btnAgregarCp.setDisable(true);
                    btnEliminarCp.setDisable(true);
                    activarControles();
                    txtidCargoEmpleado.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un Cargo de Empleado para editar");
                }
                break;
            case ACTUALIZAR:
                actualizarCargoEmpleado();
                btnEditarCp.setText("Actualizar");
                btnReportesCp.setText("Reporte");
                btnEditarCp.setDisable(false);
                btnReportesCp.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOpereciones = operaciones.NULL;
                cargarDatosCargoEmpleado();
                break;
        }
    }
    
    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarCp.setText("Agregar");
        btnEditarCp.setText("Editar");
        btnEliminarCp.setText("Eliminar");
        btnRegresarCp.setText("Regresar");
        btnReportesCp.setText("Reportes");
        btnAgregarCp.setDisable(false);
        btnEditarCp.setDisable(false);
        btnEliminarCp.setDisable(false);
        btnReportesCp.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

    public void desactivarControles() {
        txtidCargoEmpleado.setEditable(false);
        txtnombreCargo.setEditable(false);
        txtdescripcionCargo.setEditable(false);
    }

    public void activarControles() {
        txtidCargoEmpleado.setEditable(true);
        txtnombreCargo.setEditable(true);
        txtdescripcionCargo.setEditable(true);
    }

    public void limpiarControles() {
        txtidCargoEmpleado.clear();
        txtnombreCargo.clear();
        txtdescripcionCargo.clear();
    }
}