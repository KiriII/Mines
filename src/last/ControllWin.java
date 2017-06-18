package last;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class ControllWin {

    private Main main;
    private Stage stage;

    public void link(Stage stage , Main main) {
        this.stage = stage;
        this.main = main;
    }

    @FXML
    private Button newGame1;
    @FXML
    private Button exit1;

    @FXML
    private void initialize() {
        newGame1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.startNew(main.n ,  main.bombCount);
                stage.close();
            }
        });
        exit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                main.end();
            }
        });
    }
}
