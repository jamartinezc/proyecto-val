/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataaccesslayer;

import VO.*;
import CRUD.*;
import java.util.List;
/**
 *
 * @author Sergio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CRUD query = new CRUD();
        
        /*Gama gamita = new Gama();
        ProductoElemento prod = new ProductoElemento();
        Adicional adi = new Adicional();
        Pais mio = new Pais();*/
        Usuario usuario = new Usuario();
        List lista;
        lista = query.consultarUsuario("dgomezt", "nhujms23");
        
        /*lista = query.consultarGama();
        gamita = (Gama) lista.get(2);
        System.out.println(gamita.getNombre());
        
        lista = query.consultarProductoElemento();*/
        usuario = (Usuario) lista.get(0);
        System.out.println(usuario.getNombres());
        /*
        lista = query.consultarAdicional();
        adi = (Adicional) lista.get(2);
        System.out.println(adi.getNombre());
        
        lista = query.consultarPais();
        mio = (Pais) lista.get(2);
        System.out.println(mio.getNombre());*/
        
    }

}
