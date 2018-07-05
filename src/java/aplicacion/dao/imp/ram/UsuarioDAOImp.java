/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.modelo.dominio.Usuario;
import aplicacion.dao.IUsuarioDAO;
import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Publicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gabriel
 */
public class UsuarioDAOImp implements IUsuarioDAO, Serializable {

    @Override
    public Usuario login(String nombreUsuario, String password) {
        Usuario usu = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
        criteria.add(Restrictions.like("password", password));
        criteria.add(Restrictions.eq("estado", true));
        if (!criteria.list().isEmpty()) {
            usu = (Usuario) criteria.list().get(0);

        }
        session.close();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usu", usu);
        return usu;
    }

    @Override
    public void add(Usuario usuario) {

    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class).add(Restrictions.like("tipoUsuario", "final"));
        //trae de la base datos todos los usuario final nada mas
        listaUsuarios = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaUsuarios;
    }

    @Override
    public void modificarUsuario(Usuario u) {
        if (u != null) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(u);
            session.getTransaction().commit();

            session.close();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado", "Usuario Modificado");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos", "Faltan datos");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }

    }

    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> listaDeAdministradores = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class).add(Restrictions.like("tipoUsuario", "administrador"));
        //trae de la base datos todos los usuario final nada mas
        listaDeAdministradores = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaDeAdministradores;
    }
}
