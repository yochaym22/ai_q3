import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch {

    PriorityQueue<State> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(State::getTotalWeight));
    State startState = new State("BBBOWWW", 3, 0);
    ArrayList<String> resultPath = new ArrayList<>();
    State minimalEndState = null;

    public void runSearch(State startState) {

        //if we reached a goal state
        if (startState.isEndState()) {
            //Check if we found a better solution
            if (startState.getTotalWeight() < minimalEndState.getTotalWeight())
                minimalEndState = startState;
        }

        startState.expendStates();
        //For each son
        for (int i = 0; i < startState.getMovableStates().size(); i++) {
            //if node is visited
            if (startState.movableStates.get(i).visited) {
                //Update minimal parent
                if (startState.movableStates.get(i).getMinimalParent() != null) {
                    if (startState.movableStates.get(i).minimalParent.pathWeight >
                            startState.movableStates.get(i).pathWeight) {
                        startState.movableStates.get(i).setMinimalParent(startState);
                        //TODO update weights
                    }

                }
            } else {
                startState.movableStates.get(i).setMinimalParent(startState);
            }

        }
        //finished checking son states
        //set visited to true
        startState.setVisited(true);

        while(!priorityQueue.isEmpty()){
            runSearch(priorityQueue.poll());
        }
    }

}
