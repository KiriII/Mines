package last;

import Datta.Square;
import javafx.scene.layout.StackPane;

public class SquareR extends StackPane{

    Square square;

    public SquareR(Square square){
        this.square = square;
    }

    public Square getSquare(){
        return square;
    }
}
