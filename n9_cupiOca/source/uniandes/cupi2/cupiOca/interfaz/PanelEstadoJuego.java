package uniandes.cupi2.cupiOca.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Este panel muestra el ranking de los jugadores en el juego, comenzando por el jugador que est� en la casilla m�s adelante hasta el que est� en la �ltima posici�n
 * 
 */
public class PanelEstadoJuego extends JPanel
{
    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // ------------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Etiqueta no hay jugadores
     */
    private JLabel lblNoHayJugadores;

    // ------------------------------------------------------------------
    // Constructores
    // ------------------------------------------------------------------

    /**
     * Crea un nuevo panel con el ranking de los jugadores ordenados del mejor al �ltimo
     */
    public PanelEstadoJuego( )
    {

        setPreferredSize( new Dimension( 290, 280 ) );
        setBorder( new TitledBorder( "Ranking" ) );
        lblNoHayJugadores = new JLabel( "No ha empezado el juego" );
        lblNoHayJugadores.setForeground( Color.RED );
        lblNoHayJugadores.setFont( new Font( "Tahoma", Font.ITALIC, 18 ) );
        add( lblNoHayJugadores );

    }
    // ------------------------------------------------------------------
    // M�todos
    // ------------------------------------------------------------------

    /**
     * Actualiza los rankings de los jugadores
     * @param primerJugador Lista c�clica sencillamente encadenada cuya ra�z es el jugador en el ranking n�mero 1
     * @param numJugadores El n�mero de jugadores jugando el juego
     */
    public void actualizar( Jugador primerJugador, int numJugadores )
    {
        this.removeAll( );
        int ranking = 1;
        setLayout( new GridLayout( numJugadores, 1 ) );
        Jugador actual = primerJugador;
        PanelJugador temp = new PanelJugador( primerJugador );
        temp.setBorder( new TitledBorder( "Puesto " + ranking ) );
        add( temp );

        actual = actual.darSiguiente( );
        while( actual != null )
        {
            ranking++;
            temp = new PanelJugador( actual );
            temp.setBorder( new TitledBorder( "Puesto " + ranking ) );
            add( temp );
            actual = actual.darSiguiente( );
        }
        validate( );
        repaint( );
    }
}
