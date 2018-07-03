/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.dao.imp.ram.PerfilDAOImp;
import aplicacion.dao.imp.ram.PrestamoDAOImp;
import aplicacion.modelo.dominio.DetallePrestamo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Prestamo;
import aplicacion.modelo.dominio.Perfil;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class PrestamoBean implements Serializable{
private Prestamo prestamo;
    /**
     * Creates a new instance of PrestamoBean
     */
    public PrestamoBean() {
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    public void agregarPrestamo(Prestamo p){
        PrestamoDAOImp prestamoDao=new PrestamoDAOImp();
        prestamoDao.altaPrestamo(p);
    }
    public void agregarDetallePrestamo(DetallePrestamo dp){
        PrestamoDAOImp prestamoDao=new PrestamoDAOImp();
        prestamoDao.altaDetallePrestamo(dp);
    }
    public Perfil buscarPerfilDeUsuarioAprestar(String dni){
        PerfilDAOImp PDao= new PerfilDAOImp();
        return PDao.buscarPerfil(dni);
    }
public List<DetallePrestamo> listarPrestamos(){
    PrestamoDAOImp prestamoDao =new PrestamoDAOImp();
    return prestamoDao.listarPrestamos();
}
public void modificarPrestamo(DetallePrestamo p){
    PrestamoDAOImp prestamoDao =new PrestamoDAOImp();
    prestamoDao.modificarPrestamo(p);
}
}
