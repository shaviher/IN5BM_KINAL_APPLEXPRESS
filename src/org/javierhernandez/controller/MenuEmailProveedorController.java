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
import org.javierhernandez.bean.EmailProveedor;
import org.javierhernandez.bean.Proveedores;
import org.javierhernandez.database.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuEmailProveedorController implements Initializable {

    private Main escenarioPrincipal;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;
    private ObservableList<EmailProveedor> ListaEmailProveedor;
    private ObservableList<Proveedores> listaProveedores;

    @FXML
    private Button btnAgregarEm;

    @FXML
    private Button btnEditarEm;

    @FXML
    private Button btnEliminarEm;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnReportesEm;

    @FXML
    private ComboBox cmbProveedorID;

    @FXML
    private TableColumn colDescripcionEmail;

    @FXML
    private TableColumn colEmailID;

    @FXML
    private TableColumn colNombreEmail;

    @FXML
    private TableColumn colProveedorEmail;

    @FXML
    private TableView tblEmailProveedor;

    @FXML
    private TextField txtDescripcionEmail;

    @FXML
    private TextField txtEmailID;

    @FXML
    private TextField txtNombeEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosEmail();
        cmbProveedorID.setItems(getProveedores());
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

    public void cargarDatosEmail() {
        tblEmailProveedor.setItems(getEmailProveedor());
        colEmailID.setCellValueFactory(new PropertyValueFactory<EmailProveedor, Integer>("IDEmailProveedor"));
        colNombreEmail.setCellValueFactory(new PropertyValueFactory<EmailProveedor, String>("emailproveedor"));
        colDescripcionEmail.setCellValueFactory(new PropertyValueFactory<EmailProveedor, String>("descripcion"));
        colProveedorEmail.setCellValueFactory(new PropertyValueFactory<EmailProveedor, Integer>("IDProveedores"));
    }

    public void selecionarElementos() {
        txtEmailID.setText(String.valueOf(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDEmailProveedor()));
        txtNombeEmail.setText(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getEmailproveedor());
        txtDescripcionEmail.setText(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getDescripcion());
        cmbProveedorID.getSelectionModel().select(buscarProveedores(((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDProveedores()));
    }

    public Proveedores buscarProveedores(int IDProveedores) {
        Proveedores resultado = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarProveedor(?)}");
            procedimiento.setInt(1, IDProveedores);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Proveedores(registro.getInt("IDProveedores"),
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

    public ObservableList<EmailProveedor> getEmailProveedor() {
        ArrayList<EmailProveedor> ListaEma = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarEmail()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                ListaEma.add(new EmailProveedor(resultado.getInt("IDEmailProveedor"),
                        resultado.getString("emailproveedor"),
                        resultado.getString("descripcion"),
                        resultado.getInt("IDProveedores")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListaEmailProveedor = FXCollections.observableList(ListaEma);
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> listaProv = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarProveedores()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaProv.add(new Proveedores(resultado.getInt("IDProveedores"),
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

    public void guardarEmail() {
        EmailProveedor registro = new EmailProveedor();
        registro.setIDEmailProveedor(Integer.parseInt(txtEmailID.getText()));
        registro.setEmailproveedor(txtNombeEmail.getText());
        registro.setDescripcion(txtDescripcionEmail.getText());
        registro.setIDProveedores(((Proveedores) cmbProveedorID.getSelectionModel().getSelectedItem()).getIDProveedores());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarEmail(?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDEmailProveedor());
            procedimiento.setString(2, registro.getEmailproveedor());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getIDProveedores());
            procedimiento.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActualizarEmail() {
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarEmail(?, ?, ?, ?)}");
            EmailProveedor registro = (EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem();

            registro.setIDEmailProveedor(Integer.parseInt(txtEmailID.getText()));
            registro.setEmailproveedor(txtNombeEmail.getText());
            registro.setDescripcion(txtDescripcionEmail.getText());
            registro.setIDProveedores(((Proveedores) cmbProveedorID.getSelectionModel().getSelectedItem()).getIDProveedores());

            procedimiento.setInt(1, registro.getIDEmailProveedor());
            procedimiento.setString(2, registro.getEmailproveedor());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getIDProveedores());
            procedimiento.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarEmail() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarEm.setText("Guardar");
                btnEliminarEm.setText("Cancelar");
                btnEditarEm.setDisable(true);
                btnReportesEm.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarEmail();
                desactivarControles();
                limpiarControles();
                btnAgregarEm.setText("Agregar");
                btnEliminarEm.setText("Eliminar");
                btnEditarEm.setDisable(false);
                btnReportesEm.setDisable(false);
                tipoDeOpereciones = operaciones.NULL;
                cargarDatosEmail();
                break;
        }
    }

    public void Actualizaremail() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblEmailProveedor.getSelectionModel().getSelectedItem() != null) {
                    btnEditarEm.setText("Actualizar");
                    btnReportesEm.setText("Cancelar");
                    btnAgregarEm.setDisable(true);
                    btnEliminarEm.setDisable(true);
                    activarControles();
                    txtEmailID.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un email para actualizar");
                }
                break;
            case ACTUALIZAR:
                try {
                ActualizarEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
            btnEditarEm.setText("Editar");
            btnReportesEm.setText("Reporte");
            btnAgregarEm.setDisable(false);
            btnEliminarEm.setDisable(false);
            desactivarControles();
            limpiarControles();
            tipoDeOpereciones = operaciones.NULL;
            cargarDatosEmail();
            break;
        }
    }

    public void EliminarEmail() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarEm.setText("Agregar");
                btnEliminarEm.setText("Eliminar");
                btnEditarEm.setDisable(false);
                btnRegresar.setDisable(false);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblEmailProveedor.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirma la eliminación del registro", "Eliminar Email?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarEmail(?)}");
                            procedimiento.setInt(1, ((EmailProveedor) tblEmailProveedor.getSelectionModel().getSelectedItem()).getIDEmailProveedor());
                            procedimiento.execute();
                            ListaEmailProveedor.remove(tblEmailProveedor.getSelectionModel().getSelectedItem());
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
        txtEmailID.setEditable(true);
        txtNombeEmail.setEditable(true);
        txtDescripcionEmail.setEditable(true);
        cmbProveedorID.setDisable(false);
    }

    public void desactivarControles() {
        txtEmailID.setEditable(false);
        txtNombeEmail.setEditable(false);
        txtDescripcionEmail.setEditable(false);
        cmbProveedorID.setDisable(true);
    }

    public void limpiarControles() {
        txtEmailID.clear();
        txtNombeEmail.clear();
        txtDescripcionEmail.clear();
        cmbProveedorID.getSelectionModel().getSelectedItem();
    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarEm.setText("Agregar");
        btnEditarEm.setText("Editar");
        btnEliminarEm.setText("Eliminar");
        btnRegresar.setText("Regresar");
        btnReportesEm.setText("Reportes");
        btnAgregarEm.setDisable(false);
        btnEditarEm.setDisable(false);
        btnEliminarEm.setDisable(false);
        btnReportesEm.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

}
