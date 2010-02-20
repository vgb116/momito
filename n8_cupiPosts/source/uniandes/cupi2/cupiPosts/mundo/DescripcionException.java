package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepci�n se lanza en caso que la longitud de la descripcion del post supere a 300 
 */
public class DescripcionException extends Exception
{

    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------
    // Atributos
    // ----------------------------------------------------------

    /**
     * La longitud de la descripcion
     */
    public int longitudDescripcion;
    
 // -----------------------------------------------------------
    // M�todos
    // ----------------------------------------------------------

    /**
     * 
     * @param causa El mensaje que describe el problema
     * @param tamDescripcion El tama�o de la descripci�n que se intent� agregar
     */
    public DescripcionException( String causa, int tamDescripcion )
    {
        super( causa );
        longitudDescripcion = tamDescripcion;
    }

    /**
     * El tama�o de la descripcion
     * @return longitudDescripcion la longitud de la descripcion
     */
    public int darLongitudDescripcion( )
    {
        return longitudDescripcion;
    }
}
