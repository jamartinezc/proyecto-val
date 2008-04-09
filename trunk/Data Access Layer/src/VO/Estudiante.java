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
@NamedQueries({@NamedQuery(name = "Estudiante.findByCodigo", query = "SELECT e FROM Estudiante e WHERE e.codigo = :codigo"), @NamedQuery(name = "Estudiante.findByFechaInicioGrado", query = "SELECT e FROM Estudiante e WHERE e.fechaInicioGrado = :fechaInicioGrado")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Column(name = "fechaInicioGrado", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<PlaneacionSemanal> planeacionSemanalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<Padre> padreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigo")
    private Collection<Registro> registroCollection;
    @JoinColumn(name = "taller", referencedColumnName = "idTaller")
    @ManyToOne
    private Taller taller;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @OneToOne
    private Usuario idUsuario;
    @JoinColumn(name = "grado", referencedColumnName = "grado")
    @ManyToOne
    private Grado grado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codigo")
    private PlaneacionAnual planeacionAnual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<ExcelenciaTaller> excelenciaTallerCollection;

    public Estudiante() {
    }

    public Estudiante(Integer codigo) {
        this.codigo = codigo;
    }

    public Estudiante(Integer codigo, Date fechaInicioGrado) {
        this.codigo = codigo;
        this.fechaInicioGrado = fechaInicioGrado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Estudiante[codigo=" + codigo + "]";
    }

}
