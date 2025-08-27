package Menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Menu.ConfigurationMenu;
import Menu.ScoreboardMenu;
public class UI {

    private Stage mainStage;

    public UI(Stage stage) {
        this.mainStage = stage;
    }

    public void showMainMenu() {
        Config config = new Config();
        Scoreboard scoreboard = new Scoreboard();
        Launch launch = new Launch();
        ConfigInfo configInfo = new ConfigInfo();
        ScoreboardInfo scoreboardInfo = new ScoreboardInfo();

        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40; -fx-background-color: black;");

        Button startButton = new Button("Start Game");
        Button configButton = new Button("Configuration");
        Button scoreboardButton = new Button("Scoreboard");
        Button quitButton = new Button("Quit");

        startButton.setOnAction(e -> launch.pressButton());
        configButton.setOnAction(e -> {
            config.pressButton();
            configInfo.giveInfo();
        });
        scoreboardButton.setOnAction(e -> {
            scoreboard.pressButton();
            scoreboardInfo.giveInfo();
        });
        quitButton.setOnAction(e -> mainStage.close());


        layout.getChildren().addAll(startButton, configButton, scoreboardButton, quitButton);

        Scene menuScene = new Scene(layout, 400, 400);
        mainStage.setScene(menuScene);
        mainStage.setTitle("Tetris - Main Menu");
        mainStage.show();
    }

    public interface buttonPress {
        void pressButton();
    }

    public class Config implements buttonPress {
        public void pressButton() {
            ConfigurationMenu config = new ConfigurationMenu();
            config.start(mainStage);
        }
    }

    public class Scoreboard implements buttonPress {
        public void pressButton() {
            ScoreboardMenu scoreboard = new ScoreboardMenu();
            scoreboard.start(mainStage);
        }
    }

    public class Launch implements buttonPress {
        public void pressButton() {
            GameLoop game = new GameLoop();
            game.start(mainStage);
        }
    }

    abstract class Debug {
        public abstract void giveInfo();
    }
    class ConfigInfo extends Debug {
        public void giveInfo() {
            System.out.println("Configuration clicked");
        }
    }
    class ScoreboardInfo extends Debug {
        public void giveInfo() {
            System.out.println("Scoreboard clicked");
        }
    }
}