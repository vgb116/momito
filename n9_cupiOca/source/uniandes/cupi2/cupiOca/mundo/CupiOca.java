/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiOca.java,v 1.32 2010/02/22 23:55:28 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiOca
 * Autor: Juan David Villa - 14-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiOca.mundo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Clase principal de la aplicación <br>
 * Invariante de la clase: <br>
 * cantidadJugadores >= 0 <br>
 * primeraCasilla!=null <br>
 * Las casillas están correctamente enlazadas en una lista doblemente encadenada.<br>
 * Para la lista de jugadores se verifica que cada uno tenga un siguiente y que el siguiente del último jugador sea el jugador en turno <br>
 */
public class CupiOca
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La primera casilla del tablero de juego
     */
	// TODO modele el atributo que representa la primera casilla del tablero
    private Casilla casillaInicio;

    /**
     * El jugador en turno
     */
	// TODO modele el atributo que representa el jugador en turno
    private Jugador jugadorEnTurno;

    /**
     * La cantidad de jugadores que están jugando
     */
    private int cantidadJugadores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva cupiOca sin jugadores <br>
     * <b> post: </b> Se inicializa cantidadJugadores en 0. <br>
     * Se inicializa jugadorEnTurno en null. <br>
     * @param rutaTableroDejuego La ruta donde se encuentra la configuración del tablero de juego
     * @throws CupiOcaException Lanzada en caso que haya un error al cargar el archivo de configuración
     */
    public CupiOca( String rutaTableroDejuego ) throws CupiOcaException
    {
        cantidadJugadores = 0;
        cargarTableroDeJuego( rutaTableroDejuego );
        verificarInvariante( );

    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la primera casilla del tablero de juego
     * @return primeraCasilla La primera casilla
     */
    public Casilla darPrimeraCasilla( )
    {
    	// TODO Completar según la documentación
        return casillaInicio;
    }

    /**
     * Carga un tablero de juego desde un archivo de configuración con el formato descrito en el archivo de descripción del ejercicio.<br> 
     * <b> pre: </b> Existe un archivo de configuración en la ruta. <br>
     * <b> post: </b> Se inicializan las casillas del tablero de juego. <br>
     * @param ruta Ruta al archivo de configuración
     * @throws CupiOcaException Lanzada en caso que el archivo de configuración no se encuentre <br>
     *         o haya un error al leerlo
     */
    public void cargarTableroDeJuego( String ruta ) throws CupiOcaException
    {
    	
    	// TODO Completar según la documentación

    }

    /**
     * Simula el resultado de un dado
     * @return Un número aleatorio entero entre 1 y 6
     */
    public int simularDado( )
    {
        Random generador = new Random( );
        int num = generador.nextInt( 6 ) + 1;
        return num;
    }

    /**
     * El jugador actual juega con un número aleatorio generado, se cambia el jugador en turno por el siguiente. <br>
     * <b> pre: </b> jugadorEnTurno != null.<br>
     * <b> post: </b> El jugador en turno es ahora el jugador siguiente.<br>
     * @return infoJugada La información del resultado de la jugada.
     */
    public InfoJugada jugar( )
    {
    	// TODO Completar según la documentación
    	// Ayuda: llame al método jugar del jugadorActual, el número de posiciones lo obtiene con el método simular dado
        
    	
    }

    /**
     * Cambia al jugador en turno por el siguiente <br>
     * <b> pre: </b> jugadorEnTurno != null <br>
     * <b> post: </b> El siguiente jugador al jugador en turno, es ahora el jugador en turno.<br>
     */
    public void cambiarJugadorEnTurno( )
    {
    	// TODO Completar según la documentación
        jugadorEnTurno = jugadorEnTurno.darSiguiente( );
    }

    /**
     * Crea un nuevo jugador con el nombre y la imagen dados como parámetros. <br>
     * Al jugador se le asigna el último turno y se ingresa en la última posición de la lista de jugadores,<br>
     * manteniendo la estructura de una lista circular, donde todos los jugadores tienen un siguiente jugador<br>
     * <b> pre: </b> No existe un jugador con el mismo nick <br>
     * <b> post: </b> Si el jugador en turno era nulo, el jugador en turno es ahora el jugador creado y el siguiente jugador al jugador en turno es el mismo. <br>
     * Si el jugador en turno no era nulo, se busca el ultimo jugador de la lista y se agrega después de este. El siguiente jugador del jugador creado es el jugador en turno. <br>
     * Se aumenta cantidadJugadores en uno. <br>
     * @param nickJugador El nombre del nuevo jugador. - nombreJugador != null
     * @param rutaImagen La ruta de la imagen del jugador. - rutaImagen != null
     */
    public void agregarJugador( String nickJugador, String rutaImagen )
    {

    	// TODO Completar según la documentación
        
    }

    /**
     * Busca un jugador con el nick dado como parámetro. <br>
     * <b> pre: </b> jugadorEnTurno != null <br>
     * @param nick El nick del jugador buscado. - nick != null
     * @return El jugador que tiene un nick igual al dado por parámetro, null si no lo encuentra
     */
    public Jugador darJugador( String nick )
    {
    	// TODO Completar según la documentación
    	//Ayuda: Tenga en cuenta que la lista de jugadores es una lista circular
    }

    /**
     * Verifica si existe un jugador con un nick igual al pasado como parámetro
     * @param nick El nick del jugador que se quiere verificar si existe. - nick != null
     * @return True en caso que exista, false de lo contrario
     */
    public boolean existeJugador( String nick )
    {
    	
    	// TODO Completar según la documentación
    	
    }

    /**
     * Retorna el jugador en turno
     * @return jugadorEnTurno El jugador en turno
     */
    public Jugador darJugadorEnTurno( )
    {
        return jugadorEnTurno;
    }

    /**
     * Este método retorna el número de jugadores en el juego
     * @return cantidadJugadores El número de jugadores en el juego
     */
    public int darNumJugadores( )
    {
        return cantidadJugadores;
    }

    /**
     * Este método retorna el número de casillas cuyo tipo es igual al dado como parámetro
     * @param tipoCasilla El tipo de casilla buscado - tipoCasilla == (TIPO_CALAVERA || TIPO_CARCEL || TIPO_INICIO ||TIPO_OCA||TIPO_POSADA
     *        ||TIPO_PUENTE||TIPO_RODADERO||TIPO_VACIA)
     * @return numCasillas El número de casillas cuyo tipo es igual al dado como parámetro
     */
    public int darNumCasillas( String tipoCasilla )
    {
    	
    	// TODO Completar según la documentación

    }

    /**
     * Este método elimina un jugador dado su nick <br>
     * <b> pre: </b> Existe un jugador cuyo nick es nickAEliminar <br>
     * <b> post: </b> Se elimino el jugador de la lista de jugadores, y se mantuvo la estructura circular de la lista <br>
     * @param nickAEliminar El nick del jugador que se desea eliminar
     * @throws Exception En caso que el jugador que se desee eliminar sea el jugador en turno
     */
    public void eliminarJugador( String nickAEliminar ) throws Exception
    {
    	// TODO Completar según la documentación
    	
    }

    /**
     * Este método agrega en forma ordenada un jugador a una lista ordenada y sencillamente encadenada. <br>
     * El orden esta dado por la posición de la casilla en la que se encuentra un jugador. Se encuentra ordenado de mayor a menor casilla.
     * <b>post: </b>El jugador agregar es agregado manteniendo la lista ordenada
     * @param primerRanking El primer jugador de la lista ordenada - primerRanking != null
     * @param agregar El jugador que se va a agregar a la lista - agregar != null
     * @return El primer jugador de la lista ordenada
     */
    public Jugador insertarOrdenado( Jugador primerRanking, Jugador agregar )
    {
    	
    	// TODO Completar según la documentación
    	
    }

    /**
     * Retorna una lista de jugadores sencillamente encadenada ordenada.<br>
     * El orden esta dado por la posición de la casilla en la que se encuentra un jugador. Se encuentra ordenado de mayor a menor casilla.
     * <b>pre: </b>jugadorEnTurno!=null
     * @return El primer jugador de la lista ordenada por posición de casilla
     */
    public Jugador darListaRanking( )
    {
    	
    	// TODO Completar según la documentación
    	//Ayuda: Utilice el método insertarOrdenado para construir la lista.
    	//		 Utilice el método darCopia de la clase jugador, para construir una lista con copias de los jugadores

    }

    /**
     * Verifica el invariante de la clase: <br>
     * cantidadJugadores >= 0 <br>
     * primeraCasilla!=null <br>
     * Las casillas están correctamente enlazadas en una lista doblemente encadenada.<br>
     * Para la lista de jugadores se verifica que cada uno tenga un siguiente y que el siguiente del último jugador sea el jugador en turno <br>
     */
    public void verificarInvariante( )
    {
        assert cantidadJugadores >= 0 : "El número de jugadores debe ser igual a cero cuando inicia el juego y mayor a cero en el transcurso";
        assert primeraCasilla != null : "No se cargó el tablero de juego";

        Casilla casillaActual = primeraCasilla;
        Casilla casillaSiguiente = casillaActual.darSiguiente( );
        while( casillaSiguiente != null )
        {
            assert casillaSiguiente.darAnterior( ) == casillaActual : "La casilla siguiente debe tener a la actual como anterior";
            casillaActual = casillaSiguiente;
            casillaSiguiente = casillaActual.darSiguiente( );
        }

        int contador = 0;
        Jugador jugadorActual = jugadorEnTurno;
        while( contador < cantidadJugadores )
        {
            assert jugadorActual.darSiguiente( ) != null : "Todo jugador debe tener un siguiente";
            if( contador == cantidadJugadores - 1 )
            {
                assert jugadorActual.darSiguiente( ).equals( jugadorEnTurno ) : "El siguiente del último jugador debe ser el jugador en turno";
            }
            jugadorActual = jugadorActual.darSiguiente( );
            contador++;
        }

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}