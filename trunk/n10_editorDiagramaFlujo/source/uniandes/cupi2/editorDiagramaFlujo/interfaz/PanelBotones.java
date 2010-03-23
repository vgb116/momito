/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotones.java,v 1.3 2009/10/07 14:43:38 c.alvarez947 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se encuentran los botones y controles de la aplicaci�n
 */
public class PanelBotones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante Borrar
     */
    private static final String BORRAR = "Borrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazEditorDiagramaFlujo principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el bot�n para seleccionar figuras
     */
    private JToggleButton botonSeleccionar;

    /**
     * Es el bot�n para agregar un elemento de inicio
     */
    private JToggleButton botonInicio;

    /**
     * Es el bot�n para agregar un el elemento de finalizaci�n
     */
    private JToggleButton botonFin;

    /**
     * Es el bot�n para agregar una entrada
     */
    private JToggleButton botonEntrada;

    /**
     * Es el bot�n para agregar un elemento de procesamiento de datos
     */
    private JToggleButton botonProcesamientoDatos;

    /**
     * Es el bot�n para agregar un elemento de decisi�n
     */
    private JToggleButton botonDecision;

    /**
     * Es el bot�n para agregar una conexi�n
     */
    private JToggleButton botonFlechaConexion;

    /**
     * Es el bot�n para agregar un elemento de salida de informaci�n
     */
    private JToggleButton botonSalidaInformacion;

    /**
     * Es el bot�n para eliminar una figura
     */
    private JButton botonBorrar;

    /**
     * Es el grupo de los botones
     */
    private ButtonGroup grupo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ventana Es una referencia a la clase principal de la interfaz
     */
    public PanelBotones( InterfazEditorDiagramaFlujo ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "" ) );

        botonSeleccionar = new JToggleButton( new ImageIcon( "./data/icons/select.png" ) );
        botonSeleccionar.setPreferredSize( new Dimension( 60, 40 ) );
        botonSeleccionar.setToolTipText( "Seleccionar" );

        botonInicio = new JToggleButton( new ImageIcon( "./data/icons/inicio.png" ) );
        botonInicio.setPreferredSize( new Dimension( 60, 40 ) );
        botonInicio.setToolTipText( "Inicio" );

        botonFin = new JToggleButton( new ImageIcon( "./data/icons/fin.png" ) );
        botonFin.setPreferredSize( new Dimension( 60, 40 ) );
        botonFin.setToolTipText( "Fin" );

        botonEntrada = new JToggleButton( new ImageIcon( "./data/icons/entrada.png" ) );
        botonEntrada.setPreferredSize( new Dimension( 60, 40 ) );
        botonEntrada.setToolTipText( "Entrada" );

        botonBorrar = new JButton( new ImageIcon( "./data/icons/cancel.png" ) );
        botonBorrar.setPreferredSize( new Dimension( 60, 40 ) );
        botonBorrar.setActionCommand( BORRAR );
        botonBorrar.addActionListener( this );
        botonBorrar.setToolTipText( "Borrar" );

        botonProcesamientoDatos = new JToggleButton( new ImageIcon( "./data/icons/proceso.png" ) );
        botonProcesamientoDatos.setPreferredSize( new Dimension( 60, 40 ) );
        botonProcesamientoDatos.setToolTipText( "Procesamiento de Datos" );

        botonDecision = new JToggleButton( new ImageIcon( "./data/icons/decision.png" ) );
        botonDecision.setPreferredSize( new Dimension( 60, 40 ) );
        botonDecision.setToolTipText( "Decisi�n" );

        botonFlechaConexion = new JToggleButton( new ImageIcon( "./data/icons/conexion.png" ) );
        botonFlechaConexion.setPreferredSize( new Dimension( 60, 40 ) );
        botonFlechaConexion.setToolTipText( "Conexi�n" );

        botonSalidaInformacion = new JToggleButton( new ImageIcon( "./data/icons/salida.png" ) );
        botonSalidaInformacion.setPreferredSize( new Dimension( 60, 40 ) );
        botonSalidaInformacion.setToolTipText( "Salida de Informaci�n" );

        grupo = new ButtonGroup( );
        grupo.add( botonSeleccionar );
        grupo.add( botonInicio );
        grupo.add( botonFin );
        grupo.add( botonEntrada );
        grupo.add( botonProcesamientoDatos );
        grupo.add( botonDecision );
        grupo.add( botonFlechaConexion );
        grupo.add( botonSalidaInformacion );

        setLayout( new GridBagLayout( ) );
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 3, 3, 3, 3 ), 0, 0 );

        add( botonSeleccionar, gbc );

        gbc.gridx = 1;
        add( botonBorrar, gbc );

        gbc.gridx = 0;
        gbc.gridy = 2;
        add( botonInicio, gbc );

        gbc.gridx = 1;
        add( botonFin, gbc );

        gbc.gridx = 0;
        gbc.gridy = 3;
        add( botonEntrada, gbc );

        gbc.gridx = 1;
        add( botonProcesamientoDatos, gbc );

        gbc.gridx = 0;
        gbc.gridy = 4;
        add( botonDecision, gbc );

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add( botonFlechaConexion, gbc );

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add( botonSalidaInformacion, gbc );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la figura seleccionada
     * @return Retorna InterfazEditorDiagramaFlujo.NINGUNA, InterfazEditorDiagramaFlujo.SELECCIONAR, InterfazEditorDiagramaFlujo.FIN, InterfazEditorDiagramaFlujo.INICIO,
     *         InterfazEditorDiagramaFlujo.ENTRADA, InterfazEditorDiagramaFlujo.DECISION, InterfazEditorDiagramaFlujo.CONEXION,
     *         InterfazEditorDiagramaFlujo.SALIDA_DE_INFORMACION, o InterfazEditorDiagramaFlujo.PROCESO_DATOS
     */
    public String darFigura( )
    {
        String tipoSeleccionado = InterfazEditorDiagramaFlujo.NINGUNA;

        if( botonSeleccionar.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.SELECCIONAR;
        }
        else if( botonFin.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.FIN;
        }
        else if( botonInicio.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.INICIO;
        }
        else if( botonEntrada.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.ENTRADA;
        }
        else if( botonDecision.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.DECISION;
        }
        else if( botonFlechaConexion.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.CONEXION;
        }
        else if( botonSalidaInformacion.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.SALIDA_DE_INFORMACION;
        }
        else if( botonProcesamientoDatos.isSelected( ) )
        {
            tipoSeleccionado = InterfazEditorDiagramaFlujo.PROCESO_DATOS;
        }

        return tipoSeleccionado;
    }

    /**
     * Selecciona el bot�n de seleccionar
     */
    public void cambiarASeleccionar( )
    {
        botonSeleccionar.setSelected( true );
    }

    /**
     * Es el m�todo que se llama cuando se hace click sobre un bot�n
     * @param evento Es el evento del click sobre un bot�n- evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( BORRAR.equals( comando ) )
        {
            principal.eliminarFiguraSeleccionada( );
        }
    }

}