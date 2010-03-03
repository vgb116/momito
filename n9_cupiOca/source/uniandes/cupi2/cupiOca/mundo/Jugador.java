package uniandes.cupi2.cupiOca.mundo;

/**
 * Clase que representa un jugador <br>
 * <b>Invariante: </b> <br>
 * nick!=null &&!nick.equals( "" )<br>
 * imagen!=null &&!imagen.equals( "" ) <br>
 * casillaActual!=null <br>
 * turnosPerdidos>=0 <br>
 */
public class Jugador
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nick del jugador
     */
    private String nick;

    /**
     * Indica el n�mero de turnos que perdi� el jugador
     */
    private int turnosPerdidos;

    /**
     * El siguiente jugador en la lista de jugadores
     */
    // TODO modele el atributo que representa el siguiente jugador
    private Jugador jugadorSiguiente;

    /**
     * La casilla donde se encuentra actualmente el jugador
     */
    // TODO modele el atributo que representa la casilla donde se encuentra el jugador actualmente. Ll�melo casillaActual
    private Casilla casillaActual;

    /**
     * La imagen del jugador
     */
    private String imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo jugador dado su nick, la casilla donde se encuentra el jugador y su imagen <br>
     * <b> post: </b> Se inicializa turnosPerdidos en 0 <br>
     * @param elNick El nick del jugador - elNick != null
     * @param casilla La casilla del tablero donde se encuentra el jugador - casilla != null
     * @param laImagen La imagen del jugador - laImagen != null
     */
    public Jugador( String elNick, Casilla casilla, String laImagen )
    {
        // TODO Completar seg�n la documentaci�n
        nick = elNick;
        casillaActual = casilla;
        imagen = laImagen;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nick
     * @return nick El nick del jugador
     */
    public String darNick( )
    {
        return nick;
    }

    /**
     * Retorna el n�mero de turnos perdidos
     * @return turnosPerdidos El n�mero de turnos perdidos
     */
    public int darTurnosPerdidos( )
    {
        return turnosPerdidos;
    }

    /**
     * Cambia el siguiente jugador por el recibido como par�metro
     * @param elSiguiente El nuevo jugador siguiente
     */
    public void cambiarSiguiente( Jugador elSiguiente )
    {
        // TODO Completar seg�n la documentaci�n
        jugadorSiguiente = elSiguiente;
    }

    /**
     * Retorna el siguiente jugador
     * @return siguienteJugador Jugador siguiente al actual
     */
    public Jugador darSiguiente( )
    {
        // TODO Completar seg�n la documentaci�n
        return jugadorSiguiente;
    }

    /**
     * Cambia la casilla actual por la recibida como par�metro
     * @param nuevaCasilla La nueva casilla actual
     */
    public void cambiarCasillaActual( Casilla nuevaCasilla )
    {
        // TODO Completar seg�n la documentaci�n
        casillaActual = nuevaCasilla;
    }

    /**
     * Retorna la casilla actual del jugador
     * @return casillaActual La casilla en la que se encuentra el jugador
     */
    public Casilla darCasillaActual( )
    {
        return casillaActual;
    }

    /**
     * Retorna la imagen del jugador
     * @return imagen La imagen del jugador
     */
    public String darImagen( )
    {
        return imagen;
    }

    /**
     * Cambia el n�mero de turnos perdidos por el recibido como par�metro
     * @param turnos El nuevo n�mero de turnos perdidos
     */
    public void cambiarTurnosPerdidos( int turnos )
    {
        turnosPerdidos = turnos;
        verificarInvariante( );
    }

    /**
     * Disminuye el n�mero de turnos perdidos en 1
     */
    public void disminuirTurnosPerdidos( )
    {
        turnosPerdidos--;
        verificarInvariante( );
    }

    /**
     * Si turnosPerdidos==0 mover al jugador numPosiciones posiciones <br>
     * Invocar al m�todo logicaJugada <br>
     * Verificar si el jugador lleg� a la �ltima casilla <br>
     * Crear un nuevo objeto tipo InfoJugada con el resultado de la jugada <br>
     * Si turnosPerdidos>0 disminuir el n�mero de turnos perdidos
     * @param numPosiciones El n�mero de posiciones que sac� el jugador al lanzar el dado
     * @return infoJugada Esta clase contiene la casilla en la cual termina el jugador luego de la jugada, la casilla en la que estaba antes de jugar, el mensaje generado para
     *         el usuario como producto de la jugada e indica si gan� el juego
     */
    public InfoJugada jugar( int numPosiciones )
    {
        InfoJugada infoJugada;
        if( turnosPerdidos > 0 )
        {
            disminuirTurnosPerdidos( );
            infoJugada = new InfoJugada( casillaActual, casillaActual, false, "Turnos perdidos restantes: " + turnosPerdidos, 0 );
        }
        else
        {
            boolean gano = false;
            Casilla casillaInicial = darCasillaActual( );
            avanzarCasillas( numPosiciones );
            String mensaje = simularJuego( darCasillaActual( ), numPosiciones );
            Casilla casillaFinal = darCasillaActual( );
            if( ! ( casillaActual.darSiguiente( ) != null ) )
                gano = true;
            infoJugada = new InfoJugada( casillaInicial, casillaFinal, gano, mensaje, numPosiciones );
        }
        verificarInvariante( );
        return infoJugada;

    }

    /**
     * Revisa el tipo de casilla en la que cay� el jugador, <br>
     * determina si debe avanzar, retroceder o perder un n�mero de turnos <br>
     * Por �ltimo retorna un mensaje informando el resultado de la jugada. <br>
     * Si cae en una casilla TIPO_CARCEL debe perder cuatro turnos <br>
     * Si cae en una casilla TIPO_POSADA debe perder dos turnos <br>
     * Si cae en una casilla TIPO_PUENTE debe avanzar hasta el pr�ximo <br>
     * Si cae en una casilla TIPO_RODADERO debe retroceder hasta el anterior <br>
     * Si cae en una casilla TIPO_OCA debe avanzar hasta la pr�xima <br>
     * Si cae en una casilla TIPO_VACIA se queda en la casilla actual <br>
     * @param casilla La casilla en la cual cay� el jugador
     * @param numPosiciones El n�mero que sac� al tirar el dado
     * @return respuesta El mensaje dependiendo del tipo de casilla en la que cay� el jugador. <br>
     *         Si la casilla es TIPO_CALAVERA, el mensaje es "Regresa al comienzo" <br>
     *         Si la casilla es TIPO_CARCEL, el mensaje es "Vete a la c�rcel: Pierdes 4 turnos" <br>
     *         Si la casilla es TIPO_OCA, el mensaje es "Sacaste "+ numPosiciones + " pero avanzas a la pr�xima Oca" <br>
     *         Si la casilla es TIPO_POSADA, el mensaje es "Toma una siesta: Pierdes 2 turnos" <br>
     *         Si la casilla es TIPO_PUENTE, el mensaje es "Sacaste "+ numPosiciones + " pero avanzas al pr�ximo puente" <br>
     *         Si la casilla es TIPO_RODADERO, el mensaje es "Sacaste "+ numPosiciones + " pero regresa al anterior rodadero" <br>
     *         Si la casilla es TIPO_VACIA, el mensaje es vac�o ""
     */
    public String simularJuego( Casilla casilla, int numPosiciones )
    {
        String respuesta = "";
        if( casilla.darTipo( ).equals( Casilla.TIPO_CALAVERA ) )
        {
            Casilla anterior = casillaActual.buscarAnteriorTipoCasilla( Casilla.TIPO_INICIO );
            cambiarCasillaActual( anterior );
            respuesta = "Regresa al comienzo";
        }

        else if( casilla.darTipo( ).equals( Casilla.TIPO_CARCEL ) )
        {
            cambiarTurnosPerdidos( 4 );
            respuesta = "Vete a la c�rcel: Pierdes 4 turnos";
        }

        else if( casilla.darTipo( ).equals( Casilla.TIPO_OCA ) )
        {
            Casilla siguiente = casillaActual.buscarSiguienteTipoCasilla( Casilla.TIPO_OCA );
            if( siguiente != null )
                cambiarCasillaActual( siguiente );
            respuesta = "Sacaste " + numPosiciones + " , pero avanzas a la pr�xima Oca";
        }

        else if( casilla.darTipo( ).equals( Casilla.TIPO_POSADA ) )
        {
            cambiarTurnosPerdidos( 2 );
            respuesta = "Toma una siesta: Pierdes 2 turnos";
        }

        else if( casilla.darTipo( ).equals( Casilla.TIPO_PUENTE ) )
        {
            Casilla siguiente = casillaActual.buscarSiguienteTipoCasilla( Casilla.TIPO_PUENTE );
            if( siguiente != null )
                cambiarCasillaActual( siguiente );
            respuesta = "Sacaste " + numPosiciones + " , pero avanzas al pr�ximo puente";
        }

        else if( casilla.darTipo( ).equals( Casilla.TIPO_RODADERO ) )
        {
            Casilla anterior = casillaActual.buscarAnteriorTipoCasilla( Casilla.TIPO_RODADERO );
            if( anterior != null )
                cambiarCasillaActual( anterior );
            respuesta = "Sacaste " + numPosiciones + " ,pero regresa al anterior rodadero";
        }

        return respuesta;
    }

    /**
     * Avanza el n�mero de casillas que se recibe por par�metro <br>
     * si la posici�n de casillaActual + numPosiciones > la �ltima casilla <br>
     * se avanza hasta la �ltima casilla <br>
     * @param numPosiciones El numer� de posiciones que se desean avanzar - numPosiciones > 0
     */
    public void avanzarCasillas( int numPosiciones )
    {

        // TODO Completar seg�n la documentaci�n
        int n = casillaActual.darPosicionCasilla( ) + numPosiciones;
        Casilla actual = casillaActual;

        if( n > 49 )
        {
            while( actual.darSiguiente( ) != null )
            {
                actual = actual.darSiguiente( );
            }
            cambiarCasillaActual( actual );
        }
        else
        {
            while( ( actual != null ) && ( actual.darPosicionCasilla( ) != n ) )
            {
                actual = actual.darSiguiente( );
            }
            cambiarCasillaActual( actual );
        }
    }

    /**
     * Retorna una copia del jugador con el mismo nick, casilla actual e imagen
     * @return Una copia de este jugador
     */
    public Jugador darCopia( )
    {
        // TODO Completar seg�n la documentaci�n
        Jugador jug = new Jugador( nick, casillaActual, imagen );
        return jug;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * M�todo que verifica el invariante de la clase <br>
     * <b>Invariante: </b> <br>
     * nick!=null &&!nick.equals( "" )<br>
     * imagen!=null &&!imagen.equals( "" ) <br>
     * casillaActual!=null <br>
     * turnosPerdidos>=0 <br>
     */
    private void verificarInvariante( )
    {
        assert ( nick != null && !nick.equals( "" ) ) : "El nick es vac�o o null";
        assert ( imagen != null && !imagen.equals( "" ) ) : "La imagen esta vac�a o es null";
        assert ( casillaActual != null ) : "El jugador debe estar ubicado en alguna casilla";
        assert ( turnosPerdidos >= 0 ) : "El n�mero de turnos perdidos debe ser mayor o igual a cero";

    }

}
