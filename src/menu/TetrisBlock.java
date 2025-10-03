package menu;

import javafx.scene.paint.Color;

public class TetrisBlock {
    private int[][] shape;
    private int x, y;
    private int shapeIndex;
    private int rotation;
    private String color;
    //private boolean hasLanded = false;

    //public void setLanded(boolean landed){ this.hasLanded = landed; }
    //public boolean isLanded(){ return hasLanded; }

    public TetrisBlock(int shapeIndex, int rotation, int[][] shape, String color) {
        this.shapeIndex = shapeIndex;
        this.rotation = rotation;
        this.color = color;
        this.shape = shape;
        this.x = 5; // start position horizontally
        this.y = 0; // start position at top
    }

    public void rotate(){
        rotation = (rotation + 1) % TetrisShapes.getRotationCount(shapeIndex);
        shape = TetrisShapes.getShape(shapeIndex, rotation);
    }

    public int[][] pseudoRotate() {
        int pseudoRotation = (rotation + 1) % TetrisShapes.getRotationCount(shapeIndex);
        return TetrisShapes.getShape(shapeIndex, pseudoRotation);
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp(){ y--; }

    public void moveDown() {
       int [][] shape = getShape();
       if (y + shape.length < GameBoard.BOARD_HEIGHT){
           y++;
       }
    }

    public void moveLeft() {
           x--;
    }

    public void moveRight() {
           x++;
    }

    public String getColor() {
        return TetrisShapes.getColor(shapeIndex);
    }

    public void reposition(int moveHor){
        x += moveHor;
    }

    public int getShapeLongest() {
        int longest = 0;

        for (int row = 0; row < shape.length; row++) {
            double tempLongest = 0;

            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) { tempLongest += 1; }
                else if (shape[row][col] == 0) { tempLongest += 0.5; }
                if (tempLongest > longest) {
                    longest = (int) Math.ceil(tempLongest);
                }
            }
        }
        return longest;
    }

    // For debugging
    public void resetPosition() {
        this.x = 3;
        this.y = 0;
    }

    // tryMoveDown was here but it was unused so it was moved to the InputHandler file for reference
}