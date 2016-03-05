package ar.jujuy.pov.controlador.beans;

import ar.jujuy.pov.modelo.dominio.DetalleUnidad;
import ar.jujuy.pov.modelo.dominio.Marca;
import ar.jujuy.pov.modelo.dominio.Producto;
import ar.jujuy.pov.modelo.dominio.TipoProducto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProductoBean implements java.io.Serializable {
    
    private Producto producto;

    public ProductoBean() {
        producto= new Producto();
        producto.setMarca(new Marca());
        producto.setDetalleUnidad(new DetalleUnidad());
        producto.setTipoProducto(new TipoProducto());
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
   
    
}
