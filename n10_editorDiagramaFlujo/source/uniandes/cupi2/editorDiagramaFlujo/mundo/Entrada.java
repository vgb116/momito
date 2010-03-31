package uniandes.cupi2.editorDiagramaFlujo.mundo;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sun.java2d.loops.DrawParallelogram;
import sun.java2d.loops.DrawPolygons;
import uniandes.cupi2.editorDiagramaFlujo.mundo.IForma;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

public class Entrada extends FormaBasica
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para definir el tipo
     */
    public final static String TIPO = "ENTRADA";

    /**
     * Constante para definir el alto
     */
    public final static int ALTO = 40;

    /**
     * Constante para definir el ancho
     */
    public final static int ANCHO = 95;

    /**
     * Constante para definir el ancho
     */
    public final static int ANCHO_BASE = 20;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un elemento de entrada en un diagrama de flujo
     * @param punto Punto donde se desea construir el elemento
     */
    public Entrada( Punto punto )
    {

        super( punto, new Punto( punto.darX( ) + ANCHO, punto.darY( ) + ALTO ) );
    }

    /**
     * Crea una forma de entrada a partir de los datos contenidos en un archivo.
     * @param br Es el flujo de datos que sirve para leer el archivo.
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado.
     */
    public Entrada( BufferedReader br ) throws IOException, FormatoInvalidoException
    {
        super( br );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo de la forma
     * @return El tipo de la forma
     */
    public String darTipo( )
    {
        return TIPO;
    }

    /**
     * Mueve la forma de tal manera que el punto dado por parámetro quede ubicado en la mitad de la forma
     * @param punto punto de referencia para mover la forma
     */
    public void moverFigura( Punto punto )
    {
        // TODO Mover Figura
        puntoInicial.modificarX( punto.darX( ) - ANCHO / 2 );
        puntoInicial.modificarY( punto.darY( ) - ALTO / 2 );
        puntoFinal.modificarX( punto.darX( ) + ANCHO / 2 );
        puntoFinal.modificarY( punto.darY( ) + ALTO / 2 );
    }

    /**
     * Pinta una forma de entrada en el lienzo
     * @param g Lienzo donde se desea pintar
     */
    public void pintar( Graphics2D g )
    {
        int puntosX[] = { puntoInicial.darX( ) + ANCHO_BASE, puntoFinal.darX( ) + ANCHO_BASE, puntoFinal.darX( ), puntoInicial.darX( ) };
        int puntosY[] = { puntoInicial.darY( ), puntoInicial.darY( ), puntoFinal.darY( ), puntoFinal.darY( ) };

        Polygon poli = new Polygon( puntosX, puntosY, 4 );
        
        g.setPaint( Color.WHITE );
        g.fill( poli );
        g.setColor( Color.BLACK );
        g.drawPolygon( poli );
        pintarTexto( g );
    }

    /**
     * Pinta la forma cuando esta se encuentra seleccionada. Para ello, pinta cuatro rectángulos, uno en cada esquina de la forma
     * @param g La superficie donde se debe pintar.
     */
    public void pintarSeleccionado( Graphics2D g )
    {
        pintar( g );

        g.setPaint( Color.RED );
        Rectangle2D r1 = new Rectangle2D.Double( puntoInicial.darX( ) + ANCHO_BASE - 3, puntoInicial.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoInicial.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoFinal.darX( )+ANCHO_BASE - 3, puntoInicial.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoFinal.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
        g.fill( r1 );

    }

    /**
     * Sirve para pintar el texto (en color negro) del elemento. <br>
     * Para pintar el texto, se debe utilizar la fuente asociada al elemento y se debe pintar en el abajo del elemento
     * @param g La superficie donde se debe pintar.
     */
    protected void pintarTexto( Graphics2D g )
    {
        int centroX = ( puntoInicial.darX( ) + puntoFinal.darX( ) ) / 2;
        int centroY = puntoInicial.darY( ) + ALTO / 2 ;
        g.setFont( fuente );
        g.setColor( Color.BLACK );
        FontMetrics metrics = g.getFontMetrics( );
        int ancho = metrics.stringWidth( texto );
        g.drawString( texto, centroX - ancho / 2, centroY );
    }

}
