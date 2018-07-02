/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.DetalleReserva;
import aplicacion.modelo.dominio.Reserva;
import java.util.List;

/**
 *
 * @author Gabriel.Y
 */
public interface IReservaDAO {
    public void altaReserva(Reserva reserva);
    public void modificarReserva(DetalleReserva reserva);
    public void nuevoDetalleDeReserva(DetalleReserva detalle);
    public List<DetalleReserva> listarReservas();
}
