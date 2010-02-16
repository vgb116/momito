package uniandes.cupi2.cupiPosts.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiPosts.mundo.Post;

/**
 * Panel con la información de un post
 * 
 */
public class PanelPost extends JPanel implements ActionListener
{
    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 100L;

    /**
     * Constante Marcar Post
     */
    private final static String MARCAR_POST = "Marcar Post";

    /**
     * Constante Eliminar Post
     */
    public final static String ELIMINAR_POST = "Eliminar Post";

    // ------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El post detallado en este panel
     */
    private Post post;

    /**
     * La ventana principal de la aplicación
     */
    private InterfazCupiPosts principal;

    // ------------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * La etiqueta del título
     */
    private JLabel labelElTitulo;

    /**
     * La etiqueta de la ubicación
     */
    private JLabel labelUbicacion;

    /**
     * La etiqueta con la ubicación especifica de este post
     */
    private JLabel labelLaUbicacion;

    /**
     * La etiqueta del servicio
     */
    private JLabel labelServicio;

    /**
     * La etiqueta del servicio especifico de este post
     */
    private JLabel labelElServicio;

    /**
     * La etiqueta de la descripción
     */
    private JLabel labelDescripcion;

    /**
     * La descripción especifica de este post
     */
    private JTextArea textLaDescripcion;

    /**
     * La etiqueta del teléfono
     */
    private JLabel labelTelefono;

    /**
     * EL teléfono específico de este post
     */
    private JLabel labelElTelefono;

    /**
     * La etiqueta de la dirección
     */
    private JLabel labelDireccion;

    /**
     * La etiqueta con la dirección de este post
     */
    private JLabel labelLaDireccion;

    /**
     * Check box que indica si un usuario seleccionó el post
     */
    private JCheckBox seleccionado;

    /**
     * Botón para eliminar el post
     */
    private JButton botonEliminar;

    // ------------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel que muestra los detalles del post y una opción para marcarlo para que pueda ser guardado en los reportes
     * @param interfaz La ventana principal de la aplicación
     */
    public PanelPost( InterfazCupiPosts interfaz )
    {
        principal = interfaz;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "" ) );
        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 6, 2 ) );
        panelDatos.setBackground( Color.WHITE );
        JPanel panelDescripcionBoton = new JPanel( );
        panelDescripcionBoton.setLayout( new BorderLayout( ) );
        JPanel panelBoton = new JPanel( );
        panelBoton.setLayout( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
        panelBoton.setBackground( Color.WHITE );
        panelDescripcionBoton.add( BorderLayout.SOUTH, panelBoton );
        labelUbicacion = new JLabel( DialogoCrearPost.UBICACION );
        labelUbicacion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelServicio = new JLabel( DialogoCrearPost.SERVICIO );
        labelServicio.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelDescripcion = new JLabel( DialogoCrearPost.DESCRIPCION );
        labelDescripcion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelTelefono = new JLabel( DialogoCrearPost.TELEFONO );
        labelTelefono.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelDireccion = new JLabel( DialogoCrearPost.DIRECCION );
        labelDireccion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );

        seleccionado = new JCheckBox( MARCAR_POST );
        seleccionado.setActionCommand( MARCAR_POST );
        seleccionado.addActionListener( this );
        seleccionado.setBackground( Color.WHITE );

        labelElTitulo = new JLabel( );
        labelElTitulo.setForeground( Color.BLACK );
        labelElTitulo.setFont( new Font( "Tahoma", Font.BOLD, 18 ) );
        labelLaUbicacion = new JLabel( );
        labelLaUbicacion.setForeground( Color.GRAY );
        labelLaUbicacion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelElServicio = new JLabel( );
        labelElServicio.setForeground( Color.GRAY );
        labelElServicio.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        textLaDescripcion = new JTextArea( );
        textLaDescripcion.setEditable( false );
        textLaDescripcion.setForeground( Color.GRAY );
        textLaDescripcion.setLineWrap( true );

        textLaDescripcion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelElTelefono = new JLabel( );
        labelElTelefono.setForeground( Color.GRAY );
        labelElTelefono.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
        labelLaDireccion = new JLabel( );
        labelLaDireccion.setForeground( Color.GRAY );
        labelLaDireccion.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );

        botonEliminar = new JButton( "Eliminar Post" );
        botonEliminar.addActionListener( this );
        botonEliminar.setActionCommand( ELIMINAR_POST );
        botonEliminar.setToolTipText( ELIMINAR_POST );
        panelDatos.add( labelElTitulo );
        panelDatos.add( seleccionado );
        panelDatos.add( labelUbicacion );
        panelDatos.add( labelLaUbicacion );
        panelDatos.add( labelServicio );
        panelDatos.add( labelElServicio );
        panelDatos.add( labelTelefono );
        panelDatos.add( labelElTelefono );
        panelDatos.add( labelDireccion );
        panelDatos.add( labelLaDireccion );
        panelDatos.add( labelDescripcion );
        add( BorderLayout.CENTER, panelDatos );
        panelBoton.add( botonEliminar );
        panelDescripcionBoton.add( BorderLayout.CENTER, textLaDescripcion );
        add( BorderLayout.SOUTH, panelDescripcionBoton );

    }

    // ------------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método para actualizar el panel con los detalles del post
     * @param elPost El post cuyos detalles se van a mostrar en este panel
     */
    public void actualizarDatos( Post elPost )
    {
        post = elPost;
        labelElTitulo.setText( post.darTitulo( ) );
        labelLaUbicacion.setText( post.darUbicacion( ) );
        labelElServicio.setText( post.darServicio( ) );
        textLaDescripcion.setText( post.darDescripcion( ) );
        labelElTelefono.setText( "" + post.darTelefono( ) );
        labelLaDireccion.setText( post.darDireccion( ) );
        if( post.darSeleccionado( ) == true )
        {
            seleccionado.setSelected( true );
        }

    }

    /**
     * Método que recoge las acciones sobre los objetos que está escuchando.
     * @param evento El evento que se realizó.
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( evento.getActionCommand( ).equals( MARCAR_POST ) )
        {
            if( seleccionado.isSelected( ) )
            {
                post.cambiarSeleccionado( true );
            }
            else
            {
                post.cambiarSeleccionado( false );
            }
        }
        else if( evento.getActionCommand( ).equals( ELIMINAR_POST ) )
        {
            int respuesta = JOptionPane.showConfirmDialog( this, "¿Está seguro que desea eliminar el post?", "Eliminar Post", JOptionPane.YES_NO_OPTION );
            if( respuesta == 0 )
            {
                int postId = post.darId( );
                String categoriaSeleccionada = principal.darCategoriaSeleccionada( );
                principal.eliminarPost( categoriaSeleccionada, postId );
                principal.actualizarPanelCategoria( );
            }
        }

    }
}
