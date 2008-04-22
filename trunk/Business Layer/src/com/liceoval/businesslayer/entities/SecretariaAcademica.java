/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Sergio
 */
public class SecretariaAcademica extends Usuario
{
    private int idSecretariaAcademica;

    @Override
    public String getRol() { return "Secretaria Académica"; }

    public int getIdSecretariaAcademica() { return idSecretariaAcademica; }

    public void setIdSecretariaAcademica(int idSecretariaAcademica) { this.idSecretariaAcademica = idSecretariaAcademica; }
}
