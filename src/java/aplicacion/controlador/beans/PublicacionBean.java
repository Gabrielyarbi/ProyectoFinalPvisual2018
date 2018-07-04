/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Publicacion;
import java.util.List;

import aplicacion.dao.imp.ram.PublicacionDAOImp;
import aplicacion.modelo.dominio.Clasificacion;
import java.io.Serializable;
import aplicacion.modelo.dominio.PubAut;
import aplicacion.modelo.dominio.PubCla;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class PublicacionBean implements Serializable {

    private Publicacion publicacion;

    /**
     * Creates a new instance of PublicaionBean
     */
    public PublicacionBean() {
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<PubAut> obtenerPublicaciones() {
        PublicacionDAOImp publicacionDao = new PublicacionDAOImp();
        return publicacionDao.obtenerLista();
    }

    public void altaDePublicaion(Publicacion pub) {
        PublicacionDAOImp pubDAO = new PublicacionDAOImp();
        pubDAO.nuevaPublicaion(pub);

    }

    public void altaDePublicaionAutor(PubAut pubAutor) {
        PublicacionDAOImp pubDAO = new PublicacionDAOImp();
        pubDAO.nuevaPublicaionAutor(pubAutor);

    }

    public void modificarPublicacion(Publicacion pu) {
        PublicacionDAOImp pubDAO = new PublicacionDAOImp();
        pubDAO.modificarPublicacion(pu);

    }

    public List<Publicacion> librosEncontrados(String palabra) {
        PublicacionDAOImp pubDAO = new PublicacionDAOImp();
        return pubDAO.buscarLibro(palabra);
    }
public List<PubAut> buscarPublicacionAprestar(String codigo){
    PublicacionDAOImp pubDAO=new PublicacionDAOImp();
    return pubDAO.LibroAprestar(codigo);
}
public PubCla obtenerClasificacion (Publicacion pub){
    PublicacionDAOImp pubDAO=new PublicacionDAOImp();
    return pubDAO.obtenerClasificacion(pub);
}

}
