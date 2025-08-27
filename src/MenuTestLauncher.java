import javafx.application.Application;
import javafx.stage.Stage;
import Menu.UI;

public class MenuTestLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {
        UI ui = new UI(primaryStage);
        ui.showMainMenu(); // This opens your menu screen
    }

    public static void main(String[] args) {
        launch(args); // Required to run JavaFX
    }
}