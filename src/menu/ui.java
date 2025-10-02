package menu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sound.SoundManager; //importing sound

public class ui {

    private Stage mainStage;

    public ui(Stage stage) {
        this.mainStage = stage;
    }

    public void showMainMenu() {
        SoundManager.playMenuSound(); //start background music

        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 40; -fx-background-color: black;");

        Button startButton = new Button("Start Game");
        Button configButton = new Button("Configuration");
        Button scoreboardButton = new Button("Scoreboard");
        Button quitButton = new Button("Quit");

        startButton.setOnAction(e -> {
            SoundManager.stopMenuSound();
            launchGame();
        });

        configButton.setOnAction(e -> showConfig());
        scoreboardButton.setOnAction(e -> showScoreboard());
        quitButton.setOnAction(e -> mainStage.close());


        layout.getChildren().addAll(startButton, configButton, scoreboardButton, quitButton);

        Scene menuScene = new Scene(layout, 400, 400);
        mainStage.setScene(menuScene);
        mainStage.setTitle("Tetris - Main Menu");
        mainStage.show();
    }

    private void showConfig() {
        ConfigurationMenu config = new ConfigurationMenu();
        config.start(mainStage);
    }

    private void showScoreboard() {
        ScoreboardMenu scoreboard = new ScoreboardMenu();
        scoreboard.start(mainStage);
    }

    private void launchGame() {
        GameLoop game = new GameLoop();
        game.start(mainStage);

    }

    //private void showConfig() {
    //   System.out.println("Configuration clicked");
    //}

    //private void showScoreboard() {
    //  System.out.println("Scoreboard clicked");
    // }
}