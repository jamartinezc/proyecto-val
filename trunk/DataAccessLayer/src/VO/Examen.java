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
 * @author David
 */
@Entity
@Table(name = "examen")
@NamedQueries(
{
    @NamedQuery(name = "Examen.findByIdExamen", query = "SELECT e FROM Examen e WHERE e.idExamen = :id"),
    @NamedQuery(name = "Examen.findByTema", query = "SELECT e FROM Examen e WHERE e.tema = :tema"),
    @NamedQuery(name = "Examen.consultarExamenes", query = "SELECT e FROM Examen e")
})
public class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idExamen", nullable = false)
    private Integer idExamen;
    @Column(name = "tema", nullable = false)
    private String tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria")
    @ManyToOne
    private Materia idMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private Collection<ExamenPlaneado> examenPlaneadoCollection;

    public Examen() {
    }

    public Examen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Examen(Integer idExamen, String tema) {
        this.idExamen = idExamen;
        this.tema = tema;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Collection<ExamenSolicitado> getExamenSolicitadoCollection() {
        return examenSolicitadoCollection;
    }

    public void setExamenSolicitadoCollection(Collection<ExamenSolicitado> examenSolicitadoCollection) {
        this.examenSolicitadoCollection = examenSolicitadoCollection;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
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
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Examen[idExamen=" + idExamen + "]";
    }

}
