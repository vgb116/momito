package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepci�n se lanza en caso que no se haya especificado alg�n campo del post
 */
public class PostIncompletoException extends Exception
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
     * Los nombres de los campos vac�os
     */
    public String camposVacios;

    // -----------------------------------------------------------
    // M�todos
    // ----------------------------------------------------------

    /**
     * 
     * @param causa El mensaje que describe el problema
     * @param losCamposVacios La lista de los campos vac�os del post
     */
    public PostIncompletoException( String causa, String losCamposVacios )
    {
        super( causa );
        camposVacios = losCamposVacios;
    }

    /**
     * Los nombres de los campos vac�os
     * @return camposVacios los campos vac�os
     */
    public String darCamposVacios( )
    {
        return camposVacios;
    }
}
