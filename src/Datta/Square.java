package Datta;

public class Square {

    private boolean hasBomb = false;
    private int score = 0;
    private boolean isOpen = false;
    private boolean isMarked = false;

    private int x;
    private int y;

    public Square(boolean hasBomb , int x , int y){
        this.hasBomb = hasBomb;
        this.x = x;
        this.y = y;
    }

    public Square(int score , int x, int y){
        this.score = score;
        this.x = x;
        this.y = y;
    }

    public int opening() {
        if (isMarked) return -2;
        isOpen = true;
        if (hasBomb) return -1;
        return score;
    }

    public void marking(){
        if (!isOpen)
            isMarked = !isMarked;
    }

    public boolean isBomb(){
        return hasBomb;
    }

    public boolean isOpen(){
        return isOpen;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getScore() {
        return score;
    }

    public boolean isMarked(){ return isMarked; }

    public void onRestart() {
        isOpen = false;
        isMarked = false;
    }

    @Override
    public String toString(){
        if (isMarked) return "M";
        if (isBomb()) return "B";
        else return "" + score;
    }
}
