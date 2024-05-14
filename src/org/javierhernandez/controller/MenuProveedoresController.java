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
import org.javierhernandez.bean.Proveedores;
import org.javierhernandez.database.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuProveedoresController implements Initializable {

    private Main escenarioPrincipal;
    private ObservableList<Proveedores> listarProveedores;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;

    @FXML
    private Button btnAgregarP;

    @FXML
    private Button btnEditarP;

    @FXML
    private Button btnEliminarP;

    @FXML
    private Button btnRegresarP;

    @FXML
    private Button btnReportesP;

    @FXML
    private TableColumn colApellidoP;

    @FXML
    private TableColumn colContactoP;

    @FXML
    private TableColumn colDireccionP;

    @FXML
    private TableColumn colIDP;

    @FXML
    private TableColumn colNitP;

    @FXML
    private TableColumn colNombreP;

    @FXML
    private TableColumn colRazonP;

    @FXML
    private TableColumn colpaginaWebP;

    @FXML
    private TableView tblProveedores;

    @FXML
    private TextField txtProveedorApellido;

    @FXML
    private TextField txtProveedorContacto;

    @FXML
    private TextField txtProveedorDireccion;

    @FXML
    private TextField txtProveedorPW;

    @FXML
    private TextField txtProveedorRazon;

    @FXML
    private TextField txtProveedoresID;

    @FXML
    private TextField txtProveedoresNit;

    @FXML
    private TextField txtProveedoresNombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosProveedores();
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
        if (event.getSource() == btnRegresarP) {
            escenarioPrincipal.menuPrincipalView();

        }
    }

    public void cargarDatosProveedores() {
        tblProveedores.setItems(getProveedores());
        colIDP.setCellValueFactory(new PropertyValueFactory<Proveedores, Integer>("IDProveedores"));
        colNitP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nitProveedor"));
        colNombreP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nombreProveedor"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("apellidoProveedor"));
        colDireccionP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("direccionProveedor"));
        colRazonP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("razonSocial"));
        colContactoP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("contactoPrincipal"));
        colpaginaWebP.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("paginaWeb"));
    }

    public void seleccionarElementoProveedores() {
        txtProveedoresID.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getIDProveedores()));
        txtProveedoresNit.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getNitProveedor());
        txtProveedoresNombre.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getNombreProveedor());
        txtProveedorApellido.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getApellidoProveedor());
        txtProveedorDireccion.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getDireccionProveedor());
        txtProveedorRazon.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getRazonSocial());
        txtProveedorContacto.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getContactoPrincipal());
        txtProveedorPW.setText(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getPaginaWeb());
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ListarProveedores() }");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Proveedores(resultado.getInt("IDProveedores"),
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

        return listarProveedores = FXCollections.observableList(lista);
    }

    public void guardarProveedores() {
        Proveedores registro = new Proveedores();

        registro.setIDProveedores(Integer.parseInt(txtProveedoresID.getText()));
        registro.setNitProveedor(txtProveedoresNit.getText());
        registro.setNombreProveedor(txtProveedoresNombre.getText());
        registro.setApellidoProveedor(txtProveedorApellido.getText());
        registro.setDireccionProveedor(txtProveedorDireccion.getText());
        registro.setRazonSocial(txtProveedorRazon.getText());
        registro.setContactoPrincipal(txtProveedorContacto.getText());
        registro.setPaginaWeb(txtProveedorPW.getText());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarProveedor (?,?,?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDProveedores());
            procedimiento.setString(2, registro.getNitProveedor());
            procedimiento.setString(3, registro.getNombreProveedor());
            procedimiento.setString(4, registro.getApellidoProveedor());
            procedimiento.setString(5, registro.getDireccionProveedor());
            procedimiento.setString(6, registro.getRazonSocial());
            procedimiento.setString(7, registro.getContactoPrincipal());
            procedimiento.setString(8, registro.getPaginaWeb());

            procedimiento.execute();
            listarProveedores.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActualizarProveedores() {
        Proveedores registro = (Proveedores) tblProveedores.getSelectionModel().getSelectedItem();
        registro.setIDProveedores(Integer.parseInt(txtProveedoresID.getText()));
        registro.setNitProveedor(txtProveedoresNit.getText());
        registro.setNombreProveedor(txtProveedoresNombre.getText());
        registro.setApellidoProveedor(txtProveedorApellido.getText());
        registro.setDireccionProveedor(txtProveedorDireccion.getText());
        registro.setRazonSocial(txtProveedorRazon.getText());
        registro.setContactoPrincipal(txtProveedorContacto.getText());
        registro.setPaginaWeb(txtProveedorPW.getText());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarProveedor(?,?,?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDProveedores());
            procedimiento.setString(2, registro.getNitProveedor());
            procedimiento.setString(3, registro.getNombreProveedor());
            procedimiento.setString(4, registro.getApellidoProveedor());
            procedimiento.setString(5, registro.getDireccionProveedor());
            procedimiento.setString(6, registro.getRazonSocial());
            procedimiento.setString(7, registro.getContactoPrincipal());
            procedimiento.setString(8, registro.getPaginaWeb());

            procedimiento.execute();
            listarProveedores.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarProveedores() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarP.setText("Agregar");
                btnReportesP.setText("Cancelar");
                btnAgregarP.setDisable(false);
                btnReportesP.setDisable(false);
                btnEliminarP.setDisable(true);
                btnEditarP.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarProveedores();
                desactivarControles();
                btnAgregarP.setText("Agregar");
                btnReportesP.setText("Cancelar");
                btnAgregarP.setDisable(false);
                btnReportesP.setDisable(false);
                btnEliminarP.setDisable(true);
                btnEditarP.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void editarProveedor() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblProveedores.getSelectionModel().getSelectedItem() != null) {
                    btnEditarP.setText("Actualizar");
                    btnReportesP.setText("Cancelar");
                    btnEditarP.setDisable(false);
                    btnReportesP.setDisable(false);
                    btnAgregarP.setDisable(true);
                    btnEliminarP.setDisable(true);
                    activarControles();
                    txtProveedoresID.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un Proveedor para editar");
                }
                break;
            case ACTUALIZAR:
                ActualizarProveedores();
                btnEditarP.setText("Actualizar");
                btnReportesP.setText("Cancelar");
                btnEditarP.setDisable(false);
                btnReportesP.setDisable(false);
                btnAgregarP.setDisable(true);
                btnEliminarP.setDisable(true);
                desactivarControles();
                limpiarControles();
                tipoDeOpereciones = operaciones.NULL;
                cargarDatosProveedores();
                break;
        }
    }

    public void EliminarProveedor() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEliminarP.setText("Eliminar");
                btnEliminarP.setDisable(false);
                btnAgregarP.setDisable(true);
                btnEditarP.setDisable(true);
                btnReportesP.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblProveedores.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro",
                            "Eliminar Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarProveedor(?)}");
                            procedimiento.setInt(1, ((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getIDProveedores());
                            listarProveedores.remove(tblProveedores.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar una compra para eliminar");
                }
                break; // Agregué este break que faltaba
        }
    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarP.setText("Agregar");
        btnEditarP.setText("Editar");
        btnEliminarP.setText("Eliminar");
        btnRegresarP.setText("Regresar");
        btnReportesP.setText("Reportes");
        btnAgregarP.setDisable(false);
        btnEditarP.setDisable(false);
        btnEliminarP.setDisable(false);
        btnReportesP.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

    public void desactivarControles() {
        txtProveedoresID.setEditable(false);
        txtProveedoresNit.setEditable(false);
        txtProveedoresNombre.setEditable(false);
        txtProveedorApellido.setEditable(false);
        txtProveedorDireccion.setEditable(false);
        txtProveedorRazon.setEditable(false);
        txtProveedorContacto.setEditable(false);
        txtProveedorPW.setEditable(false);
    }

    public void activarControles() {
        txtProveedoresID.setEditable(true);
        txtProveedoresNit.setEditable(true);
        txtProveedoresNombre.setEditable(true);
        txtProveedorApellido.setEditable(true);
        txtProveedorDireccion.setEditable(true);
        txtProveedorRazon.setEditable(true);
        txtProveedorContacto.setEditable(true);
        txtProveedorPW.setEditable(true);
    }

    public void limpiarControles() {
        txtProveedoresID.clear();
        txtProveedoresNit.clear();
        txtProveedoresNombre.clear();
        txtProveedorApellido.clear();
        txtProveedorDireccion.clear();
        txtProveedorRazon.clear();
        txtProveedorContacto.clear();
        txtProveedorPW.clear();
    }
}
