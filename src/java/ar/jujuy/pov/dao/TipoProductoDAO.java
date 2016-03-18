package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.TipoProducto;
import java.util.List;

public interface TipoProductoDAO {

    public List<TipoProducto> getAll();

    public TipoProducto getId(Integer idTipoProducto);

    public void alta(TipoProducto tp);

    public void modificar(TipoProducto tp);

    public void eliminar(TipoProducto tp);
    
    public List<TipoProducto> filtrar(String descripcion,Boolean estado);
}
