/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCliente.java,v 1.17 2010/04/27 22:29:11 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.interfazCliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import uniandes.cupi2.cupiTwitter.cliente.mundo.ClienteCupiTwitter;
import uniandes.cupi2.cupiTwitter.cliente.mundo.CupiTwitterException;
import uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios;

/**
 * Ventana principal del cliente
 */
public class InterfazCliente extends JFrame implements IEscucharCambios
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Dialogo cargando
     */
    private DialogoCargando dialogoCargando;

    /**
     * Dialogo crear cuenta
     */
    private DialogoCrearCuenta dialogoCrearCuenta;

    /**
     * Dialogo iniciar sesión
     */
    private DialogoIniciarSesion dialogoIniciarSesion;

    /**
     * Panel información de usuario
     */
    private PanelInfoUsuario panelInfoUsuario;

    /**
     * Panel con la lista de microblogs
     */
    private PanelMicroBlogs panelMicroBlogs;

    /**
     * Panel opciones
     */
    private PanelOpciones panelOpciones;

    /**
     * Referencia a la clase principal del mundo
     */
    private ClienteCupiTwitter cliente;

    /**
     * Dialogo inicio cliente
     */
    private DialogoInicioCliente dialogoInicio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva interfaz cliente
     * @throws CupiTwitterException Si ocurre un error al conectarse al servidor
     */
    public InterfazCliente( ) throws CupiTwitterException
    {
        cliente = new ClienteCupiTwitter( this );
        setTitle( "CupiTwitter" );
        panelInfoUsuario = new PanelInfoUsuario( this );
        panelMicroBlogs = new PanelMicroBlogs( );
        panelOpciones = new PanelOpciones( this );
        dialogoCrearCuenta = new DialogoCrearCuenta( this );
        dialogoIniciarSesion = new DialogoIniciarSesion( this );
        dialogoCargando = new DialogoCargando( this );
        dialogoInicio = new DialogoInicioCliente( this );
        setSize( 300, 610 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        add( panelInfoUsuario, java.awt.BorderLayout.NORTH );
        add( panelMicroBlogs, java.awt.BorderLayout.CENTER );
        add( panelOpciones, java.awt.BorderLayout.PAGE_END );
        dialogoInicio.setVisible( true );
        if( cliente.darNombreUsuario( ) == null )
        {
            System.exit( 0 );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que muestra el dialogo crear cuenta
     */
    public void mostrarDialogoCrearCuenta( )
    {
        dialogoCrearCuenta.setVisible( true );
    }

    /**
     * Método que muestra el dialogo iniciar sesión
     */
    public void mostrarDialogoIniciarSesion( )
    {
        dialogoIniciarSesion.setVisible( true );
    }

    /**
     * Método para iniciar sesíon con los parámetros dados
     * @param usuario El usuario
     * @param pwd La contraseña
     */
    public void iniciarSesion( String usuario, String pwd )
    {
        cliente.iniciarSesion( usuario, pwd );
        mostrarMensajeEspera( );
    }

    /**
     * Método para crear una nueva cuenta
     * @param usuario El nombre de usuario
     * @param nombre Los nombres del usuario
     * @param apellidos Los apellidos del usuario
     * @param pwd La contraseña para la cuenta
     */
    public void crearCuenta( String usuario, String nombre, String apellidos, String pwd )
    {
        cliente.crearCuenta( usuario, nombre, apellidos, pwd );
        mostrarMensajeEspera( );
    }

    /**
     * Método que consulta la lista de usuarios
     */
    public void consultarMicroblogs( )
    {
        cliente.consultarMibroblogs( );
        mostrarMensajeEspera( );
    }

    /**
     * Método que que registra el seguimiento a un usuario
     * @param usuario El usuario que se desea seguir
     */
    public void seguirUsuario( String usuario )
    {
        cliente.seguirUsuario( usuario );
        mostrarMensajeEspera( );
    }

    /**
     * Método que que registra cuando se desea dejar de seguir un usuario
     * @param usuario El usuario que se desea dejar de seguir
     */
    public void dejarSeguirUsuario( String usuario )
    {
        cliente.dejarSeguirUsuario( usuario );
        mostrarMensajeEspera( );
    }

    /**
     * Método que registra un nuevo microblog
     * @param texto El texto del microblog
     */
    public void escribirMibroblog( String texto )
    {
        cliente.escribirMicroBlogs( texto );
        mostrarMensajeEspera( );
    }

    /**
     * Método que cierra la sesión
     */
    public void cerrarSesion( )
    {
        cliente.cerrarSesion( );
        mostrarMensajeEspera( );
    }

    /**
     * Método que muestra un mensaje de error utilizando de una excepción
     * @param ex La excepción
     */
    public void mostrarMsjError( Exception ex )
    {
        JOptionPane.showMessageDialog( this, ex.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Método que muestra un mensaje de error
     * @param msj El mensaje
     */
    public void mostrarMsjError( String msj )
    {
        JOptionPane.showMessageDialog( this, msj, "Error", JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Método que muestra el dialogo de espera
     */
    public void mostrarMensajeEspera( )
    {
        dialogoCargando.setVisible( true );
    }

    /**
     * Método que oculta el dialogo de espera
     */
    public void ocultarMensajeEsperar( )
    {
        dialogoCargando.setVisible( false );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarCrearCuenta()} .
     */
    public void actualizarCrearCuenta( )
    {
        dialogoCrearCuenta.setVisible( false );
        dialogoInicio.setVisible( false );
        panelInfoUsuario.actualizarUsuario( cliente.darNombreUsuario( ) );
        ocultarMensajeEsperar( );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarIniciarSesion()} .
     */
    public void actualizarIniciarSesion( )
    {
        ocultarMensajeEsperar( );
        dialogoIniciarSesion.setVisible( false );
        dialogoInicio.setVisible( false );
        panelInfoUsuario.actualizarUsuario( cliente.darNombreUsuario( ) );
        consultarMicroblogs( );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarMicroBlogs()} .
     */
    public void actualizarMicroBlogs( )
    {
        panelMicroBlogs.actualizar( cliente.darMicroblogs( ) );
        ocultarMensajeEsperar( );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarEscribirMicroblog()} .
     */
    public void actualizarEscribirMicroblog( )
    {
        ocultarMensajeEsperar( );
        JOptionPane.showMessageDialog( this, "Su microblog se ha registrado con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarError(String msj)} .
     */
    public void actualizarError( String msj )
    {
        ocultarMensajeEsperar( );
        mostrarMsjError( msj );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarSeguirUsuario()} .
     */
    public void actualizarSeguirUsuario( )
    {
        ocultarMensajeEsperar( );
        JOptionPane.showMessageDialog( this, "Se ha sido registrado como seguidor del usuario de forma satisfactoria", "Mensaje", JOptionPane.INFORMATION_MESSAGE );
        consultarMicroblogs( );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarDejarSeguirUsuario()} .
     */
    public void actualizarDejarSeguirUsuario( )
    {
        ocultarMensajeEsperar( );
        JOptionPane.showMessageDialog( this, "Se ha dejado de seguir el usuario de forma satisfactoria", "Mensaje", JOptionPane.INFORMATION_MESSAGE );
        consultarMicroblogs( );
    }

    /**
     * Test method for {@link uniandes.cupi2.cupiTwitter.cliente.mundo.IEscucharCambios#actualizarCerrarSesion()} .
     */
    public void actualizarCerrarSesion( )
    {
        dispose( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cliente.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cliente.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * 
     * @param args Parámetros de ejecución que no son usados
     */
    public static void main( String args[] )
    {
        InterfazCliente interfazCliente = null;
        try
        {
            interfazCliente = new InterfazCliente( );
            interfazCliente.setVisible( true );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, "Error inicializando el mundo. \n" + e.getMessage( ) );
        }

    }
}
