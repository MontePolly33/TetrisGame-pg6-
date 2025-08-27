import Menu.UI;
import javafx.application.Application;
import javafx.stage.Stage;
public class MainProject extends Application {


    @Override
    public void start(Stage stage) {
        UI ui = new UI(stage);
            ui.showMainMenu();
    }


        public static void main(String[] args){
            launch(args);
        }




    }

