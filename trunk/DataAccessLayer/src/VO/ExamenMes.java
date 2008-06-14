/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "examenmes")
@NamedQueries({@NamedQuery(name = "ExamenMes.findByIdExamenMes", query = "SELECT e FROM ExamenMes e WHERE e.idExamenMes = :idExamenMes"), @NamedQuery(name = "ExamenMes.findByMes", query = "SELECT e FROM ExamenMes e WHERE e.mes = :mes"), @NamedQuery(name = "ExamenMes.findByPlaneados", query = "SELECT e FROM ExamenMes e WHERE e.planeados = :planeados"), @NamedQuery(name = "ExamenMes.findByGanados", query = "SELECT e FROM ExamenMes e WHERE e.ganados = :ganados")})
public class ExamenMes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idExamenMes", nullable = false)
    private Integer idExamenMes;
    @Column(name = "mes", nullable = false)
    private int mes;
    @Column(name = "planeados", nullable = false)
    private int planeados;
    @Column(name = "ganados", nullable = false)
    private int ganados;
    @JoinColumn(name = "idMateriaPlaneada", referencedColumnName = "idMateriaPlaneada")
    @ManyToOne
    private MateriaPlaneada idMateriaPlaneada;

    public ExamenMes() {
    }

    public ExamenMes(Integer idExamenMes) {
        this.idExamenMes = idExamenMes;
    }

    public ExamenMes(Integer idExamenMes, int mes, int planeados, int ganados) {
        this.idExamenMes = idExamenMes;
        this.mes = mes;
        this.planeados = planeados;
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

    public int getplaneados() {
        return planeados;
    }

    public void setplaneados(int planeados) {
        this.planeados = planeados;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public MateriaPlaneada getIdMateriaPlaneada() {
        return idMateriaPlaneada;
    }

    public void setIdMateriaPlaneada(MateriaPlaneada idMateriaPlaneada) {
        this.idMateriaPlaneada = idMateriaPlaneada;
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
