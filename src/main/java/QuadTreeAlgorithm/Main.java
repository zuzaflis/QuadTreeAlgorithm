package QuadTreeAlgorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Main {


    public static void main(String[] args) throws IOException {
        File file = new File("bg.png");
        BufferedImage image = ImageIO.read(file);
        // uzyskanie rozmiarów obrazka
        int width = image.getWidth();
        int height = image.getHeight();

        BufferImage first = new BufferImage(image,width,height);
        int[][] pixels = first.getPixelsValue();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.print(pixels[i][j] + " ");
            }
            System.out.println();
        }
    }
}
