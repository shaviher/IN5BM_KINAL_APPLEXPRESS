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
import org.javierhernandez.bean.Proveedores;
import org.javierhernandez.bean.TelefonoProveedor;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 */
public class MenuTelefonoProveedorController implements Initializable {

    private Main escenarioPrincipal;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCELAR, NULL
    }

    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<TelefonoProveedor> ListaTelefonoProveedor;
    private ObservableList<Proveedores> listaProveedores;

    @FXML
    private Button btnAgregarTP;

    @FXML
    private Button btnEditarTP;

    @FXML
    private Button btnEliminarTP;

    @FXML
    private Button btnRegresarTP;

    @FXML
    private Button btnReportesP;

    @FXML
    private ComboBox cmbProveedorID;

    @FXML
    private TableColumn colTelefonoID;

    @FXML
    private TableColumn colNumeroPrincipal;

    @FXML
    private TableColumn colSegundarioNumero;

    @FXML
    private TableColumn colObservaciones;

    @FXML
    private TableColumn colProveedorTelefono;

    @FXML
    private TableView tblTelefonoProveedor;

    @FXML
    private TextField txtTelefonoID;

    @FXML
    private TextField txtTeleno1;

    @FXML
    private TextField txtTelefono2;

    @FXML
    private TextField txtObservacion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosTelefono();
        cmbProveedorID.setItems(getProveedores());
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresarTP) {
            escenarioPrincipal.menuPrincipalView();

        }
    }

    public void cargarDatosTelefono() {
        tblTelefonoProveedor.setItems(getTelefonoProveedor());
        colTelefonoID.setCellValueFactory(new PropertyValueFactory<>("IDTelefonoProveedor"));
        colNumeroPrincipal.setCellValueFactory(new PropertyValueFactory<>("numeroPincipal"));
        colSegundarioNumero.setCellValueFactory(new PropertyValueFactory<>("numeroSecundario"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        colProveedorTelefono.setCellValueFactory(new PropertyValueFactory<>("IDProveedores"));
    }

    public void selecionarElementos() {
        txtTelefonoID.setText(String.valueOf(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDTelefonoProveedor()));
        txtTeleno1.setText(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getNumeroPincipal());
        txtTelefono2.setText(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getNumeroSecundario());
        txtObservacion.setText(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getObservaciones());
        cmbProveedorID.getSelectionModel().select(buscarProveedores(((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDProveedores()));
    }

    public Proveedores buscarProveedores(int IDProveedores) {
        Proveedores resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarProveedor(?)}");
            procedimiento.setInt(1, IDProveedores);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Proveedores(
                        registro.getInt("IDProveedores"),
                        registro.getString("nitProveedor"),
                        registro.getString("nombreProveedor"),
                        registro.getString("apellidoProveedor"),
                        registro.getString("direccionProveedor"),
                        registro.getString("razonSocial"),
                        registro.getString("contactoPrincipal"),
                        registro.getString("paginaWeb")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<TelefonoProveedor> getTelefonoProveedor() {
        ArrayList<TelefonoProveedor> ListaTeleProv = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarTelefonoProveedor()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                ListaTeleProv.add(new TelefonoProveedor(
                        resultado.getInt("IDTelefonoProveedor"),
                        resultado.getString("numeroPincipal"),
                        resultado.getString("numeroSecundario"),
                        resultado.getString("observaciones"),
                        resultado.getInt("IDProveedores")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListaTelefonoProveedor = FXCollections.observableList(ListaTeleProv);
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> listaProv = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarProveedores()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaProv.add(new Proveedores(
                        resultado.getInt("IDProveedores"),
                        resultado.getString("nitProveedor"),
                        resultado.getString("nombreProveedor"),
                        resultado.getString("apellidoProveedor"),
                        resultado.getString("direccionProveedor"),
                        resultado.getString("razonSocial"),
                        resultado.getString("contactoPrincipal"),
                        resultado.getString("paginaWeb")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedores = FXCollections.observableList(listaProv);
    }

    public void guardarTelefono() {
        TelefonoProveedor registro = new TelefonoProveedor();
        registro.setIDTelefonoProveedor(Integer.parseInt(txtTelefonoID.getText()));
        registro.setNumeroPincipal(txtTeleno1.getText());
        registro.setNumeroSecundario(txtTelefono2.getText());
        registro.setObservaciones(txtObservacion.getText());
        registro.setIDProveedores(((Proveedores) cmbProveedorID.getSelectionModel().getSelectedItem()).getIDProveedores());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarTelefono(?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDTelefonoProveedor());
            procedimiento.setString(2, registro.getNumeroPincipal());
            procedimiento.setString(3, registro.getNumeroSecundario());
            procedimiento.setString(4, registro.getObservaciones());
            procedimiento.setInt(5, registro.getIDProveedores());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActualizarTelefono() {
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarTelefono(?, ?, ?, ?, ?)}");
            TelefonoProveedor registro = (TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem();

            registro.setIDTelefonoProveedor(Integer.parseInt(txtTelefonoID.getText()));
            registro.setNumeroPincipal(txtTeleno1.getText());
            registro.setNumeroSecundario(txtTelefono2.getText());
            registro.setObservaciones(txtObservacion.getText());
            registro.setIDProveedores(((Proveedores) cmbProveedorID.getSelectionModel().getSelectedItem()).getIDProveedores());

            procedimiento.setInt(1, registro.getIDTelefonoProveedor());
            procedimiento.setString(2, registro.getNumeroPincipal());
            procedimiento.setString(3, registro.getNumeroSecundario());
            procedimiento.setString(4, registro.getObservaciones());
            procedimiento.setInt(5, registro.getIDProveedores());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarTelefono() {
        switch (tipoDeOperaciones) {
            case NULL:
                activarControles();
                btnAgregarTP.setText("Guardar");
                btnEliminarTP.setText("Cancelar");
                btnEditarTP.setDisable(true);
                btnReportesP.setDisable(true);
                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarTelefono();
                desactivarControles();
                limpiarControles();
                btnAgregarTP.setText("Agregar");
                btnEliminarTP.setText("Eliminar");
                btnEditarTP.setDisable(false);
                btnReportesP.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                cargarDatosTelefono();
                break;
        }
    }

    public void Actualizartelefono() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblTelefonoProveedor.getSelectionModel().getSelectedItem() != null) {
                    btnEditarTP.setText("Actualizar");
                    btnReportesP.setText("Cancelar");
                    btnAgregarTP.setDisable(true);
                    btnEliminarTP.setDisable(true);
                    activarControles();
                    txtTelefonoID.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un teléfono para actualizar");
                }
                break;
            case ACTUALIZAR:
                try {
                ActualizarTelefono();
            } catch (Exception e) {
                e.printStackTrace();
            }
            btnEditarTP.setText("Editar");
            btnReportesP.setText("Reporte");
            btnAgregarTP.setDisable(false);
            btnEliminarTP.setDisable(false);
            desactivarControles();
            limpiarControles();
            tipoDeOperaciones = operaciones.NULL;
            cargarDatosTelefono();
            break;
        }
    }

    public void EliminarTelefono() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarTP.setText("Agregar");
                btnEliminarTP.setText("Eliminar");
                btnEditarTP.setDisable(false);
                btnReportesP.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                break;
            default:
                if (tblTelefonoProveedor.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirma la eliminación del registro", "Eliminar Teléfono?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarTelefono(?)}");
                            procedimiento.setInt(1, ((TelefonoProveedor) tblTelefonoProveedor.getSelectionModel().getSelectedItem()).getIDTelefonoProveedor());
                            procedimiento.execute();
                            ListaTelefonoProveedor.remove(tblTelefonoProveedor.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un teléfono para eliminar");
                }
                break;
        }
    }

    public void activarControles() {
        txtTelefonoID.setEditable(true);
        txtTeleno1.setEditable(true);
        txtTelefono2.setEditable(true);
        txtObservacion.setEditable(true);
        cmbProveedorID.setDisable(false);
    }

    public void desactivarControles() {
        txtTelefonoID.setEditable(false);
        txtTeleno1.setEditable(false);
        txtTelefono2.setEditable(false);
        txtObservacion.setEditable(false);
        cmbProveedorID.setDisable(true);
    }

    public void limpiarControles() {
        txtTelefonoID.clear();
        txtTeleno1.clear();
        txtTelefono2.clear();
        txtObservacion.clear();
        cmbProveedorID.getSelectionModel().clearSelection();
    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarTP.setText("Agregar");
        btnEditarTP.setText("Editar");
        btnEliminarTP.setText("Eliminar");
        btnReportesP.setText("Reportes");
        btnAgregarTP.setDisable(false);
        btnEditarTP.setDisable(false);
        btnEliminarTP.setDisable(false);
        btnReportesP.setDisable(false);
        tipoDeOperaciones = operaciones.NULL;
    }
}
