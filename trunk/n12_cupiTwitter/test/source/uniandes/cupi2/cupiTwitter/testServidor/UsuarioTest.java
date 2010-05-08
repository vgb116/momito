package uniandes.cupi2.cupiTwitter.testServidor;

import junit.framework.TestCase;
import uniandes.cupi2.cupiTwitter.servidor.mundo.Usuario;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Usuario estén correctamente implementados
 */
public class UsuarioTest extends TestCase
{

    /**
     * Es la clase sobre la que se realizarán las pruebas
     */
    private Usuario usuario;

    /**
     * Construye un nuevo Elemento.
     */
    private void setupEscenario1( )
    {
        usuario = new Usuario( "calvarez", "Camilo", "Alvarez", "123456", 25, 30 );
    }

    /**
     * Prueba que se retorne de forma correcta el nombre de usuario.
     */
    public void testDarUsuario( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del nombre de usuario", usuario.darUsuario( ), "calvarez" );
    }

    /**
     * Prueba que se retorne de forma correcta el nombre de usuario en el método toString().
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del la representación string del usuario", usuario.darUsuario( ), "calvarez" );
    }

    /**
     * Prueba que se retorne de forma correcta el nombre del usuario.
     */
    public void testDarNombre( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del nombre de usuario", usuario.darNombre( ), "Camilo" );
    }

    /**
     * Prueba que se retorne de forma correcta los apellidos del usuario.
     */
    public void testDarApellidos( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del nombre de usuario", usuario.darApellidos( ), "Alvarez" );
    }

    /**
     * Prueba que se retorne de forma correcta el total de mensajes del usuario.
     */
    public void testDarTotalMensajes( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del nombre de usuario", usuario.darTotalMensajes( ), 25 );
    }

    /**
     * Prueba que se retorne de forma correcta el total de seguidores del usuario.
     */
    public void testDarTotalSeguidores( )
    {
        setupEscenario1( );
        assertEquals( "Error en el retorno del nombre de usuario", usuario.darTotalSeguidores( ), 30 );
    }

}
