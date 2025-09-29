package Menu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Gravity {
    private static Timeline timeline;

    public static void startGravity(GameBoard board) {
        final TetrisBlock[] block = { RandomBlock.getRandomBlock() }; // wrap in array
        board.renderBlock(block[0]);

        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            int blockBottom = block[0].getY() + block[0].getShape().length;       // NOTE: Length of shape is how many rows long it is
            int maxRows = GameBoard.BOARD_HEIGHT;

            if (blockBottom < maxRows && !board.checkCollision(block[0])) {
                block[0].moveDown();
            } else {
                block[0].setLanded(true); // mark as landed
                board.saveBlockToGrid(block[0]);
                board.checkAndClearLines();// save block

                // Runs to check if the top row is full
                if (board.isGameOver()) {
                    timeline.stop(); // stop gravity timer
                    System.out.println("Game Over!");
                    return; // stop everything
                } else {
                    block[0] = RandomBlock.getRandomBlock(); // only assign new block if not game over
                }
            }

            board.renderBlock(block[0]);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}