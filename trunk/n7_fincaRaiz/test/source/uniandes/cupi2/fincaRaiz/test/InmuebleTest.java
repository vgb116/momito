package uniandes.cupi2.fincaRaiz.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Clase para probar el correcto funcionamiento de los métodos de la clase inmueble
 */
public class InmuebleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Es la primera clase donde se harán las pruebas
     */
    private Inmueble inmueble1;

    /**
     * Es la segunda clase donde se harán las pruebas
     */
    private Inmueble inmueble2;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Construye los inmuebles 1 y 2 <br/>
     * <b>Datos del inmueble 1:</b> <br/>
     * TipoInmueble: Apartamento, Id: 1-B-A, TipoOferta: Venta, Ciudad: Bogotá, Barrio: Álamos, Dirección: Cra. 97 No. 51-51, Telefono: 123456, Tamaño: 123.2d, Precio: 567d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 2:</b> <br/>
     * TipoInmueble: Apartamento, Id: 2-C-C, TipoOferta: Arrendar, Ciudad: Cali, Barrio: Chico, Dirección: Cra. 75 No. 41-10, Telefono: 359845, Tamaño: 258741d, Precio: 98958d,Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     */
    public void setupEscenario1( )
    {
        ArrayList imagenes = new ArrayList( );
        imagenes.add( "./data/ap1.jpg" );
        imagenes.add( "./data/ap2.jpg" );
        imagenes.add( "./data/ap3.jpg" );
        inmueble1 = new Inmueble( Inmueble.TIPO_APARTAMENTO, "1-B-A", Inmueble.VENTA, "Bogotá", "Álamos", "Cra. 97 No. 51-51", "123456", 123.2d, 567d, imagenes );
        inmueble2 = new Inmueble( Inmueble.TIPO_APARTAMENTO, "2-C-C", Inmueble.ARRENDAR, "Cali", "Chico", "Cra. 75 No. 41-10", "359845", 258741d, 98958d, imagenes );
    }

    /**
     * Prueba 1 - Verifica el método darTipoAferta. <br>
     * <b> Métodos a probar: </b> <br>
     * darTipoOferta. <br>
     * <b> Objetivo: </b> Probar que el método darTipoOferta de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tipo de oferta del inmueble 1 es VENTA.
     */
    public void testDarTipoOferta( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tipo de oferta", inmueble1.darTipoOferta( ), Inmueble.VENTA );
    }

    /**
     * Prueba 2 - Verifica el método darIdentificador. <br>
     * <b> Métodos a probar: </b> <br>
     * darIdentificador. <br>
     * <b> Objetivo: </b> Probar que el método darIdentificador de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El identificador del inmueble 1 es 1-B-A.
     */
    public void testDarIdentificador( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del identificador del inmueble", inmueble1.darIdentificador( ), "1-B-A" );
    }

    /**
     * Prueba 3 - Verifica el método darCiudad. <br>
     * <b> Métodos a probar: </b> <br>
     * darCiudad. <br>
     * <b> Objetivo: </b> Probar que el método darCiudad de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La ciudad del inmueble 1 es Bogotá.
     */
    public void testDarCiudad( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor de la ciudad", inmueble1.darCiudad( ), "Bogotá" );
    }

    /**
     * Prueba 4 - Verifica el método darBarrio. <br>
     * <b> Métodos a probar: </b> <br>
     * darBarrio. <br>
     * <b> Objetivo: </b> Probar que el método darBarrio de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El barrio del inmueble 1 es Álamos.
     */
    public void testDarBarrio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del barrio", inmueble1.darBarrio( ), "Álamos" );
    }

    /**
     * Prueba 5 - Verifica el método darDireccion. <br>
     * <b> Métodos a probar: </b> <br>
     * darDireccion. <br>
     * <b> Objetivo: </b> Probar que el método darDireccion de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La dirección del inmueble 1 es "Cra. 97 No. 51-51".
     */
    public void testDarDireccion( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor de la dirección", inmueble1.darDireccion( ), "Cra. 97 No. 51-51" );
    }

    /**
     * Prueba 6 - Verifica el método darTelefono. <br>
     * <b> Métodos a probar: </b> <br>
     * darTelefono. <br>
     * <b> Objetivo: </b> Probar que el método darTelefono de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El teléfono del inmueble 1 es 123456.
     */
    public void testDarTelefono( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del teléfono", inmueble1.darTelefono( ), "123456" );
    }

    /**
     * Prueba 7 - Verifica el método darTamanio. <br>
     * <b> Métodos a probar: </b> <br>
     * darTamanio. <br>
     * <b> Objetivo: </b> Probar que el método darTamanio de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tamaño del inmueble 1 es 123.2d
     */
    public void testDarTamanio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tamaño", inmueble1.darTamanio( ), 123.2d );
    }

    /**
     * Prueba 8 - Verifica el método darPrecio. <br>
     * <b> Métodos a probar: </b> <br>
     * darPrecio. <br>
     * <b> Objetivo: </b> Probar que el método darPrecio de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El precio del inmueble 1 es 567d
     */
    public void testDarPrecio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del precio", inmueble1.darPrecio( ), 567d );
    }

    /**
     * Prueba 9 - Verifica el método darImagenes. <br>
     * <b> Métodos a probar: </b> <br>
     * darImagenes. <br>
     * <b> Objetivo: </b> Probar que el método darImegenes de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El inmueble 1 contiene 3 imágenes.
     */
    public void testDarImagenes( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar las imágenes del inmueble", inmueble1.darImagenes( ).size( ), 3 );
    }

    /**
     * Prueba 10 - Verifica el método darTipoInmueble. <br>
     * <b> Métodos a probar: </b> <br>
     * darTipoInmueble. <br>
     * <b> Objetivo: </b> Probar que el método darPrecio de la información correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tipo del inmueble 1 es Apartamento
     */
    public void testDarTipoInmueble( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tipo de inmueble", inmueble1.darTipoInmueble( ), "Apartamento" );
    }

    /**
     * Prueba 11 - Verifica el método compararPorCiudad. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorCiudad. <br>
     * <b> Objetivo: </b> Probar que el método compararPorCiudad compare de la forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se espera que la ciudad del inmueble1 sea MAYOR a la del inmueble2
     * 2. Se espera que la ciudad del inmueble2 sea MENOR a la del inmueble1
     */
    public void testCompararPorCiudad( )
    {
        setupEscenario1( );
        
        //Compara la ciudad del inmueble1 con la del inmueble2
        assertTrue( "La ciudad del inmueble1 resultó menor" , inmueble1.compararPorCiudad( inmueble2 ) == 1 ); 
        
        //Compara la ciudad del inmueble2 con la del inmueble1
        assertTrue( "La ciudad del inmueble2 resultó mayor" , inmueble2.compararPorCiudad( inmueble1 ) == -1 );
        
    }

    /**
     * Prueba 12 - Verifica el método compararPorIdentificador. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorIdentificador. <br>
     * <b> Objetivo: </b> Probar que el método compararPorIdentificador compare de la forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se espera que el identificador del inmueble1 sea MAYOR al del inmueble2
     * 2. Se espera que el identificador del inmueble2 sea MENOR al del inmueble1
     */
    public void testCompararPorIdentificador( )
    {
        setupEscenario1( );
        
        //Compara el identificador del inmueble1 con la del inmueble2
        assertTrue( "El identificador del inmueble1 resultó menor" , inmueble1.compararPorIdentificador( inmueble2 ) == 1 ); 
        
        //Compara el identificador del inmueble2 con la del inmueble1
        assertTrue( "El identificador del inmueble2 resultó mayor" , inmueble2.compararPorIdentificador( inmueble1 ) == -1 ); 
    }
    
    /**
     * Prueba 13 - Verifica el método compararPorPrecio. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorPrecio. <br>
     * <b> Objetivo: </b> Probar que el método compararPorPrecio compare de la forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se espera que el precio del inmueble1 sea MENOR al del inmueble2
     * 2. Se espera que el precio del inmueble2 sea MAYOR al del inmueble1
     */
    public void testCompararPorPrecio( )
    {
        setupEscenario1( );
        
        //Compara el identificador del inmueble1 con la del inmueble2
        assertTrue( "El precio del inmueble1 resultó mayor" , inmueble1.compararPorPrecio( inmueble2 ) == -1 ); 
        
        //Compara el identificador del inmueble2 con la del inmueble1
        assertTrue( "El precio del inmueble2 resultó mayor" , inmueble2.compararPorPrecio( inmueble1 ) == 1 ); 
    }
    

    /**
     * Prueba 14 - Verifica el método compararPorTamanio. <br>
     * <b> Métodos a probar: </b> <br>
     * compararPorTamanio. <br>
     * <b> Objetivo: </b> Probar que el método compararPorTamanio compare de la forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se espera que el Tamanio del inmueble1 sea MENOR al del inmueble2
     * 2. Se espera que el Tamanio del inmueble2 sea MAYOR al del inmueble1
     */
    public void testCompararPorTamanio( )
    {
        setupEscenario1( );
        
        //Compara el identificador del inmueble1 con la del inmueble2
        assertTrue( "El Tamanio del inmueble1 resultó mayor" , inmueble1.compararPorTamanio( inmueble2 ) == -1 ); 
        
        //Compara el identificador del inmueble2 con la del inmueble1
        assertTrue( "El Tamanio del inmueble2 resultó mayor" , inmueble2.compararPorTamanio( inmueble1 ) == 1 );
       
    }    
    
}
