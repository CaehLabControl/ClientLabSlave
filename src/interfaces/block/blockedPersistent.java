package interfaces.block;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class blockedPersistent {
    
    private JFrame jframe=null;
    
    /**
     * Constructor de clase
     * @param f
     */
    public blockedPersistent( JFrame f )
    {
        this.jframe = f;
    }
    
    /**
     * ejecuta una tarea cada "n" tiempo
     * Para evitar que el usuario utilice las teclas (WINDOWS + D)(TAB) y asi perder el foco
     * de la aplicación, cada 50 milisegundos se envia el JFrame al frente y se cambia su propiedad a maximizado
     */
    public void block()
    {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() 
            {
                @Override
                public void run() {                   
                    try { 
                        front();
                    } catch (AWTException ex) {
                        Logger.getLogger(blockedPersistent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }, 500, 50 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
    }
    
    /**
     * 
     */
    public void front() throws AWTException{
        Robot r = new Robot();
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_HOME);
        jframe.setExtendedState( JFrame.MAXIMIZED_BOTH );//maximizado
        jframe.toFront();
        jframe.setAlwaysOnTop(true);//siempre al frente  
    }
    
}//--> fin
