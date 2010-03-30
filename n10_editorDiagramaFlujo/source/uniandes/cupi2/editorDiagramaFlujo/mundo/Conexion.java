/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Conexion.java,v 1.5 2009/10/21 15:57:50 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.editorDiagramaFlujo.mundo;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Clase que representa una conexión en un diagrama de flujo
 */
public class Conexion extends Flecha
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para definir el tipo de flecha
     */
    public final static String TIPO = "CONEXION";
    /**
     * Constante para definir el ancho de la flecha.
     */
    public final static int ANCHO_FLECHA = 10;
    /**
     * Constante para definir el alto de la flecha.
     */
    public final static int ALTO_FLECHA = 6;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de una conexión en un diagrama de flujo
     * @param nFormaInicio Forma inicial de la conexión
     * @param nFormaFinal Forma final de la conexión
     */
    public Conexion( IFormaBasica nFormaInicio, IFormaBasica nFormaFinal )
    {
        super( nFormaInicio, nFormaFinal );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo de la forma
     * @return Retorna el tipo de la forma
     */
    public String darTipo( )
    {
        return TIPO;
    }

    /**
     * Pinta la conexión en la superficie
     * @param g Es la superficie sobre la que se va a pintar
     */
    public void pintar( Graphics2D g )
    {

        g.setPaint( Color.BLACK );

        Punto medio = formaInicio.darPuntoMedio( );

        puntoFinal = formaFinal.darPuntoFronteraMasCercano( medio );
        puntoInicial = formaInicio.darPuntoFronteraMasCercano( puntoFinal );

        g.drawLine( puntoInicial.darX( ), puntoInicial.darY( ), puntoFinal.darX( ), puntoFinal.darY( ) );
        pintarFlecha( g );
        pintarTexto( g );
    }

    /**
     * Pinta la flecha del final de la conexión
     * @param g Lienzo sobre el que se va a pintar la flecha
     */
    private void pintarFlecha( Graphics2D g )
    {
        int puntox1, puntoy1, puntox2, puntoy2;
        int[] xpoints;
        int[] ypoints;

        int size = ANCHO_FLECHA;
        g.setPaint( Color.WHITE );
        if( puntoFinal.darX( ) == puntoInicial.darX( ) )
        {
            //
            // Es vertical por ende la perpendicular es horizontal.
            if( puntoFinal.darY( ) > puntoInicial.darY( ) )
                size = ( -1 ) * size;
            puntoy1 = puntoy2 = puntoInicial.darY( ) - size;
            puntox1 = puntoFinal.darX( ) + ALTO_FLECHA;
            puntox2 = puntoFinal.darX( ) - ALTO_FLECHA;
            xpoints = new int[]{ puntox1, puntox2, puntoFinal.darX( ) };
            ypoints = new int[]{ puntoy1, puntoy2, puntoFinal.darY( ) };
            g.fillPolygon( xpoints, ypoints, 3 );
            g.setPaint( Color.BLACK );
            g.drawPolygon( xpoints, ypoints, 3 );

        }
        else if( puntoFinal.darY( ) == puntoInicial.darY( ) )
        {
            //
            // Es horizontal por ende la perpendicular es vertical.
            if( puntoFinal.darX( ) > puntoInicial.darX( ) )
                size = ( -1 ) * size;

            puntox1 = puntox2 = puntoFinal.darX( ) - size;
            puntoy1 = puntoFinal.darY( ) + ALTO_FLECHA;
            puntoy2 = puntoFinal.darY( ) - ALTO_FLECHA;
            xpoints = new int[]{ puntox1, puntox2, puntoFinal.darX( ) };
            ypoints = new int[]{ puntoy1, puntoy2, puntoFinal.darY( ) };
            g.fillPolygon( xpoints, ypoints, 3 );
            g.setPaint( Color.BLACK );
            g.drawPolygon( xpoints, ypoints, 3 );
        }
        else
        {
            //
            // Hallar ángulo con la horizontal
            double cateto1 = ( puntoFinal.darY( ) - puntoInicial.darY( ) );
            double cateto2 = ( puntoFinal.darX( ) - puntoInicial.darX( ) );
            double pendienteRecta = cateto1 / cateto2;
            double angulo = Math.abs( Math.toDegrees( Math.atan( pendienteRecta ) ) );

            //
            // Hallo la distancia en X y en Y al punto de intersección sobre la línea del conector
            int c = ( int ) ( ANCHO_FLECHA * Math.sin( Math.toRadians( angulo ) ) );
            int d = ( int ) ( ANCHO_FLECHA * Math.cos( Math.toRadians( angulo ) ) );

            if( puntoInicial.darX( ) > puntoFinal.darX( ) )
                d = ( -1 ) * d;
            if( puntoInicial.darY( ) > puntoFinal.darY( ) )
                c = ( -1 ) * c;

            //
            // Punto de intersección sobre la línea del conector
            int xP1 = puntoFinal.darX( ) - d;
            int yP1 = puntoFinal.darY( ) - c;

            //
            // Hallo la distancia en X y en Y del punto de intersección sobre la línea
            // perpendicular a la del conector a los puntos límites del triángulo de la flecha
            int a = ( int ) ( ALTO_FLECHA * Math.cos( Math.toRadians( angulo ) ) );
            int b = ( int ) ( ALTO_FLECHA * Math.sin( Math.toRadians( angulo ) ) );

            if( pendienteRecta < 0 )
                b = ( -1 ) * b;

            //
            // Puntos límites del triángulo de la flecha
            puntox2 = xP1 + b;
            puntoy2 = yP1 - a;

            puntox1 = xP1 - b;
            puntoy1 = yP1 + a;

            xpoints = new int[]{ puntox1, puntox2, puntoFinal.darX( ) };
            ypoints = new int[]{ puntoy1, puntoy2, puntoFinal.darY( ) };
            g.fillPolygon( xpoints, ypoints, 3 );
            g.setPaint( Color.BLACK );
            g.drawPolygon( xpoints, ypoints, 3 );
            g.drawLine( puntox2, puntoy2, puntoFinal.darX( ), puntoFinal.darY( ) );
        }
    }

    /**
     * Sirve para pintar el texto (en color negro) del elemento. <br>
     * Para pintar el texto, se debe utilizar la fuente asociada al elemento y se debe pintar en centro del elemento
     * @param g La superficie donde se debe pintar.
     */
    protected void pintarTexto( Graphics2D g )
    {
        if(texto != null){
            int centroX = ( Math.abs( puntoFinal.darX( ) - puntoInicial.darX( ) ) / 2 );
            if( puntoInicial.darX( ) < puntoFinal.darX( ) )
                centroX += puntoInicial.darX( ) - 10;
            else
                centroX += puntoFinal.darX( ) + 10;

            int centroY = ( Math.abs( puntoFinal.darY( ) - puntoInicial.darY( ) ) / 2 );

            if( puntoInicial.darY( ) < puntoFinal.darY( ) )
                centroY += puntoInicial.darY( ) - 10;
            else
                centroY += puntoFinal.darY( ) + 10;

            g.setFont( fuente );
            g.setColor( Color.BLACK );
            g.drawString( texto, centroX, centroY );
        }
        
    }

}