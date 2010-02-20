/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiPosts.java,v 1.24 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPosts
 * Autor: Juan David Villa - 18-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPosts.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase principal de la aplicación.<br>
 * <b>inv: </b> <br>
 * categorias != null <br>
 * no existen dos o mas categorias con el mismo nombre <br>
 */

public class CupiPosts
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Las categorías de posts
     */
    private ArrayList categorias;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de posts a partir de un archivo de configuración <br>
     * @param archivoConfiguracion El archivo de configuración con los datos de las categorías de posts <br>
     * @throws PersistenciaException Error al cargar los datos del archivo de configuración
     */
    public CupiPosts( String archivoConfiguracion ) throws PersistenciaException
    {
        File archivo = new File( archivoConfiguracion );
        // el archivo de configuración que llega por parámetro está serializado
        // por lo que no se verifica que exista

        try
        {
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
            categorias = ( ArrayList )ois.readObject( );
            ois.close( );
        }
        catch( Exception e )
        {
            // Se atrapan los tipos de excepción
            throw new PersistenciaException( "Error fatal: No es posible restaurar el estado del programa (" + e.getMessage( ) + ")" );
        }
        verificarInvariante( );
    }

    /**
     * Construye un nuevo manejador de posts vacío<br>
     * <b> post: </b> Se inicializó la lista de categorías
     */
    public CupiPosts( )
    {
        categorias = new ArrayList( );
        verificarInvariante( );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo post. <br>
     * <b> post: </b> La lista de posts contiene un nuevo post.
     * @param categoria La categoría a la cual se va agregar el post
     * @param ubicacion La ubicación del anunciante
     * @param servicio El servicio del post
     * @param titulo El título del post
     * @param descripcion La descripción del post
     * @param tel El teléfono del post
     * @param direccion La dirección del post
     * @throws PostIncompletoException Esta excepción se lanza si algún campo del formulario para crear el post no fue llenado
     * @throws DescripcionException Esta excepción se lanza si la descripción del post tienen una longitud mayor a 300 caracteres
     */
    public void crearPost( String categoria, String ubicacion, String servicio, String titulo, String descripcion, int tel, String direccion ) throws PostIncompletoException, DescripcionException
    {
        Categoria cate = darCategoria( categoria );
        cate.agregarPost( new Post( cate.darIdPost( ), servicio, titulo, descripcion, tel, direccion, ubicacion ) );
    }

    /**
     * Crea una nueva categoría si no existe una con el mismo nombre. <br>
     * <b> post: </b> Se agrego una nueva categoría.
     * @param nombreCategoria El nombre de la nueva categoría - nombreCategoria != null nombreCategoria != ""
     * @throws CategoriaExistenteException Excepción lanzada en caso que ya exista la categoría
     */
    public void crearCategoria( String nombreCategoria ) throws CategoriaExistenteException
    {
        // Si existe una categoria con nombre: nombreCategoria:
        if( darCategoria( nombreCategoria ) != null )
        {
            throw new CategoriaExistenteException( "La categoria " + nombreCategoria + " ya existe en CupiPost", nombreCategoria );
        }
        else
        {
            categorias.add( new Categoria( nombreCategoria ) );
        }
        verificarInvariante( );
    }

    /**
     * Retorna la lista de categorías
     * @return categorías La lista de categorías de posts
     */
    public ArrayList darCategorias( )
    {
        return categorias;
    }

    /**
     * Busca la categoría con el nombre dado por parámetro
     * @param nombreCategoria El nombre de la categoría buscada
     * @return La categoría con el nombre dado por parámetro, null en caso de no existir
     */
    public Categoria darCategoria( String nombreCategoria )
    {
        boolean encontro = false;
        Categoria categoria = null;
        for( int i = 0; i < categorias.size( ) && !encontro; i++ )
        {
            Categoria temp = ( Categoria )categorias.get( i );
            String nombreTemp = temp.darNombre( );
            if( nombreCategoria.equals( nombreTemp ) )
            {
                encontro = true;
                categoria = temp;
            }
        }
        return categoria;
    }

    /**
     * Dado el nombre de una categoría verifica si ya existe una con ese mismo nombre
     * @param nombreCategoria El nombre de la categoría que se desea verificar que no existe - nombreCategoria != null
     * @return categoriaRepetida True si la categoría ya existe, false de lo contrario
     */
    public boolean existeCategoria( String nombreCategoria )
    {
        boolean categoriaRepetida = false;
        for( int i = 0; i < categorias.size( ) && !categoriaRepetida; i++ )
        {
            Categoria temp = ( Categoria )categorias.get( i );
            if( nombreCategoria.equals( temp.darNombre( ) ) )
            {
                categoriaRepetida = true;
            }
        }
        return categoriaRepetida;
    }

    /**
     * Genera un archivo de texto plano con la información de los posts seleccionados por el usuario <br>
     * siguiendo el formato descrito en el documento de descripción
     * @param pathArchivo Ruta en la que se desea guardar el archivo - pathArchivo != null
     * @param nombreArchivo Nombre con el que se desea guardar el reporte - nombreArchivo != null
     * @throws PersistenciaException Si ocurre algún error al generar el reporte
     */
    public void generarReportePostsSeleccionados( String pathArchivo, String nombreArchivo ) throws PersistenciaException
    {
        File archivo = new File( "pathArchivo" + File.separator + "nombreArchivo" );
        PrintWriter escritor = new PrintWriter( archivo );
        escritor.println( "REPORTE GENERADO POR CUPI-POST" );
        escritor.println( "A continuación se presentan los post marcados" );
        escritor.println( "" );
        escritor.println( "--------------------------------------------------------------" );
        escritor.println( "Titulo: " +  );

    }

    /**
     * Se encarga de guardar las categorías y posts de cupiPosts de forma serializada
     * @param ruta La ruta del archivo donde se guardan los datos
     * @throws PersistenciaException Esta excepción es generada en caso que haya algún problema al guardar los datos
     */
    public void guardarDatos( String ruta ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( ruta ) );
            oos.writeObject( categorias );
            oos.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al guardar: " + e.getMessage( ) );
        }
    }

    /**
     * Elimina un post dado su Id y la categoría a la cual pertenece. <b> post: </b> El post fue eliminado de la categoría dada.
     * @param categoria La categoría a la cual pertenece el post
     * @param postId El id del post
     */
    public void eliminarPost( String categoria, int postId )
    {
        Categoria c = darCategoria( categoria );
        c.eliminarPost( postId );

    }

    /**
     * Carga de un archivo de texto plano categorías y posts y los integra con los existentes. <br>
     * El archivo debe cumplir con el formato descrito en el documento de descripción
     * @param rutaArchivo La ruta en el sistema donde se encuentra el archivo de texto con le información - rutaArchivo != null
     * @throws FormatoArchivoException Si el archivo no existe o Si el archivo no cumple con el formato descrito en el documento de descripción
     */
    public void importarDatos( String rutaArchivo ) throws FormatoArchivoException
    {
        //
        // TODO: Completar según enunciado

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que no hayan categorias con el mismo nombre
     * @return True si todas las categorias tienen nombre distinto, false en caso contrario
     */
    private boolean categoriasNombreUnico( )
    {
        int N = categorias.size( );
        boolean resp = true;
        for( int i = 0; i < N; i++ )
        {
            Categoria cI = ( Categoria )categorias.get( i );
            String nombreI = cI.darNombre( );
            for( int j = i + 1; j < N; j++ )
            {
                Categoria cJ = ( Categoria )categorias.get( j );
                String nombreJ = cJ.darNombre( );
                if( nombreJ.equalsIgnoreCase( nombreI ) )
                {
                    resp = false;
                }
            }
        }
        return resp;
    }

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertionError. <br>
     * <b>inv: </b> categorias != null <br>
     * no existen dos o mas categorias con el mismo nombre <br>
     */
    private void verificarInvariante( )
    {
        assert categorias != null : "No existen las categorias";
        assert categoriasNombreUnico( ) : "Existen dos categorias con el mismo nombre";
    }
}