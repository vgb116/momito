package uniandes.cupi2.conejera.test;

import junit.framework.TestCase;
import uniandes.cupi2.conejera.mundo.IdParejaConejos;

/**
 *Clase para verificar el correcto funcionamiento de los m�todos de la clase IdParejaConejos
 * 
 */
public class IdParejaConejosTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la cual se van a hacer las pruebas
     */
    private IdParejaConejos id;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo IdParejaConejos
     */
    public void setupEscenario1( )
    {
        id = new IdParejaConejos( );
    }

    /**
     * Verifica que al inicializar la clase, el consecutivo comience en 0
     */
    public void testIdParejaConejos( )
    {
        setupEscenario1( );
        assertEquals( "El consecutivo debe comenzar en 0", 0, id.darConsecutivo( ) );
        assertEquals( "El consecutivo debi� haberse aumentado en 1", 1, id.darConsecutivo( ) );
    }
}
