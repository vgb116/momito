/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FincaRaizTest.java,v 1.7 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Clase para probar el funcionamiento correcto de los m�todos en la clase FincaRaiz
 */
public class FincaRaizTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private FincaRaiz fincaRaiz;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea un sistema de finca ra�z con 9 inmuebles
     * <b>N�mero de inmuebles: 9</b><br/>
     * <b>Datos del inmueble 1:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Bogot�, Barrio: �lamos1, Direcci�n: Cra. 97 No. 51-51, Tel�fono: 123456, Tama�o: 123.2d, Precio: 567d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 2:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Arrendar, Ciudad: Cali, Barrio: Sausal, Direcci�n: Cra. 42 No. 51-51, Tel�fono: 123456, Tama�o: 1234, Precio: 54457.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 3:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Venta, Ciudad: Cartagena, Barrio: Amigos, Direcci�n: Cra. 38 No. 51-51, Tel�fono: 123456, Tama�o:  123456.2d, Precio: 5678d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 4:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Cali, Barrio: Alamos, Direcci�n: Cra. 24 No. 51-51, Tel�fono: 123456, Tama�o:  1234567.2d, Precio: 56789d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 5:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Arrendar, Ciudad: Barranquilla, Barrio: Ceditros, Direcci�n: Cra. 16 No. 51-51, Tel�fono: 123456, Tama�o: 234.2d, Precio: 678d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 6:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Arrendar, Ciudad: Pasto, Barrio: Chico, Direcci�n: Cra. 78 No. 51-51, Tel�fono: 123456, Tama�o: 23452d, Precio: 2345.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 7:</b> <br/>
     * TipoInmueble: Casa, TipoOferta: Venta, Ciudad: Tunja, Barrio: Alamos, Direcci�n: Cra. 35 No. 51-51, Tel�fono: 123456, Tama�o: 5478.2d, Precio: 6789.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 8:</b> <br/>
     * TipoInmueble: Finca, TipoOferta: Arrendar, Ciudad: Bogot�, Barrio: Villavicencio, Direcci�n: Cra. 98 No. 51-51, Tel�fono: 123456, Tama�o: 234567.2d, Precio: 789.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 9:</b> <br/>
     * TipoInmueble: Apartamento, TipoOferta: Venta, Ciudad: Bogot�, Barrio: Los Rosales, Direcci�n: Cra. 47 No. 51-51, Tel�fono: 123456, Tama�o: 345d, Precio: 7891.d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/> 
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
     * <b> M�todos a probar: </b> <br>
     * FincaRaiz (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea un sistema de finca ra�z de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un sistema los atributos del objeto deben quedar con el valor correcto.
     */
    public void testFincaRaiz( )
    {
        setupEscenario1( );
        assertNotNull( "No se inicializ� la lista de inmuebles", fincaRaiz.darListaInmuebles( ) );
    }

    /**
     * Verifica el m�todo para ordenar por el nombre de la ciudad. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorCiudad. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorCiudad ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el nombre de la ciudad.
     * 
     */
    public void testOrdenarPorCiudad( )
    {
    	// TODO Completar seg�n documentaci�n   
    }

    /**
     * Verifica el m�todo para ordenar por el identificador del inmueble. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorIdentificador. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorIdentificador ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el identificador del inmueble.
     * 
     */
    public void testOrdenarPorIdentificador( )
    {
    	// TODO Completar seg�n documentaci�n   
    }

    /**
     * Verifica el m�todo para ordenar por el precio del inmueble. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorPrecio. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorPrecio ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el precio del inmueble.
     * 
     */
    public void testOrdenarPorPrecio( )
    {
    	// TODO Completar seg�n documentaci�n   
    }

    /**
     * Verifica el m�todo para ordenar por el tama�o del inmueble. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorTamanio. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorTamanio ordena los inmuebles de forma correcta (en orden ascendente). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La lista de inmuebles se encuentra ordenada por el tama�o del inmueble.
     * 
     */
    public void testOrdenarPorTamanio( )
    {
    	// TODO Completar seg�n documentaci�n   
    }

    /**
     * Verifica el m�todo buscarBinarioPorIdentificador buscando un inmueble que se sabe que deber�a encontrarse. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarBinarioPorIdentificador. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarBinarioPorIdentificador() sea capaz de encontrar inmuebles registrados en el sistema. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un inmueble previamente agregado se debe obtener una posici�n diferente de -1. <br>
     * 2. Al buscar un inmueble que no exista la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarBinarioPorIdentificador( )
    {
    	// TODO Completar seg�n documentaci�n 

    }

    /**
     * Verifica que el m�todo que busca el inmueble m�s barato para arrendar funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarInmuebleMasBaratoArrendar. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarInmuebleMasBaratoArrendar retorna el inmueble correcto (el que tiene menor precio y es para arrendar). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el inmueble con el menor precio para ser arrendado se obtiene la posici�n del inmueble que cumple con esta condici�n. <br>
     */
    public void testBuscarInmuebleMasBaratoArrendar( )
    {
    	// TODO Completar seg�n documentaci�n 
    }

    /**
     * Verifica que el m�todo que busca el inmueble m�s costoso para vender funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarInmuebleMasCostosoVenta. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarInmuebleMasCostosoVenta retorna el inmueble correcto (el que tiene mayor precio y es para vender). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el inmueble con el mayor precio para ser vendido se obtiene la posici�n del inmueble que cumple con esta condici�n. <br>
     */
    public void testBuscarInmuebleMasCostosoVenta( )
    {
    	// TODO Completar seg�n documentaci�n 
    }

    /**
     * Verifica que el m�todo que agrega un inmueble funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarInmueble. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarInmueble agrega un inmueble de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar el inmueble este se encuentra registrado en la lista de inmuebles del sistema. <br>
     */
    public void testAgregarInmueble( )
    {
        setupEscenario1( );
        fincaRaiz.agregarInmueble( "Apartamento", Inmueble.ARRENDAR, "Bogot�", "Chico", "Cra.170 No. 71-51", "7658745", 877.2d, 88.d, new ArrayList( ) );
        ArrayList inmuebles = fincaRaiz.darListaInmuebles( );
        Inmueble inmResp = null;

        assertEquals( "El n�mero total de inmuebles deber�a ser 10", 10, inmuebles.size( ) );

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
     * Verifica que el m�todo que elimina un inmueble correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarInmueble. <br>
     * <b> Objetivo: </b> Probar que el m�todo eliminarInmueble elimina un inmueble de forma correcta dado su identificador. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El inmueble es eliminado de la lista de inmuebles del sistema. <br>
     */
    public void testEliminarInmueble( )
    {
        setupEscenario1( );
        fincaRaiz.eliminarInmueble( "0-B-A" );
        ArrayList inmuebles = fincaRaiz.darListaInmuebles( );
        Inmueble inmResp = null;

        assertEquals( "El n�mero total de inmuebles deber�a ser 8", 8, inmuebles.size( ) );

        // Buscar el inmueble
        for( int i = 0; i < inmuebles.size( ); i++ )
        {
            Inmueble inm = ( Inmueble )inmuebles.get( i );
            if( inm.darIdentificador( ).equals( "0-B-A" ) )
                inmResp = inm;
        }

        assertNull( "El inmueble no deber�a existir", inmResp );
    }

}
