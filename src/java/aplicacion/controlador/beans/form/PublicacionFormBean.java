/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.PublicacionBean;
import aplicacion.modelo.dominio.Autor;
import aplicacion.modelo.dominio.Clasificacion;
import aplicacion.modelo.dominio.Editorial;
import aplicacion.modelo.dominio.PubAut;
import aplicacion.modelo.dominio.PubCla;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Publicacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class PublicacionFormBean implements Serializable {

    @ManagedProperty(value = "#{publicacionBean}")
    private PublicacionBean publicacionBean;
    private List<PubAut> publicaciones;
    private Publicacion publicacion;
    private Editorial editorial;
    private Autor autor;
    private PubAut publicacionAutor;
    private Publicacion libroSeleccionado;
    private List<Publicacion> librosEncontrados;
    private String buscar;
    private List<PubAut> publicacionAprestar;
    private String CodigoDePublicacion;
   
    private Clasificacion clasificacion;
    

    private boolean dialogo = false;

    /**
     * Creates a new instance of PublicacionFormBean
     */
    public PublicacionFormBean() {
        autor = new Autor();
        editorial = new Editorial();
        publicacion = new Publicacion();
        publicacionAutor = new PubAut();
        libroSeleccionado = new Publicacion();
        librosEncontrados = new ArrayList<>();
    
    }

    public PublicacionBean getPublicacionBean() {
        return publicacionBean;
    }

    public void setPublicacionBean(PublicacionBean publicacionBean) {
        this.publicacionBean = publicacionBean;
    }

    public List<PubAut> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PubAut> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public PubAut getPublicacionAutor() {
        return publicacionAutor;
    }

    public void setPublicacionAutor(PubAut publicacionAutor) {
        this.publicacionAutor = publicacionAutor;
    }

    public Publicacion getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Publicacion libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public List<Publicacion> getLibrosEncontrados() {
        return librosEncontrados;
    }

    public void setLibrosEncontrados(List<Publicacion> librosEncontrados) {
        this.librosEncontrados = librosEncontrados;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public List<PubAut> getPublicacionAprestar() {
        return publicacionAprestar;
    }

    public void setPublicacionAprestar(List<PubAut> publicacionAprestar) {
        this.publicacionAprestar = publicacionAprestar;
    }

    public String getCodigoDePublicacion() {
        return CodigoDePublicacion;
    }

    public void setCodigoDePublicacion(String CodigoDePublicacion) {
        this.CodigoDePublicacion = CodigoDePublicacion;
    }


    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
    

    public void listarLibros() {

        this.publicaciones = publicacionBean.obtenerPublicaciones();
    }

    @PostConstruct
    public void init() {

        listarLibros();
        this.librosEncontrados = (List<Publicacion>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("libros");
    }

    public void altaDePublicacion() {
        try {
            publicacion.setEditorial(editorial);
            publicacion.setEstado(true);

            publicacionBean.altaDePublicaion(publicacion);
            publicacionAutor.setAutor(autor);
            publicacionAutor.setPublicacion(publicacion);
            publicacionAutor.setPaEstado(true);
            publicacionBean.altaDePublicaionAutor(publicacionAutor);
            init();
            ocultarDialogo();
            autor = new Autor();
            editorial = new Editorial();
            publicacion = new Publicacion();
            publicacionAutor = new PubAut();
            libroSeleccionado = new Publicacion();
        } catch (Exception e) {
            
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Codigo de libro ya existe", "Error Codigo de libro ya existe");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            
        }
    }

    public void mostrarDialogo() {
        this.dialogo = true;
    }

    public void ocultarDialogo() {
        this.dialogo = false;
    }

    public void seleccionarLibro(PubAut p) {
        this.libroSeleccionado = p.getPublicacion();
        mostrarDialogo();
    }

    public void modificarLibro() {
        publicacionBean.modificarPublicacion(libroSeleccionado);
        ocultarDialogo();
    }

    public void buscarLibro() throws IOException {
        this.librosEncontrados = publicacionBean.librosEncontrados(buscar);
        if (this.librosEncontrados.isEmpty()) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encotro coincidencias", "No se encotro coincidencias");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {

            FacesContext.getCurrentInstance().getExternalContext().redirect("ResultadosBusqueda.xhtml");
        }
    }
    public void buscarPublicacionAprestar(){
    
        this.publicacionAprestar = publicacionBean.buscarPublicacionAprestar(CodigoDePublicacion);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("publicacion", publicacionAprestar.get(0).getPublicacion());
        if(this.publicacionAprestar.isEmpty()){
              FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encotro coincidencias", "No se encotro coincidencias");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
      mostrarDialogo();
    }
}
 public String getClasificacion (Publicacion p){
    
     String cla = publicacionBean.obtenerClasificacion(p).getClasificacion().getDescripcion();
     return cla;
 }
}