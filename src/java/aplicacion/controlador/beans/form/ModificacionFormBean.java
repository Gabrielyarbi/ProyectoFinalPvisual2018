/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.PerfilBeans;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import aplicacion.modelo.dominio.Perfil;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@ViewScoped
public class ModificacionFormBean implements Serializable {

    @ManagedProperty(value = "#{perfilBeans}")
    private PerfilBeans perfilbeans;
    private boolean dialogo = false;
    private Perfil perfil;

    /**
     * Creates a new instance of ModificacionFormBean
     */
    public ModificacionFormBean() {

    }

    public PerfilBeans getPerfilbeans() {
        return perfilbeans;
    }

    public void setPerfilbeans(PerfilBeans perfilbeans) {
        this.perfilbeans = perfilbeans;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void seleccionarPerfil() {
       this.perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
      mostrarDialogo();
    }
public void modificarPerfil (){
  perfilbeans.modificarPerfil(this.perfil);
        ocultarDialogo();
}
    public void mostrarDialogo() {
        this.dialogo = true;
    }

    public void ocultarDialogo() {
        this.dialogo = false;
    }

}
