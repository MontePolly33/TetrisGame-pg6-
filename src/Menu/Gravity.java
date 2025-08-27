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
            int blockBottom = block[0].getY() + block[0].getShape().length;
            int maxRows = (int)(board.getHeight() / GameBoard.TILE_SIZE);

            if (blockBottom < maxRows && !board.checkCollision(block[0])) {
                block[0].moveDown();
            } else {
                block[0].setLanded(true); // mark as landed
                board.saveBlockToGrid(block[0]); // save block

                if (board.isGameOver()) {
                    timeline.stop(); // stop gravity timer
                    System.out.println("Game Over!");
                    //gameOver = true;
                    return; // stop everything
                } else {
                    block[0] = RandomBlock.getRandomBlock(); // only assign if not game over
                    board.renderBlock(block[0]); // optional: render new block only if game not over
                }
            }


            board.renderBlock(block[0]); //block moves down
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}