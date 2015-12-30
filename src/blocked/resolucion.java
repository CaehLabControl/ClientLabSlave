/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blocked;

/**
 *
 * @author Administrador
 */

import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * @web http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class resolucion {
    public void tamaño() {
        // TODO code application logic here
        //Obtiene el tamaño de la pantalla
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //obtiene la resolucion de la pantalla en PPP (Puntos por pulgada)
        int sr = Toolkit.getDefaultToolkit().getScreenResolution();
        //muestra la informacion por la consola de java
        System.out.println("Tamaño de pantalla: " + d.width + "x" + d.height + ", definición: " + sr + " ppp");
        blockedScreen.ancho = d.width;
        blockedScreen.alto = d.height;
    }
}
