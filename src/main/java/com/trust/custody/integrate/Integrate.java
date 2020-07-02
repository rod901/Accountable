package com.trust.custody.integrate;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.trust.custody.barcode.QRCode;
import com.trust.custody.images.FileChooser;
import com.trust.custody.images.Image;
import com.trust.custody.model.QRModel;
import com.trust.custody.model.QRSubImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Integrate {
    Image image = new Image();
    FileChooser fileChooser = new FileChooser();

//    public void merge() throws NotFoundException, IOException, WriterException {
//        QRModel qrModel = new QRCode().createQRCode();
//        JLabel label = new JLabel(new ImageIcon(qrModel.getImage()));
//
//        ArrayList<QRSubImage> qrSubImage = breakQRCodeIntoParts(qrModel);
//
//        JFrame frame = new JFrame();
//
//        JPanel filePanel = fileChooser.getImageFrame();
//        JLabel label2 = new JLabel(new ImageIcon(qrModel.getImage()));
//
//        JLayeredPane jLayeredPane = new JLayeredPane();
//        jLayeredPane.add(label);
//        jLayeredPane.add(label2);
//
////        frame.add(filePanel);
////        frame.add(label);
//        frame.add(jLayeredPane);
//        frame.show();
//
//        System.out.println();
//    }

    public ArrayList<QRSubImage> breakQRCodeIntoParts(QRModel qrModel) throws IOException {

//        final BufferedImage source = ImageIO.read(new File("<sourceDir>/1fby-6t-555d.png"));
        final BufferedImage source = qrModel.getImage();
        int idx = 0;
        ArrayList<QRSubImage> list = new ArrayList<QRSubImage>();

        for (int x = 0; x < source.getWidth(); x += 11) {
            for (int y = 0; y < source.getHeight(); y += 11) {
                ImageIO.write(source.getSubimage(x, y, 11, 11), "png", new File("/Users/rodneybarton/git/Accountable/src/main/resources/" + idx++ + ".png"));

                QRSubImage qrSubImage = QRSubImage.builder()
                        .image(source.getSubimage(x, y, 11, 11))
                        .formatName("png")
                        .output(new File("/Users/rodneybarton/git/Accountable/src/main/resources/" + idx++ + ".png"))
                        .build();
                list.add(qrSubImage);
                System.out.println();
            }
        }
        return list;
    }
}
