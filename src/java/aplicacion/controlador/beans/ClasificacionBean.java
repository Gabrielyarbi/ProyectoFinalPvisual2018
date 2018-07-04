/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.dao.imp.ram.ClasificacionDAOImp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Clasificacion;
import java.util.List;
/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class ClasificacionBean {
private Clasificacion clasificacion;
    /**
     * Creates a new instance of ClasificacionBean
     */
    public ClasificacionBean() {
        
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
    public List<Clasificacion> listarClasificaciones (){
ClasificacionDAOImp clasDAO=new ClasificacionDAOImp();
    return clasDAO.listarClasificaciones();
}
}
