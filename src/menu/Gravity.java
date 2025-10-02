package menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sound.SoundManager;

public class Gravity {
    private static Timeline timeline;

    public static void startGravity(GameBoard board1, GameBoard board2) {
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {

            // === PLAYER 1 BLOCK ===
            TetrisBlock tempBlock1 = GameLoop.getCurrentBlock1();
            boolean blockBottom1 = board1.checkCollision(tempBlock1);
            if (blockBottom1 || tempBlock1.isLanded()) {
                tempBlock1.setLanded(true);
                board1.saveBlockToGrid(tempBlock1);

                if (board1.isGameOver()) {
                    System.out.println("Game Over (P1)!");
                    SoundManager.playGameFinishSound();
                    timeline.stop();
                    return;
                }

                TetrisBlock newBlock1 = RandomBlock.getRandomBlock();
                GameLoop.setCurrentBlock1(newBlock1);
                tempBlock1 = newBlock1;
                board1.renderBlock(newBlock1);

            } else {
                tempBlock1.moveDown();
                board1.renderBlock(tempBlock1);
            }

            // === PLAYER 2 BLOCK ===
            TetrisBlock tempBlock2 = GameLoop.getCurrentBlock2();
            boolean blockBottom2 = board2.checkCollision(tempBlock2);
            if (blockBottom2 || tempBlock2.isLanded()) {
                tempBlock2.setLanded(true);
                board2.saveBlockToGrid(tempBlock2);

                if (board2.isGameOver()) {
                    System.out.println("Game Over (P2)!");
                    SoundManager.playGameFinishSound();
                    timeline.stop();
                    return;
                }

                TetrisBlock newBlock2 = RandomBlock.getRandomBlock();
                GameLoop.setCurrentBlock2(newBlock2);
                tempBlock2 = newBlock2;
                board2.renderBlock(newBlock2);

            } else {
                tempBlock2.moveDown();
                board2.renderBlock(tempBlock2);
            }

        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void stopGravity() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}