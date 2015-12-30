package control;
import blocked.blockedScreen;
import interfaces.about;
import interfaces.close;
import interfaces.login;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static main.main.connect;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class trayIcon {
    static public JFrame miframe;
    static public PopupMenu popup = new PopupMenu();
    private Image image =new ImageIcon(getClass().getResource("/resources/icons/icon-launch.png")).getImage() ;
    static public TrayIcon trayIcon;
    //para el Timer
    private Timer timer;    
    private boolean band;

    public trayIcon( JFrame frame){
        trayIcon = new TrayIcon(image, "Control Client", popup);
        this.miframe = frame;
        //comprueba si SystemTray es soportado en el sistema
        if (SystemTray.isSupported()){
            //obtiene instancia SystemTray
            SystemTray systemtray = SystemTray.getSystemTray();
            //acciones del raton sobre el icono en la barra de tareas
            MouseListener mouseListener = new MouseListener() {
            
            public void mouseClicked(MouseEvent evt) {            
                //Si se presiono el boton izquierdo y la aplicacion esta minimizada
                if( evt.getButton() == MouseEvent.BUTTON1 && miframe.getExtendedState()==JFrame.ICONIFIED )
                    MensajeTrayIcon("'Lamento boliviano' \n Y yo estoy aqui, Borracho y loco, \n Y mi corazon idiota, Siempre brillara", MessageType.WARNING);
            }

            public void mouseEntered(MouseEvent evt) {}

            public void mouseExited(MouseEvent evt) {}

            public void mousePressed(MouseEvent evt) {}

            public void mouseReleased(MouseEvent evt) {}
        };

        //ACCIONES DEL MENU POPUP    
        //Restaurar aplicacion
        ActionListener AboutListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {  
                miframe=new about();
                miframe.setVisible(true);
                miframe.setExtendedState( JFrame.NORMAL );
                miframe.repaint();
                band = true;
            }
        };

        //Restaurar aplicacion
        ActionListener RestaurarListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                miframe=new login();
                miframe.setVisible(true);
                miframe.setExtendedState( JFrame.NORMAL );
                miframe.repaint();
                band = true;
            }
        };

        //Salir de aplicacion
        ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
                miframe=new close();
                miframe.setVisible(true);
                miframe.setExtendedState( JFrame.NORMAL );
                miframe.repaint();
                band = true;
            }
        };

        MenuItem about = new MenuItem("Acerca de...");
        about.addActionListener(AboutListener);
        popup.add(about);
        popup.addSeparator();


        MenuItem ItemRestaurar = new MenuItem("Abrir");
        ItemRestaurar.addActionListener(RestaurarListener);
        popup.add(ItemRestaurar);
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(mouseListener);
        popup.addSeparator();

        //Se crean los Items del menu PopUp y se añaden
        MenuItem SalirItem = new MenuItem("Salir");
        SalirItem.addActionListener(exitListener);
        popup.add(SalirItem);

        //Añade el TrayIcon al SystemTray
        try {
            systemtray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println( "Error:" + e.getMessage() );
        }
        } else {
            System.err.println( "Error: SystemTray no es soportado" );
        }

        //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
        miframe.addWindowListener(new WindowAdapter(){
            @Override
            public void windowIconified(WindowEvent e){
               miframe.setVisible(false);//Se oculta JFrame
               //Se inicia una tarea cuando se minimiza
               band = false;
               timer = new Timer();      
               //timer.schedule(new MyTimerTask(),0, 100000 );//Se ejecuta cada 10 segundos
            }
        });
    }
    public void setVisibleScreenBlock(Boolean status){
        miframe=new blockedScreen();
        miframe.setVisible(status);
        miframe.setExtendedState( JFrame.MAXIMIZED_BOTH );
        miframe.repaint();
        band = status;
    }
    public trayIcon() {
        MensajeTrayIcon("Haciendo algo: ", MessageType.INFO);
    }

    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo) {
        trayIcon.displayMessage("Mi Aplicación dice:", texto, tipo);
    }
    
    //clase interna que manejara una accion en segundo plano
    class MyTimerTask extends TimerTask {
        public void run() {
            if(band)//Termina Timer
                timer.cancel();
            else //realiza acción
                Suma();
        }
        //Una accion a realiza cuando la aplicacion a sido minimizada
        public void Suma()
        {
            int num1 = (int) Math.floor(Math.random()*6+1);
            int num2 = (int) Math.floor(Math.random()*6+1);
            if(connect.getReadyState().toString().equals("OPEN")){
                connect.send(""+num1);
                MensajeTrayIcon("Haciendo algo: " + num1 + " + " + num2 +  " = "+ (num1+num2), MessageType.INFO);
            }
        }
    }
}
