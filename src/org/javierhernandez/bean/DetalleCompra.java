package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class DetalleCompra {
    private int IDDetalleCompra;
    private Double costoUnitario; //*atributo arreglado*// 
    private int cantidad; //*atributo arreglado*// 
    private int IDProducto; //*atributo arreglado*// 
    private int IDCompra; //*atributo arreglado*// 
    
    public DetalleCompra(){
        
    }

    //*Correción de Constructor*/

    public DetalleCompra(int IDDetalleCompra, Double costoUnitario, int cantidad, int IDProducto, int IDCompra) {
        this.IDDetalleCompra = IDDetalleCompra;
        this.costoUnitario = costoUnitario;
        this.cantidad = cantidad;
        this.IDProducto = IDProducto;
        this.IDCompra = IDCompra;
    }

    public int getIDDetalleCompra() {
        return IDDetalleCompra;
    }

    public void setIDDetalleCompra(int IDDetalleCompra) {
        this.IDDetalleCompra = IDDetalleCompra;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getIDCompra() {
        return IDCompra;
    }

    public void setIDCompra(int IDCompra) {
        this.IDCompra = IDCompra;
    }
    
    
}