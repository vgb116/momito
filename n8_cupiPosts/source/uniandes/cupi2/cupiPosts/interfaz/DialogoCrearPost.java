/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoCrearPost.java,v 1.20 2010/02/08 13:58:40 dav-vill Exp $
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
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante Categor�a
     */
    public final static String CATEGORIA = "Categor�a:";

    /**
     * Constante Ubicaci�n
     */
    public final static String UBICACION = "Ubicaci�n:";

    /**
     * Constante Servicio
     */
    public final static String SERVICIO = "Servicio:";

    /**
     * Constante T�tulo
     */
    public final static String TITULO = "T�tulo:";

    /**
     * Constante Descripci�n
     */
    public final static String DESCRIPCION = "Descripci�n:";

    /**
     * Constante Tel�fono
     */
    public final static String TELEFONO = "Tel�fono:";

    /**
     * Constante Direcci�n
     */
    public final static String DIRECCION = "Direcci�n:";

    /**
     * Constante crear
     */
    public final static String BTN_CREAR = "Crear Post";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicaci�n
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
     * La etiqueta ubicaci�n
     */
    private JLabel labelUbicacion;

    /**
     * Combo box con los posibles servicios que se presentan en el post
     */
    private JComboBox comboServicio;

    /**
     * Combo box para seleccionar a que categor�a se va a asociar el nuevo post
     */
    private JComboBox comboCategoria;

    /**
     * La etiqueta de categor�a
     */
    private JLabel labelCategoria;

    /**
     * La etiqueta del servicio
     */
    private JLabel labelServicio;

    /**
     * El t�tulo del post
     */
    private JTextField textTitulo;

    /**
     * La etiqueta del t�tulo
     */
    private JLabel labelTitulo;

    /**
     * Cuadro de texto con la descripci�n del post
     */
    private JTextArea textDescripcion;

    /**
     * El tel�fono del anunciante
     */
    private JTextField textTelefono;

    /**
     * La etiqueta del tel�fono
     */
    private JLabel labelTelefono;

    /**
     * La direcci�n del anunciante
     */
    private JTextField textDireccion;

    /**
     * La etiqueta de la direcci�n
     */
    private JLabel labelDireccion;

    /**
     * Bot�n para crear un post
     */
    private JButton btnCrearPost;

    /**
     * Scroll del �rea de texto
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo dialogo con la funcionalidad de crear un post asociado a una categor�a
     * @param ventana La ventana principal de la aplicaci�n
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
        panelDescripcion.setBorder( new TitledBorder( "Descripci�n" ) );
        panelDescripcion.add( scroll );

        this.add( panelDescripcion, BorderLayout.CENTER );
        this.add( panelDatos, BorderLayout.NORTH );
        this.add( btnCrearPost, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza el combo box de las categor�as cada vez que el usuario crea una nueva o se cargan los datos de archivos de texto
     * @param categorias Las categor�as para poblar el combo box
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
     * M�todo que recoge las acciones sobre los objetos que est� escuchando.
     * @param evento El evento que se realiz�.
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
                JOptionPane.showMessageDialog( this, "Ingrese un n�mero telef�nico v�lido", "ERROR", JOptionPane.ERROR_MESSAGE );
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
                JOptionPane.showMessageDialog( this, "Ha ingresado una descripci�n de " + e.darLongitudDescripcion( ) + " caracteres y la longitud max es de 300", e.getMessage( ), JOptionPane.ERROR_MESSAGE );
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
