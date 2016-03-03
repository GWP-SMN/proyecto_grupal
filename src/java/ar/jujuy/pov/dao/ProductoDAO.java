package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.Producto;
import java.util.List;

public interface ProductoDAO {

    public List<Producto> getAllTrue();
    
    public List<Producto> getAll();

    public void alta(Producto p);

    public void modificar(Producto p);

    public void eliminar(Producto p);
    
    public void estado(Producto p,boolean estado);
}
