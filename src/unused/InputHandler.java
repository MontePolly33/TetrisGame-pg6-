// This file is currently unused
package unused;// This file is currently unused

import Menu.GameBoard;
import Menu.TetrisBlock;

import java.util.Scanner;

public class InputHandler {
    private TetrisBlock block;
    private GameBoard board;

    public InputHandler(TetrisBlock block, GameBoard board) {
        this.block = block;
        this.board = board;
    }

    public void handling() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command (a = left, d = right, s = down, q = quit):");

        while (true) {
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "a":
                    moveLeft();
                    break;
                case "d":
                    moveRight();
                    break;
                case "s":
                    moveDown();
                    break;
                case "q":
                    System.out.println("Exiting game...");
                    return;
                default:
                    System.out.println("Invalid command.");
            }

            board.renderBlock(block); // Refresh block after move
        }
    }

    private void moveLeft() {
        block.moveLeft();
        System.out.println("Moving left...");
    }

    private void moveRight() {
        block.moveRight();
        System.out.println("Moving right...");
    }


    private void moveDown() {
        // Check if block can move down without collision
        block.moveDown(); // Tentatively move down

        if (board.checkCollision(block)) {
            block.moveUp(); // Undo the move
            block.setLanded(true);
            System.out.println("Block landed from manual move.");
        } else {
            System.out.println("Manual move down successful.");
        }

        board.renderBlock(block); // Redraw the board
    }
}