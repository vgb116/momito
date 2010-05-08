/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelMicroBlog.java,v 1.7 2010/04/26 21:08:20 carl-veg Exp $
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
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import uniandes.cupi2.cupiTwitter.cliente.mundo.MicroBlog;

/**
 * Panel con la información de un microblog
 */
public class PanelMicroBlog extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Label fecha
     */
    private JLabel lblFecha;

    /**
     * Label nombre de usuario
     */
    private JLabel lblNombreUsuario;

    /**
     * Scroll para el texto del microblog
     */
    private JScrollPane scpTextoMicroBlog;

    /**
     * Texto del microblog
     */
    private JTextArea txtTextoMicroBlog;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de microblog
     * 
     * @param microBlog El microblog con la información que se debe mostrar en el panel
     */
    public PanelMicroBlog( MicroBlog microBlog )
    {
        scpTextoMicroBlog = new JScrollPane( );
        txtTextoMicroBlog = new JTextArea( microBlog.darTexto( ) );
        JSeparator sepInferior = new JSeparator( );
        JPanel panelDatos = new JPanel( );
        lblFecha = new JLabel( microBlog.darFecha( ) );
        lblNombreUsuario = new JLabel( "El usuario " + microBlog.darUsuario( ) + " escribió:" );

        setBackground( new Color( 255, 255, 255 ) );
        setBorder( BorderFactory.createEmptyBorder( 1, 1, 1, 1 ) );
        setForeground( new Color( 255, 255, 255 ) );
        setLayout( new BorderLayout( ) );

        txtTextoMicroBlog.setColumns( 20 );
        txtTextoMicroBlog.setEditable( false );
        txtTextoMicroBlog.setLineWrap( true );
        txtTextoMicroBlog.setRows( 5 );
        txtTextoMicroBlog.setWrapStyleWord( true );
        scpTextoMicroBlog.setViewportView( txtTextoMicroBlog );

        add( scpTextoMicroBlog, BorderLayout.CENTER );
        add( sepInferior, BorderLayout.SOUTH );

        panelDatos.setBackground( new Color( 255, 255, 255 ) );
        panelDatos.setLayout( new GridLayout( 2, 1 ) );

        panelDatos.add( lblFecha );
        panelDatos.add( lblNombreUsuario );

        add( panelDatos, BorderLayout.NORTH );
    }

}
