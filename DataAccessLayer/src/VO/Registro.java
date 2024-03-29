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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "registro")
@NamedQueries(
{
    @NamedQuery(name = "Registro.consultarRegistros", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findByActivo", query = "SELECT r FROM Registro r WHERE r.activo = :activo"),
    @NamedQuery(name = "Registro.consultarUnRegistro", query = "SELECT r FROM Registro r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "Registro.findByVecesDevuelta", query = "SELECT r FROM Registro r WHERE r.vecesDevuelta = :vecesDevuelta"),
    @NamedQuery(name = "Registro.consultarRegistroAlumnoMateria", query = "SELECT r FROM Registro r WHERE r.idEstudiante = :idE AND r.idMateria = :idM")
})
public class Registro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idRegistro", nullable = false)
    private Integer idRegistro;
    @Column(name = "vecesDevuelta", nullable = false)
    private int vecesDevuelta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistro")
    private Collection<ExamenSolicitado> examenSolicitadoCollection;
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    @ManyToOne
    private Estudiante idEstudiante;
    @JoinColumn(name = "idMateria", referencedColumnName = "idMateria")
    @ManyToOne
    private Materia idMateria;

    public Registro() {
    }

    public Registro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registro(Integer idRegistro, boolean activo, int vecesDevuelta) {
        this.idRegistro = idRegistro;
        this.activo = activo;
        this.vecesDevuelta = vecesDevuelta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getVecesDevuelta() {
        return vecesDevuelta;
    }

    public void setVecesDevuelta(int vecesDevuelta) {
        this.vecesDevuelta = vecesDevuelta;
    }

    public Collection<ExamenSolicitado> getExamenSolicitadoCollection() {
        return examenSolicitadoCollection;
    }

    public void setExamenSolicitadoCollection(Collection<ExamenSolicitado> examenSolicitadoCollection) {
        this.examenSolicitadoCollection = examenSolicitadoCollection;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Registro[idRegistro=" + idRegistro + "]";
    }

}
