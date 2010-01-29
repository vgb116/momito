/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoInfoInmueble.java,v 1.5 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_fincaRaiz
 * Autor: Camilo Alvarez Duran - 21-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.fincaRaiz.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Dialogo con la información de un inmueble
 */
public class DialogoInfoInmueble extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Comando cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    /**
     * Comando agregar imagen
     */
    private static final String AGREGAR_IMAGEN = "AGREGAR_IMAGEN";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la interfaz principal
     */
    private InterfazFincaRaiz principal;

    /**
     * La lista de imágenes
     */
    private ArrayList imagenes;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón agregar imagen
     */
    private JButton btnAgregarImagen;

    /**
     * Botón cancelar
     */
    private JButton btnCancelar;

    /**
     * Etiqueta barrio
     */
    private JLabel lblBarrio;

    /**
     * Etiqueta ciudad
     */
    private JLabel lblCiudad;

    /**
     * Etiqueta dirección
     */
    private JLabel lblDireccion;

    /**
     * Etiqueta precio
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta tamaño
     */
    private JLabel lblTamanio;

    /**
     * Etiqueta teléfono
     */
    private JLabel lblTelefono;

    /**
     * Etiqueta tipo inmueble
     */
    private JLabel lblTipoInmueble;

    /**
     * Etiqueta tipo oferta
     */
    private JLabel lblTipoOferta;

    /**
     * Panel con la información del inmueble
     */
    private JPanel pnlInfo;

    /**
     * Texto para el barrio
     */
    private JTextField txtBarrio;

    /**
     * Texto para la ciudad
     */
    private JTextField txtCiudad;

    /**
     * Texto para la dirección
     */
    private JTextField txtDireccion;

    /**
     * Texto para el precio
     */
    private JTextField txtPrecio;

    /**
     * Texto para el tamaño
     */
    private JTextField txtTamaño;

    /**
     * Texto para el teléfono
     */
    private JTextField txtTelefono;

    /**
     * Texto para el tipo de inmueble
     */
    private JComboBox cbxTipoInmueble;

    /**
     * Texto para el tipo de oferta
     */
    private JComboBox cbxTipoOferta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor que crea un dialogo para agregar un nuevo inmueble
     * @param interfazFincaRaiz Referencia a la ventana principal
     */
    public DialogoInfoInmueble( InterfazFincaRaiz interfazFincaRaiz )
    {
        super( interfazFincaRaiz, true );
        principal = interfazFincaRaiz;
        setTitle( "Agregar Inmueble" );
        setLayout( new BorderLayout( ) );
        setSize( 390, 280 );
        setLocationRelativeTo( null );

        imagenes = new ArrayList( );

        pnlInfo = new JPanel( );
        lblTipoOferta = new JLabel( );
        cbxTipoOferta = new JComboBox( );
        lblCiudad = new JLabel( );
        txtCiudad = new JTextField( );
        lblBarrio = new JLabel( );
        lblDireccion = new JLabel( );
        txtBarrio = new JTextField( );
        txtDireccion = new JTextField( );
        lblTelefono = new JLabel( );
        lblPrecio = new JLabel( );
        txtTelefono = new JTextField( );
        txtPrecio = new JTextField( );
        lblTamanio = new JLabel( );
        txtTamaño = new JTextField( );
        lblTipoInmueble = new JLabel( );
        cbxTipoInmueble = new JComboBox( );

        pnlInfo.setBorder( BorderFactory.createTitledBorder( "Detalles del Inmueble" ) );
        pnlInfo.setLayout( new GridLayout( 0, 2 ) );

        lblTipoOferta.setFont( new Font( "Arial", 1, 11 ) );
        lblTipoOferta.setHorizontalAlignment( SwingConstants.LEFT );
        lblTipoOferta.setText( "Tipo Oferta" );
        pnlInfo.add( lblTipoOferta );

        lblCiudad.setFont( new Font( "Arial", 1, 11 ) );
        lblCiudad.setHorizontalAlignment( SwingConstants.LEFT );
        lblCiudad.setText( "Ciudad" );
        pnlInfo.add( lblCiudad );

        cbxTipoOferta.setModel( new DefaultComboBoxModel( new String[]{ Inmueble.VENTA, Inmueble.ARRENDAR} ) );
        pnlInfo.add( cbxTipoOferta );

        txtCiudad.setColumns( 15 );
        pnlInfo.add( txtCiudad );

        lblBarrio.setFont( new Font( "Arial", 1, 11 ) );
        lblBarrio.setHorizontalAlignment( SwingConstants.LEFT );
        lblBarrio.setText( "Barrio" );
        pnlInfo.add( lblBarrio );

        lblDireccion.setFont( new Font( "Arial", 1, 11 ) );
        lblDireccion.setHorizontalAlignment( SwingConstants.LEFT );
        lblDireccion.setText( "Dirección" );
        pnlInfo.add( lblDireccion );

        txtBarrio.setColumns( 15 );
        pnlInfo.add( txtBarrio );

        txtDireccion.setColumns( 15 );
        pnlInfo.add( txtDireccion );

        lblTelefono.setFont( new Font( "Arial", 1, 11 ) );
        lblTelefono.setHorizontalAlignment( SwingConstants.LEFT );
        lblTelefono.setText( "Teléfono" );
        pnlInfo.add( lblTelefono );

        lblPrecio.setFont( new Font( "Arial", 1, 11 ) );
        lblPrecio.setHorizontalAlignment( SwingConstants.LEFT );
        lblPrecio.setText( "Precio" );
        pnlInfo.add( lblPrecio );

        txtTelefono.setColumns( 15 );
        pnlInfo.add( txtTelefono );

        txtPrecio.setColumns( 15 );
        pnlInfo.add( txtPrecio );

        lblTamanio.setFont( new Font( "Arial", 1, 11 ) );
        lblTamanio.setHorizontalAlignment( SwingConstants.LEFT );
        lblTamanio.setText( "Tamaño (m2)" );
        pnlInfo.add( lblTamanio );

        lblTipoInmueble.setFont( new Font( "Arial", 1, 11 ) );
        lblTipoInmueble.setText( "Tipo Inmueble" );
        pnlInfo.add( lblTipoInmueble );
        txtTamaño.setColumns( 15 );
        pnlInfo.add( txtTamaño );

        cbxTipoInmueble.setEditable( true );
        cbxTipoInmueble.setModel( new DefaultComboBoxModel( new String[]{ Inmueble.TIPO_APARTAMENTO, Inmueble.TIPO_CASA, Inmueble.TIPO_FINCA} ) );
        pnlInfo.add( cbxTipoInmueble );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        btnAgregarImagen = new JButton( "Agregar Imagen" );
        btnAgregarImagen.addActionListener( this );
        btnAgregarImagen.setActionCommand( AGREGAR_IMAGEN );

        JPanel panelOpciones = new JPanel( );
        panelOpciones.setLayout( new GridLayout( 1, 3 ) );
        panelOpciones.add( btnAceptar );
        panelOpciones.add( btnCancelar );
        panelOpciones.add( btnAgregarImagen );

        add( pnlInfo, BorderLayout.CENTER );
        add( panelOpciones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Método que pone todos los campos vacíos
     */
    public void limpiarEdicion( )
    {
        txtBarrio.setText( "" );
        txtCiudad.setText( "" );
        txtDireccion.setText( "" );
        txtPrecio.setText( "" );
        txtTamaño.setText( "" );
        txtTelefono.setText( "" );
    }

    /**
     * Método par agregar una imagen al inmueble actual
     * @param string La url de la imagen
     */
    public void agregarImagen( String string )
    {
        imagenes.add( string );
        JOptionPane.showMessageDialog( this, "Se ha agregado la imagen a la lista de imágenes del inmueble", "Imágenes", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String cmd = e.getActionCommand( );
        if( cmd.equals( ACEPTAR ) )
        {
            // Validar tamaño y precio
            double tamanio = 0;
            double precio = 0;
            try
            {
                tamanio = Double.parseDouble( txtTamaño.getText( ) );
                if(tamanio <= 0)
                {
                    JOptionPane.showMessageDialog( principal, "Valor inválido en el tamaño del inmueble.", "Valor Inválido", JOptionPane.ERROR_MESSAGE );
                    return;
                }
            }
            catch( Exception ex )
            {
                JOptionPane.showMessageDialog( principal, "Valor inválido en el tamaño del inmueble.", "Valor Inválido", JOptionPane.ERROR_MESSAGE );
                return;
            }
            try
            {
                precio = Double.parseDouble( txtPrecio.getText( ) );
                if(precio <= 0)
                {
                    JOptionPane.showMessageDialog( principal, "Valor inválido en el precio del inmueble.", "Valor Inválido", JOptionPane.ERROR_MESSAGE );
                    return;
                }
            }
            catch( Exception ex )
            {
                JOptionPane.showMessageDialog( principal, "Valor inválido en el precio del inmueble.", "Valor Inválido", JOptionPane.ERROR_MESSAGE );
                return;
            }
            String tipoOferta = Inmueble.ARRENDAR;
            if( cbxTipoOferta.getSelectedItem( ).equals( Inmueble.VENTA ) )
            {
                tipoOferta = Inmueble.VENTA;
            }
            
            if(txtCiudad.getText( ).isEmpty( ) || txtBarrio.getText( ).isEmpty( ) || txtDireccion.getText( ).isEmpty( ) || txtTelefono.getText( ).isEmpty( ))
            {
                JOptionPane.showMessageDialog( principal, "Datos Incompletos.", "Agregar Inmueble", JOptionPane.ERROR_MESSAGE );
                return;
            }
            principal.crearInmueble( cbxTipoInmueble.getSelectedItem( ) + "", tipoOferta, txtCiudad.getText( ), txtBarrio.getText( ), txtDireccion.getText( ), txtTelefono.getText( ), tamanio, precio, imagenes );
            dispose( );
        }
        else if( cmd.equals( CANCELAR ) )
        {
            dispose( );
        }
        else if( cmd.equals( AGREGAR_IMAGEN ) )
        {
            JFileChooser fileChooser = new JFileChooser( "./data/" );
            if( fileChooser.showOpenDialog( principal ) == JFileChooser.APPROVE_OPTION )
            {
                agregarImagen( fileChooser.getSelectedFile( ).getAbsolutePath( ) );
            }
        }

    }

}
