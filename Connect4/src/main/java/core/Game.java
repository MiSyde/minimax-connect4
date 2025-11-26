package core;

import java.awt.Color;

public class Game {
    Color[][] board; // Default Connect-4 boards have 6 rows (x) & 7 columns (y)
    boolean won;

    public Game() {
        board = new Color[7][6];
        for (int x = 0; x < 7; ++x) {
            for (int y = 0; y < 6; ++y) {
                board[x][y] = Color.BLACK;
            }
        }
        won = false;
    }

    public void restartGame() {
        board = new Color[7][6];
        for (int x = 0; x < 7; ++x) {
            for (int y = 0; y < 6; ++y) {
                board[x][y] = Color.BLACK;
            }
        }
         won = false;
    }

    public int getCurrentY(int x) {
        for (int y = 0; y <= 5; ++y) {
            if (board[x][y].equals(Color.BLACK)) {
                return y;
            }
        }
        return -1;
    }

    public boolean getWon() {
        return won;
    }

    public void addToBoard(int x, int y, Color color) {
        board[x][y] = color;
        won = checkWinStat(x, y, color);
        if (won) {
            System.out.println("Won");
        }
    }

    public boolean checkWinStat(int x, int y, Color player) {
        int count = 0;
        int shuffle = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}, {0, -1}}; // jobbra, balra, jobbra-le, balra-fel, balra-le, jobbra-fel, le
        for (int[] direction : directions) {
            if (shuffle == 2) {
                if (count - 1 >= 4) return true;
                count = 0;
                shuffle = 0;
            }
            int directionX = direction[0];
            int directionY = direction[1];
            for (int i = 0; i < 4; ++i) {
                int currentX = x + directionX * i;
                int currentY = y + directionY * i;
                if (directionX < 0) {
                    if (currentX < 0) {
                        break;
                    }
                }
                if (directionY < 0) {
                    if (currentY < 0) {
                        break;
                    }
                }
                if(currentX >= 7 || currentY >= 6) { break; }
                if (board[currentX][currentY].equals(player)) {
                    ++count;
                }
            }
            ++shuffle;
        }
        return count >= 4; // LE eredményét ellenőrzi, mivel az már nem lép bele a shufflebe
    }

}
/*
        [0,0][0,1][0,2][0,3][0,4][0,5]
        [1,0][1,1][1,2][1,3][1,4][1,5]
        [2,0][2,1][2,2][2,3][2,4][2,5]
        [3,0][3,1][3,2][3,3][3,4][3,5]
        [4,0][4,1][4,2][4,3][4,4][4,5]
        [5,0][5,1][5,2][5,3][5,4][5,5]
        [6,0][6,1][6,2][6,3][6,4][6,5]
*/
