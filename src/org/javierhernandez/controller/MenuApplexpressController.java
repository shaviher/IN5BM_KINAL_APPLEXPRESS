package org.javierhernandez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuApplexpressController implements Initializable {

    private Main escenarioPrincipal;
    @FXML
    MenuItem btnMenuClientes;
    @FXML
    MenuItem btnProgra;
    @FXML
    MenuItem btnMenuCompras;
    @FXML
    MenuItem btnMenuProveedores;
    @FXML
    MenuItem btnMenuCargoEmpleados;
    @FXML
    MenuItem btnMenuTipoProducto;
    @FXML
    MenuItem btnMenuProductos;
    @FXML
    MenuItem btnMenuFactura;
    @FXML
    MenuItem btnMenuEmpleados;
    @FXML
    MenuItem btnMenuDF;
    @FXML
    MenuItem btnMenuDC;
    @FXML
    MenuItem btnMenuTelePro;
    @FXML
    MenuItem btnMenuEmalPro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void regresar() {
        escenarioPrincipal.menuPrincipalView();
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuClientes) {
            escenarioPrincipal.menuClientesView();
        } else if (event.getSource() == btnProgra) {
            escenarioPrincipal.ProgramadorView();
        } else if (event.getSource() == btnMenuCompras) {
            escenarioPrincipal.menuComprasView();
        } else if (event.getSource() == btnMenuProveedores) {
            escenarioPrincipal.menuProveedoresView();
        } else if (event.getSource() == btnMenuCargoEmpleados) {
            escenarioPrincipal.menuCargoEmpleadosView();
        } else if (event.getSource() == btnMenuTipoProducto) {
            escenarioPrincipal.menuTipoProductoView();
        } else if (event.getSource() == btnMenuProductos) {
            escenarioPrincipal.menuProductosView();
        } else if (event.getSource() == btnMenuFactura) {
            escenarioPrincipal.menuFacturaView();
        } else if (event.getSource() == btnMenuEmpleados) {
            escenarioPrincipal.menuEmpleadosView();
        } else if (event.getSource() == btnMenuDF) {
            escenarioPrincipal.menuDetalleCompraView();
        } else if (event.getSource() == btnMenuDC) {
            escenarioPrincipal.menuDetalleCompraView();
        } else if (event.getSource() == btnMenuTelePro) {
            escenarioPrincipal.menuTelefonoProveedorView();
        } else if (event.getSource() == btnMenuEmalPro) {
            escenarioPrincipal.menuEmailProveedorView();

        }
    }
}
