package uniandes.cupi2.cupiOca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.InfoJugada;
import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Clase de pruebas para la clase Jugador
 * 
 */
public class JugadorTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la cual se realizarán las pruebas
     */
    private Jugador jugador;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nuevo jugador con: nick = "Juan" <br>
     * casillaActual = Un casilla TIPO_INICIO con posición 1 <br>
     * imagen = "./data/imagenes/4plus.png" <br>
     */
    public void setupEscenario1( )
    {
        jugador = new Jugador( "Juan", new Casilla( Casilla.TIPO_INICIO, 1 ), "./data/imagenes/4plus.png" );
    }

    /**
     * Valida el constructor de la clase
     */
    public void testJugador( )
    {
        setupEscenario1( );
        assertEquals( "El nick del jugador es incorrecto", "Juan", jugador.darNick( ) );
        assertEquals( "La casilla inicial del jugador es incorrecta", 1, jugador.darCasillaActual( ).darPosicionCasilla( ) );
        assertEquals( "La imagen del jugador es incorrecta", "./data/imagenes/4plus.png", jugador.darImagen( ) );
    }

    /**
     * Verifica que se cambie la casilla actual correctamente
     */
    public void testCambiarCasillaActual( )
    {
        setupEscenario1( );
        jugador.cambiarCasillaActual( new Casilla( Casilla.TIPO_VACIA, 3 ) );
        assertEquals( "La casilla se cambió correctamente", 3, jugador.darCasillaActual( ).darPosicionCasilla( ) );
        assertEquals( "La casilla se cambió correctamente", Casilla.TIPO_VACIA, jugador.darCasillaActual( ).darTipo( ) );
    }

    /**
     * Verifica que el nick se retorne correctamente
     */
    public void testDarNick( )
    {
        setupEscenario1( );
        assertEquals( "El nick del jugador es incorrecto", "Juan", jugador.darNick( ) );

    }
    /**
     * Verifica que se retorne correctamente la casilla actual
     */
    public void testDarCasillaActual( )
    {
        setupEscenario1( );
        assertEquals( "La casilla inicial del jugador es incorrecta", 1, jugador.darCasillaActual( ).darPosicionCasilla( ) );
    }

    /**
     * Verifica que se retorne la imagen correcta
     */
    public void testDarImagen( )
    {
        setupEscenario1( );
        assertEquals( "La imagen del jugador es incorrecta", "./data/imagenes/4plus.png", jugador.darImagen( ) );
    }

    /**
     * Verifica que se cambie correctamente el número de turnos perdidos
     */
    public void testCambiarTurnosPerdidos( )
    {
        setupEscenario1( );
        jugador.cambiarTurnosPerdidos( 4 );
        assertEquals( "El número de turnos perdidos es incorrecto", 4, jugador.darTurnosPerdidos( ) );
    }

    /**
     * Verifica que se disminuya correctamente los turnos perdidos
     */
    public void testDisminuirTurnosPerdidos( )
    {
        setupEscenario1( );
        jugador.cambiarTurnosPerdidos( 2 );
        jugador.disminuirTurnosPerdidos( );
        assertEquals( "El número de turnos perdidos es incorrecto", 1, jugador.darTurnosPerdidos( ) );

    }

    /**
     * Verifica que la información de la jugada sea correcta
     */
    public void testJugar( )
    {
        setupEscenario1( );
        Casilla siguiente = new Casilla( Casilla.TIPO_VACIA, 2 );
        Casilla siguiente2 = new Casilla( Casilla.TIPO_RODADERO, 3 );
        siguiente.cambiarSiguiente( siguiente2 );
        jugador.darCasillaActual( ).cambiarSiguiente( siguiente );
        InfoJugada infoJugada = jugador.jugar( 2 );

        assertEquals( "La casilla inicial es incorrecta", 1, infoJugada.darCasillaInicial( ).darPosicionCasilla( ) );
        assertEquals( "La casilla final es incorrecta", 3, infoJugada.darCasillaFinal( ).darPosicionCasilla( ) );
        assertTrue( "Debio haber ganado el juego porque llego a la última casilla", infoJugada.esGanador( ) );
        assertEquals( "Mensaje incorrecto", "Sacaste 2 ,pero regresa al anterior rodadero", infoJugada.darMensaje( ) );
        assertEquals( "El número de posiciones movidas es incorrecto", 2, infoJugada.darPosicionesMovidas( ) );

    }

    /**
     * Verifica que al caer en un tipo de casilla se genere el mensaje correcto
     */
    public void testLogicaJuego( )
    {
        setupEscenario1( );
        Casilla casilla = new Casilla( Casilla.TIPO_RODADERO, 3 );
        String respuesta = jugador.simularJuego( casilla, 2 );
        assertEquals( "El mensaje al caer a un rodadero es incorrecto", "Sacaste 2 ,pero regresa al anterior rodadero", respuesta );

    }

    /**
     * Verifica que al moverse se cambie correctamente la casilla actual del jugador
     */
    public void testMoverse( )
    {
        setupEscenario1( );
        Casilla siguiente = new Casilla( Casilla.TIPO_VACIA, 2 );
        Casilla siguiente2 = new Casilla( Casilla.TIPO_RODADERO, 3 );
        siguiente.cambiarSiguiente( siguiente2 );
        jugador.darCasillaActual( ).cambiarSiguiente( siguiente );
        jugador.jugar( 2 );

        assertEquals( "El jugador se movió a una casilla incorrecta", siguiente2, jugador.darCasillaActual( ) );
    }

    /**
     * Verifica que se retorne una copia correcta
     */
    public void testDarCopia( )
    {
        setupEscenario1( );
        Jugador copia = jugador.darCopia( );
        assertEquals( "El nick del jugador es incorrecto", "Juan", copia.darNick( ) );
        assertEquals( "La casilla inicial del jugador es incorrecta", 1, copia.darCasillaActual( ).darPosicionCasilla( ) );
        assertEquals( "La imagen del jugador es incorrecta", "./data/imagenes/4plus.png", copia.darImagen( ) );
    }

}
