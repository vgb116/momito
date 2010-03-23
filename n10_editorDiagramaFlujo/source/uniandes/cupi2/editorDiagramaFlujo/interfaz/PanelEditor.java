/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: PanelEditor.java,v 1.3 2009/10/21 15:57:51 carl-veg Exp $ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Carlos Andrés Vega - 22/11/2007 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.editorDiagramaFlujo.interfaz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.editorDiagramaFlujo.mundo.DiagramaFlujo;
import uniandes.cupi2.editorDiagramaFlujo.mundo.IFormaBasica;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

/**
 * Este es el panel que maneja la interacción con el ratón y muestra los elementos del diagrama de flujo dibujados
 */
public class PanelEditor extends JPanel implements MouseListener, MouseMotionListener
{
    
    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al diagrama de flujo
     */
    private DiagramaFlujo diagrama;

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazEditorDiagramaFlujo principal;

    /**
     * Cuenta el número de clicks que ha realizado el usuario para agregar una asociación
     */
    private int numeroClick;

    /**
     * Elemento inicial seleccionado para realizar una asociación
     */
    private IFormaBasica elementoInicial;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel donde se realizara el diagrama de flujo
     * @param ventana Es una referencia a la clase principal de la interfaz
     * @param diagramaFlujo Es una referencia al diagrama de flujo
     */
    public PanelEditor( InterfazEditorDiagramaFlujo ventana, DiagramaFlujo diagramaFlujo )
    {
        principal = ventana;
        diagrama = diagramaFlujo;
        numeroClick = 0;

        addMouseListener( this );
        addMouseMotionListener( this );

        setDoubleBuffered( true );
        setBorder( new TitledBorder( "" ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de actualizar la visualización de la composición
     */
    public void actualizar( )
    {
        validate( );
        repaint( );
    }

    /**
     * Este es el método llamado por la máquina virtual cuando hay que volver a dibujar el panel<br>
     * <code> super.paint( g ) </code> no sabe pintar las figuras, así que hay que sobrecargar el método.
     * @param g Es la superficie del panel
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        Graphics2D g2 = ( Graphics2D )g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setColor( getBackground( ) );
        g2.fillRect( 0, 0, getWidth( ), getHeight( ) );
        // Dibujar las figuras
        diagrama.dibujar( g2 );
    }

    /**
     * Este método no se implementa
     * @param evento El evento
     */
    public void mouseClicked( MouseEvent evento )
    {
        // No se requiere
    }
    
    /**
     * Este método se llama cuando se hace click sobre la superficie del editor. <br>
     * Debe tener en cuenta las siguientes opciones al presionar un botón del mouse: <br>
     * <b>1. </b>Si la opción seleccionada en el panel de botones es SELECCIONAR: <br>
     * <b> a. </b>Al hacer un click en el botón izquierdo sobre el editor, se debe seleccionar la figura que se encuentra en la posición.<br>
     * <b> b. </b>Al hacer dos clicks en el botón izquierdo sobre el editor, se debe mostrar la ventana para editar el texto de la figura.<br><br>
     * <b>2. </b>Si la opción seleccionada en el panel de botones es una de las figuras (inicio, fin, entrada de datos, proceso de datos, decisión, salida información), debe pintarlo.<br>
     * <b>3. </b>Si la opción seleccionada en el panel de botones es una conexión debe:.<br>
     * <b> a. </b>Si es el primer click, debe seleccionar la figura que se encuentre en la posición del click.<br>
     * <b> b. </b>Si es el segundo click, debe pintar un conector entre la figura que estaba seleccionada y la que está en la posición del click.<br>
     * Por último, se debe actualizar el editor
     * @param evento Es el evento del click sobre el editor
     */
    public void mousePressed( MouseEvent evento )
    {
        if( evento.getButton( ) == MouseEvent.BUTTON1 )
        {
            String opcion = principal.darOpcionSeleccionada( );
            Punto punto = new Punto( evento.getX( ), evento.getY( ) );
            if( opcion.equals( InterfazEditorDiagramaFlujo.SELECCIONAR ) )
            {
                
            	// TODO invocar el método seleccionar de la ventana principal.	
                principal.seleccionar( punto );
                
                if( evento.getClickCount( ) > 1 )
                {
                    principal.mostrarVentanaTexto( );
                }
            }
            else if( opcion.equals( InterfazEditorDiagramaFlujo.INICIO ) || opcion.equals( InterfazEditorDiagramaFlujo.FIN ) || opcion.equals( InterfazEditorDiagramaFlujo.ENTRADA ) || opcion.equals( InterfazEditorDiagramaFlujo.PROCESO_DATOS ) || opcion.equals( InterfazEditorDiagramaFlujo.DECISION ) || opcion.equals( InterfazEditorDiagramaFlujo.SALIDA_DE_INFORMACION ) )
            {
            	// TODO invocar el método agregarFigura de la ventana principal.	
               if (opcion.equals( InterfazEditorDiagramaFlujo.INICIO ))
               {
                   principal.agregarFigura( punto );
               }
            }
            else if( opcion.equals( InterfazEditorDiagramaFlujo.CONEXION ))  {
                if( numeroClick == 0 )
                {
                    IFormaBasica elementoTemp = ( IFormaBasica )principal.buscarElemento( punto );
                    if( elementoTemp != null )
                    {
                        elementoInicial = elementoTemp;
                        principal.seleccionar( punto );
                        numeroClick = 1;
                    }
                }
                else
                {
                    IFormaBasica elementoTemp = ( IFormaBasica )principal.buscarElemento( punto );
                    if( elementoTemp != null )
                    {
                    	// TODO invocar el método agregarConector de la ventana principal.	

                        numeroClick = 0;
                    }

                }

            }
        }
        actualizar( );
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseReleased( MouseEvent arg0 )
    {
        // No se requiere
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseEntered( MouseEvent arg0 )
    {
        // No se requiere
    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseExited( MouseEvent arg0 )
    {
        // No se requiere
    }

    /**
     * Este método se llama cuando se arrastra un elemento sobre la superficie del editor.
     * @param evento Es el evento de arrastrar una elemento sobre el editor
     */
    public void mouseDragged( MouseEvent evento )
    {
        if( principal.darFiguraSeleccionada( ) != null && principal.darOpcionSeleccionada( ).equals( InterfazEditorDiagramaFlujo.SELECCIONAR ) )
        {
            principal.moverFiguraSeleccionada( new Punto( evento.getX( ), evento.getY( ) ) );
            actualizar( );
        }

    }

    /**
     * Este método no se implementa
     * @param arg0 El evento
     */
    public void mouseMoved( MouseEvent arg0 )
    {
        // No se requiere
    }

}