/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MicroBlog.java,v 1.7 2010/04/26 21:08:21 carl-veg Exp $
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
 * Clase que representa un microblog
 */
public class MicroBlog
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre del usuario que escribió el microblog
     */
    private String usuario;

    /**
     * Es el la fecha en la que se escribió el microblog
     */
    private String fecha;

    /**
     * El texto del microblog
     */
    private String texto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo microblog
     * 
     * @param nUsuario El nombre del usuario
     * @param nTexto El texto del microblog
     * @param nFecha La fecha del microblog
     */
    public MicroBlog( String nUsuario, String nTexto, String nFecha )
    {
        usuario = nUsuario;
        texto = nTexto;
        fecha = nFecha;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la fecha del microblog
     * 
     * @return La fecha
     */
    public String darFecha( )
    {
        return fecha;
    }

    /**
     * Retorna el texto del microblog
     * 
     * @return El texto
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Retorna el nombre de usuario
     * 
     * @return El nombre del usuario
     */
    public String darUsuario( )
    {
        return usuario;
    }

}