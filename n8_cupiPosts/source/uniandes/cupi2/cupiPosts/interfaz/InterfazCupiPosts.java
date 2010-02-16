/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCupiPosts.java,v 1.23 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPosts
 * Autor: Juan David Villa - 18-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPosts.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.CategoriaExistenteException;
import uniandes.cupi2.cupiPosts.mundo.CupiPosts;
import uniandes.cupi2.cupiPosts.mundo.DescripcionException;
import uniandes.cupi2.cupiPosts.mundo.FormatoArchivoException;
import uniandes.cupi2.cupiPosts.mundo.PersistenciaException;
import uniandes.cupi2.cupiPosts.mundo.PostIncompletoException;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCupiPosts extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con la ruta donde se encuentra el archivo de persistencia
     */
    public final static String RUTA_ARCHIVO_CONFIGURACION = "./data/cupiPosts.data";

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiPosts cupiPosts;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con el menú de opciones
     */
    private PanelMenu panelMenu;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;
    /**
     * Panel con los posts de una categoría
     */
    private PanelCategoria panelCategoria;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal de la aplicación <br>
     * <b> post: </b> Inicializa cupiPost con el archivo que se encuentra en la ruta del archivo de configuración <br>
     * Si ocurre algún error inicializando cupiPost, crear un cupiPost vació
     */
    public InterfazCupiPosts( )
    {
        // Crea la clase principal
        try
        {
            cupiPosts = new CupiPosts( RUTA_ARCHIVO_CONFIGURACION );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error de persistencia", JOptionPane.ERROR_MESSAGE );
            cupiPosts = new CupiPosts( );
        }

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 800, 600 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "CupiPosts" );
        setResizable( false );
        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );
        panelCategoria = new PanelCategoria( this );
        panelCategoria.setBorder( new TitledBorder( "Posts" ) );
        panelMenu = new PanelMenu( this );
        panelMenu.actualizarCategorias( cupiPosts.darCategorias( ) );

        JPanel panelCentral = new JPanel( );
        panelCentral.setLayout( new BorderLayout( ) );
        panelCentral.add( BorderLayout.NORTH, panelMenu );
        panelCentral.add( BorderLayout.CENTER, panelCategoria );
        add( panelCentral, BorderLayout.CENTER );
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        // Centrar la ventana
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método crea un nuevo post
     * @param categoria La categoría a la cual se va agregar el post
     * @param ubicacion La ubicación del anunciante
     * @param servicio El servicio del post
     * @param titulo El título del post
     * @param descripcion La descripción del post
     * @param tel El teléfono del post
     * @param direccion La dirección del post
     * @throws DescripcionException Está excepción se lanza si la descripción del post tiene una longitud mayor a 300 caracteres
     * @throws PostIncompletoException Está excepción se lanza si falta algún campo del post por llenar
     */
    public void crearPost( String categoria, String ubicacion, String servicio, String titulo, String descripcion, int tel, String direccion ) throws DescripcionException, PostIncompletoException
    {
        cupiPosts.crearPost( categoria, ubicacion, servicio, titulo, descripcion, tel, direccion );
    }

    /**
     * Despliega el dialogo para crear un nuevo post
     */
    public void mostrarDialogoCrearPost( )
    {
        DialogoCrearPost dialogoNuevoPost = new DialogoCrearPost( this );
        dialogoNuevoPost.setVisible( true );

    }

    /**
     * Crea una nueva categoría con el nombre dado como parámetro
     * @param nombreCategoria El nombre de la nueva categoría
     * @return categorías El arreglo de categorías de posts
     * @throws CategoriaExistenteException Excepción lanzada en caso que ya exista la categoría
     */
    public ArrayList crearCategoria( String nombreCategoria ) throws CategoriaExistenteException
    {

        cupiPosts.crearCategoria( nombreCategoria );
        ArrayList categorias = cupiPosts.darCategorias( );
        return categorias;
    }

    /**
     * Refresca el panel categoría para que muestre los nuevos posts creados
     */
    public void actualizarPanelCategoria( )
    {
        Categoria cat = panelMenu.darCategoriaSeleccionada( );
        if( cat != null )
        {
            ArrayList posts = panelMenu.darCategoriaSeleccionada( ).darPosts( );
            panelCategoria.actualizar( posts );
        }

    }

    /**
     * Retorna las categorías de posts
     * @return El arreglo con las categorías
     */
    public ArrayList darCategorias( )
    {
        ArrayList categorias = cupiPosts.darCategorias( );
        return categorias;

    }

    /**
     * Genera un reporte con los datos de los posts seleccionados
     * @param pathArchivo La ruta donde va a quedar el archivo
     * @param nombreArchivo El nombre que va a tener el archivo
     */
    public void escribirReportePostsSeleccionados( String pathArchivo, String nombreArchivo )
    {
        try
        {
            cupiPosts.generarReportePostsSeleccionados( pathArchivo, nombreArchivo );
            JOptionPane.showMessageDialog( this, "Reporte Generado", "CupiPosts", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error al cargar datos", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Guarda las categorías y los posts de CupiPosts en un archivo
     */
    public void dispose( )
    {
        //
        // TODO: Este método es llamado al cerrar la aplicación
        // Debe :
        // Debe pedirle a cupiPosts que se serialice en el archivo cuya dirección se encuentra en la constante RUTA_ARCHIVO_CONFIGURACION
        // Al finalizar el método, asegúrese de llamar el método dispose de la clase JFrame
        
        // Maneje los mensajes de error debidos a todas las excepciones que se puedan generar
        //  - Debe preguntar al usuario si desea cerrar la aplicación sin salvar la información dado que ocurrió un error
    }

    /**
     * El nombre de la categoría que está seleccionada
     * @return El nombre de la categoría seleccionada
     */
    public String darCategoriaSeleccionada( )
    {
        Categoria cat = panelMenu.darCategoriaSeleccionada( );
        return cat.darNombre( );
    }

    /**
     * Elimina un post dado su identificador y la categoría a la cual pertenece
     * @param categoriaSeleccionada El nombre de la categoría a la cual está asociada el post
     * @param postId El id del del post que se quiere eliminar
     */
    public void eliminarPost( String categoriaSeleccionada, int postId )
    {
        cupiPosts.eliminarPost( categoriaSeleccionada, postId );

    }

    /**
     * Carga de un archivo de texto plano datos de categorías y posts
     * @param rutaArchivo La ruta donde se encuentran los datos
     */
    public void cargarDatos( String rutaArchivo )
    {
        try
        {
            cupiPosts.importarDatos( rutaArchivo );
        }
        catch( FormatoArchivoException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error al cargar datos", JOptionPane.ERROR_MESSAGE );
        }

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupiPosts.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupiPosts.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupiPosts interfaz = new InterfazCupiPosts( );
        interfaz.setVisible( true );
    }

}