package blocked;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;
/**
 * @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class blockedWindow {
    
    private JDialog jwindow=null;
    
    /**
     * Constructor de clase
     */
    public blockedWindow( JDialog w )
    {
        this.jwindow = w;
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
                    front(); 
                }
              }, 300, 10 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
    }
    
    /**
     * 
     */
    public void front(){
        jwindow.toFront();
        jwindow.setAlwaysOnTop(true);//siempre al frente   
        jwindow.setFocusableWindowState(true);
    }
    
}//--> fin
