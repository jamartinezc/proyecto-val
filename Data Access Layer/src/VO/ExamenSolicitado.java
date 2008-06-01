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
 * @author Jorge
 */
@Entity
@Table(name = "examensolicitado")
@NamedQueries({
    @NamedQuery(name = "ExamenSolicitado.consultarUnExamenSolicitado", query = "SELECT e FROM ExamenSolicitado e WHERE e.idExamenSolicitado = :id"),
    @NamedQuery(name = "ExamenSolicitado.findByNota", query = "SELECT e FROM ExamenSolicitado e WHERE e.nota = :nota"),
    @NamedQuery(name = "ExamenSolicitado.consultarExamenesSolicitados", query = "SELECT e FROM ExamenSolicitado e"),
    @NamedQuery(name = "ExamenSolicitado.consultarExamenSolicitadoEspecifico", query = "SELECT p from ExamenSolicitado p WHERE p.idEstudiante = :estu AND p.idAnalista = :analis AND p.idRegistro = :reg AND p.idExamen = :exam"),
    @NamedQuery(name = "ExamenSolicitado.findByFecha", query = "SELECT e FROM ExamenSolicitado e WHERE e.fecha = :fecha")
})
public class ExamenSolicitado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idExamenSolicitado", nullable = false)
    private Integer idExamenSolicitado;
    @Column(name = "nota", nullable = false)
    private float nota;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @ManyToOne
    private Estudiante idEstudiante;
    @JoinColumn(name = "idAnalista", referencedColumnName = "idAnalista")
    @ManyToOne
    private Analista idAnalista;
    @JoinColumn(name = "idRegistro", referencedColumnName = "idRegistro")
    @ManyToOne
    private Registro idRegistro;
    @JoinColumn(name = "idExamen", referencedColumnName = "idExamen")
    @ManyToOne
    private Examen idExamen;
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    @ManyToOne
    private Estados idEstado;

    public ExamenSolicitado() {
    }

    public ExamenSolicitado(Integer idExamenSolicitado) {
        this.idExamenSolicitado = idExamenSolicitado;
    }

    public ExamenSolicitado(Integer idExamenSolicitado, float nota, Date fecha) {
        this.idExamenSolicitado = idExamenSolicitado;
        this.nota = nota;
        this.fecha = fecha;
    }

    public Integer getIdExamenSolicitado() {
        return idExamenSolicitado;
    }

    public void setIdExamenSolicitado(Integer idExamenSolicitado) {
        this.idExamenSolicitado = idExamenSolicitado;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Analista getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(Analista idAnalista) {
        this.idAnalista = idAnalista;
    }

    public Registro getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Registro idRegistro) {
        this.idRegistro = idRegistro;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenSolicitado != null ? idExamenSolicitado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenSolicitado)) {
            return false;
        }
        ExamenSolicitado other = (ExamenSolicitado) object;
        if ((this.idExamenSolicitado == null && other.idExamenSolicitado != null) || (this.idExamenSolicitado != null && !this.idExamenSolicitado.equals(other.idExamenSolicitado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.ExamenSolicitado[idExamenSolicitado=" + idExamenSolicitado + "]";
    }

}
