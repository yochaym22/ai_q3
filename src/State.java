import java.util.ArrayList;

public class State {
     String value;
     int heuristic;
     int blankIndex;
     int pathWeight;
     int totalWeight;
     boolean visited;
     State minimalParent;
     ArrayList<State> movableStates;

    public State(String value, int blankIndex, int pathWeight) {
        this.value = value;
        this.blankIndex = blankIndex;
        this.visited = false;
        minimalParent = null;
        this.heuristic = calcHeuristic();
        this.totalWeight = pathWeight + getHeuristic();
        this.movableStates = new ArrayList<>();
    }

    public State getMinimalParent() {
        return minimalParent;
    }

    public void setMinimalParent(State minimalParent) {
        this.minimalParent = minimalParent;
    }

    public int getPathWeight() {
        return pathWeight;
    }

    public void setPathWeight(int pathWeight) {
        this.pathWeight = pathWeight;
    }

    public ArrayList<State> getMovableStates() {
        return movableStates;
    }

    public void setMovableStates(ArrayList<State> movableStates) {
        this.movableStates = movableStates;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getBlankIndex() {
        return blankIndex;
    }

    public void setBlankIndex(int blankIndex) {
        this.blankIndex = blankIndex;
    }

    public boolean isEndState(){
        if (this.heuristic == 0)
            return true;
        else
            return false;
    }

    public int calcHeuristic() {
        int heuristic = 0;

        for (int i = value.length() - 1; i >= 0; i--) {
            if (value.charAt(i) == 'W') { //if we found b
                for (int j = i; j >= 0; j--) {
                    if (value.charAt(j) == 'B') {
                        heuristic++;
                        break;
                    }
                }
            }
        }
        return heuristic;
    }

    public void expendStates() {
        int index = blankIndex - 2;
        int upperBound = this.getBlankIndex() + 2;

        if (index < 0) //lower bound
            index = 0;

        if (upperBound >= this.getValue().length())
            upperBound = this.getValue().length() - 1;

        while ((index > 0) && (index <= upperBound)) {
            if (index != blankIndex) { //exclude blank index
                State state = new State(swapCharsAtIndexes(index, blankIndex), index, this.getPathWeight());
                movableStates.add(state);
            }
            index++;
        }
    }

    private String swapCharsAtIndexes(int coloredIndex, int blankIndex) {
        StringBuilder res = new StringBuilder(this.getValue());
        char coloredChar = res.charAt(coloredIndex);
        res.setCharAt(coloredIndex, 'O');
        res.setCharAt(blankIndex, coloredChar);
        return res.toString();
    }

}
