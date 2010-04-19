package uniandes.cupi2.conejera.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Dialogo usado para definir los costos de sostenimiento de las parejas de conejos
 * 
 */
public class DialogoDefinirCostos extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando Aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Comando Cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // ------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al panel de informaci�n de la conejera
     */
    private PanelInformacionConejera panelInformacionConejera;

    // ------------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta valor de manutenci�n de una pareja adulta
     */
    private JLabel lblValorManutencionAdultos;

    /**
     * Etiqueta valor de manutenci�n de una pareja beb�
     */
    private JLabel lblValorManutencionBebes;

    /**
     * Campo de texto valor de manutenci�n de una pareja adulta
     */
    private JTextField txtValorManutencionAdultos;

    /**
     * Campo de texto valor de manutenci�n de una pareja beb�
     */
    private JTextField txtValorManutencionBebes;

    /**
     * Bot�n Aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n Cancelar
     */
    private JButton btnCancelar;

    // ---------------------------------------------------------
    // Constructor
    // --------------------------------------------------------

    /**
     * Construye un dialogo para ingresar los costos de manutenci�n <br>
     * de una pareja beb� y una pareja adulta
     * @param padre El panel con la informaci�n de la conejera
     */
    public DialogoDefinirCostos( PanelInformacionConejera padre )
    {

        panelInformacionConejera = padre;

        setLayout( new GridLayout( 3, 2, 3, 2 ) );
        setSize( 425, 120 );
        setTitle( "Definir costos mensuales de manutenci�n" );

        lblValorManutencionAdultos = new JLabel( " Valor adultos: " );
        add( lblValorManutencionAdultos );
        txtValorManutencionAdultos = new JTextField( );
        add( txtValorManutencionAdultos );

        lblValorManutencionBebes = new JLabel( " Valor beb�s: " );
        add( lblValorManutencionBebes );
        txtValorManutencionBebes = new JTextField( );
        add( txtValorManutencionBebes );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        add( btnAceptar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        add( btnCancelar );

        setModalityType( DEFAULT_MODALITY_TYPE );
        setLocationRelativeTo( null );
    }

    // ---------------------------------------------------------
    // M�todos
    // --------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            String valorAdultos = txtValorManutencionAdultos.getText( );
            String valorBebes = txtValorManutencionBebes.getText( );
            if( valorAdultos.equals( "" ) || valorAdultos == null || valorBebes.equals( "Valores de manutenci�n" ) || valorBebes == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar ambos costos", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }

            try
            {
                double valAdultos = Double.parseDouble( valorAdultos );
                double valBebes = Double.parseDouble( valorBebes );
                if( valAdultos <= 0 || valBebes <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar valores mayores a cero", "ERROR", JOptionPane.ERROR_MESSAGE );
                    return;
                }
                panelInformacionConejera.habilitarBotonDefinirCostos( false );
                JOptionPane.showMessageDialog( this, "Costos actualizados", "Comienza la simulaci�n", JOptionPane.INFORMATION_MESSAGE );
                panelInformacionConejera.establecerCostos( valAdultos, valBebes );
                dispose( );

            }
            catch( Exception ex )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar valores num�ricos", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }

        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            dispose( );
        }

    }

}
