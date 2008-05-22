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
 * @author Jorge
 */
@Entity
@Table(name = "materiaplaneada")
@NamedQueries({@NamedQuery(name = "MateriaPlaneada.findByIdMateriaPlaneada", query = "SELECT m FROM MateriaPlaneada m WHERE m.idMateriaPlaneada = :idMateriaPlaneada"), @NamedQuery(name = "MateriaPlaneada.findByMesInicio", query = "SELECT m FROM MateriaPlaneada m WHERE m.mesInicio = :mesInicio"), @NamedQuery(name = "MateriaPlaneada.findByMesFin", query = "SELECT m FROM MateriaPlaneada m WHERE m.mesFin = :mesFin")})
public class MateriaPlaneada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idMateriaPlaneada", nullable = false)
    private Integer idMateriaPlaneada;
    @Column(name = "mesInicio", nullable = false)
    private int mesInicio;
    @Column(name = "mesFin", nullable = false)
    private int mesFin;
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "idExamenMes", referencedColumnName = "idExamenMes")
    @ManyToOne
    private Examenmes idExamenMes;
    @JoinColumn(name = "idPlaneacionAnual", referencedColumnName = "idPlaneacionAnual")
    @ManyToOne
    private PlaneacionAnual idPlaneacionAnual;

    public MateriaPlaneada() {
    }

    public MateriaPlaneada(Integer idMateriaPlaneada) {
        this.idMateriaPlaneada = idMateriaPlaneada;
    }

    public MateriaPlaneada(Integer idMateriaPlaneada, int mesInicio, int mesFin) {
        this.idMateriaPlaneada = idMateriaPlaneada;
        this.mesInicio = mesInicio;
        this.mesFin = mesFin;
    }

    public Integer getIdMateriaPlaneada() {
        return idMateriaPlaneada;
    }

    public void setIdMateriaPlaneada(Integer idMateriaPlaneada) {
        this.idMateriaPlaneada = idMateriaPlaneada;
    }

    public int getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(int mesInicio) {
        this.mesInicio = mesInicio;
    }

    public int getMesFin() {
        return mesFin;
    }

    public void setMesFin(int mesFin) {
        this.mesFin = mesFin;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Examenmes getIdExamenMes() {
        return idExamenMes;
    }

    public void setIdExamenMes(Examenmes idExamenMes) {
        this.idExamenMes = idExamenMes;
    }

    public PlaneacionAnual getIdPlaneacionAnual() {
        return idPlaneacionAnual;
    }

    public void setIdPlaneacionAnual(PlaneacionAnual idPlaneacionAnual) {
        this.idPlaneacionAnual = idPlaneacionAnual;
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
