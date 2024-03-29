/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author David
 */
@Entity
@Table(name = "examenplaneado")
@NamedQueries(
{
    @NamedQuery(name = "ExamenPlaneado.findByIdExamenPlaneado", query = "SELECT e FROM ExamenPlaneado e WHERE e.idExamenPlaneado = :id"), 
    @NamedQuery(name = "ExamenPlaneado.consultarExamenes", query = "SELECT e FROM ExamenPlaneado e"),
    @NamedQuery(name = "ExamenPlaneado.findByFecha", query = "SELECT e FROM ExamenPlaneado e WHERE e.fecha = :fecha")
})
public class ExamenPlaneado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idExamenPlaneado", nullable = false)
    private Integer idExamenPlaneado;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idExamen", referencedColumnName = "idExamen")
    @ManyToOne
    private Examen idExamen;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;
    @JoinColumn(name = "idPlaneacionSemanal", referencedColumnName = "idPlaneacionSemanal")
    @ManyToOne
    private PlaneacionSemanal idPlaneacionSemanal;

    public ExamenPlaneado() {
    }

    public ExamenPlaneado(Integer idExamenPlaneado) {
        this.idExamenPlaneado = idExamenPlaneado;
    }

    public ExamenPlaneado(Integer idExamenPlaneado, Date fecha) {
        this.idExamenPlaneado = idExamenPlaneado;
        this.fecha = fecha;
    }

    public Integer getIdExamenPlaneado() {
        return idExamenPlaneado;
    }

    public void setIdExamenPlaneado(Integer idExamenPlaneado) {
        this.idExamenPlaneado = idExamenPlaneado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Examen getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Examen idExamen) {
        this.idExamen = idExamen;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    public PlaneacionSemanal getIdPlaneacionSemanal() {
        return idPlaneacionSemanal;
    }

    public void setIdPlaneacionSemanal(PlaneacionSemanal idPlaneacionSemanal) {
        this.idPlaneacionSemanal = idPlaneacionSemanal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenPlaneado != null ? idExamenPlaneado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenPlaneado)) {
            return false;
        }
        ExamenPlaneado other = (ExamenPlaneado) object;
        if ((this.idExamenPlaneado == null && other.idExamenPlaneado != null) || (this.idExamenPlaneado != null && !this.idExamenPlaneado.equals(other.idExamenPlaneado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.ExamenPlaneado[idExamenPlaneado=" + idExamenPlaneado + "]";
    }

}
