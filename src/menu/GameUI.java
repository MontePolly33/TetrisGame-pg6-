package menu;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// UNUSED
public class GameUI {
    public void start(Stage primaryStage) {
        GameBoard board = new GameBoard();

        StackPane root = new StackPane();
        root.getChildren().add(board);

        Scene scene = new Scene(root, 300, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tetris Game");
        primaryStage.show();

       // board.startGame();
    }
}
