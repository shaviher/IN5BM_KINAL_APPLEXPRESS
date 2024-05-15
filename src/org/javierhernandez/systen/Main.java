package org.javierhernandez.systen;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.javierhernandez.controller.MenuApplexpressController;
import org.javierhernandez.controller.MenuClientesController;
import org.javierhernandez.controller.MenuProgramadorController;
import org.javierhernandez.controller.MenuCompraController;
import org.javierhernandez.controller.MenuProveedoresController;
import org.javierhernandez.controller.MenuCargoEmpleadoController;
import org.javierhernandez.controller.MenuTipoProductoController;
import org.javierhernandez.controller.MenuProductosController;
import org.javierhernandez.controller.MenuFacturaController;

public class Main extends Application {

    private Stage escenarioPrincipal;
    private Scene escena;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("APPLEXPRESS");
        menuPrincipalView();
        escenarioPrincipal.getIcons().add(new Image("/org/javierhernandez/imagen/LogoApplexpress.png"));
        escenarioPrincipal.show();
    }

    public Initializable cambiarEscena(String fxmlname, int width, int heigth) throws Exception {
        Initializable result;
        FXMLLoader loader = new FXMLLoader();

        InputStream file = Main.class.getResourceAsStream("/org/javierhernandez/view/" + fxmlname);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/org/javierhernandez/view/" + fxmlname));

        escena = new Scene((AnchorPane) loader.load(file), width, heigth);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();

        result = (Initializable) loader.getController();

        return result;
    }

    public void menuPrincipalView() {
        try {
            MenuApplexpressController menuPrincipalView = (MenuApplexpressController) cambiarEscena("MenuApplexpress.fxml", 1020, 587);
            menuPrincipalView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuClientesView() {
        try {
            MenuClientesController menuClientesView = (MenuClientesController) cambiarEscena("ClientesMenuView.fxml", 1020, 587);
            menuClientesView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ProgramadorView() {
        try {
            MenuProgramadorController ProgramadorView = (MenuProgramadorController) cambiarEscena("ProgramadorView.fxml", 1020, 587);
            ProgramadorView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void menuComprasView(){
        try{
            MenuCompraController menuComprasView = (MenuCompraController) cambiarEscena("ComprasMenuView.fxml",1020,587);
            menuComprasView.setEscenarioPrincipal(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void menuProveedoresView(){
        try{
            MenuProveedoresController menuProveedorView = (MenuProveedoresController) cambiarEscena("ProveedoresMenuView.fxml",1020,587);
            menuProveedorView.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void menuCargoEmpleadosView(){
        try{
            MenuCargoEmpleadoController menuCargoEmpleadosView = (MenuCargoEmpleadoController) cambiarEscena("CargoEmpleadosMenuView.fxml",1020,587);
            menuCargoEmpleadosView.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void menuTipoProductoView(){
        try{
            MenuTipoProductoController menuTipoProductoView = (MenuTipoProductoController) cambiarEscena("TipoProductoMenuView.fxml",1020,587);
            menuTipoProductoView.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void menuProductosView(){
        try{
            MenuProductosController menuProductosView = (MenuProductosController) cambiarEscena("ProductosMenuView.fxml",1020,587);
            menuProductosView.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void menuFacturaView(){
        try{
            MenuFacturaController menuFacturaView = (MenuFacturaController) cambiarEscena("FacturaMenuView.fxml",1020,587);
            menuFacturaView.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
