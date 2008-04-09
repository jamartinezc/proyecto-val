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
@Table(name = "estados")
@NamedQueries({@NamedQuery(name = "Estados.findByEstado", query = "SELECT e FROM Estados e WHERE e.estado = :estado"), @NamedQuery(name = "Estados.findByNombre", query = "SELECT e FROM Estados e WHERE e.nombre = :nombre")})
public class Estados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "estado", nullable = false)
    private Integer estado;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<ExamenPlaneado> examenPlaneadoCollection;

    public Estados() {
    }

    public Estados(Integer estado) {
        this.estado = estado;
    }

    public Estados(Integer estado, String nombre) {
        this.estado = estado;
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<ExamenSolicitado> getExamenSolicitadoCollection() {
        return examenSolicitadoCollection;
    }

    public void setExamenSolicitadoCollection(Collection<ExamenSolicitado> examenSolicitadoCollection) {
        this.examenSolicitadoCollection = examenSolicitadoCollection;
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
        hash += (estado != null ? estado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.estado == null && other.estado != null) || (this.estado != null && !this.estado.equals(other.estado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Estados[estado=" + estado + "]";
    }

}
