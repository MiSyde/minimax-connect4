package core;

import java.awt.*;

public class AI {
    Color color = Color.YELLOW;
    private final Game game;

    public AI(Game game) { this.game = game; }

    /**
     * Calculates the best move possible with the given depth, based on the minimax algorithm and alpha-beta pruning
     * @param board The board it gets as a starting point
     * @param depth The amount of times it will go through the algorithm
     * @return The column it deemes best
     * @see AI#minimax(Color[][], int, int, int, boolean)
     * @see AI#copyBoard(Color[][])
     * @see Game#getCurrentY(Color[][], int)
     * @see AI#checkWin(Color[][], Color)
     */
    public int getBestMove(Color[][] board, int depth) {
        int bestScore = Integer.MIN_VALUE;
        int bestCol = 3;

        for (int currentCol = 0; currentCol < 7; ++currentCol) {
            if (game.isColumnAvailable(board, currentCol)) {
                Color[][] temp = copyBoard(board);
                int row = game.getCurrentY(temp, currentCol);

                if (row == -1) continue;

                temp[currentCol][row] = color;

                if (checkWin(temp, color)) { return currentCol; }

                if(checkWin(temp, Color.RED)) { return currentCol; }

                int score = minimax(temp, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                if (score > bestScore) {
                    bestScore = score;
                    bestCol = currentCol;
                }
            }
        }
        return bestCol;
    }

    /**
     * Calculates a score for a series of moves
     * If the
     * @param board The board the AI is doing the search in 
     * @param depth Depth of the search
     * @param alpha AI's best score so far - for pruning
     * @param beta Human's best score so far - for pruning
     * @param maximizing If true, it uses the AI part of the algorithm and then sends it into the human part.
     *                   If false, it uses the human part of the algorithm and then sends it into the AI part.
     * @return The best score for the current board state
     * @see AI#makeMove(Color[][], int, Color) 
     * @see AI#getWinner(Color[][]) 
     * @see Game#isBoardFull(Color[][]) 
     * @see Game#isColumnAvailable(Color[][], int) 
     */
    private int minimax(Color[][] board, int depth, int alpha, int beta, boolean maximizing) {
        Color winner = getWinner(board);
        if(winner != null) {
            if (game.isColorEqual(winner, color)) return 1000000 + depth;
            if (game.isColorEqual(winner, Color.RED)) return -1000000 - depth;
        }
        if (game.isBoardFull(board)) return 0;
        if (depth == 0) return evaluateBoard(board);

        if (maximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int col = 0; col < 7; col++) {
                if (game.isColumnAvailable(board, col)) {
                    Color[][] newBoard = makeMove(board, col, color);
                    int score = minimax(newBoard, depth - 1, alpha, beta, false);
                    maxScore = Math.max(maxScore, score);
                    alpha = Math.max(alpha, score);
                    if (beta <= alpha) break;
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int col = 0; col < 7; col++) {
                if (game.isColumnAvailable(board, col)) {
                    Color[][] newBoard = makeMove(board, col, Color.RED);
                    int score = minimax(newBoard, depth - 1, alpha, beta, true);
                    minScore = Math.min(minScore, score);
                    beta = Math.min(beta, score);
                    if (beta <= alpha) break;
                }
            }
            return minScore;
        }
    }

    /**
     * Scours the board to see if either color can win anywhere
     * @param board The board in which the checking happens
     * @return The color that can win in the next move
     */
    private Color getWinner(Color[][] board) {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                Color player = board[col][row];
                if (game.isBlack(player)) continue;

                if (col <= 3 &&
                        game.isColorEqual(player, board[col+1][row]) &&
                        game.isColorEqual(player, board[col+2][row]) &&
                        game.isColorEqual(player, board[col+3][row])) {
                    return player;
                }

                if (row <= 2 &&
                        game.isColorEqual(player, board[col][row+1]) &&
                        game.isColorEqual(player, board[col][row+2]) &&
                        game.isColorEqual(player, board[col][row+3])) {
                    return player;
                }

                if (col <= 3 && row <= 2 &&
                        game.isColorEqual(player, board[col+1][row+1]) &&
                        game.isColorEqual(player, board[col+2][row+2]) &&
                        game.isColorEqual(player, board[col+3][row+3])) {
                    return player;
                }

                if (col <= 3 && row >= 3 &&
                        game.isColorEqual(player, board[col+1][row-1]) &&
                        game.isColorEqual(player, board[col+2][row-2]) &&
                        game.isColorEqual(player, board[col+3][row-3])) {
                    return player;
                }
            }
        }
        return null;
    }

    private boolean checkWin(Color[][] board, Color player) {
        Color winner = getWinner(board);
        return winner != null && game.isColorEqual(winner, player);
    }

    /**
     * Calculates the score of the current board given both colors' positions
     * @param board The board the function works with
     * @return The final score it got after subtracting and adding
     */
    private int evaluateBoard(Color[][] board) {
        int score = 0;

        for (int row = 0; row < 6; row++) {
            if (game.isColorEqual(board[3][row], color)) score += 3;
            else if (game.isColorEqual(board[3][row], Color.RED)) score -= 3;
        }

        score += evaluateLines(board, color);
        score -= evaluateLines(board, Color.RED);

        return score;
    }

    /**
     *
     * @param board The board the function works with
     * @param player The color that we are looking for on the board
     * @return The score of the board
     */
    private int evaluateLines(Color[][] board, Color player) {
        int score = 0;

        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                // Horizontal
                if (col <= 3) {
                    score += evaluateWindow(board, col, row, 1, 0, player);
                }
                // Vertical
                if (row <= 2) {
                    score += evaluateWindow(board, col, row, 0, 1, player);
                }
                // Up-right
                if (col <= 3 && row <= 2) {
                    score += evaluateWindow(board, col, row, 1, 1, player);
                }
                // Down-right
                if (col <= 3 && row >= 3) {
                    score += evaluateWindow(board, col, row, 1, -1, player);
                }
            }
        }
        return score;
    }

    /**
     *
     * @param board The board the function works with
     * @param col
     * @param row
     * @param dCol
     * @param dRow
     * @param player
     * @return
     */
    private int evaluateWindow(Color[][] board, int col, int row, int dCol, int dRow, Color player) {
        int score = 0;
        int playerCount = 0;
        int emptyCount = 0;
        int opponentCount = 0;

        for (int i = 0; i < 4; i++) {
            int currentCol = col + (i * dCol);
            int currentRow = row + (i * dRow);

            if(currentCol < 0 || currentCol > 6 || currentRow < 0 || currentRow > 5) { break; }

            Color cell = board[currentCol][currentRow];

            if(game.isColorEqual(cell, player)) ++playerCount;
            else if(game.isBlack(cell)) ++emptyCount;
            else ++opponentCount;
        }

        // Scoring
        if (playerCount == 4) score += 100;
        else if (playerCount == 3 && emptyCount == 1) score += 50; // 3 in a row
        else if (playerCount == 2 && emptyCount == 2) score += 10; // 2 in a row
        else if (playerCount == 1 && emptyCount == 3) score += 1;  // 1 in a row

        // Block opponent
        if (opponentCount == 3 && emptyCount == 1) score += 40;

        return score;
    }

    /**
     *
     * @param board
     * @param col
     * @param player
     * @return
     */
    private Color[][] makeMove(Color[][] board, int col, Color player) {
        Color[][] newBoard = copyBoard(board);
        int row = game.getCurrentY(newBoard, col);
        if (row != -1) { newBoard[col][row] = player; }
        return newBoard;
    }

    /**
     * @param board The board that will be copied
     * @return The copy of the board in the argument
     */

    private Color[][] copyBoard(Color[][] board) {
        Color[][] newBoard = new Color[7][6];
        for (int i = 0; i < 7; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 6);
        }
        return newBoard;
    }
}