package ar.jujuy.pov.modelo.dominio;
// Generated 03/12/2015 04:50:55 by Hibernate Tools 4.3.1

/**
 * Marca generated by hbm2java
 */
public class Marca  implements java.io.Serializable {


     private int idMarca;
     private String descripcion;
     private boolean estado;
     private String imagen;

    public Marca(int idMarca, String descripcion, boolean estado, String imagen) {
        this.idMarca = idMarca;
        this.descripcion = descripcion;
        this.estado = estado;
        this.imagen = imagen;
    }
     
    public Marca() {
    }

    public int getIdMarca() {
        return this.idMarca;
    }
    
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}


