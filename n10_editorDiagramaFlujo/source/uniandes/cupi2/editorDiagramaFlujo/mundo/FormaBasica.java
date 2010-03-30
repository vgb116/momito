/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FormaBasica.java,v 1.5 2009/10/21 15:57:50 carl-veg Exp $
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

import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase abstracta que representa a un elemento en un diagrama de flujo
 */
public abstract class FormaBasica extends Forma implements IFormaBasica
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una forma básica con los puntos dados por parámetro <br>
     * @param nPuntoInicial Punto inicial de la forma
     * @param nPuntoFinal Punto final de la forma
     */
    public FormaBasica( Punto nPuntoInicial, Punto nPuntoFinal )
    {
        super( nPuntoInicial, nPuntoFinal );
    }

    /**
     * Crea un elemento a partir de los datos contenidos en un archivo. <br>
     * La primera línea contiene la información de los puntos, la segunda contiene la información de la fuente.
     * @param br Es el flujo de datos que sirve para leer el archivo.
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es el esperado.
     */
    public FormaBasica( BufferedReader br ) throws IOException, FormatoInvalidoException
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
     * <b>post: </b> puntoInicial y puntoFinal han sido inicializados.
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
     * Retorna el punto de la frontera de la forma mas cercano al punto dado por parámetro. <br>
     * <b> pre </b> Se define que la frontera tiene cuatro puntos, un punto medio superior,<br>
     *  un punto medio inferior, un punto medio derecho y un punto medio izquierdo, todos sobre el perímetro <br>
     *  de la forma. <br>
     *  <b> post: </b> El método retorna uno de los cuatro puntos anteriormente definidos, el cual se <br>
     *  encuentre más cerca al punto dado por parámetro.
     * @param punto Punto al cual se quiere encontrar un punto cercano
     * @return El método retorna uno de los cuatro puntos anteriormente definidos, el cual se <br>
     *  encuentre más cerca al punto dado por parámetro.
     */
    public Punto darPuntoFronteraMasCercano( Punto punto )
    {
        Punto punto1 = new Punto( puntoInicial.darX( ) + ( ( puntoFinal.darX( ) - puntoInicial.darX( ) ) / 2 ), puntoInicial.darY( ) );
        Punto punto2 = new Punto( puntoInicial.darX( ) + ( ( puntoFinal.darX( ) - puntoInicial.darX( ) ) / 2 ), puntoFinal.darY( ) );
        Punto punto3 = new Punto( puntoInicial.darX( ), puntoInicial.darY( ) + ( ( puntoFinal.darY( ) - puntoInicial.darY( ) ) / 2 ) );
        Punto punto4 = new Punto( puntoFinal.darX( ), puntoInicial.darY( ) + ( ( puntoFinal.darY( ) - puntoInicial.darY( ) ) / 2 ) );

        double disPunto1 = calcularDistanciaEntrePuntos( punto1, punto );
        double disPunto2 = calcularDistanciaEntrePuntos( punto2, punto );
        double disPunto3 = calcularDistanciaEntrePuntos( punto3, punto );
        double disPunto4 = calcularDistanciaEntrePuntos( punto4, punto );

        if( disPunto1 < disPunto2 && disPunto1 < disPunto3 && disPunto1 < disPunto4 )
            return punto1;
        else if( disPunto2 < disPunto1 && disPunto2 < disPunto3 && disPunto2 < disPunto4 )
            return punto2;
        else if( disPunto3 < disPunto1 && disPunto3 < disPunto2 && disPunto3 < disPunto4 )
            return punto3;
        else
            return punto4;
    }

    /**
     * Retorna el punto ubicado en el medio de la forma
     * @return El punto ubicado en el medio de la forma
     */
    public Punto darPuntoMedio( )
    {
        return new Punto( puntoInicial.darX( ) + ( ( puntoFinal.darX( ) - puntoInicial.darX( ) ) / 2 ), puntoInicial.darY( ) + ( ( puntoFinal.darY( ) - puntoInicial.darY( ) ) / 2 ) );
    }

    /**
     * Calcula la distancia entre los puntos dados por parámetro
     * @param punto1 Primer punto 
     * @param punto2 Segundo punto
     * @return La distancia entre los puntos
     */
    private double calcularDistanciaEntrePuntos( Punto punto1, Punto punto2 )
    {
        int difX, difY;
        double distancia;

        difX = punto2.darX( ) - punto1.darX( );
        difY = punto2.darY( ) - punto1.darY( );
        distancia = Math.sqrt( Math.pow( difX, 2 ) + Math.pow( difY, 2 ) );

        return distancia;

    }

    /**
     * Este método sirve para guardar el conector en un archivo.<br>
     * En la primera línea se guarda el tipo. <br>
     * En la segunda, el punto medio de la forma de inicio <br>
     * En la tercera, la fuente <br>
     * En la cuarta, el texto
     * @param out Es el flujo de datos que permite que se guarde el elemento.
     */
    public void guardar( PrintWriter out )
    {
        out.println( darTipo( ) );
        out.println( "" + puntoInicial.toString( ) + ";" + puntoFinal.toString( ) );
        out.println( "" + fuente.getFamily( ) + ";" + fuente.getStyle( ) + ";" + fuente.getSize( ) );
        out.println( texto );
    }
    
    /**
     * Informa si el punto dado por parámetro se encuentra dentro de la forma
     * @param punto Punto del cual se desea conocer si se encuentra dentro de la forma
     * @return Si el punto se encuentra dentro de la forma
     */
    public boolean estaDentro( Punto punto )
    {
        Rectangle2D rectangulo = new Rectangle2D.Double( puntoInicial.darX( ), puntoInicial.darY( ), darAncho( ), darAlto( ) );
        return rectangulo.contains( punto.darX( ), punto.darY( ) );
    }

}