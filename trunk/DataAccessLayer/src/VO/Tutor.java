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
@Table(name = "tutor")
@NamedQueries(
{
    @NamedQuery(name = "Tutor.consultarUnTutor", query = "SELECT a FROM Tutor a WHERE a.idTutor = :id"),
    @NamedQuery(name = "Tutor.consultarTutores", query = "SELECT a FROM Tutor a"),
    @NamedQuery(name = "Tutor.consultarPorIdUsuario", query = "SELECT a FROM Tutor a WHERE a.idUsuario = :id")
})
public class Tutor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idTutor", nullable = false)
    private Integer idTutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTutor")
    private Collection<Taller> tallerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTutor")
    private Collection<Materia> materiaCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Tutor() {
    }

    public Tutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Collection<Taller> getTallerCollection() {
        return tallerCollection;
    }

    public void setTallerCollection(Collection<Taller> tallerCollection) {
        this.tallerCollection = tallerCollection;
    }

    public Collection<Materia> getMateriaCollection() {
        return materiaCollection;
    }

    public void setMateriaCollection(Collection<Materia> materiaCollection) {
        this.materiaCollection = materiaCollection;
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
        hash += (idTutor != null ? idTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Tutor[idTutor=" + idTutor + "]";
    }

}
