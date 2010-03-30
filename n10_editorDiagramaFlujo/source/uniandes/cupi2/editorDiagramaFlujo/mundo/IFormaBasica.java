/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IFormaBasica.java,v 1.3 2009/10/07 14:43:38 c.alvarez947 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.mundo;

/**
 * Representa el contrato funcional con el que debe cumplir una forma b�sica en el editor
 */
public interface IFormaBasica extends IForma
{

    /**
     * Retorna el punto de la frontera de la forma mas cercano al punto dado por par�metro. <br>
     * <b> pre </b> Se define que la frontera tiene cuatro puntos, un punto medio superior,<br>
     *  un punto medio inferior, un punto medio derecho y un punto medio izquierdo, todos sobre el per�metro <br>
     *  de la forma. <br>
     *  <b> post: </b> El m�todo retorna uno de los cuatro puntos anteriormente definidos, el cual se <br<
     *  encuentre mas cerca al punto dado por par�metro.
     * @param punto El punto para realizar la verificaci�n
     * @return El punto m�s cercano
     * 
     */
    public Punto darPuntoFronteraMasCercano( Punto punto );

    /**
     * Retorna el punto ubicado en el medio de la forma
     * @return El punto en el medio de la forma
     */
    public Punto darPuntoMedio( );

}
