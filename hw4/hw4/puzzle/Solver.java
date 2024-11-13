package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private MinPQ<SearchNode> searchQueue;
    private List<WorldState> ret;

    public Solver(WorldState initial){
        ret = new LinkedList<>();
        searchQueue = new MinPQ<>(Comparator.comparingInt((SearchNode n)-> n.step + n.estimateStep));
        searchQueue.insert(new SearchNode(initial,0,null));
        while(!searchQueue.isEmpty()){
            SearchNode BMS = searchQueue.delMin();
            WorldState F = BMS.ws;
            if(F.isGoal()){
                BMS.Track(ret,BMS);
                return;
            }
            for(WorldState next : F.neighbors()){
                if(!BMS.euqalPre(next)){
                    searchQueue.insert(new SearchNode(next,BMS.step + 1,BMS));
                }
            }
        }
    }

    public int moves(){
        return ret.size() - 1;
    }

    public Iterable<WorldState> solution(){
        return ret;
    }

    class SearchNode{
        private WorldState ws;
        private int step;
        private int estimateStep;
        private SearchNode prev;

        public SearchNode(WorldState ws, int step, SearchNode prev){
            this.ws = ws;
            this.step = step;
            this.prev = prev;
            estimateStep = ws.estimatedDistanceToGoal();
        }
        public void Track(List<WorldState> ret,SearchNode node) {
            if(node == null){
                return;
            }
            Track(ret,node.prev);
            ret.add(node.ws);
        }
        public boolean euqalPre(WorldState next){
            return (prev != null && ws == next);
        }
    }
}