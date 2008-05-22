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
@Table(name = "examenmes")
@NamedQueries({@NamedQuery(name = "Examenmes.findByIdExamenMes", query = "SELECT e FROM Examenmes e WHERE e.idExamenMes = :idExamenMes"), @NamedQuery(name = "Examenmes.findByMes", query = "SELECT e FROM Examenmes e WHERE e.mes = :mes"), @NamedQuery(name = "Examenmes.findByPresentados", query = "SELECT e FROM Examenmes e WHERE e.presentados = :presentados"), @NamedQuery(name = "Examenmes.findByGanados", query = "SELECT e FROM Examenmes e WHERE e.ganados = :ganados")})
public class Examenmes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idExamenMes", nullable = false)
    private Integer idExamenMes;
    @Column(name = "mes", nullable = false)
    private int mes;
    @Column(name = "presentados", nullable = false)
    private int presentados;
    @Column(name = "ganados", nullable = false)
    private int ganados;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamenMes")
    private Collection<MateriaPlaneada> materiaPlaneadaCollection;

    public Examenmes() {
    }

    public Examenmes(Integer idExamenMes) {
        this.idExamenMes = idExamenMes;
    }

    public Examenmes(Integer idExamenMes, int mes, int presentados, int ganados) {
        this.idExamenMes = idExamenMes;
        this.mes = mes;
        this.presentados = presentados;
        this.ganados = ganados;
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

    public int getPresentados() {
        return presentados;
    }

    public void setPresentados(int presentados) {
        this.presentados = presentados;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public Collection<MateriaPlaneada> getMateriaPlaneadaCollection() {
        return materiaPlaneadaCollection;
    }

    public void setMateriaPlaneadaCollection(Collection<MateriaPlaneada> materiaPlaneadaCollection) {
        this.materiaPlaneadaCollection = materiaPlaneadaCollection;
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
        if (!(object instanceof Examenmes)) {
            return false;
        }
        Examenmes other = (Examenmes) object;
        if ((this.idExamenMes == null && other.idExamenMes != null) || (this.idExamenMes != null && !this.idExamenMes.equals(other.idExamenMes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Examenmes[idExamenMes=" + idExamenMes + "]";
    }

}
