package uniandes.cupi2.cupiOca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.InfoJugada;

/**
 * Clase de pruebas para la clase InfoJugada
 */
public class InfoJugadaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la cual se realizarán las pruebas
     */
    private InfoJugada infoJugada;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva InfoJugada con: <br>
     * casillaInicial = Casilla TIPO_INICIO en la posición 1 <br>
     * casillaFinal = Casilla TIPO_VACIA en la posición 5 <br>
     * gano = false <br>
     * mensaje = "" <br>
     * posicionesMovidas = 4 <br>
     */
    public void setupEscenario1( )
    {

        infoJugada = new InfoJugada( new Casilla( Casilla.TIPO_INICIO, 1 ), new Casilla( Casilla.TIPO_VACIA, 5 ), false, "", 4 );
    }

    /**
     * Valida que se construya correctamente una infojugada
     */
    public void testInfoJugada( )
    {

        setupEscenario1( );
        assertEquals( "La casilla inicial es incorrecta", 1, infoJugada.darCasillaInicial( ).darPosicionCasilla( ) );
        assertEquals( "La casilla final es incorrecta", 5, infoJugada.darCasillaFinal( ).darPosicionCasilla( ) );
        assertFalse( "No debió haber ganado el juego", infoJugada.esGanador( ) );
        assertEquals( "Al caer en una casilla vacía se debe generar un mensaje vacío", "", infoJugada.darMensaje( ) );
        assertEquals( "El número de posiciones movidas es incorrecto", 4, infoJugada.darPosicionesMovidas( ) );

    }

    /**
     * Verifica que se retorne correctamente la casilla inicial
     */
    public void testDarCasillaInicial( )
    {
        setupEscenario1( );
        assertEquals( "La casilla inicial es incorrecta", 1, infoJugada.darCasillaInicial( ).darPosicionCasilla( ) );
        assertEquals( "La casilla inicial es incorrecta", Casilla.TIPO_INICIO, infoJugada.darCasillaInicial( ).darTipo( ) );
    }

    /**
     * Verifica que se retorne correctamente la casilla final
     */
    public void testDarCasillaFinal( )
    {
        setupEscenario1( );
        assertEquals( "La casilla final es incorrecta", 5, infoJugada.darCasillaFinal( ).darPosicionCasilla( ) );
        assertEquals( "La casilla final es incorrecta", Casilla.TIPO_VACIA, infoJugada.darCasillaFinal( ).darTipo( ) );
    }

    /**
     * Verifica que se retorne correctamente el mensaje
     */
    public void testDarMensaje( )
    {
        setupEscenario1( );
        assertEquals( "Al caer en una casilla vacía se debe generar un mensaje vacío", "", infoJugada.darMensaje( ) );

    }

    /**
     * Verifica que se retorne correctamente si es ganador
     */
    public void testEsGanador( )
    {
        setupEscenario1( );
        assertFalse( "No debió haber ganado el juego", infoJugada.esGanador( ) );
    }

    /**
     * Verifica que se retorne correctamente las posiciones movidas
     */
    public void testDarPosicionesMovidas( )
    {
        setupEscenario1( );
        assertEquals( "El número de posiciones movidas es incorrecto", 4, infoJugada.darPosicionesMovidas( ) );
    }
}
