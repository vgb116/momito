/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ProtocoloComunicacion.java,v 1.18 2010/04/26 21:08:21 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.comun.mundo;

/**
 * Interfaz que contiene los mensajes del protocolo de comunicación
 */
public interface ProtocoloComunicacion
{
    /**
     * Indica que se desea crear un cuenta
     */
    public static final String CREAR_CUENTA = "CREAR_CUENTA";

    /**
     * Indica el número de parámetros esperados para el comando crear cuenta
     */
    public static final int TOTAL_PARAMETROS_CREAR_CUENTA = 4;

    /**
     * Indica que se ha creado una cuenta de forma satisfactoria
     */
    public static final String CREAR_CUENTA_OK = "CREAR_CUENTA_OK";

    /**
     * Indica que se desea iniciar sesión
     */
    public final static String INICIAR_SESION = "INICIAR_SESION";

    /**
     * Indica el número de parámetros esperados para el comando iniciar sesión
     */
    public static final int TOTAL_PARAMETROS_INICIAR_SESION = 2;

    /**
     * Indica que el usuario no se ha iniciado sesión
     */
    public final static String NO_LOGIN = "NO_LOGIN";

    /**
     * Indica que se ha iniciado sesión de forma correcta
     */
    public final static String INICIAR_SESION_OK = "INICIAR_SESION_OK";

    /**
     * Indica que se desea cerrar sesión
     */
    public final static String CERRAR_SESION = "CERRAR_SESION";

    /**
     * Indica que se ha cerrado sesión de forma correcta
     */
    public final static String CERRAR_SESION_OK = "CERRAR_SESION_OK";

    /**
     * Indica que se desea consultar los microblogs disponibles para un usuario
     */
    public final static String CONSULTAR_BLOGS = "CONSULTAR_BLOGS";

    /**
     * Indica se ha consultado de forma correcta los microblogs para un usuario
     */
    public final static String CONSULTAR_BLOGS_OK = "CONSULTAR_BLOGS_OK";

    /**
     * Indicia que se desea seguir un usuario
     */
    public final static String SEGUIR_USUARIO = "SEGUIR_USUARIO";

    /**
     * Indica el número de parámetros esperados para el comando seguir usuario
     */
    public static final int TOTAL_PARAMETROS_SEGUIR_USUARIO = 1;

    /**
     * Indica que se ha registra la petición para seguir un usuario de forma correcta
     */
    public final static String SEGUIR_USUARIO_OK = "SEGUIR_USUARIO_OK";

    /**
     * Indica que se desea registrar un nuevo microblog
     */
    public final static String ESCRIBIR_MICROBLOG = "ESCRIBIR_MENSAJE";

    /**
     * Indica el número de parámetros esperados para el comando escribir microblog
     */
    public static final int TOTAL_PARAMETROS_ESCRIBIR_MICROBLOG = 1;

    /**
     * Indica que se ha registrado un microblog de forma correcta
     */
    public static final String ESCRIBIR_MICROBLOG_OK = "ESCRIBIR_MICROBLOG_OK";

    /**
     * Indicia que ha habido un problema procesando un mensaje
     */
    public final static String ERROR = "ERROR";

    /**
     * Indica que se desea dejar se seguir un usuario
     */
    public static final String DEJAR_SEGUIR_USUARIO = "DEJAR_SEGUIR_USUARIO";

    /**
     * Indica el número de parámetros esperados para el comando dejar de seguir usuario
     */
    public static final int TOTAL_PARAMETROS_DEJAR_SEGUIR_USUARIO = 1;

    /**
     * Indica que ha dejado de seguir un usuario de forma correcta
     */
    public static final String DEJAR_SEGUIR_USUARIO_OK = "DEJAR_SEGUIR_USUARIO_OK";

    /**
     * Es la cadena de caracteres para separar los parámetros
     */
    public static final String SEPARADOR_PARAMETROS = ";;;";

    /**
     * Es la cadena de caracteres para separar los campos en un parámetro
     */
    public static final String SEPARADOR_CAMPOS = ":::";
}
