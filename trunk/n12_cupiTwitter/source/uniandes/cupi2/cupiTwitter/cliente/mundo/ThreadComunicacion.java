/**
 * $Id: ThreadComunicacion.java,v 1.12 2010/04/26 21:08:21 carl-veg Exp $
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

/**
 * Representa un thread para enviar mensaje de forma asíncrona
 */
public class ThreadComunicacion extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el manejador de comunicaciones
     */
    private ManejadorComunicacionesCliente manejadorComunicaciones;

    /**
     * Es el mensaje que se va a enviar
     */
    private Mensaje mensaje;

    /**
     * Es el cliente de cupitwitter
     */
    private ClienteCupiTwitter cliente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo thread para enviar el mensaje
     * 
     * @param nManejadorComunicaciones El manejador de comunicaciones
     * @param nMensaje El mensaje que se va a enviar
     * @param nCliente El cliente de cupitwitter
     */
    public ThreadComunicacion( ManejadorComunicacionesCliente nManejadorComunicaciones, Mensaje nMensaje, ClienteCupiTwitter nCliente )
    {
        this.manejadorComunicaciones = nManejadorComunicaciones;
        this.mensaje = nMensaje;
        cliente = nCliente;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se invoca cuando inicia la ejecución del thread
     */
    public void run( )
    {
        try
        {
            cliente.nuevoMensaje( manejadorComunicaciones.enviarMensajeEsperar( mensaje ) );
        }
        catch( CupiTwitterException e )
        {
            e.printStackTrace( );
        }
        catch( ErrorProtocoloException e )
        {
            e.printStackTrace( );
        }
    }

}
