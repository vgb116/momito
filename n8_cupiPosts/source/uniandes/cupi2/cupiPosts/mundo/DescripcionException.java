package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepción se lanza en caso que la longitud de la descripcion del post supere a 300 
 */
public class DescripcionException extends Exception
{

    // ------------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
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
    // Métodos
    // ----------------------------------------------------------

    /**
     * 
     * @param causa El mensaje que describe el problema
     * @param tamDescripcion El tamaño de la descripción que se intentó agregar
     */
    public DescripcionException( String causa, int tamDescripcion )
    {
        super( causa );
        longitudDescripcion = tamDescripcion;
    }

    /**
     * El tamaño de la descripcion
     * @return longitudDescripcion la longitud de la descripcion
     */
    public int darLongitudDescripcion( )
    {
        return longitudDescripcion;
    }
}
