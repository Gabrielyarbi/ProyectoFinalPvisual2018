/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.dao.IClasificacionDAO;
import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Clasificacion;
import aplicacion.modelo.dominio.PubCla;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Gabriel.Y
 */
public class ClasificacionDAOImp implements IClasificacionDAO ,Serializable{

    @Override
    public List<Clasificacion> listarClasificaciones() {
List<Clasificacion> listaClasificaciones= new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Clasificacion.class);
        listaClasificaciones = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaClasificaciones;   
    }

    @Override
    public void agregarClasificacion(Clasificacion cla) {
 Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(cla);
        session.getTransaction().commit();
        session.close();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Clasificacion Agregada", "Clasificacion Agregada");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    
    }

    @Override
    public void agregarPubClasificacion(PubCla Pcla) {
 Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Pcla);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
}
