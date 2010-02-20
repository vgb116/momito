package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepción se lanza en caso que el nombre de la categoria que se queire crear ya este siendo utilizado
 */
public class CategoriaExistenteException extends Exception
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
     * La categoria
     */
    public String categoria;
    
    // -----------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------

    /**
     * @param mensaje El mensaje que describe el problema
     * @param laCategoria La categoría que intentó insertar
     */

    CategoriaExistenteException (String mensaje, String laCategoria)
    {
        super (mensaje);
        categoria = laCategoria;
    }
    
    /**
     * La categoria que se intentó agregar
     * @return categoria la categoria
     */
    public String darCategoria()
    {
        return categoria;
    }
}
