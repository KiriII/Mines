package last;

import Datta.Board;
import Datta.Hexagon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static java.lang.StrictMath.sqrt;


public class Main extends Application {
    public Pane root;
    int n;
    int bombCount;
    private Stage main;
    private Controll mainController;

    Board board;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        main = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stage.fxml"));
        Pane root = loader.load();
        this.root = root;
        Controll controll = loader.getController();
        mainController = controll;
        primaryStage.setTitle("Sapper");
        primaryStage.getIcons().add(new Image("file:images.jpg"));
        primaryStage.setScene(new Scene(root));
        root.setStyle("-fx-background-color: #A0F0FA;");
        //primaryStage.setFullScreen(true);
        //primaryStage.setHeight(side * 2 * n);
        primaryStage.show();
        startChose();
    }
    public void lose(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("end.fxml"));
            BorderPane page = loader.load();
            Stage end = new Stage();
            ((newGame)loader.getController()).link(end , this);
            end.setTitle("FATALITY");
            end.initModality(Modality.WINDOW_MODAL);
            end.initOwner(main);
            Scene scene = new Scene(page);
            end.setScene(scene);
            end.setResizable(false);
            end.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    startNew(n,bombCount);
                }
            });
            end.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void win(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("win.fxml"));
            BorderPane page = loader.load();
            Stage win = new Stage();
            ((ControllWin)loader.getController()).link(win , this);
            win.setTitle("Congratulations");
            win.initModality(Modality.WINDOW_MODAL);
            win.initOwner(main);
            Scene scene = new Scene(page);
            win.setScene(scene);
            win.setResizable(false);
            win.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    startNew(n,bombCount);
                }
            });
            win.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void startChose(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dificulty.fxml"));
            Pane page = loader.load();
            Stage chose = new Stage();
            ((ControllDifficulty)loader.getController()).link(chose , this);
            chose.setTitle("             ");
            chose.initModality(Modality.WINDOW_MODAL);
            chose.initOwner(main);
            Scene scene = new Scene(page);
            chose.setScene(scene);
            chose.setResizable(false);
            chose.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    main.close();
                }
            });
            chose.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void startNew(int n, int bombCount) {
        board = new Board(n , bombCount);
        double side = 640.0 / (sqrt(3) * n + 1);
        root.getChildren().clear();
        for (int i = 0; i < n ; i ++){
            for (int j = 0 ; j < n; j ++){
                SquareR pane = new SquareR(board.getBoard()[i][j]);
                if (i % 2 != 0){
                    pane.setLayoutX(side * 1.5 * i);
                    pane.setLayoutY(sqrt(3) * side * j + side * sqrt(3) / 2 );
                }
                else{
                    pane.setLayoutX(side * 1.5 * i);
                    pane.setLayoutY(sqrt(3) * side * j );
                }
                Polygon hexagon = new Polygon(new Hexagon(side).getPoints());
                hexagon.setFill(Color.DARKMAGENTA);
                hexagon.setStroke(Color.BLACK);
                hexagon.setStrokeWidth(1);
                //Rectangle r = new Rectangle(size, size);
                //r.setFill(Color.WHITE);
                //r.setStroke(Color.BLACK);
                //r.setStrokeWidth(1);
                Text text = new Text(board.getBoard()[i][j].toString());
                text.setVisible(false);
                pane.getChildren().addAll(hexagon, text);
                root.getChildren().add(pane);
            }
        }
        mainController.link(this, board);
        mainController.restart();
    }

    public void restart() {
        board.restart();
        root.getChildren().forEach(N -> {
            if (N instanceof SquareR) {
                SquareR square = (SquareR)N;
                ((Polygon)square.getChildren().get(0)).setFill(Color.DARKMAGENTA);
                square.getChildren().get(1).setVisible(false);
            }
        });
        mainController.restart();
    }

    public void end(){
        main.close();
    }

}
