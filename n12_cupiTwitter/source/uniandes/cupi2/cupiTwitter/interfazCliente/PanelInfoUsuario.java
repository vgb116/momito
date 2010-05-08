/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInfoUsuario.java,v 1.11 2010/04/26 21:08:20 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.interfazCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel con la informaci�n del usuario
 */
public class PanelInfoUsuario extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opci�n cerrar sesi�n
     */
    private static final String CERRAR_SESION = "CERRAR_SESION";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz del cliente
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n cerrar sesi�n
     */
    private JButton btnCerrarSesion;

    /**
     * Label imagen
     */
    private JLabel lblImagen;

    /**
     * Label bienvenido
     */
    private JLabel lblBienvenido;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel
     * 
     * @param nPrincipal Referencia la ventana principal
     */
    public PanelInfoUsuario( InterfazCliente nPrincipal )
    {
        principal = nPrincipal;
        lblBienvenido = new JLabel( );
        btnCerrarSesion = new JButton( );
        lblImagen = new JLabel( );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/cliente.png" ) );

        setBackground( new Color( 255, 255, 255 ) );
        setLayout( new BorderLayout( ) );

        lblBienvenido.setText( "<html> Bienvenid@: <p> usuario  </html>" );
        add( lblBienvenido, BorderLayout.WEST );

        btnCerrarSesion.setText( "Cerrar Sesi�n" );
        btnCerrarSesion.addActionListener( this );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        add( btnCerrarSesion, BorderLayout.EAST );

        add( lblImagen, BorderLayout.NORTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo para actualizar la interfaz con la informaci�n de un usuario
     * 
     * @param nombreUsuario El nombre de un usuario
     */
    public void actualizarUsuario( String nombreUsuario )
    {
        lblBienvenido.setText( "<html> Bienvenid@: <p> " + nombreUsuario + "  </html>" );
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        if( command.equals( CERRAR_SESION ) )
        {

            principal.cerrarSesion( );
        }

    }

}
