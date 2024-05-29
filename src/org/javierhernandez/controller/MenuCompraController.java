package org.javierhernandez.controller;

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.javierhernandez.bean.Compras;
import org.javierhernandez.db.Conexion;
import org.javierhernandez.systen.Main;

/**
 * FXML Controller class
 *
 * @author javih
 */
public class MenuCompraController implements Initializable {

    private Main escenarioPrincipal;
    private ObservableList<Compras> listaCompras;

    public enum operaciones {
        AGREGAR, EDITAR, ACTUALIZAR, ELIMINAR, CANCERLAR, NULL
    }

    private operaciones tipoDeOpereciones = operaciones.NULL;

    @FXML
    private Button btnAgregarCo;

    @FXML
    private Button btnEditarCo;

    @FXML
    private Button btnEliminarCo;

    @FXML
    private Button btnRegresarCo;

    @FXML
    private Button btnReportesCo;

    @FXML
    private TableColumn colIDC;

    @FXML
    private TableColumn coldescripcionC;

    @FXML
    private TableColumn colfechaC;

    @FXML
    private TableColumn coltotalDocumentoC;

    @FXML
    private TableView tblCompras;

    @FXML
    private TextField txtComprasID;

    @FXML
    private TextField txtDescripcionCompras;

    @FXML
    private DatePicker txtFechaCompras;

    @FXML
    private TextField txtTotalCompras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosCompras();
    }

    public Main getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresarCo) {
            escenarioPrincipal.menuPrincipalView();

        }
    }

    public void cargarDatosCompras() {
        tblCompras.setItems(getCompras());
        colIDC.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("IDCompra"));
        colfechaC.setCellValueFactory(new PropertyValueFactory<Compras, String>("fechaDocumento"));
        coldescripcionC.setCellValueFactory(new PropertyValueFactory<Compras, String>("descripcion"));
        coltotalDocumentoC.setCellValueFactory(new PropertyValueFactory<Compras, Double>("totalDocumento"));
    }

    public void seleccionarElementoCompras() {
        txtComprasID.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getIDCompra()));
        txtFechaCompras.setValue(LocalDate.parse(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getFechaDocumento()));
        txtDescripcionCompras.setText(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getDescripcion());
        txtTotalCompras.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getTotalDocumento()));
    }

    public ObservableList<Compras> getCompras() {
        ArrayList<Compras> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ListarCompras() }");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Compras(resultado.getInt("IDCompra"),
                        resultado.getString("fechaDocumento"),
                        resultado.getString("descripcion"),
                        resultado.getDouble("totalDocumento")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompras = FXCollections.observableList(lista);
    }

    public void guardarCompras() {
        Compras registro = new Compras();

        registro.setIDCompra(Integer.parseInt(txtComprasID.getText()));
        registro.setFechaDocumento(txtFechaCompras.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        registro.setDescripcion(txtDescripcionCompras.getText());
        registro.setTotalDocumento(parseDouble(txtTotalCompras.getText()));

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_AgregarCompras(?,?,?,?) }");
            procedimiento.setInt(1, registro.getIDCompra());
            procedimiento.setString(2, registro.getFechaDocumento());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getTotalDocumento());

            procedimiento.execute();
            listaCompras.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ActualizarCompras() {
        Compras registro = (Compras) tblCompras.getSelectionModel().getSelectedItem();
        registro.setIDCompra(Integer.parseInt(txtComprasID.getText()));
        registro.setFechaDocumento(txtFechaCompras.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        registro.setDescripcion(txtDescripcionCompras.getText());
        registro.setTotalDocumento(parseDouble(txtTotalCompras.getText()));

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ CALL sp_ActualizarCompra(?,?,?,?) }");
            procedimiento.setInt(1, registro.getIDCompra());
            procedimiento.setString(2, registro.getFechaDocumento());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setDouble(4, registro.getTotalDocumento());

            procedimiento.execute();
            listaCompras.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AgregarCompras() {
        switch (tipoDeOpereciones) {
            case NULL:
                activarControles();
                btnAgregarCo.setText("Agregar");
                btnReportesCo.setText("Cancelar");
                btnAgregarCo.setDisable(false);
                btnReportesCo.setDisable(false);
                btnEliminarCo.setDisable(true);
                btnEditarCo.setDisable(true);
                tipoDeOpereciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardarCompras();
                desactivarControles();
                btnAgregarCo.setText("Agregar");
                btnReportesCo.setText("Cancelar");
                btnAgregarCo.setDisable(false);
                btnReportesCo.setDisable(false);
                btnEliminarCo.setDisable(true);
                btnEditarCo.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
        }
    }

    public void editarCompras() {
        switch (tipoDeOpereciones) {
            case NULL:
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    btnEditarCo.setText("Actualizar");
                    btnReportesCo.setText("Cancelar");
                    btnEditarCo.setDisable(false);
                    btnReportesCo.setDisable(false);
                    btnEliminarCo.setDisable(true);
                    btnAgregarCo.setDisable(true);
                    activarControles();
                    txtComprasID.setEditable(false);
                    tipoDeOpereciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showConfirmDialog(null, "Debe seleccionar una compra para editar");
                }
                break;
            case ACTUALIZAR:
                ActualizarCompras();
                btnEditarCo.setText("Actualizar");
                btnReportesCo.setText("Cancelar");
                btnEditarCo.setDisable(false);
                btnReportesCo.setDisable(false);
                btnEliminarCo.setDisable(true);
                btnAgregarCo.setDisable(true);
                desactivarControles();
                limpiarControles();
                tipoDeOpereciones = operaciones.NULL;
                cargarDatosCompras();
                break;
        }
    }

    public void EliminarCompra() {
        switch (tipoDeOpereciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEliminarCo.setText("Eliminar");
                btnEliminarCo.setDisable(false);
                btnEditarCo.setDisable(true);
                btnReportesCo.setDisable(true);
                btnAgregarCo.setDisable(true);
                tipoDeOpereciones = operaciones.NULL;
                break;
            default:
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro",
                            "Eliminar Compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{CALL sp_EliminarCompra(?)}");
                            procedimiento.setInt(1, ((Compras) tblCompras.getSelectionModel().getSelectedItem()).getIDCompra());
                            listaCompras.remove(tblCompras.getSelectionModel().getSelectedItem());
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
        btnAgregarCo.setText("Agregar");
        btnEditarCo.setText("Editar");
        btnEliminarCo.setText("Eliminar");
        btnRegresarCo.setText("Regresar");
        btnReportesCo.setText("Reportes");
        btnAgregarCo.setDisable(false);
        btnEditarCo.setDisable(false);
        btnEliminarCo.setDisable(false);
        btnReportesCo.setDisable(false);
        tipoDeOpereciones = operaciones.NULL;
    }

    public void desactivarControles() {
        txtComprasID.setEditable(false);
        txtFechaCompras.setEditable(false);
        txtDescripcionCompras.setEditable(false);
        txtTotalCompras.setEditable(false);
    }

    public void activarControles() {
        txtComprasID.setEditable(true);
        txtFechaCompras.setEditable(true);
        txtDescripcionCompras.setEditable(true);
        txtTotalCompras.setEditable(true);
    }

    public void limpiarControles() {
        txtComprasID.clear();
        txtFechaCompras.setValue(null);
        txtDescripcionCompras.clear();
        txtTotalCompras.clear();
    }

}
