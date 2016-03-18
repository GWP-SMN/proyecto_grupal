package ar.jujuy.pov.dao;
// Generated 27/11/2015 23:48:37 by Hibernate Tools 4.3.1

import ar.jujuy.pov.modelo.dominio.Marca;
import java.util.List;

public interface MarcaDAO {

    public List<Marca> getAll();

    public void alta(Marca m);

    public void modificar(Marca m);

    public void eliminar(Marca m);
    
    public void estado(Marca m,boolean estado);
    
    public List<Marca> filtrar(String descripcion,Boolean estado,int cantidad);
}
