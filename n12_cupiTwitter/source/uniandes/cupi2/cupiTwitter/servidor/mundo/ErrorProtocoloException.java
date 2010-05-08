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
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una excepci�n que representa un error en el protocolo
     * @param comando Comando en el cual ocurri� el error
     * @param totalParametros Total de par�metros esperados
     * @param totalRecibido Total de par�metros recibidos
     * @param mensaje Mensaje adicional para describir el error
     */
    public ErrorProtocoloException( String comando, int totalParametros, int totalRecibido, String mensaje )
    {
        super( "El n�mero de par�metros esperados para el comando: " + comando + " es: " + totalParametros + "el mensaje fue: \n" + mensaje );
    }
}
