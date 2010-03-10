package uniandes.cupi2.cupiOca.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * Este panel contiene los controles necesarios para comenzar el juego, agregar un jugador y jugar
 * 
 */
public class PanelControl extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante para agregar un jugador
     */
    public final static String AGREGAR_JUGADOR = "AGREGAR";

    /**
     * Constante para jugar
     */
    public final static String JUGAR = "JUGAR";

    /**
     * Comando empezar el juego
     */
    private static final String COMENZAR_JUEGO = "COMENZAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiOca principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para agregar un jugador al juego
     */
    private JButton btnAgregarJugador;

    /**
     * Botón para realizar una jugada
     */
    private JButton btnJugar;

    /**
     * Botón para comenzar el juego
     */
    private JButton btnComenzarJuego;

    /**
     * Etiqueta jugador en turno
     */
    private JLabel lblJugadorEnTurno;

    /**
     * Etiqueta con el nombre del jugador en turno
     */
    private JLabel lblNombreJugador;

    /**
     * Etiqueta con el número de posiciones que va a avanzar el jugador
     */
    private JLabel lblPosiciones;

    /**
     * Etiqueta posiciones El número de posiciones avanzadas
     */
    private JLabel lblLasPosiciones;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo panel de control usando GridBagLayout. Inicialmente el botón para jugar está deshabilitado hasta que se agregue al menos un jugador.
     * @param ventana La ventana principal de la aplicación
     */
    public PanelControl( InterfazCupiOca ventana )
    {
        setLayout( new GridBagLayout( ) );
        setBorder( new TitledBorder( "Control" ) );
        setPreferredSize( new Dimension( 290, 120 ) );
        principal = ventana;

        btnAgregarJugador = new JButton( AGREGAR_JUGADOR );
        btnAgregarJugador.setActionCommand( AGREGAR_JUGADOR );
        btnAgregarJugador.addActionListener( this );

        btnJugar = new JButton( JUGAR );
        btnJugar.setActionCommand( JUGAR );
        btnJugar.addActionListener( this );
        btnJugar.setEnabled( false );

        lblJugadorEnTurno = new JLabel( "En turno:" );
        lblNombreJugador = new JLabel( );
        lblPosiciones = new JLabel( "Casillas Avanzadas:" );
        lblLasPosiciones = new JLabel( );

        btnComenzarJuego = new JButton( COMENZAR_JUEGO );
        btnComenzarJuego.setActionCommand( COMENZAR_JUEGO );
        btnComenzarJuego.addActionListener( this );
        add( btnComenzarJuego );

        // Este GridBagContraints se reutiliza para agregar todos los componentes
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.insets = new Insets( 2, 2, 2, 2 );
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add( lblJugadorEnTurno, gbc );
        gbc.gridx = 1;
        gbc.gridy = 0;
        add( lblNombreJugador, gbc );

        gbc.gridx = 0;
        gbc.gridy = 1;
        add( lblPosiciones, gbc );
        gbc.gridx = 1;
        gbc.gridy = 1;
        add( lblLasPosiciones, gbc );
        gbc.gridx = 0;
        gbc.gridy = 2;
        add( btnAgregarJugador, gbc );
        gbc.gridx = 1;
        gbc.gridy = 2;
        add( btnJugar, gbc );
        gbc.gridx = 0;
        gbc.gridy = 3;
        add( btnComenzarJuego, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método actualiza la etiqueta lblPosiciones con el número de posiciones avanzadas
     * @param casillas El número de casillas avanzadas por el jugador
     */
    public void actualizarCasillas( String casillas )
    {
        lblLasPosiciones.setText( casillas );

    }

    /**
     * Este método actualiza la etiqueta lblNombreJugadorEnTurno con el nombre del próximo jugador en turno
     * @param jugadorEnTurno El próximo jugador en turno
     */
    public void actualizarJugadorEnTurno( String jugadorEnTurno )
    {
        lblNombreJugador.setText( jugadorEnTurno );
    }

    /**
     * Deshabilita los botones luego de que un jugador gane el juego
     */
    public void desabilitarBotones( )
    {
        btnJugar.setEnabled( false );
        btnAgregarJugador.setEnabled( false );
    }

    /**
     * Maneja los eventos del panel
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( AGREGAR_JUGADOR ) )
        {

            principal.crearDialogoAgregarJugador( );

        }

        else if( e.getActionCommand( ).equals( JUGAR ) )
        {

            principal.jugar( );

        }
        else if( e.getActionCommand( ).equals( COMENZAR_JUEGO ) )
        {
            if( principal.darNumJugadores( ) > 0 )
            {
                btnJugar.setEnabled( true );
                btnAgregarJugador.setEnabled( false );
                btnComenzarJuego.setEnabled( false );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Agregue todos los jugadores antes de comenzar el juego", "No hay jugadores", JOptionPane.ERROR_MESSAGE );
            }

        }

    }

}
