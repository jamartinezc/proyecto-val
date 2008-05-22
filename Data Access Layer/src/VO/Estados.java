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
@NamedQueries({
    @NamedQuery(name = "Estados.findByIdEstado", query = "SELECT e FROM Estados e WHERE e.idEstado = :id"),
    @NamedQuery(name = "Estados.findByNombre", query = "SELECT e FROM Estados e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estados.consultarEstados", query = "SELECT e FROM Estados e")
})
public class Estados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idEstado", nullable = false)
    private Integer idEstado;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<ExamenPlaneado> examenPlaneadoCollection;

    public Estados() {
    }

    public Estados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados(Integer idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
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
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Estados[idEstado=" + idEstado + "]";
    }

}
