package menu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sound.SoundManager;

public class GameLoop extends Application {

    private static GameBoard board1;
    private static GameBoard board2;

    private static TetrisBlock currentBlock1;
    private static TetrisBlock currentBlock2;

    @Override
    public void start(Stage stage) {
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

        currentBlock1 = RandomBlock.getRandomBlock();
        currentBlock2 = RandomBlock.getRandomBlock();

        board1.renderBlock(currentBlock1);
        board2.renderBlock(currentBlock2);

        Gravity.startGravity(board1, board2); //here
        // Gravity.startGravity(board2, currentBlock2);

        scene.setOnKeyPressed(event -> {
            if (!board1.isGameOver() && !board2.isGameOver()) {
                switch (event.getCode()) {
                    // Player 1 (WASD)
                    case A -> {
                        currentBlock1.moveLeft();
                        //SoundManager.playMoveTurnSound();
                    }

                    case D ->{
                        currentBlock1.moveRight();
                        //SoundManager.playMoveTurnSound();
                    }
                    case S -> {
                        int blockBottom1 = currentBlock1.getY() + currentBlock1.getShape().length;
                        if (blockBottom1 < board1.BOARD_HEIGHT && !board1.checkCollision(currentBlock1)){
                            currentBlock1.moveDown();
                        }
                        //SoundManager.playMoveTurnSound();
                    }
                    case W -> {
                        currentBlock1.rotate();
                        //SoundManager.playMoveTurnSound();
                    }

                    // Player 2 (Arrow keys)
                    case LEFT -> {
                        currentBlock2.moveLeft();
                        //SoundManager.playMoveTurnSound();
                    }
                    case RIGHT ->{
                        currentBlock2.moveRight();
                        //SoundManager.playMoveTurnSound();
                    }
                    case DOWN -> {
                        int blockBottom2 = currentBlock2.getY() + currentBlock2.getShape().length;
                        if (blockBottom2 < board2.BOARD_HEIGHT && !board2.checkCollision(currentBlock2)){
                            currentBlock2.moveDown();
                        }
                        //SoundManager.playMoveTurnSound();
                    }
                    case UP -> {
                        currentBlock2.rotate();
                        //SoundManager.playMoveTurnSound();
                    }

                    default -> {}
                }
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