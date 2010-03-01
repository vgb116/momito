package uniandes.cupi2.cupiOca.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Este dialogo permite agregar un nuevo jugador al juego
 */
public class DialogoIngresarJugador extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante para agregar un jugador
     */
    public final static String AGREGAR_JUGADOR = "AGREGAR";

    /**
     * Constante para seleccionar una imagen para el jugador
     */
    public final static String SELECCIONAR_IMAGEN = "SELECCIONAR IMAGEN";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * El panel principal
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta nick del jugador
     */
    private JLabel lblNick;

    /**
     * Texto nick del jugador
     */
    private JTextField txtElNick;

    /**
     * Etiqueta imagen del jugador
     */
    private JLabel lblImagen;

    /**
     * Botón seleccionar imagen del jugador
     */
    private JButton btnSelImagen;

    /**
     * Texto con la ruta de la imagen del jugador
     */
    private JTextField txtLaImagen;

    /**
     * Botón aceptar
     */
    private JButton btnAceptar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ventana principal de la aplicación
     */
    private InterfazCupiOca principal;

    /**
     * La ruta de la imagen del jugador
     */
    private String rutaImagen;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo dialogo para la creación de un nuevo jugador
     * @param ventana La ventana principal de la aplicación
     */
    public DialogoIngresarJugador( InterfazCupiOca ventana )
    {
        principal = ventana;
        rutaImagen = "";
        setSize( 300, 200 );
        setTitle( "Ingresar Jugador" );
        crearPanelPrincipal( );
        setLocationRelativeTo( null );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea el panel principal del dialogo usando un gridBagLayout y lo añade al dialogo
     */
    private void crearPanelPrincipal( )
    {
    	
    	// TODO Complete el método según la documentación
    	
    }

    /**
     * Método para manejar los eventos del dialogo, Agregar un jugador o Seleccionar la imagen de este
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {

        if( e.getActionCommand( ).equals( AGREGAR_JUGADOR ) )
        {
            String nick = txtElNick.getText( );
            if( nick.equals( "" ) )
                JOptionPane.showMessageDialog( this, "Debe ingresar un nick", "Info", JOptionPane.INFORMATION_MESSAGE );
            else if( rutaImagen.equals( "" ) )
                JOptionPane.showMessageDialog( this, "Debe seleccionar una imagen", "Info", JOptionPane.INFORMATION_MESSAGE );
            else
            {
                boolean agrego = principal.agregarJugador( nick, rutaImagen );

                if( agrego )
                {
                    super.dispose( );
                    JOptionPane.showMessageDialog( null, "Jugador Agregado!", "Nuevo Jugador", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    JOptionPane.showMessageDialog( null, "Existe un jugador con ese nick", "Nick duplicado", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        else if( e.getActionCommand( ).equals( SELECCIONAR_IMAGEN ) )
        {

            JFileChooser chooser = new JFileChooser( );
            chooser.setCurrentDirectory( new java.io.File( "./data/imagenes/jugadores" ) );
            chooser.setDialogTitle( "Seleccionar imagen" );
            chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
            chooser.setVisible( true );

            if( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
            {

                File archivoImagen = chooser.getSelectedFile( );
                try
                {
                    rutaImagen = archivoImagen.getCanonicalPath( );
                    String nombreImagen = archivoImagen.getName( );
                    txtLaImagen.setText( nombreImagen );
                }
                catch( IOException e1 )
                {
                    JOptionPane.showMessageDialog( this, e1.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }

            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe seleccionar una imagen", "Info", JOptionPane.INFORMATION_MESSAGE );

            }

        }

    }

}
