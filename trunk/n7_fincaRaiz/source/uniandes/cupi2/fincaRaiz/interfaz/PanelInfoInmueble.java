/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInfoInmueble.java,v 1.7 2009/10/15 23:08:40 c.alvarez947 Exp $
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Panel con la información de un inmueble
 */
public class PanelInfoInmueble extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando adelante
     */
    private static final String ADELANTE = "ADELANTE";

    /**
     * Comando atras
     */
    private static final String ATRAS = "ATRAS";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El índice de la imagen actual
     */
    private int imagenActual;

    /**
     * La lista de imagenes
     */
    private ArrayList imagenes;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón ir a la imagen anterior
     */
    private JButton btnAtras;

    /**
     * Botón adelante
     */
    private JButton btnAdelante;

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
     * Etiqueta imagen
     */
    private JLabel lblImagen;

    /**
     * Etiqueta indicador
     */
    private JLabel lblIndicador;

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
     * Texto para el identificador
     */
    private JTextField txtIdentificador;

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
    private JTextField txtTipoInmueble;

    /**
     * Texto para el tipo de oferta
     */
    private JTextField txtTipoOferta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor que inicializa los atributos de la clase
     */
    public PanelInfoInmueble(  )
    {
        imagenActual = 0;
        GridBagConstraints gridBagConstraints;

        JPanel pnlImagenes = new JPanel( );
        lblImagen = new JLabel( );
        JPanel panelNavegacion = new JPanel( );
        btnAtras = new JButton( );
        btnAdelante = new JButton( );
        pnlInfo = new JPanel( );
        lblIndicador = new JLabel( );
        lblTipoOferta = new JLabel( );
        txtIdentificador = new JTextField( );
        txtTipoOferta = new JTextField( );
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
        txtTipoInmueble = new JTextField( );

        setBorder( BorderFactory.createTitledBorder( "Detalles del Inmueble" ) );
        setLayout( new GridLayout( 2, 1 ) );

        pnlImagenes.setLayout( new BorderLayout( ) );

        lblImagen.setHorizontalAlignment( SwingConstants.CENTER );
        lblImagen.setText( "" );
        pnlImagenes.add( lblImagen, BorderLayout.CENTER );

        panelNavegacion.setLayout( new GridLayout( 1, 2 ) );

        btnAtras.setIcon( new ImageIcon( "./data/imagenes/anterior.png" ) );
        btnAtras.addActionListener( this );
        btnAtras.setActionCommand( ATRAS );
        panelNavegacion.add( btnAtras );

        btnAdelante.setIcon( new ImageIcon( "./data/imagenes/siguiente.png" ) );
        btnAdelante.addActionListener( this );
        btnAdelante.setActionCommand( ADELANTE );
        panelNavegacion.add( btnAdelante );

        pnlImagenes.add( panelNavegacion, BorderLayout.PAGE_END );

        add( pnlImagenes );

        pnlInfo.setLayout( new GridBagLayout( ) );
        pnlInfo.setBorder( BorderFactory.createTitledBorder( "Información del Inmueble" ) );
        lblIndicador.setFont( new Font( "Arial", 1, 11 ) );
        lblIndicador.setHorizontalAlignment( SwingConstants.LEFT );
        lblIndicador.setText( "Identificador" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblIndicador, gridBagConstraints );

        lblTipoOferta.setFont( new Font( "Arial", 1, 11 ) );
        lblTipoOferta.setHorizontalAlignment( SwingConstants.LEFT );
        lblTipoOferta.setText( "Tipo Oferta" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblTipoOferta, gridBagConstraints );

        txtIdentificador.setColumns( 15 );
        txtIdentificador.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtIdentificador, gridBagConstraints );

        txtTipoOferta.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtTipoOferta, gridBagConstraints );

        lblCiudad.setFont( new Font( "Arial", 1, 11 ) );
        lblCiudad.setHorizontalAlignment( SwingConstants.LEFT );
        lblCiudad.setText( "Ciudad" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblCiudad, gridBagConstraints );

        txtCiudad.setColumns( 15 );
        txtCiudad.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtCiudad, gridBagConstraints );

        lblBarrio.setFont( new Font( "Arial", 1, 11 ) );
        lblBarrio.setHorizontalAlignment( SwingConstants.LEFT );
        lblBarrio.setText( "Barrio" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblBarrio, gridBagConstraints );

        lblDireccion.setFont( new Font( "Arial", 1, 11 ) );
        lblDireccion.setHorizontalAlignment( SwingConstants.LEFT );
        lblDireccion.setText( "Dirección" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblDireccion, gridBagConstraints );

        txtBarrio.setColumns( 15 );
        txtBarrio.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtBarrio, gridBagConstraints );

        txtDireccion.setColumns( 15 );
        txtDireccion.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtDireccion, gridBagConstraints );

        lblTelefono.setFont( new Font( "Arial", 1, 11 ) );
        lblTelefono.setHorizontalAlignment( SwingConstants.LEFT );
        lblTelefono.setText( "Teléfono" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblTelefono, gridBagConstraints );

        lblPrecio.setFont( new Font( "Arial", 1, 11 ) );
        lblPrecio.setHorizontalAlignment( SwingConstants.LEFT );
        lblPrecio.setText( "Precio" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblPrecio, gridBagConstraints );

        txtTelefono.setColumns( 15 );
        txtTelefono.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtTelefono, gridBagConstraints );

        txtPrecio.setColumns( 15 );
        txtPrecio.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtPrecio, gridBagConstraints );

        lblTamanio.setFont( new Font( "Arial", 1, 11 ) );
        lblTamanio.setHorizontalAlignment( SwingConstants.LEFT );
        lblTamanio.setText( "Tamaño (m2)" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblTamanio, gridBagConstraints );

        txtTamaño.setColumns( 15 );
        txtTamaño.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtTamaño, gridBagConstraints );

        lblTipoInmueble.setFont( new Font( "Arial", 1, 11 ) );
        lblTipoInmueble.setText( "Tipo Inmueble" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( lblTipoInmueble, gridBagConstraints );

        txtTipoInmueble.setEditable( false );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        pnlInfo.add( txtTipoInmueble, gridBagConstraints );

        add( pnlInfo );
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que deshabilita los componentes para edición
     */
    public void desHabilitarEdicion( )
    {
        txtBarrio.setEditable( false );
        txtCiudad.setEditable( false );
        txtDireccion.setEditable( false );
        txtPrecio.setEditable( false );
        txtTamaño.setEditable( false );
        txtTelefono.setEditable( false );
        limpiarEdicion( );
    }

    /**
     * Método para habilitar la edición
     */
    public void habilitarEdicion( )
    {
        txtBarrio.setEditable( true );
        txtCiudad.setEditable( true );
        txtDireccion.setEditable( true );
        txtPrecio.setEditable( true );
        txtTamaño.setEditable( true );
        txtTelefono.setEditable( true );
        limpiarEdicion( );
    }

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
        txtIdentificador.setText( "" );
    }

    /**
     * Método que actualiza la información de un inmuebles
     * @param inmueble El inmueble
     */
    public void actualizar( Inmueble inmueble )
    {
        if( inmueble != null )
        {
            txtBarrio.setText( inmueble.darBarrio( ) );
            txtCiudad.setText( inmueble.darCiudad( ) );
            txtDireccion.setText( inmueble.darDireccion( ) );
            txtIdentificador.setText( inmueble.darIdentificador( ) );
            txtPrecio.setText( "$" + new Double(inmueble.darPrecio( )).toString( ) );
            txtTamaño.setText( inmueble.darTamanio( ) + "" );
            txtTelefono.setText( inmueble.darTelefono( ) );
            txtTipoInmueble.setText( inmueble.darTipoInmueble( ) );
            txtTipoOferta.setText( inmueble.darTipoOferta( ) );
            imagenes = inmueble.darImagenes( );
            imagenActual = 0;
            actualizarImagen( );
        }
        else
        {
            limpiarEdicion( );
            lblImagen.setIcon( null );
        }
    }

    /**
     * Actualiza la imagen
     */
    public void actualizarImagen( )
    {
        if( imagenes.size( ) > 0 )
            lblImagen.setIcon( new ImageIcon( imagenes.get( imagenActual ).toString( ) ) );
        else
            lblImagen.setIcon( null );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String cmd = e.getActionCommand( );
        if( imagenes != null )
        {
            if( cmd.equals( ADELANTE ) )
            {
                if( ( imagenActual + 1 ) <= imagenes.size( ) - 1 )
                {
                    imagenActual++;
                    actualizarImagen( );
                }
            }
            else if( cmd.equals( ATRAS ) )
            {
                if( ( imagenActual - 1 ) >= 0 && imagenes.size( ) > 0 )
                {
                    imagenActual--;
                    actualizarImagen( );
                }
            }
        }
    }
}
