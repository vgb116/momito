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
    }

    /**
     * Pinta una forma de entrada en el lienzo
     * @param g Lienzo donde se desea pintar
     */
    public void pintar( Graphics2D g )
    {
        g.setPaint( Color.WHITE );
        
        ArrayList<Integer> xPoints = new ArrayList<Integer>( );
        ArrayList<Integer> yPoints = new ArrayList<Integer>( );

        // Esquina superior izquierda
        xPoints.add( puntoInicial.darX( ) + ANCHO_BASE );
        yPoints.add( puntoInicial.darY( ) );

        // Esquina superior derecha
        xPoints.add( puntoFinal.darX( ) + ANCHO_BASE );
        yPoints.add( puntoInicial.darY( ) );

        // Esquina inferior derecha
        xPoints.add( puntoFinal.darX( ));
        yPoints.add( puntoFinal.darY( ) );
        
        // Esquina inferior izquierda
        xPoints.add( puntoInicial.darX( ) );
        yPoints.add( puntoFinal.darY( ) ); 
        
        int npoints = xPoints.size( );

        
        
        
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float( puntoInicial.darX( ), puntoInicial.darY( ), ANCHO, ALTO, 30, 30 );
        g.fill( roundedRectangle );
        g.setColor( Color.BLACK );
        g.draw( roundedRectangle );
        pintarTexto( g );
    }

     @Override
    public void pintarSeleccionado( Graphics2D g )
    {
        // TODO Auto-generated method stub
         pintar( g );
         
         g.setPaint( Color.RED );
         Rectangle2D r1 = new Rectangle2D.Double( puntoInicial.darX( ) + ANCHO_BASE - 3, puntoInicial.darY( ) - 3, 6, 6 );
         g.fill( r1 );

         r1 = new Rectangle2D.Double( puntoInicial.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
         g.fill( r1 );

         r1 = new Rectangle2D.Double( puntoFinal.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
         g.fill( r1 );

         r1 = new Rectangle2D.Double( puntoFinal.darX( ) - 3, puntoInicial.darY( ) - 3, 6, 6 );
         g.fill( r1 );

    }

    @Override
    protected void pintarTexto( Graphics2D g )
    {
        // TODO Auto-generated method stub

    }

}
