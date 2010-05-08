/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IEscucharCambios.java,v 1.4 2010/04/26 21:08:21 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTwitter.cliente.mundo;

/**
 * 
 * Interface que especifica los métodos que se invocan cuando se recibe una respuesta del servidor
 * 
 */
public interface IEscucharCambios
{
    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha creado un cuenta
     */
    public void actualizarCrearCuenta( );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha iniciado sesión
     */
    public void actualizarIniciarSesion( );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha actualizado la lista de microblogs del usuario
     */
    public void actualizarMicroBlogs( );

    /**
     * Se invoca cuando se recibe una respuesta de error del servidor
     * 
     * @param msj El mensaje de error
     */
    public void actualizarError( String msj );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se a empezado a seguir a un usuario
     */
    public void actualizarSeguirUsuario( );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha escrito un nuevo microblog
     */
    public void actualizarEscribirMicroblog( );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha cerrado la sesión
     */
    public void actualizarCerrarSesion( );

    /**
     * Se invoca cuando se recibe un respuesta satisfactoria del servidor indicando que se ha dejado de seguir un usuario
     */
    public void actualizarDejarSeguirUsuario( );

}
