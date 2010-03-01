package uniandes.cupi2.cupiOca.mundo;

/**
 * Esta clase representa una casilla del tablero de juego. <br>
 * Invariante: numCasilla >=1 && numCasilla<=49 <br>
 * tipoCasilla == (TIPO_CALAVERA || TIPO_CARCEL || TIPO_INICIO <br>
 * ||TIPO_OCA||TIPO_POSADA ||TIPO_PUENTE||TIPO_RODADERO||TIPO_VACIA) <br>
 */
public class Casilla
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante Calavera
     */
    public final static String TIPO_CALAVERA = "calavera";

    /**
     * Constante Cárcel
     */
    public final static String TIPO_CARCEL = "cárcel";

    /**
     * Constante Vacía
     */
    public final static String TIPO_VACIA = "vacía";

    /**
     * Constante Posada
     */
    public final static String TIPO_POSADA = "posada";

    /**
     * Constante Puente
     */
    public final static String TIPO_PUENTE = "puente";

    /**
     * Constante Rodadero
     */
    public final static String TIPO_RODADERO = "rodadero";

    /**
     * Constante Oca
     */
    public final static String TIPO_OCA = "cupiOca";

    /**
     * Constante inicio
     */
    public final static String TIPO_INICIO = "inicio";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La casilla en la posición anterior en el tablero de juego
     */
    // TODO modele el atributo que representa la casilla anterior
    private Casilla casillaAnterior;

    /**
     * La casilla en la posición siguiente en el tablero de juego
     */
    // TODO modele el atributo que representa la casilla siguiente
    private Casilla casillaSiguiente;

    /**
     * El tipo de esta casilla
     */
    private String tipo;

    /**
     * El número de esta casilla en el tablero de juego
     */
    private int numCasilla;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva casilla dado su tipo y posición en el tablero de juego <br>
     * <b> post: </b> Se inicializa tipo en elTipo. <br>
     * Se inicializa numCasilla en numeroCasilla. <br>
     * @param elTipo El tipo de la casilla
     * @param numeroCasilla El número de la casilla en el tablero de juego
     */
    public Casilla( String elTipo, int numeroCasilla )
    {
        tipo = elTipo;
        numCasilla = numeroCasilla;
        verificarInvariante( );

    }

    /**
     * Retorna la casilla siguiente
     * @return siguiente la casilla siguiente
     */
    public Casilla darSiguiente( )
    {
        // TODO Completar según la documentación
        return casillaSiguiente;
    }

    /**
     * Cambia la casilla siguiente por la recibida como parámetro
     * @param laSiguiente La nueva casilla siguiente
     */
    public void cambiarSiguiente( Casilla laSiguiente )
    {
        // TODO Completar según la documentación
        casillaSiguiente = laSiguiente;
    }

    /**
     * Retorna la casilla anterior
     * @return anterior La casilla anterior
     */
    public Casilla darAnterior( )
    {
        // TODO Completar según la documentación
        return casillaAnterior;
    }

    /**
     * Cambia la casilla anterior por la recibida como parámetro
     * @param laAnterior La nueva casilla anterior
     */
    public void cambiarAnterior( Casilla laAnterior )
    {
        // TODO Completar según la documentación
        casillaAnterior = laAnterior;
    }

    /**
     * Retorna el tipo de esta casilla
     * @return tipoCasilla El tipo de la casilla
     */
    public String darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna el número de la casilla
     * @return numCasilla La posición de la casilla en el tablero de juego
     */
    public int darPosicionCasilla( )
    {
        return numCasilla;
    }

    /**
     * Retorna la siguiente casilla cuyo tipo es igual al dado como parámetro<br>
     * @param tipoBuscado El tipo de la casilla que se busca
     * @return casilla La siguiente casilla con igual tipo al dado por parámetro<br>
     *         Si no hay una siguiente casilla del tipo buscado, retorna la casilla actual.
     */
    public Casilla buscarSiguienteTipoCasilla( String tipoBuscado )
    {
        // TODO Completar según la documentación

    }

    /**
     * Retorna la anterior casilla cuyo tipo es igual al dado como parámetro <br>
     * @param tipoBuscado El tipo de la casilla que se busca
     * @return casilla La anterior casilla con igual tipo al dado por parámetro <br>
     *         Si no encuentra una casilla anterior del tipo buscado, retorna la casilla actual.
     */
    public Casilla buscarAnteriorTipoCasilla( String tipoBuscado )
    {

        // TODO Completar según la documentación

    }

    /**
     * Este método retorna el número de casillas cuyo tipo <br>
     * es igual al dado como parámetro,partiendo desde la casilla actual.
     * @param tipoCasilla El tipo de casilla buscado
     * @return numCasillas El número de casillas cuyo tipo es igual al dado como parámetro
     */
    public int darNumCasillas( String tipoCasilla )
    {

        // TODO Completar según la documentación

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica: 1=< numCasilla =< 49
     * @return True si numCasilla está dentro de los límites permitidos
     */
    private boolean verificarNumCasilla( )
    {
        boolean resp = false;
        if( numCasilla >= 1 && numCasilla <= 49 )
        {
            resp = true;
        }
        return resp;
    }

    /**
     * Verifica: tipoCasilla == (TIPO_CALAVERA || TIPO_CARCEL || TIPO_INICIO ||TIPO_OCA||TIPO_POSADA ||TIPO_PUENTE||TIPO_RODADERO||TIPO_VACIA)
     * @return True si el tipoCasilla es uno de los tipos válidos
     */
    private boolean verificarTipoCasilla( )
    {
        boolean resp = false;
        if( tipo.equals( TIPO_CALAVERA ) || tipo.equals( TIPO_CARCEL ) || tipo.equals( TIPO_INICIO ) || tipo.equals( TIPO_OCA ) || tipo.equals( TIPO_POSADA ) || tipo.equals( TIPO_PUENTE ) || tipo.equals( TIPO_RODADERO ) || tipo.equals( TIPO_VACIA ) )
        {
            resp = true;
        }
        return resp;
    }

    /**
     * Método que verifica el invariante de la clase <br>
     * <b>Invariante: </b> <br>
     * numCasilla >=1 && numCasilla<=49 <br>
     * tipoCasilla == (TIPO_CALAVERA || TIPO_CARCEL || TIPO_INICIO ||TIPO_OCA||TIPO_POSADA ||TIPO_PUENTE||TIPO_RODADERO||TIPO_VACIA) <br>
     */
    private void verificarInvariante( )
    {

        // TODO Completar según la documentación
        assert verificarNumCasilla( ) : "El número de casillas no está dentro de los límites adecuados";
        assert verificarTipoCasilla( ) : "El tipo de casilla no es uno de los tipos válidos";
    }
}
