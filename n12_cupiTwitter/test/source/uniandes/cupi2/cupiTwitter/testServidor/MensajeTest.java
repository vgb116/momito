package uniandes.cupi2.cupiTwitter.testServidor;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.cupiTwitter.servidor.mundo.Mensaje;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Microblog estén correctamente implementados
 */
public class MensajeTest extends TestCase
{

    /**
     * El mensaje sobre el que se realizan las pruebas
     */
    private Mensaje mensaje;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Escenario 1 para probar el constructor del mensaje
     */
    private void setupEscenario1( )
    {
        mensaje = new Mensaje( "Comando 1" );
    }

    /**
     * Escenario 2 para probar el constructor del mensaje
     */
    private void setupEscenario2( )
    {
        ArrayList pa = new ArrayList( );
        pa.add( "a" );
        pa.add( "b" );
        pa.add( "c" );
        pa.add( "d" );
        mensaje = new Mensaje( "Comando 2", pa );
    }

    /**
     * Escenario 3 para probar el constructor del mensaje
     */
    private void setupEscenario3( )
    {
        String[] params = { "a", "b", "c", "d" };
        mensaje = new Mensaje( "Comando 3", params );
    }

    /**
     * Verifica el constructor de la clase Mensaje. <br>
     * <b> Métodos a probar: </b> <br>
     * Mensaje. <br>
     * <b> Objetivo: </b> Probar que el constructor Mensaje( String nComando, ArrayList nparametros ) es capaz de crear correctamente un Mensaje. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un Mensaje y pedir su información, esta debe ser retornada de forma correcta.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertEquals( "Construcción incorrecta.", "Comando 1", mensaje.darComando( ) );
    }

    /**
     * Verifica el constructor de la clase Mensaje. <br>
     * <b> Métodos a probar: </b> <br>
     * Mensaje. <br>
     * <b> Objetivo: </b> Probar que el constructor Mensaje( String nComando ) es capaz de crear correctamente un Mensaje. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un Mensaje y pedir su información, esta debe ser retornada de forma correcta.
     */
    public void testConstructor1( )
    {
        setupEscenario1( );
        assertEquals( "Construcción incorrecta.", mensaje.darComando( ), "Comando 1" );
    }

    /**
     * Verifica el constructor de la clase Mensaje. <br>
     * <b> Métodos a probar: </b> <br>
     * Mensaje. <br>
     * <b> Objetivo: </b> Probar que el constructor Mensaje( String nComando, String[] params ) es capaz de crear correctamente un Mensaje. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un Mensaje y pedir su información, esta debe ser retornada de forma correcta.
     */
    public void testConstructor2( )
    {
        setupEscenario2( );
        assertEquals( "Construcción incorrecta.", mensaje.darParametro( 0 ), "a" );
    }

    /**
     * Prueba que se retorne correctamente un parámetro en la posición dada
     */
    public void testDarParametro( )
    {
        setupEscenario2( );
        assertEquals( "El valor retornado para el parámetro 1 es incorrecto.", mensaje.darParametro( 1 ), "b" );
    }

    /**
     * Prueba que se retorne correctamente la lista de parámetros del mensaje
     */
    public void testDarParametros( )
    {
        setupEscenario3( );
        ArrayList pa = new ArrayList( );
        pa.add( "a" );
        pa.add( "b" );
        pa.add( "c" );
        pa.add( "d" );
        assertEquals( "El retorno de los parametros del mensaje es incorrecto.", mensaje.darParametros( ), pa );
    }

}
