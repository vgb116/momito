/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCupiOca.java,v 1.27 2009/11/23 19:13:04 dav-vill Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiOca
 * Autor: Juan David Villa - 14-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiOca.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.CupiOca;
import uniandes.cupi2.cupiOca.mundo.CupiOcaException;
import uniandes.cupi2.cupiOca.mundo.InfoJugada;
import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCupiOca extends JFrame implements WindowListener
{

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiOca cupiOca;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones de eliminar un jugador, mostrar el número de casillas de un tipo y las extensiones del juego
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * El panel que muestra el tablero de juego
     */
    private PanelTablero panelTablero;

    /**
     * Panel con el ranking de los jugadores
     */
    private PanelEstadoJuego panelEstadoJuego;

    /**
     * Panel con los controles para comenzar un nuevo juego, agregar un jugador y jugar
     */
    private PanelControl panelControl;

    /**
     * Dialogo para seleccionar un archivo de configuración
     */
    private DialogoSeleccionarArchivo dialogoSeleccionarArchivo;

    /**
     * Dialogo para crear un nuevo jugador
     */
    private DialogoIngresarJugador dialogoIngresarJugador;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción Ventana principal de la aplicación<br>
     * <b>post: </b> Se crea un nuevo tablero de juego sin ningún jugador y con la opción de jugar deshabilitada hasta que se agregue por lo menos un jugador
     */
    public InterfazCupiOca( )
    {
        // Crea los paneles de la interfaz
        panelTablero = new PanelTablero( );
        panelImagen = new PanelImagen( );
        panelExtension = new PanelExtension( this );
        panelEstadoJuego = new PanelEstadoJuego( );
        panelControl = new PanelControl( this );

        // Crear el dialogo para seleccionar un archivo
        dialogoSeleccionarArchivo = new DialogoSeleccionarArchivo( this );
        dialogoSeleccionarArchivo.addWindowListener( this );
        dialogoSeleccionarArchivo.setVisible( true );

        setTitle( "El juego de la CupiOca" );
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 800, 600 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Creación del panel contenedor (GridBagLayout) que contiene a panelTablero, panelEstadoJuego y panelControl
        JPanel panelCentral = new JPanel( );
        panelCentral.setPreferredSize( new Dimension( 800, 400 ) );
        panelCentral.setLayout( new GridBagLayout( ) );

        GridBagConstraints gridBagConstraintsPanelTablero = new GridBagConstraints( );
        GridBagConstraints gridBagConstraintsPanelRanking = new GridBagConstraints( );
        GridBagConstraints gridBagConstraintsPanelControl = new GridBagConstraints( );

        gridBagConstraintsPanelTablero.fill = GridBagConstraints.NONE;
        gridBagConstraintsPanelTablero.ipadx = 0;
        gridBagConstraintsPanelTablero.ipady = 0;
        gridBagConstraintsPanelTablero.anchor = GridBagConstraints.WEST;
        gridBagConstraintsPanelTablero.gridx = 0;
        gridBagConstraintsPanelTablero.gridy = 0;
        gridBagConstraintsPanelTablero.gridheight = 2;
        panelCentral.add( panelTablero, gridBagConstraintsPanelTablero );

        gridBagConstraintsPanelRanking.fill = GridBagConstraints.NONE;
        gridBagConstraintsPanelRanking.ipadx = 0;
        gridBagConstraintsPanelRanking.ipady = 0;
        gridBagConstraintsPanelRanking.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraintsPanelRanking.gridx = 1;
        gridBagConstraintsPanelRanking.gridy = 0;
        panelCentral.add( panelEstadoJuego, gridBagConstraintsPanelRanking );

        gridBagConstraintsPanelControl.fill = GridBagConstraints.NONE;
        gridBagConstraintsPanelControl.ipadx = 0;
        gridBagConstraintsPanelControl.ipady = 0;
        gridBagConstraintsPanelControl.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraintsPanelControl.gridx = 1;
        gridBagConstraintsPanelControl.gridy = 1;
        panelCentral.add( panelControl, gridBagConstraintsPanelControl );

        add( panelImagen, BorderLayout.NORTH );
        add( panelCentral, BorderLayout.CENTER );
        add( panelExtension, BorderLayout.SOUTH );
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Carga de un archivo de texto plano el tablero de juego
     * @param ruta La ruta donde se encuentran los datos
     */
    public void cargarDatos( String ruta )
    {
        try
        {
            cupiOca = new CupiOca( ruta );
            Casilla primeraCasilla = cupiOca.darPrimeraCasilla( );
            panelTablero.actualizarTablero( primeraCasilla );
        }
        catch( CupiOcaException e )
        {
            System.out.println("cae en la exception");
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Crea un nuevo jugador con el nombre y la imagen dados como parámetros. En el orden de creación se asigna el turno para jugar
     * @param nickJugador El nick del nuevo jugador
     * @param rutaImagen La imagen del jugador
     * @return true en caso que agregue al jugador, false en caso que ya exista el jugador
     */
    public boolean agregarJugador( String nickJugador, String rutaImagen )
    {
        boolean existeJugador = cupiOca.existeJugador( nickJugador );
        if( !existeJugador )
        {
            boolean primerJugador = false;
            Jugador jugadorEnTurno = cupiOca.darJugadorEnTurno( );
            if( ! ( jugadorEnTurno != null ) )
            {
                primerJugador = true;
            }

            cupiOca.agregarJugador( nickJugador, rutaImagen );
            if( primerJugador )
            {
                panelControl.actualizarJugadorEnTurno( nickJugador );
            }

            Jugador jugadorNuevo = cupiOca.darJugador( nickJugador );
            panelTablero.actualizarCasilla( 6, 0, jugadorNuevo );
            panelTablero.repaint( );
        }

        return !existeJugador;

    }

    /**
     * Crea un nuevo dialogo para agregar un jugador
     */
    public void crearDialogoAgregarJugador( )
    {
        dialogoIngresarJugador = new DialogoIngresarJugador( this );
        dialogoIngresarJugador.setLocationRelativeTo( this );
    }

    /**
     * Realiza una jugada para el jugador en turno. Con la información de la jugada se actualiza el número de casillas movidas en el panelControl, se dibuja al jugador en el
     * panel de la casilla en la cual cayó y se borra de la casilla en la cual estaba. Si la casilla en la cual cayó no es una casilla vacía se le informa al usuario el tipo
     * de casilla en la cual cayo y el premio o el castigo asignado. Si llegó a la casilla final se le informa al jugador que ganó y se bloquea el botón jugar del panel
     * panelControl. Al final actualiza el panel con el ranking de los jugadores.
     */
    public void jugar( )
    {
        Jugador jugador = cupiOca.darJugadorEnTurno( );
        InfoJugada infoJugada = cupiOca.jugar( );

        panelControl.actualizarCasillas( "" + infoJugada.darPosicionesMovidas( ) );
        panelTablero.actualizarJugada( jugador, infoJugada.darCasillaInicial( ).darPosicionCasilla( ), infoJugada.darCasillaFinal( ).darPosicionCasilla( ) );
        String respuesta = infoJugada.darMensaje( );
        if( respuesta != "" )
            JOptionPane.showMessageDialog( null, respuesta, "Información", JOptionPane.INFORMATION_MESSAGE );

        if( !infoJugada.esGanador( ) )
        {
            panelControl.actualizarJugadorEnTurno( cupiOca.darJugadorEnTurno( ).darNick( ) );

        }
        else
        {
            JOptionPane.showMessageDialog( null, "Ganaste!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
            panelControl.desabilitarBotones( );
        }
        panelEstadoJuego.actualizar( cupiOca.darListaRanking( ), cupiOca.darNumJugadores( ) );

    }

    /**
     * Este método retorna el número de casillas cuyo tipo es igual al dado como parámetro
     * @param tipoCasilla El tipo de casilla buscada
     * @return numCasillas El número de casillas cuyo tipo es igual al dado como parámetro
     */
    public int darNumCasillas( String tipoCasilla )
    {
        int numCasillas = cupiOca.darNumCasillas( tipoCasilla );
        return numCasillas;
    }

    /**
     * Este método retorna una lista (cíclica y sencillamente encadenada) de jugadores ordenados según su turno para jugar
     * @return La lista de jugadores
     */
    public Jugador darJugadorEnTurno( )
    {
        return cupiOca.darJugadorEnTurno( );
    }

    /**
     * Este método retorna el número de jugadores inscritos en el juego
     * @return El número de jugadores en el juego
     */
    public int darNumJugadores( )
    {
        return cupiOca.darNumJugadores( );
    }

    /**
     * Este método elimina al jugador cuyo nick es pasado como parámetro solo si no es el jugador en turno. También borra del tablero de juego y del panelEstado al jugador
     * @param nickAEliminar EL nick del jugador que se quiere eliminar
     * @throws Exception Lanza una excepción en caso que el jugador a eliminar sea el jugador en turno
     */
    public void eliminarJugador( String nickAEliminar ) throws Exception
    {
        Jugador jugador = cupiOca.darJugador( nickAEliminar );
        cupiOca.eliminarJugador( nickAEliminar );
        quitarJugadorTablero( jugador.darCasillaActual( ).darPosicionCasilla( ), jugador );
        panelEstadoJuego.actualizar( cupiOca.darListaRanking( ), cupiOca.darNumJugadores( ) );
    }

    /**
     * Borra del tablero de juego en la casilla con posición posCasilla al jugador pasado como parámetro
     * @param posCasilla La posición de la casilla en el tablero de juego, de la cual se va a borrar el jugador
     * @param jugador El jugador que se va a borrar
     */
    public void quitarJugadorTablero( int posCasilla, Jugador jugador )
    {
        PanelCasilla panelCasilla = panelTablero.buscarCasilla( posCasilla );
        panelCasilla.quitarJugador( jugador );
        panelCasilla.repaint( );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {

        String resultado = cupiOca.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiOca.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupiOca interfaz = new InterfazCupiOca( );
        interfaz.setVisible( true );
    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowActivated( WindowEvent arg0 )
    {

    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowClosed( WindowEvent arg0 )
    {

    }

    /**
     * Método declarado por la interfaz WindowListener usado en caso que el usuario se salga de la aplicación en el dialogoSeleccionarArchivo
     * @param arg0
     * 
     */
    public void windowClosing( WindowEvent arg0 )
    {

        this.dispose( );
        System.exit( 0 );

    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowDeactivated( WindowEvent arg0 )
    {

    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowDeiconified( WindowEvent arg0 )
    {

    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowIconified( WindowEvent arg0 )
    {

    }

    /**
     * Método declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowOpened( WindowEvent arg0 )
    {

    }

}