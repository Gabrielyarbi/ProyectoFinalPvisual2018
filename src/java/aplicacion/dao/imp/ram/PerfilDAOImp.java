/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.dao.IPerfilDAO;
import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Publicacion;
import aplicacion.modelo.dominio.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Gabriel
 */
public class PerfilDAOImp implements IPerfilDAO {

    @Override
    public Perfil loginP(Usuario usu) {
        Perfil perfil = null;

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Perfil.class);
        criteria.add(Restrictions.like("usuario", usu));

        if (!criteria.list().isEmpty()) {
            perfil = (Perfil) criteria.list().get(0);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfil", perfil);
        session.close();
        return perfil;
    }

    @Override
    public void agregar(Perfil perfil) {
        try {
            if (perfil != null) {
                Session session = NewHibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(perfil);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usu", perfil.getUsuario());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfil", perfil);
                session.getTransaction().commit();
                session.close();

                FacesContext.getCurrentInstance().getExternalContext().redirect("Home.xhtml");

            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos", "Faltan datos");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            }
        } catch (Exception e) {
        }
    }

    @Override
    public void modificar(Perfil perfil) {
        if (perfil != null) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(perfil);
            session.getTransaction().commit();

            session.close();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "PERFIL MODIFICADO", "PERFIL MODIFICADO");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos", "Faltan datos");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }

    }

    @Override
    public Perfil buscarPerfil(String dni) {
      Perfil perfil=null;
       
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        Criteria c =session.createCriteria(Perfil.class).add(Restrictions.like("dni", dni));
        if(!c.list().isEmpty()){
        perfil = (Perfil)c.list().get(0);
    }
    return perfil;
    }
    public void agregarAdmin(Perfil perfil) {
           if (perfil != null) {
                Session session = NewHibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(perfil);
               
                session.getTransaction().commit();
                session.close();

            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Faltan datos", "Faltan datos");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            }

    }

}

