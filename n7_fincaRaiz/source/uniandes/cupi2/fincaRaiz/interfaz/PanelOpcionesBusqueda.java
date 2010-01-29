/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelOpcionesBusqueda.java,v 1.6 2009/10/15 00:51:47 carl-veg Exp $
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
 * Panel para las opciones de búsqueda
 */
public class PanelOpcionesBusqueda extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando buscar por identificador
     */
    private static final String BUSCAR_POR_IDENTIFICADOR = "BUSCAR_POR_IDENTIFICADOR";

    /**
     * Comando buscar mas costoso para vender
     */
    private static final String BUSCAR_MAS_COSTOSO = "BUSCAR_MAS_COSTOSO";

    /**
     * Comando buscar mas económico para arrendar
     */
    private static final String BUSCAR_MAS_ECONOMICO = "BUSCAR_MAS_ECONOMICO";

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
     * Botón buscar por identificador
     */
    private JButton btnBuscarIdentificador;

    /**
     * Botón buscar el más costoso
     */
    private JButton btnBuscarMasCostoso;

    /**
     * Botón buscar el más económico
     */
    private JButton btnBuscarMasEconomico;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor que inicializa los atributos de la clase
     * @param interfazFincaRaiz Referencia a la ventana principal
     */
    public PanelOpcionesBusqueda( InterfazFincaRaiz interfazFincaRaiz )
    {
        principal = interfazFincaRaiz;
        btnBuscarIdentificador = new JButton( );
        btnBuscarMasEconomico = new JButton( );
        btnBuscarMasCostoso = new JButton( );

        setBorder( BorderFactory.createTitledBorder( "Opciones de Búsqueda" ) );
        setLayout( new GridLayout( 3, 1 ) );

        btnBuscarIdentificador.setText( "Por Identificador" );
        btnBuscarIdentificador.setActionCommand( BUSCAR_POR_IDENTIFICADOR );
        btnBuscarIdentificador.addActionListener( this );
        add( btnBuscarIdentificador );

        btnBuscarMasEconomico.setText( "Más económico para arrendar" );
        btnBuscarMasEconomico.setActionCommand( BUSCAR_MAS_ECONOMICO );
        btnBuscarMasEconomico.addActionListener( this );
        add( btnBuscarMasEconomico );

        btnBuscarMasCostoso.setText( "Más costoso para la venta" );
        btnBuscarMasCostoso.setActionCommand( BUSCAR_MAS_COSTOSO );
        btnBuscarMasCostoso.addActionListener( this );
        add( btnBuscarMasCostoso );

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
        if( cmd.equals( BUSCAR_MAS_COSTOSO ) )
        {
            principal.buscarMasCostoso( );
        }
        else if( cmd.equals( BUSCAR_MAS_ECONOMICO ) )
        {
            principal.buscarMasEconomico( );
        }
        else if( cmd.equals( BUSCAR_POR_IDENTIFICADOR ) )
        {
            principal.buscarPorIdentificador( );
        }
    }

}
