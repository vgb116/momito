/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoCargando.java,v 1.10 2010/04/26 21:08:20 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.interfazCliente;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Dialogo que se muestra mientras se recibe una respuesta del servidor
 */
public class DialogoCargando extends JDialog
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Label imagen
     */
    private JLabel lblImagen;

    /**
     * Label del mensaje
     */
    private JLabel lblMensaje;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo
     * 
     * @param parent La ventana principal
     */
    public DialogoCargando( Frame parent )
    {
        super( parent, true );
        setSize( 330, 200 );
        setLocationRelativeTo( null );
        setUndecorated( true );
        getRootPane( ).setWindowDecorationStyle( JRootPane.PLAIN_DIALOG );
        setTitle( "Procesando ..." );
        GridBagConstraints gridBagConstraints;

        lblImagen = new JLabel( );
        lblMensaje = new JLabel( );

        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        setBackground( new Color( 255, 255, 255 ) );
        setLayout( new GridBagLayout( ) );

        lblImagen.setHorizontalAlignment( SwingConstants.CENTER );
        lblImagen.setIcon( new ImageIcon( "./data/imagenes/espera.gif" ) );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add( lblImagen, gridBagConstraints );

        lblMensaje.setText( "<html><center> <b> Por favor</b> espere estamos procesando <p> su información </center></html>" );
        gridBagConstraints = new GridBagConstraints( );
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        add( lblMensaje, gridBagConstraints );
    }

}
