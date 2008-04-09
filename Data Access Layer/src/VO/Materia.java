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
@NamedQueries({@NamedQuery(name = "Materia.findByCodigo", query = "SELECT m FROM Materia m WHERE m.codigo = :codigo"), @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")})
public class Materia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @JoinColumn(name = "idAnalista", referencedColumnName = "idAnalista")
    @ManyToOne
    private Analista idAnalista;
    @JoinColumn(name = "idTutor", referencedColumnName = "idTutor")
    @ManyToOne
    private Tutor idTutor;
    @JoinColumn(name = "grado", referencedColumnName = "grado")
    @ManyToOne
    private Grado grado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
    private Collection<MateriaPlaneada> materiaPlaneadaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
    private Collection<Registro> registroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
    private Collection<Examen> examenCollection;

    public Materia() {
    }

    public Materia(Integer codigo) {
        this.codigo = codigo;
    }

    public Materia(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
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
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Materia[codigo=" + codigo + "]";
    }

}
