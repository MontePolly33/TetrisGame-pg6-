package menu;

import java.util.Random;

public class RandomBlock {

    public static TetrisBlock getRandomBlock() {
        Random rand = new Random();

        int shapeIndex = rand.nextInt(TetrisShapes.getShapeCount());
        int rotation = rand.nextInt(4); // up to 4 rotations
        int[][] shape = TetrisShapes.getShape(shapeIndex, rotation); // get actual shape matrix
        String color = TetrisShapes.getColor(shapeIndex); // get color string

        return new TetrisBlock(shapeIndex, rotation, shape, color); // this matches constructor: (int[][], String)
    }
}