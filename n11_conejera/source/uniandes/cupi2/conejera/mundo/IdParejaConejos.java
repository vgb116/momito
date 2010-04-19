package uniandes.cupi2.conejera.mundo;

/**
 * Esta clase representa el valor de un consecutivo usado para identificar a cada pareja de conejos
 * 
 */
public class IdParejaConejos
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Un consecutivo usado como identificador de las parejas de conejos
     */
    int consecutivo;
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el valor del consecutivo en 0
     */
    public IdParejaConejos( )
    {
        consecutivo = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el consecutivo
     * @return El valor del consecutivo
     */
    public int darConsecutivo( )
    {
        consecutivo++;
        return consecutivo - 1;
    }

}
