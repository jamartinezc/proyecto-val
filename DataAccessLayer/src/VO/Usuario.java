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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "usuario")
@NamedQueries(
{
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.consultarUsuarios", query = "SELECT u FROM Usuario u ORDER BY u.apellidos"),
    @NamedQuery(name = "Usuario.consultarUnUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :id"),
    @NamedQuery(name = "Usuario.findByLoginAndByClave", query = "SELECT p FROM Usuario p WHERE p.login = :log AND p.clave = :pass")
})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "clave", nullable = false)
    private String clave;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Estudiante estudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Tutor> tutorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<SecretariaAcademica> secretariaAcademicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Analista> analistaCollection;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombres, String apellidos, String login, String clave) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.login = login;
        this.clave = clave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Collection<Tutor> getTutorCollection() {
        return tutorCollection;
    }

    public void setTutorCollection(Collection<Tutor> tutorCollection) {
        this.tutorCollection = tutorCollection;
    }

    public Collection<SecretariaAcademica> getSecretariaAcademicaCollection() {
        return secretariaAcademicaCollection;
    }

    public void setSecretariaAcademicaCollection(Collection<SecretariaAcademica> secretariaAcademicaCollection) {
        this.secretariaAcademicaCollection = secretariaAcademicaCollection;
    }

    public Collection<Analista> getAnalistaCollection() {
        return analistaCollection;
    }

    public void setAnalistaCollection(Collection<Analista> analistaCollection) {
        this.analistaCollection = analistaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.Usuario[idUsuario=" + idUsuario + "]";
    }

}
