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
@Table(name = "secretariaacademica")
@NamedQueries({
    @NamedQuery(name = "SecretariaAcademica.findByIdSecretariaAcademica", query = "SELECT s FROM SecretariaAcademica s WHERE s.idSecretariaAcademica = :id"),
    @NamedQuery(name = "SecretariaAcademica.consultarSecretariasAcademicas", query = "SELECT a FROM SecretariaAcademica a"),
    @NamedQuery(name = "SecretariaAcademica.consultarPorIdUsuario", query = "SELECT a FROM SecretariaAcademica a WHERE a.idUsuario = :id")
})
public class SecretariaAcademica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idSecretariaAcademica", nullable = false)
    private Integer idSecretariaAcademica;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public SecretariaAcademica() {
    }

    public SecretariaAcademica(Integer idSecretariaAcademica) {
        this.idSecretariaAcademica = idSecretariaAcademica;
    }

    public Integer getIdSecretariaAcademica() {
        return idSecretariaAcademica;
    }

    public void setIdSecretariaAcademica(Integer idSecretariaAcademica) {
        this.idSecretariaAcademica = idSecretariaAcademica;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSecretariaAcademica != null ? idSecretariaAcademica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecretariaAcademica)) {
            return false;
        }
        SecretariaAcademica other = (SecretariaAcademica) object;
        if ((this.idSecretariaAcademica == null && other.idSecretariaAcademica != null) || (this.idSecretariaAcademica != null && !this.idSecretariaAcademica.equals(other.idSecretariaAcademica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.SecretariaAcademica[idSecretariaAcademica=" + idSecretariaAcademica + "]";
    }

}
