/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp.ram;

import aplicacion.datos.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Publicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import aplicacion.dao.IPublicacionDAO;
import aplicacion.modelo.dominio.Clasificacion;
import aplicacion.modelo.dominio.Editorial;
import aplicacion.modelo.dominio.PubAut;
import aplicacion.modelo.dominio.PubCla;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel.Y
 */
public class PublicacionDAOImp implements IPublicacionDAO ,Serializable{

    @Override
    public List<PubAut> obtenerLista() {
     List<PubAut> listaPublicaciones = new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //trae de la base datos todos los usuari final nada mas
        Criteria criteria = session.createCriteria(PubAut.class);
        listaPublicaciones = criteria.addOrder(Order.asc("codigo")).list();

        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaPublicaciones;   
    }

    @Override
    public void filtrarPublicacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nuevaPublicaion(Publicacion p) {
if(p!=null){
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(p);
    session.getTransaction().commit();
    session.close();
    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Agregado con exito" ,"Agregado con exito");
    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }else{FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Faltan datos en la publicaion" ,"Faltan datos en la publicaion");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    
    }
    }

    @Override
    public void nuevaPublicaionAutor(PubAut publicacionAutor) {
Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.save(publicacionAutor);
    session.getTransaction().commit();
    session.close();
    }

    @Override
    public void modificarPublicacion(Publicacion p) {
if(p!=null){
    Session session = NewHibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    session.update(p);
    session.getTransaction().commit();
    session.close();
  FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Modificado con exito" ,"Modificado con exito");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    
    
    }else{FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Error" ,"Error");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    
    }

    }

    @Override
    public List<Publicacion> buscarLibro(String busqueda) {
        Editorial edi;
List<Publicacion> listaEncontrada = new ArrayList<>();
listaEncontrada.clear();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Publicacion.class).add(Restrictions.like("nombre", busqueda));
    //Solo encuentra resultado con nombres exactos arreglar para q encuentre resultados con coincidencia de palabras
        if(!criteria.list().isEmpty()){
        listaEncontrada = criteria.addOrder(Order.asc("codigo")).list();
            System.out.println(listaEncontrada.size());//devuelve tamaño de lista ...
            //si encuetra resultados pero al redireccionar a la tabla el array ya esta vacio (arreglar)
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("libros", listaEncontrada);
        }else{Criteria c =session.createCriteria(Editorial.class).add(Restrictions.like("nombre", busqueda));
        if(!c.list().isEmpty()){
        edi = (Editorial)c.list().get(0);
        Criteria c2 = session.createCriteria(Publicacion.class).add(Restrictions.like("editorial", edi));
        listaEncontrada=c2.addOrder(Order.asc("codigo")).list();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("libros", listaEncontrada);
        }
        }
        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return listaEncontrada;
    }

    @Override
    public List<PubAut> LibroAprestar(String codigo) {
        Publicacion pub=new Publicacion();
        List<PubAut> PublicacionAprestar= new ArrayList<>();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria c =session.createCriteria(Publicacion.class).add(Restrictions.like("codigo", codigo));
        if(!c.list().isEmpty()){
        pub = (Publicacion)c.list().get(0);
        
       
        Criteria criteria = session.createCriteria(PubAut.class).add(Restrictions.like("publicacion", pub));
        PublicacionAprestar = criteria.addOrder(Order.asc("codigo")).list();
        }
        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return PublicacionAprestar;
    }

    @Override
    public PubCla obtenerClasificacion(Publicacion p) {
        PubCla cla = new PubCla();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria c =session.createCriteria(PubCla.class).add(Restrictions.like("publicacion", p));
        if(!c.list().isEmpty()){
        cla = (PubCla)c.list().get(0);
    }
        session.flush();//actuliseme ese opjeto de la base de dato
        session.close();
        return cla;
    }

 
}