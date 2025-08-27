package Menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class ScoreboardMenu {

    public void start(Stage stage) {
        Label labelTitle = new Label("🏆 Scoreboard");

// Placeholder: You can replace this with a list of high scores later
        Label placeholderScore = new Label("No scores to display yet.");

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> {
            new UI(stage).showMainMenu();
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(labelTitle, placeholderScore, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Scoreboard");
        stage.show();
    }
}