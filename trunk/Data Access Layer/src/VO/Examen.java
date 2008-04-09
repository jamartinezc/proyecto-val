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
@Table(name = "examen")
@NamedQueries({@NamedQuery(name = "Examen.findByCodigo", query = "SELECT e FROM Examen e WHERE e.codigo = :codigo"), @NamedQuery(name = "Examen.findByTema", query = "SELECT e FROM Examen e WHERE e.tema = :tema")})
public class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Column(name = "tema", nullable = false)
    private String tema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examen")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examen")
    private Collection<ExamenMes> examenMesCollection;
    @JoinColumn(name = "materia", referencedColumnName = "codigo")
    @ManyToOne
    private Materia materia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<ExamenPlaneado> examenPlaneadoCollection;

    public Examen() {
    }

    public Examen(Integer codigo) {
        this.codigo = codigo;
    }

    public Examen(Integer codigo, String tema) {
        this.codigo = codigo;
        this.tema = tema;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public Collection<ExamenMes> getExamenMesCollection() {
        return examenMesCollection;
    }

    public void setExamenMesCollection(Collection<ExamenMes> examenMesCollection) {
        this.examenMesCollection = examenMesCollection;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Examen[codigo=" + codigo + "]";
    }

}
