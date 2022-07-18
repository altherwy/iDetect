/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package idetect;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author youssef 19
 */
public class MyToolBar extends javax.swing.JToolBar{
    public String path_name = "";
    public MyToolBar(String path){
        this.path_name = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();
        URL url = getClass().getResource(path_name);
        Image img = (Image)t.getImage(url);
        g.drawImage(img,0, 0, this);
        //paintComponent(g);
    }


}
