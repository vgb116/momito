/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInmuebles.java,v 1.1 2009/10/15 23:08:40 c.alvarez947 Exp $
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

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Panel con la lista de inmueble registrados
 */
public class PanelInmuebles extends JPanel implements ListSelectionListener
{

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * La lista de los inmuebles registrados
     */
	//  TODO Declarar atributo que modela la lista de inmuebles
    // nombre: lstInmuebles;

    /**
     * Scroll para la lista de inmuebles
     */
    private JScrollPane scrListaInmuebles;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la interfaz principal
     */
    private InterfazFincaRaiz principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con la lista de inmuebles
     * @param nPrincipal La ventana principal
     * 
     */
    public PanelInmuebles( InterfazFincaRaiz nPrincipal )
    {
    	principal = nPrincipal;
    	setBorder( BorderFactory.createTitledBorder( "Inmuebles Registrados" ) );
        setLayout( new GridLayout( 1, 1 ) );
        
        // TODO
        // (1) Crear la lista y (2) agregar al panel como listener de la lista


        // TODO
        // (1) Crear y configurar un scroll pane para que contenga la lista
        // (2) Agregar el scroll al panel
        
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que selecciona el elemento con el índice dado en la lista de inmuebles
     * @param i El índice del inmueble a seleccionar
     */
    public void actualizarSeleccionado( int i )
    {
    	 // TODO Completar según enunciado
        
    }

    /**
     * Método que actualiza la lista de inmuebles
     * @param array La lista de inmuebles
     */
    public void actualizarLista( Object[] array )
    {
        lstInmuebles.setListData( array );

    }

    /**
     * Método para el manejo de los  eventos de selección de la lista de inmuebles
     */
    public void valueChanged( ListSelectionEvent e )
    {
    	// TODO Completar según enunciado
        // Apóyese en el método actualizar(Inmueble nInmueble ) de la InterfazFincaRaiz
    	// Ayuda: Verifique el inmueble seleccionado sea distinto de null antes de hacer cualquier cosa
    	
    }

}
