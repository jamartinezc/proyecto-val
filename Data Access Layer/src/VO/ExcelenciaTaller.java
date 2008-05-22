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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "excelenciataller")
@NamedQueries({@NamedQuery(name = "ExcelenciaTaller.findByIdTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.idTaller = :idTaller"), @NamedQuery(name = "ExcelenciaTaller.findByPresentados", query = "SELECT e FROM ExcelenciaTaller e WHERE e.presentados = :presentados"), @NamedQuery(name = "ExcelenciaTaller.findByGanados", query = "SELECT e FROM ExcelenciaTaller e WHERE e.ganados = :ganados"), @NamedQuery(name = "ExcelenciaTaller.findByPresentadosTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.presentadosTaller = :presentadosTaller"), @NamedQuery(name = "ExcelenciaTaller.findByGanadosTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.ganadosTaller = :ganadosTaller")})
public class ExcelenciaTaller implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idTaller", nullable = false)
    private Integer idTaller;
    @Column(name = "presentados", nullable = false)
    private int presentados;
    @Column(name = "ganados", nullable = false)
    private int ganados;
    @Column(name = "presentadosTaller", nullable = false)
    private int presentadosTaller;
    @Column(name = "ganadosTaller", nullable = false)
    private int ganadosTaller;
    @JoinColumn(name = "idTaller", referencedColumnName = "idTaller", insertable = false, updatable = false)
    @OneToOne
    private Taller taller;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @ManyToOne
    private Estudiante idEstudiante;

    public ExcelenciaTaller() {
    }

    public ExcelenciaTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public ExcelenciaTaller(Integer idTaller, int presentados, int ganados, int presentadosTaller, int ganadosTaller) {
        this.idTaller = idTaller;
        this.presentados = presentados;
        this.ganados = ganados;
        this.presentadosTaller = presentadosTaller;
        this.ganadosTaller = ganadosTaller;
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
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

    public int getPresentadosTaller() {
        return presentadosTaller;
    }

    public void setPresentadosTaller(int presentadosTaller) {
        this.presentadosTaller = presentadosTaller;
    }

    public int getGanadosTaller() {
        return ganadosTaller;
    }

    public void setGanadosTaller(int ganadosTaller) {
        this.ganadosTaller = ganadosTaller;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaller != null ? idTaller.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExcelenciaTaller)) {
            return false;
        }
        ExcelenciaTaller other = (ExcelenciaTaller) object;
        if ((this.idTaller == null && other.idTaller != null) || (this.idTaller != null && !this.idTaller.equals(other.idTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.ExcelenciaTaller[idTaller=" + idTaller + "]";
    }

}
