/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazEditorDiagramaFlujo.java,v 1.5 2009/10/21 15:57:51 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_editorDiagramaFlujo
 * Autor: Camilo Alvarez Duran - 06-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.editorDiagramaFlujo.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.editorDiagramaFlujo.mundo.Conexion;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Decision;
import uniandes.cupi2.editorDiagramaFlujo.mundo.DiagramaFlujo;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Entrada;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Fin;
import uniandes.cupi2.editorDiagramaFlujo.mundo.FormatoInvalidoException;
import uniandes.cupi2.editorDiagramaFlujo.mundo.IForma;
import uniandes.cupi2.editorDiagramaFlujo.mundo.IFormaBasica;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Inicio;
import uniandes.cupi2.editorDiagramaFlujo.mundo.ProcesoDatos;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;
import uniandes.cupi2.editorDiagramaFlujo.mundo.SalidaInformacion;

/**
 * Es la clase principal de la interfaz
 */
public class InterfazEditorDiagramaFlujo extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante Ninguna
     */
    public static final String NINGUNA = "NINGUNA";

    /**
     * Constante Inicio
     */
    public static final String INICIO = "INICIO";

    /**
     * Constante Fin
     */
    public static final String FIN = "FIN";

    /**
     * Constante Entrada
     */
    public static final String ENTRADA = "ENTRADA";

    /**
     * Constante Proceso de datos
     */
    public static final String PROCESO_DATOS = "PROCESO DATOS";

    /**
     * Constante Decision
     */
    public static final String DECISION = "DECISION";

    /**
     * Constante Dependencia
     */
    public static final String SALIDA_DE_INFORMACION = "SALIDA DE INFORMACION";

    /**
     * Constante Conexi�n
     */
    public static final String CONEXION = "CONEXION";

    /**
     * Constante seleccionar
     */
    public static final String SELECCIONAR = "SELECCIONAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private DiagramaFlujo diagrama;

    /**
     * Es la ruta hasta el �ltimo directorio de donde se carg� o salv� un archivo
     */
    private String ultimoDirectorio;

    /**
     * Es el panel donde se muestran las figuras
     */
    private PanelEditor panelEditor;

    /**
     * Es el panel donde se muestran los botones para controlar la aplicaci�n
     */
    private PanelBotones panelBotones;

    /**
     * Es la barra del men�
     */
    private BarraMenu barra;

    /**
     * Es el panel donde se encuentran los botones para ejecutar los puntos de extensi�n
     */
    private PanelExtension panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye e inicializa la interfaz de la aplicaci�n
     */
    public InterfazEditorDiagramaFlujo( )
    {
        diagrama = new DiagramaFlujo( );
        ultimoDirectorio = "./data";

        barra = new BarraMenu( this );
        setJMenuBar( barra );

        panelEditor = new PanelEditor( this, diagrama );
        panelBotones = new PanelBotones( this );
        panelExtension = new PanelExtension( this );

        add( panelBotones, BorderLayout.WEST );
        add( panelEditor, BorderLayout.CENTER );
        add( panelExtension, BorderLayout.SOUTH );

        setSize( 800, 600 );
        setTitle( "Editor de Diagrama de Flujo" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega una figura del tipo que se encuentra seleccionado en el panel de botones. <br>
     * <b>post: </b> Se agreg� la figura y se actualiz� la visualizaci�n del diagrama.
     * @param punto Punto donde se desea agregar la figura
     */
    public void agregarFigura( Punto punto )
    {
        String tipo = darOpcionSeleccionada( );

        if( tipo.equals( INICIO ) )
        {
            IForma inicio;
            inicio = new Inicio( punto );
            diagrama.agregarElemento( inicio );
            panelBotones.cambiarASeleccionar( );

        }
        else if( tipo.equals( FIN ) )
        {
            IForma fin;
            fin = new Fin( punto );
            diagrama.agregarElemento( fin );
            panelBotones.cambiarASeleccionar( );
        }
        else if( tipo.equals( ENTRADA ) )
        {
            IForma entrada;
            String res = JOptionPane.showInputDialog( this, "Texto para al entrada", "" );
            if( res != null )
            {
                entrada = new Entrada( punto );
                entrada.cambiarTexto( res );
                diagrama.agregarElemento( entrada );
                panelBotones.cambiarASeleccionar( );
            }
        }
        else if( tipo.equals( PROCESO_DATOS ) )
        {
            IForma procesoD;
            String res = JOptionPane.showInputDialog( this, "Texto para el procesamiento de datos", "" );
            if( res != null )
            {
                procesoD = new ProcesoDatos( punto );
                procesoD.cambiarTexto( res );
                diagrama.agregarElemento( procesoD );
                panelBotones.cambiarASeleccionar( );
            }
        }
        else if( tipo.equals( DECISION ) )
        {
            IForma decision;
            String res = JOptionPane.showInputDialog( this, "Texto para la decisi�n", "" );
            if( res != null )
            {
                decision = new Decision( punto );
                decision.cambiarTexto( res );
                diagrama.agregarElemento( decision );
                panelBotones.cambiarASeleccionar( );
            }
        }
        else if( tipo.equals( SALIDA_DE_INFORMACION ) )
        {
            IForma salida;
            String res = JOptionPane.showInputDialog( this, "Texto para la salida", "" );
            if( res != null )
            {
                salida = new SalidaInformacion( punto );
                salida.cambiarTexto( res );
                diagrama.agregarElemento( salida );
                panelBotones.cambiarASeleccionar( );
            }
        }
        panelEditor.actualizar( );
    }

    /**
     * Agrega un conector si la opci�n se encuentra seleccionada en el panel de botones.<br>
     * <b>post: </b> Se agreg� el conector y se actualiz� la visualizaci�n del diagrama. <br>
     *               Si el elemento de inicio es de tipo Decision, se pregunta el texto que desea en el conector siendo las �nicas opciones SI o NO
     * @param elementoInicio Elemento donde inicia el conector
     * @param elementoDestino Elemento destino del conector
     */
    public void agregarConector( IFormaBasica elementoInicio, IFormaBasica elementoDestino )
    {
        if( darOpcionSeleccionada( ).equals( CONEXION ) )
        {
            Conexion con = new Conexion( elementoInicio, elementoDestino );

            if( elementoInicio.darTipo( ).equals( Decision.TIPO ) )
            {
                String[] opciones = { "Si", "No"};
                String res = (String) JOptionPane.showInputDialog(null, "Selecci�n",
                    "Seleccione el tipo de decisi�n", JOptionPane.QUESTION_MESSAGE, null, 
                    opciones, 
                    opciones[1]); 
                if( res != null )
                {
                    con.cambiarTexto( res );
                    diagrama.agregarElemento( con );
                    panelBotones.cambiarASeleccionar( );
                }
            }else{
                diagrama.agregarElemento( con ); 
                panelBotones.cambiarASeleccionar( );
            }
            
        }
    }
    /**
     * Busca un elemento en el diagrama en el punto dado por par�metro
     * @param punto Punto donde se desea buscar una figura
     * @return La figura que se encuentra en el punto dado, null si no existe
     */
    public IForma buscarElemento( Punto punto )
    {
        return diagrama.buscarElemento( punto );
    }

    /**
     * Retorna la opci�n seleccionada en el panel de botones
     * @return Retorna InterfazEditorDiagramaFlujo.NINGUNA, InterfazEditorDiagramaFlujo.SELECCIONAR, InterfazEditorDiagramaFlujo.FIN, InterfazEditorDiagramaFlujo.INICIO,
     *         InterfazEditorDiagramaFlujo.ENTRADA, InterfazEditorDiagramaFlujo.DECISION, InterfazEditorDiagramaFlujo.CONEXION,
     *         InterfazEditorDiagramaFlujo.SALIDA_DE_INFORMACION, o InterfazEditorDiagramaFlujo.PROCESO_DATOS
     */
    public String darOpcionSeleccionada( )
    {
        return panelBotones.darFigura( );
    }

    /**
     * Intenta seleccionar una figura dado un punto. <br>
     * Cada figura debe saber si las coordenadas indicadas sirven para seleccionar esa figura o no. <br>
     * <b>post: </b> Si hab�a una figura que se pudiera seleccionar en esa posici�n entonces en el atributo "seleccionada" queda una referencia y la figura se pinta como
     * seleccionada.
     * @param punto punto donde se desea seleccionar una figura
     */
    public void seleccionar( Punto punto )
    {
        diagrama.hacerClick( punto );
        panelEditor.actualizar( );
    }

    /**
     * Muestra la ventana de texto utilizada para modificar el texto de la figura seleccionada. <br>
     * Si no hay una figura seleccionada o es un elemento de fin o inicio, no hace nada<>
     */
    public void mostrarVentanaTexto( )
    {
        IForma seleccionada = diagrama.darSeleccionada( );
        if( seleccionada != null && !seleccionada.darTipo( ).equals( Inicio.TIPO ) && !seleccionada.darTipo( ).equals( Fin.TIPO ) )
        {
            String texto = seleccionada.darTexto( );
            Font fuente = seleccionada.darFuente( );
            DialogoTexto dt = new DialogoTexto( this, texto, fuente );
            dt.setVisible( true );
        }
    }

    /**
     * Cambia el texto de la figura seleccionada. <br>
     * <b>pre: </b>Hay una figura seleccionada <br>
     * <b>post: </b> Se modific� el texto de la figura.
     * @param texto El texto que va a tener la figura
     * @param fuente La fuente del texto de la figura
     */
    public void cambiarTexto( String texto, Font fuente )
    {
        IForma seleccionada = diagrama.darSeleccionada( );
        if( seleccionada != null )
        {
            seleccionada.cambiarFuente( fuente );
            seleccionada.cambiarTexto( texto );

            panelEditor.actualizar( );
        }
    }

    /**
     * Elimina la figura seleccionada <br>
     * <b>post: </b>Si hay una figura seleccionada se elimina
     */
    public void eliminarFiguraSeleccionada( )
    {
        if( diagrama.darSeleccionada( ) != null )
        {
            diagrama.eliminarFigura( );
            panelEditor.actualizar( );
        }
    }

    /**
     * Retorna la figura seleccionada <br>
     * @return Figura seleccionada
     */
    public IForma darFiguraSeleccionada( )
    {
        return diagrama.darSeleccionada( );
    }

    /**
     * Mueve el centro de la figura seleccionada al punto dado por par�metro, s�lo si este punto esta dentro de la figura
     * @param punto Punto donde se desea mover la figura
     */
    public void moverFiguraSeleccionada( Punto punto )
    {
        IForma fb = diagrama.darSeleccionada( );
        if( fb.estaDentro( punto ) )
            fb.moverFigura( punto );
    }

    /**
     * Reinicia el diagrama despu�s de pedir una confirmaci�n. <br>
     * El m�todo revisa si el diagrama ha sido modificado. Si lo ha sido, <br>
     * pide una al usuario confirmar si desea guardar su trabajo antes de salir, no guardarlo, o cancelar la acci�n. <br>
     * Si no cancela la acci�n, se debe reiniciar el diagrama y el panel editor. <b>post: </b> Se reinici� el diagrama
     */
    public void reiniciar( )
    {
        boolean reiniciar = true;
        if( diagrama.haSidoModificado( ) )
        {
            reiniciar = pedirConfirmacion( );
        }

        if( reiniciar )
        {
            diagrama.reiniciar( );
            panelEditor.actualizar( );
        }
    }

    /**
     * Le pide al usuario un archivo para abrir y lo carga en el dibujo. <br>
     * Debe verificar que el diagrama actual no haya sido modificado, antes de abrir el archivo. En caso en el que <br>
     * haya sido modificado, debe pedir la confirmaci�n al usuario para guardarlo u omitir la acci�n en dado caso. <b>post: </b>Se carg� el diagrama que estaba guardado
     */
    public void abrir( )
    {
        boolean abrir = true;
        if( diagrama.haSidoModificado( ) )
        {
            abrir = pedirConfirmacion( );
        }

        if( abrir )
        {
            JFileChooser fc = new JFileChooser( ultimoDirectorio );
            fc.setDialogTitle( "Abrir Composici�n" );
            fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
            fc.setMultiSelectionEnabled( false );

            int resultado = fc.showSaveDialog( this );

            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File seleccionado = fc.getSelectedFile( );
                ultimoDirectorio = seleccionado.getParentFile( ).getAbsolutePath( );

                try
                {
                    diagrama.abrir( seleccionado.getAbsolutePath( ) );
                    panelEditor.actualizar( );
                }
                catch( FormatoInvalidoException e )
                {
                    JOptionPane.showMessageDialog( this, "Hay problemas con el formato del archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Hubo problemas cargando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Salva el diagrama en el archivo del que se hab�a cargado o donde se hab�a salvado la �ltima vez. <br>
     * Si se trata de un diagrama nuevo y no se ha salvado entonces se pregunta el nombre del archivo donde se salvar�. <br>
     * <b>post: </b> Se salv� el diagrama.
     */
    public void salvar( )
    {
        String nombreArchivo = diagrama.darNombreArchivo( );
        if( nombreArchivo == null )
        {
            salvarComo( );
        }
        else
        {
            try
            {
                diagrama.salvar( );
            }
            catch( IOException e )
            {
                JOptionPane.showMessageDialog( this, "Hubo problemas salvando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Salva el diagrama en un archivo, del cual se pregunta el nombre al usuario. <br>
     * <b>post: </b> Se salv� el diagrama en un archivo que se le pregunt� al usuario.
     */
    public void salvarComo( )
    {
        JFileChooser fc = new JFileChooser( ultimoDirectorio );
        fc.setDialogTitle( "Salvar como" );
        fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
        fc.setMultiSelectionEnabled( false );

        boolean termine = false;

        int resultado = fc.showSaveDialog( this );

        while( !termine )
        {
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File seleccionado = fc.getSelectedFile( );
                ultimoDirectorio = seleccionado.getParentFile( ).getAbsolutePath( );

                int respuesta = JOptionPane.YES_OPTION;

                // Si el archivo ya existe hay que pedir confirmaci�n para
                // sobre escribirlo
                if( seleccionado.exists( ) )
                {
                    respuesta = JOptionPane.showConfirmDialog( this, "�Desea sobreescribir el archivo seleccionado?", "Sobreescribir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE );
                }

                // Si la respuesta fue positiva (o si no fue necesario hacer la
                // pregunta) se graba el archivo
                if( respuesta == JOptionPane.YES_OPTION )
                {
                    try
                    {
                        diagrama.salvar( seleccionado.getAbsolutePath( ) );
                        termine = true;
                    }
                    catch( IOException e )
                    {
                        JOptionPane.showMessageDialog( this, "Hubo problemas salvando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                    }
                }
                else
                {
                    resultado = fc.showSaveDialog( this );
                }
            }
            else
            {
                termine = true;
            }
        }
    }

    /**
     * Este m�todo solicita una confirmaci�n para realizar una acci�n que har�a que el trabajo se perdiera. <br>
     * Presenta una ventana con las opciones "Si","No" y "Cancelar". <br>
     * Si se selecciona "Si", entonces se salva el archivo actual. <br>
     * Si se selecciona "No", el archivo no se salva y se retorna true <br>
     * Si se selecciona "Cancelar", el archivo no se salva pero se retorna false para que la acci�n no se realice.
     * @return Retorna true si la acci�n se debe realizar; retorna false en caso contrario.
     */
    private boolean pedirConfirmacion( )
    {
        boolean cerrar = true;

        int respuesta = JOptionPane.showConfirmDialog( this, "Desea salvar el archivo actual antes de continuar?", "Salvar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE );

        if( respuesta == JOptionPane.YES_OPTION )
        {
            salvar( );
        }

        else if( respuesta == JOptionPane.NO_OPTION )
        {
            // No hace nada
        }

        else if( respuesta == JOptionPane.CANCEL_OPTION )
        {
            cerrar = false;
        }

        return cerrar;
    }

    /**
     * Cierra la aplicaci�n, pero antes pregunta si se quiere salvar la composici�n actual y se salva si el usuario as� lo requiere.
     */
    public void dispose( )
    {
        boolean cerrar = true;
        if( diagrama.haSidoModificado( ) )
        {
            setVisible( true );
            cerrar = pedirConfirmacion( );
        }

        if( cerrar )
        {
            super.dispose( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Ejecuta el punto de Extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = diagrama.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Ejecuta el punto de Extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = diagrama.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n
     * @param args Son los par�metros de la l�nea de comandos. No se deben usar.
     */
    public static void main( String[] args )
    {
        InterfazEditorDiagramaFlujo ip = new InterfazEditorDiagramaFlujo( );
        ip.setVisible( true );
    }
}