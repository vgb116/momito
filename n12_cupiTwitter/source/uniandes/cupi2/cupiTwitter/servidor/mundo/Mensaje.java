/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Mensaje.java,v 1.9 2010/04/27 22:23:16 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.servidor.mundo;

import java.util.ArrayList;

import uniandes.cupi2.cupiTwitter.comun.mundo.ProtocoloComunicacion;

/**
 * Clase que representa un mensaje entre un cliente y el servidor
 */
public class Mensaje
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El comando del mensaje
     */
    private String comando;

    /**
     * La lista de par�metros del mensaje
     */
    private ArrayList parametros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo mensaje
     * 
     * @param nComando El comando del mensaje
     */
    public Mensaje( String nComando )
    {
        comando = nComando;
        parametros = new ArrayList( );
    }

    /**
     * Construye un nuevo mensaje
     * 
     * @param nComando El comando del mensaje
     * @param npar�metros La lista de par�metros del mensaje
     */
    public Mensaje( String nComando, ArrayList npar�metros )
    {
        comando = nComando;
        parametros = npar�metros;
    }

    /**
     * Construye un nuevo mensaje
     * 
     * @param nComando El comando del mensaje
     * @param params La lista de par�metros del mensaje
     */
    public Mensaje( String nComando, String[] params )
    {
        comando = nComando;
        parametros = new ArrayList( );
        for( int i = 0; i < params.length; i++ )
        {
            parametros.add( params[ i ] );
        }
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * @return Representaci�n en una cadena de caracteres del mensaje.
     */
    public String toString( )
    {
        String resp = comando;
        for( int i = 0; i < parametros.size( ); i++ )
        {
            resp += ";" + parametros.get( i );
        }
        return resp;
    }

    /**
     * Retorna el comando del mensaje
     * 
     * @return El comando
     */
    public String darComando( )
    {
        return comando;
    }

    /**
     * Retorna el valor del par�metro en la posici�n dada
     * 
     * @param i La posici�n del mensaje debe ser un entero positivo
     * @return el valor del par�metro
     */
    public String darParametro( int i )
    {
        return parametros.get( i ).toString( );
    }

    /**
     * Retorna la lista de par�metros del mensaje
     * 
     * @return La lista de par�metros
     */
    public ArrayList darParametros( )
    {
        return parametros;
    }

    /**
     * Retorna el total de par�metros del mensaje
     * 
     * @return el total de par�metros del mensaje
     */
    public int darTotalParametros( )
    {
        return parametros.size( );
    }

    /**
     * M�todo que recibe un mensaje y lo codifica en una cadena de caracteres
     * @return La l�nea de caracteres que representa en mensaje
     */
    public String codificarMensaje( )
    {
        String mCodificado = comando;
        for( int i = 0; i < parametros.size( ); i++ )
        {
            mCodificado += ProtocoloComunicacion.SEPARADOR_PARAMETROS + parametros.get( i ).toString( );
        }
        return mCodificado;
    }

}