package com.trust.custody.images;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Image {

    public void displayImage(String pathName) {
        JFrame frame = new JFrame("Load Image Sample");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(new LoadImage(pathName));
        frame.pack();
        frame.setVisible(true);
    }
}
