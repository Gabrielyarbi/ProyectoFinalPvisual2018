/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.PrestamoBean;
import aplicacion.modelo.dominio.DetallePrestamo;
import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Prestamo;
import aplicacion.modelo.dominio.Publicacion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class PrestamoFormBean implements Serializable {

    @ManagedProperty(value = "#{prestamoBean}")
    private PrestamoBean prestamoBean;
    private Prestamo prestamo;
    private DetallePrestamo dPrestamo;
    private List<DetallePrestamo> prestamos;
    private DetalleReserva reservaSeleccionada;
    private String dni;
    private Perfil perfil;
    private boolean dialogo = false;
    private boolean dialogo2 = false;
    private Publicacion publicacion;
    private Date fechaAdevolver;
    private String turno;
    private DetallePrestamo prestamoSeleccionado;
    private Date fechaDeDevolucion;

    /**
     * Creates a new instance of PrestamoFormBean
     */
    public PrestamoFormBean() {
        dPrestamo = new DetallePrestamo();
        prestamo = new Prestamo(new Date(), true);
        publicacion = new Publicacion();
        fechaAdevolver = new Date();
    }

    public PrestamoBean getPrestamoBean() {
        return prestamoBean;
    }

    public void setPrestamoBean(PrestamoBean prestamoBean) {
        this.prestamoBean = prestamoBean;
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

    public List<DetallePrestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<DetallePrestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public DetalleReserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }

    public void setReservaSeleccionada(DetalleReserva reservaSeleccionada) {
        this.reservaSeleccionada = reservaSeleccionada;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public boolean isDialogo2() {
        return dialogo2;
    }

    public void setDialogo2(boolean dialogo2) {
        this.dialogo2 = dialogo2;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Date getFechaAdevolver() {
        return fechaAdevolver;
    }

    public void setFechaAdevolver(Date fechaAdevolver) {
        this.fechaAdevolver = fechaAdevolver;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public DetallePrestamo getPrestamoSeleccionado() {
        return prestamoSeleccionado;
    }

    public void setPrestamoSeleccionado(DetallePrestamo prestamoSeleccionado) {
        this.prestamoSeleccionado = prestamoSeleccionado;
    }

    public Date getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(Date fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

    public void buscarPerfilAprestar() {
        this.perfil = prestamoBean.buscarPerfilDeUsuarioAprestar(this.dni);
        if (perfil == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encotro coincidencias", "No se encotro coincidencias");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {
            this.dialogo2 = true;
        }
    }

    public void altaDePrestamo() {

        Perfil admin = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfil");
        this.prestamo.setPerfil(this.perfil);
        this.prestamo.setAdministrativo(admin.getNombre());
        prestamoBean.agregarPrestamo(prestamo);
        this.dPrestamo.setPrestamo(prestamo);
        this.publicacion = (Publicacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("publicacion");

        this.dPrestamo.setEstado(true);
        this.dPrestamo.setPublicacion(publicacion);
        this.dPrestamo.setFechaADevolver(fechaAdevolver);
        this.dPrestamo.setTurno(turno);
        this.dPrestamo.setFechaDevolucion(new Date());
        prestamoBean.agregarDetallePrestamo(dPrestamo);
        dPrestamo = new DetallePrestamo();
        prestamo = new Prestamo(new Date(), true);
        publicacion = new Publicacion();
        fechaAdevolver = new Date();
        ocultarDialogo();
    }

    public void mostrarDialogo() {
        this.dialogo = true;
    }

    public void ocultarDialogo() {
        this.dialogo = false;
    }

    public void listarPrestamos() {
        this.prestamos = prestamoBean.listarPrestamos();
    }

    @PostConstruct
    public void init() {
        listarPrestamos();
    }

    public void seleccionarPrestamo(DetallePrestamo prestamo) {
        this.prestamoSeleccionado = prestamo;
        mostrarDialogo();
    }

    public void modificarPrestamo() {
        this.prestamoSeleccionado.setFechaDevolucion(fechaDeDevolucion);
        this.prestamoSeleccionado.setEstado(false);
        prestamoBean.modificarPrestamo(prestamoSeleccionado);

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado", "Confirmado");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        ocultarDialogo();
        init();
    }
}
