/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Inicio.java,v 1.5 2009/10/21 15:57:50 carl-veg Exp $
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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase que representa el inicio en un diagrama de flujo
 */
public class Inicio extends FormaBasica
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el tipo de la forma
     */
    public final static String TIPO = "INICIO";
    
    /**
     * Representa texto de la forma
     */
    public final static String TEXTO = "Inicio";

    /**
     * Alto de la forma
     */
    public final static int ALTO = 40;

    /**
     * Ancho de la forma
     */
    public final static int ANCHO = 95;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------


    /**
     * Constructor de un elemento de inicio en un diagrama de flujo
     * @param punto Punto donde se desea construir el elemento de inicio
     */
    public Inicio( Punto punto )
    {
        super( punto, new Punto( punto.darX( ) + ANCHO, punto.darY( ) + ALTO ) );
    }

    /**
     * Crea un elemento de inicio a partir de los datos contenidos en un archivo.
     * @param br Es el flujo de datos que sirve para leer el archivo.
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado.
     */
    public Inicio( BufferedReader br ) throws IOException, FormatoInvalidoException
    {
        super( br );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------


    /**
     * Mueve la forma de tal manera que el punto dado por parámetro quede ubicado en la mitad de la forma
     * @param punto punto de referencia para mover la forma
     */
    public void moverFigura( Punto punto )
    {
        puntoInicial.modificarX( punto.darX( ) - ANCHO / 2 );
        puntoInicial.modificarY( punto.darY( ) - ALTO / 2 );
        puntoFinal.modificarX( punto.darX( ) + ANCHO / 2 );
        puntoFinal.modificarY( punto.darY( ) + ALTO / 2 );

    }


    /**
     * Pinta el elemento en el lienzo
     * @param g Lienzo donde se desea pintar
     */
    public void pintar( Graphics2D g )
    {
        g.setPaint( Color.WHITE );
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(puntoInicial.darX( ), puntoInicial.darY( ), ANCHO, ALTO, 30, 30);
        g.fill( roundedRectangle );
        g.setColor( Color.BLACK);
        g.draw(roundedRectangle);   
        pintarTexto( g );

    }

    /**
     * Retorna el tipo de la forma
     * @return El tipo de la forma
     */
    public String darTipo( )
    {
        return TIPO;
    }

    /**
     * Pinta la forma cuando esta se encuentra seleccionada. Para ello, pinta cuatro rectángulos, uno en cada esquina de la forma
     * @param g La superficie donde se debe pintar.
     */
    public void pintarSeleccionado( Graphics2D g )
    {
        pintar( g );
        g.setPaint( Color.RED );
        Rectangle2D r1 = new Rectangle2D.Double( puntoInicial.darX( ) - 3, puntoInicial.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoInicial.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoFinal.darX( ) - 3, puntoFinal.darY( ) - 3, 6, 6 );
        g.fill( r1 );

        r1 = new Rectangle2D.Double( puntoFinal.darX( ) - 3, puntoInicial.darY( ) - 3, 6, 6 );
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
        int ancho = metrics.stringWidth( TEXTO );
        g.drawString( TEXTO, centroX - ancho / 2, centroY );
    }
    

    /**
     * No se implementa
     * @param txt 
     */
    public void cambiarTexto( String txt )
    {
      // No se implementa
    }
    
}
