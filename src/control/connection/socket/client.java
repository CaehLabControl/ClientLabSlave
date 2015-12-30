/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.connection.socket;

import control.trayIcon;
import static control.trayIcon.trayIcon;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.main.connect;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos
 */
public class client  extends WebSocketClient  {
    
    
    /**
     * @param args the command line arguments
     * @throws org.json.JSONException
     */
    public client(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public client(URI serverURI) {
        super(serverURI);
    }
    public void client(String s){
        
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("New connection opened");
        connect.send("{'action':'addClient', 'rolClient':'slave'}");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
        connect.send("{'action':'removeClient'}");
    }

    @Override
    public void onMessage(String message) {
        reciveMessage(message);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {      
//        clientConn = new client(new URI("ws://10.10.73.27:8887"), new Draft_10());
//        clientConn.connect();
    }    
    public void reciveMessage(String message){
        try {
            JSONArray jsonContainerMessage = new JSONArray(message);
            JSONObject body = jsonContainerMessage.getJSONObject(0);
            String contentMessage = body.getJSONArray("body").getJSONObject(0).getString("message");
            if(body.getJSONArray("body").getJSONObject(0).has("rolClient")){
                String rolClient = body.getJSONArray("body").getJSONObject(0).getString("rolClient");
                if(rolClient.equals("slave")){
                    System.out.println(contentMessage);
                    if(contentMessage.equals("1")){
                        System.out.println("Block");
                        new trayIcon().setVisibleScreenBlock(true);
                    }else{
                        System.out.println("Un block");
                        new trayIcon().setVisibleScreenBlock(true);
                    }                    
                }
            }else{
                trayIcon.displayMessage("Mensage nuevo dice... ", contentMessage, TrayIcon.MessageType.INFO);
                System.out.println(contentMessage);
            }            
        } catch (JSONException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
