package uniandes.cupi2.conejera.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel que proporciona la información de la conejera
 * 
 */
public class PanelInformacionConejera extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante aumentar mes
     */
    private final static String AUMENTAR_MES = "aumentar mes";

    /**
     * Constante para definir costos de la conejera
     */
    private final static String DEFINIR_COSTOS = "definir costos";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta costo sostenimiento adultos
     */
    private JLabel labelSostenimientoAdultos;

    /**
     * Etiqueta con el costo sostenimiento adultos
     */
    private JLabel labelElValorSostenimientoAdultos;

    /**
     * Etiqueta costo sostenimiento bebés
     */
    private JLabel labelSostenimientoBebes;

    /**
     * Etiqueta con el costo sostenimiento bebés
     */
    private JLabel labelElValorSostenimientoBebes;

    /**
     * Etiqueta mes actual
     */
    private JLabel labelMesActual;

    /**
     * Etiqueta con el mes actual
     */
    private JLabel labelElMesActual;

    /**
     * Botón para simular el paso de un mes
     */
    private JButton btnAumentarMes;

    /**
     * Botón para definir los costos de manutención
     */
    private JButton btnDefinirCostos;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicación
     */
    private InterfazConejera principal;

    /**
     * Dialogo usado para definir los costos de sostenimiento de las parejas de conejos
     */
    private DialogoDefinirCostos dialogoCostos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel con la información de la conejera
     * @param ventana La ventana principal de la aplicación
     */
    public PanelInformacionConejera( InterfazConejera ventana )
    {

        principal = ventana;
        dialogoCostos = new DialogoDefinirCostos( this );
        setLayout( new GridLayout( 9, 1 ) );
        setBorder( new TitledBorder( "Datos Conejera" ) );

        labelSostenimientoAdultos = new JLabel( "Valor adultos" );
        labelSostenimientoAdultos.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
        labelSostenimientoBebes = new JLabel( "Valor bebés" );
        labelSostenimientoBebes.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );
        labelElValorSostenimientoAdultos = new JLabel( "Indefinido" );
        labelElValorSostenimientoAdultos.setForeground( Color.GRAY );
        labelElValorSostenimientoAdultos.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelElValorSostenimientoBebes = new JLabel( "Indefinido" );
        labelElValorSostenimientoBebes.setForeground( Color.GRAY );
        labelElValorSostenimientoBebes.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelElMesActual = new JLabel( "" + principal.darMesActual( ) );
        labelElMesActual.setForeground( Color.GRAY );
        labelElMesActual.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelMesActual = new JLabel( "Mes actual" );
        labelMesActual.setFont( new Font( "Tahoma", Font.BOLD, 15 ) );

        btnAumentarMes = new JButton( "Aumentar mes" );
        btnAumentarMes.setActionCommand( AUMENTAR_MES );
        btnAumentarMes.addActionListener( this );
        btnAumentarMes.setEnabled( false );
        btnDefinirCostos = new JButton( "Definir costos" );
        btnDefinirCostos.setActionCommand( DEFINIR_COSTOS );
        btnDefinirCostos.addActionListener( this );
        add( labelSostenimientoAdultos );
        add( labelElValorSostenimientoAdultos );
        add( labelSostenimientoBebes );
        add( labelElValorSostenimientoBebes );
        add( labelMesActual );
        add( labelElMesActual );
        add( btnAumentarMes );
        add( btnDefinirCostos );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Habilita o deshabilita el botón de definir costos según el parámetro.
     * @param habilitar Indica si se debe deshabilitar el botón o no.
     */
    public void habilitarBotonDefinirCostos( boolean habilitar )
    {
        btnDefinirCostos.setEnabled( habilitar );
    }

    /**
     * Refresca el panel con los valores de manutención de una pareja adulta y una bebé
     * @param valorAdultos El valor del mantenimiento de una pareja adulta
     * @param valorBebes El valor del mantenimiento de una pareja bebé
     */
    public void establecerCostos( double valorAdultos, double valorBebes )
    {
        String valParejaAdulta = principal.formatearValorPesos( valorAdultos );
        String valParejaBebe = principal.formatearValorPesos( valorBebes );
        labelElValorSostenimientoAdultos.setText( valParejaAdulta );
        labelElValorSostenimientoBebes.setText( valParejaBebe );
        principal.establecerCostosMantenimiento( valorAdultos, valorBebes );
        btnAumentarMes.setEnabled( true );

    }

    /**
     * Este método se ejecuta cuando se hace click en el botón de definir atributos
     * @param e Acción que generó el evento
     */
    public void actionPerformed( ActionEvent e )
    {

        if( e.getActionCommand( ).equals( AUMENTAR_MES ) )
        {
            principal.simularPasoMes( );
            labelElMesActual.setText( "" + principal.darMesActual( ) );
        }

        else if( e.getActionCommand( ).equals( DEFINIR_COSTOS ) )
        {
            dialogoCostos.setVisible( true );
        }

    }
}
