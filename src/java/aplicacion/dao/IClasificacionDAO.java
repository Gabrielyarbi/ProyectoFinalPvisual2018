/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Clasificacion;
import aplicacion.modelo.dominio.PubCla;
import java.util.List;

/**
 *
 * @author Gabriel.Y
 */
public interface IClasificacionDAO {
       public List<Clasificacion> listarClasificaciones ();
       public void agregarClasificacion(Clasificacion cla);
       public void agregarPubClasificacion(PubCla Pcla);
}
