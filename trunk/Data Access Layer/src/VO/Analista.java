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
@Table(name = "analista")
@NamedQueries({@NamedQuery(name = "Analista.findByIdAnalista", query = "SELECT a FROM Analista a WHERE a.idAnalista = :idAnalista")})
public class Analista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idAnalista", nullable = false)
    private Integer idAnalista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnalista")
    private Collection<Materia> materiaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "analista")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Analista() {
    }

    public Analista(Integer idAnalista) {
        this.idAnalista = idAnalista;
    }

    public Integer getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(Integer idAnalista) {
        this.idAnalista = idAnalista;
    }

    public Collection<Materia> getMateriaCollection() {
        return materiaCollection;
    }

    public void setMateriaCollection(Collection<Materia> materiaCollection) {
        this.materiaCollection = materiaCollection;
    }

    public Collection<ExamenSolicitado> getExamenSolicitadoCollection() {
        return examenSolicitadoCollection;
    }

    public void setExamenSolicitadoCollection(Collection<ExamenSolicitado> examenSolicitadoCollection) {
        this.examenSolicitadoCollection = examenSolicitadoCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnalista != null ? idAnalista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analista)) {
            return false;
        }
        Analista other = (Analista) object;
        if ((this.idAnalista == null && other.idAnalista != null) || (this.idAnalista != null && !this.idAnalista.equals(other.idAnalista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Analista[idAnalista=" + idAnalista + "]";
    }

}
