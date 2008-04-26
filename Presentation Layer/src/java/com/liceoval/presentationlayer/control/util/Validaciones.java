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

    static String st = "ABCDEFGHIJKLMÑNOPQRSTUVWXYZabcdefghijklmnoñpqrstuvwxyzÁÉÍÓÚáéíóúüÜ";
        
    public static boolean nombres(String nombre)
    {
        int i,n,j,m;
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
    
}
