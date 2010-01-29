/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelOpcionesOrdenamiento.java,v 1.8 2009/10/15 00:51:47 carl-veg Exp $
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel para las opciones de ordenamiento
 */
public class PanelOpcionesOrdenamiento extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando ordenar por ciudad
     */
    private static final String ORDENAR_POR_CIUDAD = "ORDENAR_POR_CIUDAD";

    /**
     * Comando ordenar por precio
     */
    private static final String ORDENAR_POR_PRECIO = "ORDENAR_POR_PRECIO";

    /**
     * Comando ordenar por tamaño
     */
    private static final String ORDENAR_POR_TAMANIO = "ORDENAR_POR_TAMAÑO";


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la interfaz principal
     */
    private InterfazFincaRaiz principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón order por ciudad
     */
    private JButton btnOrdenarCiudad;

    /**
     * Botón Ordenar por precio
     */
    private JButton btnOrdenarPrecio;

    /**
     * Botón ordenar por tamaño
     */
    private JButton btnOrdenarTamanio;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor que inicializa los atributos de la clase
     * @param interfazFincaRaiz Referencia a la ventana principal
     */
    public PanelOpcionesOrdenamiento( InterfazFincaRaiz interfazFincaRaiz )
    {
        principal = interfazFincaRaiz;
        btnOrdenarCiudad = new JButton( );
        btnOrdenarPrecio = new JButton( );
        btnOrdenarTamanio = new JButton( );

        setBorder( BorderFactory.createTitledBorder( "Opciones de Ordenamiento" ) );
        setLayout( new GridLayout( 3, 1 ) );

        btnOrdenarCiudad.setText( "Por Ciudad" );
        btnOrdenarCiudad.setActionCommand( ORDENAR_POR_CIUDAD );
        btnOrdenarCiudad.addActionListener( this );
        add( btnOrdenarCiudad );

        btnOrdenarPrecio.setText( "Por Precio" );
        btnOrdenarPrecio.setActionCommand( ORDENAR_POR_PRECIO );
        btnOrdenarPrecio.addActionListener( this );
        add( btnOrdenarPrecio );

        btnOrdenarTamanio.setText( "Por Tamaño" );
        btnOrdenarTamanio.setActionCommand( ORDENAR_POR_TAMANIO );
        btnOrdenarTamanio.addActionListener( this );
        add( btnOrdenarTamanio );

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
        String cmd = e.getActionCommand( );
        if( cmd.equals( ORDENAR_POR_CIUDAD ) )
        {
            principal.ordenarPorCiudad( );
        }
        else if( cmd.equals( ORDENAR_POR_PRECIO ) )
        {
            principal.ordenarPorPrecio( );
        }
        else if( cmd.equals( ORDENAR_POR_TAMANIO ) )
        {
            principal.ordenarPorTamanio( );
        }

    }
}
