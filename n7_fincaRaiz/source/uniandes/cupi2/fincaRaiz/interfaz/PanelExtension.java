/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.5 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
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
     * Ventana principal de la aplicaci�n
     */
    private InterfazFincaRaiz principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    /**
     * Bot�n registrar
     */
    private JButton btnRegistrar;

    /**
     * Bot�n eliminar
     */
    private JButton btnEliminar;

    /**
     * Panel con las opciones de b�squeda
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

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        panelExtension.add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Opci�n 2" );
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
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
