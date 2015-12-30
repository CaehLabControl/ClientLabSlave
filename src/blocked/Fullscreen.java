package blocked;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;



@SuppressWarnings("serial")
class Fullscreen {
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    public static void main(String[] args) {

    final JDialog frame = new JDialog();

    JButton btn1 = new JButton("Full-Screen");
    btn1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            device.setFullScreenWindow(frame);
        }
    });
    JButton btn2 = new JButton("Normal");
    btn2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            device.setFullScreenWindow(null);
        }
    });

    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    panel.add(btn1);
    panel.add(btn2);
    //frame.add(panel);

    //frame.pack();
    frame.setVisible(true);

    }
}