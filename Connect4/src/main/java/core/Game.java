package core;

import gui.Coin;
import java.awt.Color;
import java.io.Serializable;

public class Game implements Serializable {
    Color[][] board; // Default Connect-4 boards have 6 rows (x) & 7 columns (y)
    boolean won = false;

    public Game() {
        board = new Color[7][6];
        for(int x = 0; x < 7; ++x){
            for(int y = 0; y < 6; ++y){
                board[x][y] = Color.BLACK;
            }
        }
    }

    public int getCurrentY(int x){
        for(int y = 0; y <= 5; ++y){
            if(board[x][y].equals(Color.BLACK)){
                return y;
            }
        }
        return -1;
    }

    public boolean getWon(){
        return won;
    }

    public void addToBoard(int x, int y, Color color){
        board[x][y] = color;
        if (y >= 3) {
            won = checkVertical(x, y, color);
        }
        if(!won){
            won = checkHorizontal(x, y, color);
        }
        if(won){
            System.out.println("Won");
        }
    }



    boolean checkVertical(int x, int y, Color player){
        int count = 1;
        if(y >= 3){
            for(int i = 0; i < 3; ++i){
                if(board[x][y-i].equals(player)){
                    ++count;
                } else{
                    break;
                }
            }
        }
        return count == 4;
    }
    /*
    [][][][][][]
    [][][][][][]
    [][][][][][]
    [][][][][][]
    [][][][][][]
    [][][][][][]
    [][][][][][]
     */
    boolean checkHorizontal(int x, int y, Color player){
        int count = 1;
        for(int i = x+1; i < 7; ++i){
            if(board[i][y].equals(player)){
                ++count;
            } else{
                break;
            }
        }
        for(int i = x-1; i >= 0; --i){
            if(board[i][y].equals(player)){
                ++count;
            } else{
                break;
            }
        }
        return count >= 4;
    }

    boolean checkLTDiagonal(int x, int y, Color player){ // Left-Tilted
        return false;
    }
    boolean checkRTDiagonal(int x, int y, Color player){ // Right-Tilted
        return false;
    }
}
