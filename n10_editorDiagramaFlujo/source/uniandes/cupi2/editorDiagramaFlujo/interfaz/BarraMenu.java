/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarraMenu.java,v 1.2 2009/10/07 13:36:36 c.alvarez947 Exp $
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta es la barra que contiene los menús de la aplicación
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante nuevo
     */
    private static final String NUEVO = "Nuevo";

    /**
     * constante Abrir
     */
    private static final String ABRIR = "Abrir";
    
    //
    // TODO: Completar opciones faltantes
    
    /**
     * constante Guardar
     */
    private static final String GUARDAR = "Guardar";
    
    /**
     * constante GuardarComo
     */
    private static final String GUARDARCOMO = "Guardar Como";
    
    /**
     * constante Salir
     */
    private static final String SALIR = "Salir";

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
     * El menú Archivo
     */
    private JMenu menuArchivo;

    /**
     * La opción Nuevo del menú Archivo
     */
    private JMenuItem itemNuevo;

    /**
     * La opción Abrir del menú Archivo
     */
    private JMenuItem itemAbrir;

    //
    // TODO: Completar opciones faltantes

    /**
     * La opción Guardar del menú Archivo
     */
    private JMenuItem itemGuardar;

    /**
     * La opción GuardarComo del menú Archivo
     */
    private JMenuItem itemGuardarComo;
    

    /**
     * La opción Salir del menú Archivo
     */
    private JMenuItem itemSalir;
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la barra de menú
     * @param ventana Es una referencia a la clase principal de la interfaz
     */
    public BarraMenu( InterfazEditorDiagramaFlujo ventana )
    {
        principal = ventana;

        menuArchivo = new JMenu( "Archivo" );
        add( menuArchivo );

        itemNuevo = new JMenuItem( "Nuevo" );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.addActionListener( this );
        menuArchivo.add( itemNuevo );

        itemAbrir = new JMenuItem( "Abrir" );
        itemAbrir.setActionCommand( ABRIR );
        itemAbrir.addActionListener( this );
        menuArchivo.add( itemAbrir );

        //
        // TODO: Completar opciones faltantes
        
        itemGuardar = new JMenuItem( "Guardar" );
        itemGuardar.setActionCommand( GUARDAR );
        itemGuardar.addActionListener( this );
        menuArchivo.add( itemGuardar );
        
        itemGuardarComo = new JMenuItem( "Guardar Como" );
        itemGuardarComo.setActionCommand( GUARDARCOMO );
        itemGuardarComo.addActionListener( this );
        menuArchivo.add( itemGuardarComo );
        
        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuArchivo.add( itemSalir );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acción que corresponde a la opción del menú que fue seleccionada
     * @param evento Es el evento de seleccionar una opción del menú - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( NUEVO.equals( comando ) )
        {
            principal.reiniciar( );
        }
        else if( ABRIR.equals( comando ) )
        {
            principal.abrir( );
        }
        //
        // TODO: Completar opciones faltantes
        else if(GUARDAR.equals( comando ))
        {
            principal.salvar( );
        }
        else if(GUARDARCOMO.equals( comando ))
        {
            principal.salvarComo( );
        }
        else if(SALIR.equals( comando ))
        {
            principal.dispose( );
        }
    }

}