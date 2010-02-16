/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiPostsTest.java,v 1.14 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPosts
 * Autor: Juan David Villa - 18-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPosts.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.CategoriaExistenteException;
import uniandes.cupi2.cupiPosts.mundo.CupiPosts;
import uniandes.cupi2.cupiPosts.mundo.DescripcionException;
import uniandes.cupi2.cupiPosts.mundo.FormatoArchivoException;
import uniandes.cupi2.cupiPosts.mundo.PersistenciaException;
import uniandes.cupi2.cupiPosts.mundo.Post;
import uniandes.cupi2.cupiPosts.mundo.PostIncompletoException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase CupiPosts estén correctamente implementados
 */
public class CupiPostsTest extends TestCase
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta para cargar el archivo de prueba
     */
    private final static String RUTA_PRUEBA = "./test/data/cupiPosts.data";
    
    /**
     * Ruta para la prueba de escritura
     */
    private final static String RUTA_PRUEBA_2 = "./test/data/pruebaExcritura.data";
    /**
     * Ruta para importar datos de un archivo de texto plano
     */
    private final static String RUTA_PRUEBA_3 = "./test/data/cargaTest.txt";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private CupiPosts cupiPosts;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo CupiPosts con dos categorías y un post asociado a cada una
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            cupiPosts = new CupiPosts( "" );
            cupiPosts.crearCategoria( "categoria1" );
            cupiPosts.crearCategoria( "categoria2" );
            cupiPosts.crearPost( "categoria1", "ubicacion1", "servicio1", "titulo1", "descripcion1", 1, "direccion1" );
            cupiPosts.crearPost( "categoria2", "ubicacion2", "servicio2", "titulo2", "descripcion2", 2, "direccion2" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería haber problemas de persistencia al dar una ruta de un archivo que no existe" );
        }
        catch( CategoriaExistenteException e )
        {
            fail( "No se están creando bien las categorías" );
        }
        catch( PostIncompletoException e )
        {
            fail( "No está creando bien los posts" );
        }
        catch( DescripcionException e )
        {
            fail( "No está creando bien los posts" );
        }
    }

    /**
     * Construye un nuevo CupiPosts a partir de un archivo de configuración
     */
    public void setupEscenario2( )
    {
        try
        {
            cupiPosts = new CupiPosts( RUTA_PRUEBA );
        }
        catch( PersistenciaException e )
        {
            fail( "Error al cargar el archivo de configuración" );
        }
    }

    /**
     * Construye un nuevo CupiPosts vacío
     */
    public void setupEscenario3( )
    {
        try
        {
            cupiPosts = new CupiPosts( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería haber problemas de persistencia al dar una ruta de un archivo que no existe" );
        }
    }

    /**
     * Este método prueba el constructor de la clase
     */
    public void testCupiPosts( )
    {
        setupEscenario1( );
        ArrayList categorias = cupiPosts.darCategorias( );
        assertTrue( "No se están creando correctamente las categorías.", categorias.size( ) == 2 );
        for( int i = 0; i < categorias.size( ); i++ )
        {
            Categoria categoria = ( Categoria )categorias.get( i );
            assertTrue( "No se están cargando correctamente las categorías.", categoria.darNombre( ).equals( "categoria" + ( i + 1 ) ) );
            ArrayList listaPosts = categoria.darPosts( );
            assertTrue( "No se están cargando correctamente las empresas.", listaPosts.size( ) == 1 );
        }

    }

    /**
     * Verifica que se cree una categoría y se añada a la colección sin que ocurran errores
     */
    public void testCrearCategoria( )
    {
        //
        // TODO: Completar según el enunciado y la documentación  

    }

    /**
     * Verifica que se lance una excepción al tratar de crear una categoría que ya existe
     */
    public void testCrearCategoria2( )
    {
        //
        // TODO: Completar según el enunciado y la documentación  
    }

    /**
     * Verifica que se agregue un nuevo post a la lista de posts de una categoría son que ocurran errores
     */
    public void testCrearPost( )
    {
        //
        // TODO: Completar según el enunciado y la documentación  
    }

    /**
     * Verifica que no se pueda crear un post con los datos incompletos
     */
    public void testCrearPost2( )
    {
        //
        // TODO: Completar según el enunciado y la documentación  
    }

    /**
     * Verifica que no se pueda crear un post con una descripción de mas de 300 caracteres
     */
    public void testCrearPost3( )
    {
        //
        // TODO: Completar según el enunciado y la documentación  
    }

    /**
     * Verifica que se elimine un post dado su identificador y el nombre de la categoría a la que pertenece.
     */
    public void testEliminarPost( )
    {
        setupEscenario1( );
        cupiPosts.eliminarPost( "categoria1", 1 );
        Categoria categoria = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        assertEquals( "Error al eliminar el post", 0, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se retornen todas las categorías de la clase cupiPosts
     */
    public void testDarCategorias( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar las categorías", 2, cupiPosts.darCategorias( ).size( ) );
    }

    /**
     * Verifica que se retorne una categoría dado su nombre
     */
    public void testDarCategoria( )
    {
        setupEscenario1( );

        Categoria categoria = cupiPosts.darCategoria( "categoria2" );
        assertEquals( "Error al retornar una categoría", 1, categoria.darPosts( ).size( ) );
        Post post = ( Post )categoria.darPosts( ).get( 0 );
        assertEquals( "Error al retornar una categoría", "titulo2", post.darTitulo( ) );

    }

    /**
     * Verifica que una categoría ya existe dado su nombre
     */
    public void testVerificarCategoriaExistente( )
    {
        setupEscenario1( );
        assertTrue( "Error al verificar una categoría existente", cupiPosts.existeCategoria( "categoria1" ) );
    }

    /**
     * Verifica que se carguen correctamente dos categorías de posts de un archivo serializado
     */
    public void testCargarDatos( )
    {
        setupEscenario1( );

        try
        {
            cupiPosts.guardarDatos( RUTA_PRUEBA );
        }
        catch( PersistenciaException e )
        {
            fail( "No debería ocurrir algún error" );
        }

        setupEscenario2( );
        assertEquals( "No se cargó correctamente las categorías", 2, cupiPosts.darCategorias( ).size( ) );
        Categoria categoria = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        assertEquals( "No se cargó correctamente las categoría", "categoria1", categoria.darNombre( ) );
        ArrayList posts = categoria.darPosts( );
        assertEquals( "No se cargó correctamente los posts", 1, posts.size( ) );
        Post post = ( Post )posts.get( 0 );
        assertTrue( "No se cargó correctamente el post", post.darTitulo( ).equals( "titulo1" ) && post.darUbicacion( ).equals( "ubicacion1" ) && post.darServicio( ).equals( "servicio1" ) );
    }

    /**
     * Verifica que se importen correctamente los datos de un archivo de texto plano
     */
    public void testImportarDatos( )
    {
        setupEscenario3( );
        try
        {
            cupiPosts.importarDatos( RUTA_PRUEBA_3 );
            ArrayList categorias = cupiPosts.darCategorias( );
            assertEquals( "El número de categorías cargadas es incorrecto", 2, categorias.size( ) );
            Categoria categoria1 = ( Categoria )categorias.get( 0 );
            Categoria categoria2 = ( Categoria )categorias.get( 1 );
            assertEquals( "No se cargaron correctamente los datos de las categorias", "Lapiceros", categoria1.darNombre( ) );
            assertEquals( "No se cargaron correctamente los datos de las categorias", "Libros", categoria2.darNombre( ) );
            assertEquals( "No se cargaron correctamente los posts de las categorías", 1, categoria1.darPosts( ).size( ) );
            Post post1 = ( Post )categoria1.darPosts( ).get( 0 );
            assertEquals( "Los posts tienen información incorrecta", "Lapicero Lamy", post1.darTitulo( ) );
            Post post2 = ( Post )categoria2.darPosts( ).get( 0 );
            assertEquals( "Los posts tienen información incorrecta", "Cuentos de Rafael Pombo", post2.darTitulo( ) );
        }
        catch( FormatoArchivoException e )
        {
            fail( "Error en la lectura del archivo de texto plano" );
        }

    }

    /**
     * Verifica que se guarden los datos de dos categorías
     */
    public void testGuardarDatos( )
    {
        setupEscenario1( );
        try
        {
            cupiPosts.guardarDatos( RUTA_PRUEBA_2 );
            CupiPosts cupiPosts2 = new CupiPosts( RUTA_PRUEBA_2 );
            assertEquals( "error al guardar datos", 2, cupiPosts2.darCategorias( ).size( ) );
        }
        catch( PersistenciaException e1 )
        {
            fail( "error al guardar el archivo" );
        }

    }

}