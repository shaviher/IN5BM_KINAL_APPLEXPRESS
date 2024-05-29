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
import org.javierhernandez.bean.Clientes;

import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

public class MenuClientesController implements Initializable {

    private Main escenarioPrincipal;
    private ObservableList<Clientes> listaClientes;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;

    @FXML
    private Button btnRegresar;
    @FXML
    private TableView tblClientes;
    @FXML
    private TableColumn colClienteID;
    @FXML
    private TableColumn colNombreCliente;
    @FXML
    private TableColumn colApellidoCliente;
    @FXML
    private TableColumn colNitCliente;
    @FXML
    private TableColumn colTelefonoCliente;
    @FXML
    private TableColumn colDireccionCliente;
    @FXML
    private TableColumn colCorreoCliente;
    @FXML
    private Button btnAgregarC;
    @FXML
    private Button btnEditarC;
    @FXML
    private Button btnEliminarC;
    @FXML
    private Button btnReportesC;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private TextField txtApellidoCliente;
    @FXML
    private TextField txtClienteID;
    @FXML
    private TextField txtTelefonoCliente;
    @FXML
    private TextField txtDireccionCliente;
    @FXML
    private TextField txtCorreoCliente;
    @FXML
    private TextField txtNitCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
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

    public void cargarDatos() {
        tblClientes.setItems(getClientes());
        colClienteID.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("IDCliente"));
        colNitCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("NitCliente"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("NombreCliente"));
        colApellidoCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("ApellidoCliente"));
        colTelefonoCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("TelefonoCliente"));
        colDireccionCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("DireccionCliente"));
        colCorreoCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("CorreoCliente"));
    }

    public void seleccionarElemento() {
        txtClienteID.setText(String.valueOf(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getIDCliente()));
        txtNitCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getNitCliente());
        txtNombreCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getNombreCliente());
        txtApellidoCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getApellidoCliente());
        txtTelefonoCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getTelefonoCliente());
        txtDireccionCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getDireccionCliente());
        txtCorreoCliente.setText(((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getCorreoCliente());
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
                        resultado.getString("correoCliente")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaClientes = FXCollections.observableList(lista);
    }

    public void Agregar() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarC.setText("Guardar");
                btnRegresar.setText("Cancelar");
                btnReportesC.setDisable(true);
                btnEditarC.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardar();
                desactivarControles();
                btnAgregarC.setText("Agregar");
                btnEliminarC.setText("Eliminar");
                btnEditarC.setDisable(false);
                btnReportesC.setDisable(false);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void guardar() {
        Clientes registro = new Clientes();
        registro.setIDCliente(Integer.parseInt(txtClienteID.getText()));
        registro.setNitCliente(txtNitCliente.getText());
        registro.setNombreCliente(txtNombreCliente.getText());
        registro.setApellidoCliente(txtApellidoCliente.getText());
        registro.setDireccionCliente(txtDireccionCliente.getText());
        registro.setTelefonoCliente(txtTelefonoCliente.getText());
        registro.setCorreoCliente(txtCorreoCliente.getText());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarCliente(?,?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDCliente());
            procedimiento.setString(2, registro.getNitCliente());
            procedimiento.setString(3, registro.getNombreCliente());
            procedimiento.setString(4, registro.getApellidoCliente());
            procedimiento.setString(5, registro.getDireccionCliente());
            procedimiento.setString(6, registro.getTelefonoCliente());
            procedimiento.setString(7, registro.getCorreoCliente());
            procedimiento.execute();
            listaClientes.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {

        Clientes registro = (Clientes) tblClientes.getSelectionModel().getSelectedItem();
        registro.setNitCliente(txtNitCliente.getText());
        registro.setNombreCliente(txtNombreCliente.getText());
        registro.setApellidoCliente(txtApellidoCliente.getText());
        registro.setDireccionCliente(txtDireccionCliente.getText());
        registro.setTelefonoCliente(txtTelefonoCliente.getText());
        registro.setCorreoCliente(txtCorreoCliente.getText());
        try {

            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarCliente(?,?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDCliente());
            procedimiento.setString(2, registro.getNitCliente());
            procedimiento.setString(3, registro.getNombreCliente());
            procedimiento.setString(4, registro.getApellidoCliente());
            procedimiento.setString(5, registro.getDireccionCliente());
            procedimiento.setString(6, registro.getTelefonoCliente());
            procedimiento.setString(7, registro.getCorreoCliente());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarClientes() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarC.setText("Agregar");
                btnReportesC.setText("Cancelar");
                btnAgregarC.setDisable(false);
                btnReportesC.setDisable(false);
                btnEliminarC.setDisable(true);
                btnEditarC.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardar();
                desactivarControles();
                btnAgregarC.setText("Agregar");
                btnReportesC.setText("Cancelar");
                btnAgregarC.setDisable(false);
                btnReportesC.setDisable(false);
                btnEliminarC.setDisable(true);
                btnEditarC.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void eliminarClientes() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEliminarC.setText("Eliminar");
                btnEliminarC.setDisable(false);
                btnAgregarC.setDisable(true);
                btnEditarC.setDisable(true);
                btnReportesC.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblClientes.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro",
                            "Eliminar Clientes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarCliente(?)}");
                            procedimiento.setInt(1, ((Clientes) tblClientes.getSelectionModel().getSelectedItem()).getIDCliente());
                            listaClientes.remove(tblClientes.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un cliente para eliminar");
                }
                break; // Agregué este break que faltaba
        }
    }

    public void editarClientes() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblClientes.getSelectionModel().getSelectedItem() != null) {
                    btnEditarC.setText("Actualizar");
                    btnReportesC.setText("Cancelar");
                    btnEditarC.setDisable(false);
                    btnReportesC.setDisable(false);
                    btnAgregarC.setDisable(true);
                    btnEliminarC.setDisable(true);
                    activarControles();
                    txtClienteID.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un cliente para editar");
                }
                break;
            case ACTUALIZAR:
                actualizar();
                btnEditarC.setText("Actualizar");
                btnReportesC.setText("Cancelar");
                btnEditarC.setDisable(false);
                btnReportesC.setDisable(false);
                btnAgregarC.setDisable(true);
                btnEliminarC.setDisable(true);
                desactivarControles();
                limpiarControles();
                tipoDeOpereciones = operaciones.NULL;
                cargarDatos();
                break;
        }
    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarC.setText("Agregar");
        btnEditarC.setText("Editar");
        btnEliminarC.setText("Eliminar");
        btnRegresar.setText("Regresar");
        btnReportesC.setText("Reportes");
        btnAgregarC.setDisable(false);
        btnEditarC.setDisable(false);
        btnEliminarC.setDisable(false);
        btnReportesC.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

    public void desactivarControles() {
        txtClienteID.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtApellidoCliente.setEditable(false);
        txtNitCliente.setEditable(false);
        txtTelefonoCliente.setEditable(false);
        txtDireccionCliente.setEditable(false);
        txtCorreoCliente.setEditable(false);
    }

    public void activarControles() {
        txtClienteID.setEditable(true);
        txtNombreCliente.setEditable(true);
        txtApellidoCliente.setEditable(true);
        txtNitCliente.setEditable(true);
        txtTelefonoCliente.setEditable(true);
        txtDireccionCliente.setEditable(true);
        txtCorreoCliente.setEditable(true);
    }

    public void limpiarControles() {
        txtClienteID.clear();
        txtNombreCliente.clear();
        txtApellidoCliente.clear();
        txtNitCliente.clear();
        txtTelefonoCliente.clear();
        txtDireccionCliente.clear();
        txtCorreoCliente.clear();
    }
}