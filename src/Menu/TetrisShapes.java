package Menu;

public class TetrisShapes {
    private static final int[][][][] SHAPES = {
            {
                    { {1, 1, 1, 1} }, // I
                    { {1}, {1}, {1}, {1} }
            },
            {
                    { {1, 1}, {1, 1} } // O
            },
            {
                    { {0, 1, 0}, {1, 1, 1} }, // T
                    { {1, 0}, {1, 1}, {1, 0} },
                    { {1, 1, 1}, {0, 1, 0} },
                    { {0, 1}, {1, 1}, {0, 1} }
            }
// add more shapes if you want
    };

    private static final String[] COLORS = {
            "cyan", "yellow", "purple"
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
}