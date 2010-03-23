/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PuntoTest.java,v 1.2 2009/10/07 16:40:03 c.alvarez947 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.test;

import junit.framework.TestCase;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Punto estén correctamente implementados
 */
public class PuntoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Punto punto;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Construye una nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        punto = new Punto( 10, 20 );
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "Construcción incorrecta.", punto.darX( ) == 10 );
        assertTrue( "Construcción incorrecta.", punto.darY( ) == 20 );

    }

    /**
     * Prueba que se modifiquen correctamente las coordenadas de los puntos.
     */
    public void testModificar( )
    {
        setupEscenario1( );
        punto.modificarX( 20 );
        punto.modificarY( 70 );
        assertTrue( "No se modificó correctamente la coordenada X.", punto.darX( ) == 20 );
        assertTrue( "No se modificó correctamente la coordenada Y.", punto.darY( ) == 70 );

    }

}