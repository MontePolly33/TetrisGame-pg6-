package menu;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sound.SoundManager;

public class GameBoard extends Canvas {
    public static final int TILE_SIZE = 25;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private int[][] grid = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private String[][] colorGrid = new String[BOARD_HEIGHT][BOARD_WIDTH];

    private TetrisBlock currentBlock;
    private boolean blockJustLanded;

    public GameBoard() {
        setWidth(BOARD_WIDTH * TILE_SIZE);
        setHeight(BOARD_HEIGHT * TILE_SIZE);
    }

    public boolean BlockJustLanded(){
        return blockJustLanded;
    }

    public void setCurrentBlock(TetrisBlock block){
        this.currentBlock = block;
    }

    public void startBlockLandCooldown(){
        blockJustLanded = true;
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.millis(120));
        pause.setOnFinished(e -> blockJustLanded = false);
        pause.play();
    }

    public void renderBlock(TetrisBlock block) {
        this.currentBlock = block;

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Draw background grid
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (grid[row][col] == 1) {
                    gc.setFill(Color.web(colorGrid[row][col]));
                    gc.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw current block
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
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(3);                               //drawing lines for boundaries
        gc.strokeRect(0,0, getWidth(), getHeight());
    }

    public void saveBlockToGrid(TetrisBlock block) {
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();
        String color = block.getColor();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int gridRow = y + row;
                    int gridCol = x + col;
                    if (gridRow >= 0 && gridRow < BOARD_HEIGHT && gridCol >= 0 && gridCol < BOARD_WIDTH) {
                        grid[gridRow][gridCol] = 1;
                        colorGrid[gridRow][gridCol] = color;
                    }
                }
            }
        }

        // 👇 Automatically clear lines after saving block
        checkAndClearLines();
    }

    public void checkAndClearLines() {
        for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
            boolean fullLine = true;

            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (grid[row][col] == 0) {
                    fullLine = false;
                    break;
                }
            }

            if (fullLine) {
                clearLine(row);
                SoundManager.playEraseLineSound();  //where actually clear line
                row++; // Recheck the same row
            }
        }
    }

    private void clearLine(int rowToClear) {
        for (int row = rowToClear; row > 0; row--) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                grid[row][col] = grid[row - 1][col];
                colorGrid[row][col] = colorGrid[row - 1][col];
            }
        }

        // Clear the top row
        for (int col = 0; col < BOARD_WIDTH; col++) {
            grid[0][col] = 0;
            colorGrid[0][col] = null;
        }
    }

    public boolean checkCollision(TetrisBlock block) {
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] == 1) {
                    int gridY = y + row + 1;
                    int gridX = x + col;

                    if (gridY >= BOARD_HEIGHT || (grid[gridY][gridX] == 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // public void placeBlock(TetrisBlock block) {
    // saveBlockToGrid(block);
    //}

    public boolean isGameOver() {
        for (int col = 0; col < BOARD_WIDTH; col++) {
            if (grid[0][col] == 1) {
                return true;
            }
        }
        return false;
    }

    //public TetrisBlock getCurrentBlock() {
    // return currentBlock;
    //}
}