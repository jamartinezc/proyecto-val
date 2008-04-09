/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({@NamedQuery(name = "ExamenSolicitado.findByIdExamenSolicitado", query = "SELECT e FROM ExamenSolicitado e WHERE e.idExamenSolicitado = :idExamenSolicitado"), @NamedQuery(name = "ExamenSolicitado.findByCodigo", query = "SELECT e FROM ExamenSolicitado e WHERE e.codigo = :codigo"), @NamedQuery(name = "ExamenSolicitado.findByNota", query = "SELECT e FROM ExamenSolicitado e WHERE e.nota = :nota"), @NamedQuery(name = "ExamenSolicitado.findByFecha", query = "SELECT e FROM ExamenSolicitado e WHERE e.fecha = :fecha")})
public class ExamenSolicitado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idExamenSolicitado", nullable = false)
    private Integer idExamenSolicitado;
    @Column(name = "codigo", nullable = false)
    private int codigo;
    @Column(name = "nota", nullable = false)
    private float nota;
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "analista", referencedColumnName = "idAnalista")
    @ManyToOne
    private Analista analista;
    @JoinColumn(name = "registro", referencedColumnName = "idRegistro")
    @ManyToOne
    private Registro registro;
    @JoinColumn(name = "examen", referencedColumnName = "codigo")
    @ManyToOne
    private Examen examen;
    @JoinColumn(name = "estado", referencedColumnName = "estado")
    @ManyToOne
    private Estados estado;

    public ExamenSolicitado() {
    }

    public ExamenSolicitado(Integer idExamenSolicitado) {
        this.idExamenSolicitado = idExamenSolicitado;
    }

    public ExamenSolicitado(Integer idExamenSolicitado, int codigo, float nota, Date fecha) {
        this.idExamenSolicitado = idExamenSolicitado;
        this.codigo = codigo;
        this.nota = nota;
        this.fecha = fecha;
    }

    public Integer getIdExamenSolicitado() {
        return idExamenSolicitado;
    }

    public void setIdExamenSolicitado(Integer idExamenSolicitado) {
        this.idExamenSolicitado = idExamenSolicitado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Analista getAnalista() {
        return analista;
    }

    public void setAnalista(Analista analista) {
        this.analista = analista;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
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
