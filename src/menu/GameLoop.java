package menu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import sound.SoundManager;

public class GameLoop extends Application {

    private static GameBoard board1;
    private static GameBoard board2;

    private static TetrisBlock currentBlock1;
    private static TetrisBlock currentBlock2;

    private boolean isPaused = false;
    private boolean playSounds = true;  // Change this to reference the JSON file for config stuff

    private static BlockSequencer sequencer;

    @Override
    public void start(Stage stage) {
        Random rand = new Random();

        board1 = new GameBoard();
        board2 = new GameBoard();

        board1.setId("P1");
        board2.setId("P2");

        Group root = new Group();
        board1.setLayoutX(0);
        board2.setLayoutX(GameBoard.BOARD_WIDTH * GameBoard.TILE_SIZE + 50);
        root.getChildren().addAll(board1, board2);

        Scene scene = new Scene(root, 800, 600); // Adjust dimensions as needed
        stage.setScene(scene);
        stage.setTitle("Tetris Multiplayer");
        stage.show();

        sequencer = new BlockSequencer(0, 0, new ArrayList<>());

        int shapeIndex = rand.nextInt(TetrisShapes.getShapeCount());
        int rotation = rand.nextInt(4);
        currentBlock1 = new TetrisBlock(shapeIndex, rotation, TetrisShapes.getShape(shapeIndex, rotation), TetrisShapes.getColor(shapeIndex));
        currentBlock2 = new TetrisBlock(shapeIndex, rotation, TetrisShapes.getShape(shapeIndex, rotation), TetrisShapes.getColor(shapeIndex));

        board1.renderBlock(currentBlock1);
        board2.renderBlock(currentBlock2);

        Gravity.startGravity(board1, board2, sequencer);

        scene.setOnKeyPressed(event -> {
            if (!board1.isGameOver() && !board2.isGameOver() && !isPaused) {
                switch (event.getCode()) {
                    // Player 1 (WASD)
                    case A -> {
                        if (!board1.checkHorCollision(currentBlock1, "Left")) {
                            currentBlock1.moveLeft();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }

                    case D ->{
                        if (!board1.checkHorCollision(currentBlock1, "Right")) {
                            currentBlock1.moveRight();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }
                    case S -> {
                        int block1Bottom = currentBlock1.getY() + currentBlock1.getShape().length;
                        if (block1Bottom < board1.BOARD_HEIGHT && !board1.checkVertCollision(currentBlock1)){
                            currentBlock1.moveDown();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }
                    case W -> {
                        if (board1.rotationCheckAndMove(currentBlock1) == 0){
                            currentBlock1.rotate();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }

                    // Player 2 (Arrow keys)
                    case LEFT -> {
                        if (!board2.checkHorCollision(currentBlock2, "Left")) {
                            currentBlock2.moveLeft();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }
                    case RIGHT ->{
                        if (!board2.checkHorCollision(currentBlock2, "Right")) {
                            currentBlock2.moveRight();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }
                    case DOWN -> {
                        int block2Bottom = currentBlock2.getY() + currentBlock2.getShape().length;
                        if (block2Bottom < board2.BOARD_HEIGHT && !board2.checkVertCollision(currentBlock2)){
                            currentBlock2.moveDown();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }
                    case UP -> {
                        if (board2.rotationCheckAndMove(currentBlock2) == 0){
                            currentBlock2.rotate();
                            //if (playSounds) { SoundManager.playMoveTurnSound(); }
                        }
                    }

                    default -> {}
                }
            }

            if (event.getCode() == KeyCode.P) {
                if (!isPaused) {
                    Gravity.pauseGravity(true);
                    isPaused = true;
                }
                else {
                    Gravity.pauseGravity(false);
                    isPaused = false;
                }
            }

            if (event.getCode() == KeyCode.S) {
                if (playSounds) { playSounds = false; }
                else { playSounds = true; }
            }

            if (event.getCode() == KeyCode.ESCAPE) {System.exit(0);}

            board1.renderBlock(currentBlock1);
            board2.renderBlock(currentBlock2);
        });
    }

    // Static setters for Gravity.java to update the current block
    public static void setCurrentBlock1(TetrisBlock block) {
        currentBlock1 = block;
    }

    public static void setCurrentBlock2(TetrisBlock block) {
        currentBlock2 = block;
    }

    public static TetrisBlock getCurrentBlock1(){
        return currentBlock1;
    }

    public static TetrisBlock getCurrentBlock2(){
        return currentBlock2;
    }

    public static void main(String[] args) {
        launch();
    }
}