/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Inmueble.java,v 1.10 2009/10/15 00:51:47 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * Clase que representa un inmueble <br>
 * 
 * <b>inv: </b> <br>
 * 
 * identificador != null && !identificador.equals("") <br>
 * El identificador debe ser único por inmueble <br>
 * tipoOferta != null && !tipoOferta.equals("") <br>
 * ciudad != null && !ciudad.equals("") <br>
 * barrio != null && !barrio.equals("") <br>
 * direccion != null && !direccion.equals("") <br>
 * telefono != null && !telefono.equals("") <br>
 * tamanio != null && tamanio != 0 <br>
 * precio != null && precio != 0 <br>
 * imagenes != null <br>
 * tipoInmueble != null && !tipoInmueble.equals("")
 */

public class Inmueble
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que el inmueble se encuentra a la venta
     */
    public final static String VENTA = "VENTA";

    /**
     * Indica que el inmueble se encuentra disponible para arrendar
     */
    public final static String ARRENDAR = "ARRENDAR";

    /**
     * Indica si el inmueble es un apartamento
     */
    public final static String TIPO_APARTAMENTO = "Apartamento";

    /**
     * Indica si el inmueble es una casa
     */
    public final static String TIPO_CASA = "Casa";

    /**
     * Indica se el inmueble es una finca
     */
    public final static String TIPO_FINCA = "Finca";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El identificador único del inmueble
     */
    private String identificador;

    /**
     * Indica si el inmueble es para la venta o para ser arrendado
     */
    private String tipoOferta;

    /**
     * La ciudad en la que se encuentra el inmueble
     */
    private String ciudad;

    /**
     * El barrio en el que se encuentra el inmueble
     */
    private String barrio;

    /**
     * La dirección donde se encuentra el inmueble
     */
    private String direccion;

    /**
     * El teléfono para contactarse para recibir información del inmueble
     */
    private String telefono;

    /**
     * El tamaño del inmueble en metros cuadrados
     */
    private double tamanio;

    /**
     * El precio del inmueble
     */
    private double precio;

    /**
     * La lista de imágenes del inmueble
     */
    private ArrayList imagenes;

    /**
     * El tipo de inmueble que se desea publicar
     */
    private String tipoInmueble;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo inmueble con los parámetros indicados.
     * @param nTipoInmueble El tipo de inmueble - nTipoInmueble != null
     * @param nIdentificador El identificador único del inmueble - nIdentificador != null
     * @param nTipoOferta El tipo de oferta del inmueble - nTipoOferta != null
     * @param nCiudad La ciudad donde se encuentra el inmueble - nCiudad != null
     * @param nBarrio El barrio donde se encuentra el inmueble - nBarrio != null
     * @param nDireccion Al dirección donde se encuentra el inmueble - nDireccion != null
     * @param nTelefono El teléfono del inmueble - nTelefono != null
     * @param nTamanio El tamaño del inmueble - nTamanio > 0
     * @param nPrecio El precio del inmueble - nPrecio > 0
     * @param nImagenes La lista de imágenes - nImagenes != null
     */
    public Inmueble( String nTipoInmueble, String nIdentificador, String nTipoOferta, String nCiudad, String nBarrio, String nDireccion, String nTelefono, double nTamanio, double nPrecio, ArrayList nImagenes )
    {
        identificador = nIdentificador;
        tipoOferta = nTipoOferta;
        tipoInmueble = nTipoInmueble;
        identificador = nIdentificador;
        ciudad = nCiudad;
        barrio = nBarrio;
        direccion = nDireccion;
        telefono = nTelefono;
        tamanio = nTamanio;
        precio = nPrecio;
        imagenes = nImagenes;

        // Verifica el invariante
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo del oferta del inmueble
     * 
     * @return tipo oferta
     */
    public String darTipoOferta( )
    {
        return tipoOferta;
    }

    /**
     * Retorna la identificación del inmueble
     * 
     * @return identificación
     */
    public String darIdentificador( )
    {
        return identificador;
    }

    /**
     * Retorna la ciudad donde se encuentra el inmueble
     * 
     * @return ciudad
     */
    public String darCiudad( )
    {
        return ciudad;
    }

    /**
     * Retorna el barrio donde se encuentra el inmueble
     * 
     * @return barrio
     */
    public String darBarrio( )
    {
        return barrio;
    }

    /**
     * Retorna la dirección donde se encuentra el inmueble
     * 
     * @return dirección
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * Retorna el teléfono donde se encuentra el inmueble
     * 
     * @return teléfono
     */
    public String darTelefono( )
    {
        return telefono;
    }

    /**
     * Retorna el tamaño del inmueble
     * 
     * @return tamaño
     */
    public double darTamanio( )
    {
        return tamanio;
    }

    /**
     * Retorna el precio del inmueble
     * 
     * @return precio
     */
    public double darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna la lista de imágenes del inmueble
     * 
     * @return lista Imágenes
     */
    public ArrayList darImagenes( )
    {
        return imagenes;
    }

    /**
     * Retorna el tipo de inmueble
     * 
     * @return tipo inmueble
     */
    public String darTipoInmueble( )
    {
        return tipoInmueble;
    }

    /**
     * Retorna una representación String del inmueble
     * @return Un String donde se indica el tipo de inmueble, el tipo de oferta, el tamaño y la ciudad
     */
    public String toString( )
    {
        return tipoInmueble + "-" + tipoOferta + "-" + tamanio + "m2-" + ciudad;
    }

    /**
     * Método que compara dos cadenas de caracteres sin tener en cuenta la diferenciación entre mayúsculas y minúsculas
     * @param a Cadena número Uno, b Cadena número Dos
     * @return Retorna 0 si las cadenas de caracteres son iguales<br>
     *         Retorna -1 si la cadena de caracteres a es mayor a la b<br>
     *         Retorna 1 si la cadena de caracteres a es menor a la b
     */
    public int compararStrings( String a, String b )
    {
        if( a.compareToIgnoreCase( b ) > 0 )
        {
            return 1;
        }
        else if( a.compareToIgnoreCase( b ) == 0 )
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Método que compara dos inmuebles basado en la ciudad en la que se encuentran
     * @param a El inmueble con el que se debe comparar
     * @return Retorna 0 si el inmueble a es igual<br>
     *         Retorna -1 si la ciudad del inmueble es menor que el del inmueble a<br>
     *         Retorna 1 si la ciudad del inmueble es mayor que el del inmueble a
     */
    public int compararPorCiudad( Inmueble a )
    {
        String ciudadUno = darCiudad( );
        String ciudadDos = a.darCiudad( );
        int rta = compararStrings( ciudadUno, ciudadDos );
        return rta;
    }

    /**
     * Método que compara dos inmuebles basado en el identificador
     * @param a El inmueble con el que se debe comparar
     * @return Retorna 0 si el inmueble a es igual<br>
     *         Retorna -1 si el identificador del inmueble es menor que el del inmueble a<br>
     *         Retorna 1 si el identificador del inmueble es mayor que el del inmueble a
     */
    public int compararPorIdentificador( Inmueble a )
    {
        String identificadorUno = darIdentificador( );
        String identificadorDos = a.darIdentificador( );
        int rta = compararStrings( identificadorUno, identificadorDos );
        return rta;
    }

    /**
     * Método que compara dos inmuebles basado en en el precio
     * @param a El inmueble con el que se debe comparar
     * @return Retorna 0 si el inmueble a es igual<br>
     *         Retorna -1 si el precio del inmueble es menor que el del inmueble a <br>
     *         Retorna 1 si el precio del inmueble es mayor que el del inmueble a
     */
    public int compararPorPrecio( Inmueble a )
    {
        double precioUno = darPrecio( );
        double precioDos = a.darPrecio( );
        if( precioUno > precioDos )
        {
            return 1;
        }
        else if( precioUno == precioDos )
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Método que compara dos inmuebles basado en el tamaño
     * @param a El inmueble con el que se debe comparar
     * @return Retorna 0 si el inmueble a es igual<br>
     *         Retorna -1 si el tamaño del inmueble es menor que el del inmueble a <br>
     *         Retorna 1 si el tamaño del inmueble es mayor que el del inmueble a
     */
    public int compararPorTamanio( Inmueble a )
    {
        double tamanioUno = darTamanio( );
        double tamanioDos = a.darTamanio( );
        if( tamanioUno > tamanioDos )
        {
            return 1;
        }
        else if( tamanioUno == tamanioDos )
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * El identificador es válido si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * si es único por inmueble <br>
     * @return True si el identificador es válido, false en caso contrario
     */
    private boolean identificadorEsValido( )
    {
        return identificador != null && !identificador.equals( "" );
        // falta lo de que sea único
    }

    /**
     * El tipo de oferta es válida si no es null y si es diferente de la cadena vacía. <br>
     * @return True si el tipo de oferta es válida, false en caso contrario
     */
    private boolean TipoOfertaEsValida( )
    {
        return tipoOferta != null && !tipoOferta.equals( "" );
    }

    /**
     * La ciudad es válida si no es null y si es diferente de la cadena vacía. <br>
     * @return True si la ciudad es válida, false en caso contrario
     */
    private boolean CiudadEsValida( )
    {
        return ciudad != null && !ciudad.equals( "" );
    }

    /**
     * El barrio es válido si no es null y si es diferente de la cadena vacía. <br>
     * @return True si el barrio es válido, false en caso contrario
     */
    private boolean BarrioEsValido( )
    {
        return barrio != null && !barrio.equals( "" );
    }

    /**
     * La dirección es válida si no es null y si es diferente de la cadena vacía. <br>
     * @return True si la dirección es válida, false en caso contrario
     */
    private boolean DireccionEsValida( )
    {
        return direccion != null && !direccion.equals( "" );
    }

    /**
     * El teléfono es válido si no es null y si es diferente de la cadena vacía. <br>
     * @return True si el teléfono es válido, false en caso contrario
     */
    private boolean TelefonoEsValido( )
    {
        return telefono != null && !telefono.equals( "" );
    }

    /**
     * El tipo de inmueble es válido si no es null y si es diferente de la cadena vacía. <br>
     * @return True si el tipo de inmueble es válido, false en caso contrario
     */
    private boolean TipoInmuebleEsValido( )
    {
        return tipoInmueble != null && !tipoInmueble.equals( "" );
    }

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertionError. <br>
     * <b>inv: </b> identificador != null && !identificador.equals("") <br>
     * El identificador debe ser único por inmueble <br>
     * tipoOferta != null && !tipoOferta.equals("") <br>
     * ciudad != null && !ciudad.equals("") <br>
     * barrio != null && !barrio.equals("") <br>
     * direccion != null && !direccion.equals("") <br>
     * telefono != null && !telefono.equals("") <br>
     * tamanio != null && tamanio != 0 <br>
     * precio != null && precio != 0 <br>
     * imagenes != null <br>
     * tipoInmueble != null <br>
     * && !tipoInmueble.equals("")
     */

    private void verificarInvariante( )
    {
        assert identificadorEsValido( ) : "El identificador es inválido";
        assert TipoOfertaEsValida( ) : "El tipo de oferta es inválida";
        assert CiudadEsValida( ) : "La ciudad es inválida";
        assert BarrioEsValido( ) : "El barrio es inválido";
        assert DireccionEsValida( ) : "La dirección es inválida";
        assert TelefonoEsValido( ) : "El telefono es inválido";
        assert tamanio != 0 : "El tamanio es inválido";
        assert precio != 0 : "El precio es inválido";
        assert imagenes != null : "Las imagenes están sin inicializar";
        assert TipoInmuebleEsValido( ) : "El tipo de inmueble es inválido";
    }
}
