/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.block;

/**
 *
 * @author Administrador
 */
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * http://jc-mouse.blogspot.com/
 * @author Mouse
 */
public class background extends javax.swing.JPanel {
    String imagen;
    public background(int w,int h,String foto){    
        this.setSize(w,h);
        imagen = foto;
    }
        
    @Override
    public void paint(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/resources/pictures/"+imagen));        
        g.drawImage(imagenFondo.getImage(),0,0, tamanio.width, tamanio.height, this);        
        setOpaque(false);
        super.paintComponent(g);
    }
    
}
