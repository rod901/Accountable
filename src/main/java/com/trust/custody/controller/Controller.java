package com.trust.custody.controller;

import com.trust.custody.barcode.QRCode;
import com.trust.custody.images.FileChooser;
import com.trust.custody.model.QRModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/getQR")
    @ResponseBody
    public QRModel getQR(@RequestParam String custodian) {
        QRCode qrCode = new QRCode();
        QRModel qrModel = null;
        try {
            qrModel = qrCode.createQRCode(custodian);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrModel;
    }

    //TODO: Method is not finished
    @GetMapping(value = "/getImage")
    public String showImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getImageFrame();

        return "welcome to spring boot application";
    }
}
