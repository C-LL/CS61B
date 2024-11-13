package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board {
    private int N;
    private int[][] grids;
    public Board(int[][] tiles){
        N = tiles.length;
        grids = new int[N][N];
        for(int i = 0; i < N; i++){
            System.arraycopy(tiles[i], 0, grids[i], 0, N);
        }
    }
    public int tileAt(int i, int j){
        if(i < 0 || i >= N || j < 0 || j >= N){
            throw new IndexOutOfBoundsException("i and j must be between 0 and "+(N-1));
        }
        return grids[i][j];
    }
    public int size(){
        return N;
    }
    //source from http://joshh.ug/neighbors.html
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int n = size();
        int blankX = -1;
        int blankY = -1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (tileAt(r, c) == 0) {
                    blankX = r;
                    blankY = c;
                }
            }
        }
        int[][] newGrid = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                newGrid[r][c] = tileAt(r, c);
            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (Math.abs(-blankX + r) + Math.abs(c - blankY) - 1 == 0) {
                    newGrid[blankX][blankY] = newGrid[r][c];
                    newGrid[r][c] = 0;
                    Board neighbor = new Board(newGrid);
                    neighbors.enqueue(neighbor);
                    newGrid[r][c] = newGrid[blankX][blankY];
                    newGrid[blankX][blankY] = 0;
                }
            }
        }
        return neighbors;
    }
    public int hamming(){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(grids[i][j] != 0 && grids[i][j] != (i*N+j+1)){
                    count++;
                }
            }
        }
        return count;
    }
    public int manhattan(){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(grids[i][j] != 0){
                    count += Math.abs(grids[i][j] / N - i) + Math.abs(grids[i][j] % N - j);
                }
            }
        }
        return count;
    }
    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public boolean equals(Object y){
        if(this.getClass() != y.getClass()) {
            return false;
        }
        Board yb = (Board)y;
        if(yb.size() != N){
            return false;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(grids[i][j] != yb.tileAt(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
