package uniandes.cupi2.cupiPosts.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una categoría de posts <br>
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
     * Constante para la serialización
     */
    private static final long serialVersionUID = 100L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El nombre de la categoría
     */
    private String nombre;

    /**
     * Los posts que pertenecen a esta categoría
     */
    private ArrayList posts;

    /**
     * Consecutivo usado para crear identificadores únicos de los posts en cada categoría
     */
    private int idPost;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva categoría con un nombre <b> post: </b> Se inicializó la lista de posts. <br>
     * Se inicializó idPosts en 0.
     * 
     * @param elNombre El nombre de la nueva categoría. elNombre!=null
     */
    public Categoria( String elNombre )
    {
        nombre = elNombre;
        posts = new ArrayList( );
        idPost = 0;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
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
     * Busca el post que se desea eliminar y lo elimina de la lista de posts de la categoría. <br>
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
     * Informa el nombre de la categoría
     * 
     * @return El nombre de la categoría
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * 
     * Retorna la representación String de la categoría
     * 
     * @return Retorna una cadena con el nombre de la categoría
     */
    public String toString( )
    {
        return nombre;
    }

    /**
     * Retorna los posts de la categoría
     * 
     * @return posts los posts de la categoría
     */
    public ArrayList darPosts( )
    {
        return posts;
    }

    // ------------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * El nombre es válido si: <br>
     * no es null <br>
     * es diferente de la cadena vacía <br>
     * 
     * @return True si el nombre es válido, false en caso contrario
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
        assert nombreEsValido( ) : "El nombre es inválido";
        assert posts != null : "El id es invalido";
        assert idPost >= 0 : "El id es invalido, es menor a cero";
        assert idPostEsUnico( ) : "El id es invalido, no es unico";
    }

}
