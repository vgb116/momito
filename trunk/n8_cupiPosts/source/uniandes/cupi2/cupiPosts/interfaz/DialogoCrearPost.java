/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoCrearPost.java,v 1.20 2010/02/08 13:58:40 dav-vill Exp $
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiPosts.mundo.Categoria;
import uniandes.cupi2.cupiPosts.mundo.DescripcionException;
import uniandes.cupi2.cupiPosts.mundo.Post;
import uniandes.cupi2.cupiPosts.mundo.PostIncompletoException;

/**
 * Dialogo para crear un nuevo post
 * 
 */
public class DialogoCrearPost extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante Categoría
     */
    public final static String CATEGORIA = "Categoría:";

    /**
     * Constante Ubicación
     */
    public final static String UBICACION = "Ubicación:";

    /**
     * Constante Servicio
     */
    public final static String SERVICIO = "Servicio:";

    /**
     * Constante Título
     */
    public final static String TITULO = "Título:";

    /**
     * Constante Descripción
     */
    public final static String DESCRIPCION = "Descripción:";

    /**
     * Constante Teléfono
     */
    public final static String TELEFONO = "Teléfono:";

    /**
     * Constante Dirección
     */
    public final static String DIRECCION = "Dirección:";

    /**
     * Constante crear
     */
    public final static String BTN_CREAR = "Crear Post";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicación
     */
    private InterfazCupiPosts principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Combo box con las posibles ubicaciones donde se encuentra el anunciante del post
     */
    private JComboBox comboUbicacion;

    /**
     * La etiqueta ubicación
     */
    private JLabel labelUbicacion;

    /**
     * Combo box con los posibles servicios que se presentan en el post
     */
    private JComboBox comboServicio;

    /**
     * Combo box para seleccionar a que categoría se va a asociar el nuevo post
     */
    private JComboBox comboCategoria;

    /**
     * La etiqueta de categoría
     */
    private JLabel labelCategoria;

    /**
     * La etiqueta del servicio
     */
    private JLabel labelServicio;

    /**
     * El título del post
     */
    private JTextField textTitulo;

    /**
     * La etiqueta del título
     */
    private JLabel labelTitulo;

    /**
     * Cuadro de texto con la descripción del post
     */
    private JTextArea textDescripcion;

    /**
     * El teléfono del anunciante
     */
    private JTextField textTelefono;

    /**
     * La etiqueta del teléfono
     */
    private JLabel labelTelefono;

    /**
     * La dirección del anunciante
     */
    private JTextField textDireccion;

    /**
     * La etiqueta de la dirección
     */
    private JLabel labelDireccion;

    /**
     * Botón para crear un post
     */
    private JButton btnCrearPost;

    /**
     * Scroll del área de texto
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo dialogo con la funcionalidad de crear un post asociado a una categoría
     * @param ventana La ventana principal de la aplicación
     */
    public DialogoCrearPost( InterfazCupiPosts ventana )
    {
        super( ventana, true );
        setSize( new Dimension( 350, 450 ) );
        setLocationRelativeTo( null );
        principal = ventana;
        setTitle( "Crear Post" );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setLayout( new BorderLayout( ) );

        labelCategoria = new JLabel( CATEGORIA );
        labelUbicacion = new JLabel( UBICACION );
        labelServicio = new JLabel( SERVICIO );
        labelTitulo = new JLabel( TITULO );
        labelTelefono = new JLabel( TELEFONO );
        labelDireccion = new JLabel( DIRECCION );

        textTitulo = new JTextField( );
        textDescripcion = new JTextArea( );
        textDescripcion.setLineWrap( true );
        textTelefono = new JTextField( );
        textDireccion = new JTextField( );

        comboCategoria = new JComboBox( );
        actualizarCategorias( principal.darCategorias( ) );

        comboUbicacion = new JComboBox( );
        comboUbicacion.addItem( Post.BOGOTA );
        comboUbicacion.addItem( Post.MEDELLIN );
        comboUbicacion.addItem( Post.CALI );
        comboUbicacion.addItem( Post.BARRANQUILLA );
        comboUbicacion.addItem( Post.CARTAGENA );
        comboUbicacion.addItem( Post.BUCARAMANGA );
        comboServicio = new JComboBox( );
        comboServicio.addItem( Post.SERVICIO_VENDO );
        comboServicio.addItem( Post.SERVICIO_COMPRO );
        comboServicio.addItem( Post.SERVICIO_ALQUILO );
        comboServicio.addItem( Post.SERVICIO_ARRIENDO );

        btnCrearPost = new JButton( "Agregar" );
        btnCrearPost.setActionCommand( BTN_CREAR );
        btnCrearPost.addActionListener( this );

        JPanel panelDatos = new JPanel( );
        panelDatos.setBorder( new TitledBorder( "Ingrese los datos del nuevo Post" ) );
        panelDatos.setLayout( new GridLayout( 6, 2, 10, 5 ) );

        panelDatos.add( labelCategoria );
        panelDatos.add( comboCategoria );
        panelDatos.add( labelTitulo );
        panelDatos.add( textTitulo );
        panelDatos.add( labelUbicacion );
        panelDatos.add( comboUbicacion );
        panelDatos.add( labelServicio );
        panelDatos.add( comboServicio );
        panelDatos.add( labelTelefono );
        panelDatos.add( textTelefono );
        panelDatos.add( labelDireccion );
        panelDatos.add( textDireccion );

        scroll = new JScrollPane( );
        scroll.setViewportView( textDescripcion );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setPreferredSize( new Dimension( 330, 135 ) );

        JPanel panelDescripcion = new JPanel( );
        panelDescripcion.setLayout( new FlowLayout( ) );
        panelDescripcion.setBorder( new TitledBorder( "Descripción" ) );
        panelDescripcion.add( scroll );

        this.add( panelDescripcion, BorderLayout.CENTER );
        this.add( panelDatos, BorderLayout.NORTH );
        this.add( btnCrearPost, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el combo box de las categorías cada vez que el usuario crea una nueva o se cargan los datos de archivos de texto
     * @param categorias Las categorías para poblar el combo box
     */
    public void actualizarCategorias( ArrayList categorias )
    {
        for( int i = 0; i < categorias.size( ); i++ )
        {
            Categoria categoria = ( Categoria )categorias.get( i );
            comboCategoria.addItem( categoria );
        }
    }

    /**
     * Método que recoge las acciones sobre los objetos que está escuchando.
     * @param evento El evento que se realizó.
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( evento.getActionCommand( ).equals( BTN_CREAR ) )
        {

            String categoria = comboCategoria.getSelectedItem( ).toString( );
            String ubicacion = comboUbicacion.getSelectedItem( ).toString( );
            String servicio = comboServicio.getSelectedItem( ).toString( );
            String titulo = textTitulo.getText( );
            String descripcion = textDescripcion.getText( );
            String telefono = textTelefono.getText( );

            int tel = 0;
            try
            {
                tel = Integer.valueOf( telefono ).intValue( );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "Ingrese un número telefónico válido", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }

            String direccion = textDireccion.getText( );

            try
            {
                principal.crearPost( categoria, ubicacion, servicio, titulo, descripcion, tel, direccion );
                principal.actualizarPanelCategoria( );
            }
            catch( DescripcionException e )
            {
                JOptionPane.showMessageDialog( this, "Ha ingresado una descripción de " + e.darLongitudDescripcion( ) + " caracteres y la longitud max es de 300", e.getMessage( ), JOptionPane.ERROR_MESSAGE );
                return;
            }
            catch( PostIncompletoException e2 )
            {
                JOptionPane.showMessageDialog( this, "Debe llenar los siguientes campos: " + e2.darCamposVacios( ), e2.getMessage( ), JOptionPane.ERROR_MESSAGE );
                return;
            }

        }
        dispose( );
    }

}
