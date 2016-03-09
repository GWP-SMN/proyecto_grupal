/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.controlador.beans.formbeans;

import ar.jujuy.pov.dao.UsuarioDAO;
import ar.jujuy.pov.dao.impl.UsuarioDAOImpl;
import ar.jujuy.pov.modelo.dominio.Marca;
import ar.jujuy.pov.modelo.dominio.Usuario;
import java.io.File;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class ImagenFormbean implements java.io.Serializable{

    private CroppedImage imageRecortada;
    private UploadedFile uploadedFile;
    
    private String imagen;
    private Part uploadedImagen;
    
    public ImagenFormbean() {
        super();
    }
//    getter y setter de los atributos

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public CroppedImage getImageRecortada() {
        return imageRecortada;
    }

    public void setImageRecortada(CroppedImage imageRecortada) {
        this.imageRecortada = imageRecortada;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Part getUploadedImagen() {
        return uploadedImagen;
    }

    public void setUploadedImagen(Part uploadedImagen) {
        this.uploadedImagen = uploadedImagen;
    }
    

//    metodos de 
    public void subirImagen() {
        if (uploadedFile != null) {
            if (!uploadedFile.getFileName().equals("")) {
                Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
                imagen = usuario.getIdUsuario() + extencion(uploadedFile.getContentType());
                String direccion = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "temp" + File.separator + imagen;
                FileImageOutputStream imageOutput;
                try {
                    imageOutput = new FileImageOutputStream(new File(direccion));
                    imageOutput.write(uploadedFile.getContents(), 0, uploadedFile.getContents().length);
                    imageOutput.close();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Exito!", "Recorte finalizado."));
                } catch (IOException e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "No se pudo cargar el archivo."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Error!", "Debe seleccionar un archivo."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Error!", "Debe seleccionar un archivo."));
        }
    }

    public void guardarImagen() {
        if (imageRecortada != null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String pathImagenTemp = servletContext.getRealPath("") + File.separator + "galeria" + File.separator + "usuario" + File.separator + imagen;
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(pathImagenTemp));
                imageOutput.write(imageRecortada.getBytes(), 0, imageRecortada.getBytes().length);
                imageOutput.close();

                Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogueado");
                usuario.setImagen(imagen);

                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                usuarioDAO.modificar(usuario);

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogueado", usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Exito!", "Recorte finalizado."));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar."));
            }

        }
    }
    
    public void subirImagenMarca(Marca m){
        
        
    }

    private String extencion(String tipo) {
        return "." + tipo.substring(tipo.indexOf("/") + 1, tipo.length());
    }
    
    
}
