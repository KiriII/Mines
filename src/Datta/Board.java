package Datta;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private Square[][] board = new Square[0][0];
    private int n;
    private int bombCount;

    public Board(int n , int bombCount) {
        this.n = n;
        this.board = new Square[n][n];
        this.bombCount = bombCount;
        double chanse = (double) bombCount / (n * n);
        if (chanse > 1) chanse = 1;
        final Random random = new Random();
        int bombsSet = 0;
        while (bombsSet < bombCount) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (random.nextDouble() <= chanse && board[i][j] == null) {
                        board[i][j] = new Square(true, i, j);
                        bombsSet++;
                        if (bombCount == bombsSet) break;
                    }
                }
                if (bombCount == bombsSet) break;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != null && board[i][j].isBomb()) continue;
                int score = (int) getNaighbours(i, j).stream().filter(S -> ((S != null) && (S.isBomb()))).count();
                board[i][j] = new Square(score , i , j);
            }
        }
    }

    public List<Square> getNaighbours(int i , int j){
        List<Square> list = new ArrayList<>();
        for (int k = -1; k < 2; k++){
            for (int l = -1; l < 2; l++){
                if ((i + k >= 0) && (j + l >= 0) && (i + k < n) && (j + l < n)){
                    list.add(board[i+k][j+l]);
                }
            }
        }
        return list;
    }

    public int open(int i, int j){
        ArrayDeque<Square> deque = new ArrayDeque<>();
        deque.add(board[i][j]);
        if (board[i][j].isBomb() || board[i][j].isMarked())
            return board[i][j].opening();
        while (!deque.isEmpty()) {
            Square square = deque.poll();
            if (!square.isOpen() && !square.isBomb() && !square.isMarked()) {
                if (square.opening() == 0)
                    deque.addAll(getNaighbours(square.getX(), square.getY()));
            }
        }
        return 0;
    }

    public int explosion(int i, int j){
        for (int l = 0; l < n; l++){
            for (int k = 0; k < n; k++){
                if (board[l][k].isBomb()) {
                    board[l][k].onRestart();
                    board[l][k].opening();
                }
            }
        }
        return 0;
    }

    public Square[][] getBoard(){
        return board;
    }
    public void restart() {
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j].onRestart();
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0 ; i < n; i++){
            for (int j = 0; j < n; j++){
                str.append(board[i][j]).append(" ");
            }
            str.append("\r\n");
        }
        return str.toString();
    }

    public boolean isComplited(){
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < n; j++){
                if ((!board[i][j].isBomb() && !board[i][j].isOpen()) || (board[i][j].isBomb() && board[i][j].isOpen())) return false;
            }
        }
        return true;
    }

    public int isAllMarked(int i, int j) {
        List<Square> bombList = new ArrayList<>();
        List<Square> markedList = new ArrayList<>();
        for (int k = 0; k < getNaighbours(i,j).size() ; k ++){
            if (getNaighbours(i,j).get(k).isBomb()) bombList.add(getNaighbours(i,j).get(k));
            if (getNaighbours(i,j).get(k).isMarked()) markedList.add(getNaighbours(i,j).get(k));
        }
       if (bombList.size() == markedList.size() && bombList.containsAll(markedList)) {
           for (int k = 0; k < getNaighbours(i,j).size() ; k ++) {
               if (getNaighbours(i, j).get(k).getScore() == 0) open(getNaighbours(i, j).get(k).getX(),getNaighbours(i, j).get(k).getY());
               if (!getNaighbours(i, j).get(k).isBomb()) getNaighbours(i, j).get(k).opening();
           }
       }
        else{
            return 1;
        }
        return 0;
    }
}
