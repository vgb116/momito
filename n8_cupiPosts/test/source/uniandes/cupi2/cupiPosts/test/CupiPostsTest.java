/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiPostsTest.java,v 1.14 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPosts
 * Autor: Juan David Villa - 18-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPosts.test;

import java.io.File;
import java.io.IOException;
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
 * Esta es la clase usada para verificar que los m�todos de la clase CupiPosts est�n correctamente implementados
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
     * Es la clase donde se har�n las pruebas
     */
    private CupiPosts cupiPosts;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo CupiPosts con dos categor�as y un post asociado a cada una
     * 
     */
    private void setupEscenario1( )
    {

        try
        {
            cupiPosts = new CupiPosts( );
            cupiPosts.crearCategoria( "categoria1" );
            cupiPosts.crearCategoria( "categoria2" );
            cupiPosts.crearPost( "categoria1", "ubicacion1", "servicio1", "titulo1", "descripcion1", 1, "direccion1" );
            cupiPosts.crearPost( "categoria2", "ubicacion2", "servicio2", "titulo2", "descripcion2", 2, "direccion2" );
        }
//        catch( PersistenciaException e )
//        {
//            fail( "No deber�a haber problemas de persistencia al dar una ruta de un archivo que no existe" );
//        }
        catch( CategoriaExistenteException e )
        {
            fail( "No se est�n creando bien las categor�as" );
        }
        catch( PostIncompletoException e )
        {
            fail( "No est� creando bien los posts" );
        }
        catch( DescripcionException e )
        {
            fail( "No est� creando bien los posts" );
        }
    }

    /**
     * Construye un nuevo CupiPosts a partir de un archivo de configuraci�n
     */
    public void setupEscenario2( )
    {
        try
        {
            cupiPosts = new CupiPosts( RUTA_PRUEBA );
        }
        catch( PersistenciaException e )
        {
            fail( "Error al cargar el archivo de configuraci�n" );
        }
    }

    /**
     * Construye un nuevo CupiPosts vac�o
     */
    public void setupEscenario3( )
    {
        // cupiPosts = new CupiPosts( );
        try
        {
            cupiPosts = new CupiPosts( "" );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a haber problemas de persistencia al dar una ruta de un archivo que no existe" );
        }
    }

    /**
     * Este m�todo prueba el constructor de la clase
     */
    public void testCupiPosts( )
    {
        setupEscenario1( );
        ArrayList categorias = cupiPosts.darCategorias( );
        assertTrue( "No se est�n creando correctamente las categor�as.", categorias.size( ) == 2 );
        for( int i = 0; i < categorias.size( ); i++ )
        {
            Categoria categoria = ( Categoria )categorias.get( i );
            assertTrue( "No se est�n cargando correctamente las categor�as.", categoria.darNombre( ).equals( "categoria" + ( i + 1 ) ) );
            ArrayList listaPosts = categoria.darPosts( );
            assertTrue( "No se est�n cargando correctamente las empresas.", listaPosts.size( ) == 1 );
        }

    }

    /**
     * Verifica que se cree una categor�a y se a�ada a la colecci�n sin que ocurran errores
     */
    public void testCrearCategoria( )
    {
        setupEscenario1( );
        try
        {
            // Se agrega la categor�a
            cupiPosts.crearCategoria( "categoriaN" );
            assertEquals( "La cateog�a no fue agregada correctamente", 3, cupiPosts.darCategorias( ).size( ) );

            // Se prueba que se haya agregado de forma correcta
            Categoria cat = ( Categoria )cupiPosts.darCategoria( "categoriaN" );
            assertNotNull( "No se agreg� la categoria de forma Correcta, no fue encontrada", cat );
            assertEquals( "No se agreg� la categoria de forma Correcta, no se encontro el nombre", "categoriaN", cat.darNombre( ) );

        }
        catch( CategoriaExistenteException e )
        {
            fail( "No deber�a ocurrir ning�n error. Se gener� excepci�n: " + e.getMessage( ) );
        }

    }

    /**
     * Verifica que se lance una excepci�n al tratar de crear una categor�a que ya existe
     */
    public void testCrearCategoria2( )
    {
        setupEscenario1( );
        int tamanio = cupiPosts.darCategorias( ).size( );
        // Se agrega una categoria ya existente
        Categoria cat = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        String nCat = cat.darNombre( );
        try
        {
            cupiPosts.crearCategoria( nCat );
            fail( "No deber�a crearse la categoria, pues ya existe una con el mismo nombre" );
        }
        catch( CategoriaExistenteException e )
        {
            int nTamanio = cupiPosts.darCategorias( ).size( );
            assertEquals( "Ha cambiado el tama�o de las categorias", tamanio, nTamanio );
        }
    }

    /**
     * Verifica que se agregue un nuevo post a la lista de posts de una categor�a sin que ocurran errores
     */
    public void testCrearPost( )
    {
        setupEscenario1( );
        try
        {
            cupiPosts.crearPost( "categoria1", "ubicacionX", "servicioX", "tituloX", "descripcionX", 123456, "direccionx" );
            Categoria ca = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
            int N = ca.darPosts( ).size( );
            assertEquals( "El post no fue agregado correctamente", 2, N );

            Post po = ( Post )ca.darPosts( ).get( 1 );
            assertNotNull( "El post no fue encontrado", po );
            assertEquals( "Los atributos del post no fueron agregados correctamente", 123456, po.darTelefono( ) );

        }
        catch( PostIncompletoException e )
        {
            fail( "No se agrego correctamente el post " + e.getMessage( ) );
        }
        catch( DescripcionException er )
        {
            fail( "No se creo correctamente el post " + er.getMessage( ) );
        }
    }

    /**
     * Verifica que no se pueda crear un post con los datos incompletos
     */
    public void testCrearPost2( )
    {
        setupEscenario1( );
        Categoria cat = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        int N = cat.darPosts( ).size( );

        try
        {
            cupiPosts.crearPost( "categoria1", "", "", "", "", 89, "" );
            fail( "No se debi� haber creado el post" );
        }
        catch( PostIncompletoException e )
        {
            int nN = cat.darPosts( ).size( );
            assertEquals( "Cambio el tama�o de los post", N, nN );
        }
        catch( DescripcionException e )
        {
            fail( "Se intent� crear un post defectuoso" );
        }
    }

    /**
     * Verifica que no se pueda crear un post con una descripci�n de mas de 300 caracteres
     */
    public void testCrearPost3( )
    {
        setupEscenario1( );
        Categoria cat = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        int N = cat.darPosts( ).size( );
        String desc = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        try
        {
            cupiPosts.crearPost( "categoria1", "ubicacionX", "servicioX", "tituloX", desc, 123456, "direccionx" );
            fail( "Se agrego un post con una descripci�n de longitud mayor a 300" );
        }
        catch( PostIncompletoException e )
        {
            fail( "Se intent� crear un post defectuoso" );
        }
        catch( DescripcionException e )
        {
            int nN = cat.darPosts( ).size( );
            assertEquals( "Cambio el tama�o de los post", N, nN );
        }
    }

    /**
     * Verifica que se elimine un post dado su identificador y el nombre de la categor�a a la que pertenece.
     */
    public void testEliminarPost( )
    {
        setupEscenario1( );
        cupiPosts.eliminarPost( "categoria1", 1 );
        Categoria categoria = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        assertEquals( "Error al eliminar el post", 0, categoria.darPosts( ).size( ) );
    }

    /**
     * Verifica que se retornen todas las categor�as de la clase cupiPosts
     */
    public void testDarCategorias( )
    {
        setupEscenario1( );
        assertEquals( "Error al dar las categor�as", 2, cupiPosts.darCategorias( ).size( ) );
    }

    /**
     * Verifica que se retorne una categor�a dado su nombre
     */
    public void testDarCategoria( )
    {
        setupEscenario1( );

        Categoria categoria = cupiPosts.darCategoria( "categoria2" );
        assertEquals( "Error al retornar una categor�a", 1, categoria.darPosts( ).size( ) );
        Post post = ( Post )categoria.darPosts( ).get( 0 );
        assertEquals( "Error al retornar una categor�a", "titulo2", post.darTitulo( ) );

    }

    /**
     * Verifica que una categor�a ya existe dado su nombre
     */
    public void testVerificarCategoriaExistente( )
    {
        setupEscenario1( );
        assertTrue( "Error al verificar una categor�a existente", cupiPosts.existeCategoria( "categoria1" ) );
    }

    /**
     * Verifica que se carguen correctamente dos categor�as de posts de un archivo serializado
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
            fail( "No deber�a ocurrir alg�n error" );
        }

        setupEscenario2( );
        assertEquals( "No se carg� correctamente las categor�as", 2, cupiPosts.darCategorias( ).size( ) );
        Categoria categoria = ( Categoria )cupiPosts.darCategorias( ).get( 0 );
        assertEquals( "No se carg� correctamente las categor�a", "categoria1", categoria.darNombre( ) );
        ArrayList posts = categoria.darPosts( );
        assertEquals( "No se carg� correctamente los posts", 1, posts.size( ) );
        Post post = ( Post )posts.get( 0 );
        assertTrue( "No se carg� correctamente el post", post.darTitulo( ).equals( "titulo1" ) && post.darUbicacion( ).equals( "ubicacion1" ) && post.darServicio( ).equals( "servicio1" ) );
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
            assertEquals( "El n�mero de categor�as cargadas es incorrecto", 2, categorias.size( ) );
            Categoria categoria1 = ( Categoria )categorias.get( 0 );
            Categoria categoria2 = ( Categoria )categorias.get( 1 );
            assertEquals( "No se cargaron correctamente los datos de las categorias", "Lapiceros", categoria1.darNombre( ) );
            assertEquals( "No se cargaron correctamente los datos de las categorias", "Libros", categoria2.darNombre( ) );
            assertEquals( "No se cargaron correctamente los posts de las categor�as", 1, categoria1.darPosts( ).size( ) );
            Post post1 = ( Post )categoria1.darPosts( ).get( 0 );
            assertEquals( "Los posts tienen informaci�n incorrecta", "Lapicero Lamy", post1.darTitulo( ) );
            Post post2 = ( Post )categoria2.darPosts( ).get( 0 );
            assertEquals( "Los posts tienen informaci�n incorrecta", "Cuentos de Rafael Pombo", post2.darTitulo( ) );
        }
        catch( FormatoArchivoException e )
        {
            fail( "Error en la lectura del archivo de texto plano" );
        }

    }

    /**
     * Verifica que se guarden los datos de dos categor�as
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