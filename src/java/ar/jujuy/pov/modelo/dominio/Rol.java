package ar.jujuy.pov.modelo.dominio;
// Generated 03/12/2015 04:50:55 by Hibernate Tools 4.3.1

/**
 * Rol generated by hbm2java
 */
public class Rol  implements java.io.Serializable {

     private short idRol;
     private String descripcion;

    public Rol() {
    }

    public short getIdRol() {
        return this.idRol;
    }
    
    public void setIdRol(short idRol) {
        this.idRol = idRol;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}


