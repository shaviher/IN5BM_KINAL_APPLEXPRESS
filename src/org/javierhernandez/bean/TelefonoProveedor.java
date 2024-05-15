package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class TelefonoProveedor {
    private int IDTelefonoProveedor;
    private String numeroPincipal;
    private String numeroSecundario;
    private String observaciones;
    private int IDProveedores;
    
    public TelefonoProveedor(){
        
    }

    public TelefonoProveedor(int IDTelefonoProveedor, String numeroPincipal, String numeroSecundario, String observaciones, int IDProveedores) {
        this.IDTelefonoProveedor = IDTelefonoProveedor;
        this.numeroPincipal = numeroPincipal;
        this.numeroSecundario = numeroSecundario;
        this.observaciones = observaciones;
        this.IDProveedores = IDProveedores;
    }

    public int getIDTelefonoProveedor() {
        return IDTelefonoProveedor;
    }

    public void setIDTelefonoProveedor(int IDTelefonoProveedor) {
        this.IDTelefonoProveedor = IDTelefonoProveedor;
    }

    public String getNumeroPincipal() {
        return numeroPincipal;
    }

    public void setNumeroPincipal(String numeroPincipal) {
        this.numeroPincipal = numeroPincipal;
    }

    public String getNumeroSecundario() {
        return numeroSecundario;
    }

    public void setNumeroSecundario(String numeroSecundario) {
        this.numeroSecundario = numeroSecundario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIDProveedores() {
        return IDProveedores;
    }

    public void setIDProveedores(int IDProveedores) {
        this.IDProveedores = IDProveedores;
    }
}