package aplicacion.modelo.dominio;
// Generated 10/06/2018 19:00:23 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Editoriales generated by hbm2java
 */
public class Editorial  implements java.io.Serializable {


     private Integer codigo;
     private String nombre;
     private boolean estado;
     private Set publicacioneses = new HashSet(0);

    public Editorial() {
    }

    public Editorial(Integer codigo, String nombre, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

	
    public Set getPublicacioneses() {
        return this.publicacioneses;
    }
    
    public void setPublicacioneses(Set publicacioneses) {
        this.publicacioneses = publicacioneses;
    }




}


