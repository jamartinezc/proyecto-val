/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities;

/**
 *
 * @author Sergio
 */
public class Usuario
{
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String login;
    private char[] password;
    
    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombres() { return nombres; }

    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public char[] getPassword() { return password; }

    public void setPassword(char[] password) { this.password = password; }
    
    public String getRol(){
        return "";
    }
}
