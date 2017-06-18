package last;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllDifficulty {
    private Main main;
    private Stage stage;

    public void link(Stage stage , Main main) {
        this.stage = stage;
        this.main = main;
    }

    @FXML
    private Button easy;
    @FXML
    private Button normal;
    @FXML
    private Button hard;

    @FXML
    private void initialize() {
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.startNew(9 , 10);
                main.n = 9;
                main.bombCount = 10;
                stage.close();
            }
        });
        normal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.startNew(16 , 40);
                main.n = 16;
                main.bombCount = 40;
                stage.close();
            }
        });
        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.startNew(30 ,250 );
                main.n = 30;
                main.bombCount = 250;
                stage.close();
            }
        });
    }
}
