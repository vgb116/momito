package uniandes.cupi2.cupiTwitter.servidor.mundo;

/**
 * Clase que representa un usuario en el sistema
 */
public class Usuario
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El usuario
     */
    private String usuario;

    /**
     * El nombre del usuario
     */
    private String nombre;

    /**
     * Los apellidos del usuario
     */
    private String apellidos;

    /**
     * La contraseña del usuario
     */
    private String pwd;

    /**
     * El total de mensajes
     */
    int totalMensajes;

    /**
     * El total de seguidores
     */
    int totalSeguidores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo usuario con los parámetros dados
     * @param nUsuario El nombre de usuario
     * @param nNombre El nombre del usuario
     * @param nApellidos Los apellidos
     * @param nPwd La contraseña del usuario
     * @param nTotalMensajes El total de mensajes escritos por el usuarios
     * @param nTotalSeguidores El total de seguidores que tiene el usuario
     */
    public Usuario( String nUsuario, String nNombre, String nApellidos, String nPwd, int nTotalMensajes, int nTotalSeguidores )
    {
        usuario = nUsuario;
        nombre = nNombre;
        apellidos = nApellidos;
        pwd = nPwd;
        totalMensajes = nTotalMensajes;
        totalSeguidores = nTotalSeguidores;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de usuario
     * @return El nombre de usuario
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * @return Representación en una cadena de caracteres del usuario.
     */
    public String toString( )
    {
        return usuario;
    }

    /**
     * Método que retorna el nombre del usuario
     * @return el nombre
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Método que retorna los apellidos del usuario
     * @return Los apellidos del usuario
     */
    public String darApellidos( )
    {
        return apellidos;
    }

    /**
     * Método que retorna la contraseña del usuario
     * @return La contraseña del usuario
     */
    public String darPwd( )
    {
        return pwd;
    }

    /**
     * Método que retorna el total de mensajes del usuario
     * @return El total de mensajes
     */
    public int darTotalMensajes( )
    {
        return totalMensajes;
    }

    /**
     * Método que retorna el total de seguidores del usuario
     * @return El total de seguidores
     */
    public int darTotalSeguidores( )
    {
        return totalSeguidores;
    }

}