package uniandes.cupi2.cupiPosts.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una categor�a de posts <br>
 * <b>inv:</b><br>
 * nombre != null && nombre != "" <br>
 * posts != null <br>
 * idPost >= 0 <br>
 * no existen dos o mas post con el mismo id <br>
 * */

public class Categoria implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre de la categor�a
     */
    private String nombre;

    /**
     * Los posts que pertenecen a esta categor�a
     */
    private ArrayList posts;

    /**
     * Consecutivo usado para crear identificadores �nicos de los posts en cada categor�a
     */
    private int idPost;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva categor�a con un nombre <b> post: </b> Se inicializ� la lista de posts. <br>
     * Se inicializ� idPosts en 0.
     * 
     * @param elNombre El nombre de la nueva categor�a. elNombre!=null
     */
    public Categoria( String elNombre )
    {
        nombre = elNombre;
        posts = new ArrayList( );
        idPost = 0;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega el nuevo post a la lista de posts
     * 
     * @param nuevoPost El nuevo post que se va a agregar
     */
    public void agregarPost( Post nuevoPost )
    {
        posts.add( nuevoPost );
        verificarInvariante( );
    }

    /**
     * Genera un id para un nuevo post. <br>
     * <b> post: </b> iPost se incremento en 1.
     * 
     * @return idPost El id del nuevo post
     */
    public int darIdPost( )
    {
        idPost++;
        return idPost;
    }

    /**
     * Busca el post que se desea eliminar y lo elimina de la lista de posts de la categor�a. <br>
     * <b> post: </b> la lista de posts se encuentra inicializada
     * 
     * @param idPost El identificador del post que se desea eliminar
     */
    public void eliminarPost( int idPost )
    {
        boolean encontro = false;
        for( int i = 0; i < posts.size( ) && !encontro; i++ )
        {
            Post temp = ( Post )posts.get( i );
            if( temp.darId( ) == idPost )
            {
                encontro = true;
                posts.remove( temp );
            }
        }

    }

    /**
     * Informa el nombre de la categor�a
     * 
     * @return El nombre de la categor�a
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * 
     * Retorna la representaci�n String de la categor�a
     * 
     * @return Retorna una cadena con el nombre de la categor�a
     */
    public String toString( )
    {
        return nombre;
    }

    /**
     * Retorna los posts de la categor�a
     * 
     * @return posts los posts de la categor�a
     */
    public ArrayList darPosts( )
    {
        return posts;
    }

    // ------------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * El nombre es v�lido si: <br>
     * no es null <br>
     * es diferente de la cadena vac�a <br>
     * 
     * @return True si el nombre es v�lido, false en caso contrario
     */
    private boolean nombreEsValido( )
    {
        return nombre != null && !nombre.equals( "" );
    }

    /**
     * Verifica que no hayan post con id repetido
     * 
     * @return True si el id es unico por post, false en caso contrario
     */
    private boolean idPostEsUnico( )
    {
        int N = posts.size( );
        boolean resp = true;
        for( int i = 0; i < N; i++ )
        {
            Post pI = ( Post )posts.get( i );
            int idI = pI.darId( );
            for( int j = i + 1; j < N; j++ )
            {
                Post pJ = ( Post )posts.get( j );
                int idJ = pJ.darId( );
                if( idI == idJ )
                {
                    resp = false;
                }
            }
        }
        return resp;
    }

    /**
     * Verifica que el invariante de la clase se cumpla. Si algo falla, lanza un AssertionError. <br>
     * <b>inv: </b> nombre != null && nombre != "" <br>
     * posts != null <br>
     * idPost >= 0 <br>
     * no existen dos o mas post con el mismo id <br>
     * 
     */
    private void verificarInvariante( )
    {
        assert nombreEsValido( ) : "El nombre es inv�lido";
        assert posts != null : "El id es invalido";
        assert idPost >= 0 : "El id es invalido, es menor a cero";
        assert idPostEsUnico( ) : "El id es invalido, no es unico";
    }

}
