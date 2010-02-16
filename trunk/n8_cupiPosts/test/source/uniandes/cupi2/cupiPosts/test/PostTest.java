package uniandes.cupi2.cupiPosts.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiPosts.mundo.Post;

/**
 * Clase encargada de probar el correcto funcionamiento de los métodos de la clase Post
 * 
 */
public class PostTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase sobre la cual se realizaran las pruebas
     */
    public Post post;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo post con todos sus datos
     */
    public void setupEscenario( )
    {
        post = new Post( 1, Post.SERVICIO_ALQUILO, "Alquilo apartamento nuevo", "Busco apartamento de dos habitaciones", 310987865, "kra 7 #93-50", Post.BOGOTA );
    }

    /**
     * Verifica que al construir un post se asignen correctamente los atributos
     */
    public void testConstructor( )
    {
        setupEscenario( );
        assertEquals( "El servicio es incorrecto", Post.SERVICIO_ALQUILO, post.darServicio( ) );
        assertEquals( "El título es incorrecto", "Alquilo apartamento nuevo", post.darTitulo( ) );
        assertEquals( "La descripción es incorrecta", "Busco apartamento de dos habitaciones", post.darDescripcion( ) );
        assertEquals( "El teléfono es incorrecto", 310987865, post.darTelefono( ) );
        assertEquals( "La dirección es incorrecta", "kra 7 #93-50", post.darDireccion( ) );
        assertEquals( "La ubicación es incorrecta", Post.BOGOTA, post.darUbicacion( ) );
        assertEquals( "No debe estar seleccionado", false, post.darSeleccionado( ) );

    }

}
