package com.trust.custody.images;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class FileChooser extends JFrame implements ActionListener {
    static JLabel label;

    public JPanel getImageFrame() {
        JFrame frame = new JFrame("Select a photo to give custody to");
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Attach");
        FileChooser fileChooser = new FileChooser();

        button.addActionListener(fileChooser);

        // Panel to add the buttons and labels
        JPanel p = new JPanel();
        p.add(button);

        label = new JLabel("no file selected");

        p.add(label);
        return p;
    }

    public void actionPerformed(ActionEvent evt) {
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, & GIF Images", "jpg", "jpeg", "png", "gif");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setMultiSelectionEnabled(true);

        int r = jFileChooser.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File files[] = jFileChooser.getSelectedFiles();

            int t = 0;
            // set text to blank
            label.setText("");

            // set the label to the path of the selected files
            while (t++ < files.length) {
                label.setText(label.getText() + " " + files[t - 1].getName());
                Image image = new Image();
                image.displayImage(files[t - 1].getAbsolutePath());
            }
        } else {
            label.setText("the user cancelled the operation");
        }
    }
}
