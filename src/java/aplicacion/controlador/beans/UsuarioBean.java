/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.dao.imp.ram.PerfilDAOImp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Usuario;
import aplicacion.dao.imp.ram.UsuarioDAOImp;
import aplicacion.modelo.util.PaginaUtil;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import aplicacion.modelo.dominio.Perfil;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private PaginaUtil p;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
        usuario = new Usuario();
        p = new PaginaUtil();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario validarUsuario(String nombreUsuario, String password) {
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp();
        return usuarioDAOImp.login(nombreUsuario, password);
    }

    public Perfil validarPerfil() {
        Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usu");

        PerfilDAOImp perDao = new PerfilDAOImp();
        return perDao.loginP(usu);
    }

    public void agregarUsuario(Usuario usuario) {
        UsuarioDAOImp usuDao = new UsuarioDAOImp();
        usuDao.add(usuario);
    }

    /**
     * Guarda el usuario como nulo (deslogueado) y redirecciona a la pagina de
     * inicio
     *
     */
    public void cerrarSesion() throws IOException {
        p.cerrarSesion();
    }

    public boolean renderizarF() {
        return p.renderizarPaginaFinal();
    }

    public boolean renderizarA() {
        return p.renderizarPaginaAdmin();
    }

    public boolean renderizarS() {
        return p.renderizarPaginaSuper();
    }

    public Usuario validarSesion() {
        Usuario usu = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usu");
        return usu;
    }

    public List<Usuario> obtenerUsuarios() {
        UsuarioDAOImp usuDao = new UsuarioDAOImp();
        return usuDao.listarUsuarios();
    }

    public List<Usuario> obtenerAdministradores() {
        UsuarioDAOImp usuDao = new UsuarioDAOImp();
        return usuDao.listarAdministradores();
    }

    public void modificarUsuario(Usuario us) {
        UsuarioDAOImp usuDao = new UsuarioDAOImp();
        usuDao.modificarUsuario(us);
    }
}
