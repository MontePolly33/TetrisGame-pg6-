package menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import sound.SoundManager;

public class Gravity {
    private static Timeline timeline;
    private static Timeline AItimeline;

    public static void startGravity(GameBoard board1, GameBoard board2, BlockSequencer sequencer, boolean hasAI) {
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            Random rand = new Random();

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
                    newBlock1 = RandomBlock.getSpecificBlock(sequencer.getShapeSequence().get(sequencer.getp1SequenceIndex()), rand.nextInt(4));
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
                    newBlock2 = RandomBlock.getSpecificBlock(sequencer.getShapeSequence().get(sequencer.getp2SequenceIndex()), rand.nextInt(4));
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


        if (hasAI) {
            AItimeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                Random rand = new Random();
                AI ai = new AI();

                if (board1.isGameOver() || board2.isGameOver()) {
                    System.out.println("Game Over! AI has stopped running");
                    SoundManager.playGameFinishSound();
                    AItimeline.stop();
                    return;
                }

                TetrisBlock tempBlock2 = GameLoop.getCurrentBlock2();
                if (board2.checkVertCollision(tempBlock2)) {
                    board2.saveBlockToGrid(tempBlock2);

                    TetrisBlock newBlock2;

                    if (sequencer.getp2SequenceIndex() >= sequencer.getp1SequenceIndex()){
                        newBlock2 = RandomBlock.getRandomBlock();
                        sequencer.addToSequence(newBlock2.getShapeIndex());
                    }
                    else {
                        newBlock2 = RandomBlock.getSpecificBlock(sequencer.getShapeSequence().get(sequencer.getp2SequenceIndex()), rand.nextInt(4));
                    }
                    sequencer.incrementp2Sequence();

                    GameLoop.setCurrentBlock2(newBlock2);
                    board2.renderBlock(newBlock2);
                }
                else {
                    String nextMove = ai.findBestMove(board2, tempBlock2);
                    switch (nextMove) {
                        case "Rotate" -> tempBlock2.rotate();
                        case "Left" -> tempBlock2.moveLeft();
                        case "Right" -> tempBlock2.moveRight();
                        case "Down" -> tempBlock2.moveDown();
                        default -> {}
                    }
                    board2.renderBlock(tempBlock2);
                }
            }));

            AItimeline.setCycleCount(Timeline.INDEFINITE);
            AItimeline.play();
        }
    }

    public static void pauseGravity(boolean pause) {
        if (timeline != null) {
            if (pause) { timeline.pause(); }
            else { timeline.play(); }
        }

        if (AItimeline != null) {
            if (pause) { AItimeline.pause(); }
            else { AItimeline.play(); }
        }
    }
}