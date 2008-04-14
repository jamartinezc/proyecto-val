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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "materia")
@NamedQueries({@NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria"), @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idMateria", nullable = false)
    private Integer idMateria;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @JoinColumn(name = "idAnalista", referencedColumnName = "idAnalista")
    @ManyToOne
    private Analista idAnalista;
    @JoinColumn(name = "idTutor", referencedColumnName = "idTutor")
    @ManyToOne
    private Tutor idTutor;
    @JoinColumn(name = "idGrado", referencedColumnName = "idGrado")
    @ManyToOne
    private Grado idGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private Collection<MateriaPlaneada> materiaPlaneadaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private Collection<Registro> registroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
    private Collection<Examen> examenCollection;

    public Materia() {
    }

    public Materia(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(Integer idMateria, String nombre) {
        this.idMateria = idMateria;
        this.nombre = nombre;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Analista getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(Analista idAnalista) {
        this.idAnalista = idAnalista;
    }

    public Tutor getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Tutor idTutor) {
        this.idTutor = idTutor;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    public Collection<MateriaPlaneada> getMateriaPlaneadaCollection() {
        return materiaPlaneadaCollection;
    }

    public void setMateriaPlaneadaCollection(Collection<MateriaPlaneada> materiaPlaneadaCollection) {
        this.materiaPlaneadaCollection = materiaPlaneadaCollection;
    }

    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Materia[idMateria=" + idMateria + "]";
    }

}
