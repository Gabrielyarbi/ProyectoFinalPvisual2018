/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.PerfilBeans;
import aplicacion.controlador.beans.UsuarioBean;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import aplicacion.modelo.dominio.Perfil;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@ViewScoped
public class AltaFormBean implements Serializable {

    @ManagedProperty(value = "#{perfilBeans}")
    private PerfilBeans perfilbeans;
    private UsuarioBean usuariobeans;
    private Usuario usuariof;
    private Perfil perfil;
    private Usuario usuarioAdmin;
    private Perfil perfilAdmin;
    private boolean dialogo=false;
    /**
     * Creates a new instance of AltaFormBean
     */
    public AltaFormBean() {
        perfil = new Perfil();
        usuariof = new Usuario("final", true);
        perfilAdmin=new Perfil();
        usuarioAdmin = new Usuario("administrador", true);
    }

    public PerfilBeans getPerfilbeans() {
        return perfilbeans;
    }

    public void setPerfilbeans(PerfilBeans perfilbeans) {
        this.perfilbeans = perfilbeans;
    }

    public Usuario getUsuariof() {
        return usuariof;
    }

    public void setUsuariof(Usuario usuariof) {
        this.usuariof = usuariof;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public UsuarioBean getUsuariobeans() {
        return usuariobeans;
    }

    public void setUsuariobeans(UsuarioBean usuariobeans) {
        this.usuariobeans = usuariobeans;
    }

    public Usuario getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(Usuario usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public Perfil getPerfilAdmin() {
        return perfilAdmin;
    }

    public void setPerfilAdmin(Perfil perfilAdmin) {
        this.perfilAdmin = perfilAdmin;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public void agregarPerfilF() {

        if (usuariof != null) {
            perfil.setUsuario(usuariof);
            perfilbeans.agregarPerfil(perfil);
            usuariof = new Usuario("final", true);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos de usuario", "Faltan datos de usuario");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    public void agregarPerfilAdmin() throws IOException{
     if (usuarioAdmin != null) {
            perfilAdmin.setUsuario(usuarioAdmin);
            perfilbeans.agregarPerfilAdmin(perfilAdmin);
            usuarioAdmin = new Usuario("administrador", true);
             FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado con exito", "Agregado con exito");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            ocultarDialogo();
            FacesContext.getCurrentInstance().getExternalContext().redirect("Administradores.xhtml");
             perfilAdmin=new Perfil();
        usuarioAdmin = new Usuario("administrador", true);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos de usuario", "Faltan datos de usuario");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    public void mostrarDialogo(){
    this.dialogo=true;
    }
    
    public void ocultarDialogo(){
    this.dialogo=false;
    }
}
