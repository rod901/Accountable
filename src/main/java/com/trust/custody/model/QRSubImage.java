package com.trust.custody.model;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.File;

@Data
@Builder
public class QRSubImage {
    private BufferedImage image;
    private String formatName;
    private File output;
}
