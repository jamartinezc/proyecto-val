/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({@NamedQuery(name = "Padre.findByIdentificacion", query = "SELECT p FROM Padre p WHERE p.identificacion = :identificacion"), @NamedQuery(name = "Padre.findByNombres", query = "SELECT p FROM Padre p WHERE p.nombres = :nombres"), @NamedQuery(name = "Padre.findByApellidos", query = "SELECT p FROM Padre p WHERE p.apellidos = :apellidos"), @NamedQuery(name = "Padre.findByCorreo", query = "SELECT p FROM Padre p WHERE p.correo = :correo")})
public class Padre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "identificacion", nullable = false)
    private Integer identificacion;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "correo", nullable = false)
    private String correo;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Estudiante codigo;

    public Padre() {
    }

    public Padre(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public Padre(Integer identificacion, String nombres, String apellidos, String correo) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
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

    public Estudiante getCodigo() {
        return codigo;
    }

    public void setCodigo(Estudiante codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificacion != null ? identificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Padre)) {
            return false;
        }
        Padre other = (Padre) object;
        if ((this.identificacion == null && other.identificacion != null) || (this.identificacion != null && !this.identificacion.equals(other.identificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Padre[identificacion=" + identificacion + "]";
    }

}
