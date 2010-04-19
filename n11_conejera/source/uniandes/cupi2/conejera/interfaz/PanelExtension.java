/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.8 2009/12/01 18:40:11 dav-vill Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_conejera
 * Autor: Juan David Villa - 03-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.conejera.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Constante costo de manutención de la conejera en un mes
     */
    private final static String COSTO_MES = "costo mes";

    /**
     * Constante costo de manutención acumulado de la conejera
     */
    private final static String COSTO_ACUMULADO = "costo acumulado";

    /**
     * Constante número de parejas
     */
    private final static String NUMERO_PAREJAS = "número parejas";

    /**
     * Constante descendientes bebés
     */
    private final static String DESCENDIENTES_BEBES = "número bebés";

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazConejera principal;

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
     * Botón para calcular el valor de la manutención mensual
     */
    private JButton btnManutencionMes;

    /**
     * Botón para calcular el valor acumulado de la manutención
     */
    private JButton btnManutencionAcumulado;

    /**
     * Botón para calcular el número de parejas de conejos en un mes dado
     */
    private JButton btnNumParejas;

    /**
     * Botón para calcular el número de parejas descendientes bebes de una pareja dada
     */
    private JButton btnDescendientesBebes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazConejera ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new BorderLayout( ) );

        JPanel panelNorte = new JPanel( );
        panelNorte.setLayout( new GridLayout( 1, 4 ) );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

        btnManutencionMes = new JButton( "Costo Mensual" );
        btnManutencionMes.setActionCommand( COSTO_MES );
        btnManutencionMes.addActionListener( this );
        panelNorte.add( btnManutencionMes );

        btnManutencionAcumulado = new JButton( "Costo Acumulado" );
        btnManutencionAcumulado.setActionCommand( COSTO_ACUMULADO );
        btnManutencionAcumulado.addActionListener( this );
        panelNorte.add( btnManutencionAcumulado );

        btnNumParejas = new JButton( "Número parejas" );
        btnNumParejas.setActionCommand( NUMERO_PAREJAS );
        btnNumParejas.addActionListener( this );
        panelNorte.add( btnNumParejas );

        btnDescendientesBebes = new JButton( "Descendientes bebés" );
        btnDescendientesBebes.setActionCommand( DESCENDIENTES_BEBES );
        btnDescendientesBebes.addActionListener( this );
        panelNorte.add( btnDescendientesBebes );

        JPanel panelSur = new JPanel( );
        panelSur.setLayout( new GridLayout( 2, 4 ) );
        panelSur.add( new JLabel( "" ) );
        panelSur.add( new JLabel( "" ) );
        panelSur.add( new JLabel( "" ) );
        panelSur.add( new JLabel( "" ) );
        panelSur.add( new JLabel( "" ) );
        panelSur.add( btnOpcion1 );
        panelSur.add( btnOpcion2 );
        panelSur.add( new JLabel( "" ) );

        add( panelNorte, BorderLayout.NORTH );
        add( panelSur, BorderLayout.SOUTH );

    }

    /**
     * Habilita o deshabilita los botones de la aplicación <br>
     * @param habilitar Indica si se debe habilitar o deshabilitar los botones del panel.
     */
    public void habilitarBotones( boolean habilitar )
    {
        btnManutencionMes.setEnabled( habilitar );
        btnManutencionAcumulado.setEnabled( habilitar );
        btnNumParejas.setEnabled( habilitar );
        btnDescendientesBebes.setEnabled( habilitar );
        btnOpcion1.setEnabled( habilitar );
        btnOpcion2.setEnabled( habilitar );
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

        else if( e.getActionCommand( ).equals( COSTO_MES ) )
        {
            int mesActual = principal.darMesActual( );
            String[] valoresPosibles = new String[mesActual + 1];
            for( int i = 0; i <= mesActual; i++ )
            {
                valoresPosibles[ i ] = "" + i;
            }

            String mesSeleccionado = ( String )JOptionPane.showInputDialog( null, "Escoja un mes", "Valor mensual de manutención", JOptionPane.INFORMATION_MESSAGE, null, valoresPosibles, valoresPosibles[ 0 ] );
            if( mesSeleccionado != null )
            {
                double valorMensual = principal.darValorInvertidoPorMes( Integer.parseInt( mesSeleccionado ) );
                String val = principal.formatearValorPesos( valorMensual );
                JOptionPane.showMessageDialog( null, "El valor de manutención para el mes " + mesSeleccionado + " fue de " + val, "Valor mensual de manutención", JOptionPane.INFORMATION_MESSAGE );
            }

        }

        else if( e.getActionCommand( ).equals( COSTO_ACUMULADO ) )
        {
            double valorAcumulado = principal.darValorInvertidoTotal( );
            String val = principal.formatearValorPesos( valorAcumulado );
            JOptionPane.showMessageDialog( null, "El valor de manutención acumulado es de " + val, "Valor acumulado de manutención", JOptionPane.INFORMATION_MESSAGE );
        }

        else if( e.getActionCommand( ).equals( NUMERO_PAREJAS ) )
        {
            int mesActual = principal.darMesActual( );
            String[] valoresPosibles = new String[mesActual + 1];
            for( int i = 0; i <= mesActual; i++ )
            {
                valoresPosibles[ i ] = "" + i;
            }
            String mesSeleccionado = ( String )JOptionPane.showInputDialog( null, "Escoja un mes", "Número de parejas en un mes", JOptionPane.INFORMATION_MESSAGE, null, valoresPosibles, valoresPosibles[ 0 ] );
            if( mesSeleccionado != null )
            {
                int mes = Integer.parseInt( mesSeleccionado );
                int numParejas = principal.darNumeroParejasEnMes( mes );
                JOptionPane.showMessageDialog( null, "El número de parejas en el mes " + mes + " son " + numParejas, "Cantidad de parejas", JOptionPane.INFORMATION_MESSAGE );
            }

        }
        else if( e.getActionCommand( ).equals( DESCENDIENTES_BEBES ) )
        {
            String idPareja = ( String )JOptionPane.showInputDialog( null, "Ingrese el id de la pareja", "Cantidad", JOptionPane.QUESTION_MESSAGE );
            if( idPareja != null )
            {
                try
                {
                    int id = Integer.parseInt( idPareja );
                    int descendientes = principal.descendientesBebes( id );
                    JOptionPane.showMessageDialog( null, "El número de parejas descendientes bebés es de " + descendientes, "Cantidad de parejas", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( Exception ex )
                {
                    JOptionPane.showMessageDialog( null, "Debe ingresar un número entero", "ERROR", JOptionPane.ERROR_MESSAGE );
                    return;

                }
            }
        }
    }

}
