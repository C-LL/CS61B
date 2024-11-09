package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private class Node{
        int v;
        int priority;
        public Node(int v, int priority){
            this.v = v;
            this.priority = priority;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return  Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
        /* return (int)Math.sqrt(((maze.toX(v) - maze.toX(t))*(maze.toX(v) - maze.toX(t))) +
                ((maze.toY(v) - maze.toY(t))*(maze.toY(v) - maze.toY(t)))); */
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        MinPQ<Node> pq = new MinPQ<>(Comparator.comparingInt((Node n) -> n.priority));
        pq.insert(new Node(s, h(s)));
        while(!targetFound){
            int v = pq.delMin().v;
            marked[v] = true;
            if(v == t){
                targetFound = true;
                return;
            }
            for(int w : maze.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    pq.insert(new Node(w, h(w)));
                }
            }
            announce();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

