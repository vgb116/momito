package uniandes.cupi2.fincaRaiz.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Clase para probar el correcto funcionamiento de los m�todos de la clase inmueble
 */
public class InmuebleTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Es la primera clase donde se har�n las pruebas
     */
    private Inmueble inmueble1;

    /**
     * Es la segunda clase donde se har�n las pruebas
     */
    private Inmueble inmueble2;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    
    /**
     * Construye los inmuebles 1 y 2 <br/>
     * <b>Datos del inmueble 1:</b> <br/>
     * TipoInmueble: Apartamento, Id: 1-B-A, TipoOferta: Venta, Ciudad: Bogot�, Barrio: �lamos, Direcci�n: Cra. 97 No. 51-51, Telefono: 123456, Tama�o: 123.2d, Precio: 567d,  Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     * <b>Datos del inmueble 2:</b> <br/>
     * TipoInmueble: Apartamento, Id: 2-C-C, TipoOferta: Arrendar, Ciudad: Cali, Barrio: Chico, Direcci�n: Cra. 75 No. 41-10, Telefono: 359845, Tama�o: 258741d, Precio: 98958d,Imagenes: ./data/ap1.jpg; ./data/ap2.jpg; ./data/ap3.jpg<br/>
     */
    public void setupEscenario1( )
    {
        ArrayList imagenes = new ArrayList( );
        imagenes.add( "./data/ap1.jpg" );
        imagenes.add( "./data/ap2.jpg" );
        imagenes.add( "./data/ap3.jpg" );
        inmueble1 = new Inmueble( Inmueble.TIPO_APARTAMENTO, "1-B-A", Inmueble.VENTA, "Bogot�", "�lamos", "Cra. 97 No. 51-51", "123456", 123.2d, 567d, imagenes );
        inmueble2 = new Inmueble( Inmueble.TIPO_APARTAMENTO, "2-C-C", Inmueble.ARRENDAR, "Cali", "Chico", "Cra. 75 No. 41-10", "359845", 258741d, 98958d, imagenes );
    }

    /**
     * Prueba 1 - Verifica el m�todo darTipoAferta. <br>
     * <b> M�todos a probar: </b> <br>
     * darTipoOferta. <br>
     * <b> Objetivo: </b> Probar que el m�todo darTipoOferta de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tipo de oferta del inmueble 1 es VENTA.
     */
    public void testDarTipoOferta( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tipo de oferta", inmueble1.darTipoOferta( ), Inmueble.VENTA );
    }

    /**
     * Prueba 2 - Verifica el m�todo darIdentificador. <br>
     * <b> M�todos a probar: </b> <br>
     * darIdentificador. <br>
     * <b> Objetivo: </b> Probar que el m�todo darIdentificador de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El identificador del inmueble 1 es 1-B-A.
     */
    public void testDarIdentificador( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del identificador del inmueble", inmueble1.darIdentificador( ), "1-B-A" );
    }

    /**
     * Prueba 3 - Verifica el m�todo darCiudad. <br>
     * <b> M�todos a probar: </b> <br>
     * darCiudad. <br>
     * <b> Objetivo: </b> Probar que el m�todo darCiudad de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La ciudad del inmueble 1 es Bogot�.
     */
    public void testDarCiudad( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor de la ciudad", inmueble1.darCiudad( ), "Bogot�" );
    }

    /**
     * Prueba 4 - Verifica el m�todo darBarrio. <br>
     * <b> M�todos a probar: </b> <br>
     * darBarrio. <br>
     * <b> Objetivo: </b> Probar que el m�todo darBarrio de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El barrio del inmueble 1 es �lamos.
     */
    public void testDarBarrio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del barrio", inmueble1.darBarrio( ), "�lamos" );
    }

    /**
     * Prueba 5 - Verifica el m�todo darDireccion. <br>
     * <b> M�todos a probar: </b> <br>
     * darDireccion. <br>
     * <b> Objetivo: </b> Probar que el m�todo darDireccion de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. La direcci�n del inmueble 1 es "Cra. 97 No. 51-51".
     */
    public void testDarDireccion( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor de la direcci�n", inmueble1.darDireccion( ), "Cra. 97 No. 51-51" );
    }

    /**
     * Prueba 6 - Verifica el m�todo darTelefono. <br>
     * <b> M�todos a probar: </b> <br>
     * darTelefono. <br>
     * <b> Objetivo: </b> Probar que el m�todo darTelefono de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tel�fono del inmueble 1 es 123456.
     */
    public void testDarTelefono( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tel�fono", inmueble1.darTelefono( ), "123456" );
    }

    /**
     * Prueba 7 - Verifica el m�todo darTamanio. <br>
     * <b> M�todos a probar: </b> <br>
     * darTamanio. <br>
     * <b> Objetivo: </b> Probar que el m�todo darTamanio de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tama�o del inmueble 1 es 123.2d
     */
    public void testDarTamanio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tama�o", inmueble1.darTamanio( ), 123.2d );
    }

    /**
     * Prueba 8 - Verifica el m�todo darPrecio. <br>
     * <b> M�todos a probar: </b> <br>
     * darPrecio. <br>
     * <b> Objetivo: </b> Probar que el m�todo darPrecio de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El precio del inmueble 1 es 567d
     */
    public void testDarPrecio( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del precio", inmueble1.darPrecio( ), 567d );
    }

    /**
     * Prueba 9 - Verifica el m�todo darImagenes. <br>
     * <b> M�todos a probar: </b> <br>
     * darImagenes. <br>
     * <b> Objetivo: </b> Probar que el m�todo darImegenes de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El inmueble 1 contiene 3 im�genes.
     */
    public void testDarImagenes( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar las im�genes del inmueble", inmueble1.darImagenes( ).size( ), 3 );
    }

    /**
     * Prueba 10 - Verifica el m�todo darTipoInmueble. <br>
     * <b> M�todos a probar: </b> <br>
     * darTipoInmueble. <br>
     * <b> Objetivo: </b> Probar que el m�todo darPrecio de la informaci�n correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. El tipo del inmueble 1 es Apartamento
     */
    public void testDarTipoInmueble( )
    {
        setupEscenario1( );
        assertEquals( "Error en el valor del tipo de inmueble", inmueble1.darTipoInmueble( ), "Apartamento" );
    }


    // Prueba 11
    // TODO Declarar, implementar y documentar el caso de prueba para el m�todo compararPorCiudad

    // Prueba 12
    // TODO Declarar, implementar y documentar el caso de prueba para el m�todo compararPorIdentificador

    // Prueba 13
    // TODO Declarar, implementar y documentar el caso de prueba para el m�todo compararPorPrecio
 
    // Prueba 14
    // TODO Declarar, implementar y documentar el caso de prueba para el m�todo compararPorTamanio

}
