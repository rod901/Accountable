package com.trust.custody.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImage extends Component {
    BufferedImage img;

    public LoadImage(String pathName) {
        try {
            img = ImageIO.read(new File(pathName));
        } catch (IOException e) {
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }
}
