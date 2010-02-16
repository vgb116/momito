package uniandes.cupi2.cupiPosts.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.CategoriaExistenteException;

/**
 * Panel que contiene el menu de la aplicaci�n
 * 
 */
public class PanelMenu extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 100L;

    /**
     * Constante para crear un post
     */
    public final static String CREAR_POST = "Crear Post";

    /**
     * Comando para mostrar los posts de la categor�a seleccionada en el combo box
     */
    public final static String COMBO_BOX = "Combo Box";

    /**
     * Constante para generar un reporte de los posts seleccionados
     */
    public final static String GENERAR_REPORTE = "Generar Reporte";

    /**
     * Constante para crear una categor�a
     */
    public final static String CREAR_CATEGORIA = "Crear Categor�a";

    /**
     * Constante para cargar datos de un archivo de texto plano
     */
    public final static String CARGAR_DATOS = "Cargar Datos";

    /**
     * Constante para crear una categor�a
     */
    public final static String SELECCIONAR_CATEGORIA = "Seleccione la categor�a que desee consultar:";

    /**
     * El directorio donde se encuentran las im�genes
     */
    public static final String DIRECTORIO = "data/imagenes/";

    // ------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Bot�n para crear un post
     */
    private JButton btnCrearPost;

    /**
     * Bot�n para generar un reporte de los posts seleccionados
     */
    private JButton btnGenerarReporte;

    /**
     * Bot�n para crear una categor�a
     */
    private JButton btnCrearCategoria;

    /**
     * Bot�n para cargar datos de categor�as y posts de un archivo de texto
     */
    private JButton btnCargarDatos;

    /**
     * Label para seleccionar una categor�a
     */
    private JLabel labelSeleccionarCategoria;

    /**
     * Combo box con las categor�as de posts
     */
    // TODO: Declare el combo box llamado comboCategorias para las categor�as

    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupiPosts principal;

    // ------------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del men� de opciones
     * @param ventana La ventana principal de la aplicaci�n
     */
    public PanelMenu( InterfazCupiPosts ventana )
    {

        principal = ventana;

        setPreferredSize( new Dimension( 800, 135 ) );
        setLayout( new BorderLayout( ) );

        JPanel panelCategorias = new JPanel( );
        panelCategorias.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
        panelCategorias.setBorder( new TitledBorder( "Categor�as" ) );

        // TODO inicialice el combo box y asocie su listener usando el m�todo addItemListener
        
        panelCategorias.add( labelSeleccionarCategoria );
        panelCategorias.add( comboCategorias );

        add( panelCategorias, BorderLayout.SOUTH );
        JPanel panelOpciones = new JPanel( );
        panelOpciones.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
        panelOpciones.setBorder( new TitledBorder( "Men�" ) );

        btnCrearPost = new JButton( new ImageIcon( DIRECTORIO + "nuevoPost.png" ) );
        btnCrearPost.addActionListener( this );
        btnCrearPost.setActionCommand( CREAR_POST );
        btnCrearPost.setToolTipText( CREAR_POST );

        panelOpciones.add( btnCrearPost );

        btnGenerarReporte = new JButton( new ImageIcon( DIRECTORIO + "nuevoReporte.png" ) );
        btnGenerarReporte.addActionListener( this );
        btnGenerarReporte.setActionCommand( GENERAR_REPORTE );
        btnGenerarReporte.setToolTipText( GENERAR_REPORTE );

        panelOpciones.add( btnGenerarReporte );

        btnCrearCategoria = new JButton( new ImageIcon( DIRECTORIO + "nuevaCategoria.png" ) );
        btnCrearCategoria.addActionListener( this );
        btnCrearCategoria.setActionCommand( CREAR_CATEGORIA );
        btnCrearCategoria.setToolTipText( CREAR_CATEGORIA );

        panelOpciones.add( btnCrearCategoria );

        btnCargarDatos = new JButton( new ImageIcon( DIRECTORIO + "cargarDatos.png" ) );
        btnCargarDatos.addActionListener( this );
        btnCargarDatos.setActionCommand( CARGAR_DATOS );
        btnCargarDatos.setToolTipText( CARGAR_DATOS );

        panelOpciones.add( btnCargarDatos );

        add( panelOpciones, BorderLayout.CENTER );

    }

    // ------------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el combo box con la lista de las categor�as.
     * @param categorias Lista de categor�as que se quiere que est�n en el comboBox.
     */
    public void actualizarCategorias( ArrayList categorias )
    {

        comboCategorias.removeAllItems( );
        for( int i = 0; i < categorias.size( ); i++ )
        {
            comboCategorias.addItem( categorias.get( i ) );
        }
    }

    /**
     * Maneja los eventos generado por los botones o el combo box
     * @param evento Evento generado por los botones o el combo box
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( evento.getActionCommand( ).equals( COMBO_BOX ) )
        {
            principal.actualizarPanelCategoria( );
        }

        else if( evento.getActionCommand( ).equals( CREAR_POST ) )
        {
            if( comboCategorias.getItemCount( ) == 0 )
            {
                JOptionPane.showMessageDialog( this, "Debe crear una categor�a antes de crear un post", "Cuidado", JOptionPane.ERROR_MESSAGE );
                return;
            }
            principal.mostrarDialogoCrearPost( );
        }

        else if( evento.getActionCommand( ).equals( GENERAR_REPORTE ) )
        {
            JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showSaveDialog( principal );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                principal.escribirReportePostsSeleccionados( pathArchivo, nombreArchivo );
            }
        }

        else if( evento.getActionCommand( ).equals( CREAR_CATEGORIA ) )
        {

            String nombreCategoria = ( String )JOptionPane.showInputDialog( this, "Ingrese el nombre de la categor�a", "Crear categor�a", JOptionPane.QUESTION_MESSAGE );
            if( nombreCategoria != null )
            {
                if( nombreCategoria.equals( "" ) )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE );
                    return;
                }

                ArrayList categorias;
                try
                {
                    categorias = principal.crearCategoria( nombreCategoria );
                    actualizarCategorias( categorias );
                }
                catch( CategoriaExistenteException e )
                {
                    JOptionPane.showMessageDialog( this, "Ya existe una categor�a con el nombre " + e.darCategoria( ), "Ingresar Categor�a", JOptionPane.ERROR_MESSAGE );
                    return;
                }

            }
        }
        else if( evento.getActionCommand( ).equals( CARGAR_DATOS ) )
        {
            JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showOpenDialog( principal );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );

                principal.cargarDatos( pathArchivo + "/" + nombreArchivo );
            }

            actualizarCategorias( principal.darCategorias( ) );
        }

    }

    /**
     * Retorna la categor�a seleccionada
     * @return La categor�a seleccionada
     */
    public Categoria darCategoriaSeleccionada( )
    {
        //
        // TODO Completar seg�n las instrucciones en el enunciado
    }

}
