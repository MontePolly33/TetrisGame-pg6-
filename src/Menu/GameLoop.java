package Menu; // Only include this if UI.java is also in the Menu package
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class GameLoop extends javafx.application.Application {
    private boolean running = true; //gameover when blocks touches top
    private boolean gameOver = false; // stop keypad access after gameover

    private boolean blockJustLanded = false;
    public void start(Stage stage) {
        GameBoard board = new GameBoard();
        Group root = new Group();
        root.getChildren().add(board);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tetris Game");
        stage.show();


        // Start the game loop (gravity)
        Gravity.startGravity(board);

// Keyboard movement inside JavaFX
        scene.setOnKeyPressed(event -> {
            TetrisBlock currentBlock = board.getCurrentBlock(); // requires getter



            if (board.isGameOver()) {
                running = false;
                System.out.println("Game Over!");
                return;

            }

            if (currentBlock == null) return;

            switch (event.getCode()) {
                case W -> {}
                case UP -> {currentBlock.rotate();}
                case A -> {}
                case LEFT -> {currentBlock.moveLeft();}
                case D -> {}
                case RIGHT -> {currentBlock.moveRight();}
                case S -> {
                    if (blockJustLanded) {
                        break;
                    }
                    int bottom = currentBlock.getY() + currentBlock.getShape().length;
                    int maxRows = (int) (board.getHeight() / GameBoard.TILE_SIZE);

                    if (bottom < maxRows && !board.checkCollision(currentBlock)) {
                        currentBlock.moveDown();
                    } else {
                        board.placeBlock(currentBlock);
                        board.checkAndClearLines(); // clearing
                        currentBlock = RandomBlock.getRandomBlock();
                        blockJustLanded = true;

                    // ⏳ Short delay to prevent instant re-triggering
                        PauseTransition pause = new PauseTransition(Duration.millis(120)); // slightly longer
                        pause.setOnFinished(e -> blockJustLanded = false);
                        pause.play();
                    }
                }
                case Q -> {}
                case ESCAPE -> {System.exit(0);}
                default -> {}
            }
            board.renderBlock(currentBlock);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}