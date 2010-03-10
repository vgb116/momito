package uniandes.cupi2.cupiOca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiOca.mundo.Casilla;

/**
 * Clase de pruebas para la clase Casilla
 * 
 */
public class CasillaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la cual se realizarán las pruebas
     */
    private Casilla casilla;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Casilla de tipo Puente y con consecutivo 5
     * 
     */
    public void setupEscenario1( )
    {
        casilla = new Casilla( Casilla.TIPO_PUENTE, 5 );
    }

    /**
     * Verifica que al crear un casilla se le asigne bien el consecutivo y el tipo
     */
    public void testCasilla( )
    {
        setupEscenario1( );
        assertEquals( "Tipo incorrecto", Casilla.TIPO_PUENTE, casilla.darTipo( ) );
        assertEquals( "Consecutivo incorrecto", 5, casilla.darPosicionCasilla( ) );
    }

    /**
     * Verifica que se asigne correctamente la siguiente casilla
     */
    public void testDarSiguiente( )
    {
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_VACIA, 6 );
        casilla.cambiarSiguiente( nuevaCasilla );
        assertEquals( "La siguiente casilla es incorrecta", nuevaCasilla, casilla.darSiguiente( ) );

    }

    /**
     * Verifica que se cambie correctamente la siguiente casilla
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_VACIA, 6 );
        casilla.cambiarSiguiente( nuevaCasilla );
        Casilla nuevaCasilla2 = new Casilla( Casilla.TIPO_PUENTE, 7 );
        casilla.cambiarSiguiente( nuevaCasilla2 );
        assertEquals( "La siguiente casilla no se cambió", nuevaCasilla2, casilla.darSiguiente( ) );
    }

    /**
     * Verifica que se asigne correctamente la casilla anterior
     */
    public void testDarAnterior( )
    {
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_PUENTE, 4 );
        casilla.cambiarAnterior( nuevaCasilla );
        assertEquals( "La anterior casilla es incorrecta", nuevaCasilla, casilla.darAnterior( ) );
    }

    /**
     * Verifica que se cambie correctamente la siguiente casilla
     */
    public void testCambiarAnterior( )
    {
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_VACIA, 4 );
        casilla.cambiarAnterior( nuevaCasilla );
        Casilla nuevaCasilla2 = new Casilla( Casilla.TIPO_PUENTE, 3 );
        casilla.cambiarAnterior( nuevaCasilla2 );
        assertEquals( "La casilla anterior no se cambió", nuevaCasilla2, casilla.darAnterior( ) );
    }

    /**
     * Verifica que el tipo de la casilla se retorne correctamente
     */
    public void testDarTipo( )
    {
        setupEscenario1( );
        assertEquals( "Tipo incorrecto", Casilla.TIPO_PUENTE, casilla.darTipo( ) );
    }

    /**
     * Verifica que la posición de la casilla se retorne correctamente
     */
    public void testDarPosicionCasilla( )
    {
        setupEscenario1( );
        assertEquals( "Consecutivo incorrecto", 5, casilla.darPosicionCasilla( ) );
    }

    /**
     * Verifica que se encuentre una casilla dado su tipo
     */
    public void testBuscarSiguienteTipoCasilla( )
    {
        String tipoBuscado = Casilla.TIPO_RODADERO;
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_VACIA, 6 );
        casilla.cambiarSiguiente( nuevaCasilla );
        Casilla nuevaCasilla2 = new Casilla( Casilla.TIPO_CARCEL, 7 );
        nuevaCasilla.cambiarSiguiente( nuevaCasilla2 );
        Casilla nuevaCasilla3 = new Casilla( Casilla.TIPO_POSADA, 8 );
        nuevaCasilla2.cambiarSiguiente( nuevaCasilla3 );
        Casilla nuevaCasilla4 = new Casilla( Casilla.TIPO_VACIA, 9 );
        nuevaCasilla3.cambiarSiguiente( nuevaCasilla4 );
        Casilla nuevaCasilla5 = new Casilla( Casilla.TIPO_PUENTE, 10 );
        nuevaCasilla4.cambiarSiguiente( nuevaCasilla5 );
        Casilla nuevaCasilla6 = new Casilla( Casilla.TIPO_RODADERO, 11 );
        nuevaCasilla5.cambiarSiguiente( nuevaCasilla6 );
        Casilla nuevaCasilla7 = new Casilla( Casilla.TIPO_RODADERO, 12 );
        nuevaCasilla6.cambiarSiguiente( nuevaCasilla7 );
        Casilla nuevaCasilla8 = new Casilla( Casilla.TIPO_OCA, 13 );
        nuevaCasilla7.cambiarSiguiente( nuevaCasilla8 );
        Casilla nuevaCasilla9 = new Casilla( Casilla.TIPO_RODADERO, 14 );
        nuevaCasilla8.cambiarSiguiente( nuevaCasilla9 );

        Casilla casillaBuscada = casilla.buscarSiguienteTipoCasilla( tipoBuscado );
        assertEquals( "La casilla retornada no es la esperada", 11, casillaBuscada.darPosicionCasilla( ) );

    }

    /**
     * Verifica que se encuentre una casilla dado su tipo
     */
    public void testBuscarAnteriorTipoCasilla( )
    {
        String tipoBuscado = Casilla.TIPO_PUENTE;
        setupEscenario1( );
        Casilla nuevaCasilla = new Casilla( Casilla.TIPO_VACIA, 4 );
        casilla.cambiarAnterior( nuevaCasilla );
        Casilla nuevaCasilla2 = new Casilla( Casilla.TIPO_CARCEL, 3 );
        nuevaCasilla.cambiarAnterior( nuevaCasilla2 );
        Casilla nuevaCasilla3 = new Casilla( Casilla.TIPO_PUENTE, 2 );
        nuevaCasilla2.cambiarAnterior( nuevaCasilla3 );
        Casilla nuevaCasilla4 = new Casilla( Casilla.TIPO_PUENTE, 1 );
        nuevaCasilla3.cambiarAnterior( nuevaCasilla4 );

        Casilla casillaBuscada = casilla.buscarAnteriorTipoCasilla( tipoBuscado );
        assertEquals( "La casilla retornada no es la esperada", 2, casillaBuscada.darPosicionCasilla( ) );
    }

}
