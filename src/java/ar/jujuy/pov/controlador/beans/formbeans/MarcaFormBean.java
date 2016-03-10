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
    private MarcaDAO mdao;

    private boolean imagen;

    public MarcaFormBean() {

        super();
        mdao = new MarcaDAOImpl();
        tabla = mdao.getAll();
    }

    //    Getter y Setter de los atributos
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

}
