package uniandes.cupi2.cupiPosts.mundo;


/**
 * Esta excepci�n se lanza en caso que el nombre de la categoria que se queire crear ya este siendo utilizado
 */
public class CategoriaExistenteException extends Exception {
	
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
     * La categoria
     */
    public String camposVacios;


}
