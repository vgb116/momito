package uniandes.cupi2.editorDiagramaFlujo.mundo;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.PrintWriter;

import uniandes.cupi2.editorDiagramaFlujo.mundo.IForma;
import uniandes.cupi2.editorDiagramaFlujo.mundo.Punto;

public class Entrada extends FormaBasica
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para definir el tipo 
     */
    public final static String TIPO = "ENTRADA";
    
    /**
     * Constante para definir el alto
     */
    public final static int ALTO = 40;
    
    /**
     * Constante para definir el ancho
     */
    public final static int ANCHO = 95;
    
    /**
     * Constante para definir el ancho
     */
    public final static int ANCHO_BASE = 20;
    
    public Entrada( Punto punto )
    {
        
        super( punto, new Punto( punto.darX( ) + ANCHO, punto.darY( ) + ALTO ) );
    }

    @Override
    public String darTipo( )
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moverFigura( Punto punto )
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pintar( Graphics2D g )
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pintarSeleccionado( Graphics2D g )
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void pintarTexto( Graphics2D g )
    {
        // TODO Auto-generated method stub
        
    }

    
}
