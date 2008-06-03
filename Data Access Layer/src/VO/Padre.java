/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "padre")
@NamedQueries({
    @NamedQuery(name = "Padre.consultarUnPadre", query = "SELECT p FROM Padre p WHERE p.idPadre = :idPadre"),
    @NamedQuery(name = "Padre.consultarPadres", query = "SELECT p FROM Padre p"),
    @NamedQuery(name = "Padre.findByNombres", query = "SELECT p FROM Padre p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Padre.findByApellidos", query = "SELECT p FROM Padre p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Padre.consultarPorIdEstudiante", query = "SELECT p FROM Padre p WHERE p.idEstudiante = :estudiante"),
    @NamedQuery(name = "Padre.findByCorreo", query = "SELECT p FROM Padre p WHERE p.correo = :correo")
})
public class Padre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPadre", nullable = false)
    private Integer idPadre;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "correo", nullable = false)
    private String correo;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @ManyToOne
    private Estudiante idEstudiante;

    public Padre() {
    }

    public Padre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public Padre(Integer idPadre, String nombres, String apellidos, String correo) {
        this.idPadre = idPadre;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPadre != null ? idPadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Padre)) {
            return false;
        }
        Padre other = (Padre) object;
        if ((this.idPadre == null && other.idPadre != null) || (this.idPadre != null && !this.idPadre.equals(other.idPadre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Padre[idPadre=" + idPadre + "]";
    }

}
