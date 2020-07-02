package com.trust.custody.barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.trust.custody.model.QRModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class QRCode {

    public QRModel createQRCode(String custodian) throws IOException, WriterException, NotFoundException {
        String filePath = "/Users/rodneybarton/git/Accountable/src/main/resources/" + custodian.replace(" ", "");
        int size = 33;
        String fileType = "png";
        String charset = "UTF-8";
        File fileLocation = new File(filePath);
        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(EncodeHintType.CHARACTER_SET, charset);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(custodian, BarcodeFormat.QR_CODE, size, size, hintMap);
        int width = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, width);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, fileLocation );
        System.out.println("You have successfully created QR Code.");
        System.out.println("QRCode output is: " + readQRCode(filePath, charset, hintMap));
        return QRModel.builder()
                .image(image)
                .fileType(fileType)
                .fileLocation(fileLocation)
                .build();
    }

    public String readQRCode(String path, String charset, Map hashMap) throws IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));

        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }
}