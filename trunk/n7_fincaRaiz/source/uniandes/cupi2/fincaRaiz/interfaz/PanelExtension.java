/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.5 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_fincaRaiz
 * Autor: Camilo Alvarez Duran - 21-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.fincaRaiz.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando registrar inmueble
     */
    private static final String REGISTRAR_INMUEBLE = "AGREGAR_INMUEBLE";

    /**
     * Comando eliminar inmueble
     */
    private static final String ELIMINAR_INMUEBLE = "ELIMINAR_INMUEBLE";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazFincaRaiz principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón registrar
     */
    private JButton btnRegistrar;

    /**
     * Botón eliminar
     */
    private JButton btnEliminar;

    /**
     * Panel con las opciones de búsqueda
     */
    private PanelOpcionesBusqueda panelOpcionesBusqueda;

    /**
     * Panel con las opciones de ordenamiento
     */
    private PanelOpcionesOrdenamiento panelOpcionesOrdenamiento;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazFincaRaiz ventana )
    {
        principal = ventana;

        panelOpcionesBusqueda = new PanelOpcionesBusqueda( ventana );
        panelOpcionesOrdenamiento = new PanelOpcionesOrdenamiento( ventana );

        setLayout( new GridLayout( 1, 3 ) );

        JPanel panelExtension = new JPanel( );
        panelExtension.setBorder( new TitledBorder( "Opciones" ) );
        panelExtension.setLayout( new GridLayout( 4, 1 ) );

        btnRegistrar = new JButton( "Registrar Inmueble" );
        btnRegistrar.setActionCommand( REGISTRAR_INMUEBLE );
        btnRegistrar.addActionListener( this );
        panelExtension.add( btnRegistrar );

        btnEliminar = new JButton( "Eliminar Inmueble" );
        btnEliminar.setActionCommand( ELIMINAR_INMUEBLE );
        btnEliminar.addActionListener( this );
        panelExtension.add( btnEliminar );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        panelExtension.add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        panelExtension.add( btnOpcion2 );

        JPanel panelP = new JPanel( );
        panelP.setLayout( new GridLayout( 1, 2 ) );

        add( panelExtension );
        add( panelOpcionesOrdenamiento );
        add( panelOpcionesBusqueda );

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
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( REGISTRAR_INMUEBLE.equals( e.getActionCommand( ) ) )
        {
            principal.registrarInmueble( );
        }
        else if( ELIMINAR_INMUEBLE.equals( e.getActionCommand( ) ) )
        {
            principal.eliminarInmueble( );
        }
    }

}
