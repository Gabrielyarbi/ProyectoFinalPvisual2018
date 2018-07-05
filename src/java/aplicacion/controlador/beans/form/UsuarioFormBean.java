/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.form;

import aplicacion.controlador.beans.UsuarioBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import aplicacion.modelo.dominio.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Gabriel.Y
 */
@ManagedBean
@ViewScoped
public class UsuarioFormBean implements Serializable {

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosAdministradores;
    private Usuario usuarioSeleccionado;
    private boolean dialogo = false;

    /**
     * Creates a new instance of UsuarioFormBean
     */
    public UsuarioFormBean() {
        usuarioSeleccionado = new Usuario();
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuariosAdministradores() {
        return usuariosAdministradores;
    }

    public void setUsuariosAdministradores(List<Usuario> usuariosAdministradores) {
        this.usuariosAdministradores = usuariosAdministradores;
    }

    public boolean isDialogo() {
        return dialogo;
    }

    public void setDialogo(boolean dialogo) {
        this.dialogo = dialogo;
    }

    public void listarUsuarios() {
        this.usuarios = usuarioBean.obtenerUsuarios();
    }

    public void listarAdministradores() {
        this.usuariosAdministradores = usuarioBean.obtenerAdministradores();
    }

    @PostConstruct
    public void init() {
        listarUsuarios();
        listarAdministradores();
    }

    public void seleccionarUsuario(Usuario us) {
        this.usuarioSeleccionado = us;
        mostrarDialogo();
    }

    public void modificarUsuario() {

        usuarioBean.modificarUsuario(usuarioSeleccionado);
        ocultarDialogo();
    }

    public void mostrarDialogo() {
        this.dialogo = true;
    }

    public void ocultarDialogo() {
        this.dialogo = false;
    }

    public void exportarUsuarioPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
//puedo pasar parametros al report, siempre que el diseño lo soporte
//parametros.put("usuario", "pepito");
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/repoUsuFin.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getUsuarios()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=UsuariosFinales.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//exportamos a un archivo en disco
//JasperExportManager.exportReportToPdfFile(jasperPrint, "e:/reportePrendas.pdf");
//mostrar en visor jasper
//JasperViewer.viewReport(jasperPrint,false);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarUsuarioAdminPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
//puedo pasar parametros al report, siempre que el diseño lo soporte
//parametros.put("usuario", "pepito");
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/repoUsuAdm.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.getUsuariosAdministradores()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=UsuariosAdministradores.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//exportamos a un archivo en disco
//JasperExportManager.exportReportToPdfFile(jasperPrint, "e:/reportePrendas.pdf");
//mostrar en visor jasper
//JasperViewer.viewReport(jasperPrint,false);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
