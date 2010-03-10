package uniandes.cupi2.cupiOca.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Clase que representa el tablero de juego
 * 
 */
public class PanelTablero extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Indica el número de casillas que hay en cada lado del tablero
     */
    private final static int TAM_TABLERO = 7;

    /**
     * Ruta de la imagen de una casilla que tiene una CARCEL
     */
    private final static String RUTA_CASILLA_CARCEL = "./data/imagenes/carcel_pic.png";

    /**
     * Ruta de la imagen de una casilla que tiene un PUENTE
     */
    private final static String RUTA_CASILLA_PUENTE = "./data/imagenes/puente_pic.png";

    /**
     * Ruta de la imagen de una casilla que tiene una POSADA
     */
    private final static String RUTA_CASILLA_POSADA = "./data/imagenes/posada_pic.png";

    /**
     * Ruta de la imagen de una casilla que tiene una CALABERA
     */
    private final static String RUTA_CASILLA_CALAVERA = "./data/imagenes/calavera_pic.png";

    /**
     * Ruta de la imagen de una casilla que tiene un TOBOGAN
     */
    private final static String RUTA_CASILLA_RODADERO = "./data/imagenes/tobogan_pic.png";

    /**
     * Ruta de la imagen de una casilla vacía
     */
    private final static String RUTA_CASILLA_VACIA = "./data/imagenes/casillaVacia_pic.png";

    /**
     * Ruta de la imagen de una casilla oca
     */
    private final static String RUTA_CASILLA_OCA = "./data/imagenes/oca_pic.png";

    /**
     * Ruta de la imagen de una casilla de inicio
     */
    private final static String RUTA_CASILLA_INICIO = "./data/imagenes/go_pic.png";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Matriz con las casillas del tablero de juego
     */
    private PanelCasilla[][] tableroJuego;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo tablero de juego de 7 x7 casillas
     */
    public PanelTablero( )
    {

        setBorder( new TitledBorder( "Tablero de Juego" ) );
        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 497, 399 ) );
        tableroJuego = new PanelCasilla[TAM_TABLERO][TAM_TABLERO];
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Carga todas las casillas del tablero de juego
     * @param primeraCasilla La primera casilla del tablero de juego
     */
    public void actualizarTablero( Casilla primeraCasilla )
    {
        int contador = 1;
        Casilla actual = primeraCasilla;
        for( int i = TAM_TABLERO - 1; i >= 0; i-- )
        {
            if( i % 2 == 0 )
            {
                for( int j = 0; j < TAM_TABLERO; j++ )
                {
                    actualizar( i, j, actual, contador );
                    actual = actual.darSiguiente( );
                    contador++;
                }
            }
            else
            {
                for( int j = TAM_TABLERO - 1; j >= 0; j-- )
                {
                    actualizar( i, j, actual, contador );
                    actual = actual.darSiguiente( );
                    contador++;
                }
            }
        }

    }

    /**
     * Actualiza la interfaz gráfica de la casilla en la fila i y columna j
     * @param i La fila de la casilla
     * @param j La columna de la casilla
     * @param actual La casilla que se va a dibujar
     * @param contador El número de la casilla
     */
    private void actualizar( int i, int j, Casilla actual, int contador )
    {

        String tipoCasilla = actual.darTipo( );
        GridBagConstraints gbc = new GridBagConstraints( j, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 0, 0, 0, 0 ), 0, 0 );
        if( tipoCasilla.equals( Casilla.TIPO_CALAVERA ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_CALAVERA, contador );

        }
        else if( tipoCasilla.equals( Casilla.TIPO_CARCEL ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_CARCEL, contador );

        }
        else if( tipoCasilla.equals( Casilla.TIPO_VACIA ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_VACIA, contador );

        }
        else if( tipoCasilla.equals( Casilla.TIPO_POSADA ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_POSADA, contador );

        }

        else if( tipoCasilla.equals( Casilla.TIPO_PUENTE ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_PUENTE, contador );

        }
        else if( tipoCasilla.equals( Casilla.TIPO_RODADERO ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_RODADERO, contador );

        }

        else if( tipoCasilla.equals( Casilla.TIPO_OCA ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_OCA, contador );

        }

        else if( tipoCasilla.equals( Casilla.TIPO_INICIO ) )
        {
            tableroJuego[ i ][ j ] = new PanelCasilla( RUTA_CASILLA_INICIO, contador );

        }
        add( tableroJuego[ i ][ j ], gbc );
    }

    /**
     * Agrega un nuevo jugador a la casilla en la fila i y en la columna j
     * @param i La fila de la casilla
     * @param j La columna de la casilla
     * @param nuevoJugador El nuevo jugador a agregar a la casilla
     */
    public void actualizarCasilla( int i, int j, Jugador nuevoJugador )
    {
        PanelCasilla casilla = tableroJuego[ i ][ j ];
        casilla.agregarJugador( nuevoJugador );

    }

    /**
     * Retorna una casilla dado su número
     * @param consecutivoCasilla El número de la casilla buscada
     * @return casilla La casilla buscada
     */
    public PanelCasilla buscarCasilla( int consecutivoCasilla )
    {
        boolean encontro = false;
        PanelCasilla casilla = null;
        for( int i = 0; i < TAM_TABLERO && !encontro; i++ )
        {
            for( int j = 0; j < TAM_TABLERO && !encontro; j++ )
            {
                PanelCasilla casillaTemp = tableroJuego[ i ][ j ];
                if( casillaTemp.numCasilla == consecutivoCasilla )
                {
                    encontro = true;
                    casilla = casillaTemp;
                }
            }
        }

        return casilla;
    }

    /**
     * Si casilla inicial es diferente de casillaFinal, pinta al jugador recibido como parámetro en la casilla final y lo elimina de la casilla inicial.
     * @param jugador El jugador que realizó la jugada
     * @param casillaInicial La casilla donde se encontraba el jugador antes de jugar
     * @param casillaFinal La casilla en la que cayó el jugador luego de la jugada
     */
    public void actualizarJugada( Jugador jugador, int casillaInicial, int casillaFinal )
    {
        // Si no se movió el jugador no hay que pintar las casillas
        if( casillaInicial != casillaFinal )
        {
            PanelCasilla casillaInicio = buscarCasilla( casillaInicial );
            casillaInicio.quitarJugador( jugador );
            casillaInicio.repaint( );

            PanelCasilla casillaFin = buscarCasilla( casillaFinal );
            casillaFin.agregarJugador( jugador );
            casillaFin.repaint( );
        }
    }

}
