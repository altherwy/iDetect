/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idetect;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author youssef 19
 */
public class MyButton extends javax.swing.JButton {

    public String path_name = null;

    public MyButton() {
        path_name = "toolbar.jpg";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = (Image) t.getImage(path_name);
        g.drawImage(img, 0, 0, this);
    }
}
