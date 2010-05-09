/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ManejadorPersistencia.java,v 1.16 2010/04/27 22:23:16 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiTwitter.servidor.mundo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.sun.corba.se.spi.orbutil.fsm.State;

/**
 * Clase que se encarga del manejo de la persistencia
 */
public class ManejadorPersistencia
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Conexión a la base de datos
     */
    private Connection conexion;

    /**
     * Conjunto de propiedades que contienen la configuración de la aplicación
     */
    private Properties config;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de persistencia
     * @param nProperties La configuración para la conexión a la base de datos
     * @throws Exception En caso de encontrar un error
     */
    public ManejadorPersistencia( Properties nProperties ) throws Exception
    {
        config = nProperties;

        // Establecer la ruta donde va a estar la base de datos.
        // Derby utiliza la propiedad del sistema derby.system.home para saber
        // donde están los datos
        File data = new File( config.getProperty( "admin.db.path" ) );
        System.setProperty( "derby.system.home", data.getAbsolutePath( ) );
        conectarABD( );
        iniciliazarBd( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que inicializa la la base de datos
     * @throws SQLException En caso de encontrar un error iniciando la base de datos
     */
    public void iniciliazarBd( ) throws SQLException
    {
        String sqlUsuarios = "create table usuarios(" + "usuario varchar(32)," + "nombre varchar(50)," + "apellidos varchar(50)," + "pwd varchar(12)," + "total_mensajes integer," + "total_seguidores integer, conectado varchar(1), "
                + "PRIMARY KEY(usuario))";
        String sqlUsuariosSeguidores = "create table usuarios_seguidores(" + "usuario varchar(32)," + "usuario_seguidor varchar(32)," + "PRIMARY KEY(usuario, usuario_seguidor)" + ")";
        String sqlMicroblogs = "create table microblogs(" + "usuario varchar(32)," + "microblog varchar(140)," + "fecha_publicacion varchar(20)" + ")";

        Statement s = conexion.createStatement( );

        boolean crearTabla = false;
        try
        {
            s.executeQuery( "SELECT * FROM usuarios WHERE 1=2" );
            s.executeUpdate( "UPDATE usuarios SET conectado = 'N'" );
            s.executeQuery( "SELECT * FROM usuarios_seguidores WHERE 1=2" );
            s.executeQuery( "SELECT * FROM microblogs WHERE 1=2" );
        }
        catch( SQLException se )
        {
            // La excepción se lanza si la tabla no existe
            se.printStackTrace( );
            crearTabla = true;
        }

        // Se crea una nueva tabla vacía
        if( crearTabla )
        {
            s.execute( sqlUsuarios );
            s.execute( sqlUsuariosSeguidores );
            s.execute( sqlMicroblogs );
        }

        s.close( );
        verificarInvariante( );
    }

    /**
     * Conecta el administrador a la base de datos
     * 
     * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
     * @throws Exception Se lanza esta excepción si hay problemas con los controladores
     */
    public void conectarABD( ) throws SQLException, Exception
    {
        String driver = config.getProperty( "admin.db.driver" );
        Class.forName( driver ).newInstance( );

        String url = config.getProperty( "admin.db.url" );
        conexion = DriverManager.getConnection( url );
        verificarInvariante( );
    }

    /**
     * Desconecta el administrador de la base de datos y la detiene
     * 
     * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
     */
    public void desconectarBD( ) throws SQLException
    {
        conexion.close( );
        String down = config.getProperty( "admin.db.shutdown" );
        try
        {
            DriverManager.getConnection( down );
        }
        catch( SQLException e )
        {
            // Al bajar la base de datos se produce siempre una excepción
        }
        verificarInvariante( );
    }

    /**
     * Registra un microblog en la base de datos
     * 
     * @param microblog El texto del microblog
     * @param usuario El usuario que escribió el microblog
     * @param fecha La fecha en la se escribió
     * @throws SQLException En caso de encontrar un error
     */
    public void registrarMicroBlog( String usuario, String microblog, String fecha ) throws SQLException
    {
        // TODO Ingresar un microblog en la tabla de microblogs
        Statement st = conexion.createStatement( );
        String insert = "INSERT INTO microblogs (usuario,microblog,fecha_publicacion)" + "VALUES (' " + usuario + " ',' " + microblog + "','" + fecha + "')";
        st.execute( insert );
        st.close( );
        verificarInvariante( );
    }

    /**
     * Registra un usuario en la base de datos
     * 
     * @param usuario El nombre de usuario
     * @param nombre El nombre del usuario
     * @param apellidos Los apellidos del usuario
     * @param pwd La contraseña del usuario
     * @throws SQLException En caso de encontrar un error
     */
    public void registrarUsuario( String usuario, String nombre, String apellidos, String pwd ) throws SQLException
    {
        // TODO Registrar un usuario en la tabla de usuarios
        Statement st = conexion.createStatement( );
        String insert = "INSERT INTO usuarios (usuario, nombre, apellidos, pwd,total_mensajes,total_seguidores,conectado) " + "VALUES ('" + usuario + "','" + nombre + "','" + apellidos + "','" + pwd + "', 0 , 0 , '" + "N" + "')";
        st.execute( insert );
        st.close( );
        verificarInvariante( );
    }

    /**
     * Método que busca un usuario en la base de datos teniendo en cuenta su nombre de usuario y contraseña
     * @param nUsuario El nombre del usuario
     * @param nPwd La contraseña del usuario
     * @return El usuario con toda su información si la combinación usuario contraseña es correcta, null de lo contrario
     * @throws SQLException En caso de encontrar un error
     */
    public Usuario buscarUsuario( String nUsuario, String nPwd ) throws SQLException
    {

        // TODO Buscar un usuario con el login y password dado en la tabla de usuario y construir un objeto de tipo usuario con la información obtenida
        Usuario usuario = null;
        String sql = "SELECT usuario, nombre, apellidos,pwd,total_mensajes,total_seguidores,conectado FROM usuarios WHERE usuario ='" + nUsuario + "' AND pwd = '" + nPwd + "')";
        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );

        if( resultado.next( ) ) // Se encontró el jugador
        {
            usuario = construirUsuario( resultado );

            resultado.close( );
        }
        return usuario;
    }

    /**
     * Método que busca un usuario en la base de datos teniendo en cuenta su nombre de usuario
     * @param nUsuario El nombre del usuario
     * @return El usuario con toda su información si existe, null en caso contrario
     * @throws SQLException En caso de encontrar un error
     */
    public Usuario buscarUsuario( String nUsuario ) throws SQLException
    {

        // TODO Buscar un usuario con el login dado en la tabla de usuario y construir un objeto de tipo usuario con la información obtenida
        Usuario usuario = null;
        String sql = "SELECT usuario,nombre,apellidos,pwd,total_mensajes,total_seguidores,conectado FROM usuarios WHERE usuario = '" + nUsuario + "')";
        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );

        if( resultado.next( ) )
        {
            usuario = construirUsuario( resultado );
            resultado.close( );
        }
        return usuario;
    }

    /**
     * Método que construye un usuario a partir de un resultset
     * 
     * @param rs El resultset
     * @return El usuario construido
     * @throws SQLException En caso de encontrar un error
     */
    public Usuario construirUsuario( ResultSet rs ) throws SQLException
    {
        String usuario = rs.getString( 1 );
        String nombre = rs.getString( 2 );
        String apellidos = rs.getString( 3 );
        String pwd = rs.getString( 4 );
        int totalMensajes = rs.getInt( 5 );
        int totalSeguidores = rs.getInt( 6 );
        return new Usuario( usuario, nombre, apellidos, pwd, totalMensajes, totalSeguidores );
    }

    /**
     * Método que construye un microblog a partir de un resultset
     * 
     * @param rs El resultset
     * @return El microblog construido
     * @throws SQLException En caso de encontrar un error
     */
    public Microblog construirMicroblog( ResultSet rs ) throws SQLException
    {
        String usuario = rs.getString( 1 );
        String microblog = rs.getString( 2 );
        String fecha_publicacion = rs.getString( 3 );
        return new Microblog( usuario, microblog, fecha_publicacion );
    }

    /**
     * Método para registrar un seguidor en la base de datos
     * @param usuarioSeguidor El usuario que quiere seguir
     * @param usuarioSeguido El usuario que se quiere seguir
     * @return True si se registro a usuario como seguidor, False en caso que el usuario a seguir no exista
     * @throws SQLException En caso de encontrar un error
     */
    public boolean registrarSeguidor( String usuarioSeguidor, String usuarioSeguido ) throws SQLException
    {
        // TODO Agrega un usuario seguidor a la tabla de usuario_seguidores, y actualiza el total de seguidores en la tabla de usuarios.
        // AYUDA: si el usuarioSeguido no existe retorna false
        // Si se pudo registrar el usuario seguidor retorna true
        Usuario u = buscarUsuario( usuarioSeguido );
        if( u != null )
        {
            Statement st = conexion.createStatement( );
            String insert = "INSERT INTO usuarios_seguidores (usuario,usuario_seguidor) " + "VALUES ('" + u + "','" + usuarioSeguidor + "')";
            st.execute( insert );
            st.close( );
            verificarInvariante( );
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Método para eliminar un seguidor de la base de datos
     * 
     * @param usuarioSeguidor El usuario que sigue actualmente a otro usuario
     * @param usuarioSeguido El usuario que se quiere dejar de seguir seguir
     * @throws SQLException En caso de encontrar un error
     */
    public void eliminarSeguidor( String usuarioSeguidor, String usuarioSeguido ) throws SQLException
    {

        // TODO elimina un usuario seguidor a la tabla de usuario_seguidores, y actualiza el total de seguidores en la tabla de usuarios.
        Statement st = conexion.createStatement( );
        String del = "DELETE FROM usuarios_seguidores WHERE usuario = '" + usuarioSeguido + "' AND usuario_seguidor = '" + usuarioSeguidor;
        st.executeUpdate( del );
        st.close( );
        verificarInvariante( );
    }

    /**
     * Método que ejecuta una actualización en la base de datos
     * 
     * @param sql El código sql de actualización
     * @param params La lista de parámetros para ejecutar la actualización
     * @throws SQLException En caso de encontrar un error
     */
    private void ejecutarActualizacion( String sql, String[] params ) throws SQLException
    {
        PreparedStatement ps = conexion.prepareStatement( sql );
        for( int i = 0; i < params.length; i++ )
        {
            String p = params[ i ];
            ps.setString( i + 1, p );
        }
        ps.executeUpdate( );
    }

    /**
     * Método que ejecuta una consulta en la base de datos y retorna el resultado
     * 
     * @param sql El código sql de la consulta
     * @param params La lista de parámetros para ejecutar la consulta
     * @throws SQLException En caso de encontrar un error
     */
    private ResultSet ejecutarConsulta( String consulta, String[] params ) throws SQLException
    {
        PreparedStatement ps = conexion.prepareStatement( consulta );
        for( int i = 0; i < params.length; i++ )
        {
            String p = params[ i ];
            ps.setString( i + 1, p );
        }
        return ps.executeQuery( );
    }

    /**
     * método que consulta los microblogs disponibles para un usuario dependiendo de los usuarios que sigue
     * 
     * @param nUsuario El usuario al que se le quiere consultar los microblogs
     * @return La lista de microblogs
     * @throws SQLException En caso de encontrar un error
     */
    public ArrayList consultarBlogParaUsuario( String nUsuario ) throws SQLException
    {
        String sql = "select * from usuarios_seguidores where usuario_seguidor = ? ";
        String sqlBlogs = "select  * from microblogs where usuario = ?";
        String[] params = { nUsuario };
        ResultSet rsUsuariosSeguidores = ejecutarConsulta( sql, params );
        ArrayList microBlogs = new ArrayList( );
        while( rsUsuariosSeguidores.next( ) )
        {
            String[] paramBlogs = { rsUsuariosSeguidores.getString( 1 ) };
            ResultSet rsMicroBlogs = ejecutarConsulta( sqlBlogs, paramBlogs );
            while( rsMicroBlogs.next( ) )
            {
                microBlogs.add( construirMicroblog( rsMicroBlogs ) );
            }
        }
        return microBlogs;
    }

    /**
     * Método que consulta la lista de usuarios registrados
     * 
     * @return La lista de usuarios
     * @throws SQLException En caso de encontrar un error
     */
    public ArrayList consultarUsuariosRegistrados( ) throws SQLException
    {
        // TODO Devuelve una lista con los usuarios registrados
        Statement st = conexion.createStatement( );
        String consulta = "SELECT usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado FROM usuarios";
        ResultSet resultado = st.executeQuery( consulta );
        st.close( );
        ArrayList<Usuario> respuesta = new ArrayList<Usuario>( );
        while( resultado.next( ) )
        {
            Usuario u = construirUsuario( resultado );
            respuesta.add( u );
        }
        return respuesta;
    }

    /**
     * Método que consulta la lista de usuarios que siguen al usuario que se recibe como parámetro
     * 
     * @param nUsuario El usuario sobre el que se quiere consultar
     * @return La lista de usuarios que lo siguen
     * @throws SQLException En caso de encontrar un error
     */
    public ArrayList consultarUsuariosSeguidores( String nUsuario ) throws SQLException
    {
        // TODO Devuelve una lista con los usuarios que siguen al usuario dado por parámetro
        Statement st = conexion.createStatement( );
        String consulta = "SELECT usuario_seguidor FROM usuarios_seguidores WHERE usuario = '" + nUsuario + "'";
        ResultSet resultado = st.executeQuery( consulta );
        st.close( );
        ArrayList<Usuario> respuesta = new ArrayList<Usuario>( );
        while(resultado.next( ))
        {
            String nombreSeguidor = resultado.getString( 1 );
            Usuario u = buscarUsuario( nombreSeguidor );
            respuesta.add( u );
        }
        return respuesta;
    }

    /**
     * Método que cambia el estado de usuario a conectado o desconectado
     * 
     * @param nUsuario El nombre del usuario
     * @param estado El estado del usuario verdadero en caso de estar conectado o falso en caso contrario
     * @throws SQLException En caso de encontrar un error
     */
    public void cambiarEstado( String nUsuario, boolean estado ) throws SQLException
    {
        // TODO Cambia el estado del usuario con el login dado
        Statement st = conexion.createStatement( );
        String nuevoEstado = "N";
        if(estado == true )
        {
            nuevoEstado = "S";
        }
        String cambio = "UPDATE usuarios SET conectado = '" + nuevoEstado +"' WHERE usuario = '" + nUsuario +"'";
        st.executeUpdate( cambio );
        st.close( );
        
    }

    /**
     * Método que consulta la lista de usuarios conectados
     * 
     * @return La lista de usuarios conectados
     * @throws SQLException En caso de encontrar un error
     */
    public ArrayList consultarUsuariosConectados( ) throws SQLException
    {
        String sql = "select * from usuarios where conectado ='S'";
        String[] params = {};
        ResultSet rs = ejecutarConsulta( sql, params );
        ArrayList usuariosConectados = new ArrayList( );
        while( rs.next( ) )
        {
            usuariosConectados.add( construirUsuario( rs ) );
        }
        return usuariosConectados;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * config!=null <br>
     */
    private void verificarInvariante( )
    {
        assert config != null : "Conjunto de propiedades inválidas";
    }

}