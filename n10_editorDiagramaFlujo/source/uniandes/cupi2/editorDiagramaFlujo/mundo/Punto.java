/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Punto.java,v 1.3 2009/10/21 15:57:50 carl-veg Exp $
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

/**
 * Clase que representa un punto
 */
public class Punto
{

    //  -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Coordenada x del punto
     */
    private int x;

    /**
     * Coordenada y del punto
     */
    private int y;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor con parámetros del punto
     * @param px Coordenada x
     * @param py Coordenada y
     */
    public Punto( int px, int py )
    {
        x = px;
        y = py;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la coordenada x del punto
     * @return Coordenada x del punto
     */
    public int darX( )
    {
        return x;
    }

    /**
     * Retorna la coordenada y del punto
     * @return Coordenada y del punto
     */
    public int darY( )
    {
        return y;
    }

    /**
     * Modifica la coordenada x del punto con la recibida por parámetro
     * @param px Nueva coordenada x
     */
    public void modificarX( int px )
    {
        x = px;
    }

    /**
     * Modifica la coordenada y del punto con la recibida por parámetro
     * @param py Nueva coordenada y
     */
    public void modificarY( int py )
    {
        y = py;
    }

    /**
     * @return Retorna una cadena de caracteres con la representación del punto
     */
    public String toString( )
    {
        return "" + x + ";" + y;
    }

}
