/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({@NamedQuery(name = "ExcelenciaTaller.findByTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.taller = :taller"), @NamedQuery(name = "ExcelenciaTaller.findByPresentados", query = "SELECT e FROM ExcelenciaTaller e WHERE e.presentados = :presentados"), @NamedQuery(name = "ExcelenciaTaller.findByGanados", query = "SELECT e FROM ExcelenciaTaller e WHERE e.ganados = :ganados"), @NamedQuery(name = "ExcelenciaTaller.findByPresentadosTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.presentadosTaller = :presentadosTaller"), @NamedQuery(name = "ExcelenciaTaller.findByGanadosTaller", query = "SELECT e FROM ExcelenciaTaller e WHERE e.ganadosTaller = :ganadosTaller")})
public class ExcelenciaTaller implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "taller", nullable = false)
    private Integer taller;
    @Column(name = "presentados", nullable = false)
    private int presentados;
    @Column(name = "ganados", nullable = false)
    private int ganados;
    @Column(name = "presentadosTaller", nullable = false)
    private int presentadosTaller;
    @Column(name = "ganadosTaller", nullable = false)
    private int ganadosTaller;
    @JoinColumn(name = "taller", referencedColumnName = "idTaller", insertable = false, updatable = false)
    @OneToOne
    private Taller taller1;
    @JoinColumn(name = "alumno", referencedColumnName = "codigo")
    @ManyToOne
    private Estudiante alumno;

    public ExcelenciaTaller() {
    }

    public ExcelenciaTaller(Integer taller) {
        this.taller = taller;
    }

    public ExcelenciaTaller(Integer taller, int presentados, int ganados, int presentadosTaller, int ganadosTaller) {
        this.taller = taller;
        this.presentados = presentados;
        this.ganados = ganados;
        this.presentadosTaller = presentadosTaller;
        this.ganadosTaller = ganadosTaller;
    }

    public Integer getTaller() {
        return taller;
    }

    public void setTaller(Integer taller) {
        this.taller = taller;
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

    public Taller getTaller1() {
        return taller1;
    }

    public void setTaller1(Taller taller1) {
        this.taller1 = taller1;
    }

    public Estudiante getAlumno() {
        return alumno;
    }

    public void setAlumno(Estudiante alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taller != null ? taller.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExcelenciaTaller)) {
            return false;
        }
        ExcelenciaTaller other = (ExcelenciaTaller) object;
        if ((this.taller == null && other.taller != null) || (this.taller != null && !this.taller.equals(other.taller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.ExcelenciaTaller[taller=" + taller + "]";
    }

}
