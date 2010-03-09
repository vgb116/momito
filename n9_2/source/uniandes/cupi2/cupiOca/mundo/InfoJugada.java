package uniandes.cupi2.cupiOca.mundo;

/**
 * Esta clase contiene la informaci�n del resultado de una jugada Invariante de la clase: casillaInicial!=null <br>
 * casillaFinal!=null <br>
 * gano==true ||gano==false <br>
 * mensaje!=null
 */
public class InfoJugada
{
    // ------------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La casilla donde estaba el jugador antes de jugar
     */
    private Casilla casillaInicial;

    /**
     * La casilla a la cual lleg� el jugador luego de la jugada
     */
    private Casilla casillaFinal;

    /**
     * El resultado de la jugada
     */
    private String mensaje;

    /**
     * Indica si el jugador lleg� a la �ltima casilla del tablero
     */
    boolean gano;

    /**
     * El n�mero de casillas que se movi� el jugador
     */
    private int posicionesMovidas;

    // ------------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     *Crea una nueva InfoJugada
     * @param laCasillaInicial La casilla donde estaba el jugador antes de jugar
     * @param laCasillaFinal La casilla a la cual lleg� el jugador luego de la jugada
     * @param ganoJuego Indica si el jugador lleg� a la �ltima casilla del tablero
     * @param elMensaje El resultado de la jugada
     * @param lasPosicionesMovidas El n�mero de posiciones que se movi� el jugador
     */
    public InfoJugada( Casilla laCasillaInicial, Casilla laCasillaFinal, boolean ganoJuego, String elMensaje, int lasPosicionesMovidas )
    {
        casillaInicial = laCasillaInicial;
        casillaFinal = laCasillaFinal;
        gano = ganoJuego;
        mensaje = elMensaje;
        posicionesMovidas = lasPosicionesMovidas;
        verificarInvariante( );
    }

    // ------------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la casilla inicial de la jugada
     * @return casillaInicial La casilla donde estaba el jugador antes de jugar
     */
    public Casilla darCasillaInicial( )
    {
        return casillaInicial;
    }

    /**
     * Retorna la casilla final de la jugada
     * @return casillaFinal La casilla a la cual lleg� el jugador luego de la jugada
     */
    public Casilla darCasillaFinal( )
    {
        return casillaFinal;
    }

    /**
     * Retorna el resultado de la jugada
     * @return mensaje El resultado de la jugada
     */
    public String darMensaje( )
    {
        return mensaje;
    }

    /**
     * Indica si el jugador gan�
     * @return gano Indica si el jugador lleg� a la �ltima casilla del tablero
     */
    public boolean esGanador( )
    {
        return gano;
    }

    /**
     * Retorna el n�mero de casillas movidas en la jugada
     * @return posicionesMovidas El n�mero de casillas que se movi� el jugador
     */
    public int darPosicionesMovidas( )
    {
        return posicionesMovidas;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * M�todo que verifica el invariante de la clase <br>
     * <b>Invariante: </b> <br>
     * casillaInicial!=null <br>
     * casillaFinal!=null <br>
     * gano==true ||gano==false <br>
     * mensaje!=null
     */
    public void verificarInvariante( )
    {
        assert ( casillaInicial != null ) : "La casilla inicial debe ser diferente a null";
        assert ( casillaFinal != null ) : "La casilla final debe ser diferente a null";
        assert ( gano == true || gano == false ) : "No se inicializ� ganar";
        assert mensaje != null : "Siempre tiene que haber un mensaje";
    }

}
