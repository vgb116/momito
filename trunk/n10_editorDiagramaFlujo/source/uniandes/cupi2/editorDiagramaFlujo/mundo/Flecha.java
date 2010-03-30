/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Flecha.java,v 1.6 2009/10/21 15:57:51 carl-veg Exp $
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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que representa una flecha en un diagrama de flujo
 */
public abstract class Flecha extends Forma
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Delta utilizado para poder seleccionar un conector
     */
    public final static int DELTA = 15;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Forma de inicio de la flecha
     */
    protected IFormaBasica formaInicio;

    /**
     * Forma destino de la flecha
     */
    protected IFormaBasica formaFinal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de una flecha en un diagrama de flujo
     * @param nFormaInicio Forma inicial de la flecha
     * @param nFormaFinal Forma final de la flecha
     */
    public Flecha( IFormaBasica nFormaInicio, IFormaBasica nFormaFinal )
    {
        super( );
        formaInicio = nFormaInicio;
        formaFinal = nFormaFinal;

        Punto medio = formaInicio.darPuntoMedio( );
        puntoFinal = formaFinal.darPuntoFronteraMasCercano( medio );
        puntoInicial = formaInicio.darPuntoFronteraMasCercano( puntoFinal );
    }
    
    /**
     * Crea un elemento a partir de los datos contenidos en un archivo.<br>
     * La información de los puntos se encuentra en la primera línea, la información de la fuente, en la segunda, y el texto, en la tercera.
     * @param br Es el flujo de datos que sirve para leer el archivo.
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado.
     */
    public Flecha( BufferedReader br ) throws IOException, FormatoInvalidoException
    {
        String lineaPuntos = br.readLine( );
        String lineaFuente = br.readLine( );
        texto = br.readLine( );

        try
        {
            inicializarPuntos( lineaPuntos );
            inicializarFuente( lineaFuente );
            verificarInvariante( );
        }
        catch( FormatoInvalidoException e )
        {
            br.close( );
            throw e;
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los puntos del elemento a partir de la línea que estaba en el archivo. <br>
     * <b>post: </b> puntoInicial.darX(),puntoInicial.darY(),puntoFinal.darX(),puntoFinal.darY() han sido inicializados.
     * @param lineaPuntos La línea con la información de los dos puntos.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es el esperado.
     */
    private void inicializarPuntos( String lineaPuntos ) throws FormatoInvalidoException
    {
        String[] strValores = lineaPuntos.split( ";" );
        if( strValores.length != 4 )
        {
            throw new FormatoInvalidoException( lineaPuntos );
        }

        try
        {
            puntoInicial = new Punto( Integer.parseInt( strValores[ 0 ] ), Integer.parseInt( strValores[ 1 ] ) );
            puntoFinal = new Punto( Integer.parseInt( strValores[ 2 ] ), Integer.parseInt( strValores[ 3 ] ) );
        }
        catch( NumberFormatException nfe )
        {
            throw new FormatoInvalidoException( lineaPuntos );
        }
    }

    /**
     * Inicializa la fuente usada para mostrar el texto del elemento.<br>
     * <b>post: </b> El elemento tiene una nueva fuente de texto.
     * @param lineaFuente La línea de texto que describe la fuente usada en el texto del elemento.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es el esperado.
     */
    private void inicializarFuente( String lineaFuente ) throws FormatoInvalidoException
    {
        String[] strFuente = lineaFuente.split( ";" );

        if( strFuente.length != 3 )
        {
            throw new FormatoInvalidoException( lineaFuente );
        }

        try
        {
            String nomFuente = strFuente[ 0 ];
            int estilo = Integer.parseInt( strFuente[ 1 ] );
            int tam = Integer.parseInt( strFuente[ 2 ] );

            fuente = new Font( nomFuente, estilo, tam );
        }
        catch( NumberFormatException nfe )
        {
            throw new FormatoInvalidoException( lineaFuente );
        }
    }
    
    /**
     * Sirve para saber si un punto está dentro del conector o no.
     * @param punto punto de referencia
     * @return Retorna true si el punto está dentro del conector. Retorna false en caso contrario.
     */
    public boolean estaDentro( Punto punto )
    {
        Line2D line = new Line2D.Double( puntoInicial.darX( ), puntoInicial.darY( ), puntoFinal.darX( ), puntoFinal.darY( ) );
        return line.ptLineDist( new Point2D.Double( punto.darX( ), punto.darY( ) ) ) <= DELTA;
    }

    /**
     * Pinta el conector cuando este es seleccionado. <br>
     * Para ello, pinta dos rectángulos rojos en los puntos iniciales y finales del conector
     * @param g Es la superficie donde se pinta el elemento.
     */
    public void pintarSeleccionado( Graphics2D g )
    {
        g.setColor( Color.RED );
        g.fillRect( puntoInicial.darX( ) - TAMANIO_RECTANGULO_SELECCION / 2, puntoInicial.darY( ) - TAMANIO_RECTANGULO_SELECCION / 2, TAMANIO_RECTANGULO_SELECCION, TAMANIO_RECTANGULO_SELECCION );
        g.fillRect( puntoFinal.darX( ) - TAMANIO_RECTANGULO_SELECCION / 2, puntoFinal.darY( ) - TAMANIO_RECTANGULO_SELECCION / 2, TAMANIO_RECTANGULO_SELECCION, TAMANIO_RECTANGULO_SELECCION );
        
        g.setColor( Color.BLACK );

        pintar( g );
    }

    /**
     * Este método sirve para guardar el conector en un archivo.<br>
     * En la primera línea se guarda el tipo. <br>
     * En la segunda, el punto medio de la forma de inicio <br>
     * En la tercera, el punto medio de la forma final <br>
     * @param out Es el flujo de datos que permite que se guarde el elemento.
     */
    public void guardar( PrintWriter out )
    {
        out.println( darTipo( ) );
        out.println( "" + formaInicio.darPuntoMedio( ).darX( ) + ";" + formaInicio.darPuntoMedio( ).darY( ) );
        out.println( "" + formaFinal.darPuntoMedio( ).darX( ) + ";" + formaFinal.darPuntoMedio( ).darY( ) );
        out.println( texto );
    }

    /**
     * No se implementa
     * @param punto Punto al que se desea mover la figura
     */
    public void moverFigura( Punto punto )
    {
        // No se debe mover

    }

   
}