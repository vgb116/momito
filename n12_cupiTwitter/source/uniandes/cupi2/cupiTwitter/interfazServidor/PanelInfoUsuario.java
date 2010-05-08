/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInfoUsuario.java,v 1.8 2010/04/26 21:08:20 carl-veg Exp $
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

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupiTwitter.servidor.mundo.Usuario;

/**
 * Panel con la información del usuario
 */
public class PanelInfoUsuario extends JPanel
{
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
     * Label apellidos
     */
    private JLabel lblApellidos;

    /**
     * Label nombres
     */
    private JLabel lblNombre;

    /**
     * Label total mensajes
     */
    private JLabel lblTotalMensajes;

    /**
     * Label total seguidores
     */
    private JLabel lblTotalSeguidores;

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
    private JTextField txtNombre;

    /**
     * Texto total mensajes
     */
    private JTextField txtTotalMensajes;

    /**
     * Texto total seguidores
     */
    private JTextField txtTotalSeguidores;

    /**
     * Texto usuario
     */
    private JTextField txtUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel con la información del usuario
     */
    public PanelInfoUsuario( )
    {

        lblUsuario = new JLabel( );
        txtUsuario = new JTextField( );
        lblNombre = new JLabel( );
        txtNombre = new JTextField( );
        lblApellidos = new JLabel( );
        txtApellidos = new JTextField( );
        lblTotalMensajes = new JLabel( );
        txtTotalMensajes = new JTextField( );
        lblTotalSeguidores = new JLabel( );
        txtTotalSeguidores = new JTextField( );

        setBorder( BorderFactory.createTitledBorder( "Información del Usuario" ) );
        setLayout( new GridLayout( 0, 1 ) );

        lblUsuario.setText( "Usuario:" );
        add( lblUsuario );

        txtUsuario.setColumns( 17 );
        txtUsuario.setEditable( false );
        add( txtUsuario );

        lblNombre.setText( "Nombre:" );
        add( lblNombre );

        txtNombre.setEditable( false );
        add( txtNombre );

        lblApellidos.setText( "Apellidos" );
        add( lblApellidos );

        txtApellidos.setEditable( false );
        add( txtApellidos );

        lblTotalMensajes.setText( "Total Mensajes" );
        add( lblTotalMensajes );

        txtTotalMensajes.setEditable( false );
        add( txtTotalMensajes );

        lblTotalSeguidores.setText( "Total Seguidores" );
        add( lblTotalSeguidores );

        txtTotalSeguidores.setEditable( false );
        add( txtTotalSeguidores );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la interfaz con la información del usuario
     * 
     * @param usuario El usuario con el que se debe actualizar la interfaz
     */
    public void actualizar( Usuario usuario )
    {
        txtUsuario.setText( usuario.darUsuario( ) );
        txtNombre.setText( usuario.darNombre( ) );
        txtApellidos.setText( usuario.darApellidos( ) );
        txtTotalMensajes.setText( usuario.darTotalMensajes( ) + "" );
        txtTotalSeguidores.setText( usuario.darTotalSeguidores( ) + "" );
    }

}
