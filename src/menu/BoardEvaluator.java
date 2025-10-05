package menu;

public class BoardEvaluator {
    // The main function you're calling
    public int evaluateBoard(int[][] board) {
        int heightScore = getHeight(board);
        int holesScore = getHoles(board);
        int linesCleared = getClearedLines(board);
        int bumpinessScore = getBumpiness(board);

        return (-4 * heightScore) + (3 * linesCleared) - (9 * holesScore) - (2 * bumpinessScore);
    }

    private int getHeight(int[][] board) {
        // Calculate the height of the pile (the highest filled row). Sweeps by column, checking each block to see if it's the highest point
        int height = 0;
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) {
                    height = Math.max(height, board.length - row);
                    break;
                }
            }
        }
        return height;
    }

    private int getHoles(int[][] board) {
        // Calculate the number of holes (empty spaces beneath filled blocks). Sweeps by column, looking for a filled block, and then checks if there's any holes underneath it
        int holes = 0;
        for (int col = 0; col < board[0].length; col++) {
            boolean foundBlock = false;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) {
                    foundBlock = true;
                }
                else if (foundBlock && board[row][col] == 0) {
                    holes++;
                }
            }
        }
        return holes;
    }

    private int getClearedLines(int[][] board) {
        // Calculate how many full lines are cleared in the instance where the hypothetical tetrimino is placed
        int clearedLines = 0;
        for (int row = 0; row < board.length; row++) {
            boolean isLineFull = true;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0) {
                    isLineFull = false;
                    break;
                }
            }
            if (isLineFull) {
                clearedLines++;
            }
        }
        return clearedLines;
    }

    private int getBumpiness(int[][] board) {
        // Calculate the bumpiness of the surface. Compares adjacent columns heights and makes a big number based off the differences in heights
        int bumpiness = 0;
        for (int col = 0; col < board[0].length - 1; col++) {
            int colHeight1 = getColumnHeight(board, col);
            int colHeight2 = getColumnHeight(board, col + 1);
            bumpiness += Math.abs(colHeight1 - colHeight2);
        }
        return bumpiness;
    }

    private int getColumnHeight(int[][] board, int col) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] != 0) {
                return board.length - row;
            }
        }
        return 0;
    }
}

