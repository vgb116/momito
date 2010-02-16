package uniandes.cupi2.cupiPosts.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.Post;

/**
 * Clase de prueba para verificar el funcionamiento de los métodos de la clase Categoría
 * 
 */
public class CategoriaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private Categoria categoria;

    /**
     * Post asociado a una categoría
     */
    private Post post1;
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva categoría
     * 
     */
    public void setupEscenario1( )
    {
        categoria = new Categoria( "categoría1" );
    }

    /**
     * Construye una nueva categoría y un nuevo post
     */
    public void setupEscenario2( )
    {
        categoria = new Categoria( "categoría2" );
        post1 = new Post( 1, "servicio1", "titulo1", "descripcion1", 1, "direccion1", "ubicacion1" );
    }

    /**
     * Verifica que se haya creado la categoría correctamente
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertEquals( "error al crear una categoría", "categoría1", categoria.darNombre( ) );
    }

    /**
     * Verifica que se agregue un post a la colección de posts de la categoría
     */
    public void testAgregarPost( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        assertEquals( "Error al agregar un post", 1, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se elimine un post dado su identificador de la colección de posts de la categoría
     */
    public void testEliminarPost( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        categoria.eliminarPost( 1 );
        assertEquals( "Error al eliminar un post", 0, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se retornen los posts asociados a la categoría
     */
    public void testDarPosts( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        assertEquals( "Error al dar los posts", 1, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que el toString de la clase categoría retorne el nombre de ésta
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertTrue( "Error en el toString de una categoría", "categoría1".contains( categoria.toString( ) ) );
    }

    /**
     * Verifica el correcto funcionamiento del método darIdPost
     */
    public void testDarIdPost( )
    {
        setupEscenario1( );
        assertEquals( "No se esta generando correctamente el id", -1, categoria.darIdPost( ) - categoria.darIdPost( ) );
    }

}
