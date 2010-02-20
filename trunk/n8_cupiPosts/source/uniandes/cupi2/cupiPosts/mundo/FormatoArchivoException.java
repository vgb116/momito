package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepci�n se lanza en caso que en el archivo del que se est�n imporando los posts tenga errores de formato
 */
public class FormatoArchivoException extends Exception
{
    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n con el mensaje que se pasa como par�metro y que describe la causa del problema
     * @param mensaje el mensaje que describe el problema
     */
    public FormatoArchivoException( String mensaje )
    {
        super( mensaje );
    }
}
