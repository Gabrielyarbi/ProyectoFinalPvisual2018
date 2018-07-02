/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.dao.IReservaDAO;
import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.Reserva;
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
public class ReservaDAOImp implements IReservaDAO,Serializable{

    @Override
    public void altaReserva(Reserva reserva) {
if(reserva!=null){
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(reserva);
    session.getTransaction().commit();
    session.close();
  FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Se a reservado con exito" ,"Se a reservado con exito");
  FacesContext.getCurrentInstance().addMessage(null, facesMessage);
 }else{FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Error" ,"Error");
  FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    @Override
    public void modificarReserva(DetalleReserva reserva) {
    if(reserva!=null){
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.update(reserva);
    session.getTransaction().commit();
    session.close();  
    
    }else{FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Error" ,"Error");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
 
    }

    @Override
    public void nuevoDetalleDeReserva(DetalleReserva detalle) {
       if(detalle!=null){
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(detalle);
    session.getTransaction().commit();
    session.close();
 }
    }

    @Override
    public List<DetalleReserva> listarReservas() {
         List<DetalleReserva> listaReservas = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //trae de la base datos todos los usuari final nada mas
        Criteria criteria = session.createCriteria(DetalleReserva.class).add(Restrictions.like("estado",true));
        listaReservas = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaReservas;   
    }

    
    
}
