import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class QuadTreeImage {
    private final static int MAX_LEVEL = 200;
    private final static int MIN_SIZE = 1;

    public static void main(String[] args) throws IOException {

        BufferedImage image = ImageIO.read(new File("src/bg.png"));

        QuadTree quadTree = new QuadTree(image, 0, 0, image.getWidth(), image.getHeight());

        Graphics g = image.getGraphics();
        drawQuadTree(quadTree, g);

        ImageIO.write(image, "png", new File("obrazek_quadtree.png"));
    }

    private static void drawQuadTree(QuadTree quadTree, Graphics g) {
        if (quadTree.getLevel() == MAX_LEVEL || quadTree.getSize() <= MIN_SIZE || !quadTree.isMixed()) {
            g.setColor(Color.RED);
            g.drawRect(quadTree.getX(), quadTree.getY(), quadTree.getSize(), quadTree.getSize());
        } else {
            drawQuadTree(quadTree.getNorthWest(), g);
            drawQuadTree(quadTree.getNorthEast(), g);
            drawQuadTree(quadTree.getSouthWest(), g);
            drawQuadTree(quadTree.getSouthEast(), g);
        }
    }

    private static class QuadTree {
        private BufferedImage image;
        private int x, y, size, level;

        private QuadTree northWest, northEast, southWest, southEast;

        public QuadTree(BufferedImage image, int x, int y, int size) {
            this(image, x, y, size, 0);
        }

        private QuadTree(BufferedImage image, int x, int y, int size, int level) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.size = size;
            this.level = level;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        public int getLevel() {
            return level;
        }

        public QuadTree getNorthWest() {
            if (northWest == null) {
                int subSize = size / 2;
                northWest = new QuadTree(image, x, y, subSize, level + 1);
            }
            return northWest;
        }

        public QuadTree getNorthEast() {
            if (northEast == null) {
                int subSize = size / 2;
                int subX = x + subSize;
                northEast = new QuadTree(image, subX, y, subSize, level + 1);
            }

            return northEast;

        }
        public QuadTree getSouthWest() {
            if (southWest == null) {
                int subSize = size / 2;
                int subY = y + subSize;
                southWest = new QuadTree(image, x, subY, subSize, level + 1);
            }
            return southWest;
        }


        public QuadTree getSouthEast() {
            if (southEast == null) {
                int subSize = size / 2;
                int subX = x + subSize;
                int subY = y + subSize;
                southEast = new QuadTree(image, subX, subY, subSize, level + 1);
            }
            return southEast;
        }
        private boolean isMixed() {
            if (size == 1) {
                return false;
            }
            boolean hasWhite = false;
            boolean hasBlack = false;

            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {

                    if (i >= image.getWidth() || j >= image.getHeight()) {
                        continue;
                    }
                    int pixel = image.getRGB(i, j);
                    Color color = new Color(pixel);
                    if (color.equals(Color.WHITE)) {
                        hasWhite = true;
                    } else if(color.equals(Color.BLACK)) {
                        hasBlack = true;
                    }
                    if (hasWhite && hasBlack) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}