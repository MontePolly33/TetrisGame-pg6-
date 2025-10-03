package menu;

public class AI {

    public class BoardEvaluator {
        // The main function you're calling
        public int evaluateBoard(GameBoard inputBoard) {
            int[][] board = inputBoard.getGrid();

            int heightScore = getHeight(board);
            int holesScore = getHoles(board);
            int linesCleared = getClearedLines(board);
            int bumpinessScore = getBumpiness(board);

            return (-4 * heightScore) + (3 * linesCleared) - (5 * holesScore) - (2 * bumpinessScore);
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


    public class TetrisAI {
        private BoardEvaluator evaluator = new BoardEvaluator();

        public Move findBestMove(TetrisGame game, Tetromino piece) {
            Move bestMove = null;
            int bestScore = Integer.MIN_VALUE;
            for (int rotation = 0; rotation < 4; rotation++) {
                for (int col = 0; col < game.getBoardWidth(); col++) {
                    Tetromino simulatedPiece = piece.clone();
                    simulatedPiece.rotate(rotation);
                    int[][] simulatedBoard = simulateDrop(game.getBoard(), simulatedPiece, col);
                    int score = evaluator.evaluateBoard(simulatedBoard);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(col, rotation);
                    }
                }
            }
            return bestMove;
        }

        private int[][] simulateDrop(int[][] board, Tetromino piece, int col) {
            // Simulate dropping the piece in the given column and return the new board
            int[][] simulatedBoard = copyBoard(board);
            int dropRow = getDropRow(simulatedBoard, piece, col);
            placePiece(simulatedBoard, piece, col, dropRow);
            return simulatedBoard;
        }

        private int getDropRow(int[][] board, Tetromino piece, int col) {
            // Find the row where the piece would land if dropped in the given column
            int row = 0;
            while (canPlacePiece(board, piece, col, row)) {
                row++;
            }
            return row - 1; // Return the last valid row
        }

        private boolean canPlacePiece(int[][] board, Tetromino piece, int col, int row) {
            // Check if the piece can be placed at the given column and row
            for (Point p : piece.getShape()) {
                int x = col + p.x;
                int y = row + p.y;
                if (x < 0 || x >= board[0].length || y < 0 || y >= board.length || board[y][x] != 0) {
                    return false;
                }
            }
            return true;
        }

        private void placePiece(int[][] board, Tetromino piece, int col, int row) {
            // Place the piece on the board at the given position
            for (Point p : piece.getShape()) {
                board[row + p.y][col + p.x] = 1; // 1 represents the piece
            }
        }

        private int[][] copyBoard(int[][] board) {
            int[][] newBoard = new int[board.length][board[0].length];
            for (int y = 0; y < board.length; y++) {
                System.arraycopy(board[y], 0, newBoard[y], 0, board[0].length);
            }
            return newBoard;
        }
    }

}
