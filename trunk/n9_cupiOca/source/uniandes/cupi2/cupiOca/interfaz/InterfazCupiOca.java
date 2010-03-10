/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCupiOca.java,v 1.27 2009/11/23 19:13:04 dav-vill Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazCupiOca extends JFrame implements WindowListener
{

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
    private CupiOca cupiOca;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones de eliminar un jugador, mostrar el n�mero de casillas de un tipo y las extensiones del juego
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
     * Dialogo para seleccionar un archivo de configuraci�n
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
     * Descripci�n Ventana principal de la aplicaci�n<br>
     * <b>post: </b> Se crea un nuevo tablero de juego sin ning�n jugador y con la opci�n de jugar deshabilitada hasta que se agregue por lo menos un jugador
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

        // Creaci�n del panel contenedor (GridBagLayout) que contiene a panelTablero, panelEstadoJuego y panelControl
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
    // M�todos
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
     * Crea un nuevo jugador con el nombre y la imagen dados como par�metros. En el orden de creaci�n se asigna el turno para jugar
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
     * Realiza una jugada para el jugador en turno. Con la informaci�n de la jugada se actualiza el n�mero de casillas movidas en el panelControl, se dibuja al jugador en el
     * panel de la casilla en la cual cay� y se borra de la casilla en la cual estaba. Si la casilla en la cual cay� no es una casilla vac�a se le informa al usuario el tipo
     * de casilla en la cual cayo y el premio o el castigo asignado. Si lleg� a la casilla final se le informa al jugador que gan� y se bloquea el bot�n jugar del panel
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
            JOptionPane.showMessageDialog( null, respuesta, "Informaci�n", JOptionPane.INFORMATION_MESSAGE );

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
     * Este m�todo retorna el n�mero de casillas cuyo tipo es igual al dado como par�metro
     * @param tipoCasilla El tipo de casilla buscada
     * @return numCasillas El n�mero de casillas cuyo tipo es igual al dado como par�metro
     */
    public int darNumCasillas( String tipoCasilla )
    {
        int numCasillas = cupiOca.darNumCasillas( tipoCasilla );
        return numCasillas;
    }

    /**
     * Este m�todo retorna una lista (c�clica y sencillamente encadenada) de jugadores ordenados seg�n su turno para jugar
     * @return La lista de jugadores
     */
    public Jugador darJugadorEnTurno( )
    {
        return cupiOca.darJugadorEnTurno( );
    }

    /**
     * Este m�todo retorna el n�mero de jugadores inscritos en el juego
     * @return El n�mero de jugadores en el juego
     */
    public int darNumJugadores( )
    {
        return cupiOca.darNumJugadores( );
    }

    /**
     * Este m�todo elimina al jugador cuyo nick es pasado como par�metro solo si no es el jugador en turno. Tambi�n borra del tablero de juego y del panelEstado al jugador
     * @param nickAEliminar EL nick del jugador que se quiere eliminar
     * @throws Exception Lanza una excepci�n en caso que el jugador a eliminar sea el jugador en turno
     */
    public void eliminarJugador( String nickAEliminar ) throws Exception
    {
        Jugador jugador = cupiOca.darJugador( nickAEliminar );
        cupiOca.eliminarJugador( nickAEliminar );
        quitarJugadorTablero( jugador.darCasillaActual( ).darPosicionCasilla( ), jugador );
        panelEstadoJuego.actualizar( cupiOca.darListaRanking( ), cupiOca.darNumJugadores( ) );
    }

    /**
     * Borra del tablero de juego en la casilla con posici�n posCasilla al jugador pasado como par�metro
     * @param posCasilla La posici�n de la casilla en el tablero de juego, de la cual se va a borrar el jugador
     * @param jugador El jugador que se va a borrar
     */
    public void quitarJugadorTablero( int posCasilla, Jugador jugador )
    {
        PanelCasilla panelCasilla = panelTablero.buscarCasilla( posCasilla );
        panelCasilla.quitarJugador( jugador );
        panelCasilla.repaint( );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {

        String resultado = cupiOca.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
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
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupiOca interfaz = new InterfazCupiOca( );
        interfaz.setVisible( true );
    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowActivated( WindowEvent arg0 )
    {

    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowClosed( WindowEvent arg0 )
    {

    }

    /**
     * M�todo declarado por la interfaz WindowListener usado en caso que el usuario se salga de la aplicaci�n en el dialogoSeleccionarArchivo
     * @param arg0
     * 
     */
    public void windowClosing( WindowEvent arg0 )
    {

        this.dispose( );
        System.exit( 0 );

    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowDeactivated( WindowEvent arg0 )
    {

    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowDeiconified( WindowEvent arg0 )
    {

    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowIconified( WindowEvent arg0 )
    {

    }

    /**
     * M�todo declarado por la interfaz WindowListener
     * @param arg0
     */
    public void windowOpened( WindowEvent arg0 )
    {

    }

}