package examples;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class Fullscreen {
    //////////////// Variables /////////////////////////
    boolean fullscreen = false;
    boolean displayChanged = false;
    Robot r;
    long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK + AWTEvent.KEY_EVENT_MASK;
    GraphicsEnvironment ge=null;
    GraphicsDevice gd=null;
    GraphicsDevice myDevice;
    public DisplayMode dm, dm_old;

    ///////////////// Functions /////////////////////////

    public Fullscreen() {
        try {
            this.r = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Fullscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();

        // Save old displaymode and get new one to play with.
        dm_old = gd.getDisplayMode();
        dm = dm_old;
    }

    public boolean init( Frame frame ){
        if(gd.isFullScreenSupported()){
            try {
                gd.setFullScreenWindow( frame );
                fullscreen = true; 
            } catch( Exception e ) {
                System.err.println(e);
                gd.setFullScreenWindow( null );
                fullscreen = false; 
            }
            // Once an application is in full-screen exclusive mode, 
            // it may be able to take advantage of actively setting the display mode.
        }
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {                   
                //blockMouse();
                Robot robot = null;
                try {
                    robot = new Robot();
                } catch (AWTException ex) {
                    Logger.getLogger(Fullscreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.CTRL_MASK);
            }
          }, 0, 1 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
        return fullscreen;
    }

    public void exit() {
        if (fullscreen){
            GraphicsEnvironment ge=null;
            GraphicsDevice gd=null;
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gd = ge.getDefaultScreenDevice();
            if(gd.isFullScreenSupported()){
                gd.setFullScreenWindow(null);
                if( displayChanged ) {
                    myDevice.setDisplayMode( dm_old ); 
                }
                fullscreen = false; 
            } 
        }
    }
    public void blockMouse(){
        r.mouseMove(0, 0);
    }
    public void blockKeyboard(){
        Toolkit.getDefaultToolkit().addAWTEventListener( new AWTEventListener(){
            public void eventDispatched(AWTEvent e) {
                if (e instanceof KeyEvent) {
                    KeyEvent key=(KeyEvent)e;
                    if (key.getID() == KeyEvent.KEY_PRESSED){
                        System.out.println("Pressed-> "+key.getKeyCode());
                    }else if (key.getID() == KeyEvent.KEY_RELEASED){
                        System.out.println("Released-> "+key.getKeyCode());
                        if(key.getKeyCode()==27){
//                            r.keyRelease(18);
//                            r.keyRelease(157);
                        }
                    }else if (key.getID() == KeyEvent.KEY_TYPED){
                        //System.out.println(key);
                    }
                    System.out.println(key);
                }
            }
        }, eventMask);
    }
    public int getHeight() { 
        //System.out.println("dm.getHeight:"+dm.getHeight());//ddd
        return dm.getHeight(); 
    }

    public int getWidth() { 
        //System.out.println("dm.getWidth:"+dm.getWidth());//ddd
        return dm.getWidth(); 
    }
}
