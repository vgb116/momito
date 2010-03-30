/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Forma.java,v 1.3 2009/10/07 14:43:38 c.alvarez947 Exp $
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
import java.awt.Graphics2D;
import java.io.PrintWriter;

/**
 * Clase abstracta que representa a un elemento en un diagrama de flujo
 */
public abstract class Forma implements IForma
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Tamaño de cada uno de los 4 rectángulos pequeños que aparecen <br>
     * alrededor de un elemento cuando se selecciona.
     */
    public static final int TAMANIO_RECTANGULO_SELECCION = 6;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Punto inicial de la figura
     */
    protected Punto puntoInicial;

    /**
     * Punto final de la figura
     */
    protected Punto puntoFinal;

    /**
     * La fuente del texto del elemento
     */
    protected Font fuente;
    
    /**
     * El texto a ser mostrado
     */
    protected String texto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la forma <br>
     * <b> post: <b> inicializa el texto en vacío "" <br>
     * Inicializa la fuente, en Arial, con estilo Plain, y de tamaño 10
     */
    public Forma( )
    {
        texto = "";
        fuente = new Font( "Arial", Font.PLAIN, 10 );
        verificarInvariante( );
    }

    /**
     * Construye una forma con los puntos dados por parámetro <br>
     * <b> post: <b> inicializa el texto en vacío "" <br>
     * Inicializa la fuente, en Arial, con estilo Plain, y de tamaño 10 <br>
     * Inicializa los puntos con los puntos dados por parámetro <br>
     * @param nPuntoInicial Punto inicial de la forma
     * @param nPuntoFinal Punto final de la forma
     */
    public Forma( Punto nPuntoInicial, Punto nPuntoFinal )
    {
        puntoInicial = nPuntoInicial;
        puntoFinal = nPuntoFinal;
        texto = "";
        fuente = new Font( "Arial", Font.PLAIN, 10 );
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el texto del elemento.
     * @param txt El nuevo texto.
     */
    public void cambiarTexto( String txt )
    {
        texto = txt;
    }

    /**
     * Retorna el texto del elemento.
     * @return Texto del elemento.
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Retorna el ancho del elemento.
     * @return ancho del elemento.
     */
    public int darAncho( )
    {
        return Math.abs( puntoFinal.darX( ) - puntoInicial.darX( ) );
    }

    /**
     * Retorna el alto del elemento.
     * @return alto del elemento.
     */
    public int darAlto( )
    {
        return Math.abs( puntoFinal.darY( ) - puntoInicial.darY( ) );
    }

    /**
     * Retorna la fuente del elemento.
     * @return Fuente del elemento.
     */
    public Font darFuente( )
    {
        return fuente;
    }

    /**
     * Cambia la fuente del elemento.
     * @param laFuente Nueva fuente del elemento.
     */
    public void cambiarFuente( Font laFuente )
    {
        fuente = laFuente;
    }

    // ------------------------------------------------------
    // Métodos abstractos
    // ------------------------------------------------------

    /**
     * Sirve para saber si un punto está dentro de un elemento o no.
     * @param punto El punto con el que se debe realizar la verificación.
     * @return Retorna true si el punto está dentro del elemento. Retorna false en caso contrario.
     */
    public abstract boolean estaDentro( Punto punto );

    /**
     * Sirve para pintar el elemento.
     * @param g La superficie donde se debe pintar.
     */
    public abstract void pintar( Graphics2D g );

    /**
     * Sirve para pintar el texto (en color negro) del elemento. <br>
     * Para pintar el texto, se debe utilizar la fuente asociada al elemento y se debe pintar en el centro del elemento
     * @param g La superficie donde se debe pintar.
     */
    protected abstract void pintarTexto( Graphics2D g );

    /**
     * Sirve para retornar el tipo del elemento.
     * @return El tipo del elemento
     */
    public abstract String darTipo( );

    /**
     * Pinta el elemento como seleccionado.
     * @param g Es la superficie donde se pinta el elemento.
     */
    public abstract void pintarSeleccionado( Graphics2D g );

    /**
     * Mueve la figura de tal modo que el punto dado por parámetro queda ubicado en el centro de la figura.
     * @param punto punto de referencia
     */
    public abstract void moverFigura( Punto punto );
    
    /**
     * Este método sirve para guardar el elemento en un archivo.<br>
     * @param out Es el flujo de datos que permite que se guarde el elemento.
     */
    public abstract void guardar( PrintWriter out );

    // ------------------------------------------------------
    // Invariante
    // ------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b><br>
     * x >= 0 y y >= 0, x2 >= 0 y y2 >= 0 , ancho>=0 y alto >=0
     */
    public void verificarInvariante( )
    {
        assert ( darAncho( ) > 0 ) : "El ancho es inválido";
        assert ( darAlto( ) > 0 ) : "El alto es inválido";
    }
}