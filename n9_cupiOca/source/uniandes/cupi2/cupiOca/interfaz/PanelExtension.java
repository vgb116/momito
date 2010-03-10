/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.20 2010/02/22 23:21:33 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupiOca
 * Autor: Juan David Villa - 14-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiOca.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiOca.mundo.Casilla;
import uniandes.cupi2.cupiOca.mundo.Jugador;

/**
 * Panel de manejo de extensiones y opciones del juego (Eliminar un jugador y Dar el n�mero de casillas de cada tipo)
 */
public class PanelExtension extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando n�mero casillas
     */
    private static final String NUMERO_CASILLAS = "NUMERO_CASILLAS";

    /**
     * Comando n�mero casillas
     */
    private static final String ELIMINAR_JUGADOR = "ELIMINAR_JUGADOR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupiOca principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;

    /**
     * Bot�n N�mero casillas
     */
    private JButton btnNumCasillas;

    /**
     * Bot�n Eliminar jugador
     */
    private JButton btnEliminarJugador;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazCupiOca ventana )
    {
        principal = ventana;

        setLayout( new GridLayout( 1, 2 ) );
        setBorder( new TitledBorder( "Opciones" ) );

        // Bot�n n�mero casillas
        btnNumCasillas = new JButton( "N�mero Casillas" );
        btnNumCasillas.setActionCommand( NUMERO_CASILLAS );
        btnNumCasillas.addActionListener( this );
        add( btnNumCasillas );

        // Bot�n eliminar jugador
        btnEliminarJugador = new JButton( "Eliminar Jugador" );
        btnEliminarJugador.setActionCommand( ELIMINAR_JUGADOR );
        btnEliminarJugador.addActionListener( this );
        add( btnEliminarJugador );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }

        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }

        else if( NUMERO_CASILLAS.equals( e.getActionCommand( ) ) )
        {

            String[] valoresPosibles = new String[8];
            valoresPosibles[ 0 ] = Casilla.TIPO_CALAVERA;
            valoresPosibles[ 1 ] = Casilla.TIPO_CARCEL;
            valoresPosibles[ 2 ] = Casilla.TIPO_INICIO;
            valoresPosibles[ 3 ] = Casilla.TIPO_OCA;
            valoresPosibles[ 4 ] = Casilla.TIPO_POSADA;
            valoresPosibles[ 5 ] = Casilla.TIPO_PUENTE;
            valoresPosibles[ 6 ] = Casilla.TIPO_RODADERO;
            valoresPosibles[ 7 ] = Casilla.TIPO_VACIA;

            String tipoCasilla = ( String )JOptionPane.showInputDialog( null, "Escoja un tipo de casilla", "N�mero de casillas", JOptionPane.INFORMATION_MESSAGE, null, valoresPosibles, valoresPosibles[ 0 ] );
            if( tipoCasilla != null )
            {
                int numCasillas = principal.darNumCasillas( tipoCasilla );

                JOptionPane.showMessageDialog( null, "El n�mero de casillas tipo " + tipoCasilla + " es " + numCasillas, "N�mero de casillas", JOptionPane.INFORMATION_MESSAGE );
            }
        }

        else if( ELIMINAR_JUGADOR.equals( e.getActionCommand( ) ) )
        {

            int numJugadores = principal.darNumJugadores( );
            if( numJugadores > 0 )
            {
                String[] valoresPosibles = new String[numJugadores];
                Jugador enTurno = principal.darJugadorEnTurno( );
                valoresPosibles[ 0 ] = enTurno.darNick( );
                int i = 1;
                Jugador temp = enTurno.darSiguiente( );
                while( temp != enTurno )
                {
                    valoresPosibles[ i ] = temp.darNick( );
                    temp = temp.darSiguiente( );
                    i++;

                }
                String nickAEliminar = ( String )JOptionPane.showInputDialog( null, "Escoja el jugador que desea eliminar", "Eliminar Jugador", JOptionPane.INFORMATION_MESSAGE, null, valoresPosibles, valoresPosibles[ 0 ] );
                if( nickAEliminar != null )
                {
                    try
                    {
                        principal.eliminarJugador( nickAEliminar );
                        JOptionPane.showMessageDialog( null, "El Jugador " + nickAEliminar + " ha sido eliminado", "Jugador eliminado", JOptionPane.INFORMATION_MESSAGE );
                    }
                    catch( Exception e1 )
                    {
                        JOptionPane.showMessageDialog( null, e1.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
                    }

                }

            }
            else
            {
                JOptionPane.showMessageDialog( null, "No hay ning�n jugador registrado", "ERROR", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

}
