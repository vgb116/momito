package uniandes.cupi2.cupiOca.interfaz;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Panel que representa una casilla del tablero de juego
 * 
 */
public class PanelCasilla extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * La imagen que se muestra cuando hay más de cuatro jugadores
     */
    private final static String MAS_DE_CUATRO_JUGADORES = "./data/imagenes/4plus.png";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El tipo de la casilla que representa este panel
     */
    String tipoCasilla;

    /**
     * El número de la casilla en el tablero de juego
     */
    int numCasilla;

    /**
     * El número de jugadores que están ubicados dentro del tablero de juego en esta casilla
     */
    int numJugadores;

    /**
     * Arreglo con los jugadores que hay en la casilla
     */
    ArrayList jugadores;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta con el número de esta casilla dentro del tablero de juego
     */
    JLabel etiquetaNumCasilla;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo panel casilla, sin jugadores, dado el tipo de la casilla que representa y la posición que ocupa dentro del tablero de juego. Utiliza un GridBagLayout.
     * @param elTipoCasilla El tipo de la casilla que representa
     * @param elNumCasilla El número de la casilla que ocupa en el tablero de juego
     */
    public PanelCasilla( String elTipoCasilla, int elNumCasilla )
    {
        jugadores = new ArrayList( );
        tipoCasilla = elTipoCasilla;
        numCasilla = elNumCasilla;
        numJugadores = 0;
        setBorder( BorderFactory.createLineBorder( Color.ORANGE ) );
        setLayout( new GridBagLayout( ) );
        this.setPreferredSize( new Dimension( 71, 57 ) );
        this.setComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT );
        etiquetaNumCasilla = new JLabel( "" + numCasilla, SwingConstants.NORTH_EAST );
        etiquetaNumCasilla.setForeground( Color.BLACK );
        etiquetaNumCasilla.setVerticalTextPosition( JLabel.TOP );
        etiquetaNumCasilla.setFont( new Font( "Tahoma", Font.PLAIN, 15 ) );
        GridBagConstraints gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 0, 0 );
        add( etiquetaNumCasilla, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Se encarga de pintar en este panel la imagen que indica el tipo de esta casilla y a los jugadores que estén actualmente. En caso que hayan cuatro o mas jugadores no se
     * pinta cada uno, se pinta la imagen en la ruta MAS_DE_CUATRO_JUGADORES que indica que hay más de tres jugadores<br>
     * @param g Es la superficie del panel usada para dibujar los elementos
     */
    public void paintComponent( Graphics g )
    {
        ImageIcon icon = new ImageIcon( tipoCasilla );
        g.drawImage( icon.getImage( ), 0, 0, null );
        // Pintar los jugadores
        if( jugadores.size( ) > 0 )
        {
            if( jugadores.size( ) < 4 )
            {
                for( int i = 0; i < jugadores.size( ); i++ )
                {
                    Jugador jugador = ( Jugador )jugadores.get( i );
                    String rutaImagen = jugador.darImagen( );
                    ImageIcon imagenJugador = new ImageIcon( rutaImagen );

                    int cordenadaX = 0;
                    int cordenadaY = 0;

                    if( i == 1 )
                    {
                        cordenadaY = 28;

                    }
                    else if( i == 2 )
                    {
                        cordenadaX = 35;
                        cordenadaY = 28;
                    }
                    g.drawImage( imagenJugador.getImage( ), cordenadaX, cordenadaY, null );
                }
            }
            else
            {
                ImageIcon imagenJugadores = new ImageIcon( MAS_DE_CUATRO_JUGADORES );
                g.drawImage( imagenJugadores.getImage( ), 0, 0, null );
            }
        }

        setOpaque( false );

    }

    /**
     * Agrega un nuevo jugador a la casilla y actualiza el número de jugadores actuales
     * @param jugador El nuevo jugador en la casilla
     */
    public void agregarJugador( Jugador jugador )
    {
        numJugadores++;
        jugadores.add( jugador );

    }

    /**
     * Este método quita de esta casilla al jugador pasado como parámetro y disminuye numJugadores
     * @param jugador El jugador que se va a quitar de la casilla
     * 
     */
    public void quitarJugador( Jugador jugador )
    {
        for( int i = 0; i < jugadores.size( ); i++ )
        {
            Jugador temp = ( Jugador )jugadores.get( i );
            if( temp.equals( jugador ) )
            {
                jugadores.remove( i );
            }
        }
        numJugadores--;
    }

    /**
     * Retorna el tipo de esta casilla
     * @return tipoCasilla El tipo de esta casilla
     */
    public String darTipoCasilla( )
    {
        return tipoCasilla;
    }
}
