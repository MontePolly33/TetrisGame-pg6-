package menu;
import java.util.ArrayList;

public class BlockSequencer {
    private int p1SequenceIndex;
    private int p2SequenceIndex;
    private ArrayList<Integer> shapeSequence;

    public BlockSequencer(int p1SequenceIndex, int p2SequenceIndex, ArrayList<Integer> shapeSequence) {
        this.p1SequenceIndex = p1SequenceIndex;
        this.p2SequenceIndex = p2SequenceIndex;
        this.shapeSequence = shapeSequence;
    }

    public int getp1SequenceIndex() {
        return p1SequenceIndex;
    }
    public int getp2SequenceIndex() {
        return p2SequenceIndex;
    }

    public void incrementp1Sequence() { p1SequenceIndex++; }
    public void incrementp2Sequence() { p2SequenceIndex++; }
    public void setp1SequenceIndex(int input) {p1SequenceIndex = input;}
    public void setp2SequenceIndex(int input) {p2SequenceIndex = input;}

    public ArrayList<Integer> getShapeSequence() { return shapeSequence; }
    public void setShapeSequence(ArrayList<Integer> inputArray) {shapeSequence = inputArray; }
    public void addToSequence(int input){
        shapeSequence.add(input);
    }
}