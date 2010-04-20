package uniandes.cupi2.conejera.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.conejera.mundo.IdParejaConejos;
import uniandes.cupi2.conejera.mundo.ParejaConejos;

/**
 * Clase para verificar que los métodos de la clase ParejaConejos estén correctamente implementados
 */
public class ParejaConejosTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La clase sobre la cual se realizan las pruebas
     */
    private ParejaConejos pareja;

    /**
     * Clase necesaria para ciertas pruebas
     */
    private IdParejaConejos idParejaConejos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea una nueva pareja de conejos
     */
    public void setupEscenario1( )
    {

        idParejaConejos = new IdParejaConejos( );
        pareja = new ParejaConejos( idParejaConejos.darConsecutivo( ), 0 );
        pareja.cambiarValMantenimientoAdultos( 20000.0 );
        pareja.cambiarValMantenimientoBebes( 10000 );
    }

    /**
     * Simula el paso de cuatro meses para las parejas de conejos. El árbol de conejos comienza con la pareja de id 1.
     */
    public void setupEscenario2( )
    {
        setupEscenario1( );
        pareja.simularMes( idParejaConejos, 1 );
        System.out.println("p1");
        pareja.simularMes( idParejaConejos, 2 );
        System.out.println("p2");
        pareja.simularMes( idParejaConejos, 3 );
        System.out.println("p3");
        pareja.simularMes( idParejaConejos, 4 );
        System.out.println("p4");
    }

    /**
     * Verifica que una pareja de conejos se cree correctamente
     */
    public void testParejaConejos( )
    {
        setupEscenario1( );
        assertEquals( "La pareja debe estar en estado bebé", ParejaConejos.ESTADO_BEBES, pareja.darEstado( ) );
        assertEquals( "La pareja no debe tener hijos", 0, pareja.darHijos( ).size( ) );
        assertEquals( "El id de la pareja es incorrecto", 0, pareja.darId( ) );
    }

    /**
     * Verifica que se le haya asignado un id correcto a la primera pareja de conejos. El id esperado es 1 porque al crearse la conejera se crea una pareja de conejos a la
     * cual se le asigna el id 0, pero esta no es la pareja usada en esta prueba.
     */
    public void testDarId( )
    {
        setupEscenario1( );
        assertEquals( "Se asignó un identificador incorrecto a la pareja", 0, pareja.darId( ) );
    }

    /**
     * Verifica que al pasar un mes, la pareja de conejos pase del estado bebé al estado adulto y que aumente en 1 mes su edad
     */
    public void testCrecer( )
    {
        setupEscenario1( );
        pareja.crecer( );
        assertEquals( "La edad de la pareja es incorrecta", 1, pareja.darEdad( ) );
        assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_ADULTOS, pareja.darEstado( ) );

    }

    /**
     * Busca una pareja que está en el árbol y verifica que la edad y estado de ésta sean correctos
     */
    public void testBuscarPareja( )
    {
        setupEscenario2( );
        ParejaConejos temp = pareja.buscarPareja( 4 );
        assertNotNull( "Error al encontrar una pareja", temp );
        if( temp != null )
        {
            assertEquals( "La pareja encontrada no tiene la edad correcta", 0, temp.darEdad( ) );
            assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_BEBES, temp.darEstado( ) );
        }

    }

    /**
     * Verifica que el numeró de descendientes bebés de una pareja sean correctos
     */
    public void testDescendientesBebes( )
    {
        setupEscenario2( );
        ArrayList bebes = new ArrayList( );
        pareja.descendientesBebes( bebes );
        assertEquals( "El número de descendientes bebés es incorrecto", 2, bebes.size( ) );
    }

    /**
     * Verifica que el número de parejas en un mes dado sea correcto
     */
    public void testDarNumeroParejasEnMes( )
    {
        setupEscenario2( );
        int numParejas = pareja.darNumeroParejasEnMes( 4 );
        assertEquals( "El número de parejas en el mes 4 es incorrecto", 5, numParejas );
    }

    /**
     * Verifica que la edad de una pareja se haya actaulizado correctamente luego del paso de 4 meses
     */
    public void testDarEdad( )
    {
        setupEscenario2( );
        assertEquals( "La edad de la pareja es incorrecta", 4, pareja.darEdad( ) );
    }

    /**
     * Verifica que el estado de una pareja se haya actualizado correctamente luego del paso de 4 meses
     */
    public void testDarEstado( )
    {
        setupEscenario2( );
        assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_ADULTOS, pareja.darEstado( ) );
    }

    /**
     * Verifica que el número de hijos sea correcto y que tengan los ids correctos
     */
    public void testDarHijos( )
    {
        setupEscenario2( );
        ParejaConejos temp = pareja.buscarPareja( 1 );
        assertNotNull( "Error al encontrar una pareja", temp );
        if( temp != null )
        {
            ArrayList hijos = temp.darHijos( );
            assertEquals( "El número de hijos es incorrecto", 1, hijos.size( ) );
            ParejaConejos hijo = ( ParejaConejos )hijos.get( 0 );
            assertEquals( "El id del hijo es incorrecto", 3, hijo.darId( ) );
        }

    }

    /**
     * Verifica si una pareja es una hoja usando dos casos, el primero con base en la raíz de un árbol de 3 niveles y el segundo preguntándole a una hoja de ese mismo árbol
     */
    public void testEsHoja( )
    {
        setupEscenario2( );
        assertFalse( "Error al verificar si una pareja es una hoja", pareja.esHoja( ) );
        ParejaConejos temp = pareja.buscarPareja( 5 );
        if( temp != null )
        {
            assertTrue( "Error al verificar si una pareja es una hoja", temp.esHoja( ) );
        }
    }

    /**
     * Verifica que una pareja sin hijos se reproduzca, para esto valida que tenga una nueva pareja de hijos, que el id y el estado de ésta sean los esperados
     */
    public void testReproducirse( )
    {
        setupEscenario2( );
        //System.out.println("escenario cargado");
        ParejaConejos temp = pareja.buscarPareja( 2 );
        //System.out.println("el id de temp " );
        temp.reproducirse( idParejaConejos, 5 );
        //System.out.println("temp ok");
        ArrayList hijos = temp.darHijos( );
        assertEquals( "El número de hijos es incorrecto", 1, hijos.size( ) );
        ParejaConejos hijo = ( ParejaConejos )hijos.get( 0 );
        assertEquals( "El id del hijo es incorrecto", 5, hijo.darId( ) );
        assertEquals( "Los hijos deben inicializarse en estado Bebé", ParejaConejos.ESTADO_BEBES, hijo.darEstado( ) );
    }

    /**
     * Simula el paso del mes 4 al mes 5. Verifica que se creen 3 nuevas parejas y que todas sean inicializadas en estado bebé.
     */
    public void testSimularPasoMes( )
    {
        setupEscenario2( );
        pareja.simularMes( idParejaConejos, 5 );
        //System.out.println("pareja conejos mes 5 agregada");
        assertEquals( "El número de parejas aumento correctamente", pareja.darNumeroParejasEnMes( 5 ), 8 );
        ParejaConejos temp1 = pareja.buscarPareja( 5 );
        assertNotNull( "No se crearon correctamente las nuevas parejas", temp1 );
        assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_BEBES, temp1.darEstado( ) );
        ParejaConejos temp2 = pareja.buscarPareja( 6 );
        assertNotNull( "No se crearon correctamente las nuevas parejas", temp2 );
        assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_BEBES, temp2.darEstado( ) );
        ParejaConejos temp3 = pareja.buscarPareja( 7 );
        assertNotNull( "No se crearon correctamente las nuevas parejas", temp3 );
        assertEquals( "El estado de la pareja es incorrecto", ParejaConejos.ESTADO_BEBES, temp3.darEstado( ) );
    }

    /**
     * Verifica que el valor invertido en un mes arbitrario sea correcto
     */
    public void testValorInvertidoMes( )
    {
        setupEscenario2( );
        double val = pareja.valorInvertidoMes( 4 );
        assertEquals( "El valor invertido en el mes 4 es incorrecto", 80000.0, val );
    }

}
