/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoEscribirMicroBlog.java,v 1.9 2010/04/27 22:29:11 carl-veg Exp $
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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Dialogo para escribir un nuevo microblog
 */
public class DialogoEscribirMicroBlog extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Es el comando de la opción registrar microblog
     */
    private static final String REGISTRAR_MICROBLOG = "REGISTRAR_MICROBLOG";

    /**
     * Es el comando de la opción cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz del cliente
     */
    private InterfazCliente principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Texto del microblog
     */
    private JTextArea txtTexto;

    /**
     * Label ingresar texto
     */
    private JLabel lblIngresarTexto;

    /**
     * Botón registrar microblog
     */
    private JButton btnRegistrarMicroBlog;

    /**
     * Botón Cancelar
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo dialogo escribir microblog
     * 
     * @param nInterfazCliente Referencia a la venta principal
     */
    public DialogoEscribirMicroBlog( InterfazCliente nInterfazCliente )
    {
        super( nInterfazCliente, true );
        setSize( 330, 250 );
        setLocationRelativeTo( null );
        principal = nInterfazCliente;
        setTitle( "Nuevo Microblog" );
        setLayout( new BorderLayout( ) );
        JPanel panelBotones = new JPanel( );
        lblIngresarTexto = new JLabel( "Ingrese el texto de su microblog:" );
        txtTexto = new JTextArea( 5, 20 );
        txtTexto.setLineWrap( true );
        txtTexto.setWrapStyleWord( true );

        btnRegistrarMicroBlog = new JButton( "Registrar MicroBlog" );
        btnRegistrarMicroBlog.addActionListener( this );
        btnRegistrarMicroBlog.setActionCommand( REGISTRAR_MICROBLOG );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        panelBotones.setLayout( new GridLayout( 1, 2 ) );
        panelBotones.add( btnRegistrarMicroBlog );
        panelBotones.add( btnCancelar );

        JScrollPane scrollPane = new JScrollPane( txtTexto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        add( lblIngresarTexto, BorderLayout.NORTH );
        add( scrollPane, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String command = e.getActionCommand( );
        if( command.equals( REGISTRAR_MICROBLOG ) )
        {
            if( txtTexto.getText( ) != null && txtTexto.getText( ).length( ) > 0 )
            {
                if( txtTexto.getText( ).length( ) > 140 )
                {
                    principal.mostrarMsjError( "El tamaño máximo del microblog es 140 caracteres, tamaño actual:" + txtTexto.getText( ).length( ) );
                    return;
                }
                principal.escribirMibroblog( txtTexto.getText( ) );
                dispose( );
            }
        }
        else if( command.equals( CANCELAR ) )
        {
            dispose( );
        }

    }

}
