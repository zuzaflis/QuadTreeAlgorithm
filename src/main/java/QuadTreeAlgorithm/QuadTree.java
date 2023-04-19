package QuadTreeAlgorithm;

public class QuadTree {
    private Node root;

    private static class Node{
        private int x; //x,y wpsolrzedne lewego gornego rogu w obszarze w wezle
        private int y;
        private int width;
        private int height;
        private int value;
        private boolean isLeaf;
        private Node[] children;

        public Node(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.isLeaf = false;
            this.children = new Node[4]; // kazdy moze miec maksymalnie 4 node dzieci
        }
    }

    public QuadTree(int[][] pixels) {
        this.root = buildTree(pixels,0,0, pixels.length, pixels[0].length);
    }
    private Node buildTree(int[][] pixels, int x, int y, int width,int height){
            Node node = new Node(x,y,width,height);

            //jeśli mamy rozmiar 1x1 oznacza to ze jest pojedynczy piksel lub srednia z pikseli

            if(width == 1 && height == 1){
                node.value = pixels[x][y];
                node.isLeaf = true;
            } else {
                boolean isSame = true;
                int firstValue = pixels[x][y];

                for(int i = x; i < x+width; i++){
                    for(int j = y; j< + height; j++){
                        if( pixels[x][y] != firstValue){
                            isSame = false;
                            break;
                        }
                    }
                }

               if(isSame){
                   node.isLeaf = true;
                   node.value = firstValue;
               } else {
                   int halfWidth = width/2;
                   int halfHeight = height/2;

                   node.children[0] = buildTree(pixels, x, y, halfWidth, halfHeight);
                   node.children[1] = buildTree(pixels, x + halfWidth, y, halfWidth, halfHeight);
                   node.children[2] = buildTree(pixels, x + halfWidth, y + halfHeight, halfWidth, halfHeight);
                   node.children[3] = buildTree(pixels,x, y + halfHeight, halfWidth, halfHeight);
               }
            }

            return node;
    }

}
