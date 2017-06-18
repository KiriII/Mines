package last;

import Datta.Board;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class newGame {
    private Main main;
    private Stage stage;

    public void link(Stage stage , Main main) {
        this.stage = stage;
        this.main = main;
    }

    @FXML
    private Button newGame;
    @FXML
    private Button restart;
    @FXML
    private Button exit;

    @FXML
    private void initialize(){
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.startNew(main.n , main.bombCount);
                stage.close();
            }
        });
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.restart();
                stage.close();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                main.end();
            }
        });
    }

}
