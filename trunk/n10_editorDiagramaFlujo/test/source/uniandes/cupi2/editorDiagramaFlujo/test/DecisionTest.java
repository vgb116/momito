/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DecisionTest.java,v 1.2 2009/10/07 16:40:03 c.alvarez947 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.test;

import java.awt.Font;

import junit.framework.TestCase;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Decision;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Decision est�n correctamente implementados
 */
public class DecisionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Decision elemento;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        elemento = new Decision( new Punto( 10, 10 ));
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "Construcci�n incorrecta.", elemento.darAlto( ) == Decision.ALTO );
        assertTrue( "Construcci�n incorrecta.", elemento.darAncho( ) == Decision.ANCHO);
        assertTrue( "Construcci�n incorrecta.", elemento.darTipo( ).equals( Decision.TIPO ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente el texto.
     */
    public void testAsignarDarTexto( )
    {
        setupEscenario1( );
        elemento.cambiarTexto( "Decision 1" );
        assertTrue( "Cambio y/o asignaci�n de texto incorrecto.", elemento.darTexto( ).equals( "Decision 1" ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente la fuente.
     */
    public void testAsignarDarFuente( )
    {
        setupEscenario1( );
        elemento.cambiarFuente( new Font( "Arial", 0, 10 ) );
        assertTrue( "Cambio y/o asignaci�n de fuente incorrecto.", elemento.darFuente( ).equals( new Font( "Arial", 0, 10 ) ) );
    }

    /**
     * Prueba que calcule correctamente el alto.
     */
    public void testCalculoAlto( )
    {
        setupEscenario1( );
        assertTrue( "C�lculo de altura incorrecto", elemento.darAlto( ) == Decision.ALTO );
    }

    /**
     * Prueba que calcule correctamente el ancho.
     */
    public void testCalculoAncho( )
    {
        setupEscenario1( );
        assertTrue( "C�lculo de altura incorrecto", elemento.darAncho( ) == Decision.ANCHO );
    }

    /**
     * Prueba que calcule correctamente si est� adentro una forma.
     */
    public void testCalculoAdentro( )
    {
        setupEscenario1( );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( 10, 10 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( ( int ) ( Decision.ANCHO / 2 * 1.5 ), ( int ) ( Decision.ALTO / 2 * 1.5 ) ) ) );
        assertTrue( "No debe estar adentro", !elemento.estaDentro( new Punto( ( int ) ( Decision.ANCHO / 2 * 3 ), ( int ) ( Decision.ALTO / 2 * 3 ) ) ) );
    }

    /**
     * Prueba que se mueva correctamente una forma, de tal manera que el punto dado por par�metro quede <br>
     * ubicado en el centro de la forma.
     */
    public void testMoverFigura( )
    {
        setupEscenario1( );
        elemento.moverFigura( new Punto( Decision.ANCHO, Decision.ALTO ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( Decision.ANCHO - Decision.ANCHO / 2, Decision.ALTO - Decision.ALTO / 2 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( Decision.ANCHO + Decision.ANCHO / 2 - 1, Decision.ALTO + Decision.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( Decision.ANCHO - Decision.ANCHO / 2 - 1, Decision.ALTO - Decision.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( Decision.ANCHO + Decision.ANCHO / 2 + 2, Decision.ALTO + Decision.ALTO / 2 + 2 ) ) );
    }

}