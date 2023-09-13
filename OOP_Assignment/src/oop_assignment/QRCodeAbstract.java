package oop_assignment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

abstract class QRCodeAbstract {

    // The text to be encoded in the QR code
    String text = "Undefined QR";

    public void generateQR() throws WriterException, IOException {

        // The size of the QR code in pixels
        int width = 400;
        int height = 400;

        // The QR code format
        BarcodeFormat format = BarcodeFormat.QR_CODE;

        // The encoding hints
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.MARGIN, 2);

        // Generate the QR code
        BitMatrix matrix = new MultiFormatWriter().encode(text, format, width, height, hints);

        // Create a BufferedImage from the QR code matrix
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        // Display the QR code image
        ImageIO.write(image, "png", new File("qrcode.png"));
    }

    public void displayQR() throws Exception {
        // Load the QR code image
        BufferedImage image = ImageIO.read(new File("qrcode.png"));

        // Create a JLabel and set the image of the JLabel to the QR code image
        JLabel label = new JLabel(new ImageIcon(image));

        // Create a JFrame and add the JLabel to the JFrame
        JFrame frame = new JFrame("QR Code Displayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setText(String text){
    }
    
}
