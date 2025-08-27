package Menu;

public class TetrisShapes {
    private static final int[][][][] SHAPES = {
// I
            {
                    { {1, 1, 1, 1} },
                    { {1}, {1}, {1}, {1} }
            },
// O
            {
                    { {1, 1}, {1, 1} }
            },
// T
            {
                    { {0, 1, 0}, {1, 1, 1} },
                    { {1, 0}, {1, 1}, {1, 0} },
                    { {1, 1, 1}, {0, 1, 0} },
                    { {0, 1}, {1, 1}, {0, 1} }
            },
// L
            {
                    { {1, 0}, {1, 0}, {1, 1} },
                    { {1, 1, 1}, {1, 0, 0} },
                    { {1, 1}, {0, 1}, {0, 1} },
                    { {0, 0, 1}, {1, 1, 1} }
            },
// J
            {
                    { {0, 1}, {0, 1}, {1, 1} },
                    { {1, 0, 0}, {1, 1, 1} },
                    { {1, 1}, {1, 0}, {1, 0} },
                    { {1, 1, 1}, {0, 0, 1} }
            },
// S
            {
                    { {0, 1, 1}, {1, 1, 0} },
                    { {1, 0}, {1, 1}, {0, 1} }
            },
// Z
            {
                    { {1, 1, 0}, {0, 1, 1} },
                    { {0, 1}, {1, 1}, {1, 0} }
            }
    };

    private static final String[] COLORS = {
            "cyan", "yellow", "purple", "orange", "blue", "green", "red"
    };

    public static int[][] getShape(int index, int rotation) {
        int[][][] rotations = SHAPES[index];
        return rotations[rotation % rotations.length];
    }

    public static String getColor(int index) {
        return COLORS[index];
    }

    public static int getShapeCount() {
        return SHAPES.length;
    }

    public static int getRotationCount(int shapeIndex){
        return SHAPES[shapeIndex].length;
    }
}