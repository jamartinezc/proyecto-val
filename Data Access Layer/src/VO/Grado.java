/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "grado")
@NamedQueries({@NamedQuery(name = "Grado.findByGrado", query = "SELECT g FROM Grado g WHERE g.grado = :grado"), @NamedQuery(name = "Grado.findByNombre", query = "SELECT g FROM Grado g WHERE g.nombre = :nombre")})
public class Grado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "grado", nullable = false)
    private Integer grado;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grado")
    private Collection<Materia> materiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grado")
    private Collection<PlaneacionSemanal> planeacionSemanalCollection;
    @OneToMany(mappedBy = "grado")
    private Collection<Estudiante> estudianteCollection;

    public Grado() {
    }

    public Grado(Integer grado) {
        this.grado = grado;
    }

    public Grado(Integer grado, String nombre) {
        this.grado = grado;
        this.nombre = nombre;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Materia> getMateriaCollection() {
        return materiaCollection;
    }

    public void setMateriaCollection(Collection<Materia> materiaCollection) {
        this.materiaCollection = materiaCollection;
    }

    public Collection<PlaneacionSemanal> getPlaneacionSemanalCollection() {
        return planeacionSemanalCollection;
    }

    public void setPlaneacionSemanalCollection(Collection<PlaneacionSemanal> planeacionSemanalCollection) {
        this.planeacionSemanalCollection = planeacionSemanalCollection;
    }

    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grado != null ? grado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.grado == null && other.grado != null) || (this.grado != null && !this.grado.equals(other.grado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Grado[grado=" + grado + "]";
    }

}
