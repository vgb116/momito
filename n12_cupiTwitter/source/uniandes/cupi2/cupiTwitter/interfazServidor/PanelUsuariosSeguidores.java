/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelUsuariosSeguidores.java,v 1.9 2010/04/26 21:08:20 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiTwitter
 * Autor: Camilo Alvarez Duran - 19-oct-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiTwitter.interfazServidor;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uniandes.cupi2.cupiTwitter.servidor.mundo.Usuario;

/**
 * Panel con la lista de usuarios seguidores
 */
public class PanelUsuariosSeguidores extends JPanel
{

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Lista de usuarios seguidores
     */
    private JList lstSeguidores;

    /**
     * Scroll para la lista de seguidores
     */
    private JScrollPane scrSeguidores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel de usuarios seguidores
     */
    public PanelUsuariosSeguidores( )
    {

        scrSeguidores = new JScrollPane( );
        lstSeguidores = new JList( );

        setLayout( new GridLayout( ) );

        scrSeguidores.setBorder( BorderFactory.createTitledBorder( "Usuarios Seguidores" ) );
        scrSeguidores.setViewportView( lstSeguidores );

        add( scrSeguidores );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de usuarios
     * 
     * @param usuarios La lista de usuarios
     */
    public void actualizar( ArrayList usuarios )
    {
        lstSeguidores.setListData( usuarios.toArray( new Usuario[0] ) );
    }
}
