/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoTexto.java,v 1.2 2009/10/07 13:36:36 c.alvarez947 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.interfaz;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Es el diálogo para modificar el texto y la fuente de una figura
 */
public class DialogoTexto extends JDialog
{

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazEditorDiagramaFlujo principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El panel donde se modifica el texto y la fuente usada en una figura
     */
    private PanelModificarTexto panelModificar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo para modificar el texto y la fuente de una figura
     * @param ip Es una referencia a la clase principal de la interfaz
     * @param texto Es el texto que se escribirá sobre la figura
     * @param fuente Es la fuente que se usará para el texto de la figura
     */
    public DialogoTexto( InterfazEditorDiagramaFlujo ip, String texto, Font fuente )
    {
        super( ip, "Cambiar Texto", true );

        principal = ip;

        // Averiguar cuales son las fuentes disponibles en el sistema
        String[] nombresFuentes = GraphicsEnvironment.getLocalGraphicsEnvironment( ).getAvailableFontFamilyNames( );

        ArrayList listaFuentes = new ArrayList( );
        for( int i = 0; i < nombresFuentes.length; i++ )
        {
            listaFuentes.add( nombresFuentes[ i ] );
        }

        // Construir el panel y mostrar el diálogo
        panelModificar = new PanelModificarTexto( this, listaFuentes );
        getContentPane( ).add( panelModificar );
        panelModificar.cambiarTexto( texto, fuente );

        setSize( 350, 180 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el texto mostrado
     * @param texto Nuevo texto
     * @param fuente Nueva fuente
     */
    public void cambiarTexto( String texto, Font fuente )
    {
        principal.cambiarTexto( texto, fuente );
        setVisible( false );
        dispose( );
    }
}