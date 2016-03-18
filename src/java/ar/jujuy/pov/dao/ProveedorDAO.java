package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.Proveedor;
import java.util.List;

public interface ProveedorDAO {

    public List<Proveedor> getAll();

    public void alta(Proveedor p);

    public void modificar(Proveedor p);

    public void eliminar();
    
    public void estado(Proveedor p,boolean estado);
}
