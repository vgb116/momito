package uniandes.cupi2.cupiTwitter.cliente.mundo;

/**
 * Clase para representar un error de protocolo en las comunicaciones
 */
public class ErrorProtocoloException extends Exception
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una excepción que representa un error en el protocolo
     * @param comandoEsperado El comando esperado en la comunicación
     * @param comandoRecibido El comando recibido
     * @param mensaje Mensaje adicional para describir el error
     */
    public ErrorProtocoloException( String comandoEsperado, String comandoRecibido, String mensaje )
    {
        super( "Se esperaba el comando: " + comandoEsperado + " pero se recibió " + comandoRecibido + "el mensaje fue: \n" + mensaje );
    }

}
