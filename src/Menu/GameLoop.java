package Menu; // Only include this if UI.java is also in the Menu package
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.Arrays;


public class GameLoop extends javafx.application.Application {
    private boolean blockJustLanded = false; // Currently unused

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
                if (event.getCode() == KeyCode.ESCAPE) {
                    System.exit(0);
                }
                else {
                    System.out.println("No more inputs!");
                    return;
                }
            }

            if (currentBlock == null) return;

            switch (event.getCode()) {
                case W -> {
                    // Example way of printing array to console
                    // int[] temp = new int[3];
                    // String printMe = Arrays.toString(temp);
                    // System.out.println(printMe);
                }
                case UP -> {currentBlock.rotate();}
                case A -> {}
                case LEFT -> {currentBlock.moveLeft();}
                case D -> {}
                case RIGHT -> {currentBlock.moveRight();}
                case S -> {}
                case DOWN -> {
                    // Currently unused
                    if (blockJustLanded) {
                        break;
                    }

                    int blockBottom = currentBlock.getY() + currentBlock.getShape().length;
                    int maxRows = GameBoard.BOARD_HEIGHT;

                    if (blockBottom < maxRows && !board.checkCollision(currentBlock)) {
                        currentBlock.moveDown();
                    }
                    // Now spawning and placing is only done through the gravity interval to prevent double spawning and double placing
                    // (this also gives you some wiggle room for locking in a piece just like real Tetris)

                    /* else {
                        board.saveBlockToGrid(currentBlock);
                        board.checkAndClearLines(); // clearing
                        currentBlock = RandomBlock.getRandomBlock();

                        // ⏳ Short delay to prevent instant re-triggering (found to be redundant)
                        blockJustLanded = true;
                        PauseTransition pause = new PauseTransition(Duration.millis(500)); // slightly longer
                        pause.setOnFinished(e -> blockJustLanded = false);
                        pause.play();
                    } */
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