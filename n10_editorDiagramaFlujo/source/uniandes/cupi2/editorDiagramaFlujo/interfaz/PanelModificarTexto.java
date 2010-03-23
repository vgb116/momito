/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelModificarTexto.java,v 1.2 2009/10/07 14:43:38 c.alvarez947 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Carlos Andr�s Vega - 22/11/2007 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.editorDiagramaFlujo.interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Este es el panel en el que se modifican las propiedades del texto de una figura
 */
public class PanelModificarTexto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante fuente
     */
    private static final String FUENTE = "Fuente";

    /**
     * Constante tama�o
     */
    private static final String TAMANO = "Tama�o";

    /**
     * Constante Estilo
     */
    private static final String ESTILO = "Estilo";

    /**
     * Constante cancelar
     */
    private static final String CANCELAR = "Cancelar";

    /**
     * Constante OK
     */
    private static final String OK = "Ok";

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es una referencia al di�logo que contiene este panel
     */
    private DialogoTexto dialogo;

    /**
     * Es el comboBox para seleccionar la fuente
     */
    private JComboBox cbbFuente;

    /**
     * Es el comboBox para seleccionar el tama�o de la fuente
     */
    private JComboBox cbbTamano;

    /**
     * Es el comboBox para seleccionar el estilo de la fuente (normal, negrilla, it�lica)
     */
    private JComboBox cbbEstilo;

    /**
     * Es el campo de texto donde se muestra el texto que quedar� en la figura
     */
    private JTextField txtTexto;

    /**
     * Es el bot�n Ok
     */
    private JButton botonOk;

    /**
     * Es el bot�n Cancelar
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el panel
     * @param dt Es una referencia a la clase principal de la interfaz
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    public PanelModificarTexto( DialogoTexto dt, ArrayList fuentes )
    {
        dialogo = dt;

        setLayout( new GridBagLayout( ) );

        // Inicializa los componentes
        inicializarComponentes( fuentes );

        // Ubica los componentes
        ubicarComponentes( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ubica los componentes en sus posiciones. <br>
     * <b>pre: </b>Los componentes ya est�n inicializados
     */
    private void ubicarComponentes( )
    {
        GridBagConstraints gbc;

        gbc = new GridBagConstraints( 0, 0, 3, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( txtTexto, gbc );

        gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbFuente, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbTamano, gbc );

        gbc = new GridBagConstraints( 2, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( cbbEstilo, gbc );

        JPanel panelBotones = new JPanel( new GridLayout( 1, 2, 10, 10 ) );
        panelBotones.add( botonOk );
        panelBotones.add( botonCancelar );
        gbc = new GridBagConstraints( 0, 2, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbc );
    }

    /**
     * Inicializa los componentes
     * @param fuentes Es una lista con los nombres de las fuentes instaladas en el sistema
     */
    private void inicializarComponentes( ArrayList fuentes )
    {
        cbbFuente = new JComboBox( );
        cbbFuente.setActionCommand( FUENTE );
        for( int i = 0; i < fuentes.size( ); i++ )
        {
            String nombreFuente = ( String )fuentes.get( i );
            cbbFuente.addItem( nombreFuente );
        }
        cbbFuente.addActionListener( this );

        cbbTamano = new JComboBox( );
        cbbTamano.setActionCommand( TAMANO );
        for( int i = 8; i <= 32; i += 2 )
        {
            cbbTamano.addItem( new Integer( i ) );
        }
        cbbTamano.addActionListener( this );

        cbbEstilo = new JComboBox( );
        cbbEstilo.setActionCommand( TAMANO );
        cbbEstilo.addItem( "Normal" );
        cbbEstilo.addItem( "Negrita" );
        cbbEstilo.addItem( "It�lica" );
        cbbEstilo.addActionListener( this );

        txtTexto = new JTextField( );

        botonOk = new JButton( "Ok" );
        botonOk.setActionCommand( OK );
        botonOk.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
    }

    /**
     * Cambia la fuente del texto mostrado por la seleccionada actualmente en los combo boxes.
     */
    public void actualizarFuente( )
    {
        String nombreFuente = ( String )cbbFuente.getSelectedItem( );
        int tamano = ( ( Integer )cbbTamano.getSelectedItem( ) ).intValue( );
        int numEstilo = cbbEstilo.getSelectedIndex( );

        int estilo = -1;
        switch( numEstilo )
        {
            case 0:
                estilo = Font.PLAIN;
                break;
            case 1:
                estilo = Font.BOLD;
                break;
            case 2:
                estilo = Font.ITALIC;
                break;
            default:
                estilo = Font.PLAIN;
        }

        Font fuente = new Font( nombreFuente, estilo, tamano );
        txtTexto.setFont( fuente );
    }

    /**
     * Cambia el texto mostrado y actualiza los combo box para mostrar la fuente indicada
     * @param texto El texto que se va a mostrar
     * @param fuente La fuente actual del texto
     */
    public void cambiarTexto( String texto, Font fuente )
    {
        txtTexto.setText( texto );

        String nombreFuente = fuente.getFamily( );
        int estilo = fuente.getStyle( );
        int tamano = fuente.getSize( );

        boolean encontre = false;
        for( int i = 0; i < cbbFuente.getItemCount( ) && !encontre; i++ )
        {
            String nombre = ( String )cbbFuente.getItemAt( i );
            if( nombre.equals( nombreFuente ) )
            {
                encontre = true;
                cbbFuente.setSelectedIndex( i );
            }
        }

        encontre = false;
        for( int i = 0; i < cbbTamano.getItemCount( ) && !encontre; i++ )
        {
            Integer num = ( Integer )cbbTamano.getItemAt( i );
            if( tamano == num.intValue( ) )
            {
                encontre = true;
                cbbTamano.setSelectedIndex( i );
            }
        }

        switch( estilo )
        {
            case Font.PLAIN:
                cbbEstilo.setSelectedIndex( 0 );
                break;
            case Font.BOLD:
                cbbEstilo.setSelectedIndex( 1 );
                break;
            case Font.ITALIC:
                cbbEstilo.setSelectedIndex( 2 );
                break;
            default:
                cbbEstilo.setSelectedIndex( 0 );
        }
    }

    /**
     * Es el m�todo que ejecuta la acci�n que se debe ejecutar cuando se hace click sobre un bot�n o cuando cambia la opci�n seleccionada en uno de los comboBox
     * @param evento Es el evento del click sobre un bot�n - evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( OK.equals( comando ) )
        {
            String texto = txtTexto.getText( );
            Font fuente = txtTexto.getFont( );

            dialogo.cambiarTexto( texto, fuente );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
        else if( FUENTE.equals( comando ) || TAMANO.equals( comando ) || ESTILO.equals( comando ) )
        {
            actualizarFuente( );
        }
    }

}