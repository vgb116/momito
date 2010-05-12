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
     * El usuario que ha iniciado sesión
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
     * Indica si se tiene una conexión activa con el cliente y se deben seguir recibiendo mensajes
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se invoca cuando inicia la ejecución del thread y que se encarga de esperar mensajes desde el cliente <br>
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
     * Método que se invoca cuando llega un nuevo mensaje a través del socket de conexión y que los procesa según el comando del mensaje.<br>
     * Dependiendo del comando que contenga el mensaje se invoca cada uno de los métodos que procesan este tipo especifico de mensaje.<br>
     * Por ejemplo si se recibe un mensaje con comando ProtocoloComunicacion.CREAR_CUENTA se debería invocar un método crearCuenta,<br>
     * al cual se le pasarían los parámetros del mensaje utilizando el método darParametro.
     * @param mens El mensaje que se recibió
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
     * Método que cierra la sesión del usuario actual
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
     * Método que crea una nueva cuenta de usuario y envía un mensaje de respuesta satisfactoria
     * @param nUsuario El nombre de usuario
     * @param nombre El nombre del usuario
     * @param apellidos Los apellidos del usuario
     * @param pwd La contraseña del usuario
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void crearCuenta( String nUsuario, String nombre, String apellidos, String pwd ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Cree el usuario usando el manejador de persistencia
            manejadorPersistencia.registrarUsuario( nUsuario, nombre, apellidos, pwd );
            // TODO inicialice el atributo usuario con la información recibida por parámetro
            usuario = manejadorPersistencia.buscarUsuario( nUsuario );
            // TODO cambie el estado del usuario usuando el manejador de persistencia
            manejadorPersistencia.cambiarEstado( nUsuario, conectado );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( "Error creando la cuenta" );
        }

        // TODO Envié el mensaje CREAR_CUENTA_OK usando el manejador de comuniaciones
        manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.CREAR_CUENTA_OK ) );

    }

    /**
     * Método que verifica que un usuario halla iniciado sesión
     * @return Verdadero si el usuario ha iniciado sesión o falso en caso contrario
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
     * Método que registra el inicio de sesión de un usuario y envía un mensaje de respuesta satisfactoria
     * 
     * @param nUsuario El nombre de usuario
     * @param nPwd La contraseña del usuario
     * @throws CupiTwitterServidorException En caso de que el nombre de usuario y contraseña sean inválidos
     */
    private void login( String nUsuario, String nPwd ) throws CupiTwitterServidorException
    {

        // TODO Completar según la documentación
        // 1. Busque el usuario usando el manejador de persistencia
        // 2. Si el usuario existe envié el mensaje INICIAR_SESION_OK usando el manejador de comunicaciones, cambie el estado del usuario e inicialice el atributo usuario
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
                manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.ERROR + ProtocoloComunicacion.SEPARADOR_PARAMETROS + "El nombre de usuario o contraseña es invalido") );
            }
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
    }

    /**
     * Método que registra el seguimiento a un usuario por parte del usuario que ha iniciado sesión y envía un mensaje de respuesta satisfactoria
     * @param nUsuarioSeguir el nombre de usuario a seguir
     * @throws CupiTwitterServidorException En caso de encontrar un error o no existir el usuario a seguir
     */
    private void seguirUsuario( String nUsuarioSeguir ) throws CupiTwitterServidorException
    {

        // TODO Completar según la documentación
        // 1. registre el seguidor usando el manejador de persistencia
        // 2. Envié el mensaje SEGUIR_USUARIO_OK usando el manejador de comunicaciones si se registro el seguidor, de lo contrario lanza una CupiTwitterServidorException
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
     * Método que registra la eliminación del seguimiento a un usuario por parte del usuario que ha iniciado sesión y envía un mensaje de respuesta satisfactoria
     * 
     * @param nUsuarioDejarSeguir El nombre de usuario que se desea dejar de seguir
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void dejarSeguirUsuario( String nUsuarioDejarSeguir ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Completar según la documentación
            // 1. Elimine el seguidor usando el manejador de persistencia
            // 2. Envié el mensaje DEJAR_SEGUIR_USUARIO_OK usando el manejador de comunicaciones
            manejadorPersistencia.eliminarSeguidor( usuario.darUsuario( ), nUsuarioDejarSeguir );
            manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.DEJAR_SEGUIR_USUARIO_OK ) );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( "Error eliminando el seguimiento al usuario:" + nUsuarioDejarSeguir );
        }
    }

    /**
     * Método que registra un nuevo microblog para el usuario que ha iniciado sesión y envía un mensaje de respuesta satisfactoria
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

        // TODO Envié el mensaje ESCRIBIR_MICROBLOG_OK usando el manejador de comunicaciones
        manejadorComunicaciones.enviarMensaje( new Mensaje( ProtocoloComunicacion.ESCRIBIR_MICROBLOG_OK ) );
    }

    /**
     * Método que consulta la lista de microblogs disponibles para el usuario y envía un mensaje con la lista
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void consultarBlogs( ) throws CupiTwitterServidorException
    {
        try
        {
            // TODO Completar según la documentación
            // 1. Consulte los blogs disponibles para el usuario usando el manejador de persistencia
            // 2. Ordene los blogs usando el método ordenarMicroBlogs
            // 3. Cree un nuevo mensaje para ser enviado (Objeto de tipo Mensaje)
            // 4. Envié el mensaje usando el manejador de comunicaciones
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
     * Método que ordena el listado de microblogs basado en la fecha (de mayor a menor)<br>
     * post: La lista de microblogs se encuentra ordenada de mayor a menor
     * @param microblogs La lista de microblogs
     */
    public void ordenarMicroBlogs( ArrayList microblogs )
    {
        // TODO Completar según la documentación
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