/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoIniciarSesion.java,v 1.14 2010/04/27 22:29:11 carl-veg Exp $
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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * Dialogo para iniciar sesión
 * 
 */
public class DialogoIniciarSesion extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Es el comando de la opción iniciar sesión
     */
    private static final String INICIAR_SESION = "CREAR_CUENTA";

    /**
     * Es el comando de la opción salir
     */
    private static final String CANCELAR = "SALIR";

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
     * Botón iniciar sesión
     */
    private JButton btnIniciarSesion;

    /**
     * Botón cancelar
     */
    private JButton btnCancelar;

    /**
     * Label imagen
     */
    private JLabel lblImagen;

    /**
     * Label contraseña
     */
    private JLabel lblPwd;

    /**
     * Label usuario
     */
    private JLabel lblUsuario;

    /**
     * Texto password
     */
    private JPasswordField txtPwd;

    /**
     * Texto usuario
     */
    private JTextField txtUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo iniciar sesión
     * @param nPrincipal Referencia a la ventana principal
     */
    public DialogoIniciarSesion( InterfazCliente nPrincipal )
    {
        super( nPrincipal, true );
        setLayout( new BorderLayout( ) );
        principal = nPrincipal;
        setSize( 230, 270 );
        setLocationRelativeTo( null );
        setTitle( "Iniciar Sesión" );
        lblImagen = new JLabel( );
        JPanel panelDatos = new JPanel( );
        lblUsuario = new JLabel( );
        txtUsuario = new JTextField( );
        lblPwd = new JLabel( );
        txtPwd = new JPasswordField( );
        JPanel panelBotones = new JPanel( );
        btnIniciarSesion = new JButton( );
        btnCancelar = new JButton( );

        lblImagen.setHorizontalAlignment( SwingConstants.CENTER );
        lblImagen.setIcon( new ImageIcon( "data/imagenes/inicio_sesion.png" ) );
        add( lblImagen, BorderLayout.NORTH );

        panelDatos.setLayout( new java.awt.GridLayout( 5, 0 ) );

        lblUsuario.setText( "Usuario" );
        panelDatos.add( lblUsuario );
        panelDatos.add( txtUsuario );

        lblPwd.setText( "Contraseña" );
        panelDatos.add( lblPwd );
        panelDatos.add( txtPwd );

        panelBotones.setLayout( new java.awt.GridLayout( 1, 2 ) );

        btnIniciarSesion.setText( "Iniciar Sesión" );
        btnIniciarSesion.setActionCommand( INICIAR_SESION );
        btnIniciarSesion.addActionListener( this );
        panelBotones.add( btnIniciarSesion );

        btnCancelar.setText( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        panelDatos.add( panelBotones );

        add( panelDatos, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        if( command.equals( INICIAR_SESION ) )
        {
            if( txtPwd.getText( ) == null || txtPwd.getText( ).length( ) < 1 )
            {
                JOptionPane.showMessageDialog( this, "Las contraseña no puede estar vacía", "Error Contraseña", JOptionPane.ERROR_MESSAGE );
                return;
            }
            principal.iniciarSesion( txtUsuario.getText( ), txtPwd.getText( ) );
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }
}
