package com.mio.pdf.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFUtil {

	public byte[] createImage(String fileName,String imagetype) throws IOException {
		File file = new File("src/main/resources/pdfbox/", fileName);
		PDDocument doc = PDDocument.load(file);
		PDFRenderer renderer = new PDFRenderer(doc);
		BufferedImage image = renderer.renderImage(0);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		boolean flag = ImageIO.write(image, imagetype, out);
		byte[] b = null;
		if(flag){
			b = out.toByteArray();
		}
		doc.close();
		return b;

	}
}
