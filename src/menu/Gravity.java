package menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import sound.SoundManager;

public class Gravity {
    private static Timeline timeline;

    public static void startGravity(GameBoard board1, GameBoard board2, BlockSequencer sequencer) {
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {

            // === PLAYER 1 BLOCK ===
            TetrisBlock tempBlock1 = GameLoop.getCurrentBlock1();
            if (board1.checkVertCollision(tempBlock1)) {
                //tempBlock1.setLanded(true);
                board1.saveBlockToGrid(tempBlock1);
                TetrisBlock newBlock1;

                if (board1.isGameOver()) {
                    System.out.println("Game Over (P1)!");
                    SoundManager.playGameFinishSound();
                    timeline.stop();
                    return;
                }

                if (sequencer.getp1SequenceIndex() >= sequencer.getp2SequenceIndex()){
                    newBlock1 = RandomBlock.getRandomBlock();
                    sequencer.addToSequence(newBlock1.getShapeIndex());
                }
                else {
                    newBlock1 = RandomBlock.getSpecificBlock(sequencer.getShapeSequence().get(sequencer.getp1SequenceIndex()));
                }
                sequencer.incrementp1Sequence();

                GameLoop.setCurrentBlock1(newBlock1);
                board1.renderBlock(newBlock1);
            }
            else {
                tempBlock1.moveDown();
                board1.renderBlock(tempBlock1);
            }

            // === PLAYER 2 BLOCK ===
            TetrisBlock tempBlock2 = GameLoop.getCurrentBlock2();
            if (board2.checkVertCollision(tempBlock2)) {
                //tempBlock2.setLanded(true);
                board2.saveBlockToGrid(tempBlock2);

                if (board2.isGameOver()) {
                    System.out.println("Game Over (P2)!");
                    SoundManager.playGameFinishSound();
                    timeline.stop();
                    return;
                }

                TetrisBlock newBlock2;

                if (sequencer.getp2SequenceIndex() >= sequencer.getp1SequenceIndex()){
                    newBlock2 = RandomBlock.getRandomBlock();
                    sequencer.addToSequence(newBlock2.getShapeIndex());
                }
                else {
                    newBlock2 = RandomBlock.getSpecificBlock(sequencer.getShapeSequence().get(sequencer.getp2SequenceIndex()));
                }
                sequencer.incrementp2Sequence();

                GameLoop.setCurrentBlock2(newBlock2);
                board2.renderBlock(newBlock2);
            }
            else {
                tempBlock2.moveDown();
                board2.renderBlock(tempBlock2);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void pauseGravity(boolean pause) {
        if (timeline != null) {
            if (pause) { timeline.pause(); }
            else { timeline.play(); }
        }
    }
}