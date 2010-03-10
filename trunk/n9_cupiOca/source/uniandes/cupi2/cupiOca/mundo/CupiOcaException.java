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
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;
    
    //----------------------------------------------
    // Constructor
    // ----------------------------------------------

    /**
     * Construye la excepci�n con el mensaje recibido como par�metro <br>
     * @param causaExcepcion el mensaje que describe el problema
     */
    public CupiOcaException( String causaExcepcion )
    {
        super( causaExcepcion );
    }

}
