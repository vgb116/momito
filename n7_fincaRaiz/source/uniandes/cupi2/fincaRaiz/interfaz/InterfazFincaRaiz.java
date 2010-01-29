/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazFincaRaiz.java,v 1.11 2009/10/15 23:08:40 c.alvarez947 Exp $
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
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.fincaRaiz.mundo.FincaRaiz;
import uniandes.cupi2.fincaRaiz.mundo.Inmueble;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazFincaRaiz extends JFrame 
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private FincaRaiz fincaRaiz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con la información detallada del inmueble seleccionado
     */
    private PanelInfoInmueble panelInformacionInmueble;

    /**
     * Panel con la lista de los inmuebles
     */
    private PanelInmuebles panelInmuebles;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal de la aplicación
     */
    public InterfazFincaRaiz( )
    {
        // Crea la clase principal
        fincaRaiz = new FincaRaiz( );

        // Construye la forma
        setTitle( "Finca Raíz de Los Alpes" );
        setLayout( new BorderLayout( ) );
        setSize( 815, 825 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        JPanel panelPrincipal = new JPanel( );
        panelInmuebles = new PanelInmuebles( this  );

        panelInformacionInmueble = new PanelInfoInmueble( );

        panelPrincipal.setLayout( new BorderLayout( ) );

        panelPrincipal.add( panelInmuebles, BorderLayout.WEST );
        panelPrincipal.add( panelInformacionInmueble, BorderLayout.CENTER );
        add( panelPrincipal, BorderLayout.CENTER );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        // Centrar la ventana
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método para mostrar el dialogo para registrar un inmueble
     */
    public void registrarInmueble( )
    {
        DialogoInfoInmueble dialogoInfoInmueble = new DialogoInfoInmueble( this );
        dialogoInfoInmueble.setVisible( true );
    }

    /**
     * Método para eliminar un inmueble
     */
    public void eliminarInmueble( )
    {
        String idInmueble = JOptionPane.showInputDialog( "Ingrese el identificador del inmueble a eliminar" );
        if( idInmueble != null && idInmueble.length( ) > 0 )
        {
            fincaRaiz.eliminarInmueble( idInmueble );
            actualizar( );
        }
    }

    /**
     * Método para buscar el inmueble más costos para la venta
     */
    public void buscarMasCostoso( )
    {
        int i = fincaRaiz.buscarInmuebleMasCostosoVenta( );
        if( i == -1 )
        {
            JOptionPane.showMessageDialog( this, "No hay inmuebles registrados.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            panelInmuebles.actualizarSeleccionado( i );

        }
    }

    /**
     * Método para buscar el inmueble más económico para arrendar
     */
    public void buscarMasEconomico( )
    {
        int i = fincaRaiz.buscarInmuebleMasBaratoArrendar( );
        if( i == -1 )
        {
            JOptionPane.showMessageDialog( this, "No hay inmuebles registrados.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            panelInmuebles.actualizarSeleccionado( i );

        }
    }

    /**
     * Método para buscar por la identificación del inmueble
     */
    public void buscarPorIdentificador( )
    {
        String idInmueble = JOptionPane.showInputDialog( "Ingrese el identificador del inmueble" );
        if( idInmueble != null && idInmueble.length( ) > 0 )
        {
            fincaRaiz.ordenarPorIdentificador( );
            actualizar( );
            int i = fincaRaiz.buscarBinarioPorIdentificador( idInmueble );
            if( i == -1 )
            {
                JOptionPane.showMessageDialog( this, "No se encontró un inmueble registrado con el identificador: " + idInmueble, "Búsqueda", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                panelInmuebles.actualizarSeleccionado( i );
            }
        }
    }

    /**
     * Método para ordenar por ciudad la lista de inmuebles
     */
    public void ordenarPorCiudad( )
    {
        fincaRaiz.ordenarPorCiudad( );
        actualizar( );
    }

    /**
     * Método para ordenar por precio la lista de inmuebles
     */
    public void ordenarPorPrecio( )
    {
        fincaRaiz.ordenarPorPrecio( );
        actualizar( );
    }

    /**
     * Método para ordenar por tamaño la lista de inmuebles
     */
    public void ordenarPorTamanio( )
    {
        fincaRaiz.ordenarPorTamanio( );
        actualizar( );
    }

    /**
     * Método que actualiza la interfaz según los valores del mundo
     */
    public void actualizar( )
    {
        panelInmuebles.actualizarLista( fincaRaiz.darListaInmuebles( ).toArray( new Object[0] ) );
        panelInformacionInmueble.limpiarEdicion( );
    }

    
    /**
     * Método que actualiza el inmueble seleccionado
     * @param nInmueble El inmueble seleccionado
     */
    public void actualizar(Inmueble nInmueble )
    {
        panelInformacionInmueble.actualizar( nInmueble );
    }

    /**
     * Método que crea un inmueble
     * @param nTipoInmueble El tipo de inmueble
     * @param nTipoOferta El tipo de oferta del inmueble
     * @param nCiudad La ciudad donde se encuentra el inmueble
     * @param nBarrio El barrio donde se encuentra el inmueble
     * @param nDireccion Al dirección donde se encuentra el inmueble
     * @param nTelefono El teléfono del inmueble
     * @param nTamanio El tamaño del inmueble
     * @param nPrecio El precio del inmueble
     * @param imagenes La lista de imágenes del inmueble
     */
    public void crearInmueble( String nTipoInmueble, String nTipoOferta, String nCiudad, String nBarrio, String nDireccion, String nTelefono, double nTamanio, double nPrecio, ArrayList imagenes )
    {
        fincaRaiz.agregarInmueble( nTipoInmueble, nTipoOferta, nCiudad, nBarrio, nDireccion, nTelefono, nTamanio, nPrecio, imagenes );
        panelInformacionInmueble.desHabilitarEdicion( );
        actualizar( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = fincaRaiz.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = fincaRaiz.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazFincaRaiz interfaz = new InterfazFincaRaiz( );
        interfaz.setVisible( true );
    }

}