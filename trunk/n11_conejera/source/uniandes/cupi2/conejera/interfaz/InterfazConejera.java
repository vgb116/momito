/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazConejera.java,v 1.7 2009/12/01 18:40:11 dav-vill Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_conejera
 * Autor: Juan David Villa - 03-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.conejera.interfaz;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import uniandes.cupi2.conejera.mundo.Conejera;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazConejera extends JFrame
{

    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Conejera conejera;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los datos de la conejera
     */
    private PanelInformacionConejera panelDatos;

    /**
     * Panel con la representaci�n gr�fica de la conejera
     */
    private PanelGraficoConejera panelGrafico;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la interfaz gr�fica <br>
     * <b>post: </b> Se inicializ� la referencia a la conejera y se construyo la interfaz
     */
    public InterfazConejera( )
    {
        // Crea la clase principal
        conejera = new Conejera( );

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setTitle( "La Conejera" );
        setSize( 800, 600 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelExtension = new PanelExtension( this );
        panelExtension.habilitarBotones( false );
        add( panelExtension, BorderLayout.SOUTH );

        panelDatos = new PanelInformacionConejera( this );
        add( panelDatos, BorderLayout.WEST );

        panelGrafico = new PanelGraficoConejera( conejera );
        JScrollPane scroll = new JScrollPane( panelGrafico );
        scroll.setPreferredSize( getPreferredSize( ) );
        getContentPane( ).add( scroll, BorderLayout.CENTER );
        add( scroll, BorderLayout.CENTER );

        // Centrar la ventana
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor del mes actual
     * @return mesActual
     */
    public int darMesActual( )
    {

        return conejera.darMesActual( );
    }

    /**
     * Aumenta el mes actual en un mes y delega el impacto del evento al mundo
     */
    public void simularPasoMes( )
    {
        conejera.simularPasoMes( );
        panelGrafico.actualizarImagen( );
    }

    /**
     * Retorna el n�mero de parejas en un mes dado
     * @param mes El mes para calcular el total de parejas
     * @return El total de parejas de conejos en un mes
     */
    public int darNumeroParejasEnMes( int mes )
    {
        return conejera.darNumeroParejasEnMes( mes );
    }

    /**
     * Calcula el valor de la manutenci�n de la conejera en un mes dado
     * @param mes El mes para el cual se quiere calcular el valor invertido
     * @return El valor invertido en el mes pasado como par�metro
     */
    public double darValorInvertidoPorMes( int mes )
    {
        return conejera.darValorInvertidoPorMes( mes );
    }

    /**
     * Calcula el valor total invertido en la conejera hasta la fecha
     * @return El valor total invertido
     */
    public double darValorInvertidoTotal( )
    {
        return conejera.darValorInvertidoTotal( );
    }

    /**
     * Calcula el n�mero de descendientes beb�s de una pareja de conejos
     * @param id El id de la pareja de conejos
     * @return El n�mero de parejas beb�s descendientes
     */
    public int descendientesBebes( int id )
    {
        int cantidad = 0;
        try
        {
            cantidad = conejera.descendientesBebes( id );
        }
        catch( Exception ex )
        {
            JOptionPane.showMessageDialog( this, ex.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );

        }
        return cantidad;
    }

    /**
     * Formatea un valor num�rico para presentar en la interfaz con el formato en pesos.
     * @param valor Valor num�rico a ser formateado
     * @return Cadena con el valor formateado con comas y signos.
     */
    public String formatearValorPesos( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }

    /**
     * Cambia el valor de mantenimiento de una pareja adulta por el recibido como par�metro, <br>
     * Cambia el valor de mantenimiento de una pareja beb� por el recibido como par�metro
     * @param valParejaAdulta El nuevo valor del mantenimiento de una pareja adulta
     * @param valParejaBebe El nuevo valor del mantenimiento de una pareja beb�
     */
    public void establecerCostosMantenimiento( double valParejaAdulta, double valParejaBebe )
    {
        conejera.establecerCostosMantenimiento( valParejaAdulta, valParejaBebe );
        panelExtension.habilitarBotones( true );
    }
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = conejera.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = conejera.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazConejera interfaz = new InterfazConejera( );
        interfaz.setVisible( true );
    }

}