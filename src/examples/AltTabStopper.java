/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class AltTabStopper implements Runnable{
    private boolean working = true;
    private JFrame frame;

    public AltTabStopper(JFrame frame){
         this.frame = frame;
    }

    public void stop(){
         working = false;
    }

    public static AltTabStopper create(JFrame frame){
        AltTabStopper stopper = new AltTabStopper(frame);
        new Thread(stopper, "Alt-Tab Stopper").start();
        return stopper;
    }

    public void run(){
        try{
            Robot robot = new Robot();
            while (working){
                 robot.keyRelease(KeyEvent.VK_CONTROL);
                 robot.keyRelease(KeyEvent.VK_ALT);
                 robot.keyRelease(KeyEvent.VK_DELETE);
                 frame.requestFocus();
                 try { 
                     Thread.sleep(10); 
                 } catch(Exception e) {
                 }
            }
        } catch (Exception e) { 
            e.printStackTrace(); System.exit(-1); 
        }
    }
}