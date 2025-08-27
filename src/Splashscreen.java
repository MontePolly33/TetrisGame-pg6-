
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Splashscreen extends Application {

    @Override
    public void start(Stage primaryStage) {
// Title and details
        Label title = new Label("🎮 Welcome to Tetris!");
     //  Label name = new Label("Name: Montgomerie Solonou Polly");
      //  Label studentId = new Label("Student ID: s5398257");
      //  Label course = new Label("Course: Object-Oriented Programming");

// Layout
        VBox root = new VBox(15); // 15 pixels spacing
        root.getChildren().addAll(title);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

// Scene setup
        Scene scene = new Scene(root, 400, 250);

        primaryStage.setTitle("Splash Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}