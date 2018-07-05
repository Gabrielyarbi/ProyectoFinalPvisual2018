/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.dao.imp.ram.PrestamoDAOImp;
import aplicacion.dao.imp.ram.PublicacionDAOImp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Reserva;
import aplicacion.dao.imp.ram.ReservaDAOImp;
import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.DetallePrestamo;
import java.io.Serializable;
import aplicacion.modelo.dominio.Prestamo;
import aplicacion.modelo.dominio.Publicacion;


import java.util.List;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class ReservaBean implements Serializable {

    private Reserva reserva;

    /**
     * Creates a new instance of ReservaBean
     */
    public ReservaBean() {
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void altaDeReserva(Reserva r) {
        ReservaDAOImp reservaDAO = new ReservaDAOImp();
        reservaDAO.altaReserva(r);
    }

    public void altaDeDetalleDeReserva(DetalleReserva dr) {
        ReservaDAOImp reservaDAO = new ReservaDAOImp();
        reservaDAO.nuevoDetalleDeReserva(dr);
    }



   public List<DetalleReserva> listarReservas(){
       ReservaDAOImp reservaDAO=new ReservaDAOImp();
       return reservaDAO.listarReservas();
   } 
public void confirmarReserva (Prestamo p, DetallePrestamo dp){
    PrestamoDAOImp prestamoDAO=new PrestamoDAOImp();
    prestamoDAO.altaPrestamo(p);
    prestamoDAO.altaDetallePrestamo(dp);
}
public void modificarReserva(DetalleReserva reserva){
    ReservaDAOImp reservaD=new ReservaDAOImp();
    reservaD.modificarReserva(reserva);
}
public void modificarStock(Publicacion p){
    PublicacionDAOImp pubDAO=new PublicacionDAOImp();
    pubDAO.modificarPublicacion(p);
}
}


