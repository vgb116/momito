/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoCrearCuenta.java,v 1.12 2010/04/26 21:08:20 carl-veg Exp $
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Dialogo para crear una cuenta
 * 
 */
public class DialogoCrearCuenta extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Es el comando de la opci�n crear cuenta
     */
    private static final String CREAR_CUENTA = "CREAR_CUENTA";

    /**
     * Es el comando de la opci�n cancelar
     */
    private static final String CANCELAR = "CANCELAR";

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
     * Bot�n crear cuenta
     */
    private JButton btnCrearCuenta;

    /**
     * Bot�n cancelar
     */
    private JButton btnCancelar;

    /**
     * Label apellidos
     */
    private JLabel lblApellidos;

    /**
     * Label nombres
     */
    private JLabel lblNombres;

    /**
     * Label contrase�a
     */
    private JLabel lblPwd;

    /**
     * Label confirmaci�n de contrase�a
     */
    private JLabel lblPwdConfirmacion;

    /**
     * Label usuario
     */
    private JLabel lblUsuario;

    /**
     * Texto apellidos
     */
    private JTextField txtApellidos;

    /**
     * Texto nombres
     */
    private JTextField txtNombres;

    /**
     * Texto contrase�a
     */
    private JPasswordField txtPwd;

    /**
     * Texto confirmaci�n de contrase�a
     */
    private JPasswordField txtPwdConfirmacion;

    /**
     * Texto nombre de usuario
     */
    private JTextField txtUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo crear cuenta
     * 
     * @param nPrincipal Referencia a la ventana principal
     */
    public DialogoCrearCuenta( InterfazCliente nPrincipal )
    {
        super( nPrincipal, true );
        setSize( 240, 370 );
        setLocationRelativeTo( null );
        setTitle( "Crear Cuenta" );
        principal = nPrincipal;
        JPanel panelDatos = new JPanel( );
        lblUsuario = new JLabel( );
        txtUsuario = new JTextField( );
        lblNombres = new JLabel( );
        txtNombres = new JTextField( );
        lblApellidos = new JLabel( );
        txtApellidos = new JTextField( );
        lblPwd = new JLabel( );
        txtPwd = new JPasswordField( );
        lblPwdConfirmacion = new JLabel( );
        txtPwdConfirmacion = new JPasswordField( );
        JPanel panelOpciones = new JPanel( );
        btnCrearCuenta = new JButton( );
        btnCancelar = new JButton( );

        setLayout( new java.awt.GridLayout( ) );

        panelDatos.setBorder( BorderFactory.createTitledBorder( "Informaci�n de Registro" ) );
        panelDatos.setLayout( new java.awt.GridLayout( 0, 1 ) );

        lblUsuario.setText( "Usuario" );
        panelDatos.add( lblUsuario );
        panelDatos.add( txtUsuario );

        lblNombres.setText( "Nombres" );
        panelDatos.add( lblNombres );
        panelDatos.add( txtNombres );

        lblApellidos.setText( "Apellidos" );
        panelDatos.add( lblApellidos );
        panelDatos.add( txtApellidos );

        lblPwd.setText( "Contrase�a" );
        panelDatos.add( lblPwd );
        panelDatos.add( txtPwd );

        lblPwdConfirmacion.setText( "Confirmaci�n Contrase�a" );
        panelDatos.add( lblPwdConfirmacion );
        panelDatos.add( txtPwdConfirmacion );

        panelOpciones.setLayout( new java.awt.GridLayout( 1, 2 ) );

        btnCrearCuenta.setText( "Crear Cuenta" );
        btnCrearCuenta.addActionListener( this );
        btnCrearCuenta.setActionCommand( CREAR_CUENTA );
        panelOpciones.add( btnCrearCuenta );

        btnCancelar.setText( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        panelOpciones.add( btnCancelar );

        panelDatos.add( panelOpciones );

        add( panelDatos );
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        if( command.equals( CREAR_CUENTA ) )
        {
            if( !txtPwd.getText( ).equals( txtPwdConfirmacion.getText( ) ) )
            {
                JOptionPane.showMessageDialog( this, "Las contrase�as no coinciden", "Error Contrase�a", JOptionPane.ERROR_MESSAGE );
                return;
            }
            principal.crearCuenta( txtUsuario.getText( ), txtNombres.getText( ), txtApellidos.getText( ), txtPwd.getText( ) );
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }

}
