package menu;

public class AI {
    private BoardEvaluator evaluator = new BoardEvaluator();

    public String findBestMove(GameBoard board, TetrisBlock block) {
        String bestMove = "";
        int bestScore = Integer.MIN_VALUE;
        TetrisBlock simulatedPiece = RandomBlock.getSpecificBlock(block.getShapeIndex(), block.getRotation());

        for (int rotation = 0; rotation < 4; rotation++) {
            if (rotation > 0) {
                simulatedPiece.rotate();
            }
            for (int col = 0; col < board.BOARD_WIDTH; col++) {
                int simulatedBoardScore = simulateDrop(board.getGrid(), simulatedPiece, col);
                if (simulatedBoardScore > bestScore) {
                    bestScore = simulatedBoardScore;
                    if (rotation > 0) {
                        bestMove = "Rotate";
                    } else if (block.getX() > col) {
                        bestMove = "Left";
                    } else if (block.getX() < col) {
                        bestMove = "Right";
                    } else {
                        bestMove = "Down";
                    }
                    //System.out.println(simulatedBoardScore + " + " + bestMove);
                }
            }
        }
        return bestMove;
    }

    private int simulateDrop(int[][] board, TetrisBlock block, int col) {
        // Simulate dropping the piece in the given column and return the new board
        int score;
        int[][] simulatedBoard = copyBoard(board);
        int dropRow = getDropRow(simulatedBoard, block, col);
        if (dropRow != -1) {
            placePiece(simulatedBoard, block, col, dropRow);
            score = evaluator.evaluateBoard(simulatedBoard);
        }
        else { score = -1000000; }

        return score;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int y = 0; y < board.length; y++) {
            System.arraycopy(board[y], 0, newBoard[y], 0, board[0].length);
        }
        return newBoard;
    }

    private int getDropRow(int[][] board, TetrisBlock block, int col) {
        // Find the row where the piece would land if dropped in the given column
        int row = 0;
        int[][] shape = block.getShape();
        boolean collided = false;

        while (!collided) {
            for (int pieceY = 0; pieceY < shape.length; pieceY++) {
                for (int pieceX = 0; pieceX < shape[0].length; pieceX++) {
                    if (shape[pieceY][pieceX] == 1) {
                        int x = col + pieceX;
                        int y = row + pieceY;
                        //System.out.println(Integer.toString(row) + " + " + Integer.toString(pieceY));

                        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length || board[y][x] != 0) {
                            collided = true;
                            //System.out.println("Broken here");
                            break;
                        }
                    }
                    if (pieceY == shape.length - 1 && pieceX == shape[pieceY].length - 1) {
                        row++;
                    }
                }
                if (collided) {
                    break;
                }
            }
        }

        //System.out.println("Row output:" + Integer.toString(row - 1) + " Col output: " + col);
        row--;
        return row; // Return the last valid row
    }

    private void placePiece(int[][] board, TetrisBlock block, int col, int row) {
        // Place the piece on the board at the given position
        int[][] shape = block.getShape();

        for (int pieceY = 0; pieceY < shape.length; pieceY++) {
            for (int pieceX = 0; pieceX < shape[0].length; pieceX++) {
                if (shape[pieceY][pieceX] == 1) {
                    //System.out.println(Integer.toString(row) + " + " + Integer.toString(pieceY)); // 19 + 1
                    board[row + pieceY][col + pieceX] = 1;
                }
            }
        }
    }
}
