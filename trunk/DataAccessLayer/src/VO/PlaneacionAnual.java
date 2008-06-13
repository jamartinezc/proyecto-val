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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "planeacionanual")
@NamedQueries(
{
    @NamedQuery(name = "PlaneacionAnual.findByIdPlaneacionAnual", query = "SELECT p FROM PlaneacionAnual p WHERE p.idPlaneacionAnual = :id"),
    @NamedQuery(name = "PlaneacionAnual.consultarExamenesPlaneados", query = "SELECT p FROM PlaneacionAnual p")
})
public class PlaneacionAnual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPlaneacionAnual", nullable = false)
    private Integer idPlaneacionAnual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaneacionAnual")
    private Collection<MateriaPlaneada> materiaPlaneadaCollection;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @OneToOne
    private Estudiante idEstudiante;

    public PlaneacionAnual() {
    }

    public PlaneacionAnual(Integer idPlaneacionAnual) {
        this.idPlaneacionAnual = idPlaneacionAnual;
    }

    public Integer getIdPlaneacionAnual() {
        return idPlaneacionAnual;
    }

    public void setIdPlaneacionAnual(Integer idPlaneacionAnual) {
        this.idPlaneacionAnual = idPlaneacionAnual;
    }

    public Collection<MateriaPlaneada> getMateriaPlaneadaCollection() {
        return materiaPlaneadaCollection;
    }

    public void setMateriaPlaneadaCollection(Collection<MateriaPlaneada> materiaPlaneadaCollection) {
        this.materiaPlaneadaCollection = materiaPlaneadaCollection;
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
        hash += (idPlaneacionAnual != null ? idPlaneacionAnual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionAnual)) {
            return false;
        }
        PlaneacionAnual other = (PlaneacionAnual) object;
        if ((this.idPlaneacionAnual == null && other.idPlaneacionAnual != null) || (this.idPlaneacionAnual != null && !this.idPlaneacionAnual.equals(other.idPlaneacionAnual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.PlaneacionAnual[idPlaneacionAnual=" + idPlaneacionAnual + "]";
    }

}
