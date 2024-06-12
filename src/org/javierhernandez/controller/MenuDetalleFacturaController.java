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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.javierhernandez.bean.DetalleFactura;
import org.javierhernandez.bean.Factura;
import org.javierhernandez.bean.Productos;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuDetalleFacturaController implements Initializable {

    private Main escenarioPrincipal;

    private ObservableList<DetalleFactura> listarDetalleFactura;
    private ObservableList<Productos> listarProductos;
    private ObservableList<Factura> listarFactura;

    private enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCELAR, NULL
    }

    private operaciones tipoOperaciones = operaciones.NULL;
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableView tblDetalleFactura;

    @FXML
    private TableColumn colCod;

    @FXML
    private TableColumn colPrecUnit;

    @FXML
    private TableColumn colCant;

    @FXML
    private TableColumn colNumFact;

    @FXML
    private TableColumn colCodProd;

    @FXML
    private TextField txtCod;

    @FXML
    private TextField txtPrecUnit;

    @FXML
    private TextField txtCant;

    @FXML
    private ComboBox cbxNumFact;

    @FXML
    private ComboBox cbxProd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cbxNumFact.setItems(getFactura());
        cbxProd.setItems(getProductos());
        cbxNumFact.setDisable(true);
        cbxProd.setDisable(true);
    }

    public void cargarDatos() {
        desactivarControles();
        tblDetalleFactura.setItems(getDetalleFactura());
        colCod.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("IDDetalleFactura"));
        colPrecUnit.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Double>("precioUnitario"));
        colCant.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("cantidad"));
        colNumFact.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("IDDeFactura"));
        colCodProd.setCellValueFactory(new PropertyValueFactory<DetalleFactura, Integer>("IDProducto"));
    }

    public void desactivarControles() {
        txtCod.setEditable(false);
        txtPrecUnit.setEditable(false);
        txtCant.setEditable(false);
        cbxNumFact.setDisable(true);
        cbxProd.setDisable(true);
    }

    public void activarControles() {
        txtCod.setEditable(true);
        txtCant.setEditable(true);
        cbxNumFact.setDisable(false);
        cbxProd.setDisable(false);
    }

    public void limpiarControles() {
        txtCod.clear();
        txtPrecUnit.clear();
        txtCant.clear();
        cbxNumFact.setValue(null);
        cbxProd.setValue(null);
    }

    public ObservableList<DetalleFactura> getDetalleFactura() {
        ArrayList<DetalleFactura> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_listarDetalleFactura()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new DetalleFactura(resultado.getInt("IDDetalleFactura"),
                        resultado.getDouble("precioUnitario"),
                        resultado.getInt("cantidad"),
                        resultado.getInt("IDDeFactura"),
                        resultado.getInt("IDProducto")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listarDetalleFactura = FXCollections.observableList(lista);
    }

    public ObservableList<Productos> getProductos() {
        ArrayList<Productos> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_listarProductos()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Productos(resultado.getInt("IDProducto"),
                        resultado.getString("descripcionProducto"),
                        resultado.getDouble("precioUnitario"),
                        resultado.getDouble("precioDocena"),
                        resultado.getDouble("precioMayor"),
                        resultado.getInt("existencia"),
                        resultado.getInt("idTipoProducto"),
                        resultado.getInt("IDProveedores")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listarProductos = FXCollections.observableList(lista);
    }

    public ObservableList<Factura> getFactura() {
        ArrayList<Factura> lista = new ArrayList<>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_listarFactura()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Factura(resultado.getInt("IDDeFactura"),
                        resultado.getString("estado"),
                        resultado.getDouble("totalFactura"),
                        resultado.getString("fechaFactura"),
                        resultado.getInt("IDCliente"),
                        resultado.getInt("IDEmpleado")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listarFactura = FXCollections.observableList(lista);
    }

    public void agregarDetalleFactura() {
        switch (tipoOperaciones) {
            case NULL:
                activarControles();
                btnEliminar.setText("Cancelar");
                btnAgregar.setText("Guardar");
                txtCod.setEditable(false);
                btnReporte.setDisable(true);
                btnEditar.setDisable(true);
                btnRegresar.setDisable(true);
                tipoOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarDetalleFactura();
                cargarDatos();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnRegresar.setDisable(false);
                btnReporte.setDisable(false);
                btnEditar.setDisable(false);
                tipoOperaciones = operaciones.NULL;
                break;
        }
    }

    public void guardarDetalleFactura() {
        DetalleFactura registro = new DetalleFactura();
        registro.setIDProducto(((Productos) cbxProd.getSelectionModel().getSelectedItem()).getIDProducto());
        registro.setIDDeFactura(((Factura) cbxNumFact.getSelectionModel().getSelectedItem()).getIDDeFactura());
        registro.setCantidad(Integer.parseInt(txtCant.getText()));

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_agregarDetalleFactura(?,?,?)}");
            procedimiento.setInt(1, registro.getCantidad());
            procedimiento.setInt(2, registro.getIDDeFactura());
            procedimiento.setInt(3, registro.getIDProducto());

            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seleccionarTupla() {
        int codProd = ((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDProducto();

        txtCod.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDDetalleFactura()));
        txtPrecUnit.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getPrecioUnitario()));
        txtCant.setText(String.valueOf(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getCantidad()));
        cbxNumFact.getSelectionModel().select(buscarFactura(((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDDeFactura()));
        cbxProd.getSelectionModel().select(buscaProducto(codProd));
    }

    public Productos buscaProducto(int codProd) {
        Productos result = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_buscarProductos(?)}");
            procedimiento.setInt(1, codProd);

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

    public Factura buscarFactura(int codFact) {
        Factura result = null;

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_buscarFactura(?)}");
            procedimiento.setInt(1, codFact);

            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                result = new Factura(registro.getInt("numeroFactura"),
                        registro.getString("estado"),
                        registro.getDouble("totalFactura"),
                        registro.getString("fechaFactura"),
                        registro.getInt("codigoCliente"),
                        registro.getInt("codigoEmpleado"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void eliminarDetalleFactura() {
        switch (tipoOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnReporte.setDisable(false);
                btnEditar.setDisable(false);
                btnRegresar.setDisable(false);
                tipoOperaciones = operaciones.NULL;
                break;
            default:
                if (tblDetalleFactura.getSelectionModel().getSelectedItem() != null) {

                    int resp = JOptionPane.showConfirmDialog(null, "Confirmar eliminar el registro", "Eliminar Detalle Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (resp == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_eliminarDetalleFactura(?)}");
                            procedimiento.setInt(1, ((DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem()).getIDDetalleFactura());
                            boolean execute = procedimiento.execute();
                            listarProductos.remove(tblDetalleFactura.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar");
                    }
                    cargarDatos();
                    break;
                }
        }
    }

    public void editar() {
        switch (tipoOperaciones) {
            case NULL:
                if (tblDetalleFactura.getSelectionModel().getSelectedItem() != null) {
                    btnReporte.setText("Cancelar");
                    btnEditar.setText("actualizar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnRegresar.setDisable(true);
                    txtCod.setDisable(true);
                    activarControles();
                    tipoOperaciones = operaciones.ACTUALIZAR;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una tupla para editar");
                    break;
                }
            case ACTUALIZAR:
                actualizar();
                btnReporte.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoOperaciones = operaciones.NULL;
                cargarDatos();
        }
    }

    public void actualizar() {
        DetalleFactura registro = (DetalleFactura) tblDetalleFactura.getSelectionModel().getSelectedItem();

        registro.setIDProducto(((Productos) cbxProd.getSelectionModel().getSelectedItem()).getIDProducto());
        registro.setIDDeFactura(((Factura) cbxNumFact.getSelectionModel().getSelectedItem()).getIDDeFactura());
        registro.setCantidad(Integer.parseInt(txtCant.getText()));

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_actualizarDetalleFactura(?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDDetalleFactura());
            procedimiento.setDouble(2, registro.getPrecioUnitario());
            procedimiento.setInt(3, registro.getCantidad());
            procedimiento.setInt(4, registro.getIDDeFactura());
            procedimiento.setInt(5, registro.getIDProducto());

            procedimiento.execute();

            listarDetalleFactura.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reporte() {
        switch (tipoOperaciones) {
            case ACTUALIZAR:
                btnReporte.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoOperaciones = operaciones.NULL;
                cargarDatos();
            case NULL:
                break;
        }
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

}
