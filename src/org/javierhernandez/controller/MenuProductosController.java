/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.javierhernandez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuProductosController implements Initializable {

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
    private TableColumn colDesP;

    @FXML
    private TableColumn colExiP;

    @FXML
    private TableColumn colIDP;

    @FXML
    private TableColumn colIDpr;

    @FXML
    private TableColumn colIDtp;

    @FXML
    private TableColumn colPreDP;

    @FXML
    private TableColumn colPreMP;

    @FXML
    private TableColumn colPreUp;

    @FXML
    private TableView tblProductos;

    @FXML
    private TextField txtApellidoCliente;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private TextField txtNitCliente;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtProductosID;

    @FXML
    private TextField txtTelefonoCliente;

    private Main escenarioPrincipal;

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
        // TODO
    }

    public void activarControles() {
        txtApellidoCliente.setEditable(true);
        txtDireccionCliente.setEditable(true);
        txtNitCliente.setEditable(true);
        txtNombreCliente.setEditable(true);
        txtProductosID.setEditable(true);
        txtTelefonoCliente.setEditable(true);
        cmbIDProveedor.setEditable(true);
        cmbIDTipoProducto.setEditable(true);
    }

    public void desactivarControles() {
        txtApellidoCliente.setEditable(false);
        txtDireccionCliente.setEditable(false);
        txtNitCliente.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtProductosID.setEditable(false);
        txtTelefonoCliente.setEditable(false);
        cmbIDProveedor.setEditable(false);
        cmbIDTipoProducto.setEditable(false);
    }

    public void limpiarControles() {
        txtApellidoCliente.clear();
        txtDireccionCliente.clear();
        txtNitCliente.clear();
        txtNombreCliente.clear();
        txtProductosID.clear();
        txtTelefonoCliente.clear();
        tblProductos.getSelectionModel().getSelectedItem();
        cmbIDProveedor.getSelectionModel().getSelectedItem();
        cmbIDTipoProducto.getSelectionModel().getSelectedItem();
    }
}
