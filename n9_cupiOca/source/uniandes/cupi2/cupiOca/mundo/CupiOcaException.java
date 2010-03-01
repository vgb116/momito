package uniandes.cupi2.cupiOca.mundo;

/**
 * Clase para el manejo de las excepciones del juego
 * 
 */
public class CupiOcaException extends Exception
{
    //----------------------------------------------
    // Constantes
    // ----------------------------------------------
    
    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 1L;
    
    //----------------------------------------------
    // Constructor
    // ----------------------------------------------

    /**
     * Construye la excepción con el mensaje recibido como parámetro <br>
     * @param causaExcepcion el mensaje que describe el problema
     */
    public CupiOcaException( String causaExcepcion )
    {
        super( causaExcepcion );
    }

}
