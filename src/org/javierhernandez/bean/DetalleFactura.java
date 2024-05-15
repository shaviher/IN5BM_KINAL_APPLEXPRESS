package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class DetalleFactura {
    private int IDDetalleFactura;
    private double precioUnitario;
    private int cantidad;
    private int IDDeFactura;
    private int IDProducto;
    
    public DetalleFactura(){
        
    }

    public DetalleFactura(int IDDetalleFactura, double precioUnitario, int cantidad, int IDDeFactura, int IDProducto) {
        this.IDDetalleFactura = IDDetalleFactura;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.IDDeFactura = IDDeFactura;
        this.IDProducto = IDProducto;
    }

    public int getIDDetalleFactura() {
        return IDDetalleFactura;
    }

    public void setIDDetalleFactura(int IDDetalleFactura) {
        this.IDDetalleFactura = IDDetalleFactura;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIDDeFactura() {
        return IDDeFactura;
    }

    public void setIDDeFactura(int IDDeFactura) {
        this.IDDeFactura = IDDeFactura;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }
}