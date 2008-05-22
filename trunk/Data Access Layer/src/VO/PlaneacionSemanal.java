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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "planeacionsemanal")
@NamedQueries({@NamedQuery(name = "PlaneacionSemanal.findByIdPlaneacionSemanal", query = "SELECT p FROM PlaneacionSemanal p WHERE p.idPlaneacionSemanal = :idPlaneacionSemanal"), @NamedQuery(name = "PlaneacionSemanal.findBySemana", query = "SELECT p FROM PlaneacionSemanal p WHERE p.semana = :semana")})
public class PlaneacionSemanal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPlaneacionSemanal", nullable = false)
    private Integer idPlaneacionSemanal;
    @Column(name = "semana", nullable = false)
    private int semana;
    @JoinColumn(name = "idGrado", referencedColumnName = "idGrado")
    @ManyToOne
    private Grado idGrado;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @ManyToOne
    private Estudiante idEstudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaneacionSemanal")
    private Collection<ExamenPlaneado> examenPlaneadoCollection;

    public PlaneacionSemanal() {
    }

    public PlaneacionSemanal(Integer idPlaneacionSemanal) {
        this.idPlaneacionSemanal = idPlaneacionSemanal;
    }

    public PlaneacionSemanal(Integer idPlaneacionSemanal, int semana) {
        this.idPlaneacionSemanal = idPlaneacionSemanal;
        this.semana = semana;
    }

    public Integer getIdPlaneacionSemanal() {
        return idPlaneacionSemanal;
    }

    public void setIdPlaneacionSemanal(Integer idPlaneacionSemanal) {
        this.idPlaneacionSemanal = idPlaneacionSemanal;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Collection<ExamenPlaneado> getExamenPlaneadoCollection() {
        return examenPlaneadoCollection;
    }

    public void setExamenPlaneadoCollection(Collection<ExamenPlaneado> examenPlaneadoCollection) {
        this.examenPlaneadoCollection = examenPlaneadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlaneacionSemanal != null ? idPlaneacionSemanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionSemanal)) {
            return false;
        }
        PlaneacionSemanal other = (PlaneacionSemanal) object;
        if ((this.idPlaneacionSemanal == null && other.idPlaneacionSemanal != null) || (this.idPlaneacionSemanal != null && !this.idPlaneacionSemanal.equals(other.idPlaneacionSemanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.PlaneacionSemanal[idPlaneacionSemanal=" + idPlaneacionSemanal + "]";
    }

}
