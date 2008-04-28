/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.liceoval.businesslayer.entities.wrappers;

import com.liceoval.businesslayer.entities.Usuario;

/** Una clase Wrapper para los usuarios para manejar el problema de los
 *  roles múltiples de los usuarios. Esta es una solución temporal mientras,
 *  en la próxima iteración volvemos eso una relación de composición.
 * 
 *  @author Sergio
 */
public class UsuarioWrapper
{
    private Usuario usuario;
    private boolean rolEstudiante;
    private boolean rolAnalista;
    private boolean rolTutor;
    private boolean rolSecretariaAcademica;
    
    /** Crea un usuario Wrapper sin ningún atributo especial. */
    public UsuarioWrapper() { }
    
    /** Crea un usuario Wrapper y establece su atributo usuario de acuerdo
     *  al parámetro entregado.
     * 
     *  @param usuario El usuario a partir del cual se debe crear el Wrapper.
     */
    
    public UsuarioWrapper(Usuario usuario) { this.usuario = usuario; }

    /** Devuelve el usuario que envuelve la clase.
     * 
     *  @return Un objeto de clase Usuario. El usuario que envuelve la clase.
     */
    
    public Usuario getUsuario() { return usuario; }

    /** Establece el objeto que envuelve la clase.
     * 
     *  @param usuario El usuario a envolver.
     */
    
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    /** Establece un valor booleana que determina si el usuario
     *  cumple el rol de estudiante.
     * 
     *  @return Un valor booleano. Verdadero si el usuario tiene el rol de
     *      estudiante, falso si no.
     */
    
    public boolean isEstudiante() { return rolEstudiante; }

    /** Establece un valor booleano que determina si el usuario cumple el rol
     *  de estudiante.
     * 
     *  @param rolEstudiante Un valor booleano, verdadero si el usuario cumple
     *      el rol de Estudiante, falso si no.
     */
    
    public void setRolEstudiante(boolean rolEstudiante) { this.rolEstudiante = rolEstudiante; }

    /** Devuelve un valor booleano que determina si el usuario cumple el rol de
     *  analista.
     * 
     *  @return Un valor booleano. Verdadero si el usuario cumple el rol de
     *      analista, falso si no.
     */
    
    public boolean isAnalista() { return rolAnalista; }

    /** Establece un valor booleano que determina si el usuario cumple el rol
     *  de analista.
     * 
     *  @param rolAnalista Un valor booleano. Verdadero si el usuario cumple el
     *      rol de analista, falso si no.
     */    
    
    public void setRolAnalista(boolean rolAnalista) { this.rolAnalista = rolAnalista; }

    /** Devuelve un valor booleano que determina si el usuario cumple el rol de
     *  tutor.
     * 
     *  @return Un valor booleano. Verdadero si el usuario cumple el rol de
     *      tutor, falso si no.
     */
    
    public boolean isTutor() { return rolTutor; }

    /** Establece un valor booleano que determina si el usuario cumple el rol
     *  de Tutor.
     * 
     *  @param rolTutor Un valor booleano. Verdadero si el usuario cumple el
     *      rol de Tutor, falso si no.
     */
    
    public void setRolTutor(boolean rolTutor) { this.rolTutor = rolTutor; }

    /** Devuelve un valor booleano que determina si el usuario cumple el rol
     *  de Secretaria Académica.
     * 
     *  @return Un valor booleano. Verdadero si el usuario cumple el rol de
     *      secretaria académica, falso si no.
     */
    
    public boolean isSecretariaAcademica() { return rolSecretariaAcademica; }

    /** Establece un valor booleano que determina si el usuario cumple el rol
     *  de Secretaria Académica.
     * 
     *  @param rolSecretariaAcademica Un valor booleano. Verdadero si el usuario
     *      cumple el rol de Secretaria Académica, falso si no.
     */
    
    public void setRolSecretariaAcademica(boolean rolSecretariaAcademica){
        this.rolSecretariaAcademica = rolSecretariaAcademica;
    }
}
