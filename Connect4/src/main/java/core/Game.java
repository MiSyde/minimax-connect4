package core;

import gui.Coin;
import java.awt.Color;

public class Game {
    Pos[][] board; // Default Connect-4 boards have 6 rows (x) & 7 columns (y)
    Boolean won = false;

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
        Pos current = board[x][y];
        if(current.y <= 2){
            return (board[current.x][current.y+1].value.equals(player)
                    && board[current.x][current.y+2].value.equals(player)
                    && board[current.x][current.y+3].value.equals(player));
        }
        return false;
    }
    /*
    ->y
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
            if(board[i][y].value.equals(player)){
                ++count;
            } else{
                break;
            }
        }
        for(int i = y-1; i > 0; --i){
            if(board[i][y].value.equals(player)){
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
