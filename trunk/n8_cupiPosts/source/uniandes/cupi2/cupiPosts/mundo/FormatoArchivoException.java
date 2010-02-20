package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepción se lanza en caso que en el archivo del que se están imporando los posts tenga errores de formato
 */
public class FormatoArchivoException extends Exception
{
    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepción con el mensaje que se pasa como parámetro y que describe la causa del problema
     * @param mensaje el mensaje que describe el problema
     */
    public FormatoArchivoException( String mensaje )
    {
        super( mensaje );
    }
}
