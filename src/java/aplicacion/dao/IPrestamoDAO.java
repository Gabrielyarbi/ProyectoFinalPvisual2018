/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.DetallePrestamo;
import aplicacion.modelo.dominio.Prestamo;
import java.util.List;

/**
 *
 * @author Gabriel.Y
 */
public interface IPrestamoDAO {
    public void altaPrestamo(Prestamo p);
    public void altaDetallePrestamo(DetallePrestamo dp);
    public List<DetallePrestamo> listarPrestamos ();
    public void modificarPrestamo (DetallePrestamo prestamo);

}
