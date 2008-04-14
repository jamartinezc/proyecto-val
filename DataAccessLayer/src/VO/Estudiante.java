/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "estudiante")
@NamedQueries({@NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"), @NamedQuery(name = "Estudiante.findByFechaInicioGrado", query = "SELECT e FROM Estudiante e WHERE e.fechaInicioGrado = :fechaInicioGrado")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idEstudiante", nullable = false)
    private Integer idEstudiante;
    @Column(name = "fechaInicioGrado", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<PlaneacionSemanal> planeacionSemanalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<Padre> padreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<Registro> registroCollection;
    @JoinColumn(name = "idTaller", referencedColumnName = "idTaller")
    @ManyToOne
    private Taller idTaller;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @OneToOne
    private Usuario idUsuario;
    @JoinColumn(name = "idGrado", referencedColumnName = "idGrado")
    @ManyToOne
    private Grado idGrado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private PlaneacionAnual planeacionAnual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
    private Collection<ExcelenciaTaller> excelenciaTallerCollection;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(Integer idEstudiante, Date fechaInicioGrado) {
        this.idEstudiante = idEstudiante;
        this.fechaInicioGrado = fechaInicioGrado;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Date getFechaInicioGrado() {
        return fechaInicioGrado;
    }

    public void setFechaInicioGrado(Date fechaInicioGrado) {
        this.fechaInicioGrado = fechaInicioGrado;
    }

    public Collection<PlaneacionSemanal> getPlaneacionSemanalCollection() {
        return planeacionSemanalCollection;
    }

    public void setPlaneacionSemanalCollection(Collection<PlaneacionSemanal> planeacionSemanalCollection) {
        this.planeacionSemanalCollection = planeacionSemanalCollection;
    }

    public Collection<Padre> getPadreCollection() {
        return padreCollection;
    }

    public void setPadreCollection(Collection<Padre> padreCollection) {
        this.padreCollection = padreCollection;
    }

    public Collection<ExamenSolicitado> getExamenSolicitadoCollection() {
        return examenSolicitadoCollection;
    }

    public void setExamenSolicitadoCollection(Collection<ExamenSolicitado> examenSolicitadoCollection) {
        this.examenSolicitadoCollection = examenSolicitadoCollection;
    }

    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    public Taller getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Taller idTaller) {
        this.idTaller = idTaller;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public PlaneacionAnual getPlaneacionAnual() {
        return planeacionAnual;
    }

    public void setPlaneacionAnual(PlaneacionAnual planeacionAnual) {
        this.planeacionAnual = planeacionAnual;
    }

    public Collection<ExcelenciaTaller> getExcelenciaTallerCollection() {
        return excelenciaTallerCollection;
    }

    public void setExcelenciaTallerCollection(Collection<ExcelenciaTaller> excelenciaTallerCollection) {
        this.excelenciaTallerCollection = excelenciaTallerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Estudiante[idEstudiante=" + idEstudiante + "]";
    }

}
