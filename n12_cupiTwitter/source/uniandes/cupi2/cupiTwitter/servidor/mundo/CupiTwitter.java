/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 * $Id: CupiTwitter.java,v 1.16 2010/04/27 22:23:16 carl-veg Exp $
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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que representa un servidor cupitwitter
 */
public class CupiTwitter
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el socket servidor a cual se conectan los usuarios
     */
    private ServerSocket socketServidor;

    /**
     * Es la configuración del sistema
     */
    private Properties config;

    /**
     * Es el manejador de persistencia
     */
    private ManejadorPersistencia manejadorPersistencia;

    /**
     * Es la lista de usuarios conectados
     */
    private ArrayList usuariosConectados;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo cupitwitter
     * @throws CupiTwitterServidorException En caso de que se encuentre un error en la inicialización del sistema
     */
    public CupiTwitter( ) throws CupiTwitterServidorException
    {
        config = new Properties( );
        try
        {
            config.load( new FileInputStream( "./data/servidor.properties" ) );
            manejadorPersistencia = new ManejadorPersistencia( config );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
        usuariosConectados = new ArrayList( );

    }

    /**
     * Método que inicia el socket servidor para recibir conexiones de los clientes
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    public void esperarConexiones( ) throws CupiTwitterServidorException
    {
        String aux = config.getProperty( "servidor.puerto" );
        int puerto = Integer.parseInt( aux );
        try
        {
            // TODO inicialice el ServerSocket receptor
            socketServidor = new ServerSocket( puerto );
            
            verificarInvariante( );
            while( true )
            {
                // Esperar una nueva conexión
                Socket socketNuevoCliente = socketServidor.accept( );
                
                
                // TODO Acepte una conexión usando el receptor
                
                
                // TODO Atender el nuevo cliente 
                // Ayuda: Use el método registrarUsuario
                registrarUsuario( socketNuevoCliente );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
        finally
        {
            try
            {
                socketServidor.close( );
            }
            catch( IOException e )
            {
                e.printStackTrace( );
            }
        }

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que registra un nuevo usuario
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    private void registrarUsuario( Socket socketNuevoCliente ) throws CupiTwitterServidorException
    {
        // TODO complete el método de acuerdo con su contrato
        // 1. Cree un nuevo manejadorComunicacionesServidor
        // 2. Cree un nuevo ClienteRemotoTwitter
        // 3. Inicie el thread del cliente remoto
        // 4. Agregue el nuevo cliente a la lista de clientes conectados
        
    }

    /**
     * Método que consulta la base de datos y retorna los detalles de un usuario
     * @param usuario El nombre de usuario que se desea consultar
     * @return El usuario
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    public Usuario darDetallesUsuario( String usuario ) throws CupiTwitterServidorException
    {
        // TODO complete el método de acuerdo con su contrato
        try
        {
            return manejadorPersistencia.buscarUsuario( usuario );
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
    }

    /**
     * Método que retorna la lista de usuarios seguidores del usuario que se recibe como parámetro
     * @param usuario el nombre del usuario
     * @return La lista de usuarios seguidores
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    public ArrayList darUsuariosSeguidores( String usuario ) throws CupiTwitterServidorException
    {
        
        // TODO complete el método de acuerdo con su contrato
        try
        {
            return manejadorPersistencia.consultarUsuariosSeguidores( usuario );
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
    }

    /**
     * Método que retorna la lista de usuarios registrados en el sistema
     * 
     * @return Ls lista de usuarios
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    public ArrayList darUsuarios( ) throws CupiTwitterServidorException
    {
        // TODO complete el método de acuerdo con su contrato
        try
        {
            return manejadorPersistencia.consultarUsuariosRegistrados( );
        }
        catch( SQLException e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }
    }

    /**
     * Método que retorna la lista de usuarios conectados
     * 
     * @return La lista de usuarios
     * @throws CupiTwitterServidorException En caso de encontrar un error
     */
    public ArrayList darUsuariosConectados( ) throws CupiTwitterServidorException
    {
        try
        {
            return manejadorPersistencia.consultarUsuariosConectados( );
        }
        catch( Exception e )
        {
            throw new CupiTwitterServidorException( e.getMessage( ) );
        }

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv:</b><br>
     * socketServidor!= null <br>
     * config!=null <br>
     * manejadorPersistencia!=null <br>
     * usuariosConectados!=null <br>
     */
    private void verificarInvariante( )
    {
        assert socketServidor != null : "Canal inválido";
        assert config != null : "Conjunto de propiedades inválidas";
        assert manejadorPersistencia != null : "El manejador de persistencia no debería ser null";
        assert usuariosConectados != null : "La lista de usuarios conectados no debería ser null";
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