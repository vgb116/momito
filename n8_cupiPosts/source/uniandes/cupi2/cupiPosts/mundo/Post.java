/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Post.java,v 1.16 2010/02/08 15:02:28 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 200L;

    /**
     * Constante para la ciudad Bogot�
     */
    public final static String BOGOTA = "Bogot�";

    /**
     * Constante para la ciudad Medell�n
     */
    public final static String MEDELLIN = "Medell�n";
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
     * El t�tulo del post
     */
    private String titulo;

    /**
     * La descripci�n del post
     */
    private String descripcion;

    /**
     * El tel�fono del anunciante
     */
    private int telefono;

    /**
     * La direcci�n del anunciante
     */
    private String direccion;

    /**
     * La ubicaci�n del anunciante
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
     * Este m�todo construye un nuevo post con la informaci�n recibida por par�metro <br>
     * <b> post: </b> Se inicializ� seleccionado en false
     * @param idPost El identificador del post - idPost > 0
     * @param elServicio El servicio del post - elServicio != null elServicio != ""
     * @param elTitulo El t�tulo del post - elTitulo != null elTitulo != ""
     * @param laDescripcion La descripci�n del post - laDescripcion != null laDescripcion != ""
     * @param elTelefono El tel�fono del anunciante - elTelefono > 0
     * @param laDireccion La direcci�n del anunciante - laDireccion != null laDireccion != ""
     * @param laUbicacion La ubicaci�n del anunciante laUbicacion != null laUbicacion != ""
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
    // M�todos
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
     * Retorna el t�tulo del post
     * @return El t�tulo
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Retorna la descripci�n del post
     * @return La descripci�n
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Retorna el tel�fono del post
     * @return El tel�fono
     */
    public int darTelefono( )
    {
        return telefono;
    }

    /**
     * Retorna la direcci�n del post
     * @return La direcci�n
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * Retorna la ubicaci�n del post
     * @return La ubicaci�n
     */
    public String darUbicacion( )
    {
        return ubicacion;
    }

    /**
     * Modifica el valor de seleccionado del post, con el valor recibido por par�metro
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
     * El titulo es v�lido si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * @return True si el titulo es v�lido, false en caso contrario
     */
    private boolean tituloEsValido( )
    {
        return titulo != null && !titulo.equals( "" );
    }

    /**
     * La descripcion es v�lida si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * @return True si la descripcion es v�lida, false en caso contrario
     */
    private boolean descripcionEsValido( )
    {
        return descripcion != null && !descripcion.equals( "" );
    }

    /**
     * La direccion es v�lida si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * @return True si la direccion es v�lida, false en caso contrario
     */
    private boolean direccionEsValido( )
    {
        return direccion != null && !direccion.equals( "" );
    }

    /**
     * La ubicacion es v�lida si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * @return True si la ubicacion es v�lida, false en caso contrario
     */
    private boolean ubicacionEsValido( )
    {
        return ubicacion != null && !ubicacion.equals( "" );
    }

    /**
     * El servicio es v�lido si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * @return True si el servicio es v�lido, false en caso contrario
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
        assert tituloEsValido( ) : "El t�tulo es inv�lido";
        assert descripcionEsValido( ) : "La descripcion es inv�lida";
        assert direccionEsValido( ) : "La direccion es inv�lida";
        assert telefono > 0 : "El telefono es invalido";
        assert ubicacionEsValido( ) : "La ubicacion es inv�lida";
        assert servicioEsValido( ) : "El servicio es invalido";
        assert id >= 0 : "El id es invalido";
    }

}
