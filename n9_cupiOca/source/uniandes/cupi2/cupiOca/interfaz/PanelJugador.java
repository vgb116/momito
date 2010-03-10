package uniandes.cupi2.cupiOca.interfaz;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Panel que muestra la posición de un jugador en el juego
 * 
 */
public class PanelJugador extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------
    // Atributos
    // ------------------------------------------------------------------

    /**
     * Jugador para el cual se van a mostrar los datos
     */
    private Jugador jugador;

    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // ------------------------------------------------------------------

    /**
     * Etiqueta con el nombre del jugador
     */
    private JLabel lblNick;

    /**
     * Etiqueta con la imagen del jugador
     */
    private JLabel lblImagen;

    // ------------------------------------------------------------------
    // Constructores
    // ------------------------------------------------------------------

    /**
     * Crea un nuevo panel con dos columnas, una con el nick del jugador y la otra con su imagen
     * @param elJugador El jugador que se va a representar en este panel
     */
    public PanelJugador( Jugador elJugador )
    {

        setLayout( new GridLayout( 1, 2 ) );
        jugador = elJugador;
        lblNick = new JLabel( jugador.darNick( ) );
        lblImagen = new JLabel( new ImageIcon( elJugador.darImagen( ) ) );
        add( lblNick );
        add( lblImagen );
    }

}
