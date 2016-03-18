/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.TipoProductoBean;
import ar.jujuy.pov.dao.TipoProductoDAO;
import ar.jujuy.pov.dao.impl.TipoProductoDAOImpl;
import ar.jujuy.pov.modelo.dominio.TipoProducto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class TipoProductoFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{tipoProductoBean}")
    private TipoProductoBean tpb;
    private List<TipoProducto> tabla;
    private List<TipoProducto> tablaHabilitado;
    private TipoProductoDAO tpdao;

    private TipoProducto seleccion;
    private String descripcion;
    private Boolean estado;

    public TipoProductoFormBean() {
        super();
        tpdao = new TipoProductoDAOImpl();
        tabla = tpdao.getAll();
    }

    //    Getter y Setter de los atributos

    public TipoProducto getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(TipoProducto seleccion) {
        this.seleccion = seleccion;
    }
    
    public List<TipoProducto> getTablaHabilitado() {
        return tablaHabilitado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setTablaHabilitado(List<TipoProducto> tablaHabilitado) {
        this.tablaHabilitado = tablaHabilitado;
    }

    public TipoProductoBean getTpb() {
        return tpb;
    }

    public void setTpb(TipoProductoBean tpb) {
        this.tpb = tpb;
    }

    public List<TipoProducto> getTabla() {
        return tabla;
    }

    public void setTabla(List<TipoProducto> tabla) {
        this.tabla = tabla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //    Metodos de la clase
    public void limpiarNuevo() {
        tpb.setTipoProducto(new TipoProducto());
        RequestContext.getCurrentInstance().execute("PF('widNuevoTipoProducto').show()");
    }

    public void modificar() {
        tpdao.modificar(tpb.getTipoProducto());
        tabla = tpdao.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Operación concretada."));
        RequestContext.getCurrentInstance().execute("PF('widEditarTP').hide()");
    }

    public void guardar() {

        tpb.getTipoProducto().setEstado(true);
        tpdao.alta(tpb.getTipoProducto());
        tabla = tpdao.getAll();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Producto guardado con exito.", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widNuevoTipoProducto').hide()");
        RequestContext.getCurrentInstance().execute("PF('widConfirmarAlta').hide()");
    }

    public void mostrarConfirmacionAlta() {
        RequestContext.getCurrentInstance().execute("PF('widConfirmarAlta').show()");
    }

    public void habilitado(TipoProducto tp) {
        tp.setEstado(true);
        tpdao.modificar(tp);
    }

    public void desHabilitado(TipoProducto tp) {
        tp.setEstado(false);
        tpdao.modificar(tp);
    }

    public void filtrar() {
        tabla = tpdao.filtrar(descripcion, estado);
    }

    public void filtrarHabilitado() {
        tablaHabilitado = tpdao.filtrar(descripcion, true);
    }

    public void actualizarHabilitado() {
        descripcion = "";
        tablaHabilitado = tpdao.filtrar("", true);
    }
}
