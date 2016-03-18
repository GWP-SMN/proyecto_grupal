package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.ProveedorBean;
import ar.jujuy.pov.dao.ProveedorDAO;
import ar.jujuy.pov.dao.impl.ProveedorDAOImpl;
import ar.jujuy.pov.modelo.dominio.Proveedor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ProveedorFormBean implements java.io.Serializable{
    
    @ManagedProperty(value = "#{proveedorBean}")
    private ProveedorBean proveedorBean;
    private Proveedor proveedor;
    private List<Proveedor> tablaProveedro;
    private final ProveedorDAO pdao;

    public ProveedorFormBean() {
        super();
        pdao=new ProveedorDAOImpl();
        tablaProveedro=pdao.getAll();
    }
//    Getter y Setter
    public ProveedorBean getProveedorBean() {
        return proveedorBean;
    }

    public void setProveedorBean(ProveedorBean proveedorBean) {
        this.proveedorBean = proveedorBean;
    }

    public List<Proveedor> getTablaProveedro() {
        return tablaProveedro;
    }

    public void setTablaProveedro(List<Proveedor> tablaProveedro) {
        this.tablaProveedro = tablaProveedro;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public void habilitar(Proveedor p) {
        pdao.estado(p, true);
    }

    public void deshabilitar(Proveedor p) {
        pdao.estado(p, false);
    }
    
    
    public void cargarProveedor(Proveedor p) {
        proveedorBean = new ProveedorBean();
        proveedorBean.setProveedor(new Proveedor(p.getIdProveedor(), p.getDescripcion(), p.isEstado(),p.getCuit()));
        RequestContext.getCurrentInstance().execute("PF('widModificarProveedor').show()");
    }

    public void confirmarModificarShow() {
        RequestContext.getCurrentInstance().execute("PF('widConfirmarModificar').show()");
    }
    
    public void modificar() {
        pdao.modificar(proveedorBean.getProveedor());
        tablaProveedro = pdao.getAll();

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor modificado.", "Exito");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widModificarProveedor').hide()");
        RequestContext.getCurrentInstance().execute("PF('widConfirmarModificar').hide()");
    }

    
    public void confirmarNuevoShow() {
        RequestContext.getCurrentInstance().execute("PF('widNuevoConfirmar').show()");
    }
    
    public void guardar() {

        proveedorBean.getProveedor().setEstado(true);
        pdao.alta(proveedorBean.getProveedor());
        tablaProveedro = pdao.getAll();

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor guardado.", "Exito");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widNuevoProveedor').hide()");

    }
    
    public void limpiarNuevo() {
        proveedorBean = new ProveedorBean();
        RequestContext.getCurrentInstance().execute("PF('widNuevoProveedor').show()");
    }
}
