/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ManejadorComunicacionesCliente.java,v 1.6 2010/04/27 22:29:11 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.cliente.mundo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import uniandes.cupi2.cupiTwitter.comun.mundo.ProtocoloComunicacion;
import uniandes.cupi2.cupiTwitter.servidor.mundo.UsuariosDesconectadoException;

/**
 * 
 * Clase que representa un manejador de comunicaciones encargado de enviar y recibir mensajes
 * 
 */
public class ManejadorComunicacionesCliente
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el canal de comunicaci�n con el servidor
     */
    private Socket canal;

    /**
     * Es el flujo de donde se leen los datos que llegan del servidor a trav�s del socket
     */
    private BufferedReader in;

    /**
     * El flujo que env�a los datos al servidor a trav�s del socket
     */
    private PrintWriter out;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de comunicaciones
     * @param socket El socket para la comunicaci�n con el servidor
     * @throws CupiTwitterException En caso de que se produzca un error iniciando los flujos de comunicaci�n
     * 
     */
    public ManejadorComunicacionesCliente( Socket socket ) throws CupiTwitterException
    {
        canal = socket;
        try
        {
            out = new PrintWriter( canal.getOutputStream( ), true );

            in = new BufferedReader( new InputStreamReader( canal.getInputStream( ) ) );
        }
        catch( Exception e )
        {
            throw new CupiTwitterException( "Error iniciando el flujo para enviar y recibir mensajes al servidor" );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que espera un nuevo mensaje del flujo de entrada y lo retorna
     * @return El mensaje que se recibi�
     * @throws CupiTwitterException Si ocurre un error al recibir el mensaje
     */
    public Mensaje recibirMensaje( ) throws CupiTwitterException
    {
        String linea = "";
        try
        {
            linea = in.readLine( );
            if( linea == null )
                throw new UsuariosDesconectadoException( );
            return decodificarMensaje( linea );
        }
        catch( Exception e )
        {
            throw new CupiTwitterException( "Error recibiendo un mensaje desde el servidor" );
        }
    }

    /**
     * M�todo que recibe una cadena de caracteres, las procesa y recibe un mensaje
     * @param linea La cadena de caracteres del mensaje
     * @return El mensaje resultante de la l�nea
     */
    public Mensaje decodificarMensaje( String linea )
    {
        String[] par = linea.split( ProtocoloComunicacion.SEPARADOR_PARAMETROS );
        ArrayList parametros = new ArrayList( );
        for( int i = 1; i < par.length; i++ )
        {
            parametros.add( par[ i ] );
        }
        return new Mensaje( par[ 0 ], parametros );
    }

    /**
     * M�todo que env�a un mensaje a trav�s del flujo de salida
     * @param mensaje El mensaje
     */
    public void enviarMensaje( Mensaje mensaje )
    {
        String msCodificado = mensaje.codificarMensaje( );
        out.println( msCodificado );
    }

    /**
     * M�todo que env�a un mensaje al servidor y espera hasta recibir uno
     * @param mensaje El mensaje que se desea enviar
     * @return El mensaje que se ha recibido
     * @throws CupiTwitterException Si ocurre un error al enviar un mensaje
     */
    public Mensaje enviarMensajeEsperar( Mensaje mensaje ) throws CupiTwitterException
    {
        String msCodificado = mensaje.codificarMensaje( );
        out.println( msCodificado );
        return recibirMensaje( );
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase<br>
     * <b>inv</b><br>
     * canal != null;
     */
    private void verificarInvariante( )
    {
        assert ( canal != null ) : "El canal no puede ser null";
    }
}