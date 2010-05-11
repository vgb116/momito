package uniandes.cupi2.cupiTwitter.testServidor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.cupiTwitter.servidor.mundo.ManejadorPersistencia;
import uniandes.cupi2.cupiTwitter.servidor.mundo.Microblog;
import uniandes.cupi2.cupiTwitter.servidor.mundo.Usuario;

/**
 * Clase sobre la que se realizan las pruebas para verificar la correcta implementación de la clase ManejadorPersistencia
 */
public class ManejadorPersistenciaTest extends TestCase
{

    /**
     * Es la clase sobre la que se van a realizar las pruebas
     */
    private ManejadorPersistencia manejadorPersistencia;

    /**
     * Es el conjunto de propiedades para configurar las pruebas
     */
    private Properties configuracion;

    /**
     * Es la conexión usada para las pruebas
     */
    private Connection conexionPruebas;

    private void setupEscenarioBasico( )
    {
        manejadorPersistencia = null;
        File directorioData = new File( "./test/data" );
        System.setProperty( "derby.system.home", directorioData.getAbsolutePath( ) );
        configuracion = new Properties( );
        configuracion.setProperty( "admin.db.url", "jdbc:derby:testCupiTwitter;create=true" );
        configuracion.setProperty( "admin.test.url", "jdbc:derby:testCupiTwitter" );
        configuracion.setProperty( "admin.db.driver", "org.apache.derby.jdbc.EmbeddedDriver" );
        configuracion.setProperty( "admin.db.shutdown", "jdbc:derby:;shutdown=true" );
        configuracion.setProperty( "admin.db.path", "./test/data" );

        // Conectar a la base de datos
        try
        {
            String driver = configuracion.getProperty( "admin.db.driver" );
            Class.forName( driver ).newInstance( );
            String url = configuracion.getProperty( "admin.db.url" );
            conexionPruebas = DriverManager.getConnection( url );
        }
        catch( Exception e )
        {
            fail( "Falló la conexión a la base de datos" );
        }

        try
        {
            // Crear la tabla de resultados si es necesario
            crearTablas( );
        }
        catch( SQLException e1 )
        {
            fail( "No se pudo crear la tabla" );
        }

        try
        {
            // Limpia todos los datos existentes e inserta datos iniciales para
            // las pruebas
            inicializarTabla( );
        }
        catch( SQLException e2 )
        {
            fail( "No se pudo crear la tabla" );
        }

        // Construir el manejador
        try
        {
            manejadorPersistencia = new ManejadorPersistencia( configuracion );
            manejadorPersistencia.conectarABD( );
        }
        catch( Exception e3 )
        {
            fail( "No se pudo conectar el administrador a la BD" );
        }
    }

    /**
     * Inicializa la base de datos y construye un nuevo manejador de persistencia con usuarios de prueba
     */
    private void setupEscenario1( )
    {
        setupEscenarioBasico( );
        try
        {
            Statement s = conexionPruebas.createStatement( );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us1', 'Juan1','Perez1','1',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us2', 'Juan2','Perez2','2',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us3', 'Juan3','Perez3','3',0,0,'N')" );
        }
        catch( Exception e )
        {
            // TODO: handle exception
        }
    }

    /**
     * Inicializa la base de datos y construye un nuevo manejador de persistencia y registra un conjunto de usuarios y seguidores
     */
    private void setupEscenario2( )
    {
        setupEscenarioBasico( );
        try
        {
            Statement s = conexionPruebas.createStatement( );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us1', 'Juan1','Perez1','1',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us2', 'Juan2','Perez2','2',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us3', 'Juan3','Perez3','3',0,0,'N')" );

            // Insertar los datos iniciales de usuarios seguidores
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us2', 'us1')" );
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us3', 'us2')" );
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us3', 'us1')" );
        }
        catch( Exception e )
        {
            // TODO: handle exception
        }
    }

    /**
     * Inicializa la base de datos y construye un nuevo manejador de persistencia y registra un conjunto de usuarios con seguidores y microblogs
     */
    private void setupEscenario3( )
    {
        setupEscenarioBasico( );
        try
        {
            Statement s = conexionPruebas.createStatement( );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us1', 'Juan1','Perez1','1',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us2', 'Juan2','Perez2','2',0,0,'N')" );
            s.executeUpdate( "INSERT INTO usuarios (usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado) VALUES ('us3', 'Juan3','Perez3','3',0,0,'N')" );

            // Insertar los datos iniciales de usuarios seguidores
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us2', 'us1')" );
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us3', 'us2')" );
            s.executeUpdate( "INSERT INTO usuarios_seguidores (usuario, usuario_seguidor) VALUES ('us3', 'us1')" );

            // Insertar los datos iniciales de los microblogs
            s.executeUpdate( "INSERT INTO microblogs (usuario, microblog, fecha_publicacion) VALUES ('us1', 'Post 1 de us1','" + darFecha( ) + "')" );
            s.executeUpdate( "INSERT INTO microblogs (usuario, microblog, fecha_publicacion) VALUES ('us1', 'Post 2 de us2','" + darFecha( ) + "')" );
            s.executeUpdate( "INSERT INTO microblogs (usuario, microblog, fecha_publicacion) VALUES ('us3', 'Post 1 de us3','" + darFecha( ) + "')" );
            s.executeUpdate( "INSERT INTO microblogs (usuario, microblog, fecha_publicacion) VALUES ('us2', 'Post 1 de us2','" + darFecha( ) + "')" );
            s.executeUpdate( "INSERT INTO microblogs (usuario, microblog, fecha_publicacion) VALUES ('us2', 'Post 2 de us2','" + darFecha( ) + "')" );

        }
        catch( Exception e )
        {
            // TODO: handle exception
        }
    }

    /**
     * Crea las tablas necesarias para el administrador de resultados
     * 
     * @throws SQLException Se lanza esta excepción si hay problemas creando la tabla
     */
    private void crearTablas( ) throws SQLException
    {
        String sqlUsuarios = "create table usuarios(" + "usuario varchar(32)," + "nombre varchar(50)," + "apellidos varchar(50)," + "pwd varchar(12)," + "total_mensajes integer," + "total_seguidores integer, conectado varchar(1), "
                + "PRIMARY KEY(usuario))";
        String sqlUsuariosSeguidores = "create table usuarios_seguidores(" + "usuario varchar(32)," + "usuario_seguidor varchar(32)," + "PRIMARY KEY(usuario, usuario_seguidor)" + ")";
        String sqlMicroblogs = "create table microblogs(" + "usuario varchar(32)," + "microblog varchar(140)," + "fecha_publicacion varchar(20)" + ")";

        Statement s = conexionPruebas.createStatement( );

        boolean crearTabla = false;
        try
        {
            s.executeQuery( "SELECT * FROM usuarios WHERE 1=2" );
            s.executeQuery( "SELECT * FROM usuarios_seguidores WHERE 1=2" );
            s.executeQuery( "SELECT * FROM microblogs WHERE 1=2" );
        }
        catch( SQLException se )
        {
            // La excepción se lanza si la tabla no existe
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
    }

    /**
     * Limpia la tabla de resultados e inserta valores iniciales para las pruebas
     * 
     * @throws SQLException Se lanza esta excepción si hay problemas inicializando la tabla
     */
    private void inicializarTabla( ) throws SQLException
    {
        Statement s = conexionPruebas.createStatement( );

        // Limpiar la tabla resultados
        s.executeUpdate( "DELETE FROM usuarios" );
        s.executeUpdate( "DELETE FROM usuarios_seguidores" );
        s.executeUpdate( "DELETE FROM microblogs" );

        // Insertar los datos iniciales de usuarios

    }

    private String darFecha( )
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy kk:mm" );
        return dateFormat.format( new Date( ) );
    }

    /**
     * Método que prueba el registro de microblogs en la base de datos
     */
    public void testRegistrarMicroBlog( )
    {
        setupEscenario1( );
        try
        {
            manejadorPersistencia.registrarMicroBlog( "us1", "Post 1 de us1", darFecha( ) );
            manejadorPersistencia.registrarMicroBlog( "us1", "Post 2 de us1", darFecha( ) );
            manejadorPersistencia.registrarMicroBlog( "us3", "Post 1 de us3", darFecha( ) );
            manejadorPersistencia.registrarMicroBlog( "us2", "Post 1 de us2", darFecha( ) );
            manejadorPersistencia.registrarMicroBlog( "us2", "Post 2 de us2", darFecha( ) );

            Statement s = conexionPruebas.createStatement( );
            ResultSet rs = s.executeQuery( "select microblog from microblogs where usuario = 'us1'" );
            while( rs.next( ) )
            {
                String mblog = rs.getString( 1 );
                assertTrue( "Error en el registro de microblogs, los blogs del usuario 1 no corresponden", mblog.equals( "Post 1 de us1" ) || mblog.equals( "Post 2 de us1" ) );
            }
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba el registro de usuario en la base de datos
     */
    public void testRegistrarUsuario( )
    {
        setupEscenarioBasico( );
        try
        {
            manejadorPersistencia.registrarUsuario( "us1", "Juan1", "Perez1", "1" );

            Statement s = conexionPruebas.createStatement( );
            ResultSet rs = s.executeQuery( "select usuario, nombre, apellidos, pwd, total_mensajes, total_seguidores, conectado from usuarios" );
            while( rs.next( ) )
            {
                String usuario = rs.getString( 1 );
                String nombre = rs.getString( 2 );
                String apellidos = rs.getString( 3 );
                String pwd = rs.getString( 4 );
                int total_mensajes = rs.getInt( 5 );
                int total_seguidores = rs.getInt( 6 );
                String conectado = rs.getString( 7 );
                assertEquals( "Error en el registro de usuarios, el nombre de usuario no corresponde", usuario, "us1" );
                assertEquals( "Error en el registro de usuarios, el nombre del usuario no corresponde", nombre, "Juan1" );
                assertEquals( "Error en el registro de usuarios, los apellidos del usuario no corresponden", apellidos, "Perez1" );
                assertEquals( "Error en el registro de usuarios, la contraseña del usuario no corresponde", pwd, "1" );
                assertEquals( "Error en el registro de usuarios, el total de mensajes del usuario no corresponde", total_mensajes, 0 );
                assertEquals( "Error en el registro de usuarios, el total de seguidores del usuario no corresponde", total_seguidores, 0 );
                assertEquals( "Error en el registro de usuarios, el estado del usuario no corresponde", conectado, "N" );
            }
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la búsqueda de un usuario dado su nombre de usuario y contraseña
     */
    public void testBuscarUsuario1( )
    {
        setupEscenario1( );
        System.out.println("1");
        try
        {
            Usuario usuario = manejadorPersistencia.buscarUsuario( "us1", "1" );
            System.out.println("2");
            assertEquals( "Error en la busqueda de usuarios, el nombre de usuario no corresponde", usuario.darUsuario( ), "us1" );
            assertEquals( "Error en la busqueda de usuarios, el nombre del usuario no corresponde", usuario.darNombre( ), "Juan1" );
            assertEquals( "Error en la busqueda de usuarios, los apellidos del usuario no corresponden", usuario.darApellidos( ), "Perez1" );
            assertEquals( "Error en la busqueda de usuarios, la contraseña del usuario no corresponde", usuario.darPwd( ), "1" );
            assertEquals( "Error en la busqueda de usuarios, el total de mensajes del usuario no corresponde", usuario.darTotalMensajes( ), 0 );
            assertEquals( "Error en la busqueda de usuarios, el total de seguidores del usuario no corresponde", usuario.darTotalSeguidores( ), 0 );

            Usuario usuario1 = manejadorPersistencia.buscarUsuario( "us1", "123456" );

            assertNull( "Error en la busqueda de usuarios, el usuario no deberia existir", usuario1 );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la búsqueda de un usuario dado su nombre de usuario
     */
    public void testBuscarUsuario2( )
    {
        setupEscenario1( );
        try
        {
            Usuario usuario = manejadorPersistencia.buscarUsuario( "us3" );

            assertEquals( "Error en la busqueda de usuarios, el nombre de usuario no corresponde", usuario.darUsuario( ), "us3" );
            assertEquals( "Error en la busqueda de usuarios, el nombre del usuario no corresponde", usuario.darNombre( ), "Juan3" );
            assertEquals( "Error en la busqueda de usuarios, los apellidos del usuario no corresponden", usuario.darApellidos( ), "Perez3" );
            assertEquals( "Error en la busqueda de usuarios, la contraseña del usuario no corresponde", usuario.darPwd( ), "3" );
            assertEquals( "Error en la busqueda de usuarios, el total de mensajes del usuario no corresponde", usuario.darTotalMensajes( ), 0 );
            assertEquals( "Error en la busqueda de usuarios, el total de seguidores del usuario no corresponde", usuario.darTotalSeguidores( ), 0 );

            Usuario usuario1 = manejadorPersistencia.buscarUsuario( "us25" );

            assertNull( "Error en la busqueda de usuarios, el usuario no deberia existir", usuario1 );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba registro de un usuario como seguidor de otro
     */
    public void testRegistrarSeguidor( )
    {
        setupEscenario1( );
        try
        {
            manejadorPersistencia.registrarSeguidor( "us2", "us1" );
            manejadorPersistencia.registrarSeguidor( "us3", "us2" );
            manejadorPersistencia.registrarSeguidor( "us3", "us1" );

            Statement s = conexionPruebas.createStatement( );
            ResultSet rs = s.executeQuery( "select usuario_seguidor from usuarios_seguidores where usuario='us1'" );
            while( rs.next( ) )
            {
                String usuario_seguidor = rs.getString( 1 );
                assertTrue( "Error en el registro de seguidores, el usuario 1 y el usuario 3 deberían ser los únicos seguidores del usuario 1", usuario_seguidor.equals( "us3" ) || usuario_seguidor.equals( "us2" ) );
            }
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la eliminación de un usuario como seguidor de otro
     */
    public void testEliminarSeguidor( )
    {
        setupEscenario2( );
        try
        {
            manejadorPersistencia.eliminarSeguidor( "us2", "us3" );
            manejadorPersistencia.eliminarSeguidor( "us1", "us2" );

            Statement s = conexionPruebas.createStatement( );
            ResultSet rs = s.executeQuery( "select usuario_seguidor from usuarios_seguidores where usuario='us3'" );
            while( rs.next( ) )
            {
                String usuario_seguidor = rs.getString( 1 );
                assertTrue( "Error en la eliminación de seguidores, solo el usuario 1 debería ser seguidor del usuario 3", usuario_seguidor.equals( "us1" ) );
            }

            rs = s.executeQuery( "select usuario_seguidor from usuarios_seguidores where usuario='us2'" );
            while( rs.next( ) )
            {
                fail( "Error en la eliminación de seguidores, el usuario 2 no debería tener seguidores" );
            }
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la consulta de blogs que un usuario puede ver según los usuarios a los que este siguiendo
     */
    public void testConsultarBlogParaUsuario( )
    {
        setupEscenario3( );
        try
        {
            ArrayList microblogs = manejadorPersistencia.consultarBlogParaUsuario( "us1" );
            ArrayList textoMicroblogs = new ArrayList( );
            for( int i = 0; i < microblogs.size( ); i++ )
            {
                Microblog microblog = ( Microblog )microblogs.get( i );
                textoMicroblogs.add( microblog.darUsuario( ) + ":" + microblog.darTexto( ) );
            }

            ArrayList textoMicroblogsCorrectos = new ArrayList( );
            textoMicroblogsCorrectos.add( "us2:Post 1 de us2" );
            textoMicroblogsCorrectos.add( "us2:Post 2 de us2" );
            textoMicroblogsCorrectos.add( "us3:Post 1 de us3" );

            assertEquals( "Error en la consulta de los microblogs para un usuario", textoMicroblogs, textoMicroblogsCorrectos );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la consulta de todos los usuarios registrados en el sistema
     */
    public void testConsultarUsuariosRegistrados( )
    {
        setupEscenario1( );
        try
        {
            ArrayList usuarios = manejadorPersistencia.consultarUsuariosRegistrados( );
            ArrayList nombresUsuarios = new ArrayList( );
            for( int i = 0; i < usuarios.size( ); i++ )
            {
                Usuario usuario = ( Usuario )usuarios.get( i );
                nombresUsuarios.add( usuario.darUsuario( ) );
            }

            ArrayList nombresUsuariosCorrectos = new ArrayList( );
            nombresUsuariosCorrectos.add( "us1" );
            nombresUsuariosCorrectos.add( "us2" );
            nombresUsuariosCorrectos.add( "us3" );

            assertEquals( "Error en la consulta de los usuarios registrados", nombresUsuarios, nombresUsuariosCorrectos );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la consulta de la lista de usuarios que siguen a otro
     */
    public void testConsultarUsuariosSeguidores( )
    {
        setupEscenario3( );
        try
        {
            ArrayList usuariosSeguidores = manejadorPersistencia.consultarUsuariosSeguidores( "us3" );
            ArrayList nombresUsuarios = new ArrayList( );
            for( int i = 0; i < usuariosSeguidores.size( ); i++ )
            {
                Usuario usuario = ( Usuario )usuariosSeguidores.get( i );
                nombresUsuarios.add( usuario.darUsuario( ) );
            }

            ArrayList usuariosSeguidoresCorrectos = new ArrayList( );
            usuariosSeguidoresCorrectos.add( "us1" );
            usuariosSeguidoresCorrectos.add( "us2" );

            assertEquals( "Error en la consulta de los usuarios seguidores", nombresUsuarios, usuariosSeguidoresCorrectos );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba el cambio de estado de un usuario, puede ser conectado o no
     */
    public void testCambiarEstado( )
    {
        setupEscenario1( );
        try
        {
            manejadorPersistencia.cambiarEstado( "us1", true );
            Statement s = conexionPruebas.createStatement( );
            ResultSet rs = s.executeQuery( "select conectado from usuarios where usuario='us1'" );
            while( rs.next( ) )
            {
                String estado = rs.getString( 1 );
                assertEquals( "Error en el cambio de estado de los usuario, el usuario 1 debería aparecer como conectado", estado, "S" );
            }

            manejadorPersistencia.cambiarEstado( "us1", false );
            rs = s.executeQuery( "select conectado from usuarios where usuario='us1'" );
            while( rs.next( ) )
            {
                String estado = rs.getString( 1 );
                assertEquals( "Error en el cambio de estado de los usuario, el usuario 1 debería aparecer como no conectado", estado, "N" );
            }

        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

    /**
     * Método que prueba la consulta de la lista de usuarios conectados
     */
    public void testConsultarUsuariosConectados( )
    {
        setupEscenario1( );
        try
        {
            manejadorPersistencia.cambiarEstado( "us1", true );
            manejadorPersistencia.cambiarEstado( "us3", true );
            ArrayList usuariosConectados = manejadorPersistencia.consultarUsuariosConectados( );
            ArrayList nombresUsuarios = new ArrayList( );
            for( int i = 0; i < usuariosConectados.size( ); i++ )
            {
                Usuario usuario = ( Usuario )usuariosConectados.get( i );
                nombresUsuarios.add( usuario.darUsuario( ) );
            }

            ArrayList usuariosConectadosCorrectos = new ArrayList( );
            usuariosConectadosCorrectos.add( "us1" );
            usuariosConectadosCorrectos.add( "us3" );

            assertEquals( "Error en la consulta de los usuarios conectados, solo el usuario 1 y el usuario 2 deberían aparecer conectados", nombresUsuarios, usuariosConectadosCorrectos );
        }
        catch( Exception e )
        {
            fail( "Error ejecutando la inserción en la base de datos: " + e.getMessage( ) );
        }
    }

}
