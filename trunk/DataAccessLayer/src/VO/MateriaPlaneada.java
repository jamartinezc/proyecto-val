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
@Table(name = "materiaplaneada")
@NamedQueries(
{
    @NamedQuery(name = "MateriaPlaneada.findByIdMateriaPlaneada", query = "SELECT m FROM MateriaPlaneada m WHERE m.idMateriaPlaneada = :id"),
    @NamedQuery(name = "MateriaPlaneada.consultarPorIdMAteriaIdPlan", query = "SELECT m FROM MateriaPlaneada m WHERE m.idMateriaPlaneada = :idM AND m.idPlaneacionAnual = :idP"),
    @NamedQuery(name = "MateriaPlaneada.consultarMateriasPlaneadas", query = "SELECT m FROM MateriaPlaneada m")
})
public class MateriaPlaneada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idMateriaPlaneada", nullable = false)
    private Integer idMateriaPlaneada;
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "idPlaneacionAnual", referencedColumnName = "idPlaneacionAnual")
    @ManyToOne
    private PlaneacionAnual idPlaneacionAnual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateriaPlaneada")
    private Collection<ExamenMes> examenMesCollection;

    public MateriaPlaneada() {
    }

    public MateriaPlaneada(Integer idMateriaPlaneada) {
        this.idMateriaPlaneada = idMateriaPlaneada;
    }

    public Integer getIdMateriaPlaneada() {
        return idMateriaPlaneada;
    }

    public void setIdMateriaPlaneada(Integer idMateriaPlaneada) {
        this.idMateriaPlaneada = idMateriaPlaneada;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public PlaneacionAnual getIdPlaneacionAnual() {
        return idPlaneacionAnual;
    }

    public void setIdPlaneacionAnual(PlaneacionAnual idPlaneacionAnual) {
        this.idPlaneacionAnual = idPlaneacionAnual;
    }

    public Collection<ExamenMes> getExamenMesCollection() {
        return examenMesCollection;
    }

    public void setExamenMesCollection(Collection<ExamenMes> examenMesCollection) {
        this.examenMesCollection = examenMesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaPlaneada != null ? idMateriaPlaneada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPlaneada)) {
            return false;
        }
        MateriaPlaneada other = (MateriaPlaneada) object;
        if ((this.idMateriaPlaneada == null && other.idMateriaPlaneada != null) || (this.idMateriaPlaneada != null && !this.idMateriaPlaneada.equals(other.idMateriaPlaneada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.MateriaPlaneada[idMateriaPlaneada=" + idMateriaPlaneada + "]";
    }

}
