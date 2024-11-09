package lab11.graphs;



/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean cycleFound = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        distTo[0] = 0;
        edgeTo[0] = 0;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0, 0);
    }

    // Helper methods go here
    private void dfs(int v, int lastV) {
        announce();
        marked[v] = true;

        for (int nextV : maze.adj(v)) {
            if(cycleFound){
                break;
            }
            if(nextV == lastV){
                continue;
            }
            if (marked[nextV]) {
                cycleFound = true;
                edgeTo[nextV] = v;
                announce();
                return;
            } else{
                marked[nextV] = true;
                edgeTo[nextV] = v;
                announce();
                distTo[nextV] = distTo[v] + 1;
                dfs(nextV, v);
            }
        }
    }
}

