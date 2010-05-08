/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazServidor.java,v 1.11 2010/04/26 21:08:20 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.interfazServidor;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import uniandes.cupi2.cupiTwitter.servidor.mundo.CupiTwitter;
import uniandes.cupi2.cupiTwitter.servidor.mundo.CupiTwitterServidorException;

/**
 * Interfaz principal del servidor
 */
public class InterfazServidor extends JFrame
{

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiTwitter cupiTwitter;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la imagen superior
     */
    private PanelImagen panelImagen;

    /**
     * Panel información de usuario
     */
    private PanelInfoUsuario panelInfoUsuario;

    /**
     * Panel con la lista de usuarios
     */
    private PanelUsuarios panelUsuarios;

    /**
     * Panel con la lista de seguidores
     */
    private PanelUsuariosSeguidores panelUsuariosSeguidores1;

    /**
     * Panel con las extensiones de la interfaz
     */
    private PanelExtension panelExtension;

    /**
     * Construye un nuevo panel para la interfaz principal
     * 
     * @param nCupiTwitter Referencia al mundo
     */
    public InterfazServidor( CupiTwitter nCupiTwitter )
    {
        setTitle( "Servidor Cupitwitter" );
        setSize( 700, 620 );

        setLocationRelativeTo( null );

        panelExtension = new PanelExtension( this );
        panelImagen = new PanelImagen( );
        panelUsuarios = new PanelUsuarios( this );
        JPanel panelDetalleUsurio = new JPanel( );
        panelInfoUsuario = new PanelInfoUsuario( );
        panelUsuariosSeguidores1 = new PanelUsuariosSeguidores( );

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        add( panelUsuarios, java.awt.BorderLayout.CENTER );

        panelDetalleUsurio.setLayout( new BoxLayout( panelDetalleUsurio, BoxLayout.PAGE_AXIS ) );
        panelDetalleUsurio.add( panelInfoUsuario );
        panelDetalleUsurio.add( panelUsuariosSeguidores1 );

        add( panelImagen, java.awt.BorderLayout.NORTH );
        add( panelDetalleUsurio, java.awt.BorderLayout.EAST );
        add( panelExtension, java.awt.BorderLayout.SOUTH );
        setVisible( true );
        cupiTwitter = nCupiTwitter;

    }

    /**
     * Método que actualiza la lista de usuarios conectados
     */
    public void actualizarUsuariosConectados( )
    {
        try
        {
            panelUsuarios.actualizar( cupiTwitter.darUsuariosConectados( ) );
        }
        catch( CupiTwitterServidorException e )
        {
            mostrarMsjError( e );
        }
    }

    /**
     * Método que actualiza la lista de todos los usuarios
     */
    public void actualizarUsuarios( )
    {
        try
        {
            panelUsuarios.actualizar( cupiTwitter.darUsuarios( ) );
        }
        catch( CupiTwitterServidorException e )
        {
            mostrarMsjError( e );
        }
    }

    /**
     * Método que actualiza la información de un usuario
     * 
     * @param nUsuario El nombre del usuario
     */
    public void actualizarInformacionUsuario( String nUsuario )
    {
        try
        {
            panelInfoUsuario.actualizar( cupiTwitter.darDetallesUsuario( nUsuario ) );

            panelUsuariosSeguidores1.actualizar( cupiTwitter.darUsuariosSeguidores( nUsuario ) );
        }
        catch( CupiTwitterServidorException e )
        {
            mostrarMsjError( e );
        }
    }

    /**
     * Método que muestra un mensaje de error utilizando de una excepción
     * 
     * @param ex La excepción
     */
    public void mostrarMsjError( Exception ex )
    {
        JOptionPane.showMessageDialog( this, ex.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    }

    /**
     * Método que muestra un mensaje de error
     * 
     * @param msj El mensaje
     */
    public void mostrarMsjError( String msj )
    {
        JOptionPane.showMessageDialog( this, msj, "Error", JOptionPane.ERROR_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupiTwitter.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiTwitter.metodo2( );
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
    public static void main( String[] args )
    {
        try
        {
            CupiTwitter cupiTwitter = new CupiTwitter( );
            InterfazServidor interfazServidor = new InterfazServidor( cupiTwitter );
            interfazServidor.setVisible( true );
            cupiTwitter.esperarConexiones( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

}
