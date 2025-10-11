package core;

import gui.Coin;
import java.awt.Color;

public class Game {
    Pos[][] board; // Default Connect-4 boards have 6 rows (x) & 7 columns (y)
    Color player; // Y(ellow) | R(ed)

    public Game() {
        board = new Pos[7][6];
        for(int x = 0; x < 7; ++x){
            for(int y = 0; y < 6; ++y){
                board[x][y] = new Pos(x, y, Color.BLACK);
            }
        }
    }

    public int getCurrentY(int x){
        for(int y = 0; y <= 5; ++y){
            if(board[x][y].value.equals(Color.BLACK)){
                return y;
            }
        }
        return -1;
    }

    public void addToBoard(int x, int y, Color color){
        board[x][y].value = color;
    }


    boolean checkVertical(Pos current){
        if(current.y >= 3 ){
            if(board[current.y-1][current.x].value.equals(player)){
                if(board[current.y-2][current.x].value.equals(player)){
                    return board[current.y - 3][current.x].value.equals(player); // looks funky, but it saves us another if-else
                } else{
                    return false;
                }
            } else{
                return false;
            }
        }
        return false;
    }

    boolean checkHorizontal(Pos current){

        return false;
    }

    boolean checkDiagonal(Pos current){
        return false;
    }
}
