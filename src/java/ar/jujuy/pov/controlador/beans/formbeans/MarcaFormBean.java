package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.controlador.beans.MarcaBean;
import ar.jujuy.pov.dao.MarcaDAO;
import ar.jujuy.pov.dao.impl.MarcaDAOImpl;
import ar.jujuy.pov.modelo.dominio.Marca;
import java.io.File;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class MarcaFormBean implements java.io.Serializable {

    @ManagedProperty(value = "#{marcaBean}")
    private MarcaBean mb;
    private List<Marca> tabla;
    private List<Marca> tablaHabilitado;
    private MarcaDAO mdao;

    private boolean imagen;

    private Marca seleccion;
    private Boolean estado;
    private String descripcion;
    private int cantidad;

    public MarcaFormBean() {

        super();
        mdao = new MarcaDAOImpl();
        
        tablaHabilitado=mdao.filtrar("", true, 0) ;
        tabla = mdao.getAll();
    }

    //    Getter y Setter de los atributos

    public Marca getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Marca seleccion) {
        this.seleccion = seleccion;
    }
    
    public List<Marca> getTablaHabilitado() {
        return tablaHabilitado;
    }

    public void setTablaHabilitado(List<Marca> tablaHabilitado) {
        this.tablaHabilitado = tablaHabilitado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public MarcaBean getMb() {
        return mb;
    }

    public void setMb(MarcaBean mb) {
        this.mb = mb;
    }

    public List<Marca> getTabla() {
        return tabla;
    }

    public void setTabla(List<Marca> tabla) {
        this.tabla = tabla;
    }

    public boolean isImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }

    //    Metodos de la clase
    public void confirmarNuevoShow() {
        RequestContext.getCurrentInstance().execute("PF('widNuevoConfirmar').show()");
    }

    public void confirmarModificarShow() {
        RequestContext.getCurrentInstance().execute("PF('widConfirmarModificar').show()");
    }

    public String direccionImagen(Marca m) {
        if (m.getImagen() != null) {
            File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "galeria" + File.separator + "marca" + File.separator + m.getImagen());
            if (f.exists()) {
                return "/galeria/marca/" + m.getImagen();
            }
        }
        return "/resources/img/sin_imagen.png";
    }

    public void limpiarNuevo() {
        mb = new MarcaBean();
        RequestContext.getCurrentInstance().execute("PF('widNuevaMarca').show()");
    }

    public void cargarMarca(Marca m) {
        mb = new MarcaBean();
        mb.setMarca(new Marca(m.getIdMarca(), m.getDescripcion(), m.isEstado(), m.getImagen()));
        RequestContext.getCurrentInstance().execute("PF('widModificarMarca').show()");
    }

    public void cargarMarcaImagen(Marca m) {
        FacesContext context = FacesContext.getCurrentInstance();
        ImagenFormbean formbean = context.getApplication().evaluateExpressionGet(context, "#{imagenFormbean}", ImagenFormbean.class);
        formbean.setImagen("");
        mb = new MarcaBean();
        mb.setMarca(new Marca(m.getIdMarca(), m.getDescripcion(), m.isEstado(), m.getImagen()));
        RequestContext.getCurrentInstance().execute("PF('widSubirImagen').show()");
    }

    public void guardar() {

        mb.getMarca().setEstado(true);
        mdao.alta(mb.getMarca());
        tabla = mdao.getAll();

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca guardada.", "Exito");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widNuevaMarca').hide()");

    }

    public void modificar() {
        mdao.modificar(mb.getMarca());
        tabla = mdao.getAll();

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marca modificada.", "Exito");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('widModificarMarca').hide()");
        RequestContext.getCurrentInstance().execute("PF('widConfirmarModificar').hide()");
    }

    public void habilitar(Marca m) {
        mdao.estado(m, true);
    }

    public void deshabilitar(Marca m) {
        mdao.estado(m, false);
    }

    public void filtrar() {
        tabla = mdao.filtrar(descripcion, estado, cantidad);
    }
    
    public void filtrarHabilitado() {
        tablaHabilitado = mdao.filtrar(descripcion, true, cantidad);
    }
    
    public void actualizarTablaHabilitado(){
        tablaHabilitado=mdao.filtrar("", true, 0);
        descripcion="";
    }
}
