import Menu.UI;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class MainProject extends Application {

    @Override
    public void start(Stage stage) {
        // Splash screen content
        Label title = new Label("🕹️ Welcome to Tetris!");
        Label name = new Label("By: Montgomerie Solonou Polly & Felix Van Vugt");
        Label studentId = new Label("Student IDs: s5398257 & s5334258 (respectively)");
        Label course = new Label("Course: Object-Oriented Programming");

        VBox root = new VBox(15);
        root.getChildren().addAll(title, name, studentId, course);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Scene splashScene = new Scene(root, 400, 250);
        stage.setScene(splashScene);
        stage.setTitle("Splash Screen");
        stage.show();

        // Wait 5 seconds then switch to main menu
        PauseTransition delay = new PauseTransition(Duration.seconds(3));  //shows splash screen for 5 seconds then goes to menu
        delay.setOnFinished(event -> {
            UI ui = new UI(stage);
            ui.showMainMenu();
        });
        delay.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}