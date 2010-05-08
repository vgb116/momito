/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelOpciones.java,v 1.10 2010/04/26 21:08:20 carl-veg Exp $
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel con las opciones de la interfaz
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Es el comando de la opción actualizar los microblogs
     */
    private static final String ACTUALIZAR_MICROBLOGS = "ACTUALIZAR_MICROBLOGS";

    /**
     * Es el comando de la opción seguir un usuario
     */
    private static final String SEGUIR_USUARIO = "SEGUIR_USUARIO";

    /**
     * Es el comando de la opción dejar de seguir un usuario
     */
    private static final String DEJAR_SEGUIR_USUARIO = "DEJAR_SEGUIR_USUARIO";

    /**
     * Es el comando de la opción escribir microblog
     */
    private static final String ESCRIBIR_MICROBLOG = "ESCRIBIR_MICROBLOG";

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

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
     * Botón actualiza
     */
    private JButton btnActualizar;

    /**
     * Botón escribir microblog
     */
    private JButton btnEscribirMicroblog;

    /**
     * Botón opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón seguir usuario
     */
    private JButton btnSeguirUsuario;

    /**
     * Botón dejar de seguir usuario
     */
    private JButton btnDejarSeguirUsuario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de opciones
     * 
     * @param nPrincipal Referencia a la ventana principal
     */
    public PanelOpciones( InterfazCliente nPrincipal )
    {
        principal = nPrincipal;
        GridBagConstraints gridBagConstraints;

        btnActualizar = new JButton( );
        btnSeguirUsuario = new JButton( );
        btnEscribirMicroblog = new JButton( );
        btnDejarSeguirUsuario = new JButton( );
        btnOpcion1 = new JButton( );
        btnOpcion2 = new JButton( );

        setBorder( BorderFactory.createTitledBorder( "Opciones" ) );
        setLayout( new GridBagLayout( ) );

        btnEscribirMicroblog.setText( "Escribir MicroBlog" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        btnEscribirMicroblog.addActionListener( this );
        btnEscribirMicroblog.setActionCommand( ESCRIBIR_MICROBLOG );
        add( btnEscribirMicroblog, gridBagConstraints );

        gridBagConstraints.gridy = 1;
        btnSeguirUsuario.setText( "Seguir Usuario" );
        btnSeguirUsuario.setActionCommand( SEGUIR_USUARIO );
        btnSeguirUsuario.addActionListener( this );
        add( btnSeguirUsuario, gridBagConstraints );

        gridBagConstraints.gridy = 2;
        btnDejarSeguirUsuario.setText( "Dejar de Seguir Usuario" );
        btnDejarSeguirUsuario.setActionCommand( DEJAR_SEGUIR_USUARIO );
        btnDejarSeguirUsuario.addActionListener( this );
        add( btnDejarSeguirUsuario, gridBagConstraints );

        gridBagConstraints.gridy = 3;
        btnActualizar.setText( "Refrescar" );
        btnActualizar.setActionCommand( ACTUALIZAR_MICROBLOGS );
        btnActualizar.addActionListener( this );
        add( btnActualizar, gridBagConstraints );

        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );

        btnOpcion1.setText( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        panelBotones.add( btnOpcion1 );

        btnOpcion2.setText( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        panelBotones.add( btnOpcion2 );

        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;

        add( panelBotones, gridBagConstraints );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        if( command.equals( ACTUALIZAR_MICROBLOGS ) )
        {
            principal.consultarMicroblogs( );
        }
        else if( command.equals( SEGUIR_USUARIO ) )
        {

            String usuario = JOptionPane.showInputDialog( "Ingrese el nombre del usuario que desea seguir:" );
            if( usuario != null && usuario.length( ) > 0 )
            {
                principal.seguirUsuario( usuario );
            }
            else
            {
                return;
            }
        }
        else if( command.equals( DEJAR_SEGUIR_USUARIO ) )
        {

            String usuario = JOptionPane.showInputDialog( "Ingrese el nombre del usuario que desea dejar de seguir:" );
            if( usuario != null && usuario.length( ) > 0 )
            {
                principal.dejarSeguirUsuario( usuario );
            }
            else
            {
                return;
            }
        }
        else if( command.equals( ESCRIBIR_MICROBLOG ) )
        {
            DialogoEscribirMicroBlog panelEscribirMicroBlog = new DialogoEscribirMicroBlog( principal );
            panelEscribirMicroBlog.setVisible( true );
        }
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }

    }

}
