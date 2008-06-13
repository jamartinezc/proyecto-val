/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "variablesglobales")
@NamedQueries({@NamedQuery(name = "VariablesGlobales.findByCampo", query = "SELECT v FROM VariablesGlobales v WHERE v.campo = :campo"), @NamedQuery(name = "VariablesGlobales.findByValor", query = "SELECT v FROM VariablesGlobales v WHERE v.valor = :valor")})
public class VariablesGlobales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "campo", nullable = false)
    private String campo;
    @Column(name = "valor", nullable = false)
    private String valor;

    public VariablesGlobales() {
    }

    public VariablesGlobales(String campo) {
        this.campo = campo;
    }

    public VariablesGlobales(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campo != null ? campo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VariablesGlobales)) {
            return false;
        }
        VariablesGlobales other = (VariablesGlobales) object;
        if ((this.campo == null && other.campo != null) || (this.campo != null && !this.campo.equals(other.campo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VO.VariablesGlobales[campo=" + campo + "]";
    }

}
