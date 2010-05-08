/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Microblog.java,v 1.11 2010/04/26 21:08:20 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.servidor.mundo;

import uniandes.cupi2.cupiTwitter.comun.mundo.ProtocoloComunicacion;

/**
 * Clase que representa un microblog
 */
public class Microblog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El usuario que escribió el microblog
     */
    private String usuario;

    /**
     * La fecha en la que se escribió el microblog
     */
    private String fecha;

    /**
     * el texto del microblog
     */
    private String texto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo microblog
     * 
     * @param nUsuario El nombre del usuario
     * @param nTexto El texto del microblog
     * @param nFecha La fecha del microblog
     */
    public Microblog( String nUsuario, String nTexto, String nFecha )
    {
        usuario = nUsuario;
        texto = nTexto;
        fecha = nFecha;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que compara el microblog a con otro microblog b teniendo en cuenta la fecha
     * 
     * @param micro El microblog con el que se desea comparar
     * @return 1 en caso de que el microblog a sea mayor al microblog b, -1 en caso contrario y 0 si los dos son iguales
     */
    public int compararPorFecha( Microblog micro )
    {
        String[] eFecha = fecha.split( "-" );
        String dia = eFecha[ 0 ];
        String mes = eFecha[ 1 ];
        String anio = eFecha[ 2 ].substring( 0, 4 );
        String hora = eFecha[ 2 ].substring( 5, 7 );
        String minuto = eFecha[ 2 ].substring( 8, 10 );
        String[] eFechaOtro = micro.darFecha( ).split( "-" );
        String diaOtro = eFechaOtro[ 0 ];
        String mesOtro = eFechaOtro[ 1 ];
        String anioOtro = eFechaOtro[ 2 ].substring( 0, 4 );
        String horaOtro = eFechaOtro[ 2 ].substring( 5, 7 );
        String minutoOtro = eFechaOtro[ 2 ].substring( 8, 10 );

        Double fecha1 = new Double( anio + mes + dia + hora + minuto );
        Double fecha2 = new Double( anioOtro + mesOtro + diaOtro + horaOtro + minutoOtro );
        Double diferencia = fecha1 - fecha2;
        if( diferencia > 0 )
            return 1;
        else if( diferencia < 0 )
            return -1;
        return 0;
    }

    /**
     * Retorna la fecha en que se escribió el microblog
     * 
     * @return La fecha
     */
    public String darFecha( )
    {
        return fecha;
    }

    /**
     * Método que retorna el texto del microblog
     * 
     * @return El texto
     */
    public String darTexto( )
    {
        return texto;
    }

    /**
     * Método que retorna el usuario que escribió el microblog
     * 
     * @return El usuario
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * @return Representación en una cadena de caracteres del microblog.
     */
    public String toString( )
    {
        return usuario + ProtocoloComunicacion.SEPARADOR_CAMPOS + fecha + ProtocoloComunicacion.SEPARADOR_CAMPOS + texto;
    }
}