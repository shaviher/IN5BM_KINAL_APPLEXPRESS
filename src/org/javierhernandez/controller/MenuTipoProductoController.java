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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.javierhernandez.bean.TipoProducto;
import org.javierhernandez.database.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuTipoProductoController implements Initializable{
        
        private Main escenarioPrincipal;

    private ObservableList<TipoProducto> listarTipoProducto;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;

    @FXML
    private Button btnAgregarTp;

    @FXML
    private Button btnEditarTp;

    @FXML
    private Button btnEliminarTp;

    @FXML
    private Button btnRegresarTp;

    @FXML
    private Button btnReportesTp;

    @FXML
    private TableColumn colDescripcionTc;

    @FXML
    private TableColumn colTipoCompraID;

    @FXML
    private TableView tblTipoCompras;

    @FXML
    private TextField txtDescripcionTp;

    @FXML
    private TextField txtIdTp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosTipoProducto();
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
        if (event.getSource() == btnRegresarTp) {
            escenarioPrincipal.menuPrincipalView();

        }
    }
    
    
    public void cargarDatosTipoProducto() {
        tblTipoCompras.setItems(getTipoProducto());
        colTipoCompraID.setCellValueFactory(new PropertyValueFactory<TipoProducto, Integer>("idTipoProducto"));
        colDescripcionTc.setCellValueFactory(new PropertyValueFactory<TipoProducto, String>("descripcion"));
    }

    public void seleccionarElementoTipoProducto() {
        txtIdTp.setText(String.valueOf(((TipoProducto) tblTipoCompras.getSelectionModel().getSelectedItem()).getIdTipoProducto()));
        txtDescripcionTp.setText(((TipoProducto) tblTipoCompras.getSelectionModel().getSelectedItem()).getDescripcion());
    }

    public ObservableList<TipoProducto> getTipoProducto() {
        ArrayList<TipoProducto> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ListarTipoProducto() }");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TipoProducto(resultado.getInt("idTipoProducto"),
                        resultado.getString("descripcion")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listarTipoProducto = FXCollections.observableList(lista);
    }

    public void guardarTipoProducto() {
        TipoProducto registro = new TipoProducto();
        registro.setIdTipoProducto(Integer.parseInt(txtIdTp.getText()));
        registro.setDescripcion(txtDescripcionTp.getText());
        

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarTipoProducto(?,?)}");
            procedimiento.setInt(1, registro.getIdTipoProducto());
            procedimiento.setString(2, registro.getDescripcion());

            procedimiento.execute();
            listarTipoProducto.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarTipoProducto() {
        TipoProducto registro = (TipoProducto) tblTipoCompras.getSelectionModel().getSelectedItem();
        registro.setIdTipoProducto(Integer.parseInt(txtIdTp.getText()));
        registro.setDescripcion(txtDescripcionTp.getText());
        

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_AgregarTipoProducto(?,?)}");
            procedimiento.setInt(1, registro.getIdTipoProducto());
            procedimiento.setString(2, registro.getDescripcion());

            procedimiento.execute();
            listarTipoProducto.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarTipoProducto() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarTp.setText("Guardar");
                btnReportesTp.setText("Cancelar");
                btnAgregarTp.setDisable(false);
                btnReportesTp.setDisable(false);
                btnEliminarTp.setDisable(true);
                btnEditarTp.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarTipoProducto();
                desactivarControles();
                btnAgregarTp.setText("Agregar");
                btnReportesTp.setText("Cancelar");
                btnEditarTp.setDisable(false);
                btnEliminarTp.setDisable(false);
                btnEliminarTp.setDisable(true);
                btnEditarTp.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void eliminarTipoProducto() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarTp.setText("Agregar");
                btnEliminarTp.setText("Eliminar");
                btnEditarTp.setDisable(false);
                btnReportesTp.setDisable(false);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblTipoCompras.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del Tipo De Producto",
                            "Eliminar Tipo de Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_EliminarTipoProducto(?)}");
                            procedimiento.setInt(1, ((TipoProducto) tblTipoCompras.getSelectionModel().getSelectedItem()).getIdTipoProducto());
                            listarTipoProducto.remove(tblTipoCompras.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un Tipo De Producto para eliminar");
                }
                break; // Agregué este break que faltaba
        }
    }

    public void editarTipoProducto() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblTipoCompras.getSelectionModel().getSelectedItem() != null) {
                    btnEditarTp.setText("Actualizar");
                    btnReportesTp.setText("Cancelar");
                    btnEditarTp.setDisable(false);
                    btnReportesTp.setDisable(false);
                    btnAgregarTp.setDisable(true);
                    btnEliminarTp.setDisable(true);
                    activarControles();
                    txtIdTp.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar un Tipo de Producto para editar");
                }
                break;
            case ACTUALIZAR:
                actualizarTipoProducto();
                btnEditarTp.setText("Actualizar");
                btnReportesTp.setText("Reporte");
                btnEditarTp.setDisable(false);
                btnReportesTp.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOpereciones = operaciones.NULL;
                cargarDatosTipoProducto();
                break;
        }
    }
    
    public void cancelarAccion() {
        limpiarControles();
        desactivarControles();
        btnAgregarTp.setText("Agregar");
        btnEditarTp.setText("Editar");
        btnEliminarTp.setText("Eliminar");
        btnRegresarTp.setText("Regresar");
        btnReportesTp.setText("Reportes");
        btnAgregarTp.setDisable(false);
        btnEditarTp.setDisable(false);
        btnEliminarTp.setDisable(false);
        btnReportesTp.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

    public void desactivarControles() {
        txtIdTp.setEditable(false);
        txtDescripcionTp.setEditable(false);
    }

    public void activarControles() {
        txtIdTp.setEditable(true);
        txtDescripcionTp.setEditable(true);
    }

    public void limpiarControles() {
        txtIdTp.clear();
        txtDescripcionTp.clear();
    }
    
}
