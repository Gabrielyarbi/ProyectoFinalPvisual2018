/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;

/**
 *
 * @author Gabriel
 */
public interface IPerfilDAO {
    public Perfil loginP (Usuario usu);
    public void agregar (Perfil perfil );
    public void modificar(Perfil perfil);
    public Perfil buscarPerfil(String dni);
    public void agregarAdmin(Perfil perfil);
}
