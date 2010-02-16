/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCupiPosts.java,v 1.23 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Esta es la ventana principal de la aplicaci�n.
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
     * Constante para la serializaci�n
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
     * Panel con el men� de opciones
     */
    private PanelMenu panelMenu;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;
    /**
     * Panel con los posts de una categor�a
     */
    private PanelCategoria panelCategoria;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal de la aplicaci�n <br>
     * <b> post: </b> Inicializa cupiPost con el archivo que se encuentra en la ruta del archivo de configuraci�n <br>
     * Si ocurre alg�n error inicializando cupiPost, crear un cupiPost vaci�
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
        // Creaci�n de los paneles aqu�
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Este m�todo crea un nuevo post
     * @param categoria La categor�a a la cual se va agregar el post
     * @param ubicacion La ubicaci�n del anunciante
     * @param servicio El servicio del post
     * @param titulo El t�tulo del post
     * @param descripcion La descripci�n del post
     * @param tel El tel�fono del post
     * @param direccion La direcci�n del post
     * @throws DescripcionException Est� excepci�n se lanza si la descripci�n del post tiene una longitud mayor a 300 caracteres
     * @throws PostIncompletoException Est� excepci�n se lanza si falta alg�n campo del post por llenar
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
     * Crea una nueva categor�a con el nombre dado como par�metro
     * @param nombreCategoria El nombre de la nueva categor�a
     * @return categor�as El arreglo de categor�as de posts
     * @throws CategoriaExistenteException Excepci�n lanzada en caso que ya exista la categor�a
     */
    public ArrayList crearCategoria( String nombreCategoria ) throws CategoriaExistenteException
    {

        cupiPosts.crearCategoria( nombreCategoria );
        ArrayList categorias = cupiPosts.darCategorias( );
        return categorias;
    }

    /**
     * Refresca el panel categor�a para que muestre los nuevos posts creados
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
     * Retorna las categor�as de posts
     * @return El arreglo con las categor�as
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
     * Guarda las categor�as y los posts de CupiPosts en un archivo
     */
    public void dispose( )
    {
        //
        // TODO: Este m�todo es llamado al cerrar la aplicaci�n
        // Debe :
        // Debe pedirle a cupiPosts que se serialice en el archivo cuya direcci�n se encuentra en la constante RUTA_ARCHIVO_CONFIGURACION
        // Al finalizar el m�todo, aseg�rese de llamar el m�todo dispose de la clase JFrame
        
        // Maneje los mensajes de error debidos a todas las excepciones que se puedan generar
        //  - Debe preguntar al usuario si desea cerrar la aplicaci�n sin salvar la informaci�n dado que ocurri� un error
    }

    /**
     * El nombre de la categor�a que est� seleccionada
     * @return El nombre de la categor�a seleccionada
     */
    public String darCategoriaSeleccionada( )
    {
        Categoria cat = panelMenu.darCategoriaSeleccionada( );
        return cat.darNombre( );
    }

    /**
     * Elimina un post dado su identificador y la categor�a a la cual pertenece
     * @param categoriaSeleccionada El nombre de la categor�a a la cual est� asociada el post
     * @param postId El id del del post que se quiere eliminar
     */
    public void eliminarPost( String categoriaSeleccionada, int postId )
    {
        cupiPosts.eliminarPost( categoriaSeleccionada, postId );

    }

    /**
     * Carga de un archivo de texto plano datos de categor�as y posts
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
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupiPosts.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
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
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupiPosts interfaz = new InterfazCupiPosts( );
        interfaz.setVisible( true );
    }

}