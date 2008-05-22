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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "taller")
@NamedQueries({
    @NamedQuery(name = "Taller.findByIdTaller", query = "SELECT t FROM Taller t WHERE t.idTaller = :id"),
    @NamedQuery(name = "Taller.consultarTalleres", query = "SELECT a FROM Taller a"),
    @NamedQuery(name = "Taller.consultarPorIdTutor", query = "SELECT a FROM Taller a WHERE a.idTutor = :id")
})
public class Taller implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idTaller", nullable = false)
    private Integer idTaller;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "taller1")
    private Taller taller;
    @JoinColumn(name = "idTaller", referencedColumnName = "idTaller", insertable = false, updatable = false)
    @OneToOne
    private Taller taller1;
    @JoinColumn(name = "idTutor", referencedColumnName = "idTutor")
    @OneToOne
    private Tutor idTutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTaller")
    private Collection<Estudiante> estudianteCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "taller")
    private ExcelenciaTaller excelenciaTaller;

    public Taller() {
    }

    public Taller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Taller getTaller1() {
        return taller1;
    }

    public void setTaller1(Taller taller1) {
        this.taller1 = taller1;
    }

    public Tutor getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Tutor idTutor) {
        this.idTutor = idTutor;
    }

    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    public ExcelenciaTaller getExcelenciaTaller() {
        return excelenciaTaller;
    }

    public void setExcelenciaTaller(ExcelenciaTaller excelenciaTaller) {
        this.excelenciaTaller = excelenciaTaller;
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
        if (!(object instanceof Taller)) {
            return false;
        }
        Taller other = (Taller) object;
        if ((this.idTaller == null && other.idTaller != null) || (this.idTaller != null && !this.idTaller.equals(other.idTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Taller[idTaller=" + idTaller + "]";
    }

}
