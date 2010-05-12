package uniandes.cupi2.cupiTwitter.servidor.mundo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uniandes.cupi2.cupiTwitter.comun.mundo.ProtocoloComunicacion;

/**
 * Clase que representa un cliente remoto conectado al servidor
 */
public class ClienteRemotoTwitter extends Thread
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El usuario que ha iniciado sesi�n
     */
    private Usuario usuario;

    /**
     * El manejador de persistencia
     */
    private ManejadorPersistencia manejadorPersistencia;

    /**
     * El manejador de las comunicaciones
     */
    private ManejadorComunicacionesServidor manejadorComunicaciones;

    /**
     * Indica si se tiene una conexi�n activa con el cliente y se deben seguir recibiendo mensajes
     */
    private boolean conectado;

    // -----------------------------------------------------------------
    // Cosntructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo cliente remoto <br>
     * <b> post: </b> Inicializa conectado en true <br>
     * @param nManejadorComunicaciones El manejador de comunicaciones
     * @param nManejadorPersistencia El manejador de persistencia
     */
    public ClienteRemotoTwitter( ManejadorComunicacionesServidor nManejadorComunicaciones, ManejadorPersistencia nManejadorPersistencia )
    {
        manejadorPersistencia = nManejadorPersistencia;
        manejadorComunicaciones = nManejadorComunicaciones;
        conectado = true;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que se invoca cuando inicia la ejecuci�n del thread y que se encarga de esperar mensajes desde el cliente <br>
     * Si ocurre un error al recibir un mensaje, asigna false a conectado y hace logout
     */
    public void run( )
    {
        while( conectado )
        {
            try
            {
                nuevoMensaje( manejadorComunicaciones.recibirMensaje( ) );
            }
            catch( UsuariosDesconectadoException e )
            {
                conectado = false;
                logout( );
            }
            catch( ErrorProtocoloException e )
            {
                e.printStackTrace( );
            }
        }
    }

    /**
     * M�todo que se invoca cuando llega un nuevo mensaje a trav�s del socket de conexi�n y que los procesa seg�n el comando del mensaje.<br>
     * Dependiendo del comando que contenga el mensaje se invoca cada uno de los m�todos que procesan este tipo especifico de mensaje.<br>
     * Por ejemplo si se recibe un mensaje con comando ProtocoloComunicacion.CREAR_CUENTA se deber�a invocar un m�todo crearCuenta,<br>
     * al cual se le pasar�an los par�metros del mensaje utilizando el m�todo darParametro.
     * @param mens El mensaje que se recibi�
     * @throws ErrorProtocoloException Si ocurre un error en el protocolo
     */
    public void nuevoMensaje( Mensaje mens ) throws ErrorProtocoloException
    {
        try
        {
            if( mens.darComando( ).equals( ProtocoloComunicacion.CREAR_CUENTA ) )
            {
                if( mens.darTotalParametros( ) != ProtocoloComunicacion.TOTAL_PARAMETROS_CREAR_CUENTA )
                {
                    throw new ErrorProtocoloException( ProtocoloComunicacion.CREAR_CUENTA, ProtocoloComunicacion.TOTAL_PARAMETROS_CREAR_CUENTA, mens.darTotalParametros( ), mens.toString( ) );
                }
                crearCuenta( mens.darParametro( 0 ), mens.darParametro( 1 ), mens.darParametro( 2 ), mens.darParametro( 3 ) );
            }
            else if( mens.darComando( ).equals( ProtocoloComunicacion.INICIAR_SESION ) )
            {
                if( mens.darTotalParametros( ) != ProtocoloComunicacion.TOTAL_PARAMETROS_INICIAR_SESION )
                {
                    throw new ErrorProtocoloException( ProtocoloComunicacion.INICIAR_SESION, ProtocoloComunicacion.TOTAL_PARAMETROS_INICIAR_SESION, mens.darTotalParametros( ), mens.toString( ) );
                }
                login( mens.darParametro( 0 ), mens.darParametro( 1 ) );
            }
            else
            {
                if( verificarLogin( ) )
                {
                    if( mens.darComando( ).equals( ProtocoloComunicacion.CONSULTAR_BLOGS ) )
                    {
                        consultarBlogs( );
                    }
                    else if( mens.darComando( ).equals( ProtocoloComunicacion.ESCRIBIR_MICROBLOG ) )
                    {
                        if( mens.darTotalParametros( ) != ProtocoloComunicacion.TOTAL_PARAMETROS_ESCRIBIR_MICROBLOG )
                        {
                            throw new ErrorProtocoloException( ProtocoloComunicacion.ESCRIBIR_MICROBLOG, ProtocoloComunicacion.TOTAL_PARAMETROS_ESCRIBIR_MICROBLOG, mens.darTotalParametros( ), mens.toString( ) );
                        }
                        registrarMicroBlog( mens.darParametro( 0 ) );
                    }
                    else if( mens.darComando( ).equals( ProtocoloComunicacion.SEGUIR_USUARIO ) )
                    {
                        if( mens.darTotalParametros( ) != ProtocoloComunicacion.TOTAL_PARAMETROS_SEGUIR_USUARIO )
                        {
                            throw new ErrorProtocoloException( ProtocoloComunicacion.SEGUIR_USUARIO, ProtocoloComunicacion.TOTAL_PARAMETROS_SEGUIR_USUARIO, mens.darTotalParametros( ), mens.toString( ) );
                        }
                        seguirUsuario( mens.darParametro( 0 ) );
                    }
                    else if( mens.darComando( ).equals( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO ) )
                    {
                        if( mens.darTotalParametros( ) != ProtocoloComunicacion.TOTAL_PARAMETROS_DEJAR_SEGUIR_USUARIO )
                        {
                            throw new ErrorProtocoloException( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO, ProtocoloComunicacion.TOTAL_PARAMETROS_DEJAR_SEGUIR_USUARIO, mens.darTotalParametros( ), mens.toString( ) );
                        }
                        dejarSeguirUsuario( mens.darParametro( 0 ) );
                    }
                    else if( mens.darComando( ).equals( ProtocoloComunicacion.CERRAR_SESION ) )
                    {
                        logout( );
                    }
                }
            }
        }
        catch( CupiTwitterServidorException e )
        {
            String[] params = { e.getMessage( ) };
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.ERROR, params ) );
        }
    }

    /**
     * M�todo que cierra la sesi�n del usuario actual
     */
    private void logout( )
    {
        if( usuario != null )
        {
            try
            {
                manejadorPersistencia.cambiarEstado( usuario.darUsuario( ), false );
            }
            catch( SQLException e )
            {
                e.printStackTrace( );
            }
            usuario = null;
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.CERRAR_SESION_OK ) );
            conectado = false;
        }
    }

    /**
     * M�todo que crea una nueva cuenta de usuario y env�a un mensaje de respuesta satisfactoria
     * @param nUsuario El nombre de usuario
     * @param nombre El nombre del usuario
     * @param apellidos Los apellidos del usuario
     * @param pwd La contrase�a del usuario
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void crearCuenta( String nUsuario, String nombre, String apellidos, String pwd ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Cree el usuario usando el manejador de persistencia
            manejadorPersistencia.registrarUsuario( nUsuario, nombre, apellidos, pwd );
            // TODO inicialice el atributo usuario con la informaci�n recibida por par�metro
            usuario = manejadorPersistencia.buscarUsuario( nUsuario );
            // TODO cambie el estado del usuario usuando el manejador de persistencia
            manejadorPersistencia.cambiarEstado( nUsuario, conectado );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( "Error creando la cuenta" );
        }

        // TODO Envi� el mensaje CREAR_CUENTA_OK usando el manejador de comuniaciones
        manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.CREAR_CUENTA_OK ) );

    }

    /**
     * M�todo que verifica que un usuario halla iniciado sesi�n
     * @return Verdadero si el usuario ha iniciado sesi�n o falso en caso contrario
     */
    private boolean verificarLogin( )
    {
        if( usuario == null )
        {
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.NO_LOGIN ) );
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * M�todo que registra el inicio de sesi�n de un usuario y env�a un mensaje de respuesta satisfactoria
     * 
     * @param nUsuario El nombre de usuario
     * @param nPwd La contrase�a del usuario
     * @throws CupiTwitterServidorException En caso de que el nombre de usuario y contrase�a sean inv�lidos
     */
    private void login( String nUsuario, String nPwd ) throws CupiTwitterServidorException
    {

        // TODO Completar seg�n la documentaci�n
        // 1. Busque el usuario usando el manejador de persistencia
        // 2. Si el usuario existe envi� el mensaje INICIAR_SESION_OK usando el manejador de comunicaciones, cambie el estado del usuario e inicialice el atributo usuario
        // Si el usuario no existe lance una CupiTwitterServidorException
        try
        {
            Usuario u = manejadorPersistencia.buscarUsuario( nUsuario, nPwd );
            if( u != null )
            {
                manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.INICIAR_SESION_OK ) );
                manejadorPersistencia.cambiarEstado( nUsuario, conectado );
            }
            else
            {
                manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.ERROR + ProtocoloComunicacion.SEPARADOR_PARAMETROS + "El nombre de usuario o contrase�a es invalido") );
            }
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
    }

    /**
     * M�todo que registra el seguimiento a un usuario por parte del usuario que ha iniciado sesi�n y env�a un mensaje de respuesta satisfactoria
     * @param nUsuarioSeguir el nombre de usuario a seguir
     * @throws CupiTwitterServidorException En caso de encontrar un error o no existir el usuario a seguir
     */
    private void seguirUsuario( String nUsuarioSeguir ) throws CupiTwitterServidorException
    {

        // TODO Completar seg�n la documentaci�n
        // 1. registre el seguidor usando el manejador de persistencia
        // 2. Envi� el mensaje SEGUIR_USUARIO_OK usando el manejador de comunicaciones si se registro el seguidor, de lo contrario lanza una CupiTwitterServidorException
        try
        {
            manejadorPersistencia.registrarSeguidor( usuario.darUsuario( ), nUsuarioSeguir );
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.SEGUIR_USUARIO_OK ) );
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }

    }

    /**
     * M�todo que registra la eliminaci�n del seguimiento a un usuario por parte del usuario que ha iniciado sesi�n y env�a un mensaje de respuesta satisfactoria
     * 
     * @param nUsuarioDejarSeguir El nombre de usuario que se desea dejar de seguir
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void dejarSeguirUsuario( String nUsuarioDejarSeguir ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Completar seg�n la documentaci�n
            // 1. Elimine el seguidor usando el manejador de persistencia
            // 2. Envi� el mensaje DEJAR_SEGUIR_USUARIO_OK usando el manejador de comunicaciones
            manejadorPersistencia.eliminarSeguidor( usuario.darUsuario( ), nUsuarioDejarSeguir );
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO_OK ) );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( "Error eliminando el seguimiento al usuario:" + nUsuarioDejarSeguir );
        }
    }

    /**
     * M�todo que registra un nuevo microblog para el usuario que ha iniciado sesi�n y env�a un mensaje de respuesta satisfactoria
     * @param blog El texto del microblog
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void registrarMicroBlog( String blog ) throws CupiTwitterServidorException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy kk:mm" );
        String fecha = dateFormat.format( new Date( ) );
        try
        {

            // TODO Registre el microblog usando el manejador de persistencia
            manejadorPersistencia.registrarMicroBlog( usuario.darUsuario( ), blog, fecha );

        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( "El registrando el microblog." );
        }

        // TODO Envi� el mensaje ESCRIBIR_MICROBLOG_OK usando el manejador de comunicaciones
        manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.ESCRIBIR_MICROBLOG_OK ) );
    }

    /**
     * M�todo que consulta la lista de microblogs disponibles para el usuario y env�a un mensaje con la lista
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void consultarBlogs( ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Completar seg�n la documentaci�n
            // 1. Consulte los blogs disponibles para el usuario usando el manejador de persistencia
            // 2. Ordene los blogs usando el m�todo ordenarMicroBlogs
            // 3. Cree un nuevo mensaje para ser enviado (Objeto de tipo Mensaje)
            // 4. Envi� el mensaje usando el manejador de comunicaciones
            ArrayList blogs = manejadorPersistencia.consultarBlogParaUsuario( usuario.darUsuario( ) );
            ordenarMicroBlogs( blogs );
            ArrayList param = new ArrayList( );
            for( int i = 0; i < blogs.size( ); i++ )
            {
                String blog = blogs.get( i ).toString( );
                param.add( blog );
            }
            Mensaje men = new Mensaje( ProtocoloComunicacion.CONSULTAR_BLOGS_OK, param );
            manejadorComunicaciones.enviarMensaje( men );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( "Error consultando la lista de blogs disponibles." );
        }
    }
    /**
     * M�todo que ordena el listado de microblogs basado en la fecha (de mayor a menor)<br>
     * post: La lista de microblogs se encuentra ordenada de mayor a menor
     * @param microblogs La lista de microblogs
     */
    public void ordenarMicroBlogs( ArrayList microblogs )
    {
        // TODO Completar seg�n la documentaci�n
        for( int i = microblogs.size( ) - 1; i > 0; i-- )
        {
            for( int j = 0; j < i; j++ )
            {
                Microblog microJ = ( Microblog )microblogs.get( j );
                Microblog microJJ = ( Microblog )microblogs.get( j + 1 );
                String fechaJ = microJ.darFecha( );
                String fechaJJ = microJJ.darFecha( );
                int x = fechaJ.compareToIgnoreCase( fechaJJ );
                if( x < 0 )
                {
                    microblogs.set( j, microJJ );
                    microblogs.set( j + 1, microJ );
                }
            }
        }

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv</b><br>
     * manejadorPersistencia != null; manejadorComunicaciones != null;
     */
    private void verificarInvariante( )
    {
        assert ( manejadorPersistencia != null ) : "El manejador de persistencia de un jugador no puede ser null";
        assert ( manejadorComunicaciones != null ) : "El manejador de comunicaciones de un jugador no puede ser null";
    }
}