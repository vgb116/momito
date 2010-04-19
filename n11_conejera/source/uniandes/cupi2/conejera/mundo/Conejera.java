/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Conejera.java,v 1.9 2010/04/05 22:08:22 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_conejera
 * Autor: Juan David Villa - 03-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.conejera.mundo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Clase principal de la aplicaci�n <br>
 * <b>Inv:</b> <br>
 * dineroInvertidoEnManutencion >= 0 <br>
 * parejaInicial != null <br>
 * mesActual >= 0<br>
 * idConsecutivoConejos != null <br>
 * No hay dos parejas con el mismo identificador <br>
 */
public class Conejera
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El dinero invertido en la manutenci�n de la conejera <br>
     * hasta el mes actual
     */
    private double dineroInvertidoEnManutencion;

    /**
     * La primera pareja de conejos
     */
    private ParejaConejos parejaInicial;

    /**
     * El mes en el que se encuentra la conejera
     */
    private int mesActual;

    /**
     * Id consecutivo que hace parte del identificador de cada pareja de conejos.
     */
    private IdParejaConejos idConsecutivoConejos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa la conejera con:<br>
     * El mes actual en 0 <br>
     * La clase encargada del manejo del id de las parejas de conejos <br>
     * Una pareja de conejos beb�s
     */
    public Conejera( )
    {
        mesActual = 0;
        idConsecutivoConejos = new IdParejaConejos( );
        parejaInicial = new ParejaConejos( idConsecutivoConejos.darConsecutivo( ), mesActual );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Calcula el dinero invertido en la conejera en un mes dado: <br>
     * <b>pre:</b> mes>=0 && mes<=mesActual <br>
     * @param mes El mes para el cual se quiere calcular el dinero invertido
     * @return El valor invertido en mes
     */
    public double darValorInvertidoPorMes( int mes )
    {
        // TODO completar seg�n documentaci�n
        if( parejaInicial == null )
        {
            return 0;
        }
        return parejaInicial.valorInvertidoMes( mes );
    }

    /**
     * Retorna el dinero invertido en la conejera <br>
     * hasta el mes actual incluido
     * @return El valor invertido en la conejera hasta el mes actual
     */
    public double darValorInvertidoTotal( )
    {

        return dineroInvertidoEnManutencion;
    }

    /**
     * Retorna el n�mero de parejas de conejos que hay en un mes dado <br>
     * <b>pre:</b> mes>=0 && mes<=mesActual <br>
     * <b>post:</b> totalParejasMes>=1 <br>
     * @param mes El mes en el cual se calcula el n�mero de parejas de conejos
     * @return El n�mero de parejas de conejos que hay en mes
     */
    public int darNumeroParejasEnMes( int mes )
    {
        // TODO completar seg�n documentaci�n
        if( parejaInicial == null )
        {
            return 0;
        }
        return parejaInicial.darNumeroParejasEnMes( mes );
    }

    /**
     * Aumenta el mes actual en uno <br>
     * Simula el paso de un mes en la pareja de conejos inicial<br>
     * Actualiza el dinero invertido en manutenci�n
     */
    public void simularPasoMes( )
    {
        // TODO completar seg�n documentaci�n
        mesActual++;
        dineroInvertidoEnManutencion += darValorInvertidoPorMes( mesActual );
        
        parejaInicial.simularMes( idConsecutivoConejos, mesActual );
    }

    /**
     * Retorna la ra�z del �rbol de parejas de conejos
     * @return La ra�z del �rbol
     */
    public ParejaConejos darRaiz( )
    {
        return parejaInicial;
    }

    /**
     * Retorna el valor del mes actual
     * @return El mes actual
     */
    public int darMesActual( )
    {
        return mesActual;
    }

    /**
     * Retorna el n�mero de parejas beb�s que son descendientes de la pareja cuyo id <br>
     * es recibido como par�metro <br>
     * @param id El id de la pareja para la cual se quiere calcular el n�mero de descendientes beb�s
     * @return El n�mero de parejas beb�s que son sus descendientes
     * @throws Exception En caso que no exista una pareja con el id dado por par�metro
     */
    public int descendientesBebes( int id ) throws Exception
    {
        // TODO completar seg�n documentaci�n
        try
        {
            ParejaConejos pareja = parejaInicial.buscarPareja( id );
            Collection<ParejaConejos> respuesta = new ArrayList<ParejaConejos>( );
            pareja.descendientesBebes( respuesta );
            return respuesta.size( );
        }
        catch( Exception e )
        {
            throw new Exception( "No existe un conejo con tal id" );
        }
    }

    /**
     * Retorna el objeto usado para generar los identificadores de los conejos
     * @return El objeto usado para generar los identificadores de los conejos
     */
    public IdParejaConejos darIdConejos( )
    {
        return idConsecutivoConejos;
    }

    /**
     * Cambia el valor de mantenimiento de una pareja adulta por el recibido como par�metro, <br>
     * Cambia el valor de mantenimiento de una pareja beb� por el recibido como par�metro <br>
     * Inicializa el dinero invertido en manutenci�n con el valor de mantenimiento de una pareja beb� <br>
     * <b>pre:</b> valParejaAdulta>0 && valParejaBebe>0 <br>
     * @param valParejaAdulta El nuevo valor del mantenimiento de una pareja adulta
     * @param valParejaBebe El nuevo valor del mantenimiento de una pareja beb�
     */
    public void establecerCostosMantenimiento( double valParejaAdulta, double valParejaBebe )
    {

        // TODO completar seg�n documentaci�n
        parejaInicial.cambiarValMantenimientoAdultos( valParejaAdulta );
        parejaInicial.cambiarValMantenimientoBebes( valParejaBebe );

    }

    // -------------------------------------------------------
    // Invariante
    // -------------------------------------------------------

    /**
     * Verifica la invariante de la clase. <br>
     * <b>Invariante:</b> <br>
     * dineroInvertidoEnManutencion >= 0 <br>
     * parejaInicial != null <br>
     * mesActual >= 0<br>
     * idConsecutivoConejos != null <br>
     * No hay dos parejas con el mismo identificador <br>
     */
    public void verificarInvariante( )
    {

        // TODO completar seg�n documentaci�n
        assert dineroInvertidoEnManutencion >= 0 : "El dienero de manutencion es negativo";
        assert parejaInicial != null : "La pareja inicial es nula";
        assert mesActual >= 0 : "El mes actual es negativo";
        assert idConsecutivoConejos != null : "El id consecutivo conejos es nulo";
    }

    /**
     * Verifica si existen parejas con el mismo identificador.
     * @return True si existen parejas con el mismo identificador, false en caso contrario
     */
    private boolean hayParejasMismoIdentificador( )
    {

        // TODO completar seg�n documentaci�n
        boolean respuesta = true;
        Collection<Integer> lista = new ArrayList<Integer>( );
        parejaInicial.darListaIds( lista );
        Iterator<Integer> it = lista.iterator( );
        while(it.hasNext( ))
        {
            int x = it.next( );
            
        }
        return respuesta;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}