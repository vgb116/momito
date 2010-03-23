/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IForma.java,v 1.4 2009/10/21 15:57:50 carl-veg Exp $
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
 * Interface que declara los contratos funcionales de una forma en un diagrama de flujo
 */
public interface IForma
{

    /**
     * Sirve para pintar el elemento cuando no está seleccionado.
     * @param g La superficie donde se debe pintar.
     */
    public void pintar( Graphics2D g );

    /**
     * Pinta la forma cuando esta se encuentra seleccionada
     * @param g La superficie donde se debe pintar.
     */
    public void pintarSeleccionado( Graphics2D g );

    /**
     * Sirve para saber si un punto está dentro de un elemento o no.
     * @param punto punto de referencia.
     * @return Retorna true si el punto está dentro de la forma. Retorna false en caso contrario.
     */
    public boolean estaDentro( Punto punto );

    /**
     * Cambia el texto del elemento.
     * @param txt El nuevo texto.
     */
    public void cambiarTexto( String txt );

    /**
     * Mueve la figura de tal modo que el punto dado por parámetro queda ubicado en el centro de la figura.
     * @param punto punto de referencia
     */
    public void moverFigura( Punto punto );

    /**
     * Este método sirve para guardar el elemento en un archivo. <br>
     * @param out Es el flujo de datos que permite que se guarde el elemento.
     */
    public void guardar( PrintWriter out );

    /**
     * Retorna el texto del elemento.
     * @return Texto del elemento.
     */
    public String darTexto( );

    /**
     * Retorna la fuente del elemento.
     * @return Fuente del elemento.
     */
    public Font darFuente( );

    /**
     * Cambia la fuente del elemento.
     * @param laFuente Nueva fuente del elemento.
     */
    public void cambiarFuente( Font laFuente );
    
    /**
     * Sirve para retornar el tipo del elemento.
     * @return El tipo del elemento
     */
    public String darTipo( );
}
