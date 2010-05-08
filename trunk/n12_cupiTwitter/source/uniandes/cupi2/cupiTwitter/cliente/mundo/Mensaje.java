/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Mensaje.java,v 1.6 2010/04/26 21:08:21 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.cliente.mundo;

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
     * La lista de parámetros del mensaje
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
     * @param nparametros La lista de parámetros del mensaje
     */
    public Mensaje( String nComando, ArrayList nparametros )
    {
        comando = nComando;
        parametros = nparametros;
    }

    /**
     * Construye un nuevo mensaje
     * 
     * @param nComando El comando del mensaje
     * @param params La lista de parámetros del mensaje
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * @return Representación en una cadena de caracteres del mensaje.
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
     * Retorna el valor del parámetro en la posición dada
     * 
     * @param i La posición del mensaje debe ser un entero positivo
     * @return el valor del parámetro
     */
    public String darParametro( int i )
    {
        return parametros.get( i ).toString( );
    }

    /**
     * Retorna la lista de parámetros del mensaje
     * 
     * @return La lista de parámetros
     */
    public ArrayList darParametros( )
    {
        return parametros;
    }

    /**
     * Método que recibe un mensaje y lo codifica en una cadena de caracteres
     * 
     * @return La línea de caracteres que representa en mensaje
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