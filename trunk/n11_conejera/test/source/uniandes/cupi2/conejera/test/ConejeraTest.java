/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConejeraTest.java,v 1.4 2009/11/30 19:58:48 dav-vill Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_conejera
 * Autor: Juan David Villa - 03-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.conejera.test;

import junit.framework.TestCase;
import uniandes.cupi2.conejera.mundo.Conejera;
import uniandes.cupi2.conejera.mundo.ParejaConejos;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Conejera est�n correctamente implementados
 */
public class ConejeraTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Conejera conejera;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Conejera vac�a <br>
     * Costo de mantenimiento de una pareja adulta 20.000 <br>
     * Costo de mantenimiento de una pareja beb� 10.000
     */
    public void setupEscenario1( )
    {
        conejera = new Conejera( );
        conejera.establecerCostosMantenimiento( 20000, 10000 );
    }

    /**
     * Construye una nueva conejera y simula el paso de 4 meses <br>
     * Costo de mantenimiento de una pareja adulta 20.000 <br>
     * Costo de mantenimiento de una pareja beb� 10.000
     */
    public void setupEscenario2( )
    {
        conejera = new Conejera( );
        conejera.establecerCostosMantenimiento( 20000, 10000 );
        conejera.simularPasoMes( );
        conejera.simularPasoMes( );
        conejera.simularPasoMes( );
        conejera.simularPasoMes( );
    }

    /**
     * Este m�todo prueba que el mes inicial sea 0, que se haya inicializado la clase que genera los identificadores de las parejas, que la pareja inicial haya sido
     * inicializada y que el costo de manutenci�n para el mes 0 sea correcto
     */
    public void testConejera( )
    {
        setupEscenario1( );
        assertEquals( "El mes inicial debe ser 0", 0, conejera.darMesActual( ) );
        assertNotNull( "El IdParejaConejos no fue inicializado", conejera.darIdConejos( ) );
        assertNotNull( "La pareja inicial no puede ser null", conejera.darRaiz( ) );
        assertEquals( "El dinero invertido en manutenci�n para el mes 0 es incorrecto", 10000.0, conejera.darValorInvertidoTotal( ) );
    }

    /**
     * Verifica que al pasar un mes se cambie la edad y estado de la pareja inicial de conejos, que el mes actual aumente y que el valor acumulado de manutenci�n se haya
     * calculado bien
     */
    public void testSimularPasoMes( )
    {
        setupEscenario1( );
        conejera.simularPasoMes( );
        assertEquals( "El mes actual no cambi� correctamente", 1, conejera.darMesActual( ) );
        ParejaConejos raiz = conejera.darRaiz( );
        assertEquals( "La pareja no aument� su edad correctamente", 1, raiz.darEdad( ) );
        assertEquals( "La pareja no pas� del estado beb� al estado adulto", ParejaConejos.ESTADO_ADULTOS, raiz.darEstado( ) );
        assertEquals( "La pareja no debi� haber tenido hijos", 0, raiz.darHijos( ).size( ) );
        assertEquals( "El valor acumulado de manutenci�n es incorrecto", 30000.0, conejera.darValorInvertidoTotal( ) );
    }

    /**
     * Verifica que se inicialice la pareja inicial al momento de crear la aplicaci�n
     */
    public void testDarRaiz( )
    {
        setupEscenario1( );
        assertNotNull( "La raiz no debe ser null", conejera.darRaiz( ) );
    }

    /**
     * Verifica que los costos de manutenci�n de los cuatro primeros meses sean correctos
     */
    public void testDarValorInvertidoPorMes( )
    {
        setupEscenario2( );
        assertEquals( "El valor invertido en manutenci�n en el mes 1 es incorrecto", 20000.0, conejera.darValorInvertidoPorMes( 1 ) );
        assertEquals( "El valor invertido en manutenci�n en el mes 2 es incorrecto", 30000.0, conejera.darValorInvertidoPorMes( 2 ) );
        assertEquals( "El valor invertido en manutenci�n en el mes 3 es incorrecto", 50000.0, conejera.darValorInvertidoPorMes( 3 ) );
        assertEquals( "El valor invertido en manutenci�n en el mes 4 es incorrecto", 80000.0, conejera.darValorInvertidoPorMes( 4 ) );
    }

    /**
     * Verifica que el costo de manutenci�n acumulado hasta el cuarto mes sea correcto
     */
    public void testDarValorInvertidoTotal( )
    {
        setupEscenario2( );
        assertEquals( "El valor invertido en manutenci�n hasta el mes 4 es incorrecto", 190000.0, conejera.darValorInvertidoTotal( ) );
    }

    /**
     * Verifica que el n�mero de parejas en cada mes sea correcto
     */
    public void testDarNumeroParejasEnMes( )
    {
        setupEscenario2( );
        assertEquals( "El n�mero de parejas en el mes 1 es incorrecto", 1, conejera.darNumeroParejasEnMes( 1 ) );
        assertEquals( "El n�mero de parejas en el mes 2 es incorrecto", 2, conejera.darNumeroParejasEnMes( 2 ) );
        assertEquals( "El n�mero de parejas en el mes 3 es incorrecto", 3, conejera.darNumeroParejasEnMes( 3 ) );
        assertEquals( "El n�mero de parejas en el mes 4 es incorrecto", 5, conejera.darNumeroParejasEnMes( 4 ) );
    }

    /**
     * Verifica que se calcule correctamente el n�mero de descendientes beb�s de una pareja dado su id
     */
    public void testDescendientesBebes( )
    {
        setupEscenario2( );
        try
        {
            assertEquals( "El n�mero de descendientes beb�s de la pareja con id 0 es incorrecto", 2, conejera.descendientesBebes( 0 ) );
        }
        catch( Exception e )
        {
            fail( "fallo al tratar de calcular el n�mero de descendientes beb�s" );
        }
    }

}