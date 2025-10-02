// This file is currently unused
package unused;

import javafx.application.Application;
import javafx.stage.Stage;
import menu.ui;

public class MenuTestLauncher extends Application {
    @Override
    public void start(Stage primaryStage) {
        ui ui = new ui(primaryStage);
        ui.showMainMenu(); // This opens your menu screen
    }

    public static void main(String[] args) {
        launch(args); // Required to run JavaFX
    }
}