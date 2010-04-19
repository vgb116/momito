package uniandes.cupi2.conejera.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.conejera.mundo.Conejera;
import uniandes.cupi2.conejera.mundo.ParejaConejos;

/**
 * Clase que representa gráficamente el árbol genealógico de la conejera
 * 
 */
public class PanelGraficoConejera extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante con la ruta de la imagen de una pareja de conejos adultos
     */
    private final static String RUTA_PAREJA_ADULTA = "./data/imagenes/adultos.png";

    /**
     * Constante con la ruta de la imagen de una pareja de conejos bebés
     */
    private final static String RUTA_PAREJA_BEBE = "./data/imagenes/bebes.png";

    /**
     * El ancho en pixeles de la representación de una pareja de conejos
     */
    private static final int ANCHO = 120;

    /**
     * El alto en pixeles de la representación de una pareja de conejos
     */
    private static final int ALTO = 80;

    /**
     * El tamaño de los bordes izquierdo y derecho
     */
    private static final int BORDE_X = 20;

    /**
     * El tamaño de los bordes superior e inferior
     */
    private static final int BORDE_Y = 20;

    /**
     * El color del borde de las cajas
     */
    private static final Color COLOR_BORDE = Color.WHITE;

    /**
     * El color de las líneas que unen a las cajas
     */
    private static final Color COLOR_LINEAS = Color.ORANGE;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La clase principal de la aplicación
     */
    private Conejera conejera;

    /**
     * Una pareja de conejos que se desea dibujar
     */
    private ParejaConejos parejaConejos;

    /**
     * Determina si se está mostrando la conejera o una pareja
     */
    private boolean mostrarConejera;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * La imagen generada con la conejera
     */
    private BufferedImage imagenConejera;

    /**
     * La imagen generada con una pareja
     */
    private BufferedImage imagenPareja;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un panel que representa gráficamente la conejera
     * @param laConejera Las parejas de conejos ordenadas según un orden genealogico
     */
    public PanelGraficoConejera( Conejera laConejera )
    {
        conejera = laConejera;
        setDoubleBuffered( true );
        mostrarConejera = true;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método se encarga de repintar el panel copiando la imagen generada
     * @param g La superficie del panel donde debe pintarse la imagen. g != null.
     */

    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        if( mostrarConejera )
            dibujarConejera( g );
        else
            dibujarPareja( g );
    }

    /**
     * Dibuja la imagen generada de la pareja sobre la superficie del panel
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    private void dibujarPareja( Graphics g )
    {
        g.clearRect( 0, 0, getWidth( ), getHeight( ) );

        if( imagenPareja == null )
            actualizarImagen( );

        if( imagenPareja.getWidth( ) > getWidth( ) )
            g.drawImage( imagenPareja, 0, 0, null );
        else
        {
            g.drawImage( imagenPareja, ( getWidth( ) - imagenPareja.getWidth( ) ) / 2, 0, null );
        }
    }

    /**
     * Dibuja la imagen generada de la conejera sobre la superficie del panel
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    private void dibujarConejera( Graphics g )
    {
        setBackground( Color.WHITE );

        BasicStroke continuo = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        ( ( Graphics2D )g ).setStroke( continuo );

        if( imagenConejera == null )
            actualizarImagen( );

        if( imagenConejera.getWidth( ) > getWidth( ) )
            g.drawImage( imagenConejera, 0, 0, null );
        else
        {
            g.drawImage( imagenConejera, ( getWidth( ) - imagenConejera.getWidth( ) ) / 2, 0, null );
        }

    }

    /**
     * Actualiza la imagen generada con la información actual de la conejera
     */
    public void actualizarImagen( )
    {
        if( conejera != null && mostrarConejera )
        {
            ParejaConejos raiz = conejera.darRaiz( );
            if( raiz != null )
            {
                imagenConejera = dibujarParejaConHijos( raiz );
                setPreferredSize( new Dimension( imagenConejera.getWidth( ), imagenConejera.getHeight( ) ) );
                setSize( new Dimension( imagenConejera.getWidth( ), imagenConejera.getHeight( ) ) );
            }
            else
            {
                imagenConejera = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
                setSize( new Dimension( getWidth( ) - 1, getHeight( ) - 1 ) );
            }
        }
        else if( parejaConejos != null && !mostrarConejera )
        {
            setPreferredSize( new Dimension( imagenPareja.getWidth( ), imagenPareja.getHeight( ) ) );
            setSize( new Dimension( imagenPareja.getWidth( ), imagenPareja.getHeight( ) ) );
        }
        validate( );
        repaint( );

    }

    /**
     * Este método se encarga de dibujar una pareja y a todos sus hijos
     * @param pareja La pareja que debe ser dibujada
     * @return imagenCompleta La imagen de la pareja y todos su hijos
     */
    private BufferedImage dibujarParejaConHijos( ParejaConejos pareja )
    {

        if( pareja.esHoja( ) )
        {
            return dibujarPareja( pareja );
        }

        ArrayList hijos = pareja.darHijos( );
        ArrayList imagenes = new ArrayList( );
        int ancho = 0;
        int alto = 0;

        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            BufferedImage imagenHijo = dibujarParejaConHijos( hijo );
            ancho += imagenHijo.getWidth( );
            alto = Math.max( alto, imagenHijo.getHeight( ) );
            imagenes.add( imagenHijo );
        }

        alto += ALTO + BORDE_Y * 2;
        BufferedImage imagenElemento = dibujarPareja( pareja );

        BufferedImage imagenCompleta = new BufferedImage( ancho, alto, BufferedImage.TYPE_INT_RGB );
        Graphics2D superficie = imagenCompleta.createGraphics( );

        superficie.fillRect( 0, 0, ancho, alto );

        // Dibujar el elemento
        int posElemento = ( ancho / 2 ) - ( ANCHO / 2 ) - BORDE_X;
        superficie.drawImage( imagenElemento, posElemento, 0, null );

        // Agregar a la imagen completa las imágenes de cada uno de los hijos
        int posXActual = 0;
        int posYActual = ALTO + BORDE_Y * 2;
        for( int i = 0; i < imagenes.size( ); i++ )
        {
            BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
            superficie.drawImage( imagenHijo, posXActual, posYActual, null );
            int anchoHijo = imagenHijo.getWidth( );
            posXActual += anchoHijo;
        }

        posXActual = 0;
        BasicStroke continuo = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        ( ( Graphics2D )superficie ).setStroke( continuo );

        // Dibujar las líneas
        for( int i = 0; i < imagenes.size( ); i++ )
        {
            BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
            int anchoHijo = imagenHijo.getWidth( );
            superficie.setPaint( COLOR_LINEAS );
            superficie.drawLine( ancho / 2, ALTO + BORDE_Y, posXActual + anchoHijo / 2, posYActual + BORDE_Y );
            posXActual += anchoHijo;
        }

        return imagenCompleta;
    }

    /**
     * Este método dibuja una pareja sin hijos aunque los tenga
     * @param pareja La pareja que se va a dibujar
     * @return imagenPareja La imagen de la pareja
     */
    private BufferedImage dibujarPareja( ParejaConejos pareja )
    {
        BufferedImage ImagenPareja = null;

        // Crear la superficie para dibujar el conejo
        BufferedImage imagen = new BufferedImage( ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = imagen.createGraphics( );
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2 );

        // Dibujar el rectángulo
        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
        BasicStroke continuo = new BasicStroke( 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{ 10f, 0f }, 0 );
        ( ( Graphics2D )g ).setStroke( continuo );

        g.fill( rec );
        g.setPaint( COLOR_BORDE );
        g.draw( rec );

        // Dibujar el texto
        int centroY = Math.abs( ALTO + BORDE_Y * 2 ) / 2;

        Font fuente = new Font( "Verdana", Font.BOLD, 11 );
        g.setFont( fuente );
        g.setColor( Color.BLACK );

        try
        {
            // Selecciona la imagen de una pareja bebé o adulta
            if( pareja.darEstado( ).equals( ParejaConejos.ESTADO_BEBES ) )
                ImagenPareja = ImageIO.read( new File( RUTA_PAREJA_BEBE ) );
            else
                ImagenPareja = ImageIO.read( new File( RUTA_PAREJA_ADULTA ) );
        }

        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Hubo un error al visualizar la conejera", "Error", JOptionPane.ERROR_MESSAGE );
        }

        Image laImagen = ImagenPareja.getScaledInstance( ( int ) ( 90 ), ( int ) ( 70 ), Image.SCALE_AREA_AVERAGING );
        g.drawImage( laImagen, 28, 18, null );
        g.setPaint( Color.RED );
        g.drawString( "Id: " + pareja.darId( ), 0, centroY + 17 );
        return imagen;

    }

}
