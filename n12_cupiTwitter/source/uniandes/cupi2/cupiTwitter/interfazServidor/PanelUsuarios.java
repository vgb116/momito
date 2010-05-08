/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelUsuarios.java,v 1.8 2010/04/26 21:08:20 carl-veg Exp $
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.cupiTwitter.servidor.mundo.Usuario;

/**
 * Panel con la lista de usuarios
 */
public class PanelUsuarios extends JPanel implements ActionListener, ListSelectionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando consultar todos los usuarios
     */
    private static final String CONSULTAR_USUARIOS = "CONSULTAR_USUARIOS";

    /**
     * Comando consultar usuarios conectados
     */
    private static final String CONSULTAR_USUARIOS_CONECTADOS = "CONSULTAR_USUARIOS_CONECTADOS";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazServidor principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Grupo de botones para ver usuario
     */
    private ButtonGroup btnGroupVerUsuarios;

    /**
     * Botón ver usuarios conectados
     */
    private JToggleButton btnVerUsuariosConectados;

    /**
     * botón ver todos los usuarios
     */
    private JToggleButton btnVertodosUsuarios;

    /**
     * Lista de usuarios
     */
    private JList lstUsuarios;

    /**
     * Scroll para la lista de usuarios
     */
    private JScrollPane jScrollPane;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Panel usuarios
     * 
     * @param nPrincipal Referencia a la ventana principal
     */
    public PanelUsuarios( InterfazServidor nPrincipal )
    {
        principal = nPrincipal;
        btnGroupVerUsuarios = new ButtonGroup( );
        JPanel panelBotones = new JPanel( );
        btnVertodosUsuarios = new JToggleButton( );
        btnVerUsuariosConectados = new JToggleButton( );
        jScrollPane = new JScrollPane( );
        lstUsuarios = new JList( );
        lstUsuarios.addListSelectionListener( this );

        setLayout( new BorderLayout( ) );

        panelBotones.setLayout( new GridLayout( 1, 1 ) );

        btnGroupVerUsuarios.add( btnVertodosUsuarios );

        btnVertodosUsuarios.setText( "Ver todos los usuarios" );
        btnVertodosUsuarios.addActionListener( this );
        btnVertodosUsuarios.setActionCommand( CONSULTAR_USUARIOS );
        panelBotones.add( btnVertodosUsuarios );

        btnGroupVerUsuarios.add( btnVerUsuariosConectados );

        btnVerUsuariosConectados.setText( "Ver solo usuarios conectados" );
        btnVerUsuariosConectados.addActionListener( this );
        btnVerUsuariosConectados.setActionCommand( CONSULTAR_USUARIOS_CONECTADOS );
        panelBotones.add( btnVerUsuariosConectados );

        add( panelBotones, BorderLayout.NORTH );

        jScrollPane.setBorder( BorderFactory.createTitledBorder( "Usuarios" ) );

        jScrollPane.setViewportView( lstUsuarios );

        add( jScrollPane, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de usuarios
     * 
     * @param usuarios La lista de usuarios
     */
    public void actualizar( ArrayList usuarios )
    {
        lstUsuarios.setListData( usuarios.toArray( new Usuario[0] ) );
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( CONSULTAR_USUARIOS.equals( e.getActionCommand( ) ) )
        {
            principal.actualizarUsuarios( );
        }
        else if( CONSULTAR_USUARIOS_CONECTADOS.equals( e.getActionCommand( ) ) )
        {
            principal.actualizarUsuariosConectados( );
        }
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( lstUsuarios.getSelectedValue( ) != null )
        {
            Usuario usuario = ( Usuario )lstUsuarios.getSelectedValue( );
            principal.actualizarInformacionUsuario( usuario.darUsuario( ) );
        }
    }

}
