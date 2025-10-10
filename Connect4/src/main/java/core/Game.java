package core;

import gui.Coin;
import java.awt.Color;

public class Game {
    Pos[][] board; // Default Connect-4 boards have 6 columns (x) & 7 rows (y)
    Color player; // Y(ellow) | R(ed)

    Game() {
        board = new Pos[6][7];
        for(int i = 0; i < 6; ++i){
            for(int j = 0; j < 7; ++j){
                board[i][j] = new Pos(i, j, null);
            }
        }
    }

    public void addToBoard(int col, int row, Color color){
        board[row][col] = new Pos(col, row, color);
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
