package Menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class ConfigurationMenu {

    public void start(Stage stage) {
        Label title = new Label("Configuration Settings");

        Button soundToggle = new Button("Toggle Sound");
        Button difficultyToggle = new Button("Toggle Difficulty");
        Button backButton = new Button("Back to Main Menu");

        soundToggle.setOnAction(e -> {
            System.out.println("Sound toggled.");
        });

        difficultyToggle.setOnAction(e -> {
            System.out.println("Difficulty changed.");
        });

        backButton.setOnAction(e -> {
// Go back to Main Menu using the same stage
            new UI(stage).showMainMenu();
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(title, soundToggle, difficultyToggle, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Configuration");
        stage.show();
    }
}