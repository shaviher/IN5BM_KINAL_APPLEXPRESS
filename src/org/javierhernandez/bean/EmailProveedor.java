package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class EmailProveedor {

    private int IDEmailProveedor;
    private String emailproveedor;
    private String descripcion;
    private int IDProveedores;
    
    public EmailProveedor(){
        
    }

    public EmailProveedor(int IDEmailProveedor, String emailproveedor, String descripcion, int IDProveedores) {
        this.IDEmailProveedor = IDEmailProveedor;
        this.emailproveedor = emailproveedor;
        this.descripcion = descripcion;
        this.IDProveedores = IDProveedores;
    }

    public int getIDEmailProveedor() {
        return IDEmailProveedor;
    }

    public void setIDEmailProveedor(int IDEmailProveedor) {
        this.IDEmailProveedor = IDEmailProveedor;
    }

    public String getEmailproveedor() {
        return emailproveedor;
    }

    public void setEmailproveedor(String emailproveedor) {
        this.emailproveedor = emailproveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIDProveedores() {
        return IDProveedores;
    }

    public void setIDProveedores(int IDProveedores) {
        this.IDProveedores = IDProveedores;
    }
    
    
    
}
