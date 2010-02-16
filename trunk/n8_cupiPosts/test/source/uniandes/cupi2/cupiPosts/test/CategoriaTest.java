package uniandes.cupi2.cupiPosts.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.Post;

/**
 * Clase de prueba para verificar el funcionamiento de los m�todos de la clase Categor�a
 * 
 */
public class CategoriaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se har�n las pruebas
     */
    private Categoria categoria;

    /**
     * Post asociado a una categor�a
     */
    private Post post1;
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva categor�a
     * 
     */
    public void setupEscenario1( )
    {
        categoria = new Categoria( "categor�a1" );
    }

    /**
     * Construye una nueva categor�a y un nuevo post
     */
    public void setupEscenario2( )
    {
        categoria = new Categoria( "categor�a2" );
        post1 = new Post( 1, "servicio1", "titulo1", "descripcion1", 1, "direccion1", "ubicacion1" );
    }

    /**
     * Verifica que se haya creado la categor�a correctamente
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertEquals( "error al crear una categor�a", "categor�a1", categoria.darNombre( ) );
    }

    /**
     * Verifica que se agregue un post a la colecci�n de posts de la categor�a
     */
    public void testAgregarPost( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        assertEquals( "Error al agregar un post", 1, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se elimine un post dado su identificador de la colecci�n de posts de la categor�a
     */
    public void testEliminarPost( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        categoria.eliminarPost( 1 );
        assertEquals( "Error al eliminar un post", 0, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se retornen los posts asociados a la categor�a
     */
    public void testDarPosts( )
    {
        setupEscenario2( );
        categoria.agregarPost( post1 );
        assertEquals( "Error al dar los posts", 1, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que el toString de la clase categor�a retorne el nombre de �sta
     */
    public void testToString( )
    {
        setupEscenario1( );
        assertTrue( "Error en el toString de una categor�a", "categor�a1".contains( categoria.toString( ) ) );
    }

    /**
     * Verifica el correcto funcionamiento del m�todo darIdPost
     */
    public void testDarIdPost( )
    {
        setupEscenario1( );
        assertEquals( "No se esta generando correctamente el id", -1, categoria.darIdPost( ) - categoria.darIdPost( ) );
    }

}
