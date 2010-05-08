package uniandes.cupi2.cupiTwitter.servidor.mundo;

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
     * @param comando Comando en el cual ocurrió el error
     * @param totalParametros Total de parámetros esperados
     * @param totalRecibido Total de parámetros recibidos
     * @param mensaje Mensaje adicional para describir el error
     */
    public ErrorProtocoloException( String comando, int totalParametros, int totalRecibido, String mensaje )
    {
        super( "El número de parámetros esperados para el comando: " + comando + " es: " + totalParametros + "el mensaje fue: \n" + mensaje );
    }
}
