/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FincaRaizTest.java,v 1.7 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_fincaRaiz
 * Autor: Camilo Alvarez Duran - 21-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.fincaRaiz.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.fincaRaiz.mundo.FincaRaiz;
import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Clase para probar el funcionamiento correcto de los métodos en la clase FincaRaiz
 */
public class FincaRaizTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private FincaRaiz fincaRaiz;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un sistema de finca raíz con 9 inmuebles
     * <b>Número de inmuebles: 9</b><br/>
     * <b>Datos del inmueble 1:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Bogotá, Barrio: Álamos1, Dirección: Cra. 97 No. 51-51, Teléfono: 123456, Tamaño: 123.2d, Precio: 567d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 2:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Arrendar, Ciudad: Cali, Barrio: Sausal, Dirección: Cra. 42 No. 51-51, Teléfono: 123456, Tamaño: 1234, Precio: 54457.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 3:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Venta, Ciudad: Cartagena, Barrio: Amigos, Dirección: Cra. 38 No. 51-51, Teléfono: 123456, Tamaño:  123456.2d, Precio: 5678d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 4:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Cali, Barrio: Alamos, Dirección: Cra. 24 No. 51-51, Teléfono: 123456, Tamaño:  1234567.2d, Precio: 56789d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 5:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Arrendar, Ciudad: Barranquilla, Barrio: Ceditros, Dirección: Cra. 16 No. 51-51, Teléfono: 123456, Tamaño: 234.2d, Precio: 678d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 6:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Arrendar, Ciudad: Pasto, Barrio: Chico, Dirección: Cra. 78 No. 51-51, Teléfono: 123456, Tamaño: 23452d, Precio: 2345.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 7:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Venta, Ciudad: Tunja, Barrio: Alamos, Dirección: Cra. 35 No. 51-51, Teléfono: 123456, Tamaño: 5478.2d, Precio: 6789.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 8:</b> <br/>
     * TipoInmueble: Finca, TipoOferta: Arrendar, Ciudad: Bogotá, Barrio: Villavicencio, Dirección: Cra. 98 No. 51-51, Teléfono: 123456, Tamaño: 234567.2d, Precio: 789.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 9:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Bogotá, Barrio: Los Rosales, Dirección: Cra. 47 No. 51-51, Teléfono: 123456, Tamaño: 345d, Precio: 7891.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/> 
     */
    public void setupEscenario1( )
    {
        fincaRaiz = new FincaRaiz( );
        ArrayList imagenes = new ArrayList( );
        imagenes.add( "./data/ap1.jpg" );
        imagenes.add( "./data/ap2.jpg" );
        imagenes.add( "./data/ap3.jpg" );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.VENTA, "Bogota", "Alamos1", "Cra. 97 No. 51-51", "123456", 123.2d, 567d, imagenes );
        fincaRaiz.agregarInmueble( "Casa", Inmueble.ARRENDAR, "Cali", "Sausal", "Cra. 42 No. 51-51", "123456", 1234, 54457.d, imagenes );
        fincaRaiz.agregarInmueble( "Casa", Inmueble.VENTA, "Cartagena", "Amigos", "Cra. 38 No. 51-51", "123456", 123456.2d, 5678d, imagenes );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.VENTA, "Cali", "Alamos", "Cra. 24 No. 51-51", "123456", 1234567.2d, 56789d, imagenes );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.ARRENDAR, "Barranquilla", "Ceditros", "Cra. 16 No. 51-51", "123456", 234.2d, 678d, imagenes );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.ARRENDAR, "Pasto", "Chico", "Cra. 78 No. 51-51", "123456", 23452d, 2345.d, imagenes );
        fincaRaiz.agregarInmueble( "Casa", Inmueble.VENTA, "Tunja", "Alamos", "Cra. 35 No. 51-51", "123456", 5478.2d, 6789.d, imagenes );
        fincaRaiz.agregarInmueble( "Finca", Inmueble.ARRENDAR, "Bogota", "Villavicencio", "Cra. 98 No. 51-51", "123456", 234567.2d, 789.d, imagenes );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.VENTA, "Bogota", "Los Rosales", "Cra. 47 No. 51-51", "123456", 345d, 7891.d, imagenes );
    }

    /**
     * Verifica el constructor. <br>
     * <b> Métodos a probar: </b> <br>
     * FincaRaiz (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea un sistema de finca raíz de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un sistema los atributos del objeto deben quedar con el valor correcto.
     */
    public void testFincaRaiz( )
    {
        setupEscenario1( );
        assertNotNull( "No se inicializó la lista de inmuebles", fincaRaiz.darListaInmuebles( ) );
    }

    /**
     * Verifica el método para ordenar por el nombre de la ciudad. <br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorCiudad. <br>
     * <b> Objetivo: </b> Probar que el método ordenarPorCiudad ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el nombre de la ciudad.
     * 
     */
    public void testOrdenarPorCiudad( )
    {
    	// TODO Completar según documentación   
    }

    /**
     * Verifica el método para ordenar por el identificador del inmueble. <br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorIdentificador. <br>
     * <b> Objetivo: </b> Probar que el método ordenarPorIdentificador ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el identificador del inmueble.
     * 
     */
    public void testOrdenarPorIdentificador( )
    {
    	// TODO Completar según documentación   
    }

    /**
     * Verifica el método para ordenar por el precio del inmueble. <br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorPrecio. <br>
     * <b> Objetivo: </b> Probar que el método ordenarPorPrecio ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el precio del inmueble.
     * 
     */
    public void testOrdenarPorPrecio( )
    {
    	// TODO Completar según documentación   
    }

    /**
     * Verifica el método para ordenar por el tamaño del inmueble. <br>
     * <b> Métodos a probar: </b> <br>
     * ordenarPorTamanio. <br>
     * <b> Objetivo: </b> Probar que el método ordenarPorTamanio ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el tamaño del inmueble.
     * 
     */
    public void testOrdenarPorTamanio( )
    {
    	// TODO Completar según documentación   
    }

    /**
     * Verifica el método buscarBinarioPorIdentificador buscando un inmueble que se sabe que debería encontrarse. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarBinarioPorIdentificador. <br>
     * <b> Objetivo: </b> Probar que el método buscarBinarioPorIdentificador() sea capaz de encontrar inmuebles registrados en el sistema. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un inmueble previamente agregado se debe obtener una posición diferente de -1. <br>
     * 2. Al buscar un inmueble que no exista la posición retornada debe ser -1.
     * 
     */
    public void testBuscarBinarioPorIdentificador( )
    {
    	// TODO Completar según documentación 

    }

    /**
     * Verifica que el método que busca el inmueble más barato para arrendar funcione correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarInmuebleMasBaratoArrendar. <br>
     * <b> Objetivo: </b> Probar que el método buscarInmuebleMasBaratoArrendar retorna el inmueble correcto (el que tiene menor precio y es para arrendar). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el inmueble con el menor precio para ser arrendado se obtiene la posición del inmueble que cumple con esta condición. <br>
     */
    public void testBuscarInmuebleMasBaratoArrendar( )
    {
    	// TODO Completar según documentación 
    }

    /**
     * Verifica que el método que busca el inmueble más costoso para vender funcione correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * buscarInmuebleMasCostosoVenta. <br>
     * <b> Objetivo: </b> Probar que el método buscarInmuebleMasCostosoVenta retorna el inmueble correcto (el que tiene mayor precio y es para vender). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el inmueble con el mayor precio para ser vendido se obtiene la posición del inmueble que cumple con esta condición. <br>
     */
    public void testBuscarInmuebleMasCostosoVenta( )
    {
    	// TODO Completar según documentación 
    }

    /**
     * Verifica que el método que agrega un inmueble funcione correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * agregarInmueble. <br>
     * <b> Objetivo: </b> Probar que el método agregarInmueble agrega un inmueble de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar el inmueble este se encuentra registrado en la lista de inmuebles del sistema. <br>
     */
    public void testAgregarInmueble( )
    {
        setupEscenario1( );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.ARRENDAR, "Bogotá", "Chico", "Cra.170 No. 71-51", "7658745", 877.2d, 88.d, new ArrayList( ) );
        ArrayList inmuebles = fincaRaiz.darListaInmuebles( );
        Inmueble inmResp = null;

        assertEquals( "El número total de inmuebles debería ser 10", 10, inmuebles.size( ) );

        // Buscar el inmueble
        for( int i = 0; i < inmuebles.size( ) && inmResp == null; i++ )
        {
            Inmueble inm = ( Inmueble )inmuebles.get( i );
            if( inm.darIdentificador( ).equals( "9-B-C" ) )
                inmResp = inm;
        }

        assertNotNull( "Se debe encontrar el inmueble", inmResp );
    }

    /**
     * Verifica que el método que elimina un inmueble correctamente. <br>
     * <b> Métodos a probar: </b> <br>
     * eliminarInmueble. <br>
     * <b> Objetivo: </b> Probar que el método eliminarInmueble elimina un inmueble de forma correcta dado su identificador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El inmueble es eliminado de la lista de inmuebles del sistema. <br>
     */
    public void testEliminarInmueble( )
    {
        setupEscenario1( );
        fincaRaiz.eliminarInmueble( "0-B-A" );
        ArrayList inmuebles = fincaRaiz.darListaInmuebles( );
        Inmueble inmResp = null;

        assertEquals( "El número total de inmuebles debería ser 8", 8, inmuebles.size( ) );

        // Buscar el inmueble
        for( int i = 0; i < inmuebles.size( ); i++ )
        {
            Inmueble inm = ( Inmueble )inmuebles.get( i );
            if( inm.darIdentificador( ).equals( "0-B-A" ) )
                inmResp = inm;
        }

        assertNull( "El inmueble no debería existir", inmResp );
    }

}
