/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConexionTest.java,v 1.3 2009/10/21 15:57:51 carl-veg Exp $
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
import uniandes.cupi2.editorDiagramaFlujo.mundo.Conexion;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Decision;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Conexion estén correctamente implementados
 */
public class ConexionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Conexion elemento;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        Decision elementoInicio = new Decision( new Punto( 100, 100 ) );
        Decision elementoDestino = new Decision( new Punto( 500, 500 ) );
        elemento = new Conexion( elementoInicio, elementoDestino );
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "Construcción incorrecta.", elemento.darTipo( ).equals( Conexion.TIPO ) );
    }

    /**
     * Prueba que se asigna y retorne correctamente el texto.
     */
    public void testAsignarDarTexto( )
    {
        setupEscenario1( );
        elemento.cambiarTexto( "Hola mundo" );
        assertTrue( "Cambio y/o asignación de texto incorrecto.", elemento.darTexto( ).equals( "Hola mundo" ) );
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
     * Prueba que calcule correctamente si está adentro una figura.
     */
    public void testCalculoAdentro( )
    {
        setupEscenario1( );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( 200, 150 ) ) );
        assertTrue( "Debe estar adentro", elemento.estaDentro( new Punto( 600, 550 ) ) );
        assertTrue( "No debe estar adentro", !elemento.estaDentro( new Punto( 1000, 1000 ) ) );
    }

}