/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: FincaRaiz.java,v 1.7 2009/10/15 23:08:40 c.alvarez947 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_fincaRaiz
 * Autor: Camilo Alvarez Duran - 21-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.fincaRaiz.mundo;

import java.util.ArrayList;

/**
 * Clase que representa un negocio de finca ra�z que maneja la informaci�n de inmuebles para venta y arriendo <b>inv: </b> <br>
 * inmuebles != null <br>
 */
public class FincaRaiz
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista con los inmuebles registrados
     */
    private ArrayList inmuebles;

    /**
     * Indica un n�mero consecutivo para generar el id para un inmueble
     */
    private int siguienteId;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo manejador de finca ra�z <br>
     * <b> post: </b> Se inicializ� siguienteId en 0 <br>
     * Se inicializ� la lista de inmuebles vac�a <br>
     */
    public FincaRaiz( )
    {
        siguienteId = 0;
        inmuebles = new ArrayList( );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que retorna la lista de inmuebles <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * @return lista inmuebles
     */
    public ArrayList darListaInmuebles( )
    {
        return inmuebles;
    }

    /**
     * M�todo que intercambia dos inmuebles en el arraylist.<br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: </b> se intercambian los elementos j e i del arraylist
     * @param int i indicador inmueble 1, int j indicador inmueble j
     */
    public void intercambiarInmueble( int i, int j )
    {
        Inmueble inmueblei = ( Inmueble )inmuebles.get( i );
        Inmueble inmueblej = ( Inmueble )inmuebles.get( j );
        inmuebles.set( i, inmueblej );
        inmuebles.set( j, inmueblei );
    }

    /**
     * M�todo que ordena la lista de inmuebles seg�n la ciudad usando el algoritmo de burbuja.<br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: </b> La lista de inmuebles est� ordenada por ciudad (orden ascendente)
     */

    public void ordenarPorCiudad( )
    {
        int N = inmuebles.size( );
        for( int i = N - 1; i > 0; i-- )
        {
            for( int j = 0; j < i; j++ )
            {
                Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
                Inmueble inmuebleJJ = ( Inmueble )inmuebles.get( j + 1 );
                int x = inmuebleJ.compararPorCiudad( inmuebleJJ );
                if( x > 0 )
                {
                    intercambiarInmueble( j, j + 1 );
                }
            }
        }
    }

    /**
     * M�todo que ordena la lista de inmuebles seg�n la ciudad usando el algoritmo de burbuja.<br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: La lista de inmuebles est� ordenada por ciudad (orden ascendente)
     */
    public void ordenarPorIdentificador( )
    {
        int N = inmuebles.size( );
        for( int i = N - 1; i > 0; i-- )
        {
            for( int j = 0; j < i; j++ )
            {
                Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
                Inmueble inmuebleJJ = ( Inmueble )inmuebles.get( j + 1 );
                int x = inmuebleJ.compararPorIdentificador( inmuebleJJ );
                if( x > 0 )
                {
                    intercambiarInmueble( j, j + 1 );
                }
            }
        }
    }

    /**
     * M�todo que ordena la lista de inmuebles seg�n el precio usando el algoritmo de selecci�n. <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: La lista de inmuebles est� ordenada por precio
     */
    public void ordenarPorPrecio( )
    {
        int N = inmuebles.size( );
        for( int i = 0; i < N - 1; i++ )
        {
            int k = i;
            Inmueble inmuebleMenor = ( Inmueble )inmuebles.get( k );

            for( int j = i; j < N; j++ )
            {
                Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
                int x = inmuebleJ.compararPorPrecio( inmuebleMenor );
                if( x < 0 )
                {
                    inmuebleMenor = inmuebleJ;
                    k = j;
                }
            }
            intercambiarInmueble( i, k );
        }
    }
    /**
     * M�todo que ordena la lista de inmuebles seg�n el tama�o usando el algoritmo de inserci�n. <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: La lista de inmuebles est� ordenada por tama�o
     */
    public void ordenarPorTamanio( )
    {
        int N = inmuebles.size( );
        for( int i = 1; i < N; i++ )
        {
            int j = i;
            Inmueble inmuebleJm = ( Inmueble )inmuebles.get( j - 1 );
            Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
            int x = inmuebleJ.compararPorTamanio( inmuebleJm );
            while( j > 0 && x < 0 )
            {
                intercambiarInmueble( j, j - 1 );
                j--;
            }
        }
    }
    /**
     * Busca un inmueble utilizando una b�squeda binaria. <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * La lista de inmuebles se encuentra ordenada por identificador. <br>
     * @param identificador es el identificador del inmueble que se va a buscar - identificador!=null
     * @return Retorna la posici�n del inmueble con el identificador dado. Si el inmueble no existe se retorna -1.
     */
    public int buscarBinarioPorIdentificador( String identificador )
    {
        int N = inmuebles.size( );
        int i = 0;
        int f = N - 1; 

        while( f >= i)
        {
            int m = ( f - i ) / 2 + i;
            Inmueble inmuebleM = ( Inmueble )inmuebles.get( m );
            String identifiM = inmuebleM.darIdentificador( );
            int X = identifiM.compareToIgnoreCase( identificador );

            if( X == 0 )
            {
                return m;
            }
            else if( X < 0 )
            {
                i = m+1;
            }
            else
            {
                f = m -1;
            }
        }
        return -1;
    }
    /**
     * Busca el inmueble que tenga la menor precio y sea para arrendar. <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * @return Retorna la posici�n donde se encuentra el inmueble m�s barato. Si no hay inmueble se retorna -1
     */
    public int buscarInmuebleMasBaratoArrendar( )
    {
        int N = inmuebles.size( );
        int menor = -1;

        for( int i = 0; i < N; i++ )
        {
            Inmueble inmuebleI = ( Inmueble )inmuebles.get( i );
            String ofertaI = inmuebleI.darTipoOferta( );

            if( ofertaI.equalsIgnoreCase( Inmueble.ARRENDAR ) && menor == -1 )
            {
                menor = i;
            }
            else if( ofertaI.equalsIgnoreCase( Inmueble.ARRENDAR ) && menor != -1 )
            {
                Inmueble inmuebleM = ( Inmueble )inmuebles.get( menor );
                if( inmuebleI.darPrecio( ) < inmuebleM.darPrecio( ) )
                {
                    menor = i;
                }
            }

        }
        return menor;
    }
    /**
     * Busca el inmueble que tenga el costo m�s alto y sea para vender. <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * @return Retorna la posici�n donde se encuentra el inmueble m�s costoso. Si no hay inmueble se retorna -1
     */
    public int buscarInmuebleMasCostosoVenta( )
    {
        int N = inmuebles.size( );
        int mayor = -1;

        // verifica que existan inmuebles
        if( inmuebles.isEmpty( ) == true )
        {
            return mayor;
        }
        else
        {
            for( int i = 0; i < N; i++ )
            {
                Inmueble inmuebleX = ( Inmueble )inmuebles.get( i );
                String ofertaX = inmuebleX.darTipoOferta( );

                if( ofertaX.equalsIgnoreCase( "vender" ) == true )
                {
                    Inmueble inmuebleMayor = inmuebleX;
                    mayor = i;

                    for( int j = i; j < N; j++ )
                    {
                        Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
                        String ofertaJ = inmuebleJ.darTipoOferta( );
                        double precioJ = inmuebleJ.darPrecio( );
                        double precioMayor = inmuebleMayor.darPrecio( );

                        if( ofertaJ.equalsIgnoreCase( "vender" ) == true && precioMayor < precioJ )
                        {
                            inmuebleMayor = inmuebleJ;
                            mayor = j;
                        }
                    }
                }

            }
            return mayor;
        }
    }

    /**
     * M�todo que agregar un nuevo inmueble con los par�metros dados <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: </b> El inmueble creado con la informaci�n dada se agrego a la lista de inmuebles<br>
     * El inmueble agregado tiene por id 'siguienteId-X-XX', donde X es la primera letra de la ciudad y XX es la primera letra del barrio <br>
     * Se aumento siguienteId en uno <br>
     * @param nTipoInmueble El tipo de inmueble - nTipoInmueble != null
     * @param nTipoOferta El tipo de oferta del inmueble - nTipoOferta != null
     * @param nCiudad La ciudad donde se encuentra el inmueble - nCiudad != null
     * @param nBarrio El barrio donde se encuentra el inmueble - nBarrio != null
     * @param nDireccion Al direcci�n donde se encuentra el inmueble - nDireccion != null
     * @param nTelefono El tel�fono del inmueble - nTelefono != null
     * @param nTamanio El tama�o del inmueble - nTamanio > 0
     * @param nPrecio El precio del inmueble - nPrecio > 0
     * @param imagenes La Lista de im�genes
     */
    public void agregarInmueble( String nTipoInmueble, String nTipoOferta, String nCiudad, String nBarrio, String nDireccion, String nTelefono, double nTamanio, double nPrecio, ArrayList imagenes )
    {
        String nIdentificador = siguienteId + "-" + nCiudad.charAt( 0 ) + "-" + nBarrio.charAt( 0 );
        siguienteId++;
        Inmueble inmueble = new Inmueble( nTipoInmueble, nIdentificador, nTipoOferta, nCiudad, nBarrio, nDireccion, nTelefono, nTamanio, nPrecio, imagenes );
        inmuebles.add( inmueble );

        // Verifica el invariante
        verificarInvariante( );
    }

    /**
     * El inmueble a eliminar <br>
     * <b> pre: </b> La lista de inmuebles se encuentra inicializada. <br>
     * <b> post: El inmueble con el identificador dado se elimina de la lista de inmuebles <br>
     * @param identificador El identificador del inmueble a eliminar
     */
    public void eliminarInmueble( String identificador )
    {

        boolean termino = false;
        for( int j = 0; j < inmuebles.size( ) && !termino; j++ )
        {
            Inmueble inmuebleJ = ( Inmueble )inmuebles.get( j );
            if( inmuebleJ.darIdentificador( ).equals( identificador ) )
            {
                inmuebles.remove( j );
                termino = true;
            }
        }

        // Verifica el invariante
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertError.<br>
     * <b>inv: </b> <br>
     * inmuebles != null <br>
     */
    private void verificarInvariante( )
    {
        assert inmuebles != null : "El arreglo de Inmuebles est� sin inicializar";
    }
}