package main;

import control.connection.socket.client;
import control.trayIcon;
import static control.trayIcon.trayIcon;
import interfaces.login;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.java_websocket.drafts.Draft_10;

/**
*  @web http://www.jc-mouse.net/
 * @author Mouse
 */
public class main {
    public static client connect;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket SERVER_SOCKET = new ServerSocket(1334);
            try {
                connect = new client(new URI("ws://10.10.20.153:8887"), new Draft_10());
            } catch (URISyntaxException ex) {
                Logger.getLogger(trayIcon.class.getName()).log(Level.SEVERE, null, ex);
            }
            connect.connect();
            while(true){
                System.out.println(connect.getReadyState());
                if(connect.getReadyState().toString().equals("OPEN")){
                    System.out.println("Connected");
                    new trayIcon(new login());
                    trayIcon.displayMessage("Estado", "Conexión con el servidor exitosa", TrayIcon.MessageType.INFO);
                    break;
                }else if(connect.getReadyState().toString().equals("CLOSED")){
                    trayIcon.displayMessage("Estado", "No fue posible conectarse al servidor", TrayIcon.MessageType.INFO);
                    System.out.println("Disconnected");
                    break;
                }else{
                    System.out.println("Connecting...");
                } 
            }
        } catch (IOException x) {
            JOptionPane.showMessageDialog(null, "Otra instancia de la aplicación se está ejecutando...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
    }
}
