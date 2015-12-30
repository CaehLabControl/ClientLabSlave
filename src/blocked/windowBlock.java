/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocked;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JWindow;

/**
 *
 * @author Carlos
 */
public class windowBlock {
    public static int alto;
    public static int ancho;
    static JWindow dial;
    public windowBlock(){
        initComponents();
    }
    public void ColocarImagen(String img) {
        fondo p = new fondo(ancho,alto,img);
        dial.add( p , BorderLayout.CENTER);
        p.repaint();
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                windowBlock jWindowDemo = new windowBlock();
            }
        });
    }

    private void initComponents() {
        dial =new JWindow();
        tamaño();
        Dimension d = new Dimension(ancho, alto);
        //dial.setDefaultCloseOperation(0);
        dial.setSize(d);
        ColocarImagen("blocked.jpg");        
        dial.toFront();
        //new blockedWindow(dial).block();
        dial.setLocationRelativeTo(null);
        //dial.setTitle("Pantalla Bloqueada");
        dial.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icons/icon-launch.png")));      
        //dial.setModal(true);
        dial.setAlwaysOnTop (true);
        //dial.setModalityType(ModalityType.APPLICATION_MODAL);
        //dial.setUndecorated(true);
        //dial.setResizable(false);
        dial.setVisible(true);
    }
    public void tamaño() {
        // TODO code application logic here
        //Obtiene el tamaño de la pantalla
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //obtiene la resolucion de la pantalla en PPP (Puntos por pulgada)
        int sr = Toolkit.getDefaultToolkit().getScreenResolution();
        //muestra la informacion por la consola de java
//        System.out.println("Tamaño de pantalla: " + d.width + "x" + d.height + ", definición: " + sr + " ppp");
        ancho = d.width;
        alto = d.height;
    }
}
