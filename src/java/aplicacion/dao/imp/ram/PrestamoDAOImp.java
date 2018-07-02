/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.dao.IPrestamoDAO;
import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.DetallePrestamo;
import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.Prestamo;
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
 * @author Gabriel.Y
 */
public class PrestamoDAOImp implements IPrestamoDAO, Serializable{

    @Override
    public void altaPrestamo(Prestamo p) {
    if (p != null) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            session.close();
       
        } else {
         
        } 
    }

    @Override
    public void altaDetallePrestamo(DetallePrestamo dp) {
      if (dp != null) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dp);
            session.getTransaction().commit();
            session.close();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo realizado", "Prestamo realizado");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } 
    }

    @Override
    public List<DetallePrestamo> listarPrestamos() {
         List<DetallePrestamo> listaPrestamo = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //trae de la base datos todos los usuari final nada mas
        Criteria criteria = session.createCriteria(DetallePrestamo.class).add(Restrictions.like("estado",true));
        listaPrestamo = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaPrestamo;   
    
    }
    
}
