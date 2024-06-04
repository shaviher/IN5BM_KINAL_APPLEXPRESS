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
import org.javierhernandez.bean.Compras;
import org.javierhernandez.bean.DetalleCompra;
import org.javierhernandez.bean.Productos;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuDetalleCompraController implements Initializable {

    private Main escenarioPrincipal;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCELAR, NULL
    }

    private operaciones tipoDeOperaciones = operaciones.NULL;
    private ObservableList<Compras> listasCompras;
    private ObservableList<Productos> listasProductos;
    private ObservableList<DetalleCompra> listasDetalleCompra;

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
    private TableColumn colCantidad;

    @FXML
    private TableColumn colCompraID;

    @FXML
    private TableColumn colCostoUnitario;

    @FXML
    private TableColumn colDCompraID;

    @FXML
    private TableColumn colProductoID;

    @FXML
    private TableView tblDetalleCompra;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCostoUnitario;

    @FXML
    private TextField txtDetalleCompraID;

    @FXML
    private ComboBox cmbCompraID;

    @FXML
    private ComboBox cmbProductoID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarDatosDetalleCompra();
        cmbProductoID.setItems(getProducto());
        cmbCompraID.setItems(getCompras());
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

    public void CargarDatosDetalleCompra() {
        tblDetalleCompra.setItems(getDetalleCompra());
        colDCompraID.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("IDDetalleCompra"));
        colCostoUnitario.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Double>("costoUnitario"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("cantidad"));
        colProductoID.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("IDProducto"));
        colCompraID.setCellValueFactory(new PropertyValueFactory<DetalleCompra, Integer>("IDCompra"));
        desactivarControles();
    }

    public void selecionarElementos() {
        txtDetalleCompraID.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDDetalleCompra()));
        txtCostoUnitario.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getCostoUnitario()));
        txtCantidad.setText(String.valueOf(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getCantidad()));
        cmbProductoID.getSelectionModel().select(buscaProducto(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDProducto()));
        cmbCompraID.getSelectionModel().select(buscarCompra(((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDCompra()));
    }

    public Compras buscarCompra(int IDCompra) {
        Compras result = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarCompras(?)}");
            procedimiento.setInt(1, IDCompra);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                result = new Compras(registro.getInt("IDCompra"),
                        registro.getString("fechaDocumento"),
                        registro.getString("descripcion"),
                        registro.getDouble("totalDocumento")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Productos buscaProducto(int IDProducto) {
        Productos result = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarProductos(?)}");
            procedimiento.setInt(1, IDProducto);

            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                result = new Productos(registro.getInt("IDProducto"),
                        registro.getString("descripcionProducto"),
                        registro.getDouble("precioUnitario"),
                        registro.getDouble("precioDocena"),
                        registro.getDouble("precioMayor"),
                        registro.getInt("existencia"),
                        registro.getInt("idTipoProducto"),
                        registro.getInt("IDProveedores"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ObservableList<DetalleCompra> getDetalleCompra() {
        ArrayList<DetalleCompra> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarDetalleCompra()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new DetalleCompra(resultado.getInt("IDDetalleCompra"),
                        resultado.getDouble("costoUnitario"),
                        resultado.getInt("cantidad"),
                        resultado.getInt("IDProducto"),
                        resultado.getInt("IDCompra")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listasDetalleCompra = FXCollections.observableList(lista);
    }

    public ObservableList<Productos> getProducto() {
        ArrayList<Productos> listaPro = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarProducto()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaPro.add(new Productos(resultado.getInt("IDProducto"),
                        resultado.getString("descripcionProducto"),
                        resultado.getDouble("precioUnitario"),
                        resultado.getDouble("precioDocena"),
                        resultado.getDouble("precioMayor"),
                        resultado.getInt("existencia"),
                        resultado.getInt("idTipoProducto"),
                        resultado.getInt("IDProveedores")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listasProductos = FXCollections.observableArrayList(listaPro);
    }

    public ObservableList<Compras> getCompras() {
        ArrayList<Compras> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarCompras()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Compras(resultado.getInt("IDCompra"),
                        resultado.getString("fechaDocumento"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("totalDocumento")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listasCompras = FXCollections.observableList(lista);
    }

    public void guardarDetalleCompra() {
        DetalleCompra registro = new DetalleCompra();
        registro.setIDDetalleCompra(Integer.parseInt(txtDetalleCompraID.getText()));
        registro.setCostoUnitario(Double.parseDouble(txtCostoUnitario.getText()));
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setIDProducto(((Productos) cmbProductoID.getSelectionModel().getSelectedItem()).getIDProducto());
        registro.setIDCompra(((Compras) cmbCompraID.getSelectionModel().getSelectedItem()).getIDCompra());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarDetalleCompra(?, ?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getIDDetalleCompra());
            procedimiento.setDouble(2, registro.getCostoUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setInt(4, registro.getIDProducto());
            procedimiento.setInt(5, registro.getIDCompra());

            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ActualizarDetalleCompra() {
        DetalleCompra registro = (DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem();
        registro.setIDDetalleCompra(Integer.parseInt(txtDetalleCompraID.getText()));
//        registro.setCostoUnitario(Double.parseDouble(txtCostoUnitario.getText()));
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setIDProducto(((Productos) cmbProductoID.getSelectionModel().getSelectedItem()).getIDProducto());
        registro.setIDCompra(((Compras) cmbCompraID.getSelectionModel().getSelectedItem()).getIDCompra());

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ActualizarDetalleCompra(?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDDetalleCompra());
            procedimiento.setDouble(2, registro.getCostoUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setInt(4, registro.getIDProducto());
            procedimiento.setInt(5, registro.getIDCompra());
            procedimiento.execute();

            listasDetalleCompra.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarDetalleCompra() {
        switch (tipoDeOperaciones) {
            case NULL:
                activarControles();
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReportes.setDisable(true);
                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarDetalleCompra();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReportes.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                CargarDatosDetalleCompra();
                break;
        }
    }

    public void ActualizarDetalleC() {
        switch (tipoDeOperaciones) {
            case NULL:
                if (tblDetalleCompra.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReportes.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    activarControles();
                    txtDetalleCompraID.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe SELECCIONAR un Detalle compra para actualizar");
                }
                break;
            case ACTUALIZAR:
                try {
                ActualizarDetalleCompra();
            } catch (Exception e) {
                e.printStackTrace();
            }
            btnEditar.setText("Editar");
            btnReportes.setText("Reporte");
            btnAgregar.setDisable(false);
            btnEliminar.setDisable(false);
            desactivarControles();
            limpiarControles();
            tipoDeOperaciones = operaciones.NULL;
            CargarDatosDetalleCompra();
            break;
        }
    }

    public void EliminarDetalleC() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReportes.setDisable(false);
                tipoDeOperaciones = operaciones.NULL;
                break;
            default:
                if (tblDetalleCompra.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirma la eliminación del registro", "Eliminar Detalle Compra?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarDetalleCompra(?)}");
                            procedimiento.setInt(1, ((DetalleCompra) tblDetalleCompra.getSelectionModel().getSelectedItem()).getIDDetalleCompra());
                            procedimiento.execute();
                            listasDetalleCompra.remove(tblDetalleCompra.getSelectionModel().getSelectedItem());
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
        txtDetalleCompraID.setEditable(true);
        txtCostoUnitario.setEditable(true);
        txtCantidad.setEditable(true);
        cmbProductoID.setDisable(false);
        cmbCompraID.setDisable(false);
    }

    public void desactivarControles() {
        txtDetalleCompraID.setEditable(false);
        txtCostoUnitario.setEditable(false);
        txtCantidad.setEditable(false);
        cmbProductoID.setDisable(true);
        cmbCompraID.setDisable(true);
    }

    public void limpiarControles() {
        txtDetalleCompraID.clear();
        txtCostoUnitario.clear();
        txtCantidad.clear();
        cmbProductoID.getSelectionModel().clearSelection();
        cmbCompraID.getSelectionModel().clearSelection();

    }

    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregar.setText("Agregar");
        btnEditar.setText("Editar");
        btnEliminar.setText("Eliminar");
        btnReportes.setText("Reportes");
        btnAgregar.setDisable(false);
        btnEditar.setDisable(false);
        btnEliminar.setDisable(false);
        btnReportes.setDisable(false);
        tipoDeOperaciones = operaciones.NULL;
    }
}
