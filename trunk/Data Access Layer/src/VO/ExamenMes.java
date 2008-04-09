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
@Table(name = "examenmes")
@NamedQueries({@NamedQuery(name = "ExamenMes.findByIdExamenMes", query = "SELECT e FROM ExamenMes e WHERE e.idExamenMes = :idExamenMes"), @NamedQuery(name = "ExamenMes.findByMes", query = "SELECT e FROM ExamenMes e WHERE e.mes = :mes"), @NamedQuery(name = "ExamenMes.findByGanado", query = "SELECT e FROM ExamenMes e WHERE e.ganado = :ganado")})
public class ExamenMes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idExamenMes", nullable = false)
    private Integer idExamenMes;
    @Column(name = "mes", nullable = false)
    private int mes;
    @Column(name = "ganado", nullable = false)
    private boolean ganado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamenMes")
    private Collection<MateriaPlaneada> materiaPlaneadaCollection;
    @JoinColumn(name = "examen", referencedColumnName = "codigo")
    @ManyToOne
    private Examen examen;

    public ExamenMes() {
    }

    public ExamenMes(Integer idExamenMes) {
        this.idExamenMes = idExamenMes;
    }

    public ExamenMes(Integer idExamenMes, int mes, boolean ganado) {
        this.idExamenMes = idExamenMes;
        this.mes = mes;
        this.ganado = ganado;
    }

    public Integer getIdExamenMes() {
        return idExamenMes;
    }

    public void setIdExamenMes(Integer idExamenMes) {
        this.idExamenMes = idExamenMes;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public boolean getGanado() {
        return ganado;
    }

    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    public Collection<MateriaPlaneada> getMateriaPlaneadaCollection() {
        return materiaPlaneadaCollection;
    }

    public void setMateriaPlaneadaCollection(Collection<MateriaPlaneada> materiaPlaneadaCollection) {
        this.materiaPlaneadaCollection = materiaPlaneadaCollection;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenMes != null ? idExamenMes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenMes)) {
            return false;
        }
        ExamenMes other = (ExamenMes) object;
        if ((this.idExamenMes == null && other.idExamenMes != null) || (this.idExamenMes != null && !this.idExamenMes.equals(other.idExamenMes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.ExamenMes[idExamenMes=" + idExamenMes + "]";
    }

}
