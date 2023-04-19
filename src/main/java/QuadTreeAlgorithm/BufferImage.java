package QuadTreeAlgorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferImage {
    private BufferedImage image;
    private int width;
    private int height;

    public BufferImage(BufferedImage image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }
    public int[][] getPixelsValue (){
        int[][] pixelsValue = new int[height][width];
        for(int y=0; y< height; y++){
            for(int x =0; x<width;x++){
                pixelsValue[y][x] = image.getRGB(x,y);
            }
        }
        return pixelsValue;
    }

}
