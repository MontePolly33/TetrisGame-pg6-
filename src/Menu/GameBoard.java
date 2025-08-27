package Menu;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class GameBoard extends Canvas {
    public static final int TILE_SIZE = 20;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private int[][] grid = new int[20][10];
    private int[] gridCols = {0,1,2,3,4,5,6,7,8,9};
    private String[][] colorGrid = new String[20][10];
    //private boolean blockLanded = false; //lock flag

    public GameBoard() {
        setWidth(BOARD_WIDTH * TILE_SIZE);
        setHeight(BOARD_HEIGHT * TILE_SIZE); //canvas
    }


    public void checkAndClearLines() {
        for (int row = grid.length - 1; row >= 0; row--) {
            boolean fullLine = true;

// Check if the row is full
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    fullLine = false;
                    break;
                }
            }

// If row is full, clear it
            if (fullLine) {
                clearLine(row);
                row++; // Check same row index again after shifting down
            }
        }
    }


    private void clearLine(int rowToClear) {
        for (int row = rowToClear; row > 0; row--) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = grid[row - 1][col]; // Move row above down
            }
        }

// Clear the top row (now duplicated)
        for (int col = 0; col < grid[0].length; col++) {
            grid[0][col] = 0;
            colorGrid[0][col] = null;
        }
    }


    private TetrisBlock currentBlock;

    public void renderBlock(TetrisBlock block) {
        this.currentBlock = block; // Update the currentBlock field

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if (grid[row][col] == 1){
                    gc.setFill(Color.web(colorGrid[row][col]));
                    gc.fillRect(col * TILE_SIZE, row*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    gc.setFill(Color.web(block.getColor()));
                    gc.fillRect((x + col) * TILE_SIZE, (y + row) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    public void saveBlockToGrid(TetrisBlock block){
        int [][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();
        String color = block.getColor();

        for (int row = 0; row < shape.length; row++ ){
            for (int col = 0; col <shape[row].length; col ++){
                if (shape[row][col] == 1){
                    int gridRow = y + row;
                    int gridCol = x + col;
                    if(gridRow >= 0 && gridRow <20 && gridCol >=0 && gridCol <10){
                        grid[gridRow][gridCol] = 1;
                        colorGrid[gridRow][gridCol] = color;
                    }
                }
            }
        }

    }


    public boolean checkCollision(TetrisBlock block) {
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int gridY = y + row + 1; // checking next row (downward)
                    int gridX = x + col;

// Check if it's out of bounds (bottom of the board)
                    if (gridY >= BOARD_HEIGHT) {
                        return true;
                    }

// Check if it's colliding with an existing block
                    if (grid[gridY][gridX] == 1) {
                        return true;
                    }
                }
            }
        }

        return false; // No collision
    }

    public void placeBlock(TetrisBlock block) {
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    grid[y + i][x + j] = shape[i][j];
                }
            }
        }
    }



    public boolean isGameOver() {
// Check the top row (row 0) for any non-zero blocks
        for (int col : gridCols) {
            if (grid[0][col] != 0) {
                return true;
            }
        }
        return false;
    }





    public TetrisBlock getCurrentBlock() {
        return currentBlock;
    }
}