/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DiagramaFlujoTest.java,v 1.3 2009/10/21 15:57:51 carl-veg Exp $
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

import java.io.IOException;

import junit.framework.TestCase;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Decision;
import uniandes.cupi2.editorDiagramaFlujo.mundo.DiagramaFlujo;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Entrada;
import uniandes.cupi2.editorDiagramaFlujo.mundo.FormatoInvalidoException;
import uniandes.cupi2.editorDiagramaFlujo.mundo.IForma;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;
import uniandes.cupi2.editorDiagramaFlujo.mundo.SalidaInformacion;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase DiagramaFlujo est�n correctamente implementados
 */
public class DiagramaFlujoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private DiagramaFlujo DiagramaFlujo;
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo diagrama de flujo
     * 
     */
    private void setupEscenario1( )
    {
        DiagramaFlujo = new DiagramaFlujo( );
    }

    /**
     * Verifica que el diagrama vac�o se construya correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * Diagrama, darNombreArchivo. <br>
     * <b> Objetivo: </b> probar que el m�todo constructor construye un diagrama vac�o cuyo archivo es null. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al construir un diagrama vac�o, el archivo debe ser null.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertTrue( "El archivo debe ser null dado que no se ha inicializado", DiagramaFlujo.darNombreArchivo( ) == null );
    }

    /**
     * Verifica que un elemento se agregue correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarElemento, buscarElemento. <br>
     * <b> Objetivo: </b> probar que el m�todo agregarElemento() agrega correctamente un elemento al diagrama. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un elemento y buscarlo, �ste debe ser encontrado.
     */
    public void testAgregarElemento( )
    {
        setupEscenario1( );
        DiagramaFlujo.agregarElemento( new Decision( new Punto( 10, 10 ) ) );
        assertNotNull( "En el punto 15, 15 debe haber un elemento", DiagramaFlujo.buscarElemento( new Punto( 15, 15 ) ) );
        assertTrue( "En el punto 15, 15 debe haber un elemento de tipo Clase", DiagramaFlujo.buscarElemento( new Punto( 15, 15 ) ).darTipo( ).equals( Decision.TIPO ) );
    }

    /**
     * Verifica la eliminaci�n de las figuras. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarElemento, eliminarFigura, buscarElemento. <br>
     * <b> Objetivo: </b> probar que el m�todo eliminarElemento() elimina correctamente un elemento del diagrama. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al eliminar un elemento previamente agregado, �ste debe desaparecer del diagrama. <br>
     */
    public void testEliminarElemento( )
    {
        setupEscenario1( );
        IForma elemento = new Decision( new Punto( 10, 10 ) );
        DiagramaFlujo.agregarElemento( elemento );
        DiagramaFlujo.eliminarFigura( );
        assertTrue( "En el punto 15, 15 no debe haber elementos", DiagramaFlujo.buscarElemento( new Punto( 15, 15 ) ) == null );
    }

    /**
     * Verifica el m�todo hacerClick. <br>
     * <b> M�todos a probar: </b> <br>
     * hacerClick, agregarElemento, darSeleccionada. <br>
     * <b> Objetivo: </b> probar que el m�todo hacerClick selecciona una figura si existe en el punto dado, si no asigna null a la figura seleccionada <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al hacer click en el punto donde hay un elemento, este queda seleccionado. <br>
     * 2. Al hacer click en el punto donde no hay un elemento, seleccionado queda en null <br>
     */
    public void testHacerClick( )
    {
        setupEscenario1( );
        IForma elemento = new Decision( new Punto( 10, 10 ) );
        DiagramaFlujo.agregarElemento( elemento );
        DiagramaFlujo.hacerClick( new Punto( 10, 10 ) );

        assertTrue( "No se seleccion� correctamente el elemento", DiagramaFlujo.darSeleccionada( ).equals( elemento ) );
        DiagramaFlujo.hacerClick( new Punto( 1000, 1000 ) );
        assertNull( "No deber�a haber una figura seleccionada", DiagramaFlujo.darSeleccionada( ) );
    }

    /**
     * Verifica los m�todos que se encargan de la persistencia del diagrama. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarElemento, salvar, reiniciar, abrir, buscarElemento. <br>
     * <b> Objetivo: </b> probar que los m�todos que se encargan de la persistencia del diagrama lo hagan seg�n su responsabilidad. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar tres elementos y salvarlos en un archivo, se debe poder abrir el archivo, cargar los elementos y encontrarlos en el diagrama. <br>
     */
    public void testGuardarCargarDiagrama( )
    {
        setupEscenario1( );
        IForma elemento = new Decision( new Punto( 10, 10 ) );
        IForma elemento2 = new Entrada( new Punto( 100, 200 ) );
        IForma elemento3 = new SalidaInformacion( new Punto( 300, 300 ) );
        DiagramaFlujo.agregarElemento( elemento );
        DiagramaFlujo.agregarElemento( elemento2 );
        DiagramaFlujo.agregarElemento( elemento3 );
        try
        {
            DiagramaFlujo.salvar( "./test/archivoPrueba.data" );
            DiagramaFlujo.reiniciar( );
            DiagramaFlujo.abrir( "./test/archivoPrueba.data" );
            assertTrue( "Error cuando se guarda la informaci�n", DiagramaFlujo.buscarElemento( new Punto( 10, 10 ) ).darTipo( ).equals( Decision.TIPO ) );
            assertTrue( "Error cuando se guarda la informaci�n", DiagramaFlujo.buscarElemento( new Punto( 100, 200 ) ).darTipo( ).equals( Entrada.TIPO ) );
            assertTrue( "Error cuando se guarda la informaci�n", DiagramaFlujo.buscarElemento( new Punto( 300, 300 ) ).darTipo( ).equals( SalidaInformacion.TIPO ) );
        }
        catch( IOException e )
        {
            fail( "No se pudo cargar el archivo: " + e.getMessage( ) );
        }
        catch( FormatoInvalidoException e )
        {
            fail( "No se pudo cargar el archivo ( Formato inv�lido ): " + e.getMessage( ) );
        }
    }
}