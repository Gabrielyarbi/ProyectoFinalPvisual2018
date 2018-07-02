/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.ReservaBean;
import aplicacion.modelo.dominio.DetallePrestamo;
import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Prestamo;
import aplicacion.modelo.dominio.PubAut;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Reserva;
import java.util.Date;
import javax.faces.context.FacesContext;
import aplicacion.modelo.dominio.Publicacion;
import java.io.Serializable;



import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class ReservaFormBean implements Serializable {

    @ManagedProperty(value = "#{reservaBean}")
    private ReservaBean reservaBean;
    private Reserva reserva;
    private DetalleReserva detalleReserva;
    private boolean dialogo;
    private Publicacion publicacionSeleccionada;
    private boolean dialogo2;
    private List<DetalleReserva> reservas;
    private DetalleReserva reservaSeleccionada;
    private DetalleReserva reservaPrestamo;
    private Prestamo prestamo;
    private DetallePrestamo dPrestamo;
    private String turno;
    private Date fechaAdevolver;
    /**
     * Creates a new instance of ReservaFormBean
     */
    public ReservaFormBean() {
        publicacionSeleccionada = new Publicacion();
        reserva = new Reserva(new Date(), true);
        detalleReserva = new DetalleReserva(1);
        reservaSeleccionada=new DetalleReserva();
       
        fechaAdevolver=new Date();
        prestamo = new Prestamo(new Date(), true);
        dPrestamo=new DetallePrestamo();
    }

    public ReservaBean getReservaBean() {
        return reservaBean;
    }

    public void setReservaBean(ReservaBean reservaBean) {
        this.reservaBean = reservaBean;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public DetalleReserva getDetalleReserva() {
        return detalleReserva;
    }

    public void setDetalleReserva(DetalleReserva detalleReserva) {
        this.detalleReserva = detalleReserva;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public Publicacion getPublicacionSeleccionada() {
        return publicacionSeleccionada;
    }

    public void setPublicacionSeleccionada(Publicacion publicacionSeleccionada) {
        this.publicacionSeleccionada = publicacionSeleccionada;
    }

    public DetalleReserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }

    public void setReservaSeleccionada(DetalleReserva reservaSeleccionada) {
        this.reservaSeleccionada = reservaSeleccionada;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public DetallePrestamo getdPrestamo() {
        return dPrestamo;
    }

    public void setdPrestamo(DetallePrestamo dPrestamo) {
        this.dPrestamo = dPrestamo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getFechaAdevolver() {
        return fechaAdevolver;
    }

    public void setFechaAdevolcer(Date fechaAdevolcer) {
        this.fechaAdevolver = fechaAdevolcer;
    }

    public boolean isDialogo2() {
        return dialogo2;
    }

    public void setDialogo2(boolean dialogo2) {
        this.dialogo2 = dialogo2;
    }

    public void seleccionarPublicacion(PubAut p) {
        this.publicacionSeleccionada = p.getPublicacion();
        mostrarDialogo();
    }


 
    public List<DetalleReserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<DetalleReserva> reservas) {
        this.reservas = reservas;
    }

    public DetalleReserva getReservaPrestamo() {
        return reservaPrestamo;
    }

    public void setReservaPrestamo(DetalleReserva reservaPrestamo) {
        this.reservaPrestamo = reservaPrestamo;
    }

    
    public void nuevaReserva (){
        Perfil perf=(Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
        reserva.setPerfil(perf);

        reservaBean.altaDeReserva(reserva);
        detalleReserva.setPublicacion(publicacionSeleccionada);
        detalleReserva.setReserva(reserva);
        detalleReserva.setEstado(true);
        reservaBean.altaDeDetalleDeReserva(detalleReserva);
        ocultarDialogo();
        publicacionSeleccionada = new Publicacion();
        reserva = new Reserva(new Date(), true);
        detalleReserva = new DetalleReserva();
        
    }

    public void mostrarDialogo() {
        this.dialogo = true;
    }

    public void ocultarDialogo() {
        this.dialogo = false;
    }
    
    public void mostrarDialogo2() {
        this.dialogo2 = true;
    }

    public void ocultarDialogo2() {
        this.dialogo2 = false;
    }
     public void listarReservas(){
         this.reservas=reservaBean.listarReservas();
     }
     @PostConstruct
     public void init(){
         listarReservas();
     }
     public void seleccionarReserva(DetalleReserva r){
         this.reservaSeleccionada=r;
         mostrarDialogo();
     }
     public void confirmarReserva(){
        Perfil admin=(Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil"); 
        this.prestamo.setPerfil(this.reservaSeleccionada.getReserva().getPerfil());
        this.prestamo.setAdministrativo(admin.getNombre());
        this.dPrestamo.setPrestamo(prestamo);
        this.dPrestamo.setEstado(true);
        this.dPrestamo.setPublicacion(this.reservaSeleccionada.getPublicacion());
        this.dPrestamo.setFechaADevolver(fechaAdevolver);
        
        this.dPrestamo.setTurno(turno);
        this.dPrestamo.setFechaDevolucion(new Date());
        reservaBean.confirmarReserva(prestamo, dPrestamo);
        this.reservaSeleccionada.setEstado(false);
        reservaBean.modificarReserva(this.reservaSeleccionada);
        ocultarDialogo();
        init();
     }
      /* public void altaDePrestamo(){
        
    
        Perfil admin=(Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
        this.prestamo.setPerfil(this.perfil);
        this.prestamo.setAdministrativo(admin.getNombre());
        prestamoBean.agregarPrestamo(prestamo);
        this.dPrestamo.setPrestamo(prestamo);
        this.publicacion=(Publicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("publicacion");
        
        
        this.dPrestamo.setEstado(true);
        this.dPrestamo.setPublicacion(publicacion);
        this.dPrestamo.setFechaADevolver(fechaAdevolver);
        this.dPrestamo.setTurno(turno);
        this.dPrestamo.setFechaDevolucion(new Date());
        prestamoBean.agregarDetallePrestamo(dPrestamo);
         dPrestamo=new DetallePrestamo();
      prestamo = new Prestamo(new Date(), true);
       publicacion=new Publicacion();
       fechaAdevolver=new Date();
        ocultarDialogo();
    }*/
}
