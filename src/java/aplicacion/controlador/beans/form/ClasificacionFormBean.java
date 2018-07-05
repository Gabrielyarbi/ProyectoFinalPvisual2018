/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.ClasificacionBean;
import aplicacion.modelo.dominio.Clasificacion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class ClasificacionFormBean implements Serializable{
  @ManagedProperty(value = "#{clasificacionBean}")
    private ClasificacionBean clasificacionBean;
    private List<Clasificacion> listaDeClasificaciones;
    private Clasificacion clasificacion;
    private boolean dialogo=false;
  /**
     * Creates a new instance of ClasificacionFormBean
     */
    public ClasificacionFormBean() {
        clasificacion=new Clasificacion();
    }

    public ClasificacionBean getClasificacionBean() {
        return clasificacionBean;
    }

    public void setClasificacionBean(ClasificacionBean clasificacionBean) {
        this.clasificacionBean = clasificacionBean;
    }

    public List<Clasificacion> getListaDeClasificaciones() {
        return listaDeClasificaciones;
    }

    public void setListaDeClasificaciones(List<Clasificacion> listaDeClasificaciones) {
        this.listaDeClasificaciones = listaDeClasificaciones;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }
    
        public void listarClasificaciones(){
    this.listaDeClasificaciones= clasificacionBean.listarClasificaciones();
    }
@PostConstruct
public void init(){
listarClasificaciones();
}
public void agregarClasificacion(){
clasificacionBean.agregarClasificacion(clasificacion);
ocultarDialogo();

}
public void mostrarDialogo(){
this.dialogo=true;
}
public void ocultarDialogo(){
this.dialogo=false;
}
}
