package uniandes.cupi2.cupiPosts.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.cupiPosts.mundo.Post;

/**
 * Clase que muestra para una categor�a todos los post asociados
 * 
 */
public class PanelCategoria extends JPanel
{

    // -----------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Scroll del panel principal
     */
    private JScrollPane scroll;

    /**
     * El panel principal
     */
    private JPanel panelPrincipal;

    // ------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicaci�n
     */
    private InterfazCupiPosts principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel que contiene la informaci�n de todos los posts que pertenecen a esta categor�a
     * @param interfaz La ventana principal de la aplicaci�n
     */
    public PanelCategoria( InterfazCupiPosts interfaz )
    {

        principal = interfaz;
        setLayout( new BorderLayout( ) );

        scroll = new JScrollPane( );
        panelPrincipal = new JPanel( );
        scroll.setViewportView( panelPrincipal );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setPreferredSize( new Dimension( 800, 515 ) );
        add( scroll, BorderLayout.CENTER );
    }

    // ------------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la lista de post que recibe por par�metro
     * @param losPosts La lista de posts asociados a la categor�a
     */
    public void actualizar( ArrayList losPosts )
    {
        panelPrincipal.removeAll( );
        if( losPosts.size( ) == 0 )
        {

            JLabel labelMensaje = new JLabel( "No hay Posts en esta categor�a" );
            labelMensaje.setForeground( Color.RED );
            labelMensaje.setHorizontalAlignment( JLabel.CENTER );
            labelMensaje.setFont( new Font( "Tahoma", Font.ITALIC, 18 ) );
            panelPrincipal.add( labelMensaje );

        }
        else
        {

            panelPrincipal.setLayout( new GridLayout( losPosts.size( ), 1 ) );
            for( int i = 0; i < losPosts.size( ); i++ )
            {
                Post temp = ( Post )losPosts.get( i );
                PanelPost panelPost = new PanelPost( principal );
                panelPost.actualizarDatos( temp );
                panelPrincipal.add( panelPost );
            }
        }

        panelPrincipal.validate( );
        scroll.validate( );
        scroll.repaint( );
    }
}
