package uniandes.cupi2.cupiOca.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Dialogo usado para seleccionar un archivo de configuración del tablero de juego. Utiliza JButtonGroup y JRadioButton para escoger una única configuración
 * 
 */
public class DialogoSeleccionarArchivo extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante con la ruta de la carpeta donde se encuentran las configuraciones del juego
     */
    private final static String RUTA_CONFIGURACIONES = "./data/mundos";

    /**
     * Constante para aceptar la selección de una configuración
     */
    private final static String ACEPTAR = "Aceptar";
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Grupo de botones que agrupa a todos los radio buttons usados para seleccionar un archivo de configuración
     */
    private ButtonGroup grupoBotones;

    /**
     * El panel donde se ubica el botón Aceptar
     */
    private JPanel panelBotones;

    /**
     * Botón usado para confirmar la selección un archivo de configuración
     */
    private JButton btnAceptar;

    /**
     * RadioButtons para los archivos a seleccionar
     */
    private JRadioButton[] radioButtonsArchivos;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazCupiOca principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo dialogo para que el usuario seleccione un archivo de configuración del tablero de juego
     * @param interfaz La ventana principal de la aplicación
     */
    public DialogoSeleccionarArchivo( InterfazCupiOca interfaz )
    {
        super( interfaz, true );
        setMinimumSize( new Dimension( 250, 50 ) );
        setLayout( new BorderLayout( ) );
        panelBotones = new JPanel( );
        panelBotones.setSize( 300, 100 );
        setTitle( "Seleccione un archivo" );
        principal = interfaz;

        grupoBotones = new ButtonGroup( );

        inicializarRadioButtons( );
        panelBotones.setLayout( new GridLayout( radioButtonsArchivos.length, 1 ) );

        // TODO agregar los radioButtons al grupo de botones y al panelBotones

        radioButtonsArchivos[ 0 ] = new JRadioButton( "Mundo Intermedio.oca" );
        radioButtonsArchivos[ 1 ] = new JRadioButton( "Mundo Principiantes.oca" );
        radioButtonsArchivos[ 1 ].setSelected( true );

        radioButtonsArchivos[ 0 ].setEnabled( true );
        radioButtonsArchivos[ 1 ].setEnabled( true );

        grupoBotones.add( radioButtonsArchivos[ 0 ] );
        grupoBotones.add( radioButtonsArchivos[ 1 ] );
        
        panelBotones.add( radioButtonsArchivos[ 0 ] );
        panelBotones.add( radioButtonsArchivos[ 1 ] );

        add( panelBotones, BorderLayout.CENTER );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        add( btnAceptar, BorderLayout.SOUTH );

        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        pack( );
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método para el manejo de eventos. En este caso es la selección de un archivo de configuración
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            String archivoSeleccionado = "";
            boolean encontroSeleccionado = false;
            for( int i = 0; i < radioButtonsArchivos.length; i++ )
            {
                JRadioButton temp = radioButtonsArchivos[ i ];
                if( temp.isSelected( ) )
                {
                    archivoSeleccionado = temp.getText( );
                    encontroSeleccionado = true;
                }
            }
            if( encontroSeleccionado )
            {
                principal.cargarDatos( RUTA_CONFIGURACIONES + "\\" + archivoSeleccionado );
                super.dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( null, "Seleccione un mundo", "Información", JOptionPane.INFORMATION_MESSAGE );
            }
        }

    }

    /**
     * Lista de archivos que se encuentran en la RUTA_CONFIGURACIONES con la extensión .oca
     */
    private void inicializarRadioButtons( )
    {
        ArrayList resArchivos = new ArrayList( );
        File carpeta = new File( RUTA_CONFIGURACIONES );
        if( carpeta.isDirectory( ) )
        {
            File[] archivos = carpeta.listFiles( );
            for( int i = 0; i < archivos.length; i++ )
            {
                File file = archivos[ i ];
                if( file.getName( ).endsWith( "oca" ) )
                    resArchivos.add( file );
            }
        }
        radioButtonsArchivos = new JRadioButton[resArchivos.size( )];
        for( int i = 0; i < radioButtonsArchivos.length; i++ )
        {
            radioButtonsArchivos[ i ] = new JRadioButton( ( ( File )resArchivos.get( i ) ).getName( ) );

        }
    }

}
