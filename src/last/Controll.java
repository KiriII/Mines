package last;

import Datta.Board;
import Datta.Square;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayDeque;

public class Controll {
    private Board board;
    private Main main;
    @FXML
    private void initialize(){

    }

    public void link(Main main, Board board) {
        this.main = main;
        this.board = board;

    }
    private EventHandler rectangle = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.isPrimaryButtonDown()) {
                SquareR clickedSquare = (SquareR) event.getSource();
                if (board.open(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY()) == -1) {
                    board.explosion(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY());
                    reDraw();
                    main.lose();
                }
                reDraw();
                if (clickedSquare.getSquare().isOpen()){
                    int count = 0;
                    for (int i = - 1; i < 2; i++){
                        for (int j = - 1; j < 2; j ++){
                            if (clickedSquare.getSquare().isBomb() && clickedSquare.getSquare().isMarked()){count++;}
                        }
                    }
                }
                if (board.isComplited()) main.win();
            }
            if (event.isSecondaryButtonDown()) {
                SquareR clickedSquare = (SquareR) event.getSource();
                board.getBoard()[clickedSquare.getSquare().getX()][clickedSquare.getSquare().getY()].marking();
                if (clickedSquare.getSquare().isMarked())
                    ((Rectangle)clickedSquare.getChildren().get(0)).setFill(Color.valueOf("#fbb"));
                else
                    ((Rectangle)clickedSquare.getChildren().get(0)).setFill(Color.valueOf("#fff"));
            }
            if (event.getClickCount() == 2){
                SquareR clickedSquare = (SquareR) event.getSource();
                if ((int) board.getNaighbours(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY()).stream().filter(S -> ((S != null) && (S.isBomb()))).count() == (int) board.getNaighbours(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY()).stream().filter(S -> ((S != null) && (S.isMarked()))).count()){
                    if (board.isAllMarked(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY()) == 1) {
                        board.explosion(clickedSquare.getSquare().getX(), clickedSquare.getSquare().getY());
                        main.lose();
                    }
                }
            }
        }
    };

    private void reDraw() {
        main.root.getChildren().forEach(N -> {
            if (N instanceof SquareR) {
                SquareR square = (SquareR) N;
                if (square.getSquare().isOpen()) {
                    square.getChildren().get(1).setVisible(true);
                }
            }
        });
    }

    public void restart() {
        main.root.getChildren().forEach(N -> {
            if (N instanceof SquareR) {
                //N.setOnMouseClicked(rectangle);
                N.setOnMousePressed(rectangle);
            }
        });
    }
}
