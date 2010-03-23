/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DiagramaFlujo.java,v 1.4 2009/10/07 17:01:23 c.alvarez947 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
   
package uniandes.cupi2.editorDiagramaFlujo.mundo;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un diagrama de flujo <br>
 * <b>inv:</b><br>
 * TODO: Definir y documentar invariante de la clase
 */
public class DiagramaFlujo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elementos del diagrama
     */
    private Collection<IForma> elementos;

    /**
     * Es el archivo donde se está salvando actualmente el diagrama
     */
    private File archivo;

    /**
     * Forma seleccionada por el usuario
     */
    private IForma seleccionada;

    /**
     * Indica si el dibujo ha sido modificado
     */
    private boolean modificado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diagrama <br>
     * <b> post: </b> Inicializa la lista de elementos. <br>
     * Inicializa el archivo en null. <br>
     * Inicializa modificado en false. <br>
     */
    public DiagramaFlujo( )
    {
        elementos = new ArrayList<IForma>( );
        archivo = null;
        modificado = false;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un elemento a la lista de elementos del diagrama. El elemento queda como seleccionado y el editor se encuentra modificado.
     * @param elemento Elemento que se va agregar.
     */
    public void agregarElemento( IForma elemento )
    {
        elementos.add( elemento );
        seleccionada = elemento;
        modificado = true;

    }

    /**
     * Devuelve el elemento ubicado en un punto.
     * @param punto Punto donde se desea buscar un elemento.
     * @return El elemento ubicado en el punto. En caso que no exista un elemento en ese punto devuelve null.
     */
    public IForma buscarElemento( Punto punto )
    {
        IForma elemento = null;

        boolean encontrado = false;

        // Buscar el elemento
        Iterator iter = elementos.iterator( );

        while( iter.hasNext( ) && !encontrado )
        {
            IForma aux = ( IForma )iter.next( );
            if( aux.estaDentro( punto ) )
            {
                elemento = aux;
                encontrado = true;
            }
        }
        return elemento;
    }

    /**
     * Devuelve el elemento de tipo forma básica ubicado en un punto.
     * @param punto Punto donde se desea buscar un elemento.
     * @return El elemento ubicado en el punto. En caso que no exista un elemento en ese punto devuelve null.
     */
    public IForma buscarFormaBasica( Punto punto )
    {
        IForma elemento = null;

        boolean encontrado = false;

        // Buscar el elemento
        Iterator iter = elementos.iterator( );

        while( iter.hasNext( ) && !encontrado )
        {
            IForma aux = ( IForma )iter.next( );
            if( aux instanceof IFormaBasica && aux.estaDentro( punto ) )
            {
                elemento = aux;
                encontrado = true;
            }
        }
        return elemento;
    }

    /**
     * Dibuja el diagrama de flujo en la superficie dada por parámetro. Esto significa que pinta todos sus elementos. <br>
     * Si algún elemento está seleccionado, el método lo debe pintar como tal.
     * @param g Es la superficie sobre la que se va a pintar
     */
    public void dibujar( Graphics2D g )
    {
    	// TODO complete el método según la documentación	 

    }

    /**
     * Selecciona la forma que se encuentre en el punto dado, si la hay. Si no hay una forma allí, no hace nada
     * @param punto Punto donde se desea seleccionar la forma
     */
    public void hacerClick( Punto punto )
    {
        seleccionada = null;
        Iterator iter = elementos.iterator( );
        while( iter.hasNext( ) && seleccionada == null )
        {
            IForma f = ( IForma )iter.next( );
            if( f.estaDentro( punto ) )
                seleccionada = f;
        }

    }

    /**
     * Retorna la forma seleccionada <br>
     * @return Forma seleccionada
     */
    public IForma darSeleccionada( )
    {
        return seleccionada;
    }

    /**
     * Elimina la forma <b>seleccionada</b> del diagrama. El diagrama ha sido modificado.
     */
    public void eliminarFigura( )
    {
        elementos.remove( seleccionada );
        seleccionada = null;
        modificado = true;

     
    }

    /**
     * Retorna si el diagrama ha sido modificado <br>
     * @return true si el diagrama ha sido modificado, false de lo contrario
     */
    public boolean haSidoModificado( )
    {
        return modificado;
    }

    /**
     * Elimina todos los elementos del diagrama. <b>post: </b>El diagrama queda como no modificado. No hay forma seleccionada ni archivo en donde se guarda el diagrama.
     */
    public void reiniciar( )
    {
        elementos.clear( );
        modificado = false;
        seleccionada = null;
        archivo = null;

  
    }
    // ------------------------------------------------------
    // Persistencia
    // ------------------------------------------------------

    /**
     * Construye un diagrama a partir de un archivo. El formato del archivo está descrito en la descripción del ejercicio
     * @param absolutePath Path del archivo donde está la descripción del diagrama.
     * @throws FormatoInvalidoException Se lanza esta excepción en caso que el formato del archivo sea incorrecto.
     * @throws IOException Se lanza esta excepción en caso que halla problemas con la lectura, como no tener permisos en el archivo o que el archivo no exista.
     */
    public void abrir( String absolutePath ) throws FormatoInvalidoException, IOException
    {
        List<IForma> elementosAnteriores = new ArrayList<IForma>( );

        // Cargar los elementos
        BufferedReader br = new BufferedReader( new FileReader( absolutePath ) );
        String linea = br.readLine( );
        try
        {
            // Salvar los elementos anteriores por si se presenta algún error
            elementosAnteriores.addAll( elementos );

            // Eliminar los elementos anteriores
            elementos.clear( );

            // Crear los elementos
            int cuantosElementos = Integer.parseInt( linea );
            for( int i = 0; i < cuantosElementos; i++ )
            {
                linea = br.readLine( );
                IForma elemento = crearElemento( linea, br );
                if( elemento != null )
                    elementos.add( elemento );
            }

            br.close( );
        }
        catch( NumberFormatException nfe )
        {
            br.close( );
            elementos.clear( );
            elementos.addAll( elementosAnteriores );
            throw new FormatoInvalidoException( linea );
        }

        // Reemplazar el nombre de archivo viejo
        archivo = new File( absolutePath );
    }

    /**
     * Crea un elemento a partir del tipo y de la lectura de un archivo.
     * @param tipoElemento Tipo de elemento que se va crear.
     * @param br Flujo de datos que permite encontrar las características de los elementos.
     * @return El elemento creado.
     * @throws FormatoInvalidoException Se lanza está excepción en caso que el formato del archivo sea incorrecto.
     */
    private IForma crearElemento( String tipoElemento, BufferedReader br ) throws FormatoInvalidoException
    {
        IForma nuevo = null;
        try
        {
            if( Inicio.TIPO.equals( tipoElemento ) )
            {
                nuevo = new Inicio( br );
            }
            else if( Fin.TIPO.equals( tipoElemento ) )
            {
                nuevo = new Fin( br );
            }
            else if( Entrada.TIPO.equals( tipoElemento ) )
            {
                nuevo = new Entrada( br );
            }
            else if( ProcesoDatos.TIPO.equals( tipoElemento ) )
            {
                nuevo = new ProcesoDatos( br );
            }
            else if( Decision.TIPO.equals( tipoElemento ) )
            {
                nuevo = new Decision( br );
            }
            else if( SalidaInformacion.TIPO.equals( tipoElemento ) )
            {
                nuevo = new SalidaInformacion( br );
            }
            else if( Conexion.TIPO.equals( tipoElemento ) )
            {
                String lineaPuntosInicio = br.readLine( );
                String lineaPuntosFin = br.readLine( );
                String texto = br.readLine( );
                
                Punto pInicio = cargarPunto( lineaPuntosInicio );
                Punto pFin = cargarPunto( lineaPuntosFin );
                nuevo = new Conexion( ( IFormaBasica )buscarFormaBasica( pInicio ), ( IFormaBasica )buscarFormaBasica( pFin ) );
                nuevo.cambiarTexto( texto );
            }

            return nuevo;
        }
        catch( IOException e )
        {
            throw new FormatoInvalidoException( e.getMessage( ) );
        }
        catch( FormatoInvalidoException e )
        {
            throw new FormatoInvalidoException( e.getMessage( ) );
        }

    }

    /**
     * Carga un punto a partir de la línea que estaba en el archivo. <br>
     * @param lineaPuntos La línea con la información del punto.
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es el esperado.
     */
    private Punto cargarPunto( String lineaPuntos ) throws FormatoInvalidoException
    {
        String[] strValores = lineaPuntos.split( ";" );
        if( strValores.length != 2 )
        {
            throw new FormatoInvalidoException( lineaPuntos );
        }

        try
        {
            int x = Integer.parseInt( strValores[ 0 ] );
            int y = Integer.parseInt( strValores[ 1 ] );
            Punto p = new Punto( x, y );
            return p;
        }
        catch( NumberFormatException nfe )
        {
            throw new FormatoInvalidoException( lineaPuntos );
        }
    }

    /**
     * Devuelve el nombre del archivo.
     * @return El nombre del archivo. En caso que el archivo no se halla inicializado retorna null.
     */
    public String darNombreArchivo( )
    {
        if( archivo == null )
        {
            return null;
        }
        return archivo.getAbsolutePath( );
    }
    /**
     * Salva los elementos en el archivo que se está editando.
     * @throws IOException Se lanza esta excepción en caso que halla problemas con la escritura del archivo.
     */
    public void salvar( ) throws IOException
    {
        // TODO complete el método según la documentación	    
    }

    /**
     * Salva los elementos en el archivo descrito por la ruta dado.
     * @param absolutePath Ruta donde se va guardar el archivo donde van a estar los elementos.
     * @throws IOException Se lanza esta excepción en caso que halla problemas con la escritura del archivo.
     */
    public void salvar( String absolutePath ) throws IOException
    {
        archivo = new File( absolutePath );
        salvar( );
    }

    // ------------------------------------------------------
    // Invariante
    // ------------------------------------------------------

    //
    // TODO: Documentar e implementar la invariante de la clase forma

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}