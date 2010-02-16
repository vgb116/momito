/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Post.java,v 1.16 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_cupiPosts
 * Autor: Juan David Villa - 18-sep-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPosts.mundo;

import java.io.Serializable;

/**
 * Clase que representa un post <br>
 * <b>inv:</b><br>
 * titulo != null && titulo != "" <br>
 * descripcion != null && descripcion != "" <br>
 * direccion != null && direccion != "" <br>
 * telefono > 0 <br>
 * ubicacion != null && ubicacion != "" <br>
 * servicio != null && servicio != "" <br>
 * id >= 0 <br>
 */
public class Post implements Serializable
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serialización
     */
    private static final long serialVersionUID = 200L;

    /**
     * Constante para la ciudad Bogotá
     */
    public final static String BOGOTA = "Bogotá";

    /**
     * Constante para la ciudad Medellín
     */
    public final static String MEDELLIN = "Medellín";
    /**
     * Constante para la ciudad Cali
     */
    public final static String CALI = "Cali";

    /**
     * Constante para la ciudad Barranquilla
     */
    public final static String BARRANQUILLA = "Barranquilla";

    /**
     * Constante para la ciudad Cartagena
     */
    public final static String CARTAGENA = "Cartagena";

    /**
     * Constante para la ciudad Bucaramanga
     */
    public final static String BUCARAMANGA = "Bucaramanga";

    /**
     * Constante para el servicio de Compra
     */
    public final static String SERVICIO_COMPRO = "Compro";

    /**
     * Constante para el servicio de Venta
     */
    public final static String SERVICIO_VENDO = "Vendo";

    /**
     * Constante para el servicio de Arriendo
     */
    public final static String SERVICIO_ARRIENDO = "Arriendo";

    /**
     * Constante para el servicio de Alquilar
     */
    public final static String SERVICIO_ALQUILO = "Alquilo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El servicio expuesto en el post
     */
    private String servicio;

    /**
     * El título del post
     */
    private String titulo;

    /**
     * La descripción del post
     */
    private String descripcion;

    /**
     * El teléfono del anunciante
     */
    private int telefono;

    /**
     * La dirección del anunciante
     */
    private String direccion;

    /**
     * La ubicación del anunciante
     */
    private String ubicacion;

    /**
     * Indica si el post fue seleccionado por el usuario
     */
    private boolean seleccionado;

    /**
     * El identificador de este post
     */
    private int id;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Este método construye un nuevo post con la información recibida por parámetro <br>
     * <b> post: </b> Se inicializó seleccionado en false
     * @param idPost El identificador del post - idPost > 0
     * @param elServicio El servicio del post - elServicio != null elServicio != ""
     * @param elTitulo El título del post - elTitulo != null elTitulo != ""
     * @param laDescripcion La descripción del post - laDescripcion != null laDescripcion != ""
     * @param elTelefono El teléfono del anunciante - elTelefono > 0
     * @param laDireccion La dirección del anunciante - laDireccion != null laDireccion != ""
     * @param laUbicacion La ubicación del anunciante laUbicacion != null laUbicacion != ""
     */
    public Post( int idPost, String elServicio, String elTitulo, String laDescripcion, int elTelefono, String laDireccion, String laUbicacion )
    {
        id = idPost;
        servicio = elServicio;
        titulo = elTitulo;
        descripcion = laDescripcion;
        telefono = elTelefono;
        direccion = laDireccion;
        ubicacion = laUbicacion;
        seleccionado = false;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el servicio del post
     * @return El servicio
     */
    public String darServicio( )
    {
        return servicio;
    }

    /**
     * Retorna el título del post
     * @return El título
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna la descripción del post
     * @return La descripción
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna el teléfono del post
     * @return El teléfono
     */
    public int darTelefono( )
    {
        return telefono;
    }

    /**
     * Retorna la dirección del post
     * @return La dirección
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * Retorna la ubicación del post
     * @return La ubicación
     */
    public String darUbicacion( )
    {
        return ubicacion;
    }

    /**
     * Modifica el valor de seleccionado del post, con el valor recibido por parámetro
     * @param estado El nuevo estado seleccionado del post
     */
    public void cambiarSeleccionado( boolean estado )
    {

        seleccionado = estado;
    }

    /**
     * Informa si el post se encuentra seleccionado
     * @return True si el post se encuentra seleccionado, false en caso contrario
     */
    public boolean darSeleccionado( )
    {
        return seleccionado;
    }

    /**
     * Retorna el identificador del post
     * @return id El identificador del post
     */
    public int darId( )
    {
        return id;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * El titulo es válido si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * @return True si el titulo es válido, false en caso contrario
     */
    private boolean tituloEsValido( )
    {
        return titulo != null && !titulo.equals( "" );
    }

    /**
     * La descripcion es válida si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * @return True si la descripcion es válida, false en caso contrario
     */
    private boolean descripcionEsValido( )
    {
        return descripcion != null && !descripcion.equals( "" );
    }

    /**
     * La direccion es válida si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * @return True si la direccion es válida, false en caso contrario
     */
    private boolean direccionEsValido( )
    {
        return direccion != null && !direccion.equals( "" );
    }

    /**
     * La ubicacion es válida si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * @return True si la ubicacion es válida, false en caso contrario
     */
    private boolean ubicacionEsValido( )
    {
        return ubicacion != null && !ubicacion.equals( "" );
    }

    /**
     * El servicio es válido si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * @return True si el servicio es válido, false en caso contrario
     */
    private boolean servicioEsValido( )
    {
        return servicio != null && !servicio.equals( "" );
    }

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertionError. <br>
     * <b>inv: </b> titulo != null && titulo != "" <br>
     * descripcion != null && descripcion != "" <br>
     * direccion != null && direccion != "" <br>
     * telefono > 0 <br>
     * ubicacion != null && ubicacion != "" <br>
     * servicio != null && servicio != "" <br>
     * id >= 0 <br>
     */
    private void verificarInvariante( )
    {
        assert tituloEsValido( ) : "El título es inválido";
        assert descripcionEsValido( ) : "La descripcion es inválida";
        assert direccionEsValido( ) : "La direccion es inválida";
        assert telefono > 0 : "El telefono es invalido";
        assert ubicacionEsValido( ) : "La ubicacion es inválida";
        assert servicioEsValido( ) : "El servicio es invalido";
        assert id >= 0 : "El id es invalido";
    }

}
