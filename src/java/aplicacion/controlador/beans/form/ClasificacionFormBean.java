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
  /**
     * Creates a new instance of ClasificacionFormBean
     */
    public ClasificacionFormBean() {
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
        public void listarClasificaciones(){
    this.listaDeClasificaciones= clasificacionBean.listarClasificaciones();
    }
@PostConstruct
public void init(){
listarClasificaciones();
}
}
