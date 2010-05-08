package uniandes.cupi2.cupiTwitter.testCliente;

import junit.framework.TestCase;
import uniandes.cupi2.cupiTwitter.cliente.mundo.MicroBlog;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Microblog est�n correctamente implementados
 */
public class MicroBlogTest extends TestCase
{

    /**
     * El microblog sobre el que se realizan las pruebas
     */
    private MicroBlog microblog;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        microblog = new MicroBlog( "Camilo", "Texto de prueba para el microblog", "01-01-2009 10:00" );
    }

    /**
     * Prueba que se construya correctamente el elemento.
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertEquals( "Construcci�n incorrecta.", "Camilo", microblog.darUsuario( ) );
        assertEquals( "Construcci�n incorrecta.", "Texto de prueba para el microblog", microblog.darTexto( ) );
        assertEquals( "Construcci�n incorrecta.", "01-01-2009 10:00", microblog.darFecha( ) );
    }

    /**
     * Prueba que se retorne correctamente la fecha.
     */
    public void testDarFecha( )
    {
        setupEscenario1( );
        assertEquals( "El retorno de la fecha del microblog es incorrecto.", microblog.darFecha( ), "01-01-2009 10:00" );
    }

    /**
     * Prueba que se retorne correctamente el texto.
     */
    public void testDarTexto( )
    {
        setupEscenario1( );
        assertEquals( "El retorno del texto del microblog es incorrecto.", microblog.darTexto( ), "Texto de prueba para el microblog" );
    }

    /**
     * Prueba que se retorne correctamente el nombre de usuario.
     */
    public void testDarUsuario( )
    {
        setupEscenario1( );
        assertEquals( "El retorno del nombre de usuario es incorrecto.", microblog.darUsuario( ), "Camilo" );
    }

}
