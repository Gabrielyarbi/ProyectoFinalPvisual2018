package aplicacion.modelo.dominio;
// Generated 10/06/2018 19:00:23 by Hibernate Tools 4.3.1



/**
 * DetallesReservas generated by hbm2java
 */
public class DetalleReserva  implements java.io.Serializable {


     private Integer codigo;
     private Publicacion publicacion;
     private Reserva reserva;
     private int cantidad;
     private boolean estado;

    public DetalleReserva() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}

