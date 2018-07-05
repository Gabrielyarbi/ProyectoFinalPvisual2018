/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.dao.imp.ram.PerfilDAOImp;
import javax.faces.bean.ManagedBean;
import aplicacion.modelo.dominio.Usuario;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.util.PaginaUtil;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@ViewScoped
public class PerfilBeans implements Serializable {

    private Usuario usuario;
    private Perfil perfil;
    private PaginaUtil p;

    /**
     * Creates a new instance of PerfilBeans
     */
    public PerfilBeans() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public PaginaUtil getP() {
        return p;
    }

    public void setP(PaginaUtil p) {
        this.p = p;
    }

    public void agregarPerfil(Perfil perfil) {
        PerfilDAOImp perfildao = new PerfilDAOImp();
        perfildao.agregar(perfil);
    }
    public void agregarPerfilAdmin(Perfil perfil) {
        PerfilDAOImp perfildao = new PerfilDAOImp();
        perfildao.agregarAdmin(perfil);
    }

    public void modificarPerfil(Perfil perfil) {
        PerfilDAOImp perfildao = new PerfilDAOImp();
        perfildao.modificar(perfil);

    }
}
