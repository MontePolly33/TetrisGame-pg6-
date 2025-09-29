package Menu;

public class TetrisBlock {
    private int[][] shape;
    private int x, y;
    private boolean hasLanded = false;
    private int shapeIndex;
    private int rotation;
    private String color;

    public void setLanded(boolean landed){
    this.hasLanded = landed;
}
    public boolean isLanded(){
    return hasLanded;
}

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
           System.out.println(shape.length);
       }
    }

    public void moveLeft() {
       if(x > 0){
           x--;
       }
    }

    public void moveRight() {
       if (x + getShape()[0].length < 10){
           x++;
       }
    }

    public String getColor() {
        return TetrisShapes.getColor(shapeIndex);
    }

    // For debugging
    public void resetPosition() {
        this.x = 3;
        this.y = 0;
    }

    public boolean tryMoveDown(GameBoard board) {
        // Move down temporarily
        this.y++;

        // Check collision
        if (board.checkCollision(this)) {
            moveUp(); // Undo
            this.setLanded(true);
            return false;
        }

        return true; // Move succeeded
    }
}