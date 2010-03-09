/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiOcaTest.java,v 1.19 2010/02/23 00:27:47 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiOca
 * Autor: Juan David Villa - 14-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiOca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.CupiOca;
import uniandes.cupi2.cupiOca.mundo.CupiOcaException;
import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Esta es la clase usada para verificar que los métodos de la clase CupiOca <br>
 * estén correctamente implementados
 */
public class CupiOcaTest extends TestCase
{

    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con la ruta de un archivo de configuración del tablero de juego
     */
    private final static String RUTA_CONFIGURACION = "./test/data/mundoTest.oca";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private CupiOca cupiOca;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva CupiOca sin jugadores <br>
     * 
     */
    public void setupEscenario1( )
    {

        try
        {
            cupiOca = new CupiOca( RUTA_CONFIGURACION );
        }
        catch( CupiOcaException e )
        {
            fail( );
        }

    }

    /**
     * Construye una nueva CupiOca con cinco jugadores
     */
    public void setupEscenario2( )
    {
        setupEscenario1( );
        Jugador temp;
        cupiOca.agregarJugador( "a", "imagenA" );
        temp = cupiOca.darJugador( "a" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 4 ) );
        cupiOca.agregarJugador( "b", "imagenB" );
        temp = cupiOca.darJugador( "b" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 3 ) );
        cupiOca.agregarJugador( "c", "imagenC" );
        temp = cupiOca.darJugador( "c" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 1 ) );
        cupiOca.agregarJugador( "d", "imagenD" );
        temp = cupiOca.darJugador( "d" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 5 ) );
        cupiOca.agregarJugador( "e", "imagenE" );
        temp = cupiOca.darJugador( "e" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 2 ) );
    }

    /**
     * Valida el constructor de la clase
     */
    public void testCupiOca( )
    {
        setupEscenario1( );

        assertEquals( "La cantidad de jugadores iniciales debe ser cero", 0, cupiOca.darNumJugadores( ) );
    }

    /**
     * Verifica que se cree un jugador
     */
    public void testAgregarJugador( )
    {
        setupEscenario1( );

        cupiOca.agregarJugador( "Juan", "./data/imagenes/4plus.png" );
        assertNotNull( cupiOca.darJugador( "Juan" ) );
    }

    /**
     * Verifica que se cambie el jugador en turno por el siguiente
     */
    public void testCambiarJugadorEnTurno( )
    {
        setupEscenario1( );
        cupiOca.agregarJugador( "Juan", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Pedro", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Genaro", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Diego", "./data/imagenes/4plus.png" );
        cupiOca.cambiarJugadorEnTurno( );
        assertEquals( "El jugador en turno no se cambió correctamente", "Pedro", cupiOca.darJugadorEnTurno( ).darNick( ) );
    }

    /**
     * Verifica que se retorne un jugador dado su nick
     */
    public void testDarJugador( )
    {
        setupEscenario1( );
        cupiOca.agregarJugador( "Prueba dar jugador", "imagenPrueba" );
        assertNotNull( cupiOca.darJugador( "Prueba dar jugador" ) );
    }

    /**
     * Agrega cuatro jugadores, simula 3 cambios de turno <br>
     * y verifica que el cuarto jugador agregado sea el jugador en turno
     */
    public void testDarJugadorEnTurno( )
    {
        setupEscenario1( );
        cupiOca.agregarJugador( "Juan", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Pedro", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Carlos", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Laura", "./data/imagenes/4plus.png" );
        cupiOca.cambiarJugadorEnTurno( );
        cupiOca.cambiarJugadorEnTurno( );
        cupiOca.cambiarJugadorEnTurno( );
        Jugador jugadorEnTurno = cupiOca.darJugadorEnTurno( );
        assertEquals( "El jugador en turno es incorrecto", "Laura", jugadorEnTurno.darNick( ) );
    }

    /**
     * Agrega tres jugadores y verifica que el número de jugadores sea el correcto
     */
    public void testDarNumJugadores( )
    {
        setupEscenario1( );
        cupiOca.agregarJugador( "Juan", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Pedro", "./data/imagenes/4plus.png" );
        cupiOca.agregarJugador( "Laura", "./data/imagenes/4plus.png" );
        assertEquals( "El número de jugadores es incorrecto", 3, cupiOca.darNumJugadores( ) );
    }

    /**
     * Verifica que la primera casilla tenga consecutivo 1 <br>
     * y su tipo sea TIPO_INICIO
     */
    public void testDarPrimeraCasilla( )
    {
        setupEscenario1( );
        Casilla primeraCasilla = cupiOca.darPrimeraCasilla( );
        assertEquals( "La primera casilla es incorrecta", Casilla.TIPO_INICIO, primeraCasilla.darTipo( ) );
        assertEquals( "La primera casilla es incorrecta", 1, primeraCasilla.darPosicionCasilla( ) );

    }

    /**
     * Verifica que el número del dado esté entre 1 y 6
     */
    public void testSimularDado( )
    {
        setupEscenario1( );
        int dado = cupiOca.simularDado( );
        boolean numeroCorrecto = false;
        if( dado >= 1 && dado <= 6 )
            numeroCorrecto = true;
        assertTrue( "El número del dado debe estar entre 1 y 6", numeroCorrecto );
    }

    /**
     * Cargar el archivo de propiedades y verifica que las casillas de un tipo aleatorio estén en las posiciones que determina el archivo de configuración
     */
    public void testCargarTableroDeJuego( )
    {
        setupEscenario1( );
        Casilla primeraCasilla = cupiOca.darPrimeraCasilla( );
        Casilla primerPuente = primeraCasilla.buscarSiguienteTipoCasilla( Casilla.TIPO_OCA );
        Casilla segundoPuente = primerPuente.buscarSiguienteTipoCasilla( Casilla.TIPO_OCA );
        assertEquals( "El primer puente está en una ubicación incorrecta", 8, primerPuente.darPosicionCasilla( ) );
        assertEquals( "El segundo puente está en una ubicación incorrecta", 15, segundoPuente.darPosicionCasilla( ) );

    }

    /**
     * Verifica que se encuentre el número de casillas de cada tipo
     */
    public void testDarNumCasillas( )
    {
        setupEscenario1( );
        assertEquals( "El número de calaveras es incorrecto", 1, cupiOca.darNumCasillas( Casilla.TIPO_CALAVERA ) );
        assertEquals( "El número de inicio es incorrecto", 1, cupiOca.darNumCasillas( Casilla.TIPO_INICIO ) );
        assertEquals( "El número de ocas es incorrecto", 2, cupiOca.darNumCasillas( Casilla.TIPO_OCA ) );
        assertEquals( "El número de posadas es incorrecto", 1, cupiOca.darNumCasillas( Casilla.TIPO_POSADA ) );
        assertEquals( "El número de puentes es incorrecto", 7, cupiOca.darNumCasillas( Casilla.TIPO_PUENTE ) );
        assertEquals( "El número de rodaderos es incorrecto", 7, cupiOca.darNumCasillas( Casilla.TIPO_RODADERO ) );
        assertEquals( "El número de vacías es incorrecto", 29, cupiOca.darNumCasillas( Casilla.TIPO_VACIA ) );
        assertEquals( "El número de cárceles es incorrecto", 1, cupiOca.darNumCasillas( Casilla.TIPO_CARCEL ) );

    }

    /**
     * Elimina un jugador diferente al jugador en turno, <br>
     * luego intenta eliminar al jugador en turno. <br>
     * Verifica que se genere una excepción
     */
    public void testEliminarJugador( )
    {

        setupEscenario1( );
        cupiOca.agregarJugador( "a", "imagenA" );
        cupiOca.agregarJugador( "b", "imagenB" );
        cupiOca.agregarJugador( "c", "imagenC" );

        try
        {
            cupiOca.eliminarJugador( "b" );
            assertEquals( "El número de jugadores debe disminuir", 2, cupiOca.darNumJugadores( ) );
        }
        catch( Exception e )
        {
            fail( "Fallo al eliminar un jugador que no estaba en turno" );
        }

        try
        {
            cupiOca.eliminarJugador( "a" );
            fail( "No se debe permitir eliminar el jugador en turno" );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }

    }

    /**
     * Agrega cuatro jugadores con nicks "a","c","d" y "e" en las casillas 4,1,5 y 2. <br>
     * Luego los ordena de mejor a peor ranking quedando en el siguiente orden "d","a","e","c". <br>
     * Por último agrega un nuevo jugador "b" en la posición 3 y <br>
     * agrega en forma ordenada este elemento en la lista que se había ordenado anteriormente,<br>
     * verificando que "b" haya sido agregado en la tercera posición.
     */
    public void testInsertarOrdenado( )
    {
        setupEscenario1( );
        Jugador temp;
        cupiOca.agregarJugador( "a", "imagenA" );
        temp = cupiOca.darJugador( "a" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 4 ) );
        cupiOca.agregarJugador( "c", "imagenC" );
        temp = cupiOca.darJugador( "c" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 1 ) );
        cupiOca.agregarJugador( "d", "imagenD" );
        temp = cupiOca.darJugador( "d" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 5 ) );
        cupiOca.agregarJugador( "e", "imagenE" );
        temp = cupiOca.darJugador( "e" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 2 ) );

        Jugador mejorRanking = cupiOca.darListaRanking( );
        cupiOca.agregarJugador( "b", "imagenB" );
        temp = cupiOca.darJugador( "b" );
        temp.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 3 ) );
        Jugador listaOrdenada = cupiOca.insertarOrdenado( mejorRanking, temp.darCopia( ) );

        // Encuentre a "b" en la lista ordenada y debe ser la tercera posición
        assertEquals( "B debió ser agregado en la tercera posición de la lista ordenada", "b", listaOrdenada.darSiguiente( ).darSiguiente( ).darNick( ) );

    }

    /**
     * Se agregan cinco jugadores con nicks "a","b","c","d" y "e" <br>
     * en las casillas 4,3,1,5 y 2 respectivamente. <br>
     * Se valida que el método ordenar retorne la siguiente lista <br>
     * ordenada "d","a","b","e" y "c"
     */
    public void testOrdenar( )
    {
        setupEscenario2( );
        Jugador mejorRanking = cupiOca.darListaRanking( );
        assertEquals( "El jugador con el mejor ranking es incorrecto", "d", mejorRanking.darNick( ) );
        Jugador temp = mejorRanking.darSiguiente( );
        int posicion = 2;
        while( temp != null )
        {
            if( posicion == 2 )
            {
                assertEquals( "El jugador con el segundo mejor ranking es incorrecto", "a", temp.darNick( ) );
            }
            else if( posicion == 3 )
            {
                assertEquals( "El jugador con el tercer mejor ranking es incorrecto", "b", temp.darNick( ) );
            }
            else if( posicion == 4 )
            {
                assertEquals( "El jugador con el cuarto mejor ranking es incorrecto", "e", temp.darNick( ) );
            }
            else if( posicion == 5 )
            {
                assertEquals( "El jugador con el peor ranking es incorrecto", "c", temp.darNick( ) );
            }

            posicion++;
            temp = temp.darSiguiente( );
        }
    }

}