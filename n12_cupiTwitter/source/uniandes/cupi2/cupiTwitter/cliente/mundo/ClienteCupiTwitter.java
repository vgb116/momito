/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ClienteCupiTwitter.java,v 1.18 2010/04/27 13:00:41 carl-veg Exp $
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

import java.net.Socket;
import java.util.ArrayList;

import uniandes.cupi2.cupiTwitter.comun.mundo.ProtocoloComunicacion;

/**
 * Clase que representa un cliente del servicio de cupitwitter
 */
public class ClienteCupiTwitter
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el nombre del usuario
     */
    private String nombreUsuario;

    /**
     * Es el atributo para hacer el manejo de las comunicaciones
     */
    private ManejadorComunicacionesCliente manejadorComunicaciones;

    /**
     * Es el atributo que referencia a quien se debe notificar cuando se realicen cambios
     */
    private IEscucharCambios escuchaCambios;

    /**
     * Es la lista de microblogs que el cliente puede leer
     */
    private ArrayList<MicroBlog> microblogs;

    /**
     * El el último comando enviado
     */
    private String ultimoComandoEnviado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicia un cliente del servicio cupitwitter
     * @param nEscuchaCambios El objeto que se debe notificar cuando se obtenga una respuesta desde el servidor
     * @throws CupiTwitterException en caso de encontrar un error conectando con el servidor
     */
    public ClienteCupiTwitter( IEscucharCambios nEscuchaCambios ) throws CupiTwitterException
    {
        try
        {
            manejadorComunicaciones = new ManejadorComunicacionesCliente( new Socket( "localhost", 9999 ) );
        }
        catch( Exception e )
        {
            throw new CupiTwitterException( "Se presentaron problemas con la conexión al servidor. " + e.getMessage( ) );
        }
        escuchaCambios = nEscuchaCambios;
        microblogs = new ArrayList<MicroBlog>( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la sesión de usuario en el servidor
     * @param usuario El nombre de usuario
     * @param pwd La contraseña del usuario
     */
    public void iniciarSesion( String usuario, String pwd )
    {
        String[] params = { usuario, pwd };
        nombreUsuario = usuario;
        enviarMensaje( new Mensaje( ProtocoloComunicacion.INICIAR_SESION, params ) );
    }

    /**
     * Crear una cuenta en el servidor
     * @param usuario El nickname de usuario
     * @param nombre Los nombres del usuario
     * @param apellidos Los apellidos del usuario
     * @param pwd La contraseña del usuario
     */
    public void crearCuenta( String usuario, String nombre, String apellidos, String pwd )
    {
        String[] params = { usuario, nombre, apellidos, pwd };
        nombreUsuario = usuario;
        enviarMensaje( new Mensaje( ProtocoloComunicacion.CREAR_CUENTA, params ) );
    }

    /**
     * Consulta la lista de microblogs en el servidor
     */
    public void consultarMibroblogs( )
    {
        enviarMensaje( new Mensaje( ProtocoloComunicacion.CONSULTAR_BLOGS ) );
    }

    /**
     * Registra el usuario que ha iniciado sesión como un seguidor del usuario que se recibe como parámetro
     * @param usuario El nombre del usuario a seguir
     */
    public void seguirUsuario( String usuario )
    {
        String[] params = { usuario };
        enviarMensaje( new Mensaje( ProtocoloComunicacion.SEGUIR_USUARIO, params ) );
    }

    /**
     * Elimina el usuario que ha iniciado sesión como un seguidor del usuario que se recibe como parámetro
     * @param usuario El nombre del usuario a seguir
     */
    public void dejarSeguirUsuario( String usuario )
    {
        String[] params = { usuario };
        enviarMensaje( new Mensaje( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO, params ) );
    }

    /**
     * Registra un microblog para el usuario
     * @param texto El texto del microblog
     */
    public void escribirMicroBlogs( String texto )
    {
        String[] params = { texto };
        enviarMensaje( new Mensaje( ProtocoloComunicacion.ESCRIBIR_MICROBLOG, params ) );
    }

    /**
     * Cierra la sesión del usuario
     */
    public void cerrarSesion( )
    {
        enviarMensaje( new Mensaje( ProtocoloComunicacion.CERRAR_SESION ) );
    }

    /**
     * Envía un mensaje al servidor de forma asíncrona
     * @param mensaje El mensaje a enviar
     */
    public void enviarMensaje( Mensaje mensaje )
    {
        ThreadComunicacion threadComunicacion = new ThreadComunicacion( manejadorComunicaciones, mensaje, this );
        ultimoComandoEnviado = mensaje.darComando( );
        threadComunicacion.start( );
    }

    /**
     * Método que se procesa los mensajes que llegan del servidor invocando el método respectivo para cada comando
     * @param msj El mensaje
     * @throws ErrorProtocoloException Si existe un error en el protocolo de comunicación
     */
    public void nuevoMensaje( Mensaje msj ) throws ErrorProtocoloException
    {
        boolean error = false;
        if( msj.darComando( ).contains( ProtocoloComunicacion.ERROR ) )
        {
            escuchaCambios.actualizarError( msj.darParametro( 0 ) );
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.CREAR_CUENTA_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.CREAR_CUENTA ) )
            {
                error = true;
            }
            else
                escuchaCambios.actualizarCrearCuenta( );
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.INICIAR_SESION_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.INICIAR_SESION ) )
            {
                error = true;
            }
            else
            {
                escuchaCambios.actualizarIniciarSesion( );
            }
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.CONSULTAR_BLOGS_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.CONSULTAR_BLOGS ) )
            {
                error = true;
            }
            else
            {
                microblogs.clear( );
                ArrayList parametros = msj.darParametros( );
                for( int i = 0; i < parametros.size( ); i++ )
                {
                    agregarMicroBlog( ( String )parametros.get( i ) );
                }
                escuchaCambios.actualizarMicroBlogs( );
            }
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.SEGUIR_USUARIO_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.SEGUIR_USUARIO ) )
            {
                error = true;
            }
            else
            {
                escuchaCambios.actualizarSeguirUsuario( );
            }
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO ) )
            {
                error = true;
            }
            else
            {
                escuchaCambios.actualizarDejarSeguirUsuario( );
            }
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.ESCRIBIR_MICROBLOG_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.ESCRIBIR_MICROBLOG ) )
            {
                error = true;
            }
            else
            {
                escuchaCambios.actualizarEscribirMicroblog( );
            }
        }
        else if( msj.darComando( ).equals( ProtocoloComunicacion.CERRAR_SESION_OK ) )
        {
            if( !ultimoComandoEnviado.equals( ProtocoloComunicacion.CERRAR_SESION ) )
            {
                error = true;
            }
            else
            {
                escuchaCambios.actualizarCerrarSesion( );
            }
        }
        if( error )
        {
            throw new ErrorProtocoloException( ultimoComandoEnviado + "_OK", msj.darComando( ), msj.toString( ) );
        }
    }

    /**
     * Método que procesa el string que representa un microblog y lo agrega a la lista de microblogs que el usuario puede leer
     * @param msj El string del microblog
     */
    public void agregarMicroBlog( String msj )
    {
        String[] valores = msj.split( ProtocoloComunicacion.SEPARADOR_CAMPOS );
        MicroBlog microBlog = new MicroBlog( valores[ 0 ], valores[ 2 ], valores[ 1 ] );
        microblogs.add( microBlog );
    }

    /**
     * Retorna el nombre del usuario
     * @return El nombre del usuario
     */
    public String darNombreUsuario( )
    {
        return nombreUsuario;
    }

    /**
     * Retorna la lista de microblogs que el usuario puede leer
     * @return La lista de microblogs
     */
    public ArrayList darMicroblogs( )
    {
        return microblogs;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv</b><br>
     * escuchaCambios != null; manejadorComunicaciones != null; microblogs != null;
     */
    private void verificarInvariante( )
    {
        assert ( escuchaCambios != null ) : "La interfaz de escucha de cambios no puede ser null";
        assert ( manejadorComunicaciones != null ) : "El manejador de comunicaciones no puede ser null";
        assert ( microblogs != null ) : "La lista de microblogs no puede ser null";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * 
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * 
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
