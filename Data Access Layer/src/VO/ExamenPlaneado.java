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
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "examenplaneado")
@NamedQueries({@NamedQuery(name = "ExamenPlaneado.findByIdExamenPlaneado", query = "SELECT e FROM ExamenPlaneado e WHERE e.idExamenPlaneado = :idExamenPlaneado")})
public class ExamenPlaneado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idExamenPlaneado", nullable = false)
    private Integer idExamenPlaneado;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private Examen codigo;
    @JoinColumn(name = "estado", referencedColumnName = "estado")
    @ManyToOne
    private Estados estado;
    @JoinColumn(name = "planeacionSemanal", referencedColumnName = "idPlaneacionSemanal")
    @ManyToOne
    private PlaneacionSemanal planeacionSemanal;

    public ExamenPlaneado() {
    }

    public ExamenPlaneado(Integer idExamenPlaneado) {
        this.idExamenPlaneado = idExamenPlaneado;
    }

    public Integer getIdExamenPlaneado() {
        return idExamenPlaneado;
    }

    public void setIdExamenPlaneado(Integer idExamenPlaneado) {
        this.idExamenPlaneado = idExamenPlaneado;
    }

    public Examen getCodigo() {
        return codigo;
    }

    public void setCodigo(Examen codigo) {
        this.codigo = codigo;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public PlaneacionSemanal getPlaneacionSemanal() {
        return planeacionSemanal;
    }

    public void setPlaneacionSemanal(PlaneacionSemanal planeacionSemanal) {
        this.planeacionSemanal = planeacionSemanal;
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
