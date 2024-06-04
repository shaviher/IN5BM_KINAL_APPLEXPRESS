package org.javierhernandez.bean;

/**
 *
 * @author javih
 */
public class Compras {

    private int IDCompra;
    private String fechaDocumento;
    private String descripcion;
    private double totalDocumento;

    public Compras() {

    }

    public Compras(int IDCompra, String fechaDocumento, String descripcion, double totalDocumento) {
        this.IDCompra = IDCompra;
        this.fechaDocumento = fechaDocumento;
        this.descripcion = descripcion;
        this.totalDocumento = totalDocumento;
    }

    public int getIDCompra() {
        return IDCompra;
    }

    public void setIDCompra(int IDCompra) {
        this.IDCompra = IDCompra;
    }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotalDocumento() {
        return totalDocumento;
    }

    public void setTotalDocumento(double totalDocumento) {
        this.totalDocumento = totalDocumento;
    }

    @Override
    public String toString() {
        return IDCompra + " | " + descripcion;
    }
}
