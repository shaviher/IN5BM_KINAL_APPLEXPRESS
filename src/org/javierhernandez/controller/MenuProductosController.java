/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import org.javierhernandez.bean.Productos;
import org.javierhernandez.bean.Proveedores;
import org.javierhernandez.bean.TipoProducto;
import org.javierhernandez.database.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuProductosController implements Initializable {

    private Main escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NULL
    }
    private operaciones tipoDeOperacion = operaciones.NULL;
    private ObservableList<Productos> ListaProductos;
    private ObservableList<Proveedores> listaProveedores;
    private ObservableList<TipoProducto> listaTipoDeProducto;

    @FXML
    private Button btnAgregarPr;

    @FXML
    private Button btnEditarCPr;

    @FXML
    private Button btnEliminarPr;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnReportesPr;

    @FXML
    private ComboBox cmbIDProveedor;

    @FXML
    private ComboBox cmbIDTipoProducto;

    @FXML
    private TableColumn colIDProductos;

    @FXML
    private TableColumn colIDTipoProducto;

    @FXML
    private TableColumn colIDProveedor;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colPrecioUnitario;

    @FXML
    private TableColumn colPrecioDocena;

    @FXML
    private TableColumn colPrecioMayor;

    @FXML
    private TableColumn colExistencia;

    @FXML
    private TableView tblProductos;

    @FXML
    private TextField txtDescripcionPro;

    @FXML
    private TextField txtExistencia;

    @FXML
    private TextField txtPrecioDocena;

    @FXML
    private TextField txtPrecioMayor;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtProductosID;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guardarProducto();
        cmbIDTipoProducto.setItems(getTipoP());
        cmbIDProveedor.setItems(getProveedores());
        // TODO
    }

    public void cargarDatosProductos() {
        tblProductos.setItems(getProducto());
        colIDProductos.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("IDProducto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Productos, String>("Descripcion"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionProducto"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioUnitario"));
        colPrecioDocena.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioDocena"));
        colPrecioMayor.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioMayor"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("existencia"));
        colIDTipoProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("idTipoProducto"));
        colIDProveedor.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("IDProveedores"));
    }

    public void selecionarElementos() {
        txtProductosID.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIDProducto()));
        txtDescripcionPro.setText(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getDescripcionProducto());
        txtPrecioUnitario.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioUnitario()));
        txtPrecioDocena.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioDocena()));
        txtPrecioMayor.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioMayor()));
        txtExistencia.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getExistencia()));
        cmbIDTipoProducto.getSelectionModel().select(buscarTipoProducto(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getIdTipoProducto()));
        cmbIDProveedor.getSelectionModel().select(buscarProveedores(((Proveedores)tblProductos.getSelectionModel().getSelectedItem()).getIDProveedores()));
    }

    public TipoProducto buscarTipoProducto(int idTipoProducto) {
        TipoProducto resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_BuscarTipoProducto(?)}");
            procedimiento.setInt(1, idTipoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new TipoProducto(registro.getInt("idTipoProducto"),
                        registro.getString("descripcion")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
    
     public Proveedores buscarProveedores(int IDProveedores){
        Proveedores resultado = null;
        
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("CALL sp_ListarProveedores()");
            procedimiento.setInt(1, IDProveedores);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
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
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resultado;
    }
    

    public ObservableList<Productos> getProducto() {
        ArrayList<Productos> listaPro = new ArrayList<Productos>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("CALL sp_ListarProducto()");
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
        return ListaProductos = FXCollections.observableArrayList(listaPro);
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> listaProv = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("CALL sp_ListarProveedores()");
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

    public ObservableList<TipoProducto> getTipoP() {
        ArrayList<TipoProducto> listaTp = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_ListarTipoProducto()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaTp.add(new TipoProducto(resultado.getInt("idTipoProducto"),
                        resultado.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTipoDeProducto = FXCollections.observableList(listaTp);
    }

    public void guardarProducto() {
        Productos registro = new Productos();
        registro.setIDProducto(Integer.parseInt(txtProductosID.getText()));
        registro.setDescripcionProducto(txtDescripcionPro.getText());
        registro.setPrecioUnitario(Double.parseDouble(txtPrecioUnitario.getText()));
        registro.setPrecioDocena(Double.parseDouble(txtPrecioDocena.getText()));
        registro.setPrecioMayor(Double.parseDouble(txtPrecioMayor.getText()));
        registro.setExistencia(Integer.parseInt(txtExistencia.getText()));
        registro.setIDProveedores(((Proveedores)cmbIDProveedor.getSelectionModel().getSelectedItem()).getIDProveedores());
         registro.setIdTipoProducto(((TipoProducto)cmbIDTipoProducto.getSelectionModel().getSelectedItem()).getIdTipoProducto());
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarDetalleCompra(?,?,?,?,?,?,?,?,?)}");
            procedimiento.setInt(1, registro.getIDProducto());
            procedimiento.setString(2, registro.getDescripcionProducto());
            procedimiento.setDouble(3, registro.getPrecioUnitario());
            procedimiento.setDouble(4, registro.getPrecioDocena());
            procedimiento.setDouble(5, registro.getPrecioMayor());
            procedimiento.setInt(6, registro.getExistencia());
            procedimiento.setInt(7, registro.getIDProveedores());
            procedimiento.setInt(8, registro.getIdTipoProducto());
            procedimiento.execute();

            ListaProductos.add(registro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
     public void agregarProducto(){
         switch(tipoDeOperacion){
             case NULL:
              activarControles();
             btnAgregarPr.setText("Guardar");
             btnEliminarPr.setText("Cancelar");
             btnEditarCPr.setDisable(true);
             btnReportesPr.setDisable(true);   
             tipoDeOperacion = operaciones.ACTUALIZAR;
             break;
             case ACTUALIZAR:
             guardarProducto ();
             desactivarControles();
             limpiarControles ();
             btnAgregarPr.setText("Agregar");
             btnEliminarPr.setText("Eliminar");
             btnEditarCPr.setDisable(false);
             btnReportesPr.setDisable(false);
             tipoDeOperacion = operaciones.NULL;
             cargarDatosProductos();
             break;
         }  

    }

    public void activarControles() {
        txtProductosID.setEditable(true);
        txtDescripcionPro.setEditable(true);
        txtPrecioUnitario.setEditable(true);
        txtPrecioDocena.setEditable(true);
        txtPrecioMayor.setEditable(true);
        txtExistencia.setEditable(true);
        cmbIDProveedor.setEditable(true);
        cmbIDTipoProducto.setEditable(true);
    }

    public void desactivarControles() {
        txtProductosID.setEditable(false);
        txtDescripcionPro.setEditable(false);
        txtPrecioUnitario.setEditable(false);
        txtPrecioDocena.setEditable(false);
        txtPrecioMayor.setEditable(false);
        txtExistencia.setEditable(false);
        cmbIDProveedor.setEditable(false);
        cmbIDTipoProducto.setEditable(false);
    }

    public void limpiarControles() {
        txtProductosID.clear();
        txtDescripcionPro.clear();
        txtPrecioUnitario.clear();
        txtPrecioDocena.clear();
        txtPrecioMayor.clear();
        txtExistencia.clear();
        tblProductos.getSelectionModel().getSelectedItem();
        cmbIDProveedor.getSelectionModel().getSelectedItem();
        cmbIDTipoProducto.getSelectionModel().getSelectedItem();
    }
}
