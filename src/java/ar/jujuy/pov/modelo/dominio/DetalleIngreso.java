package ar.jujuy.pov.modelo.dominio;
// Generated 03/12/2015 04:50:55 by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * DetalleIngreso generated by hbm2java
 */
public class DetalleIngreso implements java.io.Serializable{

    private long idDetalleIngreso;
    private EncabezadoIngreso encabezadoIngreso;
    private Producto producto;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;

    public DetalleIngreso() {
    }

    public DetalleIngreso(EncabezadoIngreso encabezadoIngreso, Producto producto, BigDecimal cantidad, BigDecimal precioUnitario) {
        this.encabezadoIngreso = encabezadoIngreso;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public long getIdDetalleIngreso() {
        return this.idDetalleIngreso;
    }

    public void setIdDetalleIngreso(long idDetalleIngreso) {
        this.idDetalleIngreso = idDetalleIngreso;
    }

    public EncabezadoIngreso getEncabezadoIngreso() {
        return this.encabezadoIngreso;
    }

    public void setEncabezadoIngreso(EncabezadoIngreso encabezadoIngreso) {
        this.encabezadoIngreso = encabezadoIngreso;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

}
