package uniandes.cupi2.cupiPosts.mundo;

/**
 * Esta excepción se lanza en caso que no se haya especificado algún campo del post
 */
public class PostIncompletoException extends Exception
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
     * Los nombres de los campos vacíos
     */
    public String camposVacios;

    // -----------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------

    /**
     * 
     * @param causa El mensaje que describe el problema
     * @param losCamposVacios La lista de los campos vacíos del post
     */
    public PostIncompletoException( String causa, String losCamposVacios )
    {
        super( causa );
        camposVacios = losCamposVacios;
    }

    /**
     * Los nombres de los campos vacíos
     * @return camposVacios los campos vacíos
     */
    public String darCamposVacios( )
    {
        return camposVacios;
    }
}
