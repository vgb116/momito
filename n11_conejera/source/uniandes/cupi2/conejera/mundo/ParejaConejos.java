package uniandes.cupi2.conejera.mundo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Clase que representa una pareja de conejos <br>
 * <b>Inv:</b> <br>
 * El estado no es nulo y tiene como valor ESTADO_BEBES ó ESTADO_ADULTOS <br>
 * La lista de hijos debe estar inicializada <br>
 * Una pareja bebé no tiene hijos<br>
 * Una pareja de 0 meses debe tener el ESTADO_BEBES. <br>
 * Una pareja adulta tiene mas de 0 meses. <br>
 * El identificador debe ser mayor o igual a cero <br>
 * El mes en el que nació la pareja debe ser mayor o igual a cero <br>
 */
public class ParejaConejos
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que la pareja de conejos se encuentra en estado de bebés
     */
    public final static String ESTADO_BEBES = "Bebés";

    /**
     * Constante que indica que la pareja de conejos se encuentra en estado adultos
     */
    public final static String ESTADO_ADULTOS = "Adultos";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica el estado de la pareja <br>
     * ESTADO_BEBES o ESTADO_ADULTOS
     */
    private String estado;

    /**
     * La edad de la pareja en meses
     */
    private int edad;

    /**
     * Las parejas hijas de esta pareja
     */
    private ArrayList hijos;

    /**
     * El id de la pareja
     */
    private int id;

    /**
     * El mes en el que nació la pareja
     */
    private int mesNacimiento;

    /**
     * El valor del mantenimiento de una pareja adulta
     */
    private double valManetinimientoAdultos;

    /**
     * El valor del mantenimiento de una pareja bebé
     */
    private double valManetinimientoBebes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva pareja de conejos, con el identificador y el mes dados como parámetros.<br>
     * <b> post: </b> Se inicializó la lista de hijos vacía. <br>
     * Se inicializó el estado en ESTADO_BEBES. <br>
     * Se inicializó la edad en 0. <br>
     * Se le asignó un identificador a la pareja <br>
     * Se inicializó el mes en el que nació la pareja
     * @param identificador El identificador correspondiente a esta pareja
     * @param mes El mes en el que nació la pareja
     */
    public ParejaConejos( int identificador, int mes )
    {
        estado = ESTADO_BEBES;
        hijos = new ArrayList<ParejaConejos>( );
        edad = 0;
        id = identificador;
        mesNacimiento = mes;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el id de la pareja
     * @return El id de la pareja
     */
    public int darId( )
    {
        return id;
    }

    /**
     * Retorna los hijos de la pareja
     * @return Los hijos de la pareja
     */
    public ArrayList darHijos( )
    {
        return hijos;
    }

    /**
     * Retorna el estado de la pareja
     * @return El estado de la pareja
     */
    public String darEstado( )
    {
        return estado;
    }

    /**
     * Retorna la edad en meses de la pareja
     * @return La edad en meses de esta pareja
     */
    public int darEdad( )
    {
        return edad;
    }

    /**
     * Aumenta la edad de la pareja en un mes. <br>
     * <b> post: </b> Si la edad final es de 1 mes, <br>
     * el estado de la pareja cambia de ESTADO_BEBES a ESTADO_ADULTOS.
     */
    public void crecer( )
    {
        edad++;
        if( edad == 1 )
        {
            estado = ESTADO_ADULTOS;
        }
        verificarInvariante( );
    }

    /**
     * Crea una nueva pareja de conejos hijos. <br>
     * Asigna los costos de mantenimiento a la nueva pareja <br>
     * <b> pre: </b> La lista de hijos se encuentra inicializada. <br>
     * <b> post: </b> La pareja tiene una nueva pareja de hijos en ESTADO_BEBES.
     * @param idConsecutivoConejos Consecutivo para generar un nuevo id - idConsecutivoConejos != null
     * @param mesActual El mes actual - mesActual > 0
     */
    public void reproducirse( IdParejaConejos idConsecutivoConejos, int mesActual )
    {
        int i = idConsecutivoConejos.darConsecutivo( );
        ParejaConejos hijo = new ParejaConejos( i, mesActual );
        hijo.valManetinimientoAdultos = valManetinimientoAdultos;
        hijo.valManetinimientoBebes = valManetinimientoBebes;
        hijos.add( hijo );
        verificarInvariante( );
    }

    /**
     * Simula un mes de vida en la pareja actual de conejos y en sus descendientes. <br>
     * <b> post: </b> Aumenta la edad de la pareja y de sus hijos en un mes. <br>
     * Si la pareja tiene más de un mes, se reproduce.<br>
     * @param idConsecutivoConejos Consecutivo para generar un nuevo id - idConsecutivoConejos != null
     * @param mesActual El mes actual - mesActual > 0
     */
    public void simularMes( IdParejaConejos idConsecutivoConejos, int mesActual )
    {
        edad++;
        if( edad > 1 )
        {
            for( int i = 0; i < hijos.size( ); i++ )
            {
                ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
                hijo.simularMes( idConsecutivoConejos, mesActual );
            }
            reproducirse( idConsecutivoConejos, mesActual );

        }
        else if( edad > 0 )
        {
            estado = ESTADO_ADULTOS;
        }
        verificarInvariante( );
    }

    /**
     * Calcula el costo de manutención de la conejera en un mes dado. <br>
     * <b> pre: </b> La lista de hijos se encuentra inicializada. <br>
     * <b> post: </b> El valor invertido en el mes >0
     * @param mes El mes para el cual se quiere calcular el costo de manutención - mes >= 0
     * @return valorInvertidoMes Retorna el valor de manutención para un mes dado como parámetro.
     */
    public double valorInvertidoMes( int mes )
    {
        double respuesta = 0;
        if( mes == mesNacimiento )
        {
            respuesta += valManetinimientoBebes;
        }
        else if( mes > mesNacimiento )
        {
            respuesta += valManetinimientoAdultos;
            for( int i = 0; i < hijos.size( ); i++ )
            {
                ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
                respuesta += hijo.valorInvertidoMes( mes );
            }
        }
        return respuesta;
    }
    /**
     * Calcula el total de parejas de conejos en un mes dado como parámetro <br>
     * @param mes El mes para calcular el total de parejas - mes >= 0
     * @return numParejas El total de parejas de conejos en el mes dado
     */
    public int darNumeroParejasEnMes( int mes )
    {
        int respuesta = 0;
        if( mesNacimiento <= mes )
        {
            respuesta++;
        }
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            respuesta += hijo.darNumeroParejasEnMes( mes );
        }

        return respuesta;
    }

    /**
     * Busca la pareja de conejos con id igual a idPareja <br>
     * <b> pre: </b> La lista de hijos se encuentra inicializada.
     * @param idPareja El identificador de la pareja buscada - idPareja >= 0
     * @return La pareja con el identificador buscado, null si no existe
     */
    public ParejaConejos buscarPareja( int idPareja )
    {
        if( id == idPareja )
        {
            return this;
        }
        else
        {
            for( int i = 0; i < hijos.size( ); i++ )
            {
                ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
                ParejaConejos buscada = hijo.buscarPareja( idPareja );
                if( buscada != null )
                {
                    return buscada;
                }
            }
        }
        return null;
    }

    /**
     * Almacena en la lista recibida por parámetro sus descendientes con ESTADO_BEBES <br>
     * <b> pre: </b> La lista de hijos se encuentra inicializada
     * @param respuesta Lista para guardar los descendientes bebés de esta pareja.
     */
    public void descendientesBebes( Collection respuesta )
    {
        if( estado.equals( ESTADO_BEBES ) )
        {
            respuesta.add( this );
        }
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            hijo.descendientesBebes( respuesta );
        }
    }

    /**
     * Agrega a la lista que recibe por parámetro los ids de las parejas en el árbol <br>
     * @param lista La lista en la que se van a adicionar los ids existentes<br>
     */
    public void darListaIds( Collection lista )
    {
        lista.add( id );
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            hijo.darListaIds( lista );
        }
    }

    /**
     * Indica si la pareja es una hoja o no. <br>
     * <b> pre: </b> La lista de hijos se encuentra inicializada.
     * @return true si la pareja es una hoja, false de lo contrario
     */
    public boolean esHoja( )
    {
        return hijos.size( ) == 0;
    }

    /**
     * Cambia el valor de mantenimiento de una pareja adulta por el recibido por parámetro
     * @param nuevoValor el nuevo valor del mantenimiento de una pareja adulta
     */
    public void cambiarValMantenimientoAdultos( double nuevoValor )
    {
        valManetinimientoAdultos = nuevoValor;
        verificarInvariante( );
    }

    /**
     * Cambia el valor de mantenimiento de una pareja bebé por el recibido como parámetro
     * @param nuevoValor el nuevo valor del mantenimiento de una pareja bebé
     */
    public void cambiarValMantenimientoBebes( double nuevoValor )
    {
        valManetinimientoBebes = nuevoValor;
        verificarInvariante( );
    }

    /**
     * Retorna el valor de mantenimiento de una pareja adulta
     * @return El valor de mantenimiento de una pareja adulta
     */
    public double darValMantenimientoAdultos( )
    {
        return valManetinimientoAdultos;
    }

    /**
     * Retorna el valor de mantenimiento de una pareja bebé
     * @return El valor de mantenimiento de una pareja bebé
     */
    public double darValMantenimientoBebes( )
    {
        return valManetinimientoBebes;
    }

    // -------------------------------------------------------
    // Invariante
    // -------------------------------------------------------

    /**
     * El estado no es nulo y tiene como valor ESTADO_BEBES ó ESTADO_ADULTOS. <br>
     * @return True si el estado es válido, false en caso contrario
     */
    private boolean EstadoEsValido( )
    {
        boolean respuesta = true;
        if( ( estado == null ) || ( !estado.equals( ESTADO_ADULTOS ) ) || !estado.equals( ESTADO_BEBES ) )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.EstadoEsValido( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * La lista de hijos está inicializada. <br>
     * @return True si la lista está inicializada, false en caso contrario
     */
    private boolean ListaHijosInicializada( )
    {
        boolean respuesta = true;
        if( hijos == null )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.ListaHijosInicializada( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * Los bebés no tienen hijos. <br>
     * @return True si el bebé no tiene hijos, false en caso contrario
     */
    private boolean BebeSinHijos( )
    {
        boolean respuesta = true;
        if( ( estado == ESTADO_BEBES ) && ( hijos.size( ) != 0 ) )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.BebeSinHijos( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * Los bebés tienen cero meses. <br>
     * @return True si el bebé tiene cero meses, false en caso contrario
     */
    private boolean BebeCeroMeses( )
    {
        boolean respuesta = true;
        if( ( estado == ESTADO_BEBES ) && ( edad != 0 ) )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.BebeCeroMeses( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * Los adultos tienen más de cero meses. <br>
     * @return True si el adulto tiene más de cero meses, false en caso contrario
     */
    private boolean AdultoMayor( )
    {
        boolean respuesta = true;
        if( ( estado == ESTADO_ADULTOS ) && ( edad == 0 ) )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.AdultoMayor( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * El mes de nacimiento es menor a cero. <br>
     * @return True si el el mes de nacimiento es mayor a cero, false en caso contrario
     */
    private boolean MesNacimiento( )
    {
        boolean respuesta = true;
        if( mesNacimiento < 0 )
            respuesta = false;
        for( int i = 0; i < hijos.size( ); i++ )
        {
            ParejaConejos hijo = ( ParejaConejos )hijos.get( i );
            if( hijo.MesNacimiento( ) == false )
                respuesta = false;
        }
        return respuesta;
    }

    /**
     * Verifica la invariante de la clase. <br>
     * <b>Invariante:</b> <br>
     * El estado no es nulo y tiene como valor ESTADO_BEBES ó ESTADO_ADULTOS <br>
     * La lista de hijos debe estar inicializada <br>
     * Una pareja bebé no tiene hijos<br>
     * Una pareja de 0 meses debe tener el ESTADO_BEBES. <br>
     * Una pareja adulta tiene mas de 0 meses. <br>
     * El identificador debe ser mayor o igual a cero <br>
     * El mes en el que nació la pareja debe ser mayor o igual a cero <br>
     */

    public void verificarInvariante( )
    {
        assert EstadoEsValido( ) : "El estado es inválido";
        assert ListaHijosInicializada( ) : "La lista de bebés está sin inicializar";
        assert BebeSinHijos( ) : "Un bebé tiene hijos";
        assert BebeCeroMeses( ) : "Un bebé tiene más de cero meses";
        assert AdultoMayor( ) : "Un adulto tiene cero meses";
        assert id > 0 : "El id es menor que cero";
        assert MesNacimiento( ) : "Un adulto tiene cero meses";
    }
}