/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CupiPosts.java,v 1.24 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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

import uniandes.cupi2.cupiPosts.interfaz.InterfazCupiPosts;
import uniandes.cupi2.cupiPosts.interfaz.PanelPost;

/**
 * Clase principal de la aplicaci�n.<br>
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
     * Las categor�as de posts
     */
    private ArrayList categorias;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de posts a partir de un archivo de configuraci�n <br>
     * @param archivoConfiguracion El archivo de configuraci�n con los datos de las categor�as de posts <br>
     * @throws PersistenciaException Error al cargar los datos del archivo de configuraci�n
     */
    public CupiPosts( String archivoConfiguracion ) throws PersistenciaException
    {
        File archivo = new File( archivoConfiguracion );
        // el archivo de configuraci�n que llega por par�metro est� serializado
        // por lo que no se verifica que exista

        try
        {
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
            categorias = ( ArrayList )ois.readObject( );
            ois.close( );
        }
        catch( Exception e )
        {
            // Se atrapan los tipos de excepci�n
            throw new PersistenciaException( "Error fatal: No es posible restaurar el estado del programa (" + e.getMessage( ) + ")" );
        }
        verificarInvariante( );
    }

    /**
     * Construye un nuevo manejador de posts vac�o<br>
     * <b> post: </b> Se inicializ� la lista de categor�as
     */
    public CupiPosts( )
    {
        categorias = new ArrayList( );
        verificarInvariante( );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo post. <br>
     * <b> post: </b> La lista de posts contiene un nuevo post.
     * @param categoria La categor�a a la cual se va agregar el post
     * @param ubicacion La ubicaci�n del anunciante
     * @param servicio El servicio del post
     * @param titulo El t�tulo del post
     * @param descripcion La descripci�n del post
     * @param tel El tel�fono del post
     * @param direccion La direcci�n del post
     * @throws PostIncompletoException Esta excepci�n se lanza si alg�n campo del formulario para crear el post no fue llenado
     * @throws DescripcionException Esta excepci�n se lanza si la descripci�n del post tienen una longitud mayor a 300 caracteres
     */
    public void crearPost( String categoria, String ubicacion, String servicio, String titulo, String descripcion, int tel, String direccion ) throws PostIncompletoException, DescripcionException
    {
        Categoria cate = darCategoria( categoria );
        String cau = "";
        if( categoria == null || categoria.equals( "" ) )
        {
            cau = cau + "Categoria";
        }
        if( ubicacion == null || ubicacion.equals( "" ) )
        {
            cau = cau + "Ubicacion";
        }
        if( servicio == null || servicio.equals( "" ) )
        {
            cau = cau + "Servicio";
        }
        if( titulo == null || titulo.equals( "" ) )
        {
            cau = cau + "Titulo";
        }
        if( descripcion == null || descripcion.equals( "" ) )
        {
            cau = cau + "Descripcion";
        }
        if( tel <= 0 )
        {
            cau = cau + "Telefono";
        }
        if( direccion == null || direccion.equals( "" ) )
        {
            cau = cau + "Direccion";
        }
        if( !cau.equals( "" ) )
        {
            throw new PostIncompletoException( "No se llenaron todos los campos", cau );
        }
        if( descripcion.length( ) > 300 )
        {
            throw new DescripcionException( "El tama�o de la descripci�n supera los l�mites", descripcion.length( ) );
        }
        else
        {
            cate.agregarPost( new Post( cate.darIdPost( ), servicio, titulo, descripcion, tel, direccion, ubicacion ) );
        }

    }
    /**
     * Crea una nueva categor�a si no existe una con el mismo nombre. <br>
     * <b> post: </b> Se agrego una nueva categor�a.
     * @param nombreCategoria El nombre de la nueva categor�a - nombreCategoria != null nombreCategoria != ""
     * @throws CategoriaExistenteException Excepci�n lanzada en caso que ya exista la categor�a
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
     * Retorna la lista de categor�as
     * @return categor�as La lista de categor�as de posts
     */
    public ArrayList darCategorias( )
    {
        return categorias;
    }

    /**
     * Busca la categor�a con el nombre dado por par�metro
     * @param nombreCategoria El nombre de la categor�a buscada
     * @return La categor�a con el nombre dado por par�metro, null en caso de no existir
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
     * Dado el nombre de una categor�a verifica si ya existe una con ese mismo nombre
     * @param nombreCategoria El nombre de la categor�a que se desea verificar que no existe - nombreCategoria != null
     * @return categoriaRepetida True si la categor�a ya existe, false de lo contrario
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
     * Genera un archivo de texto plano con la informaci�n de los posts seleccionados por el usuario <br>
     * siguiendo el formato descrito en el documento de descripci�n
     * @param pathArchivo Ruta en la que se desea guardar el archivo - pathArchivo != null
     * @param nombreArchivo Nombre con el que se desea guardar el reporte - nombreArchivo != null
     * @throws PersistenciaException Si ocurre alg�n error al generar el reporte
     */
    public void generarReportePostsSeleccionados( String pathArchivo, String nombreArchivo ) throws PersistenciaException
    {
        try
        {
            File archivo = new File( pathArchivo + File.separator + nombreArchivo );
            PrintWriter escritor = new PrintWriter( archivo );
            escritor.println( "REPORTE GENERADO POR CUPI-POST" );
            escritor.println( "A continuaci�n se presentan los post marcados" );
            escritor.println( "" );

            int N = categorias.size( );
            for( int i = 0; i < N; i++ )
            {
                Categoria cat = ( Categoria )categorias.get( i );
                int nN = cat.darPosts( ).size( );
                for( int j = 0; j < nN; j++ )
                {
                    Post p = ( Post )cat.darPosts( ).get( j );
                    if( p.darSeleccionado( ) )
                    {
                        escritor.println( "--------------------------------------------------------------" );
                        escritor.println( "El t�tulo : " + p.darTitulo( ) );
                        escritor.println( "Servicio : " + p.darServicio( ) );
                        escritor.println( "Ubicaci�n : " + p.darUbicacion( ) );
                        escritor.println( "Tel�fono : " + p.darTelefono( ) );
                        escritor.println( "Direcci�n : " + p.darDireccion( ) );
                        escritor.println( "Descripci�n : " + p.darDescripcion( ) );
                        escritor.println( "--------------------------------------------------------------" );
                    }
                }
            }
            escritor.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
    }
    /**
     * Se encarga de guardar las categor�as y posts de cupiPosts de forma serializada
     * @param ruta La ruta del archivo donde se guardan los datos
     * @throws PersistenciaException Esta excepci�n es generada en caso que haya alg�n problema al guardar los datos
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
     * Elimina un post dado su Id y la categor�a a la cual pertenece. <b> post: </b> El post fue eliminado de la categor�a dada.
     * @param categoria La categor�a a la cual pertenece el post
     * @param postId El id del post
     */
    public void eliminarPost( String categoria, int postId )
    {
        Categoria c = darCategoria( categoria );
        c.eliminarPost( postId );

    }

    /**
     * Carga de un archivo de texto plano categor�as y posts y los integra con los existentes. <br>
     * El archivo debe cumplir con el formato descrito en el documento de descripci�n
     * @param rutaArchivo La ruta en el sistema donde se encuentra el archivo de texto con le informaci�n - rutaArchivo != null
     * @throws FormatoArchivoException Si el archivo no existe o Si el archivo no cumple con el formato descrito en el documento de descripci�n
     */
    public void importarDatos( String rutaArchivo ) throws FormatoArchivoException
    {
        try
        {
            File archivo = new File( rutaArchivo );
            FileReader reader = new FileReader( archivo );
            BufferedReader lector = new BufferedReader( reader );

            // Lee la primera l�nea
            String numeroCat = lector.readLine( );
            while( numeroCat != null && numeroCat != "" )
            {
                // lee el n�mero de categor�as
                try
                {
                    int numCat = Integer.parseInt( numeroCat );
                    for( int i = 0; i < numCat; i++ )
                    {
                        // Nombre de la categoria i
                        String cat = lector.readLine( );
                        while( cat != null && cat != "" )
                        {
                            // Crea la categoria i, si esta no existe
                            // crearCategoria( cat );
                            if( darCategoria( cat ) == null )
                            {
                                categorias.add( new Categoria( cat ) );
                            }
                            String numeroPost = lector.readLine( );
                            while( numeroPost != null && numeroPost != "" )
                            {
                                try
                                {
                                    int numPost = Integer.parseInt( numeroPost );
                                    for( int j = 0; j < numPost; j++ )
                                    {
                                        String postt = lector.readLine( );
                                        while( postt != null && postt != "" )
                                        {
                                            String[] datos = postt.split( ";" );
                                            if( datos.length == 6 )
                                            {
                                                String ServicioJ = datos[ 0 ];
                                                String TituloJ = datos[ 1 ];
                                                String DescripcionJ = datos[ 2 ];
                                                int TelefonoJ = Integer.parseInt( datos[ 3 ] );
                                                String DireccionJ = datos[ 4 ];
                                                String UbicacionJ = datos[ 5 ];
                                                try
                                                {
                                                    crearPost( cat, UbicacionJ, ServicioJ, TituloJ, DescripcionJ, TelefonoJ, DireccionJ );
                                                }
                                                catch( PostIncompletoException e )
                                                {
                                                    throw new FormatoArchivoException( "La informacion del post esta incompleta" );
                                                }
                                                catch( DescripcionException e )
                                                {
                                                    throw new FormatoArchivoException( "La descripci�n del post excede la longitud permitida" );
                                                }
                                            }
                                        }
                                    }
                                }
                                catch( NumberFormatException e )
                                {
                                    throw new FormatoArchivoException( "No se encontro el n�mero de posts de la categoria " + cat );
                                }
                            }

                        }
                    }
                }

                catch( NumberFormatException e )
                {
                    throw new FormatoArchivoException( "En la primera linea no se encontro un n�mero" );
                }
            }
        }
        catch( FileNotFoundException e )
        {
            throw new FormatoArchivoException( "No se encontr� el archivo " );
        }
        catch( IOException e )
        {
            throw new FormatoArchivoException( "Error al importar los Datos" );
        }

    }
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
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