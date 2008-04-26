/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.presentationlayer.control.util;

/**
 *
 * @author Administrador
 */
public class Validaciones {

    static String st = " ABCDEFGHIJKLMÑNOPQRSTUVWXYZabcdefghijklmnoñpqrstuvwxyzÁÉÍÓÚáéíóúüÜ";
    static String num= "0123456789";
    
    public static boolean nombres(String nombre)
    {
        int i,n;
        int a;
        n=nombre.length();
        
        for(i=0;i<n;i++)
        {
            a=nombre.charAt(i);
            if(st.indexOf(a) < 0)
                return false;
        }
        return true;
        
    }
    
    public static boolean numeros(String numeros)
    {
        int i,n;
        int a;
        n=numeros.length();
        
        for(i=0;i<n;i++)
        {
            a=numeros.charAt(i);
            if(st.indexOf(a) < 0)
                return false;
        }
        return true;
    
    }
    
    public static boolean correo(String correo)
    {
        int n,a,b;
        n=correo.length()-1;
       
        a=correo.indexOf("@");
        b=correo.indexOf(".");
        if(b==0||b==n||a==0||a==n||a<0||b<0)
        {return false;}
       
        return true;
    
    }
    
}
