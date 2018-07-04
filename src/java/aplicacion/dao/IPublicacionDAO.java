/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Clasificacion;
import aplicacion.modelo.dominio.PubAut;
import aplicacion.modelo.dominio.Publicacion;
import aplicacion.modelo.dominio.PubCla;
import java.util.List;

/**
 *
 * @author Gabriel.Y
 */
public interface IPublicacionDAO {
    public List<PubAut> obtenerLista();
    public void filtrarPublicacion();
    public void nuevaPublicaion(Publicacion p);
    public void nuevaPublicaionAutor (PubAut publicacionAutor);
    public void modificarPublicacion (Publicacion p);
    public List<Publicacion> buscarLibro (String busqueda);
    public List<PubAut> LibroAprestar (String codigo);
    public PubCla obtenerClasificacion (Publicacion p);
 
    
}
