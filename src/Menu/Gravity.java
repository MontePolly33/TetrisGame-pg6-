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
                block[0].setLanded(true); //mark as landed to prevent further input
                board.saveBlockToGrid(block[0]); //Store landed block in the grid
                block[0] = RandomBlock.getRandomBlock(); // assign new block
            }

            board.renderBlock(block[0]);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}