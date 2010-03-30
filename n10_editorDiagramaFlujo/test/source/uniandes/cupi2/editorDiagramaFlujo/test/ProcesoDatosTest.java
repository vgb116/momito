/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ProcesoDatosTest.java,v 1.2 2009/10/07 16:40:03 c.alvarez947 Exp $
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
import uniandes.cupi2.editorDiagramaFlujo.mundo.ProcesoDatos;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase ProcesoDatos est�n correctamente implementados
 */
public class ProcesoDatosTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private ProcesoDatos elemento;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        elemento = new ProcesoDatos( new Punto( 10, 10 ));
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "Construcci�n incorrecta.", elemento.darAlto( ) == ProcesoDatos.ALTO );
        assertTrue( "Construcci�n incorrecta.", elemento.darAncho( ) == ProcesoDatos.ANCHO);
        assertTrue( "Construcci�n incorrecta.", elemento.darTipo( ).equals( ProcesoDatos.TIPO ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente el texto.
     */
    public void testAsignarDarTexto( )
    {
        setupEscenario1( );
        elemento.cambiarTexto( "Proceso 1" );
        assertTrue( "Cambio y/o asignaci�n de texto incorrecto.", elemento.darTexto( ).equals( "Proceso 1" ) );
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
        assertTrue( "C�lculo de altura incorrecto", elemento.darAlto( ) == ProcesoDatos.ALTO );
    }

    /**
     * Prueba que calcule correctamente el ancho.
     */
    public void testCalculoAncho( )
    {
        setupEscenario1( );
        assertTrue( "C�lculo de altura incorrecto", elemento.darAncho( ) == ProcesoDatos.ANCHO );
    }

    /**
     * Prueba que calcule correctamente si est� adentro una forma.
     */
    public void testCalculoAdentro( )
    {
        setupEscenario1( );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( 10, 10 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( ( int ) ( ProcesoDatos.ANCHO / 2 * 1.5 ), ( int ) ( ProcesoDatos.ALTO / 2 * 1.5 ) ) ) );
        assertTrue( "No debe estar adentro", !elemento.estaDentro( new Punto( ( int ) ( ProcesoDatos.ANCHO / 2 * 3 ), ( int ) ( ProcesoDatos.ALTO / 2 * 3 ) ) ) );
    }

    /**
     * Prueba que se mueva correctamente una forma, de tal manera que el punto dado por par�metro quede <br>
     * ubicado en el centro de la forma.
     */
    public void testMoverFigura( )
    {
        setupEscenario1( );
        elemento.moverFigura( new Punto( ProcesoDatos.ANCHO, ProcesoDatos.ALTO ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( ProcesoDatos.ANCHO - ProcesoDatos.ANCHO / 2, ProcesoDatos.ALTO - ProcesoDatos.ALTO / 2 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( ProcesoDatos.ANCHO + ProcesoDatos.ANCHO / 2 - 1, ProcesoDatos.ALTO + ProcesoDatos.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( ProcesoDatos.ANCHO - ProcesoDatos.ANCHO / 2 - 1, ProcesoDatos.ALTO - ProcesoDatos.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( ProcesoDatos.ANCHO + ProcesoDatos.ANCHO / 2 + 2, ProcesoDatos.ALTO + ProcesoDatos.ALTO / 2 + 2 ) ) );
    }

}