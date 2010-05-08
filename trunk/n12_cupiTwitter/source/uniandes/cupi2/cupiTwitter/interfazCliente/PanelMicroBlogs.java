/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelMicroBlogs.java,v 1.8 2010/04/26 21:08:20 carl-veg Exp $
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

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.cupiTwitter.cliente.mundo.MicroBlog;

/**
 * Panel con la lista de microblogs
 */
public class PanelMicroBlogs extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Panel para la lista de microblogs
     */
    private JPanel panelListaMicroBlogs;

    /**
     * Scroll para lista de microblogs
     */
    private JScrollPane scpBlogs;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel
     */
    public PanelMicroBlogs( )
    {
        scpBlogs = new JScrollPane( );
        panelListaMicroBlogs = new JPanel( );
        setLayout( new GridLayout( 1, 0 ) );
        panelListaMicroBlogs.setLayout( new BoxLayout( panelListaMicroBlogs, BoxLayout.Y_AXIS ) );
        scpBlogs.setViewportView( panelListaMicroBlogs );

        add( scpBlogs );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que actualiza la lista de microblogs
     * 
     * @param microblogs La lista de microblogs
     */
    public void actualizar( ArrayList microblogs )
    {
        panelListaMicroBlogs.removeAll( );
        for( int i = 0; i < microblogs.size( ); i++ )
        {
            MicroBlog microBlog = ( MicroBlog )microblogs.get( i );
            PanelMicroBlog blog = new PanelMicroBlog( microBlog );
            panelListaMicroBlogs.add( blog );
        }
        updateUI( );
    }
}
