/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SalidaInformacionTest.java,v 1.3 2009/10/21 15:57:51 carl-veg Exp $
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

import java.awt.Font;

import junit.framework.TestCase;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;
import uniandes.cupi2.editorDiagramaFlujo.mundo.SalidaInformacion;

/**
 * Esta es la clase usada para verificar que los métodos de la clase SalidaInformacioón estén correctamente implementados
 */
public class SalidaInformacionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private SalidaInformacion elemento;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        elemento = new SalidaInformacion( new Punto( 10, 10 ));
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "Construcción incorrecta.", elemento.darAlto( ) == SalidaInformacion.ALTO );
        assertTrue( "Construcción incorrecta.", elemento.darAncho( ) == SalidaInformacion.ANCHO);
        assertTrue( "Construcción incorrecta.", elemento.darTipo( ).equals( SalidaInformacion.TIPO ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente el texto.
     */
    public void testAsignarDarTexto( )
    {
        setupEscenario1( );
        elemento.cambiarTexto( "Salida Informacion 1" );
        assertTrue( "Cambio y/o asignación de texto incorrecto.", elemento.darTexto( ).equals( "Salida Informacion 1" ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente la fuente.
     */
    public void testAsignarDarFuente( )
    {
        setupEscenario1( );
        elemento.cambiarFuente( new Font( "Arial", 0, 10 ) );
        assertTrue( "Cambio y/o asignación de fuente incorrecto.", elemento.darFuente( ).equals( new Font( "Arial", 0, 10 ) ) );
    }

    /**
     * Prueba que calcule correctamente el alto.
     */
    public void testCalculoAlto( )
    {
        setupEscenario1( );
        assertTrue( "Cálculo de altura incorrecto", elemento.darAlto( ) == SalidaInformacion.ALTO );
    }

    /**
     * Prueba que calcule correctamente el ancho.
     */
    public void testCalculoAncho( )
    {
        setupEscenario1( );
        assertTrue( "Cálculo de altura incorrecto", elemento.darAncho( ) == SalidaInformacion.ANCHO );
    }

    /**
     * Prueba que calcule correctamente si está adentro una forma.
     */
    public void testCalculoAdentro( )
    {
        setupEscenario1( );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( 10, 10 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( ( int ) ( SalidaInformacion.ANCHO / 2 * 1.5 ), ( int ) ( SalidaInformacion.ALTO / 2 * 1.5 ) ) ) );
        assertTrue( "No debe estar adentro", !elemento.estaDentro( new Punto( ( int ) ( SalidaInformacion.ANCHO / 2 * 3 ), ( int ) ( SalidaInformacion.ALTO / 2 * 3 ) ) ) );
    }

    /**
     * Prueba que se mueva correctamente una forma, de tal manera que el punto dado por parámetro quede <br>
     * ubicado en el centro de la forma.
     */
    public void testMoverFigura( )
    {
        setupEscenario1( );
        elemento.moverFigura( new Punto( SalidaInformacion.ANCHO, SalidaInformacion.ALTO ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( SalidaInformacion.ANCHO - SalidaInformacion.ANCHO / 2, SalidaInformacion.ALTO - SalidaInformacion.ALTO / 2 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( SalidaInformacion.ANCHO + SalidaInformacion.ANCHO / 2 - 1, SalidaInformacion.ALTO + SalidaInformacion.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( SalidaInformacion.ANCHO - SalidaInformacion.ANCHO / 2 - 1, SalidaInformacion.ALTO - SalidaInformacion.ALTO / 2 - 1 ) ) );
        assertFalse( "No debe estar adentro", elemento.estaDentro( new Punto( SalidaInformacion.ANCHO + SalidaInformacion.ANCHO / 2 + 2, SalidaInformacion.ALTO + SalidaInformacion.ALTO / 2 + 2 ) ) );
    }

}