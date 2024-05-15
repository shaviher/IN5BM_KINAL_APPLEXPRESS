package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class Factura {
    private int IDDeFactura;
    private String estado;
    private double totalFactura;
    private String fechaFactura;
    private int IDCliente;
    private int IDEmpleado;
    
    public Factura(){
        
    }

    public Factura(int IDDeFactura, String estado, double totalFactura, String fechaFactura, int IDCliente, int IDEmpleado) {
        this.IDDeFactura = IDDeFactura;
        this.estado = estado;
        this.totalFactura = totalFactura;
        this.fechaFactura = fechaFactura;
        this.IDCliente = IDCliente;
        this.IDEmpleado = IDEmpleado;
    }

    public int getIDDeFactura() {
        return IDDeFactura;
    }

    public void setIDDeFactura(int IDDeFactura) {
        this.IDDeFactura = IDDeFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public int getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(int IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }
}